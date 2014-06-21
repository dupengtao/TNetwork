package com.dpt.TNetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.dpt.TNetwork.engine.WalletEngine;
import com.dpt.TNetwork.engine.domain.WalletAccountQuery;
import com.dpt.TNetwork.net.listener.DefaultTOListener;
import com.dpt.TNetwork.net.listener.INetClientJsonListener;
import com.dpt.TNetwork.net.util.NetClient;
import com.dpt.TNetwork.net.util.UriHelper;
import org.json.JSONObject;

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

    public void openImageActivity(View view){
        Intent intent = new Intent(this,NetImageActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initEngine();
        loadDate();
    }

    private void loadDate() {

        //loadTOType();

        //loadJsonType();

        //onlyLoadInCache();

        loadPostJsonType();
    }

    private void loadPostJsonType() {

    }

    /**
     * load in cache
     */
    private void onlyLoadInCache() {
        String s = NetClient.loadInCache(UriHelper.getWalletHomeUri());
        if(TextUtils.isEmpty(s)){
            mTv.setText("nothing in cache");
        }else {
            mTv.setText(s);
        }
    }


    private void loadJsonType() {
        engine.loadWalletHome(new INetClientJsonListener() {
            @Override
            public void onSuccess(JSONObject jsonObject, String[] otherMsg) {
                mTv.setText(jsonObject.toString());
            }

            @Override
            public void onFailure(Throwable e, String content) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onNotNetwork() {

            }
        });
    }

    private void loadTOType() {
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
