package me.wanx.common.core.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 记录方法执行的时间 spring aop
* @ClassName: RecordMethodExeTimeServiceImpl 
* @Description: TODO 
* @author gqwang
* @date 2015年1月12日 上午10:08:25 
*
 */
@Component
public class RecordMethodExeTimeServiceImpl implements MethodInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(RecordMethodExeTimeServiceImpl.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object obj = null;
		StopWatch watch = new StopWatch();
		//开始计时
		watch.start();
		try {
			obj = invocation.proceed();
		} catch (Throwable e) {
			logger.error("记录方法执行的时间执行方法出错:",e);
		}
		//停止计时
		watch.stop();
		//获取执行的时间
		long time = watch.getTotalTimeMillis(); // 毫秒
		String clazzName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Class<?>[] clazz = invocation.getMethod().getParameterTypes();
		String params = "";
		for(Class<?> c : clazz){
			String t = c.getName();
			t = t.substring(t.lastIndexOf(".")+1, t.length());
			params += t +",";
		}
		params = params.substring(0, params.length()-1);
		logger.info("{} exe time:【{}】ms",clazzName+"."+methodName+"("+params+")",time);
		return obj;
	}

}
