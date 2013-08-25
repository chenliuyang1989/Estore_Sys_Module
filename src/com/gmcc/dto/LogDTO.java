package com.gmcc.dto;

import java.io.Serializable;
import java.util.Date;

import com.ibm.model.BaseObject;
import com.ibm.util.annotation.DisplayColumn;

public class LogDTO extends BaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DisplayColumn(property = "id", titleKey = "businessLog.id",initFlag="1")
	private Long id;
	@DisplayColumn(property = "logType", titleKey = "businessLog.type",initFlag="1",sortable=true)
	private String logType;
	@DisplayColumn(property = "cityName", titleKey = "field.cityName",initFlag="1",sortable=true)
	private String cityName;
	@DisplayColumn(property = "operName", titleKey = "businessLog.operatorName",initFlag="1",sortable=true)
	private String operName;
	@DisplayColumn(property = "loginIp", titleKey = "businessLog.loginIp",initFlag="1")
	private String loginIp;
	@DisplayColumn(property = "operTime", titleKey = "businessLog.operatorTime",initFlag="1")
	private Date operTime;
	@DisplayColumn(property = "operMothod", titleKey = "businessLog.operatorMethodKey",decorator = "com.gmcc.decorator.LogOperatorKeyWrapper",initFlag="1")
	private Long operMothod;
	@DisplayColumn(property = "operatorObjectKey", titleKey = "businessLog.operatorObjectKey",initFlag="1")
	private String operObjectKey;
	@DisplayColumn(property = "content", titleKey = "businessLog.operatorContent",initFlag="1")
	private String content;
	
	
	public String getOperObjectKey() {
		return operObjectKey;
	}

	public void setOperObjectKey(String operObjectKey) {
		this.operObjectKey = operObjectKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Long getOperMothod() {
		return operMothod;
	}

	public void setOperMothod(Long operMothod) {
		this.operMothod = operMothod;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Override
	public String getLastUpdatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastUpdatedBy(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getLastUpdatedTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastUpdatedTime(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCreatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatedBy(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCreatedTime(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreatedTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEnabled(String enabled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
