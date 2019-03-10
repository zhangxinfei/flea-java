package com.soecode.lyf.util.entity;

import com.soecode.lyf.util.entity.model.SYAuthTableSonTypeVO;

import java.util.List;

public class SYAuthTableType {

	private Integer id;
	
	private String tabletype;
	
	private String tabletypename;
	
	private String tabletypeurl;
	
	private String ptabletype;
	
	private String level;

	private String[] tabletypeArray;

	private String wizardtype;

	public String getWizardtype() {
		return wizardtype;
	}

	public void setWizardtype(String wizardtype) {
		this.wizardtype = wizardtype;
	}

	public String[] getTabletypeArray() {
		return tabletypeArray;
	}

	public void setTabletypeArray(String[] tabletypeArray) {
		this.tabletypeArray = tabletypeArray;
	}

	private List<SYAuthTableSonTypeVO>  authTableSonTypeList;


	public List<SYAuthTableSonTypeVO> getAuthTableSonTypeList() {
		return authTableSonTypeList;
	}

	public void setAuthTableSonTypeList(List<SYAuthTableSonTypeVO> authTableSonTypeList) {
		this.authTableSonTypeList = authTableSonTypeList;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTabletype() {
		return tabletype;
	}

	public void setTabletype(String tabletype) {
		this.tabletype = tabletype;
	}

	public String getTabletypename() {
		return tabletypename;
	}

	public void setTabletypename(String tabletypename) {
		this.tabletypename = tabletypename;
	}

	public String getTabletypeurl() {
		return tabletypeurl;
	}

	public void setTabletypeurl(String tabletypeurl) {
		this.tabletypeurl = tabletypeurl;
	}

	public String getPtabletype() {
		return ptabletype;
	}

	public void setPtabletype(String ptabletype) {
		this.ptabletype = ptabletype;
	}
	
}
