package com.gmcc.dao.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.gmcc.dao.RoleDAO;
import com.gmcc.dao.hibernate.base.BaseDao;
import com.gmcc.model.Authority;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.gmcc.util.AppContentGmcc;

public class RoleDAOHibernate extends BaseDao<Role,Long> implements RoleDAO{

	public List<Role> getRoleList(Role role, int pageNo, int pageSize){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
		if(pageNo>1){
			pageNo = (pageNo-1)*pageSize+1;
			pageSize=pageNo+pageSize-1;
		}
		Object[] args = new Object[2];
		args[1]=pageNo;
		args[0]=pageSize;
		StringBuffer sql=new StringBuffer();
		sql.append("select * from(select rownum rn,r.id,r.description,r.name,r.role_type from tb_app_role r ");
		if(role.getMenuId()!=null && !"".equals(role.getMenuId())){
			sql.append(" left join tr_app_role_menu rm on rm.role_id=r.id " );

			sql.append("where rm.menu_id="+role.getMenuId());
		}else{
			sql.append(" where 1=1 ");
		}

		sql.append(" and r.STATUS_FLAG=1 ");
		if(role.getName()!=null && !"".equals(role.getName())){
			sql.append(" and r.name like '%"+role.getName().trim()+"%'");
		}
		sql.append(" and rownum<=?) where rn>=? ");
		System.out.println(sql.toString());
		List list=jdbcTemplate.queryForList(sql.toString(), args);
		return list;
	}
	public Long getRoleCount(Role role){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) from tb_app_role r ");

		if(role.getMenuId()!=null && !"".equals(role.getMenuId())){
			sql.append(" left join tr_app_role_menu rm on rm.role_id=r.id " );

			sql.append("where rm.menu_id="+role.getMenuId());
		}else{
			sql.append(" where 1=1 ");
		}
		sql.append(" and r.STATUS_FLAG=1 ");
		if(role.getName()!=null && !"".equals(role.getName())){
			sql.append(" and r.name like '%"+role.getName().trim()+"%'");
		}
		System.out.println(sql.toString());
		return jdbcTemplate.queryForLong(sql.toString());
	}
	public List<User> getUserList(User user, String roleId,String userName,int pageNo,
			int pageSize) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
		if(pageNo>1){
			pageNo = (pageNo-1)*pageSize+1;
			pageSize=pageNo+pageSize-1;
		}
		Object[] args = new Object[2];
		args[1]=pageNo;
		args[0]=pageSize;
		StringBuffer sql=new StringBuffer();
		sql.append("select * from(select rownum rn,u.id,u.ACCOUNT_ENABLED,u.USERNAME," +
				" u.FULL_NAME,u.CITYSYSID,u.CHANNEL,u.TELEPHONE,u.ISPHONECHECK,u.LAST_LOGIN_TIME,u.activate_time,e.ele_name usertype,u.last_updated_time,u.last_updated_By,u.created_time " +
				" from tb_app_user u" +
				" left join tb_app_element e on e.id=u.user_type ");
