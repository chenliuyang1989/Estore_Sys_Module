package com.gmcc.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractElement;
import com.gmcc.model.ElementGroup;

@Entity
@Table(name = "TB_APP_ELEMENT")
public class Element extends AbstractElement implements java.io.Serializable,Comparable {

	// Constructors

	private static final long serialVersionUID = 1L;

	/** default constructor */
	public Element() {
	}

	/** minimal constructor */
	public Element(Long id, ElementGroup elementGroup) {
		super(id, elementGroup);
	}

	public Element(Long id, String name) {
		super(id, name);
	}

	/** full constructor */
	public Element(Long id, ElementGroup elementGroup, String eleCode,
			String eleName, Long orderNum) {
		super(id, elementGroup, eleCode, eleName, orderNum);
	}
	public int compareTo(Object arg0) {
		if(arg0 instanceof Element){
			return (this.getOrderNum().intValue()-((Element)arg0).getOrderNum().intValue())  ;
		}else{
			return -1;
		}
	}

}
