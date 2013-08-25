package com.gmcc.service.impl.job;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gmcc.dao.JobDAO;
import com.gmcc.model.JobConfig;
import com.gmcc.model.JobLog;
import com.gmcc.service.job.JobService;
import com.gmcc.service.jobmanager.JobManager;
import com.ibm.service.IOperateManager;
import com.ibm.service.impl.OperateManagerImp;

public class JobServiceImpl extends OperateManagerImp<JobConfig, Long> implements JobService{

	private JobManager jobManager;
	protected IOperateManager<JobLog, Long> jobLogOperate;
	private JobDAO jobDAO;
	public JobManager getJobManager() {
		return jobManager;
	}

	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}
	
	public void setJobLogOperate(IOperateManager<JobLog, Long> jobLogOperate) {
		this.jobLogOperate = jobLogOperate;
		this.jobLogOperate.setEntityClass(JobLog.class);
		this.jobLogOperate.setPKClass(Long.class);
	}
	
	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	@Transactional
	public void delBatch(Long[] jobIds) throws Exception {
		this.setEntityClass(JobConfig.class);
		this.setPKClass(Long.class);
		for(int i=0;i<jobIds.length;i++){
			this.delete(jobIds[i]);
		}
	}
	
	public JobConfig updJob(JobConfig job)throws Exception{
		job=this.merge(job);
		return job;
	}
	
	public JobConfig savaJobLogAndUpdStatus(JobConfig job, String status, String desc)throws Exception{
		saveJobLog(job, status, desc);
		job.setCurrentstatus(status);
		job.setRunport("0");
		job = this.merge(job);
		return job;
	}
	
	private JobLog saveJobLog(JobConfig job, String status, String desc)throws Exception{
		JobLog jobLog = new JobLog();
		jobLog.setJobConfig(job);
		jobLog.setRunmachineip(job.getRunmachineip());
		jobLog.setRunport(job.getRunport());
		jobLog.setStartdate(new Date());
		jobLog.setExceptioninfo(desc.getBytes());
		jobLog = jobLogOperate.merge(jobLog);
		return jobLog;
	}
	
	public void updJobStatus(String jobName,String status,String desc)throws Exception{
		this.setEntityClass(JobConfig.class);
		JobConfig job=this.findUniqueBy("jobname", jobName);
		saveJobLog(job , status, desc);
		job.setCurrentstatus(status);
		if(status!=null && "1".equals(status))
			job.setStartdate(new Date());
		else if(status!=null && "0".equals(status))
			job.setEnddate(new Date());
		this.merge(job);
	}
	public List<JobConfig> getJobConfigList(int pageNo,int pageSize,JobConfig job)throws Exception{
		return jobDAO.getJobConfigList(pageNo, pageSize, job);
	}
	
	public Long getJobConfigCount(JobConfig job)throws Exception{
		return jobDAO.getJobConfigCount(job);
	}
}
