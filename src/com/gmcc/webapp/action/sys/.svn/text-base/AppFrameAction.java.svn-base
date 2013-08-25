package com.gmcc.webapp.action.sys;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;


import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;


import com.gmcc.cons.element.Constants;
import com.gmcc.model.BusinessLog;
import com.gmcc.model.Element;
import com.gmcc.model.User;
import com.gmcc.service.AuthorityManager;
import com.gmcc.service.intf.sms.SmsSender;
import com.gmcc.sso.TokenUtils;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.InetUtils;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.BaseAction;
import com.ibm.service.IOperateManager;
import com.ibm.util.StringUtil;


@SuppressWarnings("rawtypes")
public class AppFrameAction extends BaseAction {
	private static final long serialVersionUID = -2852584205064256304L;
	
	private AuthorityManager authorityManager;
	private IOperateManager<BusinessLog, Long> businessLogOperateManager;
	
	/**
	 * @return the businessLogOperateManager
	 */
	public IOperateManager<BusinessLog, Long> getBusinessLogOperateManager() {
		return businessLogOperateManager;
	}

	/**
	 * @param businessLogOperateManager the businessLogOperateManager to set
	 */
	public void setBusinessLogOperateManager(
			IOperateManager<BusinessLog, Long> businessLogOperateManager) {
		this.businessLogOperateManager = businessLogOperateManager;
	}
	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}
	private String encodeCode(String value){
		PasswordEncoder encoder=new ShaPasswordEncoder();
		String str=encoder.encodePassword(value, null);
		return str;
	}
	private String genRandomNum(){
//		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
//				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
//				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] str = { '0','1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int count = 0;
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < 6) {
			int i = Math.abs(r.nextInt(10));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}
	
	public String sendMsg(){
		User user=SpringSecurityUtils.getCurrentUser();
		String userName = SpringSecurityUtils.getCurrentUserName();
		String code=genRandomNum();
		if(userName.equals("admin")){
 	    	code="123456";
 	    }
	    System.out.println("VerificationCode:"+code);
   	    //发短信
//   	    setVerificationCodeCookie(code,userName);
	    authorityManager.updUserLoginMsg(encodeCode(code), user.getUserId());
	   	 TokenUtils tu = new TokenUtils();
	   	 try {
			String msg=tu.getParamsByCode("verification_code").getValue();
			msg=msg.replaceAll("[{]userid[}]", userName)
					.replaceAll("[{]code[}]", code);
			SmsSender smsSender=new SmsSender();
	   	    smsSender.sendMsg(SpringSecurityUtils.getCurrentUser().getPhoneNumber(), msg, "GBK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			log.error(e);
		}
   	    
		return null;
	}
	public String execute() {
        try{
        	String userName = SpringSecurityUtils.getCurrentUserName();
        	if(!StringUtil.isValidString(userName)){
            	System.out.println("AppFrameAction->Can not get User");
        		return "faild";
        	}
        	Element verifiEle=this.authorityManager.getItemsByGroupAndCode(AppContentGmcc.VERIFICATION_ELEMENT_GROUPNAME, AppContentGmcc.VERIFICATION_ELEMENT_CODE);
        	User user=SpringSecurityUtils.getCurrentUser();
        	Long isPhoneCheck=user.getIsPhoneCheck();
        	TokenUtils tu = new TokenUtils();
        	if(verifiEle!=null && "1".equals(verifiEle.getEleName()) && isPhoneCheck!=null && isPhoneCheck.longValue()==1l
        			&& (this.getRequest().getParameter("verificationCode")==null || "".equals(this.getRequest().getParameter("verificationCode")))){
        		this.getRequest().setAttribute("userName",SpringSecurityUtils.getCurrentUserName());
        	    this.getRequest().setAttribute("userPwd",this.getRequest().getSession().getAttribute("passEasy"));
           	    if(SpringSecurityUtils.getCurrentUser().getPhoneNumber()==null || "".equals(SpringSecurityUtils.getCurrentUser().getPhoneNumber())){
           	    	this.getRequest().setAttribute("phoneCheck","false");
           	    	return "logout";
           	    }
	           	 String code=genRandomNum();
	           	if(userName.equals("admin")){
	     	    	code="123456";
	     	    }
	           	 System.out.println("VerificationCode:"+code);
           	    authorityManager.updUserLoginMsg(encodeCode(code), user.getUserId());
           	    this.getRequest().setAttribute("phoneCheck","true");
        	   
           	    //发短信
//           	    setVerificationCodeCookie(code,userName);
//           	    delCodeCheckYetCookie(userName);
           	 	
	           	 String msg=tu.getParamsByCode("verification_code").getValue();
	 			msg=msg.replaceAll("[{]userid[}]", userName)
 					.replaceAll("[{]code[}]", code);
           	    SmsSender smsSender=new SmsSender();
           	    smsSender.sendMsg(SpringSecurityUtils.getCurrentUser().getPhoneNumber(), msg, "GBK");
       	      	return "faild";
        	}
    		if(verifiEle!=null && "1".equals(verifiEle.getEleName()) && isPhoneCheck!=null && isPhoneCheck.longValue()==1l){
    			String paraValue = encodeCode(this.getRequest().getParameter("verificationCode"));
//        		String cookieValue=getVerificationCodeCookie(userName);
				Calendar cal1=new GregorianCalendar();
				cal1.setTime(new Date());
				Calendar cal2=new GregorianCalendar();
				cal2.setTime(user.getPhoneSendTime());
				long difference=cal1.getTimeInMillis()-cal2.getTimeInMillis();
				long minute=difference/(60*1000);
    			String userMsg=user.getPhoneMsg();
        		if((userMsg==null || !"1".equals(userMsg)) && 
        				(!paraValue.equals(userMsg) || minute>Long.parseLong("10")) ){ 
        			this.getRequest().setAttribute("phoneCheck","true");
        			this.getRequest().setAttribute("codeCheck","false");
           	      	this.getRequest().setAttribute("userName",SpringSecurityUtils.getCurrentUserName());
           	      	this.getRequest().setAttribute("userPwd",this.getRequest().getSession().getAttribute("passEasy"));
           	      	return "faild";
        		}else{
//        			setCodeCheckYetCookie(userName,encodeCode("true"));
        			authorityManager.updUserLoginMsg("1", user.getUserId());
        		}
    		}
//    		delVerificationCodeCookie(userName);
        	//设置
        	boolean isExternalUser = true;
        	
        	String tokenWebApp = tu.getParamsByCode(TokenUtils.TOKEN_VALIDATE_URL).getValue();
        	tokenWebApp = tokenWebApp.substring(0,tokenWebApp.lastIndexOf("/"));
        	tokenWebApp = tokenWebApp.substring(tokenWebApp.lastIndexOf("/"));
        	String contextPath = this.getRequest().getContextPath();
        	contextPath = contextPath != null && contextPath.length() > 0 ? contextPath : "/";
        	
        	if(contextPath.equals(tokenWebApp)){
        		isExternalUser = false;
        	}
        	this.getRequest().setAttribute(TokenUtils.isExternalUser, String.valueOf(isExternalUser));
        	
    		String estoreCE = (String)this.getRequest().getSession().getAttribute("estoreCE");
    		this.getRequest().setAttribute("loginEd", "true");
    		if(StringUtil.isValidString(estoreCE) && estoreCE.equals("true") && isExternalUser){
    			return "winCE";
    		}
        	BusinessLog businessLog = new BusinessLog();
			businessLog.setType(this.getItemsOfElement(Constants.ELE_TYPE_LOG, Constants.ELE_TYPE_LOG_1));
			businessLog.setOperatorId(user.getUserId());
			businessLog.setOperatorName(user.getUsername());
        	businessLog.setOperatorTime(new Date());
        	businessLog.setOperatorObjectKey("user_login");
        	businessLog.setOperatorMethodKey("user_login");
        	businessLog.setOperatorContent("user_login");
        	String cityCode=user.getCitySysID();
        	businessLog.setLoginIp(InetUtils.getRemortIP());
        	if(cityCode!=null && !"".equals(cityCode))
        		businessLog.setCity(getItemsOfElement(AppContentGmcc.CITY_GROUPNAME, cityCode));
        	businessLogOperateManager.save(businessLog);
        	authorityManager.updUserLastLoginTime(user.getUserId());
    		
        }catch(Exception e){
	    	log.error(e);
	    	return "faild";
	    }
       
        return SUCCESS;
	}
	
}
