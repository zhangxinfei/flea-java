package com.soecode.lyf.util.pageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

@Service
public abstract class CrudService<D extends CrudDao<T>, T> {

	@Autowired
	private D dao;

	public D getDao() {
		return dao;
	}

	public PageInfo<Map<String, Object>> selectByPageMap(PageUtil pageUtil, String where, String order){
		PageHelper.startPage(pageUtil.getPage(),pageUtil.getLimit(),true);
		PageHelper.orderBy(order);
		List<Map<String, Object>> list = dao.selectByPageMap(where);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return pageInfo;
	}
	
	public PageInfo<T> selectByPage(PageUtil pageUtil, String where, String order) {
		PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit(), true);
		PageHelper.orderBy(order);
		List<T> list = dao.selectByPage(where);
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;
	}
}
