package me.wanx.common.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	
	private static String CHARSET = "UTF-8";
	
	/**
	 * 用httpclient 发送post 请求
	 * 如果参数为null,会转化成""
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpPost(String url,Map<String,String> params){
		String result = null;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.addRequestHeader("Content-Type","text/html;charset="+CHARSET);
		if(params != null && !params.isEmpty()){
			for(Map.Entry<String,String> en : params.entrySet()){
				String key = en.getKey();
				String value = en.getValue();
				if(!StringUtils.isNotBlank(value)){
					value = "";
				}
				NameValuePair v = new NameValuePair(key, value);
				post.addParameter(v);
			}
		}
		//设置发送内容的编码 post 设置了header 这里可以不用设置
//		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, CHARSET);
		try {
			client.executeMethod(post);
			if(post.getStatusCode() == HttpStatus.SC_OK){
				result = post.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			logger.error("httpclient send post error",e);
		} catch (IOException e) {
			logger.error("httpclient send post error",e);
		}finally{
			post.releaseConnection();
		}
		return result;
	}
	
	public static String httpPost(String url,Map<String,String> params,Map<String,String> headerParams){
		String result = null;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.addRequestHeader("Content-Type","text/html;charset="+CHARSET);
		if(headerParams != null && !headerParams.isEmpty()){
			for(Map.Entry<String, String> en : headerParams.entrySet()){
				String k = en.getKey();
				String v = en.getValue();
				if(!StringUtils.isNotBlank(v)){
					v = "";
				}
				post.addRequestHeader(k, v);
			}
		}
		if(params != null && !params.isEmpty()){
			for(Map.Entry<String,String> en : params.entrySet()){
				String key = en.getKey();
				String value = en.getValue();
				if(!StringUtils.isNotBlank(value)){
					value = "";
				}
				NameValuePair v = new NameValuePair(key, value);
				post.addParameter(v);
			}
		}
		try {
			client.executeMethod(post);
			if(post.getStatusCode() == HttpStatus.SC_OK){
				result = post.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			logger.error("httpclient send post error",e);
		} catch (IOException e) {
			logger.error("httpclient send post error",e);
		}finally{
			post.releaseConnection();
		}
		return result;
	}
	
	
	public static String httpGet(String url,Map<String,String> params){
		String result = null;
		HttpClient client = new HttpClient();
		StringBuffer sb = new StringBuffer(url);
		sb.append("?");
		if(params != null && !params.isEmpty()){
			for(Map.Entry<String,String> en : params.entrySet()){
				String key = en.getKey();
				String value = en.getValue();
				if(!StringUtils.isNotBlank(value)){
					value = "";
				}else{
					try {
						value = URLEncoder.encode(value,CHARSET);
					} catch (UnsupportedEncodingException e) {
						logger.error("set http get param error",e);
						sb.append(key).append("=").append(value).append("&");
						continue;
					}
				}
				sb.append(key).append("=").append(value).append("&");
			}
			//删除最后一个 & 符号
			sb.deleteCharAt(sb.length()-1);
		}
		GetMethod get = new GetMethod(sb.toString());
		get.addRequestHeader("Content-Type","text/html;charset="+CHARSET);
		try {
			client.executeMethod(get);
			if(get.getStatusCode() == HttpStatus.SC_OK){
				result = get.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			logger.error("httpclient send get error",e);
		} catch (IOException e) {
			logger.error("httpclient send get error",e);
		}finally{
			get.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 返回responseBoby和responseHeader
	 * @param url
	 * @param params
	 * @return
	 */
	public static Map<String,Object> httpGetResultMap(String url,Map<String,String> params){
		Map<String, Object> result = new HashMap<String,Object>();
		HttpClient client = new HttpClient();
		StringBuffer sb = new StringBuffer(url);
		sb.append("?");
		if(params != null && !params.isEmpty()){
			for(Map.Entry<String,String> en : params.entrySet()){
				String key = en.getKey();
				String value = en.getValue();
				if(!StringUtils.isNotBlank(value)){
					value = "";
				}else{
					try {
						value = URLEncoder.encode(value,CHARSET);
					} catch (UnsupportedEncodingException e) {
						logger.error("set http get param error",e);
						sb.append(key).append("=").append(value).append("&");
						continue;
					}
				}
				sb.append(key).append("=").append(value).append("&");
			}
			//删除最后一个 & 符号
			sb.deleteCharAt(sb.length()-1);
		}
		GetMethod get = new GetMethod(sb.toString());
		get.addRequestHeader("Content-Type","text/html;charset="+CHARSET);
		try {
			client.executeMethod(get);
			if(get.getStatusCode() == HttpStatus.SC_OK){
				String respBody = get.getResponseBodyAsString();
				Header[] respHeader = get.getResponseHeaders();
				result.put("respBody", respBody);
				result.put("respHeader", respHeader);
			}
		} catch (HttpException e) {
			logger.error("httpclient send get error",e);
		} catch (IOException e) {
			logger.error("httpclient send get error",e);
		}finally{
			get.releaseConnection();
		}
		return result;
	}

}
