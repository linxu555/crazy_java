package main.java.url;

/**
 * @author: linxueqin
 * @Description: TODO
 * @date: Created on 2018/12/14 15:00
 **/
public class MultiThreadDown {
    public static void main(String[] args) throws Exception{
        //初始化DownloadUtil对象
        final DownloadUtil downloadUtil = new DownloadUtil("https://github.com/dyc87112/SpringBoot-Learning.git","SpringBoot-Learning-master.zip",4);
        //开始下载
        downloadUtil.download();
        new Thread(){
            @Override
            public void run() {
                while (downloadUtil.getCompleteRate() < 1){
                    System.out.println("已完成："+downloadUtil.getCompleteRate());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
