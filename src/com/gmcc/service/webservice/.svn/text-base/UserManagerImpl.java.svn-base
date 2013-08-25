package com.gmcc.service.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.gmcc.model.User;
import com.gmcc.service.webservice.IUserService;

import com.ibm.service.IOperateManager;


@org.apache.cxf.interceptor.InInterceptors (
		interceptors = {"com.gmcc.webapp.interceptor.WSServerInterceptor",
				"org.apache.cxf.interceptor.LoggingInInterceptor"})
@WebService(serviceName = "UserService", endpointInterface = "com.gmcc.service.webservice.IUserService", name = "UserManagerImpl", targetNamespace = "urn:com.gmcc.service.webservice")
public class UserManagerImpl   implements  IUserService {

	private IOperateManager<User, Long> operateManager;
	
	//Autowired
	@javax.jws.WebMethod()
	public void setOperateManager(IOperateManager<User, Long> operateManager) {
		this.operateManager = operateManager;
		// 初始化

		this.operateManager.setEntityClass(User.class);
		this.operateManager.setPKClass(Long.class);
	}

    /**zz
     * {@inheritDoc}
     */
    @javax.jws.WebMethod()
	public User getUser(@WebParam(name="userId")String userId) {
    	
    	return operateManager.findById(Long.valueOf(userId));
    }

    /**
     * {@inheritDoc}
     */
    @javax.jws.WebMethod()
	public List<User> getUsers() {
    	User user=new User();
    	
        return operateManager.findByExample(user);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @javax.jws.WebMethod()
	public User saveUser(User user) {
    	try{
    		return operateManager.merge(user);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * {@inheritDoc}
     */
    @javax.jws.WebMethod()
	public void removeUser(String userId) {
    	
    }

 
}
