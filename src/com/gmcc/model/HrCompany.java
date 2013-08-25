package com.gmcc.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.gmcc.dao.model.AbstractHrCompany;

@Entity
@Table(name="TB_HR_COMPANY")
public class HrCompany extends AbstractHrCompany implements java.io.Serializable {

    // Constructors
    
	private static final long serialVersionUID = 1L;

	/** default constructor */
    public HrCompany() {
    }

	/** minimal constructor */
    public HrCompany(Long id, String compLevel, String compCode, String compName, Date createdTime, String createdBy, String enabled) {
        super(id, compLevel, compCode, compName, createdTime, createdBy, enabled);        
    }
    
    /** full constructor */
    public HrCompany(Long id, String compLevel, String compCode, String compName, String compSubname, String compFullname, HrCompany parentId, String addr, String tel, String fax, Date createdTime, String createdBy, Date lastUpdatedTime, String lastUpdatedBy, String note, String enabled) {
        super(id, compLevel, compCode, compName, compSubname, compFullname, parentId, addr, tel, fax, createdTime, createdBy, lastUpdatedTime, lastUpdatedBy, note, enabled);        
    }    
    
   
}
