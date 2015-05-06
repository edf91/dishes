package org.dishes.facade.dto;

import java.io.Serializable;
/**
 * 用户dto
 */
public class UserDTO implements Serializable{

	private static final long serialVersionUID = -9075745547437908607L;
	
	private String id; // 唯一标示
	private String name; // 昵称
	private String userAccount; // 账号
	private String roleName; // 角色名称
	private boolean disabled; // 状态
	private String telPhone; // 联系方式
	
	private String password; // 密码
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
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
	
}
