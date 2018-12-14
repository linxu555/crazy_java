package main.java.communication;

import java.util.concurrent.BlockingQueue;

/**
 * @author: linxueqin
 * @Description: 消费者类
 * @date: Created on 2018/12/13 11:04
 **/
public class Consumer extends Thread {
    private BlockingQueue<String> bq;

    public Consumer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(getName()+"消费者准备消费集合元素！");
            try {
                Thread.sleep(200);
                //尝试取出元素，如果队列已空，则线程被阻塞
                bq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName()+"消费完成："+bq);
        }
    }
}
