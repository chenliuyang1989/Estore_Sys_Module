package com.gmcc.dao.hibernate;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.gmcc.dao.HrCompanyDAO;
import com.gmcc.dao.hibernate.base.BaseDao;
import com.gmcc.dto.OrgAuthorityDTO;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.util.AppConst;

public class HrCompanyDAOHibernate extends BaseDao<OrgAuthorityDTO, Long> implements HrCompanyDAO {

    /**
     * Constructor that sets the entity to User.class.
     */
	public List<OrgAuthorityDTO> getOrgParList(Long userId, Long orgType) {
		// TODO Auto-generated method stub
		String sql="select org.COMP_ID id,org.ORGSYSID orgSysId,org.COMP_CODE code ,org.COMP_NAME compName " +
				" from TB_HR_COMPANY org,TB_R_ORG_USER ou " +
				" where org.COMP_ID=ou.COMP_ID " +
				" and ou.USER_ID="+userId+" and org.ORGTYPE="+orgType;
		
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List<OrgAuthorityDTO> getOrgChildList(Long parId,Long userId) {
		String sql="select org.COMP_ID id,org.ORGSYSID orgSysId,org.COMP_CODE code,org.COMP_NAME compName " +
				" from TB_HR_COMPANY org,TB_R_ORG_USER ou " +
				" where org.COMP_ID=ou.COMP_ID " +
				" and ou.USER_ID="+userId+
				" and org.PARENT_ID="+parId;
		return this.getJdbcTemplate().queryForList(sql);
	}
    public List getOrgList(String sql){
		return this.getJdbcTemplate().queryForList(sql);
    }
    public int updateOrg(String sql){
    	super.executeNativeUpdateSql(sql);
    	return 1;
    }
    public HrCompany getHrCompanyByName(String name){
    	String hql="from HrCompany h where h.compName='"+name+"' and h.enabled='"+AppConst.ENABLED+"'";
    	List<HrCompany> list=this.getHibernateTemplate().find(hql);
    	if(list!=null && list.size()>0){
    		return list.get(0);
    	}
    	return null;
    }
    public Element getElementById(Long eleId){
    	String hql="from Element h where h.id="+eleId;
    	List<Element> list=this.getHibernateTemplate().find(hql);
    	if(list!=null && list.size()>0){
    		return list.get(0);
    	}
    	return null;
    }
    public Long getCompTypeById(Long compId){
//    	String sql="select t.orgtype from tb_hr_company t " +
////    			",tb_app_element e " +
//    			" where t.comp_level = 1 " +
////    			" and e.id=t.orgtype " +
//    			" start with t.comp_id = " +compId+
//    			" connect by prior t.parent_id = t.comp_id";
    	String sql="select t.orgtype from tb_hr_company t " +
					" where t.comp_id = " +compId;
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(this.getHgdao().getSessionFactory()));
    	return jdbcTemplate.queryForLong(sql);
    	
    }
}
