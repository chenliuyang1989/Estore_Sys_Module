package com.gmcc.service;

import com.gmcc.model.Versionnotice;
import com.ibm.service.IOperateManager;

public interface VersionnoticeService {

	public abstract Versionnotice getVersionnotice(Versionnotice versionnotice) throws Exception;

	public abstract void saveVersionnotice(Versionnotice versionnotice) throws Exception;
	
	public abstract void updVersionnotice(Versionnotice versionnotice) throws Exception;

	public abstract IOperateManager<Versionnotice, Long> getOperateManager();

	public abstract void setOperateManager(IOperateManager<Versionnotice, Long> operateManager);

}