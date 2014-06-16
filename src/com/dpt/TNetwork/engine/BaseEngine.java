package com.dpt.TNetwork.engine;

import com.dpt.TNetwork.net.util.NetClient;
import com.dpt.TNetwork.net.util.UriHelper;

import java.util.Map;

/**
 * Created by dupengtao on 2014/6/16.
 */
public class BaseEngine {

    protected final NetClient mNetClient;

    public BaseEngine() {
         mNetClient = NetClient.init();
    }

   protected Map<String,String> getDefaultHeadParams(){
       return UriHelper.getDefaultHeadParams();
   }
}
