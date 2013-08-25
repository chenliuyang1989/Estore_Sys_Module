package com.gmcc.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.gmcc.dao.AuthorityDAO;
import com.gmcc.dto.MenuDTO;
import com.gmcc.model.Authority;
import com.gmcc.model.Role;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.ConvertUtils;
import com.ibm.dao.hibernate.GenericDAOHibernate;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Authority objects.
 *
 */
//Repository("authorityDao")
public class AuthorityDAOHibernate extends GenericDAOHibernate<Authority, Long> implements AuthorityDAO {

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public AuthorityDAOHibernate() {
        super(Authority.class);
    }

    /**
     * {@inheritDoc}
     */
	public Authority getAuthorityByName(String authorityName) {
        @SuppressWarnings("rawtypes")
		List as = getHibernateTemplate().find("from Authority where name=?", authorityName);
        if (as.isEmpty()) {
            return null;
        } else {
            return (Authority) as.get(0);
        }
    }

	@SuppressWarnings("unchecked")
	public List<Authority> getAllAppAuthoritys(){
    	String hql=" from Authority where parentId=0 order by menuPosition  ";
    	List<Authority> as = getHibernateTemplate().find(hql);
    	if (as.isEmpty()) {
            return null;
        } else {
            return as;
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<Authority> getAllTopAuthoritys(){
    	String hql=" from Authority where parentId=0 order by menuPosition  ";
    	List<Authority> as = getHibernateTemplate().find(hql);
    	if (as.isEmpty()) {
            return null;
        } else {
            return as;
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<Authority> getSubAuthorities(Long id){
    	String hql=" from Authority where parentId=? order by menuPosition ";
    	List<Authority> as = getHibernateTemplate().find(hql,id);
    	if (as.isEmpty()) {
            return null;
        } else {
            return as;
        }
    	
    }
    
    @SuppressWarnings("unchecked")
	public List<Authority> getAllAppAuthoritys(String username){
    	String hql=" select a from User u left join u.roles r left join r.authoritys  a where a.parentId=0 and u.username=? order by a.menuPosition  ";
    	List<Authority> as = getHibernateTemplate().find(hql,username);
    	return as;
    }
    
    @SuppressWarnings("unchecked")
	public List<Authority> getAllTopAuthoritysByApp(String username, String app) {
    	Object[] obj = new Object[2];
    	obj[0] = username;
    	obj[1] = Long.valueOf(app);
    	String hql=" select a from User u left join u.roles r left join r.authoritys a where a.menuLevel='2' and u.username=? and a.parentId = ? order by a.menuPosition  ";
    	List<Authority> as = getHibernateTemplate().find(hql, obj);
    	return as;
    }
    
    public Authority getAuthoritysByName(String menuName) {
    	Authority authority = null;
    	List<Authority> as = getHibernateTemplate().find("from Authority where menuName = ?", menuName);
    	if(!as.isEmpty()){
    		authority = as.get(0);
    	}
    	return authority;
    }
    
    @SuppressWarnings("unchecked")
	public List<Authority> getAllTopAuthoritys(String username){
    	String hql=" select a from User u left join u.roles r left join r.authoritys  a where a.menuLevel='2' and u.username=? order by a.menuPosition  ";
    	List<Authority> as = getHibernateTemplate().find(hql,username);
    	if (as.isEmpty()) {
            return null;
        } else {
            return as;
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<Authority> getAllAuthoritys(String username){
    	String hql=" select a from User u left join u.roles r left join r.authoritys  a where  u.username=? order by a.menuPosition  ";
    	List<Authority> as = getHibernateTemplate().find(hql,username);
    	if (as.isEmpty()) {
            return null;
        } else {
            return as;
        }
    }
    
    /**
	 * {@inheritDoc}
	 */
    @SuppressWarnings("unchecked")
	public List<Authority> getSubAuthorities(Long id,String username){
    	String hql=" select a from User u left join u.roles r left join r.authoritys  a where a.parentId=:parentId" +
    			" and u.username=:username order by a.menuPosition ";
    	Map pars =new HashMap();
    	pars.put("parentId", id);
    	pars.put("username", username);
    	List<Authority> as = findByNamedParam(hql,pars);
    	if (as.isEmpty()) {
            return null;
        } else {
            return as;
        }
    	
    }

	/* (non-Javadoc)
	 * @see com.ibm.dao.AuthorityDao#getAllAuthoritys()
	 */
	@SuppressWarnings("unchecked")
	public List<Authority> getAllAuthoritys() {
    	String hql=" from Authority where isMenu=true order by menuPosition ";
    	List<Authority> as = getHibernateTemplate().find(hql);
    	if (as.isEmpty()) {
            return null;
        } else {
            return as;
        }
	}
	/**
	 * {@inheritDoc}
	 */
	public List<MenuDTO> getAllMenus(Role role) {
		List<Authority> menusOfRole= new ArrayList<Authority>();
		if(role!=null){
			menusOfRole=role.getAuthoritys();
		}
		List<MenuDTO> menuDTOs= new ArrayList<MenuDTO>();
		List<Authority> allRootMenus=this.getAllTopAuthoritys();
		List<Authority> subMenus;
		String subMenuStr;
		MenuDTO menuDTO;
		for(Authority root:allRootMenus){
			subMenus=this.getSubAuthorities(root.getId());
			menuDTO=new MenuDTO();
			if(subMenus!=null){
				subMenuStr=ConvertUtils.getSubMenuStr(menusOfRole,subMenus);
				menuDTO.setRootMenu(getCheckBoxHtml(menusOfRole,root));
				menuDTO.setSubMenus(subMenuStr);
				
				menuDTOs.add(menuDTO);
			}
		}
		
		return menuDTOs;
	}
	/**
	 * {@inheritDoc}
	 */
	private String getCheckBoxHtml(List<Authority> menusOfRole,Authority menu){
		StringBuilder sb=new StringBuilder("");
		sb.append("<input id='");
		sb.append(menu.getId());
		sb.append("' type='checkbox' name='menusOfRole");
		//sb.append(menu.getId());
		sb.append("' ");
		if(menusOfRole!=null && menusOfRole.contains(menu)){
			sb.append(" checked='checked' ");
		}
		sb.append(" value='");
		sb.append(menu.getId());
		sb.append("' onclick=\"checkSome('");
		sb.append(menu.getId());
		sb.append("'); \">");
		sb.append(menu.getMenuName());
		sb.append("</input> ");
		return sb.toString();
	}
	/**
	 * {@inheritDoc}
	 */
	public void doLogin(String username,String jsessionId){
		//用于记录用户登录状态的数据库表"TB_USER_LOGIN"字段比较少，直接使用SimpleJdbcTemplate 处理 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String name[] = new String[]{username};
		String userSessionId="";
		try{
			 userSessionId=(String)jdbcTemplate.queryForObject("select USER_SESSION_ID from TB_APP_USER_LOGIN where USER_NAME=?", name, String.class);
		}catch(Exception ex){
		}
		if("".equals(userSessionId)){//当该用户为第一次登录时 ，插入一条新登录信息记录
			Object[] obj = new Object[3];
			obj[0] = jsessionId;
			obj[1] = username;
			obj[2] = new Date();
			jdbcTemplate.update("insert into TB_APP_USER_LOGIN(USER_SESSION_ID,USER_NAME,LAST_LOGIN_TIME) values(?,?,?)", obj);
		}else{//当该用户不是第一次登录时，更新该用户的JSESSION ID
			Object[] obj = new Object[3];
			obj[0] = jsessionId;
			obj[1] = new Date();
			obj[2] = username;
			jdbcTemplate.update("update TB_APP_USER_LOGIN set USER_SESSION_ID=?, LAST_LOGIN_TIME=?, CHANGE_AUTH = '' where USER_NAME=?", obj);
		}
		
	}
	/**
	 * {@inheritDoc}
	 */
	public String getLoginedUserSessionId(String username){
		JdbcTemplate jdbcTemplate =
            new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String name[] = new String[]{username};
		String userSessionId=(String)jdbcTemplate.queryForObject("select USER_SESSION_ID from TB_APP_USER_LOGIN where USER_NAME=?", name, String.class);
		if(userSessionId==null){
			return "";
		}else{
			return userSessionId;
		}
	}
	/**
	 * 保存用户权限有变更的标识
	 * @param username
	 */
	public void saveAuthorityChanged(String username){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String pars[]= new String[]{AppContentGmcc.AUTHORITY_CHANGED_UPDATE,username};
		jdbcTemplate.update("update TB_APP_USER_LOGIN set CHANGE_AUTH=? where USER_NAME=?", pars);
	}
	/**
	 * 
	 * @param username
	 * @return 在线用户是否有权限变更
	 */
	public boolean isUpdatedAuth(String username){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String name[] = new String[]{username};
		String userSessionId=(String)jdbcTemplate.queryForObject("select CHANGE_AUTH from TB_APP_USER_LOGIN where USER_NAME=?", name, String.class);
		if(userSessionId==null){
			return false;
		}else{
			return true;
		}
	}
//	public boolean userLonginFirst(String userName){
//		JdbcTemplate jdbcTemplate =new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
//		String sql="select USER_SESSION_ID from TB_APP_USER_LOGIN where USER_NAME='"+userName+"'";
//		List list=jdbcTemplate.queryForList(sql);
//		if(list!=null && list.size()>0){
//			return false;
//		}
//		return true;
//	}
}
