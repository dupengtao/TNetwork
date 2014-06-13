package com.dpt.TNetwork.net.util;

/**
 * Created by dupengtao on 2014/6/13.
 */
public class NetClent {

    private static NetClent mNetClent;

    private NetClent() {
    }

    public static synchronized NetClent init() {
        if (mNetClent == null) {
            mNetClent = new NetClent();
        }
        return mNetClent;
    }

    public void excuteRequest() {

    }
}
