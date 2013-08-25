package com.gmcc.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.gmcc.dao.model.AbstractElement;
import com.gmcc.model.ElementGroup;
import com.ibm.model.BaseObject;

/**
 * AbstractElement entity provides the base persistence definition of the
 * Element entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractElement extends BaseObject {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private ElementGroup elementGroup;
	private String eleName;
	private String eleCode;
	private String keyFlag;
	private Long orderNum;
	
	private Date createTime;
	private String createBy;
	private Date lastUpdatedTime; // 最后更新时间
	private String lastUpdatedBy; // 最后更新人
	
	private String enabled;	
	@Transient
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
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
	// Constructors

	/** default constructor */
	public AbstractElement() {
	}

	/** minimal constructor */
	public AbstractElement(Long id, ElementGroup elementGroup) {
		this.id = id;
		this.elementGroup = elementGroup;
	}

	public AbstractElement(Long id, String name) {
		this.id = id;
		this.eleName = name;
	}

	/** full constructor */
	public AbstractElement(Long id, ElementGroup elementGroup, String eleCode,
			String eleName, Long orderNum) {
		this.id = id;
		this.elementGroup = elementGroup;
		this.eleCode = eleCode;
		this.eleName = eleName;
		this.orderNum = orderNum;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "KEY_FLAG", length = 50)
	public String getKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(String keyFlag) {
		this.keyFlag = keyFlag;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ELE_GROUP_ID")
	public ElementGroup getElementGroup() {
		return this.elementGroup;
	}

	public void setElementGroup(ElementGroup elementGroup) {
		this.elementGroup = elementGroup;
	}
	

	@Column(name = "ELE_CODE", length = 40)
	public String getEleCode() {
		return eleCode;
	}

	public void setEleCode(String eleCode) {
		this.eleCode = eleCode;
	}

	@Column(name = "ELE_NAME", length = 100)
	public String getEleName() {
		return this.eleName;
	}

	public void setEleName(String eleName) {
		this.eleName = eleName;
	}
	
	@Column(name = "ORDER_NUM")
	public Long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 4;
		result = prime * result
				+ ((createBy == null) ? 0 : createBy.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((eleCode == null) ? 0 : eleCode.hashCode());
		result = prime * result + ((eleName == null) ? 0 : eleName.hashCode());
		result = prime * result
				+ ((elementGroup == null) ? 0 : elementGroup.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		AbstractElement other = (AbstractElement) obj;
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
		if (eleCode == null) {
			if (other.eleCode != null)
				return false;
		} else if (!eleCode.equals(other.eleCode))
			return false;
		if (eleName == null) {
			if (other.eleName != null)
				return false;
		} else if (!eleName.equals(other.eleName))
			return false;
		if (elementGroup == null) {
			if (other.elementGroup != null)
				return false;
		} else if (!elementGroup.equals(other.elementGroup))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return true;
	}
	

}