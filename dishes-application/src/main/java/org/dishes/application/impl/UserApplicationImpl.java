package org.dishes.application.impl;

import java.util.List;

import javax.inject.Named;

import org.dishes.application.UserApplication;
import org.dishes.domain.User;

/**
 * 用户应用实现
 */
@Named
public class UserApplicationImpl implements UserApplication {
	public List<User> listUser() {
		return User.findAllEntity();
	}
	// 添加用户
	public void createUser(User user) {
		user.save();
	}
	// 判断账号是否已经存在
	public boolean isAccountExists(String userAccount) {
		return User.isAccountExists(userAccount);
	}
	// 删除用户
	public void deleteUser(String userId) {
		User.get(User.class, userId).delete();;
	}
	// 根据id获取用户
	public User getUserById(String userId) {
		return User.get(User.class, userId);
	}
	
}
