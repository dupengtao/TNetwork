package com.dpt.TNetwork.application;

import android.app.Application;
import com.dpt.TNetwork.net.VolleyController;

/**
 * Created by dupengtao on 2014/6/13.
 */
public class TNetworkApp extends Application {


    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static TNetworkApp sInstance;
    private VolleyController mVolleyController;

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the singleton
        sInstance = this;
        mVolleyController = VolleyController.getInstance(getApplicationContext());
    }

    /**
     * @return TNetworkApp singleton instance
     */
    public static synchronized TNetworkApp getInstance() {
        return sInstance;
    }

    public VolleyController getVolleyController() {
        return mVolleyController;
    }
}
