package com.gmcc.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.gmcc.dao.model.AbstractBillSn;

@Entity
@Table(name="TB_APP_BILL_SN")
public class BillSn extends AbstractBillSn implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** default constructor */
    public BillSn() {
    }

	/** minimal constructor */
    public BillSn(Long id) {
        super(id);        
    }
    
    /** full constructor */
    public BillSn(Long id, String snType, String needDate, Long snValue) {
        super(id, snType, needDate, snValue);        
    }
}
