package me.wanx.common.core.service;

/**
 * 
* @ClassName: ILoggerAdapter 
* @Description: 日志适配器框架,解耦我们项目中直接使用日志框架 
* @author gqwang
* @date 2015年11月11日 上午8:59:19 
*
 */
public interface ILoggerAdapter {
	
	public void debug(String msg);
	public void debug(String msg,Throwable e);
	public void debug(String msg,Object ... params);
	
	public void warn(String msg);
	public void warn(String msg,Throwable e);
	public void warn(String msg,Object ... params);
	
	public void info(String msg);
	public void info(String msg,Throwable e);
	public void info(String msg,Object ... params);
	
	public void error(String msg);
	public void error(String msg,Throwable e);
	public void error(String msg,Object ... params);
	
}
