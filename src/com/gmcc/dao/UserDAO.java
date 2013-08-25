package com.gmcc.dao;

import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.gmcc.model.Element;
import com.gmcc.model.ElementGroup;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.ibm.dao.GenericDAO;
import com.ibm.dto.AjaxDTO;
import com.ibm.util.CriteriaContent;
import com.ibm.util.Page;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UserDAO extends GenericDAO<User, Long> {

    /**
     * Gets users information based on login name.
     * @param username the user's username
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.userdetails.UsernameNotFoundException thrown when user not found in database
     */
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Gets a list of users ordered by the uppercase version of their username.
     *
     * @return List populated list of users
     */
    List<User> getUsers();

    
	public int getRoleTypeByNames(String[] roleNames);

     List<User> getUsers(Page p);
     
     /**
 	 * 
 	 * @param departmentId
 	 * @return 获得指定部门的用户名称列表
 	 */
     public List<AjaxDTO> getUserNames(String name,Long entId);
    /**
     * Saves a user's information.
     * @param user the object to be saved
     * @return the persisted User object
     */
    User saveUser(User user);

    Page getUsers(CriteriaContent queryParameters);
    /**
     * Retrieves the password in DB for a user
     * @param username the user's username
     * @return the password in DB, if the user is already persisted
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    String getUserPassword(String username);
    /**
     * 获得数据字典中的所有分组
     * @return 数据字典中的所有分组
     */

	public List<ElementGroup> getAllGroups();
    /**
     * 
     * @param groupId
     * @return 数据字典中指定分组的枚举项
     */

	public List<Element> getItemsOfGroup(Long groupId);
	
	public HrCompany getHrComByName(String comName);
    
	/**
     * 
     * @param groupName
     * @return 数据字典中指定分组的枚举项
     */
	public List<Element> getItemsOfGroup(String groupName);
	
	/**
     * 
     * @param groupName
     * @return 数据字典的枚举项
     */
	public List<Element> getItemsOfGroup(String groupName, String eleName);
	public Element getItemsOfGroupByGroupNameAndEleName(String groupName, String eleName);
	/**
     * 
     * @param groupName
     * @return 指定组当前版本号
     */
    public String getGroupVersion(String groupName);
	/**
	 * 获取商品信息列表
	 * @param goodCode
	 * @return
	 */
	public Role getRoleById(Long roleId);
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	
	
	public List<User> findByRoleId(String roleId) ;
	/**
	 * 根据用户名查询可用用户
	 * @param userName
	 * @return
	 */
	/**
	 * 通过角色名查找角色
	 */
	public Role getRoleByName(String name);
	public User getUserByName(String userName);
	/**
	 * 根据用户名查询用户(type-null:所有, type-true:可用的,type-false:不可用的)
	 * @param userName
	 * @return
	 */
	public User getUserByName(String userName,Boolean type);
	public User getUserByFullNameAndCityId(String fullName,String cityId);

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
