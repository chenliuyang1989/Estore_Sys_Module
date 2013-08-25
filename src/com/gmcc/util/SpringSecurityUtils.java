
package com.gmcc.util;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.UserDetails;

import com.gmcc.model.User;
import com.ibm.util.StringUtil;

/**
 * SpringSecurity的工具类.
 * 
 */
public class SpringSecurityUtils {

	public static String getCurrentUserName() {
		User currentUser = getCurrentUser();
		if(currentUser==null||!StringUtil.isValidString(currentUser.getUsername())){
			return "";
		}
		return currentUser.getUsername();
	}

	public static User getCurrentUser() {
		User currentUser = null;
		SecurityContext ctx = SecurityContextHolder.getContext();
		if(ctx!=null){
			if (ctx.getAuthentication()!=null && ctx.getAuthentication().getPrincipal()!=null && ctx.getAuthentication().getPrincipal() instanceof UserDetails) {
				currentUser = (User) ctx.getAuthentication().getPrincipal();
			} else if (ctx.getAuthentication()!=null && ctx.getAuthentication().getDetails()!=null && ctx.getAuthentication().getDetails() instanceof UserDetails) {
				currentUser = (User) ctx.getAuthentication().getDetails();
			} else if (ctx.getAuthentication()!=null && ctx.getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
				currentUser = new User(ctx.getAuthentication().getPrincipal().toString());
			}
		}
		return currentUser;
	}
	
}