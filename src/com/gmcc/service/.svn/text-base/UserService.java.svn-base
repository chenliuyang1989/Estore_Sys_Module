package com.gmcc.service;

import java.util.List;

import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.ibm.service.IOperateManager;

public interface UserService extends IOperateManager<User,Long>{

	public List<User> getUserList(User user,String roleId,String userName,int pageNo,int pageSize)throws Exception;
	
	public Long getUserCount(User user,String roleId,String userName)throws Exception;
	
	public List<Role> getAllRoleList()throws Exception;
	
	/**
	 * ���������û��Ƿ��������֤()
	 * @param id
	 * @param flag 1-����������֤,0-�رն�����֤
	 */
	public void updPhoneCheck(String[] id,Long flag)throws Exception;
	
	public void deleteUser(String[] id) throws Exception;
	
	public void userEnabledAgain(String[] id) throws Exception;
}
