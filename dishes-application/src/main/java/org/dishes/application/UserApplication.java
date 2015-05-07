package org.dishes.application;

import java.util.List;

import org.dishes.domain.User;


/**
 * 用户应用
 */
public interface UserApplication {
	/**
	 * 用户列表
	 * @return
	 */
	List<User> listUser();
	/**
	 * 创建用户
	 * @param user
	 */
	void createUser(User user);
	/**
	 * 检查账号是否已经存在
	 * @return
	 */
	boolean isAccountExists(String userAccount);
	/**
	 * 删除用户
	 * @param userId
	 */
	void deleteUser(String userId);
	/**
	 * 根据id获取用户
	 * @param userId
	 * @return
	 */
	User getUserById(String userId);
	User doLogin(User user);
}
