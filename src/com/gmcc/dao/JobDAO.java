package com.gmcc.dao;

import java.util.List;

import com.gmcc.model.JobConfig;
import com.ibm.dao.hibernate.base.IBaseDao;
/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface JobDAO extends IBaseDao<JobConfig, Long> {

	public List<JobConfig> getJobConfigList(int pageNo,int pageSize,JobConfig job)throws Exception;
	
	public Long getJobConfigCount(JobConfig job)throws Exception;
}
