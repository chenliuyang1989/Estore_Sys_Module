package com.gmcc.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.gmcc.dto.LogDTO;
import com.gmcc.model.BusinessLog;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.ibm.service.IOperateManager;

public interface LogService extends IOperateManager<LogDTO,Long>{

	public List<LogDTO> getLogList(int pageNo,int pageSize,BusinessLog businessLog) throws Exception;
	
	public Long getLogCount(BusinessLog businessLog) throws Exception;
}
