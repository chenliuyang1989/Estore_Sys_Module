package com.gmcc.service.impl;


import java.util.List;

import com.gmcc.dao.LogDAO;
import com.gmcc.dto.LogDTO;
import com.gmcc.model.BusinessLog;
import com.gmcc.service.LogService;
import com.ibm.service.impl.OperateManagerImp;

public class LogServiceImpl extends OperateManagerImp<LogDTO,Long> implements LogService{

	private LogDAO logDAO;
	
	public LogDAO getLogDAO() {
		return logDAO;
	}

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	public List<LogDTO> getLogList(int pageNo, int pageSize,
			BusinessLog businessLog) throws Exception {
		// TODO Auto-generated method stub
		return logDAO.getLogList(pageNo, pageSize, businessLog);
	}

	public Long getLogCount(BusinessLog businessLog) throws Exception {
		// TODO Auto-generated method stub
		return logDAO.getLogCount(businessLog);
	}


}
