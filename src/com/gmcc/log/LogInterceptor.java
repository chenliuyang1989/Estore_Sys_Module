package com.gmcc.log;

import java.util.Date;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.gmcc.cons.element.Constants;
import com.gmcc.model.BusinessLog;
import com.gmcc.model.User;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.InetUtils;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.BaseBusinessLogAction;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.service.IOperateManager;
import com.ibm.util.ReflectionUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LogInterceptor implements Interceptor {
	private static final long serialVersionUID = 5067790608840428809L;

	private IOperateManager<BusinessLog, Long> businessLogOperateManager;
	
	/**
	 * @return the businessLogOperateManager
	 */
	public IOperateManager<BusinessLog, Long> getBusinessLogOperateManager() {
		return businessLogOperateManager;
	}
	
	/**
	 * @param businessLogOperateManager
	 *            the businessLogOperateManager to set
	 */
	public void setBusinessLogOperateManager(
			IOperateManager<BusinessLog, Long> businessLogOperateManager) {
		this.businessLogOperateManager = businessLogOperateManager;
		this.businessLogOperateManager.setEntityClass(BusinessLog.class);
		this.businessLogOperateManager.setPKClass(Long.class);
	}
	
	/**
	 * Intercept the action invocation and check to log web action method
	 * running
	 * 
	 * @param invocation
	 *            the current action invocation
	 * @return the method's return value
	 * @throws Exception
	 *             when setting the error on the response fails
	 */
	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {
		Object obj = invocation.getAction();
        String methodName=invocation.getProxy().getMethod();
        String result = invocation.invoke();
        
        SecurityContext ctx = SecurityContextHolder.getContext();
        if(ctx.getAuthentication()!=null&&methodName!=null){
            User user = SpringSecurityUtils.getCurrentUser();
	        String subClass = obj.getClass().getSuperclass().getName();
	        if(AppContentGmcc.BUSINESS_LOG_ACTION.equals(subClass)){
	        	BaseBusinessLogAction baseAction=(BaseBusinessLogAction)obj;
	        	String objectType = ReflectionUtils.getObjectLogBusinessType(obj.getClass(),methodName);
	        	String objectKey= ReflectionUtils.getObjectLogBusinessKey(obj.getClass(),methodName);
	        	String methodKey= ReflectionUtils.getMethodLogBusinessKey(obj.getClass(),methodName);	        	
	        	if(methodKey!=null && objectKey!=null){
//	        		if(baseAction.getOperatorPK()!=null){
	        			String methodMessage=methodKey;
		        		String objectMessage=objectKey;
			        	BusinessLog businessLog = new BusinessLog();
			        	businessLog.setType(baseAction.getItemsOfElement(Constants.ELE_TYPE_LOG, objectType));
			        	businessLog.setOperatorTime(new Date());
			        	businessLog.setOperatorName(user.getUsername());
			        	businessLog.setOperatorId(baseAction.getOperatorPK());
			        	businessLog.setOperatorObjectKey(objectMessage);
			        	businessLog.setOperatorMethodKey(methodMessage);
			        	businessLog.setOperatorContent(baseAction.getOperatorContent());
			        	String cityCode=user.getCitySysID();
			        	if(cityCode!=null && !"".equals(cityCode))
			        		businessLog.setCity(baseAction.getItemsOfElement(AppContentGmcc.CITY_GROUPNAME, cityCode));
//			        	businessLog.setCity(city);
			        	businessLog.setLoginIp(InetUtils.getRemortIP());
			        	businessLogOperateManager.save(businessLog);
//	        		}
	        	}
	        }else if(AppContentGmcc.DISPLAY_QUERY_ACTION.equals(subClass)){
	        	DisplayTagQueryAction baseAction=(DisplayTagQueryAction)obj;
	        	String objectType = ReflectionUtils.getObjectLogBusinessType(obj.getClass(),methodName);
	        	String objectKey= ReflectionUtils.getObjectLogBusinessKey(obj.getClass(),methodName);
	        	String methodKey= ReflectionUtils.getMethodLogBusinessKey(obj.getClass(),methodName);	        	
	        	if(methodKey!=null && objectKey!=null){
//	        		if(baseAction.getOperatorPK()!=null){
	        			String methodMessage=methodKey;
		        		String objectMessage=objectKey;
			        	BusinessLog businessLog = new BusinessLog();
			        	businessLog.setType(baseAction.getItemsOfElement(Constants.ELE_TYPE_LOG, objectType));
			        	businessLog.setOperatorTime(new Date());
			        	businessLog.setOperatorName(user.getUsername());
			        	businessLog.setOperatorId(baseAction.getOperatorPK());
			        	businessLog.setOperatorObjectKey(objectMessage);
			        	businessLog.setOperatorMethodKey(methodMessage);
			        	businessLog.setOperatorContent(baseAction.getOperatorContent());
			        	String cityCode=user.getCitySysID();
			        	if(cityCode!=null && !"".equals(cityCode))
			        		businessLog.setCity(baseAction.getItemsOfElement(AppContentGmcc.CITY_GROUPNAME, cityCode));
			        	businessLog.setLoginIp(InetUtils.getRemortIP());
			        	businessLogOperateManager.save(businessLog);
//	        		}
	        	}
	        }
        }
        return result;
    }

	public void destroy() {
	}

	public void init() {
	}

	
}
