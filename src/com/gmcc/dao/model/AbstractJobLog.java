package com.gmcc.dao.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.gmcc.model.JobConfig;
import com.ibm.model.BaseObject;
import com.ibm.util.annotation.DisplayColumn;

@MappedSuperclass
public abstract class AbstractJobLog extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields    
    private Long id;
    @DisplayColumn(property = "jobConfig.jobname", titleKey = "jobLog.jobConfig.jobname", initFlag="1")
    private JobConfig jobConfig;
    @DisplayColumn(property = "jobConfig.runmachineip", titleKey = "job.runmachineip", initFlag="1")
    private String runmachineip;
    @DisplayColumn(property = "startdate", titleKey = "joblog.date",decorator="com.gmcc.decorator.LongTimeWrapper", initFlag="1")
    private Date startdate;
    
    private Date enddate;
    @DisplayColumn(property = "logInfo", titleKey = "joblog.exceptioninfo", initFlag="1")
    private String logInfo;
    private byte[] exceptioninfo;
    
    private String runport;
    private String runappname;
    
	private Date createdTime;
    private String createdBy;
    private Date lastUpdatedTime;
    private String lastUpdatedBy;
    private String enabled;	
    
    @Transient
	public String getLogInfo() {
		return new String(exceptioninfo);
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = new String(exceptioninfo);
	}
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

    // Constructors

    /** default constructor */
    public AbstractJobLog() {
    }

	/** minimal constructor */
    public AbstractJobLog(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractJobLog(Long id, JobConfig jobConfig, Date startdate, Date enddate, byte[] exceptioninfo, String runmachineip, String runport, String runappname) {
        this.id = id;
        this.jobConfig = jobConfig;
        this.startdate = startdate;
        this.enddate = enddate;
        this.exceptioninfo = exceptioninfo;
        this.runmachineip = runmachineip;
        this.runport = runport;
        this.runappname = runappname;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="LOGID", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="JOBID")

    public JobConfig getJobConfig() {
        return this.jobConfig;
    }
    
    public void setJobConfig(JobConfig jobConfig) {
        this.jobConfig = jobConfig;
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
      @Lob
      @Basic(fetch = FetchType.LAZY)
      @Column(name = "EXCEPTIONINFO", columnDefinition = "BLOB",nullable=true)
    public byte[] getExceptioninfo() {
        return this.exceptioninfo;
    }
    
    public void setExceptioninfo(byte[] exceptioninfo) {
        this.exceptioninfo = exceptioninfo;
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
    
    @Column(name="RUNAPPNAME", length=100)

    public String getRunappname() {
        return this.runappname;
    }
    
    public void setRunappname(String runappname) {
        this.runappname = runappname;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((enddate == null) ? 0 : enddate.hashCode());
		result = prime * result
				+ ((exceptioninfo == null) ? 0 : exceptioninfo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
		result = prime * result
				+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result
				+ ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
		result = prime * result
				+ ((runappname == null) ? 0 : runappname.hashCode());
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
		AbstractJobLog other = (AbstractJobLog) obj;
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
		if (exceptioninfo == null) {
			if (other.exceptioninfo != null)
				return false;
		} else if (!exceptioninfo.equals(other.exceptioninfo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jobConfig == null) {
			if (other.jobConfig != null)
				return false;
		} else if (!jobConfig.equals(other.jobConfig))
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
		if (runappname == null) {
			if (other.runappname != null)
				return false;
		} else if (!runappname.equals(other.runappname))
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