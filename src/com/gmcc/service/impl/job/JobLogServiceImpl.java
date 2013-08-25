package com.gmcc.service.impl.job;

import com.gmcc.service.job.JobLogService;
import com.gmcc.model.JobLog;
import com.ibm.service.impl.OperateManagerImp;

public class JobLogServiceImpl extends OperateManagerImp<JobLog,Long>
implements JobLogService{

	public void saveJobLog(JobLog jobLog) throws Exception {
		super.merge(jobLog);
	}

}
