package com.gmcc.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.gmcc.dto.LogDTO;
import com.gmcc.model.BusinessLog;
import com.ibm.dao.hibernate.base.IBaseDao;
/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface LogDAO extends IBaseDao<LogDTO, Long> {

	public List<LogDTO> getLogList(int pageNo,int pageSize,BusinessLog businessLog)throws Exception;
	
	public Long getLogCount(BusinessLog businessLog)throws Exception;
}
