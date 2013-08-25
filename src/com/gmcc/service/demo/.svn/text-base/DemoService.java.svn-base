package com.gmcc.service.demo;

import java.util.List;

import com.gmcc.model.DemoBill;
import com.gmcc.model.DemoBillDetail;
import com.ibm.service.IOperateManager;

public interface DemoService extends IOperateManager<DemoBill, Long> {
	public abstract DemoBill saveDemoBill(DemoBill demoBill) throws Exception;
	public abstract void delDemoBill(Long id) throws Exception;
	public abstract List<DemoBillDetail> loadDemoBillDetail(Long id) throws Exception;
	public abstract DemoBill saveDemoBill(DemoBill demoBill, List<DemoBillDetail> detailList) throws Exception;
}
