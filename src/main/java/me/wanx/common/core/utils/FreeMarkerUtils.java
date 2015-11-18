package me.wanx.common.core.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
* @ClassName: FreeMarkerUtils 
* @Description: freemarker工具类 
* @author gqwang
* @date 2015年11月18日 下午3:55:52 
*
 */
public class FreeMarkerUtils {

	private static final Logger logger = LoggerFactory.getLogger(FreeMarkerUtils.class);
	
	
	/**
	 * 根据模版内容返回填充后的内容
	 * @param map 模版中的key-value
	 * @param templateTxt 模版内容
	 * @return
	 */
	public static String getMessgae(Map<String,Object> map,String templateTxt){
		String result = null;
		Configuration config = new Configuration();
		try {
			StringTemplateLoader strTemplate = new StringTemplateLoader();
			strTemplate.putTemplate("t", templateTxt);
			config.setTemplateLoader(strTemplate);
			config.setDefaultEncoding("UTF-8");
			Template template = config.getTemplate("t","UTF-8");
			//输出流
			StringWriter out = new StringWriter();
			template.process(map, out);
			result = out.toString();
			//logger.info("***********result="+result);
		} catch (IOException e) {
			logger.error("获取freemark模版出错",e);
			return result;
		} catch (TemplateException e) {
			logger.error("freemark模版处理异常",e);
			return result;
		}
		return result;
	}
	
	
	public static void main(String[] args){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("test", "test value");
		String templateTxt = "test template value is ${test}";
		String r = getMessgae(map, templateTxt);
		logger.info("-----------------r="+r);
		map.put("test", "测试中文");
		r = getMessgae(map, templateTxt);
		logger.info("-----------------r="+r);
	}
	
}
