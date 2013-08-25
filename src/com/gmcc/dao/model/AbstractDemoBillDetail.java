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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.gmcc.model.DemoBill;
import com.ibm.model.BaseObject;

/**
 * AbstractDemoBillDetail entity provides the base persistence definition
 * of the DemoBillDetail entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractDemoBillDetail extends BaseObject implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long id;
	private DemoBill demoBill;
	private String content;
	private String createdBy;
	private Date createdTime;
	private Date lastUpdatedTime;
	private String lastUpdatedBy;
	private String enabled;
	private int index;
	
	@Transient
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/** default constructor */
	public AbstractDemoBillDetail() {
	}

	/** minimal constructor */
	public AbstractDemoBillDetail(Long id,
			DemoBill demoBill) {
		this.id = id;
		this.demoBill = demoBill;
	}

	/** full constructor */
	public AbstractDemoBillDetail(Long id,
			DemoBill demoBill, String content,
			String createdBy, Date createdTime, Date lastUpdatedTime,
			String lastUpdatedBy, String enabled) {
		this.id = id;
		this.demoBill = demoBill;
		this.content = content;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.lastUpdatedBy = lastUpdatedBy;
		this.enabled = enabled;
	}

	// Property accessors
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HEAD_ID", nullable = false)
	public DemoBill getDemoBill() {
		return this.demoBill;
	}

	public void setDemoBill(DemoBill demoBill) {
		this.demoBill = demoBill;
	}

	@Column(name = "CONTENT", length = 50)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CREATED_BY", length = 20)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME", length = 7)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED_TIME", length = 7)
	public Date getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Column(name = "LAST_UPDATED_BY", length = 20)
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "ENABLED", length = 1)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result
				+ ((demoBill == null) ? 0 : demoBill.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDemoBillDetail other = (AbstractDemoBillDetail) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (demoBill == null) {
			if (other.demoBill != null)
				return false;
		} else if (!demoBill.equals(other.demoBill))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
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