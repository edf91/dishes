package org.dishes.domain.exception;
/**
 * 异常基类
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = 6171727836302809595L;
	
	public BaseException(String errorCode,Throwable cause){
		super(errorCode, cause);
	}
}
