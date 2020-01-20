package multi_thread;

/**
 * @author fatKarin
 * @date 2019/11/6 15:14
 */

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientRequest {
    public static void main(String[] args) throws InterruptedException {


        Thread clientThread = new Thread(new Client());
        clientThread.start();
    }
}

/**
 * 客户端线程
 */
class Client implements Runnable {
    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(;;) {
            try {
                //构建IO
                Socket socket = new Socket("127.0.0.1",5678);
                //socket.setSoTimeout(30000);
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                PrintWriter printWriter=new PrintWriter(os, true);//将输出流包装成打印流
                printWriter.println("hello,I'm " + new Random().nextInt());
                Thread.sleep(5);

                System.out.println("连接已建立" + socket.getLocalPort());
                //executorService.submit(new ClientHandler(socket));
            }
            catch (Exception E) {
                E.printStackTrace();
            }


        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        System.out.println("新线程建立，处理新到连接");
        this.socket = socket;
    }

    @Override
    public void run() {
        // 接收返回消息
        while (!Thread.currentThread().isInterrupted()&&!socket.isClosed()) {
            InputStream someThing = null;//读取数据
            try {
                someThing = socket.getInputStream();
                if(someThing!=null){
                    BufferedReader bufr = new BufferedReader(new InputStreamReader(someThing));
                    String msgFromServer = bufr.readLine();
                    while (msgFromServer != null) {
                        System.out.println("客户端"+ socket.getLocalPort() +":消息:" + msgFromServer);
                        // 将在这里阻塞
                        msgFromServer = bufr.readLine();
                    }
                    // 处理数据
                    Thread.sleep(50);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                System.err.println("port:" + socket.getLocalPort() + " time:" + new Date());
            }
            finally {
                try {
                    someThing.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
