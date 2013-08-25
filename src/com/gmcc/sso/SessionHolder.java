package com.gmcc.sso;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 会话持有者, 用于查找Session
 *
* @author dp
 *
 */
public class SessionHolder implements HttpSessionListener {

	private static Map<String, HttpSession> map = new ConcurrentHashMap<String, HttpSession>(); 
	private static Log logger = LogFactory.getLog(SessionHolder.class);

	public void sessionCreated(HttpSessionEvent e) {
		if(logger.isDebugEnabled()){
			logger.debug("[SessionHolder] ======== sessionCreated:" + e.getSession().getId());
		}		
		map.put(e.getSession().getId(), e.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent e) {
		if(logger.isDebugEnabled()){
			logger.debug("[SessionHolder] ======== sessionDestroyed:" + e.getSession().getId());
		}
		map.remove(e.getSession().getId());
	}

	public static void refreshSession(String sessionId, HttpSession session) {
		if(logger.isDebugEnabled()){
			logger.debug("[SessionHolder] ======== refresh session["+sessionId+"]");
		}
		map.put(sessionId, session);
	}
	
	public static void removeSession(String sessionId) {
		if(logger.isDebugEnabled()){
			logger.debug("[SessionHolder] ======== sessionRemove:" + sessionId);
		}
		map.remove(sessionId);
	}
	
	public static HttpSession getSession(String sessionId) {
		if(logger.isDebugEnabled()){
			logger.debug("[SessionHolder] ======== all sessions:" + map.size());
		}
		HttpSession session = map.get(sessionId);
		if(logger.isDebugEnabled()){
			logger.debug("[SessionHolder] ======== get session["+sessionId+"]:" + (session == null ? "null" : session.getId()));
		}
		return session;
	}

}
