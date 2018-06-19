package com.xing.test.work;

import com.rabbitmq.client.*;
import com.xing.test.model.ConnectionUtils;

import java.io.IOException;

/**
 * @author wang xing
 * @date 2018/6/4 下午3:10
 */
public class Recv2 {
    private final static String QUEUE_NAME = "QUEUE_simple";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionUtils.getConnection(); /*从连接中创建通道*/
        Channel channel = connection.createChannel();

        //声明队列 如果能确定是哪一个队列 这边可以删掉,不去掉 这里会忽略创建
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        channel.basicQos(1);//TODO 保证一次只分发一个
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8"); System.out.println(" [1] Received '" + message + "'"); try {
                   // doWork(message);
                } catch (Exception e) {
                    e.printStackTrace(); } finally {
                    System.out.println(" [x] Done"); }
                channel.basicAck(envelope.getDeliveryTag(), false);//TODO 返回我处理完了
            }
        };
        //监听队列
        boolean autoAck = false; //TODO 手动确认消息(关闭true自动应答)
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

    private static void doWork(String  task ) throws InterruptedException {
        Thread.sleep(2000);
    }


}
