package me.wanx.common.core.utils.excel.jxls;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.wanx.common.core.exception.CommonException;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;


/**
 * 用jxls导出excel比poi简单
 * 缺点要配置导出模版
* @ClassName: JxlsUtils 
* @Description: TODO 
* @author gqwang
* @date 2015年4月28日 上午9:37:25 
*
 */
public class JxlsUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JxlsUtils.class);
	
	
	/**
	 * 
	 * @param exportFilePath 导出文件的路径
	 * @param exportTemplateFilePath 导出文件的模版
	 * @param data 导出数据的map
	 * @return
	 * @throws CommonException 
	 * 如果导出的数据Object不能是内部类
	 */
	public static File buildExcelExport(String exportFilePath,String exportTemplateFilePath,Map<String,Object> data) throws CommonException{
		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(exportTemplateFilePath, data, exportFilePath);
			return new File(exportFilePath);
		} catch (ParsePropertyException e) {
			logger.error("解析数据出错",e);
			throw new CommonException("", "解析数据出错", e);
		} catch (InvalidFormatException e) {
			logger.error("校验数据出错",e);
			throw new CommonException("", "校验数据出错", e);
		} catch (IOException e) {
			logger.error("IO异常",e);
			throw new CommonException("", "解析数据出错", e);
		}
	}

}
