package me.wanx.common.core.utils.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: DataValidate 
* @Description: 参数校验   运行时校验,可以配置到属性上 
* @author gqwang
* @date 2015年10月27日 下午3:39:06 
*
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DataValidate {
	
	/**
	 * 是否校验
	 * 添加了注解  默认是
	 * @return
	 */
	boolean isCheck() default true;
	
	/**
	 * 是否可以为空字串
	 * 默认 是
	 * 只对String 类型
	 * @return
	 */
	boolean isBlank() default true;
	
	/**
	 * 是否可以为null
	 * 默认 是
	 * @return
	 */
	boolean isNull() default true;
	
	/**
	 * 最小长度
	 * 只对String 类型
	 * @return
	 */
	int minLength() default -1;
	
	/**
	 * 最大长度
	 * 只对String 类型
	 * @return
	 */
	int maxLength() default -1;
	
	/**
	 * 正则表达式
	 * 只对String 类型
	 * @return
	 */
	String regExp() default "";
	
	/**
	 * 出错提示信息
	 * @return
	 */
	String errorMsg() default "";
}
