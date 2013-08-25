package com.gmcc.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.ibm.model.BaseObject;


@MappedSuperclass

public abstract class AbstractVersionnotice extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String note;
	private String description;
	private Date visibletime;
	private Date deadline;
	private String visibletime1;
	private String deadline1;
	private String visibletime2;
	private String deadline2;
	private String notetype;
	private String priority;
	private String enabled;
	private Date createdTime;
	private Date lastUpdatedTime;
	private String createdBy;
	private String lastUpdatedBy;
	private String toflag;

	public AbstractVersionnotice() {
		super();
	}

	@Id
	@Column(name = "NOTICESYSID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NOTICETITLE")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Column(name = "NOTICECONTENT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DISPLAYBEGINTIME")
	public Date getVisibletime() {
		return visibletime;
	}

	public void setVisibletime(Date visibletime) {
		this.visibletime = visibletime;
	}

	@Column(name = "DISPLAYENDTIME")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@Transient
	public String getVisibletime1() {
		return visibletime1;
	}

	public void setVisibletime1(String visibletime1) {
		this.visibletime1 = visibletime1;
	}

	@Transient
	public String getDeadline1() {
		return deadline1;
	}

	public void setDeadline1(String deadline1) {
		this.deadline1 = deadline1;
	}

	@Transient
	public String getVisibletime2() {
		return visibletime2;
	}

	public void setVisibletime2(String visibletime2) {
		this.visibletime2 = visibletime2;
	}
	
	@Transient
	public String getDeadline2() {
		return deadline2;
	}

	public void setDeadline2(String deadline2) {
		this.deadline2 = deadline2;
	}

	@Column(name = "NOTICETYPE")
	public String getNotetype() {
		return notetype;
	}

	public void setNotetype(String notetype) {
		this.notetype = notetype;
	}

	@Column(name = "PRIORITY")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "LAST_UPDATED_BY")
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String userName) {
		this.lastUpdatedBy = userName;
	}

	@Column(name = "LAST_UPDATED_TIME")
	public Date getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date date) {
		this.lastUpdatedTime = date;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String userName) {
		this.createdBy = userName;
	}
	
	public void setCreatedTime(Date date) {
		this.createdTime = date;
	}
	
	@Transient
	public String getToflag() {
		return toflag;
	}

	public void setToflag(String toflag) {
		this.toflag = toflag;
	}

	@Column(name = "CREATED_TIME")
	public Date getCreatedTime() {
		return this.createdTime;
		
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	@Column(name = "ENABLED")
	public String getEnabled() {
		return this.enabled;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

}