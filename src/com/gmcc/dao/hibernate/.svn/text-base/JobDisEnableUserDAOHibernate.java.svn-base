package com.gmcc.dao.hibernate;

import com.gmcc.dao.JobDisEnableUserDAO;
import com.gmcc.dao.hibernate.base.BaseDao;
import com.gmcc.model.User;

public class JobDisEnableUserDAOHibernate extends BaseDao<User, Long> implements JobDisEnableUserDAO {
	
	public void updUserDisEnable(){
//		String sql=" update tb_app_user u set u.account_enabled=0 where u.id in "+
//		" (select l.operator_id "+
//		" from tb_app_business_log l,tb_app_element e,tb_app_element_group g  "+
//		" where l.type=e.id "+
//		" and g.ele_group_name='logType' "+
//		" and e.ele_group_id=g.id "+
//		" and e.ele_code='1_LOGIN' "+
//		" group by l.operator_id "+
//		" having floor(sysdate - to_date(to_char(max(l.operator_time),'yyyy-mm-dd'),'yyyy-mm-dd'))>" +
//		" (select to_number(eh.ele_name) from tb_app_element eh,tb_app_element_group ehg where eh.ele_group_id=ehg.id "+
//		" and ehg.ele_group_name='noLoginDeadlineGroup' and eh.ele_code='noLoginDeadlineCode'))";
		String sql=" update tb_app_user u set u.account_enabled=0 where  "+
		" floor(sysdate - to_date(to_char(u.activate_time,'yyyy-mm-dd'),'yyyy-mm-dd'))> "+
		" (select to_number(eh.ele_name) from tb_app_element eh,tb_app_element_group ehg where eh.ele_group_id=ehg.id  "+
		" and ehg.ele_group_name='noLoginDeadlineGroup' and eh.ele_code='noLoginDeadlineCode') "+
		" and (u.id in  "+
		" (select l.operator_id  "+
		" from tb_app_business_log l,tb_app_element e,tb_app_element_group g   "+
		" where l.type=e.id  "+
		" and g.ele_group_name='logType'  "+
		" and e.ele_group_id=g.id  "+
		" and e.ele_code='1_LOGIN'  "+
		" group by l.operator_id  "+
		" having floor(sysdate - to_date(to_char(max(l.operator_time),'yyyy-mm-dd'),'yyyy-mm-dd'))> "+
		" (select to_number(eh.ele_name) from tb_app_element eh,tb_app_element_group ehg where eh.ele_group_id=ehg.id  "+
		" and ehg.ele_group_name='noLoginDeadlineGroup' and eh.ele_code='noLoginDeadlineCode')) "+
		" or  not exists (select 1 from tb_app_business_log l1,tb_app_element e,tb_app_element_group g "+
		"   where l1.type = e.id "+
		"    and g.ele_group_name = 'logType' "+
		"     and e.ele_group_id = g.id "+
		"     and e.ele_code = '1_LOGIN' " +
		"  and l1.operator_id=u.id))";
		this.getJdbcTemplate().execute(sql);
	}
	
	public void updUserPwdErrorNum(){
//		String sql=" update tb_app_user u set u.errornum=0,u.account_enabled=1 where u.errornum>= "+
//		" (select to_number(e.ele_name) from tb_app_element e,tb_app_element_group g  "+
//		" where e.ele_group_id=g.id "+
//		" and g.ele_group_name='pwdErrorGroup' "+
//		" and e.ele_code='pwdErrorCode') ";
		String sql=" update tb_app_user u set u.errornum=0,u.account_enabled=1 where u.errornum>0";
		this.getJdbcTemplate().execute(sql);
	}
}
