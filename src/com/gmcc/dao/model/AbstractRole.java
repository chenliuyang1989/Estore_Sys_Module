package com.gmcc.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.security.GrantedAuthority;

import com.gmcc.model.Authority;
import com.gmcc.model.Element;
import com.ibm.util.annotation.DisplayColumn;

@MappedSuperclass
public abstract class AbstractRole implements Serializable,
		GrantedAuthority {

	private static final long serialVersionUID = 5585817150957494111L;

	private Long roleId;
	@DisplayColumn(property = "name", titleKey = "role.name", initFlag="1")
	private String name;
	@DisplayColumn(property = "description", titleKey = "role.description", initFlag="1")
	private String description;
	
	private Date lastUpdatedTime;
	private String lastUpdatedBy;	
	@DisplayColumn(property = "roleType.eleName", titleKey = "role.roleType", initFlag="1")
	private Element roleType;
	protected List<Authority> authoritys = new ArrayList<Authority>();
	
	private Long menuId;
	private Integer statusFlag;
	@DisplayColumn(property = "menuStr", titleKey = "role.availableMenus", initFlag="1")
	private String menuStr;
	// Constructors

	/** default constructor */
	public AbstractRole() {
	}

	/** minimal constructor */
	public AbstractRole(Long id) {
		this.roleId = id;
	}

	public AbstractRole(String name) {
		this.name = name;
	}

	/** full constructor */
	public AbstractRole(Long id, String description, String name,
			Date lastUpdatedTime, String lastUpdatedBy) {
		this.roleId = id;
		this.description = description;
		this.name = name;
		this.lastUpdatedTime = lastUpdatedTime;
		this.lastUpdatedBy = lastUpdatedBy;

	}

	// Property accessors
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long id) {
		this.roleId = id;
	}

	@Column(name = "DESCRIPTION", length = 128)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "NAME", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LAST_UPDATED_TIME")
	public Date getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Column(name = "LAST_UPDATED_BY")
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TR_APP_ROLE_MENU", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = @JoinColumn(name = "menu_id"))
	// Fetch(FetchMode.SUBSELECT)
	public List<Authority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(List<Authority> authoritys) {
		this.authoritys = authoritys;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_TYPE")
	public Element getRoleType() {		
		if (this.roleType != null && this.roleType.getId() == -1) {
			return null;
		}
		return this.roleType;
	}

	/**
	 * @param roleType
	 *            the roleType to set
	 */
	public void setRoleType(Element roleType) {
		this.roleType = roleType;
	}
	@Column(name = "STATUS_FLAG")
	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	@Transient
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	@Transient
	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 15;
		result = prime * result
				+ ((authoritys == null) ? 0 : authoritys.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((roleType == null) ? 0 : roleType.hashCode());
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
		AbstractRole other = (AbstractRole) obj;
		if (authoritys == null) {
			if (other.authoritys != null)
				return false;
		} else if (!authoritys.equals(other.authoritys))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleType == null) {
			if (other.roleType != null)
				return false;
		} else if (!roleType.equals(other.roleType))
			return false;
		return true;
	}
	
	

}