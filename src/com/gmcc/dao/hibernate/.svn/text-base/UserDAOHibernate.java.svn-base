package com.gmcc.dao.hibernate;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.gmcc.dao.UserDAO;
import com.gmcc.dto.OrgAuthorityDTO;
import com.gmcc.model.Authority;
import com.gmcc.model.Element;
import com.gmcc.model.ElementGroup;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.gmcc.util.AppConst;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.ListUtil;
import com.ibm.dao.hibernate.GenericDAOHibernate;
import com.ibm.dto.AjaxDTO;
import com.ibm.util.CriteriaContent;
import com.ibm.util.DictionaryUtils;
import com.ibm.util.Page;
import com.ibm.util.StringUtil;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *   Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *   Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *   Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with 
 *   the new BaseDaoHibernate implementation that uses generics.
*/
public class UserDAOHibernate extends GenericDAOHibernate<User, Long> implements UserDAO, UserDetailsService {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDAOHibernate() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return getHibernateTemplate().find("from User u order by upper(u.username)");
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	public List<User> getUsers(Page p){
    	Query query = getSession().createQuery("from User u order by upper(u.username)");
    	query.setFirstResult(p.getCurrentPageNo());
    	query.setMaxResults(p.getPageSize()+p.getCurrentPageNo());
    	List<User> ls= query.list();
    	return ls;
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	public List<AjaxDTO> getUserNames(String name,Long entId){
    	StringBuffer hql =new StringBuffer("select new com.ibm.dao.dto.AjaxDTO(a.id,a.fullname)" );
    	hql=hql.append(" from User a ");
    	if(entId!=null && entId!=-1){
    		hql=hql.append(" where a.entId=:entId");
    	}
    	if(name!=null && !"".equals(name)){
    		hql=hql.append(" where a.fullname like:name");
    	}
    	Query query = getSession().createQuery(hql.toString());
    	if(entId!=null && entId!=-1){
    		query.setLong("entId", entId);
    	}
    	if(name!=null && !"".equals(name)){
    		query.setString("name", '%'+name+'%');
    	}
    	List<AjaxDTO> ls= query.list();
    	return ls;
    }
    
    public Page getUsers(CriteriaContent queryParameters){
    	return this.pagedQuery(" from User u order by upper(u.username) ", 
    			queryParameters.getPageNo(), queryParameters.getPageSize());
    }
    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        log.debug("user's id: " + user.getId());
        getHibernateTemplate().saveOrUpdate(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getHibernateTemplate().flush();
        return user;
    }

    /**
     * Overridden simply to call the saveUser method. This is happenening 
     * because saveUser flushes the session and saveObject of BaseDaoHibernate 
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public User save(User user) {
        return this.saveUser(user);
    }

    
    /** 
     * {@inheritDoc}
    */
    @SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	String pi = DictionaryUtils.SSO_SEPARATOR;
//    	boolean isMock = false;
    	String mockUserName = username;
    	String mockCity = "";
    	String userCities = "";
    	
    	if(username.contains(pi)){
    		String[] paraS_ = username.split(pi);
    		mockUserName = paraS_[0];
    		mockCity = paraS_[1];
    		String token = paraS_[2];
    		String type = paraS_[3];
    		if(token.toLowerCase().equalsIgnoreCase(DictionaryUtils.genToken(mockUserName,type))){
    			List<Element> el = this.getItemsOfGroup("mockLoginCityType", "%NGCC");
    			if(!el.isEmpty()){
    				userCities = el.get(0).getEleName();
    			}else{
    				userCities = mockCity;
    			}
    			User mockUser = newMockUser(mockUserName, mockCity,userCities);
				return mockUser;
    		}
    	}
    	
    	List users =null;
    	try{
    		users = getHibernateTemplate().find("from User where  username=?", username);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {        	
			List<Authority> auths = new ArrayList<Authority>();
			User user = (User) users.get(0);
//			Element userType=user.getUserType();
//			if(!user.isEnabled()){
//				throw new org.springframework.security.LockedException("User account is locked");
//			}
			//Calendar created = Calendar.getInstance();
			//created.setTime(createdTime);
			//created.add(Calendar.MONTH, user.getEnabledMonth());
			//Calendar now = Calendar.getInstance();
			//if(now.getTimeInMillis()<created.getTimeInMillis()){
			//	throw new UsernameNotFoundException(" 密码过期");
			//}
			
			
			Set<Role> roles=user.getRoles();
			for(Role role:roles){
				List<Authority> auths0=role.getAuthoritys();
				auths.addAll(auths0);
			}
			if (auths.size() == 0) {// sr3Permissions
				throw new UsernameNotFoundException(" user not found");
			}
			Authority perminssion = new Authority();
			perminssion.setMenuFunction("ROLE_ADMIN");
			auths.add(perminssion);
			GrantedAuthority[] authorities = (GrantedAuthority[]) auths.toArray(new GrantedAuthority[auths.size()]);
			user.setAuth(authorities);
			
			String roleTypeStr="";
			for(Role role:roles){
				Element roleType=role.getRoleType();
				if(roleType!=null){
					roleTypeStr=roleType.getEleCode();
				}
				break;
			}
			user.setRoleTypeCode(roleTypeStr);
			Map dataAuth=new HashMap();
			Element orgType=null;
			//仓库权限
//			if(roleTypeStr.equals(AppContentGmcc.DATA_AUTHORITY_WAREHOUSE)){
				orgType=this.getItemOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME, AppContentGmcc.ORG_TYPE_WAREHOUSE);
				if(orgType!=null){
					Long[] warehouse=getOrgSysId(orgType,user);
					dataAuth.put(AppContentGmcc.DATA_AUTHORITY_WAREHOUSE, warehouse);
				}
//			}
			
			//供应商权限
			else if(roleTypeStr.equals(AppContentGmcc.DATA_AUTHORITY_PROVIDER)){
				orgType=this.getItemOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME, AppContentGmcc.ORG_TYPE_PROVIDER);
				if(orgType!=null){
					Long[] provider=getOrgSysId(orgType,user);
					dataAuth.put(roleTypeStr, provider);
				}
			}
			//物流商权限
//			else if(roleTypeStr.equals(AppContentGmcc.DATA_AUTHORITY_LOGISTICS)){
				orgType=this.getItemOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME, AppContentGmcc.ORG_TYPE_LOGISTICS);
				if(orgType!=null){
					Long[] logistics=getOrgSysId(orgType,user);
					dataAuth.put(AppContentGmcc.DATA_AUTHORITY_LOGISTICS, logistics);
				}
//			}
//			orgType=this.getItemOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME, AppContentGmcc.ORG_TYPE_CITY);
//			if(orgType!=null){
				String[] cities=getOrgCityList(user.getUserId());
				dataAuth.put(AppContentGmcc.DATA_AUTHORITY_CITY, cities);
				
