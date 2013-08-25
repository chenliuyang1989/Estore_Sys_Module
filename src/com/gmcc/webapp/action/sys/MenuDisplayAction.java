package com.gmcc.webapp.action.sys;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;

import org.apache.struts2.ServletActionContext;

import com.gmcc.cons.element.Constants;
import com.gmcc.model.Authority;
import com.gmcc.model.BusinessLog;
import com.gmcc.model.User;
import com.gmcc.service.AuthorityManager;
import com.gmcc.sso.TokenUtils;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.InetUtils;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.BaseAction;
import com.ibm.service.IOperateManager;
import com.ibm.util.StringUtil;

@SuppressWarnings("rawtypes")
public class MenuDisplayAction extends BaseAction {

	private AuthorityManager authorityManager;
//	private IOperateManager<BusinessLog, Long> businessLogOperateManager;
	private static final long serialVersionUID = -2852584205064256304L;

	public String execute() {
		User user = SpringSecurityUtils.getCurrentUser();
//		String appTmp=this.getRequest().getParameter(TokenUtils.APP_PARAMETER);
		if(user!=null && StringUtil.isValidString(user.getUsername())){
			//log
//			if(appTmp==null || "".equals(appTmp)){
//				BusinessLog businessLog = new BusinessLog();
//				businessLog.setType(this.getItemsOfElement(Constants.ELE_TYPE_LOG, Constants.ELE_TYPE_LOG_1));
//				businessLog.setOperatorId(user.getUserId());
//				businessLog.setOperatorName(user.getUsername());
//	        	businessLog.setOperatorTime(new Date());
//	        	businessLog.setOperatorObjectKey("user_login");
//	        	businessLog.setOperatorMethodKey("user_login");
//	        	businessLog.setOperatorContent("user_login");
//	        	String cityCode=user.getCitySysID();
//	        	businessLog.setLoginIp(InetUtils.getRemortIP());
//	        	if(cityCode!=null && !"".equals(cityCode))
//	        		businessLog.setCity(getItemsOfElement(AppContentGmcc.CITY_GROUPNAME, cityCode));
//				try {
//					businessLogOperateManager.save(businessLog);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			
			//动态设置菜单
			ServletContext servletContext = ServletActionContext.getServletContext();
			MenuRepository repository = new MenuRepository();
			MenuRepository defaultRepository = (MenuRepository)servletContext.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
			repository.setDisplayers(defaultRepository.getDisplayers());
	        MenuComponent mc=null;
	        
	        TokenUtils tu = new TokenUtils();
	        String tokenWebApp = "";
			try {
				tokenWebApp = tu.getParamsByCode(TokenUtils.TOKEN_VALIDATE_URL).getValue();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
        	tokenWebApp = tokenWebApp.substring(0,tokenWebApp.lastIndexOf("/"));
        	tokenWebApp = tokenWebApp.substring(tokenWebApp.lastIndexOf("/"));
        	String contextPath = this.getRequest().getContextPath();
        	contextPath = contextPath != null && contextPath.length() > 0 ? contextPath : "/";
        	
	        //从数据库加载菜单信息
	        String app = this.getRequest().getParameter(TokenUtils.APP_PARAMETER);	       
	        List<Authority> topAuthorityList = null;
	        if(!StringUtil.isValidString(app)){
	        	List<Authority> appUserAuthorityList = this.authorityManager.getAllAppAuthoritys(user.getUsername());
	        	if(!appUserAuthorityList.isEmpty()){
	        		for(Authority auth : appUserAuthorityList){
	        			if(contextPath.equals(tokenWebApp)){
	        				app = String.valueOf(auth.getId());
	        				break;
	        			}else{
	        				if(auth.getMenuApp().indexOf(contextPath)!=-1){
		        				app = String.valueOf(auth.getId());
		        				break;
		        			}
	        			}
	        		}
	        	}else{
		        	Authority tempAuth = this.authorityManager.getAuthoritysByName(TokenUtils.traget_系统管理);
		        	app = String.valueOf(tempAuth.getId());
	        	}
	        	if(!StringUtil.isValidString(app)){
	        		Authority tempAuth = this.authorityManager.getAuthoritysByName(TokenUtils.traget_系统管理);
		        	app = String.valueOf(tempAuth.getId());
	        	}
	        }

	        topAuthorityList = this.authorityManager.getAllTopAuthoritysByApp(user.getUsername(), app);
	        if(topAuthorityList!=null){
	        	boolean isFirst = true;
	        	for(Authority auth : topAuthorityList){
	        		if(isFirst){
	        			getRequest().setAttribute("DefaultMenu", auth.getId());
	        			isFirst = false;
	        		}
		        	getRequest().setAttribute("topMenu"+auth.getId(), auth.getId());
		        }
		        List<Authority> authorityList = this.authorityManager.getAllAuthoritys(user.getUsername());
		        for(int i=0;i<authorityList.size();i++){
			       Authority authority=authorityList.get(i);
			       if(authority!=null && authority.getIsMenu() && authority.getStatus()){
		            MenuComponent menuComponent = repository.getMenu(authority.getId()+"");
		            if(menuComponent!=null){
		            	mc=menuComponent;
		            	repository.removeMenu(authority.getId()+"");
		            }else{
		            	mc = new MenuComponent();
		            }
		            MenuComponent parentMenu = repository.getMenu(authority.getParentId()+"");
		            if (parentMenu == null) { 
		                parentMenu = new MenuComponent();    
		                parentMenu.setName(authority.getParentId()+"");    
		                repository.addMenu(parentMenu);			                
		            }
		            mc.setParent(parentMenu);
		            mc.setName(authority.getId()+"");
		            mc.setTitle(authority.getMenuName());
		            mc.setTarget("mainFrame");
		            String temp = servletContext.getContextPath();
		            temp = temp != null && temp.length() > 0 ? temp : "/";
		            if(StringUtil.isValidString(authority.getMenuApp()) && authority.getMenuApp().indexOf(temp)==-1){
						mc.setUrl(this.append(authority.getMenuApp()+authority.getMenuUrl(), "jsessionid", TokenUtils.encodeString(user.getUsername())));
		            }else{
		            	mc.setPage(authority.getMenuUrl());
		            }
		            repository.addMenu(mc);
			       }
		        }
	        }
	        servletContext.setAttribute(MenuRepository.MENU_REPOSITORY_KEY, repository);
		}
		return SUCCESS;
	}
	
	private String append(String url, String paraName, String paraValue){
		url = url.trim();
		int i = url.indexOf('?');
		StringBuffer buf = new StringBuffer(url);
		buf.append(i >= 0 ? "&" : "?");
		buf.append(paraName+"=");
		buf.append(paraValue);
		return buf.toString();
	}

	public AuthorityManager getAuthorityManager() {
		return authorityManager;
	}

	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}

//	/**
//	 * @return the businessLogOperateManager
//	 */
//	public IOperateManager<BusinessLog, Long> getBusinessLogOperateManager() {
//		return businessLogOperateManager;
//	}
//
//	/**
//	 * @param businessLogOperateManager the businessLogOperateManager to set
//	 */
//	public void setBusinessLogOperateManager(
//			IOperateManager<BusinessLog, Long> businessLogOperateManager) {
//		this.businessLogOperateManager = businessLogOperateManager;
//	}
	
}
