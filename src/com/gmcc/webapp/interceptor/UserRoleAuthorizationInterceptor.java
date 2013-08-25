package com.gmcc.webapp.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.gmcc.model.User;
import com.gmcc.service.AuthorityManager;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.util.AppContentGmcc;
import com.ibm.util.ReflectionUtils;

public class UserRoleAuthorizationInterceptor implements Interceptor {
	
    private static final long serialVersionUID = 5067790608840427509L;
    private static final Log log = LogFactory.getLog(UserRoleAuthorizationInterceptor.class);
    private AuthorityManager authorityManager;
    private boolean enabledKickOff;

    /**
     * Intercept the action invocation and check to see if the user has the proper role.
     * @param invocation the current action invocation
     * @return the method's return value, or null after setting HttpServletResponse.SC_FORBIDDEN
     * @throws Exception when setting the error on the response fails
     */
    public String intercept(ActionInvocation invocation) throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	Object o= invocation.getAction();
    	String className =o.getClass().getName();
    	String username=SpringSecurityUtils.getCurrentUserName();
    	if(request.getSession().getAttribute("username")==null){
    		request.getSession().setAttribute("username",username);
    	}
    	String forward="";
    	//intercept action methods in packages "com.gmcc.webapp.action" and "com.gmcc.webapp.struts";
    	if((className.startsWith(AppContentGmcc.ACTION_PACKAGE)&&!AppContentGmcc.STRUTS_MENU_ACTION_PACKAGE.equals(className))){
    		HttpServletResponse response = ServletActionContext.getResponse();
    		String methodName=invocation.getProxy().getMethod();
    		String functionKey=ReflectionUtils.getAnnosationFunctionKey(o.getClass(),methodName);
	      
    		//校验是否重复登录，被踢出
    		if(isEnabledKickOff()){
    			checkKickOff(request,username);
    		}
	       
    		//NO_ANNOTATION_FUNCTION_KEY for action go to execute
    		if(AppContentGmcc.NO_ANNOTATION_FUNCTION_KEY.equals(functionKey)){
    			return invocation.invoke();
    		}
	  
    		if(getAuths().contains(functionKey)){
    			forward= invocation.invoke();
    			////用户所拥有的权限变更时，完成最后一个操作即提示并退出到登录页面
    			if(isUpdateddAuth(username)){
    				handleNotAuthorized(request, response,"error=UP_AUTH");
    			}
    			return forward;
    		}else{
    			log.debug(LocalizedTextUtil.findDefaultText("errors.login.forbidden", new Locale("zh_CN"))+"("+functionKey+")"+
    			LocalizedTextUtil.findDefaultText(functionKey, new Locale("zh_CN")));
	    	    handleNotAuthorized(request, response,"error=FORBIDDEN");
	    	    return null;
    		}
       }else{
    	   return invocation.invoke();
       }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private List<String> getAuths() {
    	User u=SpringSecurityUtils.getCurrentUser();
    	GrantedAuthority[] gAuthorities=u.getAuthorities();
    	List<String> as=new ArrayList();
    	for (GrantedAuthority auth : gAuthorities) {
    		as.add(auth.getAuthority());
    	}
		return as;
	}
    /**
     * Handle a request that is not authorized according to this interceptor.
     * Default implementation sends HTTP status code 403 ("forbidden").
     *
     * <p>This method can be overridden to write a custom message, forward or
     * redirect to some error page or login page, or throw a ServletException.
     * 
     * @param request current HTTP request
     * @param response current HTTP response
     * @throws javax.servlet.ServletException if there is an internal error
     * @throws java.io.IOException in case of an I/O error when writing the response
     */
    protected void handleNotAuthorized(HttpServletRequest request,
                                       HttpServletResponse response,String errorMessage)
    throws ServletException, IOException {
       // response.sendError(HttpServletResponse.SC_FORBIDDEN);
    	
    	String url=request.getContextPath()+"/login.jsp?"+errorMessage;
        response.sendRedirect(url);
        
    }
    
    public AuthorityManager getAuthorityManager() {
		return authorityManager;
	}

	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}

	/**
	 *  如果当前账户已经登录则踢出前面登录账户
	 * @param request
	 * @throws Exception
	 */
	private void checkKickOff(HttpServletRequest request,String username) throws Exception{
		
		// String username=SpringSecurityUtils.getCurrentUserName();
		 if(username!=null){
			 String currentSessionId=request.getSession().getId();
			 String sessionIdInDB=authorityManager.getLoginedUserSessionId(username);
			 if(!currentSessionId.equals(sessionIdInDB)){				 
				 HttpServletResponse response = ServletActionContext.getResponse();
				 handleNotAuthorized(request, response,"error=KICK_OFF");
			 }
		 }else{
			 //重新登录
			  throw new UsernameNotFoundException("user not found");
		 }
	}
	/**
	 * 
	 * @param username
	 * @return 在线用户是否有权限变更
	 */
	private boolean isUpdateddAuth(String username){
		boolean isUpdated=true;
		
		isUpdated=authorityManager.isUpdatedAuth(username);
		
		return isUpdated;
		
	}
	
	/**
	 * @return the enabledKickOff
	 */
	public boolean isEnabledKickOff() {
		return enabledKickOff;
	}

	/**
	 * @param enabledKickOff the enabledKickOff to set
	 */
	public void setEnabledKickOff(boolean enabledKickOff) {
		this.enabledKickOff = enabledKickOff;
	}

	/**
     * This method currently does nothing.
     */
    public void destroy() {
    }

    /**
     * This method currently does nothing.
     */
    public void init() {
    }
}
