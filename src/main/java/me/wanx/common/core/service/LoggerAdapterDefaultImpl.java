package me.wanx.common.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: LoggerAdapterDefaultImpl 
* @Description: 日志适配器默认实现 
* 				实现slf4j 
* @author gqwang
* @date 2015年11月11日 上午9:14:47 
*
 */
public class LoggerAdapterDefaultImpl implements ILoggerAdapter {
	
	private Logger logger;
	
	public LoggerAdapterDefaultImpl(@SuppressWarnings("rawtypes") Class clazz){
		logger = LoggerFactory.getLogger(clazz);
	}

	@Override
	public void debug(String msg) {
		logger.debug(msg);
	}

	@Override
	public void debug(String msg, Throwable e) {
		logger.debug(msg,e);
	}

	@Override
	public void debug(String msg, Object... params) {
		logger.debug(msg, params);
	}

	@Override
	public void warn(String msg) {
		logger.warn(msg);
	}

	@Override
	public void warn(String msg, Throwable e) {
		logger.warn(msg, e);
	}

	@Override
	public void warn(String msg, Object... params) {
		logger.warn(msg, params);
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
	}

	@Override
	public void info(String msg, Throwable e) {
		logger.info(msg,e);
	}

	@Override
	public void info(String msg, Object... params) {
		logger.info(msg,params);
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
	}

	@Override
	public void error(String msg, Throwable e) {
		logger.error(msg, e);
	}

	@Override
	public void error(String msg, Object... params) {
		logger.error(msg,params);
	}

}
