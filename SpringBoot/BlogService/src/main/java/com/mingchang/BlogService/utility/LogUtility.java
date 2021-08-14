package com.mingchang.BlogService.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtility {

    public static void info(Class<?> class_run, String msg) {
        Logger logger = LoggerFactory.getLogger("operate_log");
        logger.info("<" + class_run.getName() + ">" + msg);
    }

    public static void error(Class<?> class_run, String msg) {
        Logger logger = LoggerFactory.getLogger("error_log");
        logger.error("<" + class_run.getName() + ">" + msg);
    }

    public static void fileConteollerError(String msg) {
        Logger logger = LoggerFactory.getLogger("fileControllerError_log");
        logger.error(msg);
    }

    public static void locationDataInfo(String msg) {
        Logger logger = LoggerFactory.getLogger("locationDataInfo_log");
        logger.info(msg);
    }

}
