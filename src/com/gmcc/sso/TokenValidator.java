package com.gmcc.sso;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.exception.FunctionalException;

/**
 * 令版验证器
 * 
* @author dp
 * 
 */
public class TokenValidator {

	private Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 验证令牌是否有效
	 * @param tokenString 令牌信息串
	 * @throws TokenValidationException
	 */
	public String validate(String tokenString) throws Exception {
		// 前置条件, 验证令牌不为空
		if (tokenString == null)
			throw new FunctionalException("令牌不能为空值!");

		// 创建令牌
		Token token = Token.valueOf(TokenUtils.decodeString(tokenString));

		// 验证令牌是否完整
		if (token.getUserId() == null || token.getSessionId() == null)
			throw new FunctionalException("令牌的 userId:"
					+ token.getUserId() + "sessionId:" + token.getSessionId() + " 不能有空值!");
		
    	try {
    		TokenUtils tu = new TokenUtils();
    		HttpURLConnection conn = null;
        	URL url = new URL(tu.getParamsByCode(TokenUtils.TOKEN_VALIDATE_URL).getValue());
        	conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.addRequestProperty(TokenUtils.TOKEN_PARAMETER_KEY, tokenString);			
			conn.connect();
			int code = conn.getResponseCode();
			if (logger.isDebugEnabled()) {
				logger.debug("remote server response code is:" + code);
				logger.debug("remote server response message is:" + conn.getResponseMessage());
			}
			if (code < 200 || code > 300)
				return null;
			conn.disconnect();
		}catch(Exception e) {
			logger.error(e);
			throw e;
		}
		
		return token.getUserId();
	}
	
	public void logout() throws Exception {
    	try {
    		TokenUtils tu = new TokenUtils();
    		HttpURLConnection conn = null;
        	URL url = new URL(tu.getParamsByCode(TokenUtils.TOKEN_VALIDATE_URL).getValue());
        	conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			int code = conn.getResponseCode();
			if (logger.isDebugEnabled()) {
				logger.debug("remote server response code is:" + code);
				logger.debug("remote server response message is:" + conn.getResponseMessage());
			}
			conn.disconnect();
		}catch(Exception e) {
			logger.error(e);
			throw e;
		}
		
	}

}
