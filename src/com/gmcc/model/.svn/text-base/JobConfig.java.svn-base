package com.gmcc.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmcc.dao.model.AbstractJobConfig;

@Entity
@Table(name="T_JOB_CONFIG")
public class JobConfig extends AbstractJobConfig implements java.io.Serializable {

    // Constructors

	private static final long serialVersionUID = 1L;

	/** default constructor */
    public JobConfig() {
    }

	/** minimal constructor */
    public JobConfig(Long jobid) {
        super(jobid);        
    }
    
    /** full constructor */
    public JobConfig(Long jobid, String jobname, String jobschedule, String currentstatus, Date startdate, Date enddate, String classname, String command, String jobdesc, String runmachineip, String runport, Set<JobLog> jobLogs) {
        super(jobid, jobname, jobschedule, currentstatus, startdate, enddate, classname, command, jobdesc, runmachineip, runport, jobLogs);        
    }
   
}
