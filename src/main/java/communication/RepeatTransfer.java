package main.java.communication;

/**
 * @author: linxueqin
 * @Description: 重复存款、取款且不能连续
 * @date: Created on 2018/12/12 11:28
 **/
public class RepeatTransfer{
    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 存款
     */
    private double balance;
    /**
     * 标识账户中是否已有存款
     */
    private boolean flag = true;

    public RepeatTransfer() {}

    public RepeatTransfer(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public synchronized void account(double drawAmount){
        try {
            if(!flag){
                wait();
            }else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName()+"取钱："+drawAmount);
                balance -= drawAmount;
                System.out.println("账户余额为："+balance);
                flag = false;
                notifyAll();
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }




}
