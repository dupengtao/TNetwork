package com.dpt.TNetwork.net.listener;

/**
 * 网络访问回调
 *
 * @author dupengtao@cyou-inc.com
 *         <p/>
 *         2014-3-13
 */
public interface INetClientBaseListener {
    void onFailure(Throwable e, String content);

    void onStart();

    void onFinish();

    void onNotNetwork();
}
