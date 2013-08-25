package com.gmcc.dao.hibernate;

import java.util.List;

import com.gmcc.dao.AppLockDao;
import com.gmcc.model.AppLock;
import com.ibm.dao.hibernate.GenericDAOHibernate;

public class AppLockDaoHibernate extends  GenericDAOHibernate<AppLock, Long>  implements
	AppLockDao {

	public AppLockDaoHibernate() {
		super(AppLock.class);
	}

	public AppLock getAppLockByCode(String code) {
		
		AppLock relust=null;
		
		String hql=" From AppLock t where t.code=?";
		List list=this.getHibernateTemplate().find(hql,code);
		this.getHibernateTemplate().clear();
		
		if(list!=null || list.size() >0){
			relust=(AppLock)list.get(0);
		}
		
		return relust;
	}
	
}
