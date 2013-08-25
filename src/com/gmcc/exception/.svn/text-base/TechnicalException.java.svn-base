package com.gmcc.exception;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException,从被Spring声明式事务管理的函数中抛出时会触发事务回滚.
 * 
 */
public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public TechnicalException() {
		super();
	}

	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(Throwable cause) {
		super(cause);
	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}
}
