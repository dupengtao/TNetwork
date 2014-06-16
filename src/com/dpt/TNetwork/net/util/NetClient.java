package com.dpt.TNetwork.net.util;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.dpt.TNetwork.application.TNetworkApp;
import com.dpt.TNetwork.net.DefaultRequestFactory;
import com.dpt.TNetwork.net.VolleyErrorHelper;
import com.dpt.TNetwork.net.listener.INetClientBaseListener;
import com.dpt.TNetwork.net.listener.INetClientJsonListener;
import com.dpt.TNetwork.net.listener.INetClientStrListener;
import com.dpt.TNetwork.util.LogHelper;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by dupengtao on 2014/6/13.
 */
public class NetClient {

    private static final String TAG = NetClient.class.getSimpleName();
    private static NetClient mNetClient;
    private final DefaultRequestFactory mRequestFactory;

    private NetClient() {
        mRequestFactory = new DefaultRequestFactory();
    }

    public static synchronized NetClient init() {
        if (mNetClient == null) {
            mNetClient = new NetClient();
        }
        return mNetClient;
    }

    public void excuteRequest(Request request, String tag) {
        TNetworkApp.getInstance().getVolleyController().addToRequestQueue(request, tag);
    }

    /**
     * @param request
     */
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

    // json
    public void excuteJsonRequest(int method, String url, Map<String, String> headParams, Map<String, String> postParams, boolean isShouldCache,String tag,INetClientJsonListener listener) {
        JsonObjectRequest jsonObjectRequest = mRequestFactory.produceJsonRequest(method, url, headParams, postParams, makeJsonListener(listener), makeErrorListener(listener));
        if (isShouldCache){
            setNoCache(jsonObjectRequest);
        }
        excuteRequest(jsonObjectRequest,tag);
    }

    /**
     * a simple get json request
     */
    public void excuteJsonRequest(String url,INetClientJsonListener listener) {
        JsonObjectRequest jsonObjectRequest = mRequestFactory.produceJsonRequest(url,makeJsonListener(listener), makeErrorListener(listener));
        excuteRequest(jsonObjectRequest);
    }
    /**
     * a simple get json request with headParams
     */
    public void excuteJsonRequest(String url,Map<String,String> headParams,INetClientJsonListener listener) {
        JsonObjectRequest jsonObjectRequest = mRequestFactory.produceJsonRequest(url,headParams,makeJsonListener(listener), makeErrorListener(listener));
        excuteRequest(jsonObjectRequest);
    }
    /**
     * a simple post json request
     */
    public void excutePostJsonRequset(String url,Map<String,String> headParams,Map<String, String> postParams,INetClientJsonListener listener){
        JsonObjectRequest jsonObjectRequest = mRequestFactory.producePostJsonRequest(url,headParams,postParams,makeJsonListener(listener),makeErrorListener(listener));
        excuteRequest(jsonObjectRequest);
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

    private Response.Listener<JSONObject> makeJsonListener(final INetClientJsonListener jsonListener) {
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                jsonListener.onSuccess(jsonObject,null);
                jsonListener.onFinish();
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

            if (VolleyErrorHelper.ERROR_NO_INTERNET == message) {
                //没有网络的回调
                listener.onNotNetwork();
            } else {
                message += "statusCode" + error.networkResponse.statusCode;
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
