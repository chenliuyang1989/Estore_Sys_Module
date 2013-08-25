package com.gmcc.dao.model;


import java.util.Date;

import javax.persistence.Column;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import javax.persistence.Transient;

import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.ibm.model.BaseObject;
import com.ibm.util.annotation.DisplayColumn;

@MappedSuperclass
public abstract class AbstractHrCompany extends BaseObject implements java.io.Serializable {

    // Fields    
	private static final long serialVersionUID = 1L;
	private Long id;
    private String compLevel;
    @DisplayColumn(property = "compCode", titleKey = "company.compCode", initFlag="1")
    private String compCode;
    @DisplayColumn(property = "compName", titleKey = "company.companyname", initFlag="1")
    private String compName;
    @DisplayColumn(property = "compSubname", titleKey = "company.compsubname", initFlag="1")
    private String compSubname;
    @DisplayColumn(property = "compFullname", titleKey = "company.compFullname", initFlag="1")
    private String compFullname;
   
    @DisplayColumn(property = "addr", titleKey = "company.addr", initFlag="1")
    private String addr;
    @DisplayColumn(property = "tel", titleKey = "company.tel", initFlag="1")
    private String tel;
    @DisplayColumn(property = "fax", titleKey = "company.fax", initFlag="1")
    private String fax;    
    @DisplayColumn(property = "parentId.compName", titleKey = "company.parent", initFlag="1")
    private HrCompany parentId;
    @DisplayColumn(property = "orgProperties", titleKey = "company.orgProperties",decorator = "com.gmcc.decorator.OrgPropertiesWrapper", initFlag="1")
    private String orgProperties;
    @DisplayColumn(property = "orgType.eleName", titleKey = "company.orgType.eleName", initFlag="1")
    private Element orgType;
//    @DisplayColumn(property = "isPurchase", titleKey = "company.isPurchase",decorator = "com.gmcc.decorator.IsNeedInvoice", initFlag="1")
    private String isPurchase;
//    @DisplayColumn(property = "isSales", titleKey = "company.isSales",decorator = "com.gmcc.decorator.IsNeedInvoice", initFlag="1")
    private String isSales;
//    @DisplayColumn(property = "isLogistics", titleKey = "company.isLogistics",decorator = "com.gmcc.decorator.IsNeedInvoice", initFlag="1")
    private String isLogistics;
//    @DisplayColumn(property = "isWareHouse", titleKey = "company.isWareHouse",decorator = "com.gmcc.decorator.IsNeedInvoice", initFlag="1")
    private String isWareHouse;
    @DisplayColumn(property = "orgCategory", titleKey = "company.orgCategory", initFlag="1")
    private String orgCategory;//采购-销售-物流-仓储
    private Long orgSysId;
    private Date createdTime;
    private String createdBy;
    private Date lastUpdatedTime;
    private String lastUpdatedBy;
    private String note;
    private String enabled;
    
    private String pidLevel;
    private String parName;
    private Long parId;
    private Long orgTypeId;
    
    @Transient
    public String getOrgCategory() {
    	StringBuffer str=new StringBuffer("");
		orgCategory=str.append(this.isPurchase==null?"0":this.isPurchase).append(this.isSales==null?"0":this.isSales)
		.append(this.isLogistics==null?"0":this.isLogistics).append(this.isWareHouse==null?"0":this.isWareHouse).toString();
		return orgCategory;
//		return this.isPurchase+this.isSales+
//				this.isLogistics+this.isWareHouse;
	}

	public void setOrgCategory(String orgCategory) {
//		this.orgCategory = this.isPurchase==null?"":this.isPurchase+this.isSales==null?"":this.isSales+
//				this.isLogistics==null?"":this.isLogistics+this.isWareHouse==null?"":this.isWareHouse;;
		this.orgCategory=orgCategory;
	}

	@Transient
    public String getParName() {
		return parName;
	}

	public void setParName(String parName) {
		this.parName = parName;
	}

