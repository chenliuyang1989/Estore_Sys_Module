package com.gmcc.webapp.action.job;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gmcc.cons.element.Constants;
import com.gmcc.dto.LogDTO;
import com.gmcc.exception.FunctionalException;
import com.gmcc.model.Element;
import com.gmcc.model.JobConfig;
import com.gmcc.service.job.JobService;
import com.gmcc.sso.TokenUrlRewriter;
import com.gmcc.sso.TokenUtils;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.util.StringUtil;

public class JobListAction extends DisplayTagQueryAction<JobConfig, Long>{
	private static final long serialVersionUID = 1L;
	private JobConfig job;
	private JobService jobService;
	
	public JobConfig getJob() {
		return job;
	}
	public void setJob(JobConfig job) {
		this.job = job;
	}
	
	public JobService getJobService() {
		return jobService;
	}
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	//	public String queryPage() throws Exception {
//		super.setPageSize(20);
//		return super.queryPage();
//	}
	public String queryPage() throws Exception {
		this.setCollMap(collMap);
		super.displaytagCheck();
		super.saveQueryCondtion(this);
		if (super.getPageSize() == 0) {
			super.setPageSize(this.configPageSize());
		}
		// Display标签支持,获取需要跳转的页码
		String paraName = new ParamEncoder("resultList").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		Object pobject = (String) getRequest().getParameter((paraName));// 页数
		String exportName = getRequest().getParameter(TableTagParameters.PARAMETER_EXPORTING);

		// 把页码转为整形
		int pageNo;// 需要跳转到第pageNo页
		
		if (pobject == null) {
			pageNo = 1;
		} else {
			pageNo = Integer.valueOf(pobject.toString());
		}
		if (exportName != null) {
			pageNo = 1;
			super.setPageSize(super.queryManager.getMaxPageSize());
		}
		List<JobConfig> list=jobService.getJobConfigList(pageNo, super.getPageSize(), job);
		Long count=jobService.getJobConfigCount(job);
		super.setTotalRows(count.intValue());
		super.setResultList(list);
		initCookie();
		super.currentUrl="query";
		return SUCCESS;
	}
//	@Override
//	protected List<Criterion> filters() throws Exception {
//		List<Criterion> list = super.filters();
//		if(job!=null && StringUtil.isValidString(job.getJobname())){
//			String[] jobNames = job.getJobname().trim().split("\r\n");
//			if(jobNames!=null){
//				for(int i=0;i<jobNames.length;){
//					if(StringUtils.isNotBlank(jobNames[i].trim())){
//						list.add(Restrictions.or(Restrictions.like("jobname", jobNames[i].trim()),
//								Restrictions.or(Restrictions.like("jobname", jobNames[i+1].trim()),
//										Restrictions.like("jobname", "&"))));
//					}
//				}
//				
//			}
//		}
//		if(job!=null && StringUtil.isValidString(job.getJobname())){
//			list.add(Restrictions.like("jobname", "%"+job.getJobname()+"%"));
//		}
//		if(job!=null && StringUtil.isValidString(job.getCurrentstatus())){
//			list.add(Restrictions.eq("currentstatus", job.getCurrentstatus()));
//		}
//		return list;
//	}
//	
//	@Override
//	protected List<Order> orders() {
//		List<Order> list = super.orders();
//		list.add(Order.desc("id"));
//		return list;
//	}
	
	
	//批启JOB
	@SkipValidation
	public String batchRunJob() throws Exception{
		String sessionId = this.getSession().getId();
		try{
			String[] _chk=this.getRequest().getParameterValues("_chk");
			for(int i=0; i<_chk.length; i++){
				JobConfig jobTmp = this.jobService.findById(Long.valueOf(_chk[i]));
				String paraVal = "1_"+jobTmp.getJobname()+"_"+jobTmp.getJobschedule();
				try {
		    		HttpURLConnection conn = null;
		        	URL url = new URL(TokenUrlRewriter.rewrite(jobTmp.getRunmachineip(), sessionId,
		        			TokenUtils.TOKEN_PARAMETER_KEY+"_"+SpringSecurityUtils.getCurrentUserName()));
		        	conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setDoInput(true);
					conn.setDoOutput(true);					
					conn.addRequestProperty("para", paraVal);
					conn.connect();
					int code = conn.getResponseCode();
					if (code < 200 || code > 300)
						throw new FunctionalException(jobTmp.getJobname()+":"+conn.getResponseMessage());
					conn.disconnect();
				}catch(Exception e) {
					throw e;
				}
				jobService.savaJobLogAndUpdStatus(jobTmp,"3",getText("job.log.open")+getText("job.jobname")+":"+
						jobTmp.getJobname()+","+getText("job.desc")+":"+jobTmp.getJobdesc());
			}
		}catch(Exception e) {
			this.saveMessage(e.getMessage());
			return this.queryPage();
		}
		this.addActionMessage(getText("message.save.success"));
		return this.queryPage();
	}
	
