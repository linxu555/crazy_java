package main.java.thread;


/**
 * @author: linxueqin
 * @Description: TODO
 * @date: Created on 2018/12/10 19:09
 **/
public class DrawThread extends Thread {
    /**
     * 模拟用户账户
     */
    private Account account;

    /**
     * 当前取钱线程希望取出的钱数
     */
    private double drawAccount;

    public DrawThread(String name,Account account, double drawAccount) {
        super(name);
        this.account = account;
        this.drawAccount = drawAccount;
    }

    /**
     * 当多个线程修改同一个共享数据时，将设计数据安全问题
     * 使用同步代码块
     */
    @Override
    public void run() {
        synchronized (account) {
            if (account.getBalance() >= drawAccount) {
                System.out.println(getName() + "取钱成功！吐出钞票：" + drawAccount);

                try {
                    Thread.sleep(1);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }

                //修改余额
                account.setBalance(account.getBalance() - drawAccount);
                System.out.println("\t余额为：" + account.getBalance());
            } else {
                System.out.println(getName() + "取钱失败！余额不足！");
            }
        }

    }
}
