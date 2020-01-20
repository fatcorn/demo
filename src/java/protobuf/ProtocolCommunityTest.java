package protobuf;

import com.google.protobuf.ByteString;

import java.io.*;
import java.net.Socket;
import java.time.Instant;
import java.util.Arrays;

/**
 * 由于安卓部分java可完成功能测试较为消耗资源与耗时，
 * 故一些Java可完成测试功能，可再次测试
 * @author fatKarin
 * @date 2020/1/8 13:42
 */
public class ProtocolCommunityTest {

    private static Socket socket;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                socket = new Socket("127.0.0.1",5678);
                socket.setKeepAlive(false);
                OutputStream os = socket.getOutputStream();

                // 构建协议
                Protocol.message.Builder messageBuilder = Protocol.message.newBuilder();
                //  构建头
                Protocol.message.Header.Builder headerBuilder = Protocol.message.Header.newBuilder();
                headerBuilder.setMessageType("login_message");
                headerBuilder.setProtocolVersion(1.0f);
                // 构建body
                Protocol.message.Login.Builder loginBuiler = Protocol.message.Login.newBuilder();

                loginBuiler.setToken("0aa19369-3976-4ccc-b88b-54aa0b74497d");

                // 填充协议
                messageBuilder.setHeader(headerBuilder.build());
                messageBuilder.setBody(ByteString.copyFrom(loginBuiler.build().toByteArray()));
                byte[] bytes = spliceBytes(fillLengthByte(messageBuilder.build().toByteArray().length),messageBuilder.build().toByteArray());
                System.out.println("即将发送的数据" + Arrays.toString(bytes));

                os.write(bytes);

                //os.flush();

                System.out.println("已发送协议");

                new Thread(new Receiver()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    // 低位进高位，逢128 进1
    public static byte[] fillLengthByte(Integer length) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (length%128);
        bytes[1] = (byte) (length/128);
        bytes[2] = (byte) (length/(128*128));
        bytes[3] = (byte) (length/(128*128*128));
        return bytes;
    }
    // 拼接byte
    public static byte[] spliceBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1,0,data3,0,data1.length);
        System.arraycopy(data2,0,data3,data1.length, data2.length);
        return data3;
    }

    static class Receiver implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    InputStream is = socket.getInputStream();
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;

                    while((len = is.read(buffer)) != -1){
                        outStream.write(buffer,0,len);
                        System.out.println("回来的数据长：" + buffer.length + "数据：" + Arrays.toString(buffer));
                    }
                    byte[] messageByte = outStream.toByteArray();
                    System.out.println("响应数据:"+ Arrays.toString(messageByte));
                    outStream.close();
                    is.close();
                    Thread.sleep(200);

                } catch (IOException | InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}
