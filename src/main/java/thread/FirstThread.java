package main.java.thread;

/**
 * @author: linxueqin
 * @Description: TODO
 * @date: Created on 2018/12/7 20:08
 **/
public class FirstThread extends Thread{
    private int i;

    @Override
    public void run() {
        for (; i < 100; i++){
            System.out.println(getName() + "------" + i);
        }
    }

    public static void main(String[] args){
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + "......."+i);
            if(i == 20){
                //创建并启动第一个线程
                new FirstThread().start();
                //创建并启动第二个线程
                new FirstThread().start();
            }
        }
    }
}
