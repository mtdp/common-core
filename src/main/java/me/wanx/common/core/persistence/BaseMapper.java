package me.wanx.common.core.persistence;

import java.util.List;

/**
 * 
* @ClassName: BaseMapper 
* @Description: TODO 
* @author gqwang
* @date 2014年11月14日 上午10:06:28 
* 
* @param <T>
 */
public interface BaseMapper<T> {
	public List<T> gets();
	public T get(Integer id);
	public int add(T t);
	public int del(Integer id);
	public int update(T t);

}
