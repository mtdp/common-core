package me.wanx.common.core.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * 记录方法执行的时间 spring aop
* @ClassName: RecordMethodExeTimeServiceImpl 
* @Description: TODO 
* @author gqwang
* @date 2015年1月12日 上午10:08:25 
*
 */
@Aspect
public class RecordMethodExeTimeServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(RecordMethodExeTimeServiceImpl.class);
	
	@Around("execution * *(..)")
	public Object recordTimeProcess(ProceedingJoinPoint pj){
		System.out.println("-------------");
		Object obj = null;
		StopWatch watch = new StopWatch();
		//开始计时
		watch.start();
		try {
			obj = pj.proceed();
		} catch (Throwable e) {
			logger.error("记录方法执行的时间执行方法出错:",e);
		}
		//停止计时
		watch.stop();
		//获取执行的时间
		watch.getTotalTimeMillis(); // 毫秒
//		pj.getThis().getClass().getName();
		return obj;
	}

}
