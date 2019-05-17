package com.springboot.btest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchedulingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingService.class);

    @Autowired
    private RedisService redisService;

//    @Scheduled(cron = "0 0/5 * * * ?") // 每5分钟执行一次
    public void pubCards() {
        LOGGER.info("开始检查发布记录...");
        LOGGER.info("发布记录完毕...");
    }

}
