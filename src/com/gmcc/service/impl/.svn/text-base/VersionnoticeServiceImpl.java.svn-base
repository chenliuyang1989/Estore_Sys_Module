package com.gmcc.service.impl;

import com.gmcc.model.Versionnotice;
import com.gmcc.service.VersionnoticeService;
import com.ibm.service.IOperateManager;
import com.ibm.service.impl.OperateManagerImp;


/**
 * @title VersionnoticeServiceImpl.java
 * @desc com.gmcc.estore.wms.service.impl.baseinfo
 * @company www.ibm.com.cn
 * @date Jul 14, 2011 
 * @author Shine.He
 * @version 1.4
 */

public class VersionnoticeServiceImpl extends OperateManagerImp<Versionnotice, Long> implements VersionnoticeService {

	private IOperateManager<Versionnotice, Long> operateManager;
	
	/* (non-Javadoc)
	 * @see com.gmcc.estore.wms.service.impl.baseinfo.VersionnoticeService#getVersionnotice(com.gmcc.estore.wms.model.Versionnotice)
	 */
	public Versionnotice getVersionnotice(Versionnotice versionnotice) throws Exception {
		this.operateManager.setEntityClass(Versionnotice.class);
		return operateManager.findUniqueBy("id", versionnotice.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.gmcc.estore.wms.service.impl.baseinfo.VersionnoticeService#saveVersionnotice(com.gmcc.estore.wms.model.Versionnotice)
	 */
	public void saveVersionnotice(Versionnotice versionnotice) throws Exception {
		operateManager.saveOrUpdate(versionnotice);
		
	}
	
	public void updVersionnotice(Versionnotice versionnotice) throws Exception {
		operateManager.merge(versionnotice);
	}

	/* (non-Javadoc)
	 * @see com.gmcc.estore.wms.service.impl.baseinfo.VersionnoticeService#getOperateManager()
	 */
	public IOperateManager<Versionnotice, Long> getOperateManager() {
		return operateManager;
		
	}

	/* (non-Javadoc)
	 * @see com.gmcc.estore.wms.service.impl.baseinfo.VersionnoticeService#setOperateManager(com.ibm.service.IOperateManager)
	 */
	public void setOperateManager(IOperateManager<Versionnotice, Long> operateManager) {
		this.operateManager = operateManager;
		this.operateManager.setEntityClass(Versionnotice.class);
		this.operateManager.setPKClass(Long.class);
		
	}
	
}
