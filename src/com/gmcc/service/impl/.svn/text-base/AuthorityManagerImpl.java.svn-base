package com.gmcc.service.impl;

import java.util.List;

import com.gmcc.dao.AuthorityDAO;
import com.gmcc.dao.UserDAO;
import com.gmcc.dto.MenuDTO;
import com.gmcc.model.Authority;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.gmcc.service.AuthorityManager;
import com.ibm.dto.AjaxDTO;
import com.ibm.service.impl.GenericManagerImpl;

/**
 * Implementation of AuthorityManager interface.
 * 
 */
// Service("authorityManager")
public class AuthorityManagerImpl extends GenericManagerImpl<Authority, Long> implements AuthorityManager {

	AuthorityDAO authorityDAO;
	UserDAO userDAO;

	// Autowired
	public AuthorityManagerImpl(AuthorityDAO authorityDAO) {
		super(authorityDAO);
		this.authorityDAO = authorityDAO;
	}

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	

	/**
	 * {@inheritDoc}
	 */
	public List<Authority> getAuthoritys(Authority role) {
		return authorityDAO.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public Authority getAuthority(String rolename) {
		return authorityDAO.getAuthorityByName(rolename);
	}

	/**
	 * {@inheritDoc}
	 */
	public Authority saveAuthority(Authority role) {
		return authorityDAO.save(role);
	}

	public List<Authority> getAllAppAuthoritys() {
		return authorityDAO.getAllTopAuthoritys();
	}

	public List<Authority> getAllTopAuthoritys() {
		return authorityDAO.getAllTopAuthoritys();

	}
	/**
	 * {@inheritDoc}
	 */
	public List<Authority> getSubAuthorities(Long id) {
		return authorityDAO.getSubAuthorities(id);
	}
	
	public List<Authority> getAllAppAuthoritys(String username) {
		return authorityDAO.getAllAppAuthoritys(username);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Authority> getAllTopAuthoritys(String username) {
		return authorityDAO.getAllTopAuthoritys(username);
	}
	
	public List<Authority> getAllTopAuthoritysByApp(String username, String app){
		return authorityDAO.getAllTopAuthoritysByApp(username, app);
	}
	
	public Authority getAuthoritysByName(String menuName){
		return authorityDAO.getAuthoritysByName(menuName);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Authority> getAllAuthoritys(String username) {
		return authorityDAO.getAllAuthoritys(username);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Authority> getSubAuthorities(Long id, String username) {
		return authorityDAO.getSubAuthorities(id, username);
	}

	/* (non-Javadoc)
	 * @see com.gmcc.service.AuthorityManager#getAllAuthoritys()
	 */
	public List<Authority> getAllAuthoritys() {
		return authorityDAO.getAllAuthoritys();
	}
	/**
	 * {@inheritDoc}
	 */
	public List<MenuDTO> getAllMenus(Role role) {
		return authorityDAO.getAllMenus(role);
	}
	/**
	 * {@inheritDoc}
	 */
	public void doLogin(String username,String jsessionId){
		authorityDAO.doLogin(username, jsessionId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getLoginedUserSessionId(String username){
		return authorityDAO.getLoginedUserSessionId(username);
	}
	
	/**
	 * 保存用户权限有变更的标识
	 * @param username
	 */
	public void saveAuthorityChanged(String username){
		authorityDAO.saveAuthorityChanged(username);
	}
	
	/**
	 * 
	 * @param username
	 * @return 在线用户是否有权限变更
	 */	
	public boolean isUpdatedAuth(String username){
		return authorityDAO.isUpdatedAuth(username);
	}
	
	/**
	 * 用于查找同组别的数据字典枚举项列表
	 * @param groupName
	 * @return 同组别的数据字典枚举项列表
	 */
	public List<Element> getCacheItems(String groupName){
		return userDAO.getItemsOfGroup(groupName);
	}
	
	/**
	 * 用于查找数据字典枚举项列表
	 * @param groupName
	 * @return 同组别的数据字典枚举项列表
	 */
	public List<Element> getCacheItemsByGroup(String groupName, String eleName){
		return userDAO.getItemsOfGroup(groupName, eleName);
	}
	
	/**
	 * 
	 * @param groupName
	 * @return 指定数据组的版本
	 */
	public String getCacheVersion(String groupName){
		return userDAO.getGroupVersion(groupName);
	}
	public List<AjaxDTO> getUserNames(String name,Long departmentId){
		return userDAO.getUserNames(name,departmentId);
	}
	
	/**
	 * 
	 * @param roleid
	 * @return 指定角色ID的用户列表
	 */
	public List<User> getUsersbyRoleId(String roleId){
		return userDAO.findByRoleId(roleId);
	}
	
	public User getUserbyName(String userName){
		return userDAO.getUserByName(userName);
	}
	public User getUserByName(String userName,Boolean type){
		return userDAO.getUserByName(userName,type);
	}
//	public boolean userLonginFirst(String userName){
//		return authorityDAO.userLonginFirst(userName);
//	}
	public void updUserErrorNum(Long userId,Long errorNum){
		userDAO.updUserErrorNum(userId, errorNum);
	}
	public void updUserEnable(Long userId){
		userDAO.updUserEnable(userId);
	}
	public User getUser(String userName,String pwd){
		return userDAO.getUser(userName, pwd);
	}
	public Element getItemsByGroupAndCode(String groupName, String eleCode){
		return userDAO.getItemsByGroupAndCode(groupName, eleCode);
	}
	
    public Element getItemsOfGroupByGroupNameAndEleName(String groupName, String eleName)
	{
		return  userDAO.getItemsOfGroupByGroupNameAndEleName(groupName, eleName);

	}
    public Role getRoleByName(String name)
    {
    	return userDAO.getRoleByName(name);
    }

	public void updUserLastLoginTime(Long userId){
		userDAO.updUserLastLoginTime(userId);
	}
	public void updUserLoginMsg(String phoneMsg,Long userId){
		userDAO.updUserLoginMsg(phoneMsg, userId);
	}

	public User getUserByFullNameAndCityId(String fullName, String cityId) {
		// TODO Auto-generated method stub
		return userDAO.getUserByFullNameAndCityId(fullName, cityId);
	}

	public HrCompany getHrComByName(String comName) {
		// TODO Auto-generated method stub
		return userDAO.getHrComByName(comName);
	}

	public int getRoleTypeByNames(String[] roleNames) {
		// TODO Auto-generated method stub
		return userDAO.getRoleTypeByNames(roleNames);
	}
}