	@Transient
	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}
	@Transient
	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	// Constructors
    @Transient
    public String getPidLevel() {
		return pidLevel;
	}

	public void setPidLevel(String pidLevel) {
		this.pidLevel = pidLevel;
	}

	/** default constructor */
    public AbstractHrCompany() {
    }

	/** minimal constructor */
    public AbstractHrCompany(Long id, String compLevel, String compCode, String compName, Date createdTime, String createdBy, String enabled) {
        this.id = id;
        this.compLevel = compLevel;
        this.compCode = compCode;
        this.compName = compName;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.enabled = enabled;
    }
    
    /** full constructor */
    public AbstractHrCompany(Long id, String compLevel, String compCode, String compName, String compSubname, String compFullname, HrCompany parentId, String addr, String tel, String fax, Date createdTime, String createdBy, Date lastUpdatedTime, String lastUpdatedBy, String note, String enabled) {
        this.id = id;
        this.compLevel = compLevel;
        this.compCode = compCode;
        this.compName = compName;
        this.compSubname = compSubname;
        this.compFullname = compFullname;
        this.parentId = parentId;
        this.addr = addr;
        this.tel = tel;
        this.fax = fax;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.lastUpdatedTime = lastUpdatedTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.note = note;
        this.enabled = enabled;
    }

   
    @Id
    @Column(name="COMP_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="ORGSYSID")
    public Long getOrgSysId() {
		return orgSysId;
	}

	public void setOrgSysId(Long orgSysId) {
		this.orgSysId = orgSysId;
	}

	@Column(name="COMP_LEVEL", nullable=false, length=1)
    public String getCompLevel() {
        return this.compLevel;
    }
    
    public void setCompLevel(String compLevel) {
        this.compLevel = compLevel;
    }
    
    @Column(name="COMP_CODE", unique=true, nullable=false, length=50)
    public String getCompCode() {
        return this.compCode;
    }
    
    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }
    
    @Column(name="COMP_NAME", nullable=false, length=50)
    public String getCompName() {
        return this.compName;
    }
    
    public void setCompName(String compName) {
        this.compName = compName;
    }
    
    @Column(name="COMP_SUBNAME", length=50)
    public String getCompSubname() {
        return this.compSubname;
    }
    
    public void setCompSubname(String compSubname) {
        this.compSubname = compSubname;
    }
    
    @Column(name="COMP_FULLNAME", length=100)
    public String getCompFullname() {
        return this.compFullname;
    }
    
    public void setCompFullname(String compFullname) {
        this.compFullname = compFullname;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="PARENT_ID")
    public HrCompany getParentId() {
		return parentId;
	}

	public void setParentId(HrCompany parentId) {
		this.parentId = parentId;
	}
    @Column(name="ADDR", length=100)
    public String getAddr() {
        return this.addr;
    }
	public void setAddr(String addr) {
        this.addr = addr;
    }
    
    @Column(name="TEL", length=20)
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Column(name="FAX", length=20)
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
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
    
    @Column(name="NOTE", length=100)
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    @Column(name="ENABLED", nullable=false, length=1)
    public String getEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
   
	 @Column(name="ORGPROPERTIES",length=1)
	public String getOrgProperties() {
		return orgProperties;
	}

	public void setOrgProperties(String orgProperties) {
		this.orgProperties = orgProperties;
	}
	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="ORGTYPE")
	public Element getOrgType() {
		return orgType;
	}

	public void setOrgType(Element orgType) {
		this.orgType = orgType;
	}
	@Column(name="ISPURCHASE", length=1)
	public String getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(String isPurchase) {
		this.isPurchase = isPurchase;
	}
	@Column(name="ISSALES", length=1)
	public String getIsSales() {
		return isSales;
	}

	public void setIsSales(String isSales) {
		this.isSales = isSales;
	}
	@Column(name="ISLOGISTICS", length=1)
	public String getIsLogistics() {
		return isLogistics;
	}

	public void setIsLogistics(String isLogistics) {
		this.isLogistics = isLogistics;
	}
	@Column(name="ISWAREHOUSE", length=1)
	public String getIsWareHouse() {
		return isWareHouse;
	}

	public void setIsWareHouse(String isWareHouse) {
		this.isWareHouse = isWareHouse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result
				+ ((compCode == null) ? 0 : compCode.hashCode());
		result = prime * result
				+ ((compFullname == null) ? 0 : compFullname.hashCode());
		result = prime * result
				+ ((compLevel == null) ? 0 : compLevel.hashCode());
		result = prime * result
				+ ((compName == null) ? 0 : compName.hashCode());
		result = prime * result
				+ ((compSubname == null) ? 0 : compSubname.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		AbstractHrCompany other = (AbstractHrCompany) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (compCode == null) {
			if (other.compCode != null)
				return false;
		} else if (!compCode.equals(other.compCode))
			return false;
		if (compFullname == null) {
			if (other.compFullname != null)
				return false;
		} else if (!compFullname.equals(other.compFullname))
			return false;
		if (compLevel == null) {
			if (other.compLevel != null)
				return false;
		} else if (!compLevel.equals(other.compLevel))
			return false;
		if (compName == null) {
			if (other.compName != null)
				return false;
		} else if (!compName.equals(other.compName))
			return false;
		if (compSubname == null) {
			if (other.compSubname != null)
				return false;
		} else if (!compSubname.equals(other.compSubname))
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
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
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
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}   
    

}