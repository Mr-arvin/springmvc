package com.monkey1024.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {

	public static void main(String[] args) {
		//创建记录日志的对象
        Logger log = LoggerFactory.getLogger(Slf4jTest.class);

        log.debug("debug信息");
        log.info("info信息");
        log.warn("warn信息");
        log.error("error信息");

	}

}