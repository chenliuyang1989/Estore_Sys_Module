package com.gmcc.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractDemoBillDetail;

/**
 * DemoBillDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_WMS_DEMOBILL_DETAIL")
public class DemoBillDetail extends AbstractDemoBillDetail implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Constructors

	/** default constructor */
	public DemoBillDetail() {
	}

	/** minimal constructor */
	public DemoBillDetail(Long id, DemoBill demoBill) {
		super(id, demoBill);
	}

	/** full constructor */
	public DemoBillDetail(Long id, DemoBill demoBill,
			String content, String createdBy,
			Date createdTime, Date lastUpdatedTime, String lastUpdatedBy,
			String enabled) {
		super(id, demoBill, content, createdBy, createdTime,
				lastUpdatedTime, lastUpdatedBy, enabled);
	}

}
