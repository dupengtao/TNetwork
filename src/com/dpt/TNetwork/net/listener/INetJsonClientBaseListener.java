package com.dpt.TNetwork.net.listener;

import org.json.JSONObject;

/**
 * 网络访问回调
 *
 * @author dupengtao@cyou-inc.com
 *         <p/>
 *         2014-3-13
 */
public interface INetJsonClientBaseListener extends INetClientBaseListener {
    void onSuccessCallBack(JSONObject jsonObject);
}
