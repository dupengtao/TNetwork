package com.dpt.TNetwork;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.dpt.TNetwork.engine.WalletEngine;
import com.dpt.TNetwork.engine.domain.WalletAccountQuery;
import com.dpt.TNetwork.net.listener.DefaultTOListener;

public class NetworkActivity extends Activity {
    private static final String TAG = NetworkActivity.class.getSimpleName();
    private WalletEngine engine;
    private TextView mTv;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTv = (TextView) findViewById(R.id.tv);
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
                mTv.setText(walletAccountQuery.toString());
            }

            @Override
            public void onNotNetwork() {
                mTv.setText("on Not Network");
            }

            @Override
            public void onFailure(Throwable e, String content) {
                mTv.setText("on Failure");
            }
        });
    }

    private void initEngine() {
         engine = new WalletEngine();
    }
}
