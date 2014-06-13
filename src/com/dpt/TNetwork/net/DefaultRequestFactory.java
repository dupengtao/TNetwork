package com.dpt.TNetwork.net;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by dupengtao on 2014/6/13.
 */
public class DefaultRequestFactory implements RequestFactory {
    @Override
    public Request produceStrRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest strRequest = new StringRequest(method, url, listener, errorListener);
        return strRequest;
    }

    @Override
    public Request produceJsonRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        return null;
    }


}
