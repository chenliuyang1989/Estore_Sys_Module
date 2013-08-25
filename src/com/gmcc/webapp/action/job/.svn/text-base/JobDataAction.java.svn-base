package com.gmcc.webapp.action.job;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.gmcc.cons.element.Constants;
import com.gmcc.exception.FunctionalException;
import com.gmcc.model.JobConfig;
import com.gmcc.service.job.JobService;
import com.gmcc.sso.TokenUrlRewriter;
import com.gmcc.sso.TokenUtils;
import com.gmcc.util.AppConst;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.BaseBusinessLogAction;
import com.ibm.util.annotation.LogBusinessKey;

public class JobDataAction extends BaseBusinessLogAction<JobConfig,Long>{
	private static final long serialVersionUID = 1L;
	
	private JobConfig job;
	private JobService jobService;
	private Long _chk[];
	
	public JobConfig getJob() {
		return job;
	}
	public void setJob(JobConfig job) {
		this.job = job;
	}
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
		this.jobService.setEntityClass(JobConfig.class);
	}
	public void set_chk(Long[] _chk) {
		this._chk = _chk;
	}
	
	public String load() throws Exception {
		if(this.job==null || this.job.getId()==null){
			job=new JobConfig();
			this.job.setEnabled(AppConst.ENABLED);
		}else{
			this.job=this.jobService.findById(this.job.getId());
		}
		return SUCCESS;
	}
	
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_save_jobconfig", methodKey="save")
	public String save()throws Exception {
		try{
			this.job = this.jobService.updJob(job);
		}catch (Exception e) {
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return INPUT;
		}
		this.addActionMessage(getText("message.save.success"));
		this.setOperatorPK(this.job.getId());
		this.setOperatorContent(this.getText("job.save.log")+
				this.getText("jobLog.jobConfig.jobname")+
				this.job.getJobname());
		return SUCCESS;
	}
	
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_delete_jobconfig", methodKey="delete")
	public String delete() throws Exception {
		try{
			if(job.getId()!=null){
				this.jobService.delete(job.getId());
				this.setOperatorPK(job.getId());
				this.setOperatorContent(this.getText("job.delete.log")+
						this.getText("job.id")+
						this.job.getId());
			}
		}catch (Exception e) {
			this.addActionError(getText("error.delete"));
			log.error(e.getMessage(), e);
			return INPUT;
		}
		this.addActionMessage(getText("message.delete.success"));
		return super.backToList();
	}
	
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_delete_jobconfig", methodKey="delete")
	public String deleteBatch()throws Exception{
		try{
			if(_chk!=null){
				jobService.delBatch(_chk);
				for(int i=0;i<_chk.length;i++){
					this.setOperatorPK(Long.valueOf(_chk[i]));
					this.setOperatorContent(this.getText("job.delete.log")+
							this.getText("job.id")+
							this.job.getId());
				}
			}
		}catch(Exception ex){
			this.addActionError(getText("error.delete"));
			log.error(ex.getMessage(), ex);
			return super.backToList();
		}
		this.saveMessage(getText("message.delete.success"));
		return super.backToList();
	}
	
	
	//检查JOB名称是否已存在
	@SkipValidation
	public String getJobByName()throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		try{
			String jobName=req.getParameter("jobName");
			JobConfig jobTmp=null;
			this.jobService.setEntityClass(JobConfig.class);
			List<JobConfig> list=jobService.findBy("jobname", jobName);
			if(list!=null && list.size()>0){
				JobConfig jobSess=list.get(0);
				jobTmp=new JobConfig();
				jobTmp.setId(jobSess.getId());
				jobTmp.setClassname(jobSess.getClassname());
				jobTmp.setJobname(jobSess.getJobname());
				jobTmp.setJobschedule(jobSess.getJobschedule());
				jobTmp.setJobdesc(jobSess.getJobdesc());
				jobTmp.setCurrentstatus(jobSess.getCurrentstatus());
				jobTmp.setRunmachineip(jobSess.getRunmachineip());
				jobTmp.setRunport(jobSess.getRunport());
				jobTmp.setCommand(jobSess.getCommand());
			}
			JSONObject array=JSONObject.fromObject(jobTmp);
			System.out.println("["+array.toString()+"]");
			res.getWriter().write("["+array.toString()+"]");    
			res.getWriter().flush();
			res.getWriter().close();
		}catch (Exception e) {
			res.getWriter().write("-1");   
			res.getWriter().flush();
			res.getWriter().close();
			e.printStackTrace();
		}
		return null;
	}
	
}
