package com.gmcc.dao.hibernate;

import java.util.List;

import com.gmcc.cons.element.Constants;
import com.gmcc.dao.JobDAO;
import com.gmcc.dao.LogDAO;
import com.gmcc.dao.hibernate.base.BaseDao;
import com.gmcc.dto.LogDTO;
import com.gmcc.model.BusinessLog;
import com.gmcc.model.JobConfig;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.DateUtil;
import com.ibm.util.StringUtil;



public class JobDAOHibernate extends BaseDao<JobConfig, Long> implements JobDAO {

	public List<JobConfig> getJobConfigList(int pageNo, int pageSize,
			JobConfig job) throws Exception{
		// TODO Auto-generated method stub
//		String hql="select d from  GoodsSupplyDtl d order by d.createdTime desc";
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
//		Table table = AnnotationUtils.findAnnotation(Transport.class, Table.class);
		if(pageNo>1){
			pageNo = (pageNo-1)*pageSize+1;
			pageSize=pageNo+pageSize-1;
		}
		Object[] args = new Object[2];
		args[1]=pageNo;
		args[0]=pageSize;
		StringBuffer sql=new StringBuffer();
		sql.append("select * from(select rownum rn,t.* from(select t.jobid id,t.jobname,t.jobschedule," +
				" t.currentstatus,t.startdate,t.enddate,t.jobdesc,t.runmachineip "+
				" from t_job_config t " +
				" where 1=1 ");
		if(job!=null && StringUtil.isValidString(job.getJobname().trim())){
			String[] jobNames = job.getJobname().trim().split("\r\n");
			if(jobNames.length>0){
				if(jobNames.length==1 && StringUtil.isValidString(jobNames[0].trim())){
					sql.append(" and upper(jobname) like '%"+jobNames[0].trim().toUpperCase()+"%'");
				}else{
					if(StringUtil.isValidString(jobNames[0].trim())){
						sql.append(" and (upper(jobname) like '%"+jobNames[0].trim().toUpperCase()+"%'");
					}
					for(int i=1;i<jobNames.length-1;i++){
						if(StringUtil.isValidString(jobNames[i].trim())){
							sql.append(" or upper(jobname) like '%"+jobNames[i].trim().toUpperCase()+"%' ");
						}
					}
					if(StringUtil.isValidString(jobNames[jobNames.length-1].trim())){
						sql.append(" or upper(jobname) like '%"+jobNames[jobNames.length-1].trim().toUpperCase()+"%') ");
					}else{
						sql.append(" ) ");
					}
				}
			}
		}
		if(job!=null && StringUtil.isValidString(job.getJobdesc().trim())){
			String[] jobDesc = job.getJobdesc().trim().split("\r\n");
			if(jobDesc.length>0){
				if(jobDesc.length==1 && StringUtil.isValidString(jobDesc[0].trim())){
					sql.append(" and upper(t.jobdesc) like '%"+jobDesc[0].trim().toUpperCase()+"%'");
				}else{
					if(StringUtil.isValidString(jobDesc[0].trim())){
						sql.append(" and (upper(t.jobdesc) like '%"+jobDesc[0].trim().toUpperCase()+"%'");
					}
					for(int i=1;i<jobDesc.length-1;i++){
						if(StringUtil.isValidString(jobDesc[i].trim())){
							sql.append(" or upper(t.jobdesc) like '%"+jobDesc[i].trim().toUpperCase()+"%' ");
						}
					}
					if(StringUtil.isValidString(jobDesc[jobDesc.length-1].trim())){
						sql.append(" or upper(t.jobdesc) like '%"+jobDesc[jobDesc.length-1].trim().toUpperCase()+"%') ");
					}else{
						sql.append(" ) ");
					}
				}
			}
//			sql.append(" and t.jobdesc like '%"+job.getJobdesc().trim()+"%'");
		}
		if(job!=null && StringUtil.isValidString(job.getCurrentstatus())){
			sql.append(" and currentstatus = '"+job.getCurrentstatus()+"'");
		}
		if(job!=null && StringUtil.isValidString(job.getRunmachineip().trim())){
			sql.append(" and runmachineip like '%"+job.getRunmachineip().trim()+"%'");
		}
		sql.append("  order by t.jobid desc) t where rownum<=?) where rn>=?");
		System.out.println(sql.toString());
		List list=this.getJdbcTemplate().queryForList(sql.toString(), args);
		return list;
	}
	public Long getJobConfigCount(JobConfig job) throws Exception{
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("select count(1)  "+
				" from t_job_config t " +
				" where 1=1 ");
		if(job!=null && StringUtil.isValidString(job.getJobname())){
			String[] jobNames = job.getJobname().trim().split("\r\n");
			if(jobNames.length>0){
				if(jobNames.length==1 && StringUtil.isValidString(jobNames[0].trim())){
					sql.append(" and upper(jobname) like '%"+jobNames[0].trim().toUpperCase()+"%'");
				}else{
					if(StringUtil.isValidString(jobNames[0].trim())){
						sql.append(" and (upper(jobname) like '%"+jobNames[0].trim().toUpperCase()+"%'");
					}
					for(int i=1;i<jobNames.length-1;i++){
						if(jobNames.length==1 && StringUtil.isValidString(jobNames[i].trim())){
							sql.append(" or upper(jobname) like '%"+jobNames[i].trim().toUpperCase()+"%' ");
						}
					}
					if(StringUtil.isValidString(jobNames[jobNames.length-1].trim())){
						sql.append(" or upper(jobname) like '%"+jobNames[jobNames.length-1].trim().toUpperCase()+"%') ");
					}else{
						sql.append(" ) ");
					}
					
				}
			}
		}
		if(job!=null && StringUtil.isValidString(job.getJobdesc().trim())){
			sql.append(" and t.jobdesc like '%"+job.getJobdesc().trim()+"%'");
		}
		if(job!=null && StringUtil.isValidString(job.getCurrentstatus())){
			sql.append(" and currentstatus = '"+job.getCurrentstatus()+"'");
		}
		if(job!=null && StringUtil.isValidString(job.getRunmachineip().trim())){
			sql.append(" and runmachineip like '%"+job.getRunmachineip().trim()+"%'");
		}
		System.out.println(sql.toString());
		return this.getJdbcTemplate().queryForLong(sql.toString());
	}

}
