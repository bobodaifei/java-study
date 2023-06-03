package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoContsonTestApplicationTests {

    static Logger logger = LogManager.getLogger(DemoContsonTestApplicationTests.class.getName());

    @Test
    void contextLoads() {
        logger.trace("entry");    //等同于logger.entry();但此方法在新版本好像已经废弃
        logger.error("Did it again!");
        logger.info("这是info级信息");
        logger.debug("这是debug级信息");
        logger.warn("这是warn级信息");
        logger.fatal("严重错误");
        logger.trace("exit");
    }

}
