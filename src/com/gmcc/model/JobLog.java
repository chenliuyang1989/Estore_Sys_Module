package com.gmcc.model;

import java.sql.Blob;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractJobLog;

@Entity
@Table(name="T_JOB_LOG")
public class JobLog extends AbstractJobLog implements java.io.Serializable {

    // Constructors

	private static final long serialVersionUID = 1L;

	/** default constructor */
    public JobLog() {
    }

	/** minimal constructor */
    public JobLog(Long logid) {
        super(logid);        
    }
    
    /** full constructor */
    public JobLog(Long logid, JobConfig jobConfig, Date startdate, Date enddate, byte[] exceptioninfo, String runmachineip, String runport, String runappname) {
        super(logid, jobConfig, startdate, enddate, exceptioninfo, runmachineip, runport, runappname);        
    }
   
}
