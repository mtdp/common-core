package me.wanx.common.utils.excel;

import me.wanx.common.core.utils.excel.ExcelField;

public class ExportBean {
	
	public ExportBean(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@ExcelField(filedName="中文id")
	private int id;
	
	@ExcelField(filedName="中文name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
