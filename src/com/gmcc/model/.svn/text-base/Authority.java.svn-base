package com.gmcc.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.gmcc.dao.model.AbstractAuthority;

@Entity
@Table(name = "TB_APP_MENU")
public class Authority extends AbstractAuthority implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -4469676515702223320L;

	/** default constructor */
	public Authority() {
	}

	/** minimal constructor */
	public Authority(Long id) {
		super(id);
	}

	/** full constructor */
	public Authority(Long id, Long parentId, String menuApp, String menuUrl, String menuName,
			String menuPosition, String menuFunction, String menuLevel,
			String menuKey, Boolean isMenu) {
		super(id, parentId, menuApp, menuUrl, menuName, menuPosition, menuFunction,
				menuLevel, menuKey, isMenu);
	}

	@Override
	public String toString() {

		return ReflectionToStringBuilder.toString(this);
	}

	@Transient
	public String getAuthority() {

		return super.getMenuFunction();
	}

	public int compareTo(Object o) {

		return CompareToBuilder.reflectionCompare(this, o);
	}

}
