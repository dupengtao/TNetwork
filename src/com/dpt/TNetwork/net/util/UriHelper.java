package com.dpt.TNetwork.net.util;

import com.dpt.TNetwork.util.LogHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dupengtao on 2014/6/16.
 */
public class UriHelper {

    private static final String TAG=UriHelper.class.getSimpleName();
    private static Map<String,String> heads;
    /**
     * 主机地址
     */
    public static final String SERVER_HOST;
    public static final String SERVER_FUNDS_HOST;

    static {
        if (false) {// 线上
            SERVER_HOST = "http://iuu.changyou.com/m/";
            SERVER_FUNDS_HOST = "http://pay.iuu.changyou.com/";//
        } else {// 线下
            //SERVER_HOST = "http://10.12.0.47:8080/mm/m/";// 方琪
//             SERVER_HOST = "http://10.12.1.2:8080/mm/m/";// 冯越
            // SERVER_HOST = "http://111.206.74.123:8080/mm/m/";//外网
            SERVER_HOST = "http://10.127.129.126:8080/mm/m/";// 内网
//             SERVER_HOST = "http://10.12.0.47:8080/mm/m/";// fq
//            SERVER_HOST = "http://10.127.130.164:8080/mm/m/";// test
            // SERVER_FUNDS_HOST="http://10.12.13.21:8080/member/";
//             SERVER_FUNDS_HOST="http://10.12.14.12:8080/member/";
            SERVER_FUNDS_HOST = "http://10.127.129.126:8080/member/";// 内网
//             SERVER_FUNDS_HOST = "http://paytest.cyg.changyou.com/";//
//             SERVER_FUNDS_HOST = "http://10.127.130.164:8080/member/";// test
//             SERVER_FUNDS_HOST = "http://10.12.1.2:8080/member/";//冯越
//            SERVER_FUNDS_HOST = "http://10.12.0.47:8080/member/";// 方琪
        }
    }


    public static Map<String,String> getDefaultHeadParams(){
        if(heads==null){
            heads= new HashMap<String, String>();
            //添加默认的头

            heads.put("JSESSIONID", "539e9c31cf2dcecffe1a0e4");// 登录标识
            heads.put("TOKEN", "67b62751b35b0e20d83bf96f75cd37c7");// token
            heads.put("IMEI", "352910051162513");
            heads.put("TICKET", "7ffca554c5714b4464261b2af67193212pLq/MTGZl7se6TihkEKsw==");
            heads.put("CUCUMBER", "38,59,36,50,47,61,39,15,41,4,39,44,10,53,36,55,");
        }
        return heads;
    }

    public static String getWalletHomeUri(){
        StringBuilder builder = new StringBuilder(SERVER_FUNDS_HOST);
        builder.append("user_account/query");
        LogHelper.e(TAG, builder.toString());
        return builder.toString();
    }
}
