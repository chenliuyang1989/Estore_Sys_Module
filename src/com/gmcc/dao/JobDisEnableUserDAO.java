package com.gmcc.dao;

import com.gmcc.model.User;
import com.ibm.dao.hibernate.base.IBaseDao;

public interface JobDisEnableUserDAO extends IBaseDao<User, Long> {

    public void updUserDisEnable();
    
    public void updUserPwdErrorNum();
}
