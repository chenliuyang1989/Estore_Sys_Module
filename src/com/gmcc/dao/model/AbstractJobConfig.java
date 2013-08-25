package com.gmcc.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.gmcc.model.JobLog;
import com.ibm.model.BaseObject;
import com.ibm.util.annotation.DisplayColumn;

@MappedSuperclass

public abstract class AbstractJobConfig extends BaseObject implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields    
    private Long id;
    @DisplayColumn(property = "jobname", titleKey = "job.jobname", initFlag="1")
    private String jobname;
    @DisplayColumn(property = "jobdesc", titleKey = "job.jobdesc", initFlag="1")
    private String jobdesc;
    @DisplayColumn(property = "jobschedule", titleKey = "job.jobschedule", initFlag="1")
    private String jobschedule;
    @DisplayColumn(property = "currentstatus", titleKey = "job.currentstatus", initFlag="1", decorator = "com.gmcc.decorator.JobStatusWrapper")
    private String currentstatus;//0-已经停止，1-正在运行，2-已经暂停，3-已经启动，4-已经关闭
    @DisplayColumn(property = "startdate", titleKey = "job.startdate", decorator = "com.gmcc.decorator.LongTimeWrapper", initFlag="1")
    private Date startdate;
    @DisplayColumn(property = "enddate", titleKey = "job.enddate", decorator = "com.gmcc.decorator.LongTimeWrapper", initFlag="1")
    private Date enddate;
    //@DisplayColumn(property = "currentstatus", titleKey = "job.currentstatus")
    private String classname;
    private String command;
   
    @DisplayColumn(property = "runmachineip", titleKey = "job.runmachineip", initFlag="1")
    private String runmachineip;
//    @DisplayColumn(property = "runport", titleKey = "job.runport")
    private String runport;
    private Set<JobLog> jobLogs = new HashSet<JobLog>(0);

    private Date scheduleDatel;
	private Date createdTime;
    private String createdBy;
    private Date lastUpdatedTime;
    private String lastUpdatedBy;
    private String enabled;	
    
    @Transient
    public Date getCreatedTime() {
        return this.createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    @Transient
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    @Transient
    public Date getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    @Transient
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    @Transient
    public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Transient
    public Date getScheduleDatel() {
		return scheduleDatel;
	}
	public void setScheduleDatel(Date scheduleDatel) {
		this.scheduleDatel = scheduleDatel;
	}
    // Constructors

	/** default constructor */
    public AbstractJobConfig() {
    }

	/** minimal constructor */
    public AbstractJobConfig(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractJobConfig(Long id, String jobname, String jobschedule, String currentstatus, Date startdate, Date enddate, String classname, String command, String jobdesc, String runmachineip, String runport, Set<JobLog> jobLogs) {
        this.id = id;
        this.jobname = jobname;
        this.jobschedule = jobschedule;
        this.currentstatus = currentstatus;
        this.startdate = startdate;
        this.enddate = enddate;
        this.classname = classname;
        this.command = command;
        this.jobdesc = jobdesc;
        this.runmachineip = runmachineip;
        this.runport = runport;
        this.jobLogs = jobLogs;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="JOBID", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="JOBNAME", length=300)

    public String getJobname() {
        return this.jobname;
    }
    
    public void setJobname(String jobname) {
        this.jobname = jobname;
    }
    
    @Column(name="JOBSCHEDULE", length=300)

    public String getJobschedule() {
        return this.jobschedule;
    }
    
    public void setJobschedule(String jobschedule) {
        this.jobschedule = jobschedule;
    }
    
    @Column(name="CURRENTSTATUS", length=10)

    public String getCurrentstatus() {
        return this.currentstatus;
    }
    
    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="STARTDATE")

    public Date getStartdate() {
        return this.startdate;
    }
    
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ENDDATE")

    public Date getEnddate() {
        return this.enddate;
    }
    
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    
    @Column(name="CLASSNAME", length=500)

    public String getClassname() {
        return this.classname;
    }
    
    public void setClassname(String classname) {
        this.classname = classname;
    }
    
    @Column(name="COMMAND", length=60)

    public String getCommand() {
        return this.command;
    }
    
    public void setCommand(String command) {
        this.command = command;
    }
    
    @Column(name="JOBDESC", length=400)

    public String getJobdesc() {
        return this.jobdesc;
    }
    
    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }
    
    @Column(name="RUNMACHINEIP", length=60)

    public String getRunmachineip() {
        return this.runmachineip;
    }
    
    public void setRunmachineip(String runmachineip) {
        this.runmachineip = runmachineip;
    }
    
    @Column(name="RUNPORT", length=10)

    public String getRunport() {
        return this.runport;
    }
    
    public void setRunport(String runport) {
        this.runport = runport;
    }
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="jobConfig")
    public Set<JobLog> getJobLogs() {
        return this.jobLogs;
    }
    
    public void setJobLogs(Set<JobLog> jobLogs) {
        this.jobLogs = jobLogs;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classname == null) ? 0 : classname.hashCode());
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result
				+ ((currentstatus == null) ? 0 : currentstatus.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((enddate == null) ? 0 : enddate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
		result = prime * result + ((jobdesc == null) ? 0 : jobdesc.hashCode());
		result = prime * result + ((jobname == null) ? 0 : jobname.hashCode());
		result = prime * result
				+ ((jobschedule == null) ? 0 : jobschedule.hashCode());
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		result = prime * result
				+ ((runmachineip == null) ? 0 : runmachineip.hashCode());
		result = prime * result + ((runport == null) ? 0 : runport.hashCode());
		result = prime * result
				+ ((startdate == null) ? 0 : startdate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractJobConfig other = (AbstractJobConfig) obj;
		if (classname == null) {
			if (other.classname != null)
				return false;
		} else if (!classname.equals(other.classname))
			return false;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (currentstatus == null) {
			if (other.currentstatus != null)
				return false;
		} else if (!currentstatus.equals(other.currentstatus))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (enddate == null) {
			if (other.enddate != null)
				return false;
		} else if (!enddate.equals(other.enddate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jobLogs == null) {
			if (other.jobLogs != null)
				return false;
		} else if (!jobLogs.equals(other.jobLogs))
			return false;
		if (jobdesc == null) {
			if (other.jobdesc != null)
				return false;
		} else if (!jobdesc.equals(other.jobdesc))
			return false;
		if (jobname == null) {
			if (other.jobname != null)
				return false;
		} else if (!jobname.equals(other.jobname))
			return false;
		if (jobschedule == null) {
			if (other.jobschedule != null)
				return false;
		} else if (!jobschedule.equals(other.jobschedule))
			return false;
		if (lastUpdatedBy == null) {
			if (other.lastUpdatedBy != null)
				return false;
		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
			return false;
		if (lastUpdatedTime == null) {
			if (other.lastUpdatedTime != null)
				return false;
		} else if (!lastUpdatedTime.equals(other.lastUpdatedTime))
			return false;
		if (runmachineip == null) {
			if (other.runmachineip != null)
				return false;
		} else if (!runmachineip.equals(other.runmachineip))
			return false;
		if (runport == null) {
			if (other.runport != null)
				return false;
		} else if (!runport.equals(other.runport))
			return false;
		if (startdate == null) {
			if (other.startdate != null)
				return false;
		} else if (!startdate.equals(other.startdate))
			return false;
		return true;
	}

}