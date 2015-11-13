package me.wanx.common.utils.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.wanx.common.core.utils.excel.ExportExcel;

public class ExportExcelTest {
	
	public static void main(String[] args) throws IOException {
		//生成数据
		List<ExportBean> list = new ArrayList<ExportBean>();
		for(int i = 0; i < 10; i++){
			list.add(new ExportBean(i,"i="+i));
		}
		ExportExcel<ExportBean> ee = new ExportExcel<ExportBean>(list);
		ee.export("d:/tmp/exportBean.xls");
	}

}
