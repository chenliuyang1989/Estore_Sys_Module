package com.gmcc.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ibm.model.BaseObject;


/**
 * AbstractSMSSendQueue entity provides the base persistence definition of the SMSSendQueue entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractSMSSendQueue extends BaseObject implements java.io.Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1565107629259629008L;
	// Fields    

     private Long id;
     private String phoneNumber;
     private String content;
     private String category;
     private String times;
     private String days;
     private Date designatetime;
     private String dealstatus;
     private String description;
     private String createdBy;
     private Date createdTime;
     private String lastUpdatedBy;
     private Date lastUpdatedTime;
     private String enabled;	

    // Constructors

    /** default constructor */
    public AbstractSMSSendQueue() {
    }

	/** minimal constructor */
    public AbstractSMSSendQueue(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractSMSSendQueue(Long id, String phoneNumber, String content, String times, String days, Date designatetime, String dealstatus, String createdBy, Date createdTime, String lastUpdatedBy, Date lastUpdatedTime) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.content = content;
        this.times = times;
        this.days = days;
        this.designatetime = designatetime;
        this.dealstatus = dealstatus;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

   
    public AbstractSMSSendQueue(String phoneNumber, String content,
			String category, String times, String days, Date designatetime,
			String dealstatus) {
    	this.phoneNumber = phoneNumber;
    	this.content = content;
    	this.category = category;
    	this.times = times;
    	this.days = days;
    	this.designatetime = designatetime;
    	this.dealstatus = dealstatus;
	}

	// Property accessors
    @Id 
    @Column(name="ID", unique=true, nullable=false, precision=20, scale=0)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="PHONE_NUMBER", length=20)

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Column(name="CONTENT", length=1000)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    @Column(name="CATEGORY", length=1)
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name="TIMES", length=100)

    public String getTimes() {
        return this.times;
    }
    
    public void setTimes(String times) {
        this.times = times;
    }
    
    @Column(name="DAYS", length=100)

    public String getDays() {
        return this.days;
    }
    
    public void setDays(String days) {
        this.days = days;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DESIGNATETIME", length=7)

    public Date getDesignatetime() {
        return this.designatetime;
    }
    
    public void setDesignatetime(Date designatetime) {
        this.designatetime = designatetime;
    }
    
    @Column(name="DEALSTATUS", length=1)

    public String getDealstatus() {
        return this.dealstatus;
    }
    
    public void setDealstatus(String dealstatus) {
        this.dealstatus = dealstatus;
    }
    @Column(name="description", length=200)
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="CREATED_BY", length=20)

    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_TIME", length=7)

    public Date getCreatedTime() {
        return this.createdTime;
    }
    
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    
    @Column(name="LAST_UPDATED_BY", length=20)

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_UPDATED_TIME", length=7)

    public Date getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }
    
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    @Transient
	public String getEnabled() {
		return enabled;
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
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		result = prime * result
				+ ((dealstatus == null) ? 0 : dealstatus.hashCode());
		result = prime * result
				+ ((designatetime == null) ? 0 : designatetime.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((times == null) ? 0 : times.hashCode());
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
		AbstractSMSSendQueue other = (AbstractSMSSendQueue) obj;
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
		if (days == null) {
			if (other.days != null)
				return false;
		} else if (!days.equals(other.days))
			return false;
		if (dealstatus == null) {
			if (other.dealstatus != null)
				return false;
		} else if (!dealstatus.equals(other.dealstatus))
			return false;
		if (designatetime == null) {
			if (other.designatetime != null)
				return false;
		} else if (!designatetime.equals(other.designatetime))
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
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (times == null) {
			if (other.times != null)
				return false;
		} else if (!times.equals(other.times))
			return false;
		return true;
	}
   
}