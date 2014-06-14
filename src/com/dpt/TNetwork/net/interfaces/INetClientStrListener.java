package com.dpt.TNetwork.net.interfaces;

/**
 * 网络访问回调
 *
 * @author dupengtao@cyou-inc.com
 *         <p/>
 *         2014-3-13
 */
public interface INetClientStrListener extends INetClientBaseListener {
    /**
     * on success callback
     *
     * @param otherMsg some message default is null
     */
    void onSuccess(String content, String[] otherMsg);
}
