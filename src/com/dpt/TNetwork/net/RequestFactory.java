package com.dpt.TNetwork.net;

import com.android.volley.Request;
import com.android.volley.Response;
import org.json.JSONObject;

/**
 * Created by dupengtao on 2014/6/13.
 */
public interface RequestFactory {
    /**
     * produce string type request
     */
    Request produceStrRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener);

    /**
     * produce json type request
     */
    Request produceJsonRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener);


}
