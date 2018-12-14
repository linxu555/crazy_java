package main.test;

import main.java.thread.ThreadPoolDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: linxueqin
 * @Description: 线程池测试类
 * @date: Created on 2018/12/13 17:16
 **/
public class ThreadPoolTest {
    public static void main(String[] args){
        //创建一个具有固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(6);

        //向线程池中提交两个线程
        pool.submit(new ThreadPoolDemo());
        pool.submit(new ThreadPoolDemo());

        //关闭线程池
        pool.shutdown();
    }
}
