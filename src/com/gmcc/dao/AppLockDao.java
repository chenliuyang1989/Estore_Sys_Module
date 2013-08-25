package com.gmcc.dao;

import com.gmcc.model.AppLock;
import com.ibm.service.GenericManager;


public interface AppLockDao extends GenericManager< AppLock, Long>  {
	public AppLock getAppLockByCode(String code);
}
