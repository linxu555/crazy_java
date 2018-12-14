package main.java.url;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: linxueqin
 * @Description: 多线程下载工具类
 * @date: Created on 2018/12/14 11:28
 **/
public class DownloadUtil {

    /**
     * 定义下载资源的路径
     */
    private String path;
    /**
     * 指定下载的文件的保存位置
     */
    private String targetFile;
    /**
     * 定义需要使用多少个线程下载资源
     */
    private int threadNum;
    /**
     * 定义下载的线程对象
     */
    private DownThread[] threads;

    /**
     * 定义下载的文件的总大小
     */
    private long fileSize;

    public DownloadUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        //初始化threads数组
        threads = new DownThread[threadNum];

    }

    /**
     * 实现多线程下载
     * @throws Exception
     */
    public void download() throws Exception{
        //创建URL对象
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty(
                "Accept",
                "image/gif,image/jpeg,image/pjpeg, "
                        + "application/x-shockwave-flash,application/xaml+xml,"
                        +"application/vnd.ms-xpsdocument,application/x-ms-xbap, "
                        +"application/x-ms-application,application/vnd.ms-excel"
                        + "application/vnd.ms-powerpoint,application/msword, */*");
        conn.setRequestProperty("Accept-Language","zh-CN");
        conn.setRequestProperty("Charset","UTF-8");
        conn.setRequestProperty("Connection","Keep-Alive");
        //得到文件大小
        fileSize = conn.getContentLength();
        conn.disconnect();
        long currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile,"rw");
        //设置本地文件的大小
        file.setLength(fileSize);
        file.close();
        for (int i = 0; i< threadNum; i++){
            //计算每个线程下载的开始位置
            long startPos = i * currentPartSize;
            //每个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile,"rw");
            //定位该线程的下载位置
            threads[i] = new DownThread(startPos,currentPartSize,currentPart);
            //启动下载线程
            threads[i].start();
        }

    }

    /**
     * 获取下载的完成百分比
     * @return 完成的百分比
     */
    public double getCompleteRate(){
        //统计多个线程已经下载的总大小
        long sumSize = 0;
        for (int i = 0; i < threadNum; i++){
            sumSize += threads[i].length;
        }
        //返回已经完成的百分比
        return sumSize * 1.0 / fileSize;
    }

    /**
     * 下载线程： 负责读取从start开始，到end结束的所有字节数据
     * 并写入RandomAccessFile对象
     */
    private class DownThread extends Thread{
        /**
         * 当前线程的下载位置
         */
        private long startPos;

        /**
         * 定义当前线程负责下载的文件大小
         */
        private long currentPartSize;

        /**
         * 当前线程需要下载的文件快
         */
        private RandomAccessFile currentPart;

        /**
         * 定义该线程已下载的字节数
         */
        public long length;

        public DownThread(long startPos, long currentPartSize,
                          RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5*1000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty(
                     "Accept",
                     "image/gif,image/jpeg,image/pjpeg, "
                     + "application/x-shockwave-flash,application/xaml+xml,"
                     +"application/vnd.ms-xpsdocument,application/x-ms-xbap, "
                     +"application/x-ms-application,application/vnd.ms-excel"
                     + "application/vnd.ms-powerpoint,application/msword, */*");
                conn.setRequestProperty("Accept-Language","zh-CN");
                conn.setRequestProperty("Charset","UTF-8");
                InputStream inputStream = conn.getInputStream();
                //跳过startPos个字节，表名该线程只下载自己负责的那部分文件
                inputStream.skip(this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                //读取网络数据，并写入本地文件
                while (length < currentPartSize
                        && (hasRead = inputStream.read(buffer)) != -1)
                {
                    currentPart.write(buffer,0,hasRead);
                    //累计该线程下载的总大小
                    length += hasRead;
                }
                currentPart.close();
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
