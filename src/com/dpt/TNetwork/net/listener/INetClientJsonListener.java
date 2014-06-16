package com.dpt.TNetwork.net.listener;

import org.json.JSONObject;

/**
 *
 * @author dupengtao@cyou-inc.com
 *         <p/>
 *         2014-3-13
 */
public interface INetClientJsonListener extends INetClientBaseListener {
    void onSuccess(JSONObject jsonObject,String[] otherMsg);
}
