package main.test;

import main.java.thread.Account;
import main.java.thread.DrawThread;

/**
 * @author: linxueqin
 * @Description: TODO
 * @date: Created on 2018/12/10 19:19
 **/
public class DrawTest {
    public static void main(String[] args){
        //创建一个账户
        Account acct = new Account("1234567",1000);

        //模拟两个用户对同一个账户取钱
        new DrawThread("甲",acct,800).start();
        new DrawThread("乙",acct,800).start();
    }
}
