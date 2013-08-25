package com.gmcc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ibm.model.BaseObject;

@Entity
@Table(name = "TB_APP_LOCK")
public class AppLock extends BaseObject implements	java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ISLOCK_Y="1";
	public static final String ISLOCK_N="0";
	
	//库存相关job锁定
	public static final String  INVENTORY_JOB_LOCK="inventory_job_lock";
	

	
	
	private Long id;
	private String code;
	
	
	private String isLock=ISLOCK_N;
	private Date lastUpdatedTime=new Date();
	private String lastUpdatedBy;
	
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "isLock")
	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	
	
	@Column(name = "last_update_time")
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	

	@Column(name = "last_update_by")
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Transient
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

	@Transient
	public Date getCreatedTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEnabled(String enabled) {
		// TODO Auto-generated method stub
		
	}

	@Transient
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
