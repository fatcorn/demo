package multi_thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试目的：
 *      观察 bio 的阻塞, 线程池的使用
 * @author fatKarin
 * @date 2019/11/6 11:43
 */
public class BioTester {
    public static void main(String[] args) throws InterruptedException {
        Thread clientThread = new Thread(new Client());
        clientThread.start();

        Server server = new Server();
        server.run();
    }
}



// 服务器线程
class Server {

    public Server() {
        System.out.println("服务端初始化...");
    }

    public void run() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);//线程池

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5678);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!Thread.currentThread().isInterrupted()){//主线程死循环等待新连接到来
            Socket socket = null;
            try {
                assert serverSocket != null;
                socket = serverSocket.accept();
                System.out.println("接受到消息");

                // single 单线程， multi 多线程
                String flag = "single";

                if(flag.equals("single")) {
                    /**
                     * 单线程处理 接受tcp请求, 在收到第一条消息后,
                     */
                    InputStream someThing = null;//读取数据
                    try {
                        someThing = socket.getInputStream();
                        if (someThing != null) {
                            BufferedReader bufr = new BufferedReader(new InputStreamReader(someThing));
                            String client_msg = bufr.readLine();
                            while (client_msg != null) {
                                System.out.println("在这里阻塞");
                                System.out.println(new Date() + ":客户端消息:" + client_msg);
                                // 单线程 在这里阻塞,原因连接没有断掉，会一直读，也不会返回null
                                client_msg = bufr.readLine();
                                System.out.println("hello1" + Thread.currentThread().isInterrupted());
                            }
                            // 处理数据
                            Thread.sleep(50);
                            System.out.println("hello1");
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            someThing.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                else {
                    executor.submit(new ConnectIOnHandler(socket));//为新的连接创建新的线程,多线程处理
                    Thread.sleep(50);
                }

            }catch (IOException e) {
                e.printStackTrace();
            }

            }
    }
}

class ConnectIOnHandler extends Thread{
    private Socket socket;
    public ConnectIOnHandler(Socket socket){
        System.out.println("新线程建立，处理新到连接");
        this.socket = socket;
    }
    public void run(){
        while(!Thread.currentThread().isInterrupted()&&!socket.isClosed()){ //死循环处理读写事件

            InputStream someThing = null;//读取数据
            try {
                someThing = socket.getInputStream();
                if(someThing!=null){
                    BufferedReader bufr=new BufferedReader(new InputStreamReader(someThing));
                    String client_msg = bufr.readLine();
                    while (client_msg != null) {
                        System.out.println("在这里阻塞");
                        System.out.println(new Date() +":客户端消息:" + client_msg);
                        // 再此处阻塞, 如果 不设置 超时时间,会一直卡在这里，导致线程池资源耗尽，设置超时时间则会关掉连接
                        client_msg = bufr.readLine();
                    }
                    // 处理数据
                    Thread.sleep(50);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
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
