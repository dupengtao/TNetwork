package com.dpt.TNetwork.net;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONObject;

import java.util.Map;

import static com.android.volley.Request.Method;

/**
 * Created by dupengtao on 2014/6/13.
 */
public class DefaultRequestFactory {
    public  StringRequest produceStrRequest(int method, String url, final Map<String, String> headParams, final Map<String, String> postParams, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest strRequest = new StringRequest(method, url, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headParams != null && !headParams.isEmpty()) {
                    return headParams;
                }
                return super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return postParams;
            }
        };
        return strRequest;
    }

    public  StringRequest produceStrRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return produceStrRequest(url, null, listener, errorListener);
    }

    public  StringRequest produceStrRequest(String url, Map<String, String> headParams, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return produceStrRequest(Method.GET, url, headParams, null, listener, errorListener);
    }

    /**
     * easy to make a post StringRequesty
     */
    public  StringRequest producePostStrRequest(String url, Map<String, String> headParams, Map<String, String> postParams, Response.Listener<String> listener, Response.ErrorListener errorListener){
        return produceStrRequest(Method.POST, url, headParams, postParams, listener, errorListener);
    }

    public  Request produceJsonRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        return null;
    }
}
