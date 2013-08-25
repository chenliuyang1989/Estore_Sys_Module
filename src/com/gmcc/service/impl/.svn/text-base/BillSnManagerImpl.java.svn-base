package com.gmcc.service.impl;


import com.gmcc.service.BillSnManager;
import com.gmcc.model.BillSn;
import com.ibm.dao.hibernate.base.IBaseDao;
import com.ibm.service.impl.OperateManagerImp;
import com.gmcc.cons.billsn.Constants;
import com.gmcc.dao.BillSnDAO;

public class BillSnManagerImpl extends OperateManagerImp<BillSn,Long> implements BillSnManager {
	
	private IBaseDao<BillSn, Long> billSnDao;
	public void setBillSnDao(IBaseDao<BillSn, Long> billSnDao) {
		this.billSnDao = billSnDao;
		this.billSnDao.setEntityClass(BillSn.class);
		this.billSnDao.setPKClass(Long.class);
	}
	private BillSnDAO snDAO;
	
	public BillSnDAO getSnDAO() {
		return snDAO;
	}

	public void setSnDAO(BillSnDAO snDAO) {
		this.snDAO = snDAO;
	}

	public synchronized String getBillSn(String snType) throws Exception {
		return snDAO.getBillSn(snType);
	}
	
//	public synchronized String getBillSn(String snType) throws Exception {
//		String billSnValue = "";
//		BillSn billSn = billSnDao.findUniqueBy(Constants.BILL_SN, snType);
//		billSn.setSnValue(billSn.getSnValue()+1);
//		String temp = billSn.getSnValue()+"";
//		int snLength=Integer.parseInt(billSn.getSnLength());
//		if(billSn.getSnLength()==null || "".equals(billSn.getSnLength())){
//			snLength=5;
//		}
//		for(int i=0;i<snLength;i++){
//			if(temp.length()<snLength){
//				temp="0"+temp;
//			}
//		}
//		if(billSn.getNeedDate().equals(AppConst.ENABLED)){
//			billSnValue = snType+"-"+com.ibm.util.DateUtil.getNow("yyyyMMdd")+"-"+temp;
//		}else{
//			billSnValue = snType+temp;
//		}
//		//update
//		billSnDao.update(billSn);
//		return billSnValue;
//	}
	
	public synchronized String getWarehouseSn(String snType, String cityShortName) throws Exception {
		String billSnValue = "";
		BillSn billSn = billSnDao.findUniqueBy(Constants.BILL_SN, snType);
		billSn.setSnValue(billSn.getSnValue()+1);
		String temp = "0000"+billSn.getSnValue();
		temp = temp.substring(temp.length()-4);
		billSnValue = cityShortName+temp;
		//update
		billSnDao.update(billSn);
		return billSnValue;
	}
}
