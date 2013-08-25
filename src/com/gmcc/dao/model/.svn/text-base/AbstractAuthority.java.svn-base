package com.gmcc.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.security.GrantedAuthority;

import com.gmcc.dao.model.AbstractAuthority;
import com.ibm.model.BaseObject;

/**
 * AbstractAuthority entity provides the base persistence definition of the
 * Authority entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractAuthority extends BaseObject implements
		GrantedAuthority, Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -192668195762162767L;
	private Long id;
	private Long parentId;
	private String menuApp;
	private String menuUrl;
	private String menuName;
	private String menuPosition;
	private String menuFunction;
	private String menuLevel;
	private String menuKey;
	private Boolean isMenu;
	private Boolean status;

	private Date createTime;
	private String createBy;
	private Date lastUpdatedTime;
	private String lastUpdatedBy;

	private String enabled;	
	@Transient
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Column(name = "CREATED_TIME")
	public Date getCreatedTime() {
		return createTime;
	}

	public void setCreatedTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATED_BY", length = 20)
	public String getCreatedBy() {
		return createBy;
	}

	public void setCreatedBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "LAST_UPDATED_BY", length = 20)
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
	public AbstractAuthority() {
	}

	/** minimal constructor */
	public AbstractAuthority(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractAuthority(Long id, Long parentId, String menuApp, String menuUrl,
			String menuName, String menuPosition, String menuFunction,
			String menuLevel, String menuKey, Boolean isMenu) {
		this.id = id;
		this.parentId = parentId;
		this.menuApp = menuApp;
		this.menuUrl = menuUrl;
		this.menuName = menuName;
		this.menuPosition = menuPosition;
		this.menuFunction = menuFunction;
		this.menuLevel = menuLevel;
		this.menuKey = menuKey;
		this.isMenu = isMenu;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PARENT_ID", scale = 0)
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "MENU_APP", length = 50)
	public String getMenuApp() {
		return this.menuApp;
	}

	public void setMenuApp(String menuApp) {
		this.menuApp = menuApp;
	}

	@Column(name = "MENU_URL", length = 100)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "MENU_NAME", length = 100)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_POSITION", length = 6)
	public String getMenuPosition() {
		return this.menuPosition;
	}

	public void setMenuPosition(String menuPosition) {
		this.menuPosition = menuPosition;
	}

	@Column(name = "MENU_FUNCTION", length = 100)
	public String getMenuFunction() {
		return this.menuFunction;
	}

	public void setMenuFunction(String menuFunction) {
		this.menuFunction = menuFunction;
	}

	@Column(name = "MENU_LEVEL", length = 1)
	public String getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	@Column(name = "MENU_KEY", length = 100)
	public String getMenuKey() {
		return this.menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	@Column(name = "IS_MENU", precision = 1, scale = 0)
	public Boolean getIsMenu() {
		return this.isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}
	@Column(name = "STATUS_FLAG", precision = 1, scale = 0)
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createBy == null) ? 0 : createBy.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isMenu == null) ? 0 : isMenu.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		result = prime * result + ((menuApp == null) ? 0 : menuApp.hashCode());
		result = prime * result
				+ ((menuFunction == null) ? 0 : menuFunction.hashCode());
		result = prime * result + ((menuKey == null) ? 0 : menuKey.hashCode());
		result = prime * result
				+ ((menuLevel == null) ? 0 : menuLevel.hashCode());
		result = prime * result
				+ ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result
				+ ((menuPosition == null) ? 0 : menuPosition.hashCode());
		result = prime * result + ((menuUrl == null) ? 0 : menuUrl.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		AbstractAuthority other = (AbstractAuthority) obj;
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
		if (isMenu == null) {
			if (other.isMenu != null)
				return false;
		} else if (!isMenu.equals(other.isMenu))
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
		if (menuApp == null) {
			if (other.menuApp != null)
				return false;
		} else if (!menuApp.equals(other.menuApp))
			return false;
		if (menuFunction == null) {
			if (other.menuFunction != null)
				return false;
		} else if (!menuFunction.equals(other.menuFunction))
			return false;
		if (menuKey == null) {
			if (other.menuKey != null)
				return false;
		} else if (!menuKey.equals(other.menuKey))
			return false;
		if (menuLevel == null) {
			if (other.menuLevel != null)
				return false;
		} else if (!menuLevel.equals(other.menuLevel))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (menuPosition == null) {
			if (other.menuPosition != null)
				return false;
		} else if (!menuPosition.equals(other.menuPosition))
			return false;
		if (menuUrl == null) {
			if (other.menuUrl != null)
				return false;
		} else if (!menuUrl.equals(other.menuUrl))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}	

}