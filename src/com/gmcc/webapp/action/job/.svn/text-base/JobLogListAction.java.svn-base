package com.gmcc.webapp.action.job;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gmcc.model.JobLog;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.util.StringUtil;

public class JobLogListAction extends DisplayTagQueryAction<JobLog,Long>{

	private static final long serialVersionUID = 1L;
	private JobLog jobLog;

	public JobLog getJobLog() {
		return jobLog;
	}

	public void setJobLog(JobLog jobLog) {
		this.jobLog = jobLog;
	}

	@Override
	protected List<Criterion> filters() throws Exception {
		List<Criterion> list = super.filters();
//		list.add(Restrictions.eq("enabled", AppConst.ENABLED));
		if(jobLog!=null && StringUtil.isValidString(jobLog.getJobConfig().getJobname())){
			list.add(Restrictions.like("job.jobname", "%"+jobLog.getJobConfig().getJobname()+"%"));
		}
		return list;
	}
	
	@Override
	protected Map<String, String> alias() throws Exception {
		Map<String, String> alias = super.alias();
		if(jobLog!=null && StringUtil.isValidString(jobLog.getJobConfig().getJobname())){
			alias.put("jobConfig", "job");
		}
		return alias;
	}
	
	@Override
	protected List<Order> orders() {
		List<Order> list = super.orders();
		list.add(Order.desc("startdate"));
		return list;
	}
	
}
