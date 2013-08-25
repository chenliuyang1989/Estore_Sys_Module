package com.gmcc.service;

import java.util.List;

import com.gmcc.model.Role;
import com.ibm.service.IOperateManager;

public interface RoleService extends IOperateManager<Role,Long>{

	public List<Role> getRoleList(Role role,int pageNo,int pageSize)throws Exception;
	public List getRoleMenuList(Role role,int pageNo,int pageSize)throws Exception;
	public Long getRoleCount(Role role)throws Exception;
}
