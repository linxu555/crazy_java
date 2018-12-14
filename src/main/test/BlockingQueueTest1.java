package main.test;

import main.java.communication.Consumer;
import main.java.communication.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: linxueqin
 * @Description: 阻塞队列控制线程通信 测试类
 * @date: Created on 2018/12/13 11:08
 **/
public class BlockingQueueTest1 {
    public static void main(String[] args){
        //创建一个容量为1的BlockingQueue
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
        new Producer(bq).start();
        new Producer(bq).start();
        new Producer(bq).start();
        //启动一个消费者线程
        new Consumer(bq).start();
    }
}
