package com.gmcc.service;

import com.gmcc.dto.MenuDTO;
import com.gmcc.model.Authority;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.ibm.dto.AjaxDTO;
import com.ibm.service.GenericManager;


import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * 菜单系统功能service
 */
public interface AuthorityManager extends GenericManager<Authority, Long> {
    /**
     * {@inheritDoc}
     */
    List<Authority> getAuthoritys(Authority authority);

    /**
     * {@inheritDoc}
     */
    Authority getAuthority(String authorityName);
	public HrCompany getHrComByName(String comName);

    List<Authority> getAllAppAuthoritys();
    
    /**
     * 
     * @return 所有顶级菜单
     */
    List<Authority> getAllTopAuthoritys();
    /**
     * 
     * @param 父菜单id
     * @return 指定菜单的子菜单
     */
    List<Authority> getSubAuthorities(Long id);
    
    /**
     * 
     * @param username
     * @return 指定用户拥有的应用
     */
    List<Authority> getAllAppAuthoritys(String username);
    
    /**
     * 
     * @param username
     * @return 指定用户拥有的顶级菜单项
     */
    List<Authority> getAllTopAuthoritys(String username);
    
    List<Authority> getAllTopAuthoritysByApp(String username, String app);
    
    Authority getAuthoritysByName(String menuName);
    
    /**
     * 
     * @param username
     * @return 指定用户拥有的菜单项
     */
    List<Authority> getAllAuthoritys(String username);
    /**
     * 
     * @param id
     * @param username
     * @return 特定父菜单下，指定用户拥有的子菜单项
     */
    List<Authority> getSubAuthorities(Long id,String username);

	/**
	 * 获取全部菜单记录
	 * @return
	 */
	List<Authority> getAllAuthoritys();
	/**
	 * 
	 * @param roleName
	 * @return 指定角色所拥有的菜单项
	 */
	public List<MenuDTO> getAllMenus(Role role) ;
	/**
	 * 用户登录时，记录到用户登录表
	 * @param username
	 * @param jsessionId
	 */
	public void doLogin(String username,String jsessionId);
	/**
	 * 
	 * @param username
	 * @return 指定用户登录系统后的sessionId
	 */
	public String getLoginedUserSessionId(String username);
	
	/**
	 * 保存用户权限有变更的标识
	 * @param username
	 */
	public void saveAuthorityChanged(String username);
	
	/**
	 * 
	 * @param username
	 * @return 在线用户是否有权限变更
	 */
	public boolean isUpdatedAuth(String username);
	
	/**
	 * 用于查找同组别的数据字典枚举项列表
	 * @param groupName
	 * @return 同组别的数据字典枚举项列表
	 */
	public List<Element> getCacheItems(String groupName);
	
	/**
	 * 用于查找数据字典枚举项列表
	 * @param groupName
	 * @return 同组别的数据字典枚举项列表
	 */
	
	
	public List<Element> getCacheItemsByGroup(String groupName, String eleName);
	/**
	 * 根据组名和名称查找枚举列表
	 * @param groupName
	 * @param eleName
	 * @return
	 */
	public Element getItemsOfGroupByGroupNameAndEleName(String groupName, String eleName);
    /**
     * 根基角色名查找角色
     */
	public Role getRoleByName(String name);
	
	
	public int getRoleTypeByNames(String[] roleNames);
	/**
	 * 
	 * @param groupName
	 * @return 指定数据组的版本
	 */
	public String getCacheVersion(String groupName);
	
	/**
	 * 
	 * @param departmentId
	 * @return 获得指定部门的用户名称列表
	 */
	public List<AjaxDTO> getUserNames(String name,Long departmentId);
		
	/**
	 * 
	 * @param roleid
	 * @return 指定角色ID的用户列表
	 */
	public List<User> getUsersbyRoleId(String roleid);
	/**
	 * 根据用户名查询可用用户
	 * @param userName
	 * @return
	 */
	public User getUserbyName(String userName);
	/**
	 * 根据用户名查询可用用户
	 * @param fullName cityId
	 * @return
	 */
	public User getUserByFullNameAndCityId(String fullName,String cityId);
	/**
	 * 根据用户名查询用户(type-null:所有, type-true:可用的,type-false:不可用的)
	 * @param userName
	 * @return
	 */
	public User getUserByName(String userName,Boolean type);
	
//	/**
//	 * 检查用户是否第一次登录系统
//	 * @param userName 用户名
//	 * @return
//	 */
//	public boolean userLonginFirst(String userName);
	/**
	 * 更新用户登录错误次数
	 */
	public void updUserErrorNum(Long userId,Long errorNum);
	/**
	 * 锁定用户,更新用户账号为未激活
	 */
	public void updUserEnable(Long userId);
	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public User getUser(String userName,String pwd);
	
	public Element getItemsByGroupAndCode(String groupName, String eleCode);
	
	/**
	 * 更新最后登录时间
	 * @param userId
	 */
	public void updUserLastLoginTime(Long userId);
	
	public void updUserLoginMsg(String phoneMsg,Long userId);
}
