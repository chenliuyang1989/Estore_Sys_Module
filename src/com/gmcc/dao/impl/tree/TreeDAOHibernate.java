package com.gmcc.dao.impl.tree;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import com.gmcc.dao.tree.TreeDAO;
import com.gmcc.dto.TreeDTO;
import com.ibm.dao.hibernate.GenericDAOHibernate;

public class TreeDAOHibernate extends GenericDAOHibernate<TreeDTO,Long> implements TreeDAO{

	public TreeDAOHibernate() {
		super(TreeDTO.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<TreeDTO> getChildNode(boolean hasParent,Long parentId, String sql,String parentIdColumnName) {
		// TODO Auto-generated method stub
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		
		if(hasParent && parentId!=null){
			sql=sql+" and t."+parentIdColumnName+"="+parentId;
		}else if(hasParent && parentId==null){
			sql=sql+" and nvl(t."+parentIdColumnName+",0)=0";
		}
//		System.out.println("daoSql:"+sql);
		List list=jdbcTemplate.queryForList(sql);
		return list;
	}

	public List<TreeDTO> getChildTable(String sql,Long forkeyId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		sql=sql+forkeyId;
		return jdbcTemplate.queryForList(sql);
	}
	public List<TreeDTO> getChildTable(String sql){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		return jdbcTemplate.queryForList(sql);
	}
	public List<TreeDTO> getObjectList(String sql){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		return jdbcTemplate.queryForList(sql);
	}
//	public boolean hasSubItem(String sql){
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
//		int count=jdbcTemplate.queryForInt(sql);
//		if(count>0){
//			return true;
//		}
//		return false;
//	}
//	public 
//	public TreeDTO getSelf(String id, String entityName) {
//		// TODO Auto-generated method stub
//		TreeDTO dto=null;
//		hql=hql+id;
//		List<TreeDTO> listTmp=getHibernateTemplate().find(hql);
//		if(listTmp!=null && listTmp.size()>0){
//			dto=listTmp.get(0);
//		}
//		return dto;
//	}

//	public boolean is_leaf(String id, String entityName) {
//		// TODO Auto-generated method stub
//		boolean flag=true;
//		TreeDTO dto=null;
//		hql=hql+id;
//		List<TreeDTO> listTmp=getHibernateTemplate().find(hql);
//		if(listTmp!=null && listTmp.size()>0){
//			flag=false;
//		}
//		return flag;
//	}

	
}
