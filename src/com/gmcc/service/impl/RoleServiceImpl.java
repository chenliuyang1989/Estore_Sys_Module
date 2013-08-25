package com.gmcc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gmcc.dao.RoleDAO;
import com.gmcc.model.Element;
import com.gmcc.model.Role;
import com.gmcc.service.RoleService;
import com.gmcc.util.AppContentGmcc;
import com.ibm.service.impl.OperateManagerImp;

public class RoleServiceImpl extends OperateManagerImp<Role,Long> implements RoleService{

	private RoleDAO roleDAO;
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public List<Role> getRoleList(Role role, int pageNo, int pageSize)
			throws Exception {
		List<Role> roleList=new ArrayList();
		List<Role> list=roleDAO.getRoleList(role, pageNo, pageSize);
		Iterator ite = list.iterator(); 
		while(ite.hasNext()){
			Map map = (Map)ite.next(); 
			Role roleTmp=new Role();
			BigDecimal id=(BigDecimal) map.get("id");
			roleTmp.setRoleId(id.longValue());
			String description=(String) map.get("description");
			roleTmp.setDescription(description);
			String name=(String) map.get("name");
			roleTmp.setName(name);
//			String statusFlag=(String)map.get("status_flag");
			
			BigDecimal roleTypeId=(BigDecimal) map.get("role_type");
			Element roleType=roleDAO.getElementById(roleTypeId.longValue());
			if(roleType!=null){
				roleTmp.setRoleType(roleType);
			}
			
			roleTmp.setMenuStr(roleDAO.getMenuByRole(id.longValue(), AppContentGmcc.MENU_LEVEL_MAX));
			roleList.add(roleTmp);
		}
		return roleList;
	}
	
	public Long getRoleCount(Role role)throws Exception {
		return roleDAO.getRoleCount(role);
	}

	public List getRoleMenuList(Role role, int pageNo, int pageSize)
			throws Exception {
		return roleDAO.getRoleMenuList(role, pageNo, pageSize);
	}

}
