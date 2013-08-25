package com.gmcc.service.impl;

import java.util.List;

import com.gmcc.model.Params;
import com.gmcc.service.ParamsManager;
import com.ibm.dao.hibernate.base.IBaseDao;

public class ParamsManagerImpl implements ParamsManager {
	
	private IBaseDao<Params, Long> paramsDao;
	public void setParamsDao(IBaseDao<Params, Long> paramsDao) {
		this.paramsDao = paramsDao;
		this.paramsDao.setEntityClass(Params.class);
		this.paramsDao.setPKClass(Long.class);
	}
	
	public Params getParamsByCode(String code) throws Exception {
		List<Params> paramsList = paramsDao.find("from Params where enabled ='1' and code = ? ", code);
		if(!paramsList.isEmpty()){
			return paramsList.get(0);
		}
		return null;
	}
	
}
