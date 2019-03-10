package com.soecode.lyf.common.commonMethod;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO支持类实现
 * @author ThinkGem
 * @version 2014-05-16
 * @param <T>
 */
public interface CrudDao<T> {
	/**
	 * 分页查询--调用存储过程
	* @Description: TODO
	* @param param
	* @return
	* @author sangbin
	* @date 2017年11月14日 下午4:11:58 
	* @throws
	 */
	List<Map<String,Object>> selectByPageMap(@Param("where") String where);

	/**
	 * Describe: 分页查询，返回实体类的集合
	 * @author 张新飞
	 * @date 2018/12/17
	 * @parms  * @param null
	 * @return
	 */
	List<T> selectByPage(@Param("where") String where);
	
}