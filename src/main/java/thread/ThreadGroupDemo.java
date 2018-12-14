package main.java.thread;

/**
 * @author: linxueqin
 * @Description: 线程组类
 * @date: Created on 2018/12/13 16:06
 **/
public class ThreadGroupDemo extends Thread {
    public ThreadGroupDemo(String name) {
        super(name);
    }

    public ThreadGroupDemo(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++){
            System.out.println(getName()+"线程的i变量："+i);
        }
    }
}