	//批关JOB
	@SkipValidation
	public String batchCloseJob() throws Exception{
		String sessionId = this.getSession().getId();
		try{
			String[] _chk=this.getRequest().getParameterValues("_chk");
			for(int i=0; i<_chk.length; i++){
				JobConfig jobTmp = this.jobService.findById(Long.valueOf(_chk[i]));
				String paraVal = "4_"+jobTmp.getJobname()+"_"+jobTmp.getJobschedule();
				try {
		    		HttpURLConnection conn = null;
		        	URL url = new URL(TokenUrlRewriter.rewrite(jobTmp.getRunmachineip(), sessionId,
		        			TokenUtils.TOKEN_PARAMETER_KEY+"_"+SpringSecurityUtils.getCurrentUserName()));
		        	conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setDoInput(true);
					conn.setDoOutput(true);					
					conn.addRequestProperty("para", paraVal);
					conn.connect();
					int code = conn.getResponseCode();
					if (code < 200 || code > 300)
						throw new FunctionalException(jobTmp.getJobname()+":"+conn.getResponseMessage());
					conn.disconnect();
				}catch(Exception e) {
					throw e;
				}
				jobService.savaJobLogAndUpdStatus(jobTmp,"4",getText("job.log.open")+getText("job.jobname")+":"+
						jobTmp.getJobname()+","+getText("job.desc")+":"+jobTmp.getJobdesc());
			}
		}catch(Exception e) {
			this.saveMessage(e.getMessage());
			return this.queryPage();
		}
		this.addActionMessage(getText("message.save.success"));
		return this.queryPage();
	}
	
	//开启JOB
	@SkipValidation
	public String runJob()throws Exception{
		JobConfig jobTmp=null;
		String id=this.getRequest().getParameter("id");
		if(id!=null && !"".equals(id)){
			jobTmp = this.jobService.findById(Long.valueOf(id));
		}
		if(jobTmp!=null){
			try {
				
					String paraVal = "1_"+jobTmp.getJobname()+"_"+jobTmp.getJobschedule();
					String sessionId = this.getSession().getId();
		    		HttpURLConnection conn = null;
		        	URL url = new URL(TokenUrlRewriter.rewrite(jobTmp.getRunmachineip(), sessionId,
		        			TokenUtils.TOKEN_PARAMETER_KEY+"_"+SpringSecurityUtils.getCurrentUserName()));
		        	conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.addRequestProperty("para", paraVal);
					conn.connect();
					int code = conn.getResponseCode();
					if (code < 200 || code > 300)
						return null;
					conn.disconnect();
				
			
			}catch(Exception e) {
				throw e;
			}
			 jobService.savaJobLogAndUpdStatus(jobTmp,"3",getText("job.log.open")+getText("job.jobname")+":"+
					jobTmp.getJobname()+","+getText("job.desc")+":"+jobTmp.getJobdesc());
			this.addActionMessage(getText("message.save.success"));
		}
		return this.queryPage();
	}
	
//	暂停JOB
	@SkipValidation
	public String pauseJob()throws Exception{
		
		JobConfig jobTmp=null;
		String id=this.getRequest().getParameter("id");
		if(id!=null && !"".equals(id)){
			jobTmp = this.jobService.findById(Long.valueOf(id));
		}
		if(jobTmp!=null){
			String paraVal = "2_"+jobTmp.getJobname()+"_"+jobTmp.getJobschedule();
			try {
				String sessionId = this.getSession().getId();
	    		HttpURLConnection conn = null;
	        	URL url = new URL(TokenUrlRewriter.rewrite(jobTmp.getRunmachineip(), sessionId,
	        			TokenUtils.TOKEN_PARAMETER_KEY+"_"+SpringSecurityUtils.getCurrentUserName()));
	        	conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.addRequestProperty("para", paraVal);
				conn.connect();
				int code = conn.getResponseCode();
				if (code < 200 || code > 300)
					return null;
				conn.disconnect();
			}catch(Exception e) {
				throw e;
			}
			 jobService.savaJobLogAndUpdStatus(jobTmp,"2",getText("job.log.pause")+getText("job.jobname")+":"+
					jobTmp.getJobname()+","+getText("job.desc")+":"+jobTmp.getJobdesc());
			this.addActionMessage(getText("message.save.success"));
		}
		return this.queryPage();
	}
	
