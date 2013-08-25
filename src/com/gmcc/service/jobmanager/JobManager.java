package com.gmcc.service.jobmanager;

import java.util.List;
import java.util.Locale;

import com.gmcc.model.JobConfig;
import com.gmcc.service.job.JobService;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class JobManager {
	private JobService jobService;
	
	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	
	public synchronized void initJobStatus(){
		jobService.setEntityClass(JobConfig.class);
		List<JobConfig> jobList=jobService.findAll();
		for(JobConfig job : jobList){
			try {
				if(job.getRunport()==null || !job.getRunport().equals("1")){
					job.setCurrentstatus("4");
					job.setRunport("1");
					jobService.merge(job);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updJobAndSaveJobLog(String jobName,String status,String desc,String flag)throws Exception{
		Locale locale = Locale.getDefault();
		String runDesc=LocalizedTextUtil.findDefaultText("job.log.run", locale);
		if(flag!=null && "s".equals(flag)){
			runDesc=LocalizedTextUtil.findDefaultText("job.log.endSucces", locale);
		}else if(flag!=null && "f".equals(flag)){
			runDesc=LocalizedTextUtil.findDefaultText("job.log.endFail", locale);
		}
		jobService.updJobStatus(jobName,status,runDesc+
				LocalizedTextUtil.findDefaultText("job.jobname", locale)+":"+jobName+","+
				LocalizedTextUtil.findDefaultText("job.desc", locale)+":"+LocalizedTextUtil.findDefaultText(desc, locale));
	}
	
	public JobConfig getJobByName(String jobName)throws Exception{
		jobService.setEntityClass(JobConfig.class);
		List<JobConfig> list=jobService.findBy("jobname", jobName);
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}
	
}
