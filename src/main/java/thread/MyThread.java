package main.java.thread;

/**
 * @author: linxueqin
 * @Description: 用于线程池练习的线程类
 * @date: Created on 2018/12/14 9:18
 **/
public class MyThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName()+"---的i值为："+i);
        }
    }
}
