package main.java.communication;

import java.util.concurrent.BlockingQueue;

/**
 * @author: linxueqin
 * @Description: 生产者类
 * @date: Created on 2018/12/13 10:57
 **/
public class Producer extends Thread {

    private BlockingQueue<String> bq;

    public Producer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        String[] strArr = new String[]{
                "Java",
                "Struts",
                "Spring"
        };
        for (int i = 0;1<10; i++){
            System.out.println(getName()+"生产者准备生产集合元素！");
            try {
                Thread.sleep(200);
                //尝试放入元素，如果队列已满，则线程被阻塞
                bq.put(strArr[i % 3]);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println(getName()+"生产完成："+bq);
        }

    }
}
