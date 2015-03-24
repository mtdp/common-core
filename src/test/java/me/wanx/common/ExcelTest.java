package me.wanx.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.test.annotation.NotTransactional;

import me.wanx.common.core.utils.excel.ExcelField;
import me.wanx.common.core.utils.excel.ExportExcel;
import me.wanx.common.core.utils.excel.Type;

public class ExcelTest {
	public static void main(String[] args) throws DecoderException, UnsupportedEncodingException {
		/*List<Foo> list = new ArrayList<Foo>();
		list.add(new Foo(1,"01"));
		list.add(new Foo(2,"02"));
		
		ExportExcel<Foo> ee = new ExportExcel<Foo>(list);
		
		try {
			ee.export("d:/tmp/excel.xls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//		C4E3BAC3
//		String t = "瀚银,99无限,测试,en";
		String t = "你好";
		t= "99无线测试，验证码：9999";
		System.out.println(Arrays.toString(t.getBytes("UTF-8")));
		System.out.println(Arrays.toString(t.getBytes("GBK")));
		
		System.out.println(new String(Hex.decodeHex("C4E3BAC3".toCharArray()),"GBK"));
		
		System.out.println(Hex.encodeHex("瀚银,99无限,测试,en".getBytes("GBK"), false));
	}
	
}

class Foo{
	@ExcelField(filedName="id",sort=1)
	private int id;
	@ExcelField(filedName="name",sort=2)
	private String name;
	@ExcelField(type=Type.OBJECT,sort=3)
	private Hoo hoo;
	
	public Foo(int id,String name){
		this.id = id;
		this.name = name;
		this.hoo = new Hoo(123);
	}
}

class Hoo{
	private int i;
	public Hoo(int i){
		this.i = i;
	}
}
