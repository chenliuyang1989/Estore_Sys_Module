package com.gmcc.service.impl.job;

import java.net.UnknownHostException;

import com.gmcc.dao.JobDisEnableUserDAO;
import com.gmcc.model.User;
import com.gmcc.service.job.JobDisEnableUserService;
import com.gmcc.util.InetUtils;
import com.ibm.service.impl.OperateManagerImp;

public class JobDisEnableUserServiceImpl extends OperateManagerImp<User,Long> implements JobDisEnableUserService{

	private JobDisEnableUserDAO jobDisEnableUserDAO;
	private String serverIp;
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	public JobDisEnableUserDAO getJobDisEnableUserDAO() {
		return jobDisEnableUserDAO;
	}

	public void setJobDisEnableUserDAO(JobDisEnableUserDAO jobDisEnableUserDAO) {
		this.jobDisEnableUserDAO = jobDisEnableUserDAO;
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

	public void updUserDisEnable() throws Exception {
		if(this.canRun()){
			jobDisEnableUserDAO.updUserDisEnable();
		}
	}
	
	public void updUserPwdErrorNum()throws Exception{
		if(this.canRun()){
			jobDisEnableUserDAO.updUserPwdErrorNum();
		}
	}

}
