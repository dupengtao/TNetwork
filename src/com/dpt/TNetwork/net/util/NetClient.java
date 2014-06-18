package com.dpt.TNetwork.net.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.dpt.TNetwork.application.TNetworkApp;
import com.dpt.TNetwork.net.AbAnimImageListener;
import com.dpt.TNetwork.net.DefaultRequestFactory;
import com.dpt.TNetwork.net.VolleyErrorHelper;
import com.dpt.TNetwork.net.listener.INetClientBaseListener;
import com.dpt.TNetwork.net.listener.INetClientJsonListener;
import com.dpt.TNetwork.net.listener.INetClientStrListener;
import com.dpt.TNetwork.util.LogHelper;
import org.json.JSONObject;

import java.io.File;
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


    private static Request setNoCache(Request request) {
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

    //image
    public static ImageLoader.ImageContainer loadImage(String url, ImageView imageView, int loadingResId, int errorResId) {
        return loadImage(url,imageView,loadingResId,errorResId,0,0);
    }

    public static ImageLoader.ImageContainer loadImage(String url, ImageView imageView, int loadingResId, int errorResId,int maxWidth, int maxHeight) {
        ImageLoader imageLoader = TNetworkApp.getInstance().getVolleyController().getImageLoader();
        return imageLoader.get(url, ImageLoader.getImageListener(imageView, loadingResId, errorResId), maxWidth, maxHeight);
    }

    /**
     * if do not use xml anim , you should use{@link com.android.volley.toolbox.ImageLoader#get(String, com.android.volley.toolbox.ImageLoader.ImageListener)}
     * eg.
     * @see {@link com.dpt.TNetwork.net.AbAnimImageListener}
     * @param loadingResId if loadingResId is 0 ,ImageView will not loading image
     * @param animResId anim in xml
     */
    public static void loadImageWithAnim(Context context,String url, ImageView imageView, int loadingResId, int errorResId, final int animResId){
        TNetworkApp.getInstance().getVolleyController().getImageLoader().get(url,new AbAnimImageListener(context,imageView,errorResId,loadingResId) {
            @Override
            public int getAnimResId() {
                if(animResId<1){
                    return 0;
                }
                return animResId;
            }
        });
    }

    /**
     *
     * @param isShouldCache if false will not in cache
     */
    public static ImageRequest loadImage(String url, final ImageView imageView, int loadingResId, final int errorResId,int maxWidth, int maxHeight,boolean isShouldCache,Bitmap.Config decodeConfig,String tag){
        ImageRequest imgRequest = getImageRequest(url, imageView, loadingResId, errorResId, maxWidth, maxHeight, isShouldCache, decodeConfig);
        TNetworkApp.getInstance().getVolleyController().addToRequestQueue(imgRequest, tag);
        return imgRequest;
    }

    private static ImageRequest getImageRequest(String url, final ImageView imageView, int loadingResId, final int errorResId, int maxWidth, int maxHeight, boolean isShouldCache, Bitmap.Config decodeConfig) {
        if(loadingResId>0){
            imageView.setImageResource(loadingResId);
        }
        if(maxWidth<1){
            maxWidth=0;
        }
        if (maxHeight<1){
            maxHeight=0;
        }
        ImageRequest imgRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                //TODO 这里面并没有放到lruCache中
                imageView.setImageBitmap(response);
            }
        }, maxWidth, maxHeight, decodeConfig, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(errorResId);
            }
        });
        if(!isShouldCache){
            setNoCache(imgRequest);
        }
        return imgRequest;
    }

    public static ImageRequest loadImageByRequest(String url, ImageView imageView, int loadingResId, int errorResId) {
        return loadImage(url,imageView,loadingResId,errorResId,0,0,true, Bitmap.Config.RGB_565,null);
    }

    public static void cacheRemove(String url) {
        TNetworkApp.getInstance().getVolleyController().getRequestQueue().getCache().remove(url);
    }

    public static void cacheClear() {
        TNetworkApp.getInstance().getVolleyController().getRequestQueue().getCache().clear();
    }

    public static void cancelSingleRequest(String reqTag) {
        TNetworkApp.getInstance().getVolleyController().getRequestQueue().cancelAll(reqTag);
    }

    public static void cancelAllRequests() {
        TNetworkApp.getInstance().getVolleyController().getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    /**
     * count cache size
     */
    public static long getCacheSize(Context context) {
        File cacheDir = new File(context.getCacheDir(), "volley");
        return cacheDir.length();
    }

}
