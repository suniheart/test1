package com.xing.test;

/**
 * 消费者
 *
 * @author wang xing
 * @date 2018/6/4 上午11:39
 */
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);

    }

}