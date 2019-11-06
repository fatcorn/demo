package multi_thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * @author fatKarin
 * @date 2019/11/6 11:43
 */
public class NioTester {

    public static void main(String[] args) {
        Thread serverThread = new Thread(new NioServer());
        serverThread.start();

        Thread clientThread = new Thread(new Client());
        clientThread.start();
    }

}

//class Channel{
//    Socket socket;
//    Event event;//读，写或者连接
//}

class NioServer implements Runnable{

    @Override
    public void run() {

        try {
            // 1.创建ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(5678));

            // 2.创建Selector，并ServerSocketChannel注册OP_ACCEPT事件，接收连接。
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 3.开启轮询
            while (selector.select() > 0) {
                // 从selector所有事件就绪的key，并遍历处理。
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()) {  // 接受事件就绪
                            // 获取serverSocketChannel
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            // 接收连接
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()) {  // 读事件就绪
                            // 获取socketChannel
                            client = (SocketChannel) selectionKey.channel();
                            // 创建buffer,并将获取socketChannel中的数据读入到buffer中
                            ByteBuffer readBuf = ByteBuffer.allocate(1024);
                            int readCount = client.read(readBuf);
                            if (readCount <= 0) {
                                return;
                            }
                            Charset charset = Charset.forName(StandardCharsets.UTF_8.name());

                            readBuf.flip();
                            System.out.println(String.valueOf(charset.decode(readBuf).array()));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    selectionKeys.remove(selectionKey);
                });
            }
        }
        catch (Exception e) {

        }


    }
}
