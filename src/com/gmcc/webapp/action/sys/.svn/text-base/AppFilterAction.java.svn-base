package com.gmcc.webapp.action.sys;

import java.util.ArrayList;
import java.util.List;

import com.gmcc.dto.MenuDTO;
import com.gmcc.model.Authority;
import com.gmcc.service.AuthorityManager;
import com.gmcc.sso.TokenUtils;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.BaseAction;
import com.ibm.util.StringUtil;

@SuppressWarnings("rawtypes")
public class AppFilterAction extends BaseAction {
	private static final long serialVersionUID = -2852584205064256304L;
	
	private AuthorityManager authorityManager;
	
	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}
	private List<MenuDTO> appList;
	public List<MenuDTO> getAppList() {
		return appList;
	}

	public String execute() {
        try{
        	boolean isExternalUser = true;
        	String username = SpringSecurityUtils.getCurrentUserName();
        	if(!StringUtil.isValidString(username)){
        		System.out.println("AppFilterAction->Can not get User");
        		return "faild";
        	}
        	
        	TokenUtils tu = new TokenUtils();
        	String netUrl = tu.getParamsByCode(TokenUtils.ESTORE_NET_URL).getValue();
        	String tokenWebApp = tu.getParamsByCode(TokenUtils.TOKEN_VALIDATE_URL).getValue();
        	
        	tokenWebApp = tokenWebApp.substring(0,tokenWebApp.lastIndexOf("/"));
        	tokenWebApp = tokenWebApp.substring(tokenWebApp.lastIndexOf("/"));
        	String contextPath = this.getRequest().getContextPath();
        	contextPath = contextPath != null && contextPath.length() > 0 ? contextPath : "/";
        	if(contextPath.equals(tokenWebApp)){
        		isExternalUser = false;
        	}
        	
    		List<Authority> appAuthorityList = this.authorityManager.getAllAppAuthoritys();
	        List<Authority> appUserAuthorityList = this.authorityManager.getAllAppAuthoritys(username);
    		String traget = "";
    		appList = new ArrayList<MenuDTO>();	
        	for(Authority au : appAuthorityList){
	        	MenuDTO dto = new MenuDTO();
	        	StringBuffer bf = new StringBuffer();
	        	if(appUserAuthorityList!=null && appUserAuthorityList.contains(au)){
	        		if(TokenUtils.traget_代客下单.equals(au.getMenuName()) 
	        				|| TokenUtils.traget_运营分析.equals(au.getMenuName())){
	        			traget = "_blank";
	        		}else{
	        			traget = "";
	        		}
	        		
	        		if(isExternalUser && !au.getMenuApp().contains(contextPath)){
        				bf.append("<li class=\"menu"+au.getMenuPosition()+"c\"></li>");
        			}else{
        				String menuApp = au.getMenuApp();
        				if(isExternalUser){
        					menuApp = netUrl + menuApp.substring(menuApp.lastIndexOf("/"));
    	        		}
		        		bf.append("<li class=\"menu"+au.getMenuPosition()+"\">");
		        		if(StringUtil.isValidString(traget)){
		        			String urlParameter = menuApp+au.getMenuUrl()+"?"+TokenUtils.APP_PARAMETER+"="+au.getId()+"&defalutUser="+username;
		        			bf.append("<a id=\"app_"+au.getId()+"\" href=\""+urlParameter+"\" target=\""+traget+"\"></a>");
		        		}else{
		        			String urlParameter = "parent.changeMenu('"+contextPath+"/system/menuDisplay.do?"+TokenUtils.APP_PARAMETER+"="+au.getId()+"&defalutUser="+username+"')";
		        			bf.append("<a id=\"app_"+au.getId()+"\" href=\"#\" onclick=\""+urlParameter+"\"></a>");
		        		}
		        		bf.append("</li>");
        			}
	        	}else{
	        		bf.append("<li class=\"menu"+au.getMenuPosition()+"c\"></li>");
	        	}
	        	String ret = bf.toString();
	        	dto.setRootMenu(ret);
	        	appList.add(dto);
        	}
        }catch(Exception e){
	    	e.printStackTrace();
	    }
		return SUCCESS;
	}
	
}
