package com.gmcc.service.impl.demo;

import java.util.List;
import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.gmcc.model.DemoBill;
import com.gmcc.model.DemoBillDetail;
import com.gmcc.service.BillSnManager;
import com.gmcc.service.demo.DemoService;
import com.ibm.dao.hibernate.base.IBaseDao;
import com.ibm.service.impl.OperateManagerImp;
import com.ibm.util.StringUtil;
import com.gmcc.cons.billsn.Constants;

public class DemoServiceImpl extends OperateManagerImp<DemoBill, Long> implements DemoService {		
	private BillSnManager billSnManager; 
	public void setBillSnManager(BillSnManager billSnManager) {
		this.billSnManager = billSnManager;
	}
	
	private IBaseDao<DemoBillDetail,Long> detailManager;
	public void setDetailManager(IBaseDao<DemoBillDetail, Long> detailManager) {
		this.detailManager = detailManager;
	}

	@Transactional
	public DemoBill saveDemoBill(DemoBill demoBill) throws Exception {
		if(!StringUtil.isValidString(demoBill.getBillNum())){
			demoBill.setBillNum(billSnManager.getBillSn(Constants.BILL_SN_DEMO));
		}
		super.setEntityClass(DemoBill.class);
		demoBill = super.merge(demoBill);
		return demoBill;
	}
	
	@Transactional
	public void delDemoBill(Long id) throws Exception {
		super.delete(id);
	}
	
	public List<DemoBillDetail> loadDemoBillDetail(Long id) throws Exception {
		List<DemoBillDetail> dbdList = new ArrayList<DemoBillDetail>();		
		for(DemoBillDetail dbd : super.findById(id).getDemoBillDetails()){
			dbdList.add(dbd);
		}
		return dbdList;
	}
	
	@Transactional
	public DemoBill saveDemoBill(DemoBill demoBill, List<DemoBillDetail> detailList) throws Exception {
		if(!StringUtil.isValidString(demoBill.getBillNum())){
			demoBill.setBillNum(billSnManager.getBillSn(Constants.BILL_SN_DEMO));
		}
		super.setEntityClass(DemoBill.class);
		demoBill = super.merge(demoBill);
		detailManager.setEntityClass(DemoBillDetail.class);
		for(DemoBillDetail dbd : detailList){			
			dbd.setDemoBill(demoBill);
//			super.saveOrUpdateObj(dbd);			
			detailManager.merge(dbd);
		}
		return demoBill;		
	}

}
