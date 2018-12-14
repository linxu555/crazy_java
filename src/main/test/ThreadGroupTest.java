package main.test;

import main.java.thread.ThreadGroupDemo;

/**
 * @author: linxueqin
 * @Description: TODO
 * @date: Created on 2018/12/13 16:09
 **/
public class ThreadGroupTest {
    public static void main(String[] args){
        //获取主线程所在的线程组，这是所有线程默认的线程组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字是："+mainGroup.getName());
        System.out.println("主线程是否是后台线程："+mainGroup.isDaemon());
        new ThreadGroupDemo("主线程组的线程").start();

        ThreadGroup tg = new ThreadGroup("新线程组");
        System.out.println("tg线程组的名字是："+tg.getName());

        tg.setDaemon(true);
        System.out.println("tg线程组是否是后台线程组："+tg.isDaemon());
        ThreadGroupDemo threadGroupDemo = new ThreadGroupDemo(tg,"tg组的线程甲");
        threadGroupDemo.start();
        new ThreadGroupDemo(tg,"tg组的线程乙").start();

    }
}
