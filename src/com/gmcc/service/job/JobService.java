package com.gmcc.service.job;

import java.util.List;

import com.gmcc.model.JobConfig;
import com.ibm.service.IOperateManager;

public interface JobService extends IOperateManager<JobConfig,Long>{
	public void delBatch(Long[] jobIds)throws Exception;	
	public void updJobStatus(String jobName,String status,String desc)throws Exception;
	public JobConfig savaJobLogAndUpdStatus(JobConfig job,String status,String desc)throws Exception;
	public JobConfig updJob(JobConfig job)throws Exception;	
	
	public List<JobConfig> getJobConfigList(int pageNo,int pageSize,JobConfig job)throws Exception;
	
	public Long getJobConfigCount(JobConfig job)throws Exception;
}
