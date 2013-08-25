package com.gmcc.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.gmcc.dao.model.AbstractElementGroup;
import com.ibm.model.BaseObject;

/**
 * AbstractElementGroup entity provides the base persistence definition of the
 * ElementGroup entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractElementGroup extends BaseObject {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private String eleGroupName;
	private String eleGroupRemark;
	private Long ttype;
	private Integer version;
	private String keyFlag;

	private Date createTime;
	private String createBy;
	private Date lastUpdatedTime; // 最后更新时间
	private String lastUpdatedBy; // 最后更新人

	private String enabled;	
	private String eleStr;
	@Transient
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Transient
	public String getEleStr() {
		return eleStr;
	}
	public void setEleStr(String eleStr) {
		this.eleStr = eleStr;
	}
	@Transient
	public Date getCreatedTime() {
		return createTime;
	}

	public void setCreatedTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public String getCreatedBy() {
		return createBy;
	}

	public void setCreatedBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Column(name = "LAST_UPDATED_BY", length = 50)
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	@Column(name = "LAST_UPDATED_TIME")
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	/** default constructor */
	public AbstractElementGroup() {
	}

	/** minimal constructor */
	public AbstractElementGroup(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractElementGroup(Long id, String eleGroupName,
			String eleGroupRemark, String keyFlag) {
		this.id = id;
		this.eleGroupName = eleGroupName;
		this.eleGroupRemark = eleGroupRemark;
		this.keyFlag = keyFlag;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "KEY_FLAG", length = 1)
	public String getKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(String keyFlag) {
		this.keyFlag = keyFlag;
	}

	@Column(name = "ELE_GROUP_NAME", length = 50)
	public String getEleGroupName() {
		return this.eleGroupName;
	}

	public void setEleGroupName(String eleGroupName) {
		this.eleGroupName = eleGroupName;
	}

	@Column(name = "ELE_GROUP_REMARK", length = 100)
	public String getEleGroupRemark() {
		return this.eleGroupRemark;
	}

	public void setEleGroupRemark(String eleGroupRemark) {
		this.eleGroupRemark = eleGroupRemark;
	}

	@Column(name = "ttype", length = 1)
	public void setTtype(Long ttype) {
		this.ttype = ttype;
	}

	public Long getTtype() {
		return ttype;
	}

	/**
	 * @return the version
	 */
	@Version
	@Column(name = "GROUP_VERSION", length = 100)
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 3;
		result = prime * result
				+ ((createBy == null) ? 0 : createBy.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((eleGroupName == null) ? 0 : eleGroupName.hashCode());
		result = prime * result
				+ ((eleGroupRemark == null) ? 0 : eleGroupRemark.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keyFlag == null) ? 0 : keyFlag.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		result = prime * result + ((ttype == null) ? 0 : ttype.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		AbstractElementGroup other = (AbstractElementGroup) obj;
		if (createBy == null) {
			if (other.createBy != null)
				return false;
		} else if (!createBy.equals(other.createBy))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (eleGroupName == null) {
			if (other.eleGroupName != null)
				return false;
		} else if (!eleGroupName.equals(other.eleGroupName))
			return false;
		if (eleGroupRemark == null) {
			if (other.eleGroupRemark != null)
				return false;
		} else if (!eleGroupRemark.equals(other.eleGroupRemark))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keyFlag == null) {
			if (other.keyFlag != null)
				return false;
		} else if (!keyFlag.equals(other.keyFlag))
			return false;
		if (lastUpdatedBy == null) {
			if (other.lastUpdatedBy != null)
				return false;
		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
			return false;
		if (lastUpdatedTime == null) {
			if (other.lastUpdatedTime != null)
				return false;
		} else if (!lastUpdatedTime.equals(other.lastUpdatedTime))
			return false;
		if (ttype == null) {
			if (other.ttype != null)
				return false;
		} else if (!ttype.equals(other.ttype))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	

}