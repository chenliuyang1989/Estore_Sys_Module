package com.gmcc.dao;

import java.util.List;

import com.gmcc.dto.MenuDTO;
import com.gmcc.model.Authority;
import com.gmcc.model.Role;import com.ibm.dao.GenericDAO;



/**
 * Authority Data Access Object (DAO) interface.
 *菜单系统功能DAO
 */
public interface AuthorityDAO extends GenericDAO<Authority, Long> {
    /**
     * Gets Authority information based on authorityName
     * @param authorityName the authorityName
     * @return populated role object
     */
	Authority getAuthorityByName(String authorityName);

	/**
     * 
     * @return 所有应用
     */
	List<Authority> getAllAppAuthoritys(String username);
 
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
	 * 获取全部菜单信息
	 * @return
	 */
	List<Authority> getAllAuthoritys();
	/**
	 * 
	 * @param roleName
	 * @return
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
	 * 检查用户是否第一次登录系统
	 * @param userName 用户名
	 * @return
	 */
//	public boolean userLonginFirst(String userName);
}