//		sql.append(" left join tb_app_user_login b ");
//		sql.append(" on u.username = b.user_name ");
		if(user!=null && roleId!=null && !"".equals(roleId.trim()) && user.getCompId()!=null && !"".equals(user.getCompId())){
			sql.append(" left join TR_APP_USER_ROLE ur on ur.user_id=u.id " );
			sql.append(" left join TB_R_ORG_USER ou on ou.user_id=u.id " );
			String[] roleIds = roleId.trim().split(",");
			boolean first = true;
			if(roleIds!=null)
			{
			sql.append(" where ur.role_id in (");
			for(int i=0;i<roleIds.length;i++)
			{
				if(StringUtils.isNotBlank(roleIds[i].trim()))
				{
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(roleIds[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(roleIds[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");
			}
			sql.append(" and ou.comp_id="+user.getCompId());

		}else if(user!=null && roleId!=null && !"".equals(roleId.trim())  &&( user.getCompId()==null || "".equals(user.getCompId()))){
			sql.append(" left join TR_APP_USER_ROLE ur on ur.user_id=u.id " );
			String[] roleIds = roleId.trim().split(",");
			boolean first = true;
			if(roleIds!=null)
			{
			sql.append(" where ur.role_id in (");
			for(int i=0;i<roleIds.length;i++)
			{
				if(StringUtils.isNotBlank(roleIds[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(roleIds[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(roleIds[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");
			}
		}else if(user!=null && (roleId==null || "".equals(roleId.trim())) && user.getCompId()!=null && !"".equals(user.getCompId())){
			sql.append(" left join TB_R_ORG_USER ou on ou.user_id=u.id " );
			sql.append(" where ou.comp_id="+user.getCompId());
		}else{
			sql.append(" where 1=1 ");
		}
		if(user!=null && userName!=null && !"".equals(userName.trim())){
			boolean first = true;
			String[] userNames = userName.trim().split("\r\n");
			if(userNames!=null)
			{
			sql.append(" and u.username in (");
			for(int i=0;i<userNames.length;i++)
			{
				if(StringUtils.isNotBlank(userNames[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(userNames[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(userNames[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");}
		}
		if(user!=null && user.getFullname()!=null && !"".equals(user.getFullname())){
			sql.append(" and u.FULL_NAME like '%"+user.getFullname()+"%'");
		}
		//新增的查询条件
		if(user!=null && user.getLastUpdatedBy()!=null && !"".equals(user.getLastUpdatedBy()))
		{
			sql.append(" and u.last_updated_by like '%"+user.getLastUpdatedBy()+"%'");
		}
		if(user!=null && user.getCreateTimeStart()!=null && !"".equals(user.getCreateTimeStart()))
		{
			System.out.println("创建开始时间"+user.getCreateTimeStart());
			sql.append(" AND  u.created_time >= to_date('"+ user.getCreateTimeStart()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		if(user!=null && user.getCreateTimeEnd()!=null && !"".equals(user.getCreateTimeEnd()))
		{
			System.out.println("创建开始结束"+user.getCreateTimeEnd());

			sql.append(" AND  u.created_time <= to_date('"+ user.getCreateTimeEnd()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		
		if(user!=null && user.getUpdateTimeStart()!=null && !"".equals(user.getUpdateTimeStart()))
		{
			sql.append(" AND  u.last_updated_time >= to_date('"+ user.getUpdateTimeStart()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		if(user!=null && user.getUpdateTimeEnd()!=null && !"".equals(user.getUpdateTimeEnd()))
		{
			sql.append(" AND  u.last_updated_time <= to_date('"+ user.getUpdateTimeEnd()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		
		if(user!=null && user.getLoginTimeStart()!=null && !"".equals(user.getLoginTimeStart()))
		{
			sql.append(" AND  u.last_login_time >= to_date('"+ user.getLoginTimeStart()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		
		if(user!=null && user.getLoginTimeEnd()!=null && !"".equals(user.getLoginTimeEnd()))
		{
			sql.append(" AND  u.last_login_time <= to_date('"+ user.getLoginTimeEnd()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		//新增结束
		
		
		
		
		if(user!=null && user.getStatus()!=null && !"".equals(user.getStatus())){
			sql.append(" and u.ACCOUNT_ENABLED = "+user.getStatus());
		}
		if(user!=null && user.getChannel()!=null && !"".equals(user.getChannel().trim()) && !"-1".equals(user.getChannel())){
			String[] channels = user.getChannel().trim().split(",");
			boolean first = true;
			if(channels!=null)
			{
			sql.append(" and u.CHANNEL in (");
			for(int i=0;i<channels.length;i++)
			{
				if(StringUtils.isNotBlank(channels[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(channels[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(channels[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");
			}
			
		}

		if(user!=null && user.getCitySysID()!=null && !"".equals(user.getCitySysID().trim()) && !"-1".equals(user.getCitySysID())){
//			sql.append(" and u.CITYSYSID = "+user.getCitySysID());
			String[] cityIds = user.getCitySysID().trim().split(",");
			boolean first = true;
			if(cityIds!=null)
			{
			sql.append(" and u.CITYSYSID in (");
			for(int i=0;i<cityIds.length;i++)
			{
				if(StringUtils.isNotBlank(cityIds[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(cityIds[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(cityIds[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");
			}
			
		}
		if(user!=null && user.getIsPhoneCheck()!=null){
			sql.append(" and u.ISPHONECHECK = "+user.getIsPhoneCheck());
		}
		if(user!=null && user.getUserType()!=null && user.getUserType().getId()!=null){
			sql.append(" and u.user_type = "+user.getUserType().getId());
		}
		sql.append(" and rownum<=?) where rn>=? ");
		System.out.println(sql.toString());
		List list=jdbcTemplate.queryForList(sql.toString(), args);
		return list;
	}
	public Long getUserCount(User user, String roleId,String userName) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) from tb_app_user u ");
//		sql.append(" left join tb_app_user_login b ");
//		sql.append(" on u.username = b.user_name ");
//		if(user!=null && user.getRoleId()!=null && !"".equals(user.getRoleId()) && user.getCompId()!=null && !"".equals(user.getCompId())){
			if(user!=null && roleId!=null && !"".equals(roleId.trim()) && user.getCompId()!=null && !"".equals(user.getCompId())){

			sql.append(" left join TR_APP_USER_ROLE ur on ur.user_id=u.id " );
			sql.append(" left join TB_R_ORG_USER ou on ou.user_id=u.id " );
			String[] roleIds = roleId.trim().split(",");
			boolean first = true;
			if(roleIds!=null)
			{
			sql.append(" where ur.role_id in (");
			for(int i=0;i<roleIds.length;i++)
			{
				if(StringUtils.isNotBlank(roleIds[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(roleIds[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(roleIds[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");
			}
//			sql.append(" where ur.role_id="+user.getRoleId());
			sql.append(" and ou.comp_id="+user.getCompId());
		}
//			else if(user!=null && user.getRoleId()!=null && !"".equals(user.getRoleId()) &&( user.getCompId()==null || "".equals(user.getCompId()))){
			else if(user!=null && roleId!=null && !"".equals(roleId.trim())  &&( user.getCompId()==null || "".equals(user.getCompId()))){
			sql.append(" left join TR_APP_USER_ROLE ur on ur.user_id=u.id " );
			String[] roleIds = roleId.trim().split(",");
			boolean first = true;
			if(roleIds!=null)
			{
			sql.append(" where ur.role_id in (");
			for(int i=0;i<roleIds.length;i++)
			{
				if(StringUtils.isNotBlank(roleIds[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(roleIds[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(roleIds[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");
			}
//			sql.append(" where ur.role_id="+user.getRoleId());
		}else if(user!=null && (roleId==null || "".equals(roleId.trim())) && user.getCompId()!=null && !"".equals(user.getCompId())){
			sql.append(" left join TB_R_ORG_USER ou on ou.user_id=u.id " );
			sql.append(" where ou.comp_id="+user.getCompId()+" ");
		}else{
			sql.append(" where 1=1 ");
		}
		
		if(user!=null && userName!=null && !"".equals(userName.trim())){
			boolean first = true;
			String[] userNames = userName.trim().split("\r\n");
			if(userNames!=null)
			{
			sql.append(" and u.username in (");
			for(int i=0;i<userNames.length;i++)
			{
				if(StringUtils.isNotBlank(userNames[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(userNames[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(userNames[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");}
		}
		if(user!=null && user.getFullname()!=null && !"".equals(user.getFullname())){
			sql.append(" and u.FULL_NAME like '%"+user.getFullname()+"%'");
		}
		
		//新增的查询条件
		if(user!=null && user.getLastUpdatedBy()!=null && !"".equals(user.getLastUpdatedBy()))
		{
			sql.append(" and u.last_updated_by like '%"+user.getLastUpdatedBy()+"%'");
		}
		if(user!=null && user.getCreateTimeStart()!=null && !"".equals(user.getCreateTimeStart()))
		{
			sql.append(" AND  u.created_time >= to_date('"+ user.getCreateTimeStart()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		if(user!=null && user.getCreateTimeEnd()!=null && !"".equals(user.getCreateTimeEnd()))
		{
			sql.append(" AND  u.created_time <= to_date('"+ user.getCreateTimeEnd()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		
		if(user!=null && user.getUpdateTimeStart()!=null && !"".equals(user.getUpdateTimeStart()))
		{
			sql.append(" AND  u.last_updated_time >= to_date('"+ user.getUpdateTimeStart()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		if(user!=null && user.getUpdateTimeEnd()!=null && !"".equals(user.getUpdateTimeEnd()))
		{
			sql.append(" AND  u.last_updated_time <= to_date('"+ user.getUpdateTimeEnd()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		if(user!=null && user.getLoginTimeStart()!=null && !"".equals(user.getLoginTimeStart()))
		{
			sql.append(" AND  u.last_login_time >= to_date('"+ user.getLoginTimeStart()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		
		if(user!=null && user.getLoginTimeEnd()!=null && !"".equals(user.getLoginTimeEnd()))
		{
			sql.append(" AND  u.last_login_time <= to_date('"+ user.getLoginTimeEnd()+"','yyyy-mm-dd hh24:mi:ss') ");
		}
		
		
		//新增结束
		
		
		
		
		if(user!=null && user.getStatus()!=null && !"".equals(user.getStatus())){
			sql.append(" and u.ACCOUNT_ENABLED = "+user.getStatus());
		}
//		if(user!=null && user.getChannel()!=null && !"".equals(user.getChannel())){
//			sql.append(" and u.CHANNEL = "+user.getChannel());
//		}
		
		if(user!=null && user.getChannel()!=null && !"".equals(user.getChannel().trim()) && !"-1".equals(user.getChannel())){
			String[] channels = user.getChannel().trim().split(",");
			boolean first = true;
			if(channels!=null)
			{
			sql.append(" and u.CHANNEL in (");
			for(int i=0;i<channels.length;i++)
			{
				if(StringUtils.isNotBlank(channels[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(channels[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(channels[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");
			}
			
		}
		if(user!=null && user.getCitySysID()!=null && !"".equals(user.getCitySysID().trim()) && !"-1".equals(user.getCitySysID())){
//			sql.append(" and u.CITYSYSID = "+user.getCitySysID());
			String[] cityIds = user.getCitySysID().trim().split(",");
			boolean first = true;
			if(cityIds!=null)
			{
			sql.append(" and u.CITYSYSID in (");
			for(int i=0;i<cityIds.length;i++)
			{
				if(StringUtils.isNotBlank(cityIds[i].trim()))
				{
					
				if(first)
				{
					sql.append("'")
					   .append(String.valueOf(cityIds[i].trim()))
					   .append("'");
					first = false;
				}
				else
				{
					sql.append(",'")
					   .append(String.valueOf(cityIds[i].trim()))
					   .append("'");
				} 
				}
			}
			sql.append(")");
			}
			
		}
		if(user!=null && user.getIsPhoneCheck()!=null){
			sql.append(" and u.ISPHONECHECK = "+user.getIsPhoneCheck());
		}
		if(user!=null && user.getUserType()!=null && user.getUserType().getId()!=null){
			sql.append(" and u.user_type = "+user.getUserType().getId());
		}
		System.out.println(sql.toString());
		return jdbcTemplate.queryForLong(sql.toString());
	}
	public List<Role> getAllRoleList(){
		return this.getHibernateTemplate().find("from Role r where r.statusFlag=1");
	}
	public List<Role> getRoleByUser(Long userId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
		String sql="select r.name from TB_APP_ROLE r " +
				" left join TR_APP_USER_ROLE ur on ur.role_id=r.id "+
				" where ur.user_id="+userId;
		return jdbcTemplate.queryForList(sql);
	}
	public String getHrCompanyByUser(Long userId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
		String sql="select distinct(t.comp_id),nvl(t.parent_id,0) parid,t.COMP_NAME name from "+
					" tb_hr_company t "+
					" left join tb_r_org_user ur on ur.comp_id=t.comp_id "+
					" connect by prior  t.parent_id=t.comp_id start with ur.user_id="+userId;
		List<HrCompany>  compList=jdbcTemplate.queryForList(sql);
		String compStr="";
//		List<HrCompany> list=new ArrayList();
		Iterator ite = compList.iterator(); 
		while(ite.hasNext()){
			Map map = (Map)ite.next();
			BigDecimal parid=(BigDecimal) map.get("parid");
			if(parid.longValue()==0l){
				compStr=compStr+(String)map.get("name")+",";
			}
		}
		if(!compStr.equals(""))
			return compStr.substring(0,compStr.length()-1);
		return null;
	}
	public String getMenuByRole(Long roleId,String menuLevel){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
		String sql="select m.id,m.menu_name name from tr_app_role_menu rm,tb_app_menu m" +
				" where rm.menu_id=m.id" +
				" and m.menu_level='"+menuLevel+"' "+//AppContentGmcc.MENU_LEVEL_MAX+"' " +
				" and rm.role_id="+roleId;
		List<Authority>  menuList=jdbcTemplate.queryForList(sql);
		String menuStr="";
		Iterator ite = menuList.iterator(); 
		while(ite.hasNext()){
			Map map = (Map)ite.next();
			menuStr=menuStr+(String)map.get("name")+",";

		}
		if(!menuStr.equals(""))
			return menuStr.substring(0,menuStr.length()-1);
		return null;
				
	}
	public Element getElementById(Long id){
		String hql="from Element e where e.id="+id;
		List<Element> list=this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public void updPhoneCheck(String[] id,Long flag){
		String ids="";
		for(int i=0;i<id.length;i++){
			ids=ids+id[i]+",";
		}
		ids=ids.substring(0,ids.length()-1);
		String sql="update tb_app_user u set u.isphonecheck="+flag+" where u.id in("+ids+")";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
		jdbcTemplate.execute(sql);
	}
	public List getRoleMenuList(Role role, int pageNo, int pageSize)
	throws Exception {
StringBuffer sb = new StringBuffer();
sb.append(" select c.menu_name menu_name ,1 firstLevel,d.ele_name secondLevel,a.name thirdLevel,1 four,e.menu_name fiveLevel,c.id id from tb_app_role a  ")
 .append(" left join tr_app_role_menu b ")
 .append(" on a.id = b.role_id  ")
 .append(" left join tb_app_menu c ")
  .append(" on b.menu_id = c.id  ")
  .append(" left join tb_app_element d ")
  .append(" on d.id = a.role_type ")
  .append("   left join tb_app_menu e  ")
   .append("  on c.parent_id = e.id ")
  .append(" where 1 = 1 ")
  .append(" and c.menu_level ='3'  ")
.append("  and e.menu_name is not null  ");
//.append(" and c.menu_name not in ('盘点结果明细表','盘点结果汇总表','仓库提交','物流商在手订单统计','系统在库数','移动审核','收货查询','仓库盘点数','订单仓库路由规则','更改物流商','开始盘点任务','盘点登记') ");

  if(role!=null && role.getName()!=null && !"".equals(role.getName().toString()))
  {
	  sb.append(" AND a.name like '%")
	    .append(role.getName().toString())
	    .append("%'");
  }
  if(role!=null && role.getRoleType()!=null && role.getRoleType().getId()!=null  && !"-1".equals(role.getRoleType().getId()))
  {
	  sb.append(" AND d.id = '")
	    .append(role.getRoleType().getId().toString())
	    .append("'");
  }
	  

	  sb.append(" order by  e.menu_name ");
	  System.out.println(sb.toString());
JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));

return jdbcTemplate.queryForList(sb.toString());
}
}
