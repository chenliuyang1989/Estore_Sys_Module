package com.gmcc.log;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.gmcc.util.AppContentGmcc;

public class LogAdvice implements MethodBeforeAdvice {

	private final Log log = LogFactory.getLog(LogAdvice.class);
	
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		SecurityContext ctx = SecurityContextHolder.getContext();
		String methodname=method.getName();
		if(!AppContentGmcc.SERVICE_METHOD_SET_ENTITY_CLASS.equals(methodname)&&
				!AppContentGmcc.SERVICE_METHOD_SET_PK_CLASS.equals(methodname)){
			if (ctx.getAuthentication() != null) {
				if(log.isDebugEnabled()){
					log.debug("doing "	+ target.getClass().getName() + "." + method.getName()+ ".");
				}
			} else {
				if(log.isDebugEnabled()){
					log.debug("running " + target.getClass().getName() + "."+ method.getName() + ".");
				}
			}
		}
	}
}
