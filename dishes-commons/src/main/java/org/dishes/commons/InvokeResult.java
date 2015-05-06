package org.dishes.commons;


/**
 * 操作返回对象
 * @param <T>
 */
public class InvokeResult<T> {
	private boolean hasError; 
	private String errorCode; // 异常代码
	private String errorMsg; // 异常信息
	private T data; // 返回数据
	
	private InvokeResult(){
		
	}
	private InvokeResult(T data){
		hasError = false;
		this.data = data;
	}
	private InvokeResult(String errorCode){
		hasError = true;
		this.errorCode = errorCode;
	}
	private InvokeResult(String errorCode,String errorMsg){
		this(errorCode);
		this.errorMsg = errorMsg;
	}
	
	public static <T> InvokeResult<T> success(T data){
		return new InvokeResult<T>(data);
	}
	
	public static <T> InvokeResult<T> failure(String errorCode){
		
		return new InvokeResult<T>(errorCode);
	}
	public static <T> InvokeResult<T> failure(String errorCode,String errorMsg){
		return  new InvokeResult<T>(errorCode,errorMsg);
	}
	public static InvokeResult<Void> voidResult(){
		return new InvokeResult<Void>();
	}
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public T getData() {
		return data;
	}
	public boolean isHasError() {
		return hasError;
	}
	
}
