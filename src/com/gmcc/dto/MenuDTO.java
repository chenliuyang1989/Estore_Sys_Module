package com.gmcc.dto;

import java.io.Serializable;
/**
 * 
 * * @author dengpenggg 
 *
 */
public class MenuDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String rootMenu;
	private String subMenus;
	
	
	public MenuDTO() {
		super();
	}

	public MenuDTO(String rootMenu, String subMenus) {
		super();
		this.rootMenu = rootMenu;
		this.subMenus = subMenus;
	}

	public String getRootMenu() {
		return rootMenu;
	}

	public void setRootMenu(String rootMenu) {
		this.rootMenu = rootMenu;
	}

	public String getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(String subMenus) {
		this.subMenus = subMenus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rootMenu == null) ? 0 : rootMenu.hashCode());
		result = prime * result
				+ ((subMenus == null) ? 0 : subMenus.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuDTO other = (MenuDTO) obj;
		if (rootMenu == null) {
			if (other.rootMenu != null)
				return false;
		} else if (!rootMenu.equals(other.rootMenu))
			return false;
		if (subMenus == null) {
			if (other.subMenus != null)
				return false;
		} else if (!subMenus.equals(other.subMenus))
			return false;
		return true;
	}
	
	
}
