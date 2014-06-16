package com.dpt.TNetwork.engine;

import com.dpt.TNetwork.net.listener.INetClientStrListener;
import com.dpt.TNetwork.net.util.UriHelper;

/**
 * Created by dupengtao on 2014/6/16.
 */
public class WalletEngine extends BaseEngine {


    public void loadWalletHome(INetClientStrListener resultCb){
        mNetClient.excuteRequest(UriHelper.getWalletHomeUri(),getDefaultHeadParams(),resultCb);
    }
}
