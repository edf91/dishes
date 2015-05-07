package org.dishes.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统用户，对应员工
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity{
	
	public static final String INIT_PASSWORD = "888888"; // 初始密码
	private static final String USER_ADMIN = "ADMIN_USER";
	private static final String USER_CASHIER = "CASHIER_USER";
	private static final String USER_WAITER = "WAITER_USER";
	private static final long serialVersionUID = -8898252061681247502L;
	
	@Column(name = "name")
	private String name; // 名称
	@Column(name = "user_account")
	private String userAccount; // 账号
	
	@Column(name = "password")
	private String password; // 密码
	
	@Column(name = "tel_phone")
	private String  telPhone; // 联系方式
	
	@Column(name = "type")
	private String type; // 用户类型
	
	@Column(name = "disabled")
	private boolean disabled; // 是否无效，如果无效无法登陆
	
	public User() {
		this.password = INIT_PASSWORD;
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

	public User doLogin() {
		String hql = "FROM User u WHERE u.userAccount = ? AND u.password = ?";
		List<User> result = findByHQL(hql, this.getUserAccount(),this.getPassword());
		if(result.isEmpty()) return null;
		return result.get(0);
	}
	public void resetPassword() {
		this.password = INIT_PASSWORD;
		this.update();
	}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
}
