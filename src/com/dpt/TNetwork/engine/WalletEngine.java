package com.dpt.TNetwork.engine;

import com.dpt.TNetwork.net.listener.INetClientJsonListener;
import com.dpt.TNetwork.net.listener.INetClientStrListener;
import com.dpt.TNetwork.net.util.UriHelper;

/**
 * Created by dupengtao on 2014/6/16.
 */
public class WalletEngine extends BaseEngine {

    //str
    public void loadWalletHome(INetClientStrListener resultCb){
        mNetClient.excuteRequest(UriHelper.getWalletHomeUri(),getDefaultHeadParams(),resultCb);
    }
    //json
    public void loadWalletHome(INetClientJsonListener resultCb){
        mNetClient.excuteJsonRequest(UriHelper.getWalletHomeUri(),getDefaultHeadParams(),resultCb);
    }
}
