package com.gmcc.webapp.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmcc.model.Authority;
import com.gmcc.service.AuthorityManager;
import com.gmcc.service.ParamsManager;
import com.gmcc.sso.TokenUtils;

public class UrlFilter implements Filter {

	private ParamsManager paramsManager;
	private AuthorityManager authorityManager;
	
	public ParamsManager getParamsManager() {
		return paramsManager;
	}

	public void setParamsManager(ParamsManager paramsManager) {
		this.paramsManager = paramsManager;
	}

	public AuthorityManager getAuthorityManager() {
		return authorityManager;
	}

	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
//		 HttpSession session = request.getSession();
		try {
//			TokenUtils tu = new TokenUtils();
			boolean flag1 = false;
			boolean flag2 = false;
//			String tokenWebApp = tu.getParamsByCode(TokenUtils.TOKEN_LOGIN_URL).getValue();
			String tokenWebApp = paramsManager.getParamsByCode(TokenUtils.TOKEN_LOGIN_URL).getValue();
//			System.out.println("tokenWebApp**************:"+tokenWebApp);
			String defalutUser=request.getParameter("defalutUser");
	    	if(defalutUser!=null){
	    		request.getSession().setAttribute("defalutUser", defalutUser);
	    	}else{
	    		defalutUser=(String) request.getSession().getAttribute("defalutUser");
	    	}
			
			if (defalutUser == null || defalutUser.equals("")) {
				request.getRequestDispatcher("login.jsp?loginUrl="+tokenWebApp).forward(request, response);
//				response.sendRedirect(tokenWebApp);
//				return;
			}
//			AuthorityManager authorityManager = (AuthorityManager) AppUtil
//					.getBean("authorityManager");
			List<Authority> appUserAuthorityList = authorityManager
					.getAllAppAuthoritys(defalutUser);
			if (appUserAuthorityList != null && appUserAuthorityList.size() > 0) {
				String msAuthority=paramsManager.getParamsByCode(TokenUtils.ESTORE_MS_AUTHORITY).getValue();
//				System.out.println("msAuthority**************:"+msAuthority);
				for (Authority au : appUserAuthorityList) {
					String appUrl = au.getMenuApp();
//					if(msAuthority.indexOf(appUrl)>-1){
					if (appUrl.indexOf(msAuthority)>-1) {
						flag1 = true;
						break;
					}
				}
			}
			String cookieName = TokenUtils.TOKEN_PARAMETER_KEY + "_"
					+ defalutUser;

			Cookie[] cookies = request.getCookies();
			if ((cookies != null) && (cookies.length > 0)) {
				for (int i = cookies.length - 1; i >= 0; i--) {
					if (cookieName.equals(cookies[i].getName())) {
						flag2 = true;
						break;
					}
				}
			}
			if (!flag1 || !flag2) {
//				PrintWriter out =response.getWriter();
//				out.print("window.location.href='"+tokenWebApp+"';  </script>");
				request.getRequestDispatcher("login.jsp?loginUrl="+tokenWebApp).forward(request, response);
//				response.sendRedirect(tokenWebApp);
//				return;
//				out.close();
				return ;
			}else{
				chain.doFilter(req, resp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		
//		return ;
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
