package basic.learn.rabbit.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * @author wangtao
 * @date 2018-12-21
 * @desc
 */
public class RPCClient {

    private Connection connection;
    private Channel channel;
    private String replyQueueName;

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();

        replyQueueName = channel.queueDeclare().getQueue();

    }

    public String call(String message) throws Exception {
        final String corrId = UUID.randomUUID().toString();//声明一个暗号,接收server消息时暗号要对上才能接收

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)//声明一个接收地,让server往这个队列返回消息
                .build();

        //向server发送请求,请求消息发送给了队列replyQueueName,另外在props里加上了接收地replyQueueName和暗号corrId
        String requestQueueName = "rpc_queue";
        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
        //用BlockingQueue来延缓main线程,1代表我们只需要一个response
        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

        //接收server返回的消息,对每一个收到的message都check一下corrid,相同的则put到BlockingQueue中
        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response.offer(new String(body, "UTF-8"));

                }
            }
        });
        return response.take();

    }

    public void close() throws IOException {
        connection.close();
    }

    public static void main(String[] args) {
        RPCClient fibonacciRpc = null;
        String response = null;
        try {
            fibonacciRpc = new RPCClient();
            System.out.println(" [x] Requesting fib(30)");
            response = fibonacciRpc.call("30");
            System.out.println(" [.] Got '" + response + "'");

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fibonacciRpc != null) {
                try {
                    fibonacciRpc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
