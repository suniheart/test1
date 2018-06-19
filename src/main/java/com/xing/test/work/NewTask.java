package com.xing.test.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xing.test.model.ConnectionUtils;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

/**
 * @author wang xing
 * @date 2018/6/4 下午2:54
 */
public class NewTask {
    private static final String QUEUE_NAME="QUEUE_simple"; /*P----->|QUEUE | */
    private final static String EXCHANGE_NAME = "test_exchange_fanout";
    @Test
    public void sendMsg() throws Exception { /* 获取一个连接 */

        Connection connection = ConnectionUtils.getConnection(); /*从连接中创建通道*/
        Channel channel = connection.createChannel();
        //创建队列 (声明) 因为我们要往队列里面发送消息,这是后就得知道往哪个队列中发送,就好比在哪个管子里面放
        // 声明队列
        boolean durable=true;//生命队列持久化
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        //TODO 每个消费者发送确认信号之前，消息队列不发送下一个消息过来，一次只处理一个消息 //限制发给同一个消费者不得超过1条消息
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);
        for (int i=0;i<1000;i++){ // 消息内容
            String message = "." + i;
            channel.basicPublish("",
                    QUEUE_NAME,
                    null,
                    message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
           // Thread.sleep(i * 10);//停十毫秒
        }

        channel.close();
        connection.close();
    }

    @Test
    public void sendpublish() throws Exception { /* 获取一个连接 */

        Connection connection = ConnectionUtils.getConnection(); /*从连接中创建通道*/
        Channel channel = connection.createChannel();
        //TODO 声明exchange 交换机 转发器
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); //fanout 分裂

        //创建队列 (声明) 因为我们要往队列里面发送消息,这是后就得知道往哪个队列中发送,就好比在哪个管子里面放
        // 声明队列
        boolean durable=true;//生命队列持久化
        // 消息内容
        String message = "Hello PB";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }

    @Test
    public void test() throws Exception { /* 获取一个连接 */

        Calendar cale = null;
        cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH);//TODO 获取上个月的月份
        System.out.println(month);
    }


}
