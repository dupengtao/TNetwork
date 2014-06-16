package com.dpt.TNetwork;

import android.app.Activity;
import android.os.Bundle;
import com.dpt.TNetwork.engine.WalletEngine;
import com.dpt.TNetwork.engine.domain.WalletAccountQuery;
import com.dpt.TNetwork.net.listener.DefaultTOListener;
import com.dpt.TNetwork.util.LogHelper;

public class NetworkActivity extends Activity {
    private static final String TAG = NetworkActivity.class.getSimpleName();
    private WalletEngine engine;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initEngine();
        loadDate();
    }

    private void loadDate() {

        engine.loadWalletHome(new DefaultTOListener<WalletAccountQuery>(WalletAccountQuery.class) {
            @Override
            public void onSuccess(WalletAccountQuery walletAccountQuery, String[] otherMsg) {
                LogHelper.e(TAG,walletAccountQuery.toString());
            }
        });
    }

    private void initEngine() {
         engine = new WalletEngine();
    }
}
