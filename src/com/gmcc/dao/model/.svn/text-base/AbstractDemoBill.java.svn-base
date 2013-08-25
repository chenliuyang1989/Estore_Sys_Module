package com.gmcc.dao.model;

import com.gmcc.model.Element;
import com.ibm.model.BaseObject;
import com.gmcc.model.DemoBillDetail;
import com.ibm.util.annotation.DisplayColumn;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class AbstractDemoBill extends BaseObject implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long id;
	@DisplayColumn(property = "billNum", titleKey = "advanceDemo.billNum", href="advanceDemoDataAction.html?view=true", paramId = "advanceDemo.id", paramProperty="id", sortable=true)
	private String billNum;
	@DisplayColumn(property = "billName", titleKey = "advanceDemo.billName")
	private String billName;
	@DisplayColumn(property = "billStatus.eleName", titleKey = "advanceDemo.billStatus", sortable=true)
	private Element billStatus;	
	@DisplayColumn(property = "eleCode", titleKey = "advanceDemo.billStatus", decorator = "com.gmcc.decorator.BillStatusWrapper")
	private String eleCode;	
	@DisplayColumn(property = "tranTime", titleKey = "advanceDemo.tranTime" , decorator = "com.gmcc.decorator.ShortDateWrapper", sortable=true)
	private Date tranTime;	
	private Date createdTime;	
	private String createdBy;
	@DisplayColumn(property = "lastUpdatedTime", titleKey = "field.lastUpdatedTime", sortable=true)
	private Date lastUpdatedTime;
	@DisplayColumn(property = "lastUpdatedBy", titleKey = "field.lastUpdatedBy", sortable=true)
	private String lastUpdatedBy;	
	private String enabled;
	private Set<DemoBillDetail> demoBillDetails = new HashSet<DemoBillDetail>(0);

	// Constructors

	/** default constructor */
	public AbstractDemoBill() {
	}

	/** minimal constructor */
	public AbstractDemoBill(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractDemoBill(Long id, String billNum, String billName, Element billStatus,
			Date tranTime, Date createdTime, String createdBy, Date lastUpdatedTime, String lastUpdatedBy,
			String enabled, Set<DemoBillDetail> demoBillDetails) {
		this.id = id;
		this.billNum = billNum;
		this.billName = billName;
		this.billStatus = billStatus;
		this.tranTime = tranTime;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.lastUpdatedTime = lastUpdatedTime;
		this.lastUpdatedBy = lastUpdatedBy;		
		this.enabled = enabled;
		this.demoBillDetails = demoBillDetails;
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

	@Column(name = "BILL_NUM", length = 20)
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	
	@Column(name = "BILL_NAME", length = 50)
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BILL_STATUS")
	public Element getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(Element billStatus) {
		this.billStatus = billStatus;
	}
	
	@Column(name = "ELE_CODE", length = 20)
	public String getEleCode() {
		return eleCode;
	}

	public void setEleCode(String eleCode) {
		this.eleCode = eleCode;
	}

	@Column(name = "TRAN_TIME", length = 7)
	public Date getTranTime() {
		return this.tranTime;
	}
	public void setTranTime(Date tranTime) {
		this.tranTime = tranTime;
	}

	@Column(name = "CREATED_TIME", length = 7)
	public Date getCreatedTime() {
		return this.createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "CREATED_BY", length = 20)
	public String getCreatedBy() {
		return this.createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "demoBill")
	public Set<DemoBillDetail> getDemoBillDetails() {
		return this.demoBillDetails;
	}

	public void setDemoBillDetails(Set<DemoBillDetail> demoBillDetails) {
		this.demoBillDetails = demoBillDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((billName == null) ? 0 : billName.hashCode());
		result = prime * result + ((billNum == null) ? 0 : billNum.hashCode());		
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
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
		AbstractDemoBill other = (AbstractDemoBill) obj;
		if (billName == null) {
			if (other.billName != null)
				return false;
		} else if (!billName.equals(other.billName))
			return false;
		if (billNum == null) {
			if (other.billNum != null)
				return false;
		} else if (!billNum.equals(other.billNum))
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