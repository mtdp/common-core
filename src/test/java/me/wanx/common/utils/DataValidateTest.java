package me.wanx.common.utils;

import me.wanx.common.core.utils.validate.DataValidate;
import me.wanx.common.core.utils.validate.DataValidateResult;
import me.wanx.common.core.utils.validate.DataValidateUtil;

public class DataValidateTest {
	
	public static void main(String[] args) {
		foo f = new foo("");
		DataValidateResult r = DataValidateUtil.validata(f);
		System.out.println(r);
	}

}

class foo{
	
	@DataValidate(isCheck = true,isNull=false,isBlank=false,minLength=2,maxLength=5)
	private Object name;
	
	public foo(){}
	public foo(String name){
		this.name = name;
	}
	
}

