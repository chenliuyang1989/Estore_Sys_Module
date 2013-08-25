package com.gmcc.dao.hibernate;

import java.util.List;

import com.gmcc.cons.element.Constants;
import com.gmcc.dao.LogDAO;
import com.gmcc.dao.hibernate.base.BaseDao;
import com.gmcc.dto.LogDTO;
import com.gmcc.model.BusinessLog;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.DateUtil;
import com.ibm.util.StringUtil;



public class LogDAOHibernate extends BaseDao<LogDTO, Long> implements LogDAO {

	public List<LogDTO> getLogList(int pageNo, int pageSize,
			BusinessLog businessLog) throws Exception{
		// TODO Auto-generated method stub
//		String hql="select d from  GoodsSupplyDtl d order by d.createdTime desc";
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
//		Table table = AnnotationUtils.findAnnotation(Transport.class, Table.class);
		if(pageNo>1){
			pageNo = (pageNo-1)*pageSize+1;
			pageSize=pageNo+pageSize-1;
		}
		Object[] args = new Object[2];
		args[1]=pageNo;
		args[0]=pageSize;
		StringBuffer sql=new StringBuffer();
		sql.append("select * from(select rownum rn,t.* from(select b.id id,et.ele_name logType,e.ele_name cityName," +
				" b.OPERATOR_NAME operName,to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss')  operTime, " +
				" b.OPERATOR_METHOD_KEY operMothod,b.OPERATOR_CONTENT content,b.OPERATOR_OBJECT_KEY operatorObjectKey,b.loginip loginIp"+
				" from tb_app_business_log b,tb_app_element e,tb_app_element_group eg,tb_app_element et" +
				" where eg.id=e.ELE_GROUP_ID " +
				" and eg.ELE_GROUP_NAME='"+AppContentGmcc.CITY_GROUPNAME+"'"+
				" and b.CITYSYSID=e.ele_code "+
				" and et.id=b.type");
		String formatStr="yyyy-MM-dd";
		if(businessLog.getType()!=null && StringUtil.isValidString(businessLog.getType().getEleCode())){
			sql.append(" and et.ele_code='"+businessLog.getType().getEleCode()+"'") ;
		}
		if(businessLog.getOperatorTimeBegin()!=null && businessLog.getOperatorTimeEnd()==null){
			sql.append(" AND to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss') >= ('"+ 
					DateUtil.dateToStr(businessLog.getOperatorTimeBegin(), formatStr)+" 00:00:00')") ;
		}
		if(businessLog.getOperatorTimeBegin()!=null && businessLog.getOperatorTimeEnd()!=null){
			sql.append(" AND to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss') >= ('"+ 
					DateUtil.dateToStr(businessLog.getOperatorTimeBegin(), formatStr)+" 00:00:00')"+
					" AND to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss') <= ('"+ 
					DateUtil.dateToStr(businessLog.getOperatorTimeEnd(), formatStr)+" 23:59:59')") ;
		}
		if(businessLog.getOperatorTimeBegin()==null && businessLog.getOperatorTimeEnd()!=null){
			sql.append(" AND to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss') <= ('"+ 
					DateUtil.dateToStr(businessLog.getOperatorTimeEnd(), formatStr)+" 23:59:59')") ;
		}
		if(StringUtil.isValidString(businessLog.getCity().getEleCode()) && !AppContentGmcc.CITY_PROVINCE_CODE.equals(businessLog.getCity().getEleCode())){
			sql.append(" and b.CITYSYSID='"+businessLog.getCity().getEleCode()+"'");
		}
		if(businessLog.getOperatorName()!=null && StringUtil.isValidString(businessLog.getOperatorName().trim())){
			sql.append(" and b.OPERATOR_NAME like '%"+businessLog.getOperatorName().trim()+"%'");
		}
		if(businessLog.getOperatorContent()!=null && StringUtil.isValidString(businessLog.getOperatorContent().trim())){
			sql.append(" and b.OPERATOR_CONTENT like '%"+businessLog.getOperatorContent().trim()+"%'");
		}
		if(businessLog.getLoginIp()!=null && StringUtil.isValidString(businessLog.getLoginIp().trim())){
			sql.append(" and b.LOGINIP like '%"+businessLog.getLoginIp().trim()+"%'");
		}
		sql.append("  order by b.operator_time desc) t where rownum<=?) where rn>=?");
		System.out.println(sql.toString());
		List list=this.getJdbcTemplate().queryForList(sql.toString(), args);
		return list;
	}
	public Long getLogCount(BusinessLog businessLog) throws Exception{
		// TODO Auto-generated method stub
		String formatStr="yyyy-MM-dd";
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) "+
				" from tb_app_business_log b,tb_app_element e,tb_app_element_group eg,tb_app_element et" +
				" where eg.id=e.ELE_GROUP_ID " +
				" and eg.ELE_GROUP_NAME='"+AppContentGmcc.CITY_GROUPNAME+"'"+
				" and b.CITYSYSID=e.ele_code "+
				" and et.id=b.type ");
		if(businessLog.getType()!=null && StringUtil.isValidString(businessLog.getType().getEleCode())){
			sql.append(" and et.ele_code='"+businessLog.getType().getEleCode()+"'") ;
		}
		if(businessLog.getOperatorTimeBegin()!=null && businessLog.getOperatorTimeEnd()==null){
			sql.append(" AND to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss') >= ('"+ 
					DateUtil.dateToStr(businessLog.getOperatorTimeBegin(), formatStr)+" 00:00:00')") ;
		}
		if(businessLog.getOperatorTimeBegin()!=null && businessLog.getOperatorTimeEnd()!=null){
			sql.append(" AND to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss') >= ('"+ 
					DateUtil.dateToStr(businessLog.getOperatorTimeBegin(), formatStr)+" 00:00:00')"+
					" AND to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss') <= ('"+ 
					DateUtil.dateToStr(businessLog.getOperatorTimeEnd(), formatStr)+" 23:59:59')") ;
		}
		if(businessLog.getOperatorTimeBegin()==null && businessLog.getOperatorTimeEnd()!=null){
			sql.append(" AND to_char(b.OPERATOR_TIME,'yyyy-MM-dd HH24:mi:ss') <= ('"+ 
					DateUtil.dateToStr(businessLog.getOperatorTimeEnd(), formatStr)+" 23:59:59')") ;
		}
		if(StringUtil.isValidString(businessLog.getCity().getEleCode()) && !AppContentGmcc.CITY_PROVINCE_CODE.equals(businessLog.getCity().getEleCode())){
			sql.append(" and b.CITYSYSID='"+businessLog.getCity().getEleCode()+"'");
		}
		if(businessLog.getOperatorName()!=null && StringUtil.isValidString(businessLog.getOperatorName().trim())){
			sql.append(" and b.OPERATOR_NAME like '%"+businessLog.getOperatorName().trim()+"%'");
		}
		if(businessLog.getOperatorContent()!=null && StringUtil.isValidString(businessLog.getOperatorContent().trim())){
			sql.append(" and b.OPERATOR_CONTENT like '%"+businessLog.getOperatorContent().trim()+"%'");
		}
		if(businessLog.getLoginIp()!=null && StringUtil.isValidString(businessLog.getLoginIp().trim())){
			sql.append(" and b.LOGINIP like '%"+businessLog.getLoginIp().trim()+"%'");
		}
		System.out.println(sql.toString());
		return this.getJdbcTemplate().queryForLong(sql.toString());
	}

}
