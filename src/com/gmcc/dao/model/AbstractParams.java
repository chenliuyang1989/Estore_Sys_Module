package com.gmcc.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.ibm.model.BaseObject;

@MappedSuperclass
public abstract class AbstractParams extends BaseObject implements java.io.Serializable {

    // Fields    
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String value;
	private String commets;
	private String enabled;	
	
	private Date createdTime;
    private String createdBy;
    private Date lastUpdatedTime;
    private String lastUpdatedBy;
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
	
    // Constructors

    /** default constructor */
    public AbstractParams() {
    }

	/** minimal constructor */
    public AbstractParams(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractParams(Long id, String code, String value, String commets, String enabled) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.enabled = enabled;
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
    
    @Column(name="CODE", length=64)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="VALUE", length=512)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name="COMMETS", length=512)
	public String getCommets() {
		return commets;
	}
	public void setCommets(String commets) {
		this.commets = commets;
	}
	
	@Column(name="ENABLED", length=1)
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
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((commets == null) ? 0 : commets.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		AbstractParams other = (AbstractParams) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (commets == null) {
			if (other.commets != null)
				return false;
		} else if (!commets.equals(other.commets))
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
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
    
}