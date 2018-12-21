package basic.learn.rabbit.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wangtao
 * @date 2018-12-21
 * @desc
 */
public class PRCService {

    private static final String RPC_QUEUE_NAME = "rpc_queue";

    private static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = null;
        try {
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.exchangeDeclare("holy-exchange", "fanout", true);

            //准备接收
            String queueName = channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null).getQueue();
            //一次只处理一个消息
            channel.basicQos(1);
            channel.queueBind(queueName,"holy-exchange","");

            System.out.println(" [x] Awaiting RPC requests");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties()
                            .builder()
                            .correlationId(properties.getCorrelationId())
                            .build();

                    String response = "";

                    try {
                        String message = new String(body, "UTF-8");
                        int n = Integer.parseInt(message);

                        System.out.println(" [.] fib(" + message + ")");
                        response += fib(n);
                    } finally {
                        //回调 队列名是接收时指定的队列名properties.getReplyTo(),参数replyProps里带上corrid
                        String queueName = properties.getReplyTo();
                        System.out.println("===返回的队列名:" + queueName);
                        channel.basicPublish("", queueName, replyProps, response.getBytes("UTF-8"));
                        long tag = envelope.getDeliveryTag();
                        System.out.println("===tag:" + tag);
                        channel.basicAck(envelope.getDeliveryTag(), false);//是否批量,true:一次性ack所有小于等于tag的消息,false:只ack index为tag的消息
                        synchronized (this) {
                            this.notify();
                        }
                    }
                }
            };

            //消费rpc_queue里的任务
            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
            while (true) {
                synchronized (consumer) {
                    try {
                        consumer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
