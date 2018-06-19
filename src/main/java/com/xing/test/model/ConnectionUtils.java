package com.xing.test.model;

/**
 * @author wang xing
 * @date 2018/6/4 下午2:22
 */
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class ConnectionUtils {
    public static Connection getConnection() throws IOException, TimeoutException { //定义连接工厂

    ConnectionFactory factory = new ConnectionFactory(); //设置服务地址
    factory.setHost("47.106.180.38");//端口
        factory.setPort(5672);//amqp协议 端口 类似与mysql的3306
        // 设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/root");
        factory.setUsername("root");
        factory.setPassword("123456");
    // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}