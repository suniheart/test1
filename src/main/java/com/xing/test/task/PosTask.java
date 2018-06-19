package com.xing.test.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;


@Component
public class PosTask {

    private static final Logger logger = LoggerFactory.getLogger(PosTask.class);



    /**
     * 每个月的第一天0时0分0秒算出来上一个月的每台pos机器的总流水
     */
    @Scheduled(cron="0 0 0 1 1-12 ?")
    public void expiredActivity() {
        logger.info("--------------run job start-----------------");
        //int row = activityService.updateActivityStatusTimeOut();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH);//TODO 获取上个月的月份
        System.out.println(month);
        //1、从数据库中查询所有的pos机
        //2、循环查询pos机的流水，将这一台pos机的流水发送到rabbitmq队列中，去计算pos流水总和，把一台pos机的所有流水作为队列中的一条数据
        logger.info("--------------run job end,row={}-----------------","row");
    }

}
