package com.gmcc.sso;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.util.SpringSecurityUtils;

/**
 * URL重写, 加入当前系统令牌
 * @author dp
 */
public class TokenUrlRewriter {

	private static Log logger = LogFactory.getLog(TokenUrlRewriter.class);
	
	/**
	 * 重写URL
	 * @param url 原始URL
	 * @return 加入当前系统令牌的URL
	 * @throws IOException
	 */
	public static String rewrite(String url, String sessionId,String tokenParam) throws IOException {
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter source url: " + url);
		if (url == null)
			throw new java.lang.IllegalArgumentException("url is null!");
		url = url.trim();
		int i = url.indexOf('?');
		StringBuffer buf = new StringBuffer(url);
		buf.append(i >= 0 ? "&" : "?");
		buf.append(tokenParam);
		buf.append("=");
		buf.append(URLEncoder.encode(TokenUtils.encodeString(generate(sessionId).toString()), "UTF-8"));
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter result url: " + buf.toString());
		return buf.toString();
	}

	/**
	 * 通过当前会话生成令牌
	 * @param session 当前会话
	 * @return 令牌
	 */
	public static Token generate(String sessionId) {
		String userName = SpringSecurityUtils.getCurrentUserName();
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter user id: " + userName);
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter session id: " + sessionId == null ? null : sessionId);		
		return new Token(userName, sessionId);
	}
	
	public static Token generate(String sessionId, String userName) {
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter user id: " + userName);
		if (logger.isDebugEnabled())
			logger.debug("TokenUrlRewriter session id: " + sessionId == null ? null : sessionId);		
		return new Token(userName, sessionId);
	}

}
