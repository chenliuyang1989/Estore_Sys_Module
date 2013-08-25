package com.gmcc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.gmcc.dao.RoleDAO;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.gmcc.service.RoleService;
import com.gmcc.service.UserService;
import com.ibm.service.impl.OperateManagerImp;

public class UserServiceImpl extends OperateManagerImp<User,Long> implements UserService{

	private RoleDAO roleDAO;
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public List<User> getUserList(User user,String roleId,String userName,int pageNo,int pageSize)
			throws Exception {
		List<User> userList=new ArrayList();
		List<User> list=roleDAO.getUserList(user,roleId,userName, pageNo, pageSize);
		Iterator ite = list.iterator(); 
		while(ite.hasNext()){
			Map map = (Map)ite.next(); 
			User userTmp=new User();
			BigDecimal id=(BigDecimal) map.get("id");
			userTmp.setUserId(id.longValue());
			userTmp.setChannel((String) map.get("CHANNEL"));
			userTmp.setCitySysID((String) map.get("CITYSYSID"));
			BigDecimal enabled=(BigDecimal) map.get("ACCOUNT_ENABLED");
			if(enabled!=null && enabled.intValue()==1){
				userTmp.setEnabled(true);
				userTmp.setStatus("1");
			}else{
				userTmp.setEnabled(false);
				userTmp.setStatus("0");
			}
			userTmp.setUsername((String) map.get("username"));
			userTmp.setFullname((String) map.get("FULL_NAME"));
			
			userTmp.setRoleStr(getRoleStr(id.longValue()));
			userTmp.setCompStr(roleDAO.getHrCompanyByUser(id.longValue()));
			userTmp.setPhoneNumber((String) map.get("TELEPHONE"));
			
			BigDecimal isPhoneCheck=(BigDecimal) map.get("isPhoneCheck");
			userTmp.setIsPhoneCheck(isPhoneCheck==null?0l:isPhoneCheck.longValue());
			userTmp.setUserTypeName((String) map.get("userType"));
			userTmp.setActivateTime((Date) map.get("activate_time"));
			userTmp.setLastUpdatedTime((Date) map.get("last_updated_time"));
			userTmp.setCreatedTime((Date)map.get("created_time"));
			userTmp.setLastUpdatedBy((String)map.get("last_updated_By"));
			userTmp.setLastLoginTime((Date)map.get("last_login_time"));
			userList.add(userTmp);
		}
		return userList;
	}
	private String getRoleStr(Long userId){
		String roleStr="";
		List<Role> roleList=roleDAO.getRoleByUser(userId);
		Iterator ite = roleList.iterator(); 
		while(ite.hasNext()){
			Map map = (Map)ite.next();
			if(map.get("name")!=null && !"".equals(map.get("name")))
				roleStr=roleStr+(String)map.get("name")+",";
		}
		if(!roleStr.equals(""))
			return roleStr.substring(0,roleStr.length()-1);
		return null;
	}
//	private String getCompStr(Long userId){
//		String compStr="";
//		List<HrCompany> compList=roleDAO.getHrCompanyByUser(userId);
//		Iterator ite = compList.iterator(); 
//		while(ite.hasNext()){
//			Map map = (Map)ite.next();
//			if(map.get("name")!=null && !"".equals(map.get("name")))
//				compStr=compStr+(String)map.get("name")+",";
//		}
//		if(!compStr.equals(""))
//			return compStr.substring(0,compStr.length()-1);
//		return null;
//	}
	public Long getUserCount(User user,String roleId,String userName)throws Exception {
		return roleDAO.getUserCount(user,roleId,userName);
	}

	public List<Role> getAllRoleList()throws Exception{
		return roleDAO.getAllRoleList();
	}
	@Transactional
	public void updPhoneCheck(String[] id, Long flag) throws Exception {
		this.setEntityClass(User.class);
		this.setPKClass(Long.class);
		for (int i = 0; i < id.length; i++) {
			User user = super.findById(Long.valueOf(id[i]));
			user.setIsPhoneCheck(flag);
			super.merge(user);
		}
	}
	@Transactional
	public void deleteUser(String[] id) throws Exception {
		this.setEntityClass(User.class);
		this.setPKClass(Long.class);
		for (int i = 0; i < id.length; i++) {
			User user = super.findById(Long.valueOf(id[i]));
			user.setEnabled(false);
			super.merge(user);
		}
	}
	@Transactional
	public void userEnabledAgain(String[] id) throws Exception {
		this.setEntityClass(User.class);
		this.setPKClass(Long.class);
		for (int i = 0; i < id.length; i++) {
			User user = super.findById(Long.valueOf(id[i]));
			user.setErrorNum(0L);
			user.setActivateTime(new Date());
			user.setEnabled(true);
			super.merge(user);
		}
	}
}
