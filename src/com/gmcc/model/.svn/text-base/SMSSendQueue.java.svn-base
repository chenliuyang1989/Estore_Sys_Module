package com.gmcc.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractSMSSendQueue;


/**
 * SMSSendQueue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_SMS_SEND_QUEUE")
public class SMSSendQueue extends AbstractSMSSendQueue implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public SMSSendQueue() {
    }

	/** minimal constructor */
    public SMSSendQueue(Long id) {
        super(id);        
    }
    
    /** full constructor */
    public SMSSendQueue(Long id, String phoneNumber, String content, String times, String days, Date designatetime, String dealstatus, String createdBy, Date createdTime, String lastUpdatedBy, Date lastUpdatedTime) {
        super(id, phoneNumber, content, times, days, designatetime, dealstatus, createdBy, createdTime, lastUpdatedBy, lastUpdatedTime);        
    }
    
    public SMSSendQueue(String phoneNumber, String content, String category, String times, String days, Date designatetime, String dealstatus) {
    	super(phoneNumber, content, category, times, days, designatetime, dealstatus);
    }
   
}
