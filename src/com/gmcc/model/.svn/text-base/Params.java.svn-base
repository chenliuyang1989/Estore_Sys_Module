package com.gmcc.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractParams;

@Entity
@Table(name="TB_APP_PARAMS")
public class Params extends AbstractParams implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** default constructor */
    public Params() {
    }

	/** minimal constructor */
    public Params(Long id) {
        super(id);        
    }
    
    /** full constructor */
    public Params(Long id, String code, String value, String commets, String enabled) {
        super(id, code, value, commets, enabled);        
    }
	
}
