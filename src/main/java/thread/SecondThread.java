package main.java.thread;

import java.lang.annotation.Target;

/**
 * @author: linxueqin
 * @Description: TODO
 * @date: Created on 2018/12/10 12:26
 **/
public class SecondThread implements Runnable {
    private int i;

    @Override
    public void run() {
        for (; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + "------" + i);
        }
    }

    public static void main(String[] args){
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + "*****"+i);
            if(i == 20){
                SecondThread secondThread = new SecondThread();
                new Thread(secondThread,"新线程1").start();
                new Thread(secondThread,"新线程2").start();
            }
        }
    }
}
