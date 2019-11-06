package multi_thread;

/**
 * @author fatKarin
 * @date 2019/11/6 15:14
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * 客户端线程
 */
public class Client implements Runnable {
    @Override
    public void run() {
        for(;;) {
            try {
                //构建IO
                Socket s = new Socket("127.0.0.1",5678);
                s.setSoTimeout(3000);
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                PrintWriter printWriter=new PrintWriter(os, true);//将输出流包装成打印流
                printWriter.println("hello,I'm " + new Random().nextInt());
                Thread.sleep(500);
                System.out.println("是否建立连接：" + s.isConnected());
            }
            catch (Exception E) {
                E.printStackTrace();
            }


        }
    }
}
