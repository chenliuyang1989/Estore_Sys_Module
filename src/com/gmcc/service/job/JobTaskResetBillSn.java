package com.gmcc.service.job;

import java.net.UnknownHostException;
import java.util.List;

import com.gmcc.model.BillSn;
import com.gmcc.util.InetUtils;
import com.ibm.dao.hibernate.base.IBaseDao;

public class JobTaskResetBillSn {

	private IBaseDao<BillSn, Long> billSnDao;
	public void setBillSnDao(IBaseDao<BillSn, Long> billSnDao) {
		this.billSnDao = billSnDao;
		this.billSnDao.setEntityClass(BillSn.class);
		this.billSnDao.setPKClass(Long.class);
	}
	
	private String serverIp;
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	private boolean canRun(){
		try {
			if(this.serverIp.equals("localhost")||InetUtils.getLocalIp().equals(this.serverIp)){
				return true;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")	
	public void resetBillSn() throws Exception {
		if(this.canRun()){
			List<BillSn> snList = (List<BillSn>)this.billSnDao.find("from BillSn");
			for(BillSn sn : snList){
				if(sn.getNeedDate()!=null && "1".equals(sn.getNeedDate()) ){
					sn.setSnValue(new Long(0));
					this.billSnDao.merge(sn);
				}
			}
		}
	}

}
