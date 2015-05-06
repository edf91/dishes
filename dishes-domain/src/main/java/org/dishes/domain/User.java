package org.dishes.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 系统用户，对应员工
 */
@Entity
public abstract class User extends Actor{
	
	private static final String INIT_PASSWORD = "888888"; // 初始密码
	
	private static final long serialVersionUID = -8898252061681247502L;
	
	@Column(name = "user_account")
	private String userAccount; // 账号
	
	@Column(name = "password")
	private String password; // 密码
	
	@Column(name = "tel_phone")
	private String  telPhone; // 联系方式
	
	@Column(name = "type",insertable = false, updatable = false)
	private String type; // 用户类型,不需要主动插入以及更新，因此只需要提供get方法
	
	@Column(name = "disabled")
	private boolean disabled; // 是否无效，如果无效无法登陆
	
	protected User() {
	}
	
	public User(String userAccount){
		this.userAccount = userAccount;
		this.password = INIT_PASSWORD;
	}
	
	
	/***********************static method start*****************/
	/**
	 * 获取所有的用户
	 */
	public static List<User> findAllEntity() {
		return findByHQL("FROM User");
	}
	/**
	 * 判断账号是否存在
	 * @param userAccount
	 * @return
	 */
	public static boolean isAccountExists(String userAccount) {
		return !findByHQL("FROM User u WHERE u.userAccount = ?", userAccount).isEmpty();
	}

	/***********************static method end*****************/
	
	/***********************public method start*****************/
	/***********************public method end*****************/
	
	/*get/set method*/
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	
}
