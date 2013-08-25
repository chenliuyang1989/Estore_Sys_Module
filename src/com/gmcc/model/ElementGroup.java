package com.gmcc.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractElementGroup;

@Entity
@Table(name = "TB_APP_ELEMENT_GROUP")
public class ElementGroup extends AbstractElementGroup implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -4262818393209319656L;

	/** default constructor */
	public ElementGroup() {
	}

}
