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

import com.gmcc.model.Element;
import com.ibm.model.BaseObject;
import com.ibm.util.annotation.DisplayColumn;

/**
 * AbstractBusinessLog entity provides the base persistence definition of the
 * BusinessLog entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractBusinessLog extends BaseObject {

	// Fields
	private static final long serialVersionUID = 1L;
	@DisplayColumn(property = "id", titleKey = "businessLog.id",initFlag="1")
	protected Long id;
	@DisplayColumn(property = "type.eleName", titleKey = "businessLog.type",initFlag="1")
	protected Element type;
	protected Long operatorId;
	@DisplayColumn(property = "city.eleName", titleKey = "field.cityName",initFlag="1")
     private Element city;
	@DisplayColumn(property = "operatorName", titleKey = "businessLog.operatorName",initFlag="1")
	protected String operatorName;
	@DisplayColumn(property = "loginIp", titleKey = "businessLog.loginIp",initFlag="1")
	protected String loginIp;
	@DisplayColumn(property = "operatorTime", titleKey = "businessLog.operatorTime", sortable=true,decorator = "com.gmcc.decorator.LongTimeWrapper",initFlag="1")
	protected Date operatorTime;
	@DisplayColumn(property = "operatorMethodKey", titleKey = "businessLog.operatorMethodKey",decorator = "com.gmcc.decorator.LogOperatorKeyWrapper",initFlag="1")
	protected String operatorMethodKey;
	@DisplayColumn(property = "operatorObjectKey", titleKey = "businessLog.operatorObjectKey",initFlag="1")
	protected String operatorObjectKey;
	@DisplayColumn(property = "operatorContent", titleKey = "businessLog.operatorContent",initFlag="1")
	protected String operatorContent;
	protected Date lastUpdatedTime;
	private String lastUpdatedBy;
	private Date createTime;
	private String createBy;
	
	private String enabled;	
	private Date operatorTimeBegin;
    private Date operatorTimeEnd;
    @Transient
	public Date getOperatorTimeBegin() {
		return operatorTimeBegin;
	}
	public void setOperatorTimeBegin(Date operatorTimeBegin) {
		this.operatorTimeBegin = operatorTimeBegin;
	}
	@Transient
	public Date getOperatorTimeEnd() {
		return operatorTimeEnd;
	}
	public void setOperatorTimeEnd(Date operatorTimeEnd) {
		this.operatorTimeEnd = operatorTimeEnd;
	}
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
	
	@Transient
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	@Transient
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	// Constructors

	/** default constructor */
	public AbstractBusinessLog() {
	}

	/** minimal constructor */
	public AbstractBusinessLog(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractBusinessLog(Long id, Element type, Long operatorId, String operatorName,
			Date operatorTime, String operatorObjectKey, 
			String operatorMethodKey, String operatorContent) {
		this.id = id;
		this.type = type;
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorTime = operatorTime;
		this.operatorObjectKey = operatorObjectKey;
		this.operatorMethodKey = operatorMethodKey;
		this.operatorContent = operatorContent;
	}

	// Property accessors
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "LOG_PK")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="type")
	public Element getType() {
		return type;
	}
	public void setType(Element type) {
		this.type = type;
	}
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CITYSYSID",referencedColumnName="ELE_CODE")
	public Element getCity() {
		return city;
	}
	public void setCity(Element city) {
		this.city = city;
	}
	@Column(name = "OPERATOR_ID", scale = 0)
	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "OPERATOR_NAME")
	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Column(name = "OPERATOR_TIME", length = 7)
	public Date getOperatorTime() {
		return this.operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	@Column(name = "OPERATOR_OBJECT_KEY")
	public String getOperatorObjectKey() {
		return this.operatorObjectKey;
	}

	public void setOperatorObjectKey(String operatorObjectKey) {
		this.operatorObjectKey = operatorObjectKey;
	}

	@Column(name = "OPERATOR_METHOD_KEY")
	public String getOperatorMethodKey() {
		return this.operatorMethodKey;
	}

	public void setOperatorMethodKey(String operatorMethodKey) {
		this.operatorMethodKey = operatorMethodKey;
	}

	@Column(name = "OPERATOR_CONTENT")
	public String getOperatorContent() {
		return operatorContent;
	}

	public void setOperatorContent(String operatorContent) {
		this.operatorContent = operatorContent;
	}
	@Column(name = "LOGINIP")
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 2;
		result = prime * result
				+ ((createBy == null) ? 0 : createBy.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		result = prime * result
				+ ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime
				* result
				+ ((operatorMethodKey == null) ? 0 : operatorMethodKey
						.hashCode());
		result = prime * result
				+ ((operatorName == null) ? 0 : operatorName.hashCode());
		result = prime
				* result
				+ ((operatorObjectKey == null) ? 0 : operatorObjectKey
						.hashCode());
		result = prime * result
				+ ((operatorTime == null) ? 0 : operatorTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		AbstractBusinessLog other = (AbstractBusinessLog) obj;
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
		if (operatorId == null) {
			if (other.operatorId != null)
				return false;
		} else if (!operatorId.equals(other.operatorId))
			return false;
		if (operatorMethodKey == null) {
			if (other.operatorMethodKey != null)
				return false;
		} else if (!operatorMethodKey.equals(other.operatorMethodKey))
			return false;
		if (operatorName == null) {
			if (other.operatorName != null)
				return false;
		} else if (!operatorName.equals(other.operatorName))
			return false;
		if (operatorObjectKey == null) {
			if (other.operatorObjectKey != null)
				return false;
		} else if (!operatorObjectKey.equals(other.operatorObjectKey))
			return false;
		if (operatorTime == null) {
			if (other.operatorTime != null)
				return false;
		} else if (!operatorTime.equals(other.operatorTime))
			return false;
		return true;
	}
	

}