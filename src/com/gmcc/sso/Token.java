package com.gmcc.sso;

import com.gmcc.exception.FunctionalException;

/**
 * 令牌信息
 * @author dp
 */
public final class Token {

	/**
	 * 从字符串格式化构造令牌, 与toString()方法对应.
	 * @param strToken 令牌字符串
	 * @return 令牌
	 */
	public static Token valueOf(String strToken) throws FunctionalException {
		if (strToken == null)
			throw new FunctionalException("token is null!");
		String[] ts = strToken.split("\\|");
		if (ts.length != 2)
			throw new FunctionalException("illegal token!");
		String userId = ts[0];
		if (userId == null || userId.length() == 0)
			throw new FunctionalException("illegal token!");
		String sessionId = ts[1];
		if (sessionId == null || sessionId.length() == 0)
			throw new FunctionalException("illegal token!");
		return new Token(userId, sessionId);
	}

	//  用户ID
	private final String userId;

	// 会话ID
	private final String sessionId;

	/**
	 * 构造令牌
	 * @param userId 用户ID
	 * @param sessionId 会话ID
	 */
	public Token(String userId, String sessionId) {
		this.userId = userId;
		this.sessionId = sessionId;
	}

	/**
	 * 获取用户ID
	 * @return 用户ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 获取会话ID
	 * @return 会话ID
	 */
	public String getSessionId() {
		return sessionId;
	}

	public String toString() {
		return userId + "|" + sessionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
