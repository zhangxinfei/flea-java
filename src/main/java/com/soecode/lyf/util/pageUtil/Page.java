package com.soecode.lyf.util.pageUtil;

import java.util.List;

public class Page {
	
	/**
	 * 每页显示行数
	 */
	int pageSize = 10;
	/**
	 * 当前页码
	 */
	int pageNo = 1;
	/**
	 * 总页数
	 */
	int totalPage = 1;
	
	/**
	 * 当前查询条件下的总记录数
	 */
	int totalCount = 0;
	
	/**
	 * 分页起始行号
	 */
	int startNum = 0;
	
	/**
	 * 分页的结束行号
	 */
	int endNum = 0;
	
	List<?> list;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Page(int pageSize, int pageNo, int totalCount) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 计算总页数
	 * @return
	 */
	public int getTotalPage() {
		
		/**
		 * 共100条数据， 每页展示10条， 一共10页
		 * 共95条数据， 每页展示10条， 一共10页
		 */
		totalPage = totalCount/pageSize;
		if(totalCount%pageSize == 0){
			return totalPage;
		}else{
			return ++totalPage;
		}
		
	}

	/**
	 * 计算总页数
	 * @return
	 */
	public int getTotalPage(int totalCount,int pageSize) {

		/**
		 * 共100条数据， 每页展示10条， 一共10页
		 * 共95条数据， 每页展示10条， 一共10页
		 */
		totalPage = totalCount/pageSize;
		if(totalCount%pageSize == 0){
			return totalPage;
		}else{
			return ++totalPage;
		}

	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 计算开始行号
	 * @return
	 */
	public int getStartNum() {
		return (pageNo - 1)*pageSize ;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	/**
	 * 计算结束行号
	 * @return
	 */
	public int getEndNum() {
		return pageNo * pageSize + 1;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	
	
	
	public Page(){
		
	}

}
