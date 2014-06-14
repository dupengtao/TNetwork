package com.dpt.TNetwork.net.util;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dpt.TNetwork.application.TNetworkApp;
import com.dpt.TNetwork.net.DefaultRequestFactory;
import com.dpt.TNetwork.net.VolleyErrorHelper;
import com.dpt.TNetwork.net.interfaces.INetClientBaseListener;
import com.dpt.TNetwork.net.interfaces.INetClientStrListener;
import com.dpt.TNetwork.util.LogHelper;

import java.util.Map;

/**
 * Created by dupengtao on 2014/6/13.
 */
public class NetClent {

    private static final String TAG = NetClent.class.getSimpleName();
    private static NetClent mNetClent;
    private final DefaultRequestFactory mRequestFactory;

    private NetClent() {
        mRequestFactory = new DefaultRequestFactory();
    }

    public static synchronized NetClent init() {
        if (mNetClent == null) {
            mNetClent = new NetClent();
        }
        return mNetClent;
    }

    public void excuteRequest(Request request, String tag) {
        TNetworkApp.getInstance().getVolleyController().addToRequestQueue(request, tag);
    }

    public void excuteRequest(Request request) {
        TNetworkApp.getInstance().getVolleyController().addToRequestQueue(request);
    }

    public void excuteRequest(int method, String url, Map<String, String> headParams, Map<String, String> postParams, boolean isShouldCache,String tag,INetClientStrListener listener) {
        StringRequest strRequest = mRequestFactory.produceStrRequest(method, url, headParams, postParams, makeStrListener(listener), makeErrorListener(listener));
        if (isShouldCache){
            setNoCache(strRequest);
        }
        excuteRequest(strRequest,tag);
    }

    /**
     * a simple get string request
     */
    public void excuteRequest(String url,INetClientStrListener listener) {
        StringRequest strRequest = mRequestFactory.produceStrRequest(url,makeStrListener(listener), makeErrorListener(listener));
        excuteRequest(strRequest);
    }
    /**
     * a simple get string request with headParams
     */
    public void excuteRequest(String url,Map<String,String> headParams,INetClientStrListener listener) {
        StringRequest strRequest = mRequestFactory.produceStrRequest(url,headParams,makeStrListener(listener), makeErrorListener(listener));
        excuteRequest(strRequest);
    }
    /**
     * a simple post string request
     */
    public void excutePostStrRequset(String url,Map<String,String> headParams,Map<String, String> postParams,INetClientStrListener listener){
        StringRequest strRequest = mRequestFactory.producePostStrRequest(url,headParams,postParams,makeStrListener(listener),makeErrorListener(listener));
        excuteRequest(strRequest);
    }



    private Request setNoCache(Request request) {
        request.setShouldCache(false);
        return request;
    }

    private Response.Listener<String> makeStrListener(final INetClientStrListener strListener) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                strListener.onSuccess(response,null);
                strListener.onFinish();
            }
        };
        return listener;
    }

    private Response.ErrorListener makeErrorListener(final INetClientBaseListener listener) {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                processError(error, listener);
            }
        };
        return errorListener;
    }

    private void processError(VolleyError error, INetClientBaseListener listener) {
        String message = "";
        try {
            message = VolleyErrorHelper.getMessage(error);
            message += "statusCode" + error.networkResponse.statusCode;
            if (VolleyErrorHelper.ERROR_NO_INTERNET == message) {
                //没有网络的回调
                listener.onNotNetwork();
            } else {
                //请求失败的回调
                listener.onFailure(error, message);
            }
            LogHelper.e(TAG, message);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onFailure(error, message);
        } finally {
            //请求完成的回调
            listener.onFinish();
        }
    }

}
