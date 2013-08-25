package com.gmcc.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.gmcc.dao.model.AbstractDemoBill;
import com.gmcc.model.Element;

@Entity
@Table(name = "TB_WMS_DEMOBILL")
public class DemoBill extends AbstractDemoBill implements
		java.io.Serializable {

	// Constructors
	private static final long serialVersionUID = 1L;

	/** default constructor */
	public DemoBill() {
	}

	/** minimal constructor */
	public DemoBill(Long id) {
		super(id);
	}

	/** full constructor */
	public DemoBill(Long id, String billNum, String billName, Element billStatus,
			Date tranTime, Date createdTime, String createdBy, Date lastUpdatedTime, String lastUpdatedBy,
			String enabled, Set<DemoBillDetail> demoBillDetails) {
		super(id, billNum, billName, billStatus, tranTime, createdTime, createdBy, lastUpdatedTime, lastUpdatedBy,
				enabled, demoBillDetails);
	}

}
