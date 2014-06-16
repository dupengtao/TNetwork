package com.dpt.TNetwork.net.listener;

/**
 * Created by dupengtao on 2014/6/16.
 */
public abstract class DefaultTOListener<T> extends AbNetClientTOListener<T> {

    public DefaultTOListener(Class<T> clazz) {
        super(clazz);
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
}
