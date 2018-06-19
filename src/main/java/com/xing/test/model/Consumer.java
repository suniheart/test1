package com.xing.test.model;

import com.rabbitmq.client.*;

import java.io.IOException;
/**
 * @author wang xing
 * @date 2018/6/4 下午2:31
 */
public class Consumer {
    private final static String QUEUE_NAME = "QUEUE_simple";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionUtils.getConnection(); /*从连接中创建通道*/
        Channel channel = connection.createChannel();

        //声明队列 如果能确定是哪一个队列 这边可以删掉,不去掉 这里会忽略创建
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
