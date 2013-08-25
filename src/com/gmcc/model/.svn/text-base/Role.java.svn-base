package com.gmcc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.gmcc.dao.model.AbstractRole;
import com.gmcc.model.Authority;

@Entity
@Table(name = "TB_APP_ROLE")
public class Role extends AbstractRole implements java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -5257585741534040071L;

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(Long id) {
		super(id);
	}

	/** minimal constructor */
	public Role(String name) {
		super(name);
	}

	/** full constructor */
	public Role(Long id, String description, String name, Date lastUpdatedTime,
			String lastUpdatedBy) {
		super(id, description, name, lastUpdatedTime, lastUpdatedBy);
	}

	@Transient
	public String getAuthority() {

		return null;
	}

	public void addAuthority(Authority authority) {
		super.getAuthoritys().add(authority);
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public int compareTo(Object o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}

}
