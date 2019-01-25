package basic.learn.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author wangtao
 * @date 2019-01-25
 * @desc
 */
public class NioClient {


    public static void main(String[] args) {
        System.out.println("客户端启动");

        try {
            //1.创建socket通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
            //2.切换异步非阻塞
            socketChannel.configureBlocking(false);
            //3.指定缓冲区大小
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("this is a message".getBytes());
            //4.切换到读取模式
            byteBuffer.flip();
            //5.写入到通道
            socketChannel.write(byteBuffer);
            byteBuffer.clear();

            int len;
            byteBuffer.flip();
            byteBuffer.clear();
            while (true) {
                if ((len = socketChannel.read(byteBuffer)) > 0) {
                    byteBuffer.flip();
                    System.out.println("获取反馈信息:" + new String(byteBuffer.array(), 0, len));
                    break;
                }
            }

            //6.关闭通道
            socketChannel.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