				String[] channels=getOrgChannelList(user.getUserId());
				dataAuth.put(AppContentGmcc.DATA_AUTHORITY_CHANNEL, channels);
//			}
			//地市用户权限
//			if(roleTypeStr.equals(AppContentGmcc.DATA_AUTHORITY_PROVINCE)){
//				dataAuth.put(roleTypeStr, -1l);
//			}
			if(roleTypeStr.equals(AppContentGmcc.DATA_AUTHORITY_CITY_WAREHOUSE)){
				String cityId=user.getCitySysID();
				if(cityId!=null && !"".equals(cityId)){
					dataAuth.put(roleTypeStr, Long.valueOf(cityId));
					user.setCitySysID(cityId);
				}else{
					dataAuth.put(roleTypeStr, -1l);
					user.setCitySysID("-1");
				}
				Long[] cityWarehouse=new Long[]{Long.valueOf(user.getCitySysID())};
				dataAuth.put(AppContentGmcc.DATA_AUTHORITY_CITY_WAREHOUSE,cityWarehouse );
			}
//			user.getChannel()
			//系统管理员
//			if(roleTypeStr.equals(AppContentGmcc.DATA_AUTHORITY_SYSMGR)){
//				dataAuth.put(roleTypeStr, -1l);
//			}
//			String cityId=user.getCitySysID();
//			if(cityId!=null && !"".equals(cityId)){
//				dataAuth.put(AppContentGmcc.DATA_AUTHORITY_CITY, Long.valueOf(cityId));
//			}else{
//				dataAuth.put(AppContentGmcc.DATA_AUTHORITY_CITY, -1L);//全省范围
//			}
			user.setAuthoritiesData(dataAuth);
			
//			if(isMock){
//				User mockUser = newMockUser(mockUserName, mockCity);
//				mockUser.setAuthoritiesData(dataAuth);
//				return mockUser;
//			}
			
//			user.setLastLoginTime(new Date());
//			this.saveUser(user);
			return user;
    	}
        
    }
    
	public User newMockUser(String mockUserName,String mockCity,String userCities) throws UsernameNotFoundException {
 		
    	
		List<Authority> auths = new ArrayList<Authority>();
		User user = genMockUser(mockUserName,mockCity);
		user.setCitySysID(mockCity);
		
		Authority authority_ = new Authority();
		authority_.setEnabled(AppConst.ENABLED);
		authority_.setIsMenu(true);
		authority_.setMenuApp("http://localhost/estoreSa");
//		authority_.setMenuKey(menuKey);
		authority_.setMenuLevel("1");
		authority_.setMenuName("代客下单");
		authority_.setMenuPosition("09");
		authority_.setMenuUrl("/sa/index.do");
		authority_.setParentId(0l);
		authority_.setStatus(true);
		auths.add(authority_);
		
		authority_ = new Authority();
		authority_.setEnabled(AppConst.ENABLED);
		authority_.setIsMenu(true);
		authority_.setMenuApp("http://localhost/estoreSa");
//		authority_.setMenuKey(menuKey);
		authority_.setMenuLevel("1");
		authority_.setMenuName("代客下单");
		authority_.setMenuPosition("10");
		authority_.setMenuUrl("/sa/index.do");
		authority_.setParentId(9l);
		authority_.setStatus(true);
		auths.add(authority_);
    	
    	Authority perminssion = new Authority();
		perminssion.setMenuFunction("ROLE_ADMIN");
		auths.add(perminssion);
		
		GrantedAuthority[] authorities = (GrantedAuthority[]) auths.toArray(new GrantedAuthority[auths.size()]);
		user.setAuth(authorities);
		
		String roleTypeStr="529";

		
		user.setRoleTypeCode(roleTypeStr);
		
    	Map dataAuth=new HashMap();
    	String[] citys = userCities.split(",");
    	dataAuth.put(AppContentGmcc.DATA_AUTHORITY_CITY, citys);
    	dataAuth.put(AppContentGmcc.DATA_AUTHORITY_CHANNEL, new String[]{"13"});//代客下单
    	user.setAuthoritiesData(dataAuth);
		return user;
	
    }
	
    private User genMockUser(String mockUserName,String mockCity){
		
//        String Name = request.getParameter("Name");
//		String City = request.getParameter("City");
//		
//		
		User userMock = new User();
		userMock.setChannel("13");
		
		Element userType = new Element();
		userType.setId(21800l);
		userType.setEleCode("1");
		userMock.setEnabled(true);
		userMock.setUsername(mockUserName);
//		userMock.setPassword(password);
		userMock.setUserType(userType);
		userMock.setCitySysID(mockCity);
		
		
		return userMock;
    }
    
    public String[] getOrgCityList(Long userId) {
    	String sql="select  c.orgcitycode,c.comp_name "+
					" from tb_hr_company c,tb_r_org_user u,tb_app_element e,tb_app_element_group eg "+
					" where u.comp_id=c.comp_id "+
					" and c.comp_level!=1 "+
					" and c.ENABLED='"+AppConst.ENABLED+"' "+
					" and e.id=c.orgtype "+
					" and e.ele_code='"+AppContentGmcc.ORG_TYPE_CITY+"' "+
					" and e.ele_group_id=eg.id "+
					" and eg.ele_group_name='"+AppContentGmcc.ORG_TYPE_GROUPNAME+"' "+
					" and u.user_id="+userId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		List list=jdbcTemplate.queryForList(sql);
		Iterator ite = list.iterator(); 
		String[] cities=new String[list.size()];
		int i=0;
		while(ite.hasNext()){
			Map map = (Map)ite.next(); 
//			BigDecimal orgSysId=(BigDecimal) map.get("orgSysId");
			cities[i]=(String) map.get("orgcitycode");
			i++;
		}
		return cities;
	}
    
    public String[] getOrgChannelList(Long userId) {
    	String sql="select  to_char(c.orgsysid) orgsysid ,c.comp_name "+
					" from tb_hr_company c,tb_r_org_user u,tb_app_element e,tb_app_element_group eg "+
					" where u.comp_id=c.comp_id "+
					" and c.comp_level!=1 "+
					" and c.ENABLED='"+AppConst.ENABLED+"' "+
					" and e.id=c.orgtype "+
					" and e.ele_code='"+AppContentGmcc.ORG_TYPE_CHANNEL+"' "+
					" and e.ele_group_id=eg.id "+
					" and eg.ele_group_name='"+AppContentGmcc.ORG_TYPE_GROUPNAME+"' "+
					" and u.user_id="+userId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		List list=jdbcTemplate.queryForList(sql);
		Iterator ite = list.iterator(); 
		String[] channels=new String[list.size()];
		int i=0;
		while(ite.hasNext()){
			Map map = (Map)ite.next(); 
//			BigDecimal orgSysId=(BigDecimal) map.get("orgSysId");
			channels[i]=(String) map.get("orgsysid");
			i++;
		}
		return channels;
	}
    
    private Element getItemOfGroup(String groupName, String eleName){
    	List<Element> list=this.getItemsOfGroup(groupName, eleName);
    	if(list!=null && list.size()>0){
    		return list.get(0);
    	}
    	return null;
    }
   
    private Long[] getOrgSysId(Element orgType,User user){
		List<OrgAuthorityDTO> orgList=getOrgAuthorityList(orgType,user);
		List<OrgAuthorityDTO> listNew = ListUtil.removeDuplication(orgList, "orgSysId");  
		Long[] sysId=new Long[listNew.size()];
		for(int i=0;i<listNew.size();i++){
			sysId[i]=listNew.get(i).getOrgSysId();
		}
		if(sysId==null || sysId.length<=0){
			sysId=new Long[1];
			sysId[0]=-1L;
		}
		return sysId;
	}
    private List<OrgAuthorityDTO> getOrgAuthorityList(Element orgType,User user){
		List<OrgAuthorityDTO> orgList=new ArrayList();
		List<OrgAuthorityDTO> orgParList=getOrgParList(user.getUserId(), orgType.getId());
		if(orgParList!=null && orgParList.size()>0){
			Iterator ite = orgParList.iterator(); 
			while(ite.hasNext()){
				Map map = (Map)ite.next(); 
				BigDecimal id=(BigDecimal) map.get("id");
				BigDecimal orgSysId=(BigDecimal) map.get("orgSysId");
				String code=(String)map.get("code");
				OrgAuthorityDTO dto=new OrgAuthorityDTO();
				dto.setId(id.longValue());
				if(orgSysId!=null){
					dto.setOrgSysId(orgSysId.longValue());
				}
				dto.setCode(code);
				if(orgSysId!=null)
					orgList.add(dto);
				getOrgNodeList(orgList,id.longValue(),user.getUserId());
			}
		}
		return orgList;
	}
	private List<OrgAuthorityDTO> getOrgNodeList(List<OrgAuthorityDTO> orgList,Long parId,Long userId){
		List<OrgAuthorityDTO> nodeList=getOrgChildList(parId,userId);
		if(nodeList!=null){
			Iterator ite = nodeList.iterator(); 
			while(ite.hasNext()){
				Map map = (Map)ite.next(); 
				BigDecimal id=(BigDecimal) map.get("id");
				BigDecimal orgSysId=(BigDecimal) map.get("orgSysId");
				String code=(String)map.get("code");
				OrgAuthorityDTO dto=new OrgAuthorityDTO();
				dto.setId(id.longValue());
				if(orgSysId!=null){
					dto.setOrgSysId(orgSysId.longValue());
				}
				dto.setCode(code);
				if(orgSysId!=null)
					orgList.add(dto);
				getOrgNodeList(orgList,dto.getId(),userId);
			}
		
		}
		return orgList;
	}
	private List<OrgAuthorityDTO> getOrgParList(Long userId, Long orgType) {
		// TODO Auto-generated method stub
		String sql="select org.COMP_ID id,org.ORGSYSID orgSysId,org.COMP_CODE code " +
				" from TB_HR_COMPANY org,TB_R_ORG_USER ou " +
				" where org.COMP_ID=ou.COMP_ID " +
				" and ou.USER_ID="+userId+
				" and org.ORGTYPE="+orgType+
				" and org.ENABLED="+AppConst.ENABLED;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		return jdbcTemplate.queryForList(sql);
	}

	private List<OrgAuthorityDTO> getOrgChildList(Long parId,Long userId) {
		String sql="select org.COMP_ID id,org.ORGSYSID orgSysId,org.COMP_CODE code " +
				" from TB_HR_COMPANY org,TB_R_ORG_USER ou " +
				" where org.COMP_ID=ou.COMP_ID " +
				" and ou.USER_ID="+userId+
				" and org.PARENT_ID="+parId+
				" and org.ENABLED="+AppConst.ENABLED;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		return jdbcTemplate.queryForList(sql);
	}
    /** 
     * {@inheritDoc}
    */
    public String getUserPassword(String username) {
        SimpleJdbcTemplate jdbcTemplate =
                new SimpleJdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where username=?", String.class, username);

    }
    /**
     * 获得数据字典中的所有分组
     * @return 数据字典中的所有分组
     */
    @SuppressWarnings("unchecked")
	public List<ElementGroup> getAllGroups(){
    	return getHibernateTemplate().find("from ElementGroup g order by upper(g.eleGroupName)");
    }
    /**
     * 
     * @param groupName
     * @return 指定组当前版本号
     */
    public String getGroupVersion(String groupName){
    	SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
    	Table table = AnnotationUtils.findAnnotation(ElementGroup.class, Table.class);
    	return jdbcTemplate.queryForObject("select GROUP_VERSION from " + table.name() + " where ELE_GROUP_NAME=?", String.class, groupName);
    }
    /**
     * 
     * 
     * @param groupId
     * @return 数据字典中指定分组的枚举项
     */
    @SuppressWarnings("unchecked")
	public List<Element> getItemsOfGroup(Long groupId){
    	return getHibernateTemplate().find("from Element e where e.elementGroup.id=? order by  by e.id",groupId);
    }
    /**
     * 
     * @param groupName
     * @return 数据字典中指定分组的枚举项
     */
    @SuppressWarnings("unchecked")
	public List<Element> getItemsOfGroup(String groupName){
    	return getHibernateTemplate().find("from Element e where e.elementGroup.eleGroupName=? order by e.orderNum,e.eleCode",groupName);
    }
    
    /**
     * 
     * @param groupName
     * @return 数据字典的枚举项
     */
    @SuppressWarnings("unchecked")
	public List<Element> getItemsOfGroup(String groupName, String eleName){    	
    	String hql = "from Element e where e.elementGroup.eleGroupName=? ";
    	if(StringUtil.isValidString(eleName)){
    		hql += "and e.eleCode like '"+eleName.trim()+"%' ";
    	}
    	hql += "order by e.id";
    	return getHibernateTemplate().find(hql, groupName);
    }
    
    public Element getItemsOfGroupByGroupNameAndEleName(String groupName, String eleName)
    {
    	String hql = "from Element e where e.elementGroup.eleGroupName='"+groupName+"' and e.eleName='"+eleName+"'";
    	List<Element> list=getHibernateTemplate().find(hql);
    	if(list!=null && list.size()>0){
    		return list.get(0);
    	}
    	return null;
    }
	
	public Role getRoleById(Long roleId){
		Object r=getHibernateTemplate().get(Role.class, roleId);
		Role role=null;
		if(r!=null){
			role=(Role)r;
		}
		return role;
	}
	@SuppressWarnings("unchecked")
	public List<User> findByRoleId(String roleId) {
		 List<User> users =null;
	    
	     users = getHibernateTemplate().
	     		 find("select u from User u left join u.roles r where r.id=?",
	    		 new Long(roleId));
	    	
	     return users;
	}
	
	public User getUserByName(String userName){
		List<User> users = getHibernateTemplate().find("from User where  username='"+userName+"'");
		if(users!=null && users.size()>0){
			return users.get(0);
		}
		return null;
	}
	public User getUserByName(String userName,Boolean type){
		
		String pi = DictionaryUtils.SSO_SEPARATOR;
    	String mockUserName = userName;
    	String mockCity = "";
    	if(userName.contains(pi)){
    		String[] paraS_ = userName.split(pi);
    		mockUserName = paraS_[0];
    		mockCity = paraS_[1];
    		String token = paraS_[2];
    		String userType = paraS_[3];
    		if(token.toLowerCase().endsWith(DictionaryUtils.genToken(mockUserName,userType).toLowerCase())){
    			User mockUser = newMockUser(mockUserName, mockCity,mockCity);
    			return mockUser;
    		}
    	}
		
		String hql="";
		if(type==null){
			hql="from User where  username='"+userName+"'";
		}else{
			hql="from User where  username='"+userName+"' and enabled="+type;	
		}
		List<User> users = getHibernateTemplate().find(hql);
		if(users!=null && users.size()>0){
			return users.get(0);
		}
		return null;
	}
	
	public void updUserErrorNum(Long userId,Long errorNum){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String sql="update tb_app_user set errornum="+errorNum+" where id="+userId;
		jdbcTemplate.execute(sql);
	}
	public void updUserEnable(Long userId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String sql="update tb_app_user set ACCOUNT_ENABLED=0 where id="+userId;
		jdbcTemplate.execute(sql);
	}
	public void updUserLastLoginTime(Long userId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String sql="update tb_app_user set LAST_LOGIN_TIME=sysdate where id="+userId;
		jdbcTemplate.execute(sql);
	}
	public User getUser(String userName,String pwd){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String sql="select id,username,errornum from tb_app_user where username='"+userName+"' and password!='"+pwd+"'";
		List list=jdbcTemplate.queryForList(sql);
		if(list!=null && list.size()>0){
			Map map=(Map) list.iterator().next();
			BigDecimal id=(BigDecimal) map.get("id");
			BigDecimal errorNum=(BigDecimal) map.get("errornum");
			User user=new User(userName);
			user.setUserId(id.longValue());
			user.setErrorNum(errorNum.longValue());
			return user;
		}
		return null;
	}
	public Element getItemsByGroupAndCode(String groupName, String eleCode){
		String hql = "from Element e where e.elementGroup.eleGroupName='"+groupName+"' and e.eleCode='"+eleCode+"'";
    	List<Element> list=getHibernateTemplate().find(hql);
    	if(list!=null && list.size()>0){
    		return list.get(0);
    	}
    	return null;
	}
	
	public void updUserLoginMsg(String phoneMsg,Long userId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		String sql="update tb_app_user set PHONE_MSG='"+phoneMsg+"', PHONE_SEND_TIME=sysdate where id="+userId;
		jdbcTemplate.execute(sql);
	}

	public User getUserByFullNameAndCityId(String fullName, String cityId) {
		String hql="from User e where  e.fullname='"+fullName+"' and e.citySysID='"+cityId+"'";
		List<User> users = getHibernateTemplate().find(hql);
		if(users!=null && users.size()>0){
			return users.get(0);
		}
		return null;
	}

	public Role getRoleByName(String name) {
        
		String hql="from Role a where a.name ='"+name+"'";
		List<Role> roles = getHibernateTemplate().find(hql);
		if(roles!=null && roles.size()>0)
		{
			return roles.get(0);
		}
		return null;
	}

	public HrCompany getHrComByName(String comName) {
		String hql = "from HrCompany a where a.compName ='"+comName+"'";
		List<HrCompany> companys = getHibernateTemplate().find(hql);
		if(companys!=null && companys.size()>0)
		{
			return companys.get(0);
		}
			
		return null;
	}

	public int getRoleTypeByNames(String[] roleNames) {
		StringBuffer sb = new StringBuffer();
		boolean ff = true;

		sb.append(" select  count(distinct role_type)  from tb_app_role a where a.name in (");
		for (int i = 0; i < roleNames.length; i++) {
			if (StringUtils.isNotBlank(roleNames[i].trim())) {
				if (ff) {
					sb.append("'" + String.valueOf(roleNames[i].trim())
							+ "'");
					ff = false;
				} else {
					sb.append(", '" + String.valueOf(roleNames[i].trim())
							+ "'");
				}
			}
		}
		sb.append(")");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));

		return jdbcTemplate.queryForInt(sb.toString());
	}
}
