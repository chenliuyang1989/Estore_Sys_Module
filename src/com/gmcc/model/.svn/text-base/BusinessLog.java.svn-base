package com.gmcc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractBusinessLog;

@Entity
@Table(name = "TB_APP_BUSINESS_LOG")
@SequenceGenerator(allocationSize=1, name = "LOG_PK", sequenceName = "BUSINESS_LOG_SEQ")
public class BusinessLog extends AbstractBusinessLog implements Serializable {

	private static final long serialVersionUID = 8943395520262363896L;

	/** default constructor */
	public BusinessLog() {
	}
	
	/** minimal constructor */
	public BusinessLog(Long id) {
		super(id);
	}
	
	/** full constructor */
	public BusinessLog(Long id, Element type, Long operatorId, String operatorName,
			Date operatorTime, String operatorObjectKey, 
			String operatorMethodKey, String operatorContent) {
		super(id, type, operatorId, operatorName, operatorTime, operatorObjectKey, operatorMethodKey, operatorContent);
	}

}
