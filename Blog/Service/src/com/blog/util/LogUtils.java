package com.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
//	private static Logger logger_error = LoggerFactory.getLogger("error_log");
//	private static Logger logger_operation = LoggerFactory.getLogger("operate_log");
//	private static Logger file_controller_error = LoggerFactory.getLogger("fileControllerError_log");
//	private static Logger scheduled = LoggerFactory.getLogger("scheduled_log");
	
	public static void info(Class<?> class_run, String msg) {
		Logger logger = LoggerFactory.getLogger("operate_log");
		StringBuffer strLog = new StringBuffer();
		strLog.append("<").append(class_run.getName()).append(">").append(msg);
		logger.info(strLog.toString());
	}
	
	public static void error(Class<?> class_run, String msg) {
		Logger logger = LoggerFactory.getLogger("error_log");
		StringBuffer strLog = new StringBuffer();
		strLog.append("<").append(class_run.getName()).append(">").append(msg);
		logger.error(strLog.toString());
	}
	
	public static void fileConteollerError(String msg) {
		Logger logger = LoggerFactory.getLogger("fileControllerError_log");
		StringBuffer strLog = new StringBuffer();
		strLog.append(msg);
		logger.error(strLog.toString());
	}

	public static void locationDataInfo(String msg) {
		Logger logger = LoggerFactory.getLogger("locationDataInfo_log");
		StringBuffer strLog = new StringBuffer();
		strLog.append(msg);
		logger.info(strLog.toString());
	}
	
//	public static void scheduled (String msg) {
//		Logger logger = LoggerFactory.getLogger("scheduled_log");
//		StringBuffer strLog = new StringBuffer();
//		strLog.append(msg);
//		logger.error(strLog.toString());
//	}
	
}
