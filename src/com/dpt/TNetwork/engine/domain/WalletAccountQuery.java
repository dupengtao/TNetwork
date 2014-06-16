package com.dpt.TNetwork.engine.domain;

/**
 * {
 * "errorCode": 200,
 * "errorMsg": "SUCCESS",
 * "userName": "都彭韬",
 * "cellphone": "18618321722",
 * "balance": 995823,
 * "frozenBalance": 0,
 * "accountSafeType": 1,
 * "bankAmount": 3
 * }
 * 
 * @author dupengtao@cyou-inc.com
 *         2014-4-22
 */
public class WalletAccountQuery extends CustomTO {
    public String userName;
    public String cellphone;
    public String balance;
    public String frozenBalance;
    public int accountSafeType;
    public String bankAmount;

    @Override
    public String toString() {
        return "WalletAccountQuery{" +
                "userName='" + userName + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", balance='" + balance + '\'' +
                ", frozenBalance='" + frozenBalance + '\'' +
                ", accountSafeType=" + accountSafeType +
                ", bankAmount='" + bankAmount + '\'' +
                '}';
    }
}
