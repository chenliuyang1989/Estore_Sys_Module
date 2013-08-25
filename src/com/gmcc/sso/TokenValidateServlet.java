package com.gmcc.sso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.exception.FunctionalException;
import com.gmcc.model.Authority;
import com.gmcc.service.AuthorityManager;
import com.gmcc.service.jobmanager.JobManager;
import com.ibm.util.AppUtil;
import com.ibm.util.StringUtil;

/**
 * 令牌验证Servlet
 *
* @author dp
 *
 */
public class TokenValidateServlet extends HttpServlet {

	private static final long serialVersionUID = -5781567481020208874L;

	private Log logger = LogFactory.getLog(getClass());

	public void init() throws ServletException {
		super.init();
		//init job
		JobManager jobManager = (JobManager)AppUtil.getBean("jobManager");
		jobManager.initJobStatus();
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String tokenString = req.getHeader(TokenUtils.TOKEN_PARAMETER_KEY);
			if(!StringUtil.isValidString(tokenString)){
				Cookie[] cookies = req.getCookies();
				Token token = null;
				if(cookies!=null){
					for (int i = 0; i < cookies.length; ++i) {
		            	if (TokenUtils.TOKEN_PARAMETER_KEY.equals(cookies[i].getName())){            		
		            		try {
		            			token = Token.valueOf(TokenUtils.decodeString(cookies[i].getValue()));
							} catch (FunctionalException e) {
								logger.error(e);
							}
		            	}
		            }
				}
	            if(token!=null){
	            	AuthorityManager authorityManager = (AuthorityManager)AppUtil.getBean("authorityManager");
					List<Authority> appUserAuthorityList = authorityManager.getAllAppAuthoritys(token.getUserId());
					HashMap<String, String> tempMap = new HashMap<String, String>();
					for(Authority au : appUserAuthorityList){
	    				String appUrl = au.getMenuApp();
	    				appUrl = appUrl.substring(appUrl.lastIndexOf("/"));
	    				if(!tempMap.containsKey(appUrl)){
	    					Cookie cookie = new Cookie(TokenUtils.TOKEN_PARAMETER_KEY, null);
	    		        	cookie.setPath(appUrl);
	    		        	cookie.setMaxAge(0);
	    		        	resp.addCookie(cookie);
	    		        	tempMap.put(appUrl, appUrl);
	    				}
	    			}
					SessionHolder.removeSession(token.getSessionId());
	            }
			}else{
				Token token = Token.valueOf(TokenUtils.decodeString(tokenString));
				HttpSession httpSession = SessionHolder.getSession(token.getSessionId());
				if(httpSession==null){
					throw new FunctionalException("用户未认证或认证过时");
				}
				String currentUserName = (String)httpSession.getAttribute(TokenUtils.TOKEN_USERNAME);
				if(!currentUserName.equals(token.getUserId())){
					throw new FunctionalException("校验用户不一致");
				}
			}
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("TokenValidateServlet===Exception:" + e.getMessage(), e);	
			}					
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
	}
}
