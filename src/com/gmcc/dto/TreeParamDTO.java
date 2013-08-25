package com.gmcc.dto;

public class TreeParamDTO {

	private static final long serialVersionUID = 7955689943502060631L;
	
	private boolean hasParent;
	private Long parentId;
	private String sql;
	private String parentIdColumnName;
	private String chStr;
	private String checkBoxName;
	private boolean hasChildTable;
	private String childTableSql;
	private String childTabCheckBoxName;
	private boolean hasRadio;
	private String typeName;
	
	public String getChildTabCheckBoxName() {
		return childTabCheckBoxName;
	}
	public void setChildTabCheckBoxName(String childTabCheckBoxName) {
		this.childTabCheckBoxName = childTabCheckBoxName;
	}
	public boolean isHasParent() {
		return hasParent;
	}
	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getChildTableSql() {
		return childTableSql;
	}
	public void setChildTableSql(String childTableSql) {
		this.childTableSql = childTableSql;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getParentIdColumnName() {
		return parentIdColumnName;
	}
	public void setParentIdColumnName(String parentIdColumnName) {
		this.parentIdColumnName = parentIdColumnName;
	}
	public String getChStr() {
		return chStr;
	}
	public void setChStr(String chStr) {
		this.chStr = chStr;
	}
	public String getCheckBoxName() {
		return checkBoxName;
	}
	public void setCheckBoxName(String checkBoxName) {
		this.checkBoxName = checkBoxName;
	}
	public boolean isHasChildTable() {
		return hasChildTable;
	}
	public void setHasChildTable(boolean hasChildTable) {
		this.hasChildTable = hasChildTable;
	}
	public boolean isHasRadio() {
		return hasRadio;
	}
	public void setHasRadio(boolean hasRadio) {
		this.hasRadio = hasRadio;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
