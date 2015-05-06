package org.dishes.facade;

import java.util.List;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.command.CreateUserCommand;
import org.dishes.facade.dto.UserDTO;


/**
 * 用户门面
 */
public interface UserFacade {
	/**
	 * 获取用户列表
	 * @return
	 */
	InvokeResult<List<UserDTO>> listUser();
	/**
	 * 创建用户
	 * @param command
	 * @return
	 */
	InvokeResult<String> createUser(CreateUserCommand command);
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	InvokeResult<String> deleteUser(String userId);
	/**
	 * 根据id获取用户
	 * @param userId
	 * @return
	 */
	InvokeResult<UserDTO> getUserById(String userId);
}
