package com.gmcc.dao;

import java.util.List;

import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;

public interface RoleDAO {
	
	public List getRoleMenuList(Role role,int pageNo,int pageSize)throws Exception;


	/**
	 * 根据查询条件查询角色列表
	 * @param role
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Role> getRoleList(Role role, int pageNo, int pageSize);
	/**
	 * 根据查询条件查询角色记录数
	 * @param role
	 * @return
	 */
	public Long getRoleCount(Role role);
	/**
	 * 根据查询条件查询用户列表
	 * @param user
	 * @param userName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<User> getUserList(User user,String roleId,String userName,int pageNo,int pageSize);
	/**
	 * 根据查询条件查询用户记录数
	 * @param user
	 * @param userName
	 * @return
	 */
	public Long getUserCount(User user,String roleId,String userName);
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> getAllRoleList();
	/**
	 * 根据用户ID查询用户所属角色
	 * @param userId
	 * @return
	 */
	public List<Role> getRoleByUser(Long userId);
	/**
	 * 根据用户ID查询用户可用组织
	 * @param userId
	 * @return
	 */
	public String getHrCompanyByUser(Long userId);
	/**
	 * 根据角色ID,菜单等级查询用户可用菜单权限
	 * @param roleId
	 * @return
	 */
	public String getMenuByRole(Long roleId,String menuLevel);
	
	public Element getElementById(Long id);
	/**
	 * 批量更新用户是否需短信验证()
	 * @param id
	 * @param flag 1-开启短信验证,0-关闭短信验证
	 */
	public void updPhoneCheck(String[] id,Long flag);
}
