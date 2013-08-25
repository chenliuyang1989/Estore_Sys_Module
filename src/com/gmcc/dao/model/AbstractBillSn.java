package com.gmcc.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.gmcc.dao.model.AbstractBillSn;
import com.ibm.model.BaseObject;

@MappedSuperclass
public abstract class AbstractBillSn extends BaseObject implements java.io.Serializable {

    // Fields    
	private static final long serialVersionUID = 1L;
	private Long id;
    private String snType;
    private String needDate;
    private Long snValue;
    private Date createdTime;
    private String createdBy;
    private Date lastUpdatedTime;
    private String lastUpdatedBy;
    private String snLength;//流水号长度
//    private Date snDate;//流水号日期
//    private String delimiter;//分隔符

    
	private String enabled;	
	@Transient
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

    // Constructors

    /** default constructor */
    public AbstractBillSn() {
    }

	/** minimal constructor */
    public AbstractBillSn(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractBillSn(Long id, String snType, String needDate, Long snValue) {
        this.id = id;
        this.needDate = needDate;
        this.snType = snType;
        this.snValue = snValue;
    }

   
    // Property accessors
    @Id 
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="SN_LENGTH",length=1)
   public String getSnLength() {
		return snLength;
	}
	public void setSnLength(String snLength) {
		this.snLength = snLength;
	}
	@Column(name="SN_TYPE", length=20)
	public String getSnType() {
		return snType;
	}

	
	public void setSnType(String snType) {
		this.snType = snType;
	}
	
	@Column(name="NEED_DATE", length=1)
	public String getNeedDate() {
		return needDate;
	}
	public void setNeedDate(String needDate) {
		this.needDate = needDate;
	}
	@Column(name="SN_VALUE")
	public Long getSnValue() {
		return snValue;
	}

	public void setSnValue(Long snValue) {
		this.snValue = snValue;
	}
	
	@Transient
    public Date getCreatedTime() {
        return this.createdTime;
    }
    
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    
    @Transient
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    @Transient
    public Date getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }
    
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    
    @Transient
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		result = prime * result
				+ ((needDate == null) ? 0 : needDate.hashCode());
		result = prime * result + ((snType == null) ? 0 : snType.hashCode());
		result = prime * result + ((snValue == null) ? 0 : snValue.hashCode());
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
		AbstractBillSn other = (AbstractBillSn) obj;
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
		if (needDate == null) {
			if (other.needDate != null)
				return false;
		} else if (!needDate.equals(other.needDate))
			return false;
		if (snType == null) {
			if (other.snType != null)
				return false;
		} else if (!snType.equals(other.snType))
			return false;
		if (snValue == null) {
			if (other.snValue != null)
				return false;
		} else if (!snValue.equals(other.snValue))
			return false;
		return true;
	}


    
}