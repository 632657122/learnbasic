package basic.learn.rabbit.bind;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wangtao
 * @date 2018-12-21
 * @desc
 */
public class ProducerBind {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");

        //设置rabbimq地址
        factory.setHost("localhost");

        //建立到代理服务器的链接
        Connection conn = factory.newConnection();

        //获得信道
        Channel channel = conn.createChannel();

        //声明交换器
        String exchangeName = "holy-exchange";
        channel.exchangeDeclare(exchangeName, "fanout", true);
        String routingKey = "hola";

        //发布消息
        byte[] messageBodyBytes = "holy_test".getBytes();
        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

        channel.close();
        conn.close();
    }
}