	//继续JOB
	@SkipValidation
	public String resumeJob()throws Exception{
		JobConfig jobTmp=null;
		String id=this.getRequest().getParameter("id");
		if(id!=null && !"".equals(id)){
			jobTmp = this.jobService.findById(Long.valueOf(id));
		}
		if(jobTmp!=null){
			String paraVal = "3_"+jobTmp.getJobname()+"_"+jobTmp.getJobschedule();
			try {
				String sessionId = this.getSession().getId();
	    		HttpURLConnection conn = null;
	        	URL url = new URL(TokenUrlRewriter.rewrite(jobTmp.getRunmachineip(), sessionId,
	        			TokenUtils.TOKEN_PARAMETER_KEY+"_"+SpringSecurityUtils.getCurrentUserName()));
	        	conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.addRequestProperty("para", paraVal);
				conn.connect();
				int code = conn.getResponseCode();
				if (code < 200 || code > 300)
					return null;
				conn.disconnect();
			}catch(Exception e) {
				throw e;
			}
			 jobService.savaJobLogAndUpdStatus(jobTmp,"1",getText("job.log.resume")+getText("job.jobname")+":"+
					jobTmp.getJobname()+","+getText("job.desc")+":"+jobTmp.getJobdesc());
			this.addActionMessage(getText("message.save.success"));
		}
		return this.queryPage();
	}
	
	//关闭JOB
	@SkipValidation
	public String closeJob()throws Exception{
		JobConfig jobTmp=null;
		String id=this.getRequest().getParameter("id");
		if(id!=null && !"".equals(id)){
			jobTmp = this.jobService.findById(Long.valueOf(id));
		}
		if(jobTmp!=null){
			String paraVal = "4_"+jobTmp.getJobname()+"_"+jobTmp.getJobschedule();
			try {
				String sessionId = this.getSession().getId();
	    		HttpURLConnection conn = null;
	        	URL url = new URL(TokenUrlRewriter.rewrite(jobTmp.getRunmachineip(), sessionId,
	        			TokenUtils.TOKEN_PARAMETER_KEY+"_"+SpringSecurityUtils.getCurrentUserName()));
	        	conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.addRequestProperty("para", paraVal);
				conn.connect();
				int code = conn.getResponseCode();
				if (code < 200 || code > 300)
					return null;
				conn.disconnect();
			}catch(Exception e) {
				throw e;
			}
			 jobService.savaJobLogAndUpdStatus(jobTmp,"4",getText("job.log.close")+getText("job.jobname")+":"+
					jobTmp.getJobname()+","+getText("job.desc")+":"+jobTmp.getJobdesc());
			this.addActionMessage(getText("message.save.success"));
		}
		return this.queryPage();
	}
	
}
