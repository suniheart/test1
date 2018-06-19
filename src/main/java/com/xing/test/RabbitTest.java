package com.xing.test;

/**
 * 消费者:
 *
 * @author wang xing
 * @date 2018/6/4 上午11:39
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender1 helloSender2;

    @GetMapping("/hello")
    public void hello() {
        helloSender1.send();
    }
}
