package me.wanx.common;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import me.wanx.common.core.utils.HttpClientUtils;

import org.apache.commons.codec.binary.Hex;

public class HttpTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "http://222.73.44.38:8080/mt";
		Map<String,String> map = new HashMap<String,String>();
		//un=800093&pw=800093&da=13917382240&rd=1&dc=15&sm=E5ABD2F82C3939CEDECFDE2CB2E2CAD42C656E
		map.put("un", "800093");
		map.put("pw", "800093");
		map.put("da", "13917382240");
		map.put("rd", "1");
		map.put("dc", "15");
		map.put("sm", "99无线测试，验证码：9999");
		
		String s = sendContentProcessor(map.get("sm"));
		System.out.println(s);
		
		map.put("sm", s);
		
		String ss = HttpClientUtils.httpGet(url, map);
		
		System.out.println(ss);
		
	}
	
	public static String sendContentProcessor(String c) throws UnsupportedEncodingException{
		String r = null;
		char[] cc =Hex.encodeHex(c.getBytes("GBK"), false);
		r = new String(cc);
		return r;
	}

}
