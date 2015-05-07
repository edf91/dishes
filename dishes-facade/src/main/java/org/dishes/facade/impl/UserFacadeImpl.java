package org.dishes.facade.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.dishes.application.UserApplication;
import org.dishes.commons.ConstantsValue;
import org.dishes.commons.InvokeResult;
import org.dishes.domain.User;
import org.dishes.facade.UserFacade;
import org.dishes.facade.assembler.UserAssembler;
import org.dishes.facade.command.CreateUserCommand;
import org.dishes.facade.command.UserLoginCommand;
import org.dishes.facade.dto.SessionUser;
import org.dishes.facade.dto.UserDTO;
/**
 * 用户门面层
 */
@Named
public class UserFacadeImpl implements UserFacade{
	@Resource
	private UserApplication userApplication;
	
	// 用户列表
	public InvokeResult<List<UserDTO>> listUser() {
		List<User> users = userApplication.listUser();
		return InvokeResult.success(UserAssembler.toDtos(users));
	}
	// 添加用户
	public InvokeResult<String> createUser(CreateUserCommand command) {
		try {
			User user = UserAssembler.toEntity(command);
			if(userApplication.isAccountExists(command.getUserAccount())) return InvokeResult.failure(ConstantsValue.ERROR_USER_CODE,"账号" + command.getUserAccount() + "已经存在");
			userApplication.createUser(user);
			return InvokeResult.success("创建账号" + command.getUserAccount() + "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_USER_CODE,"创建账号" + command.getUserAccount() + "失败");
		}
		
	}
	// 删除用户
	public InvokeResult<String> deleteUser(String userId) {
		try {
			userApplication.deleteUser(userId);
			return InvokeResult.success("删除用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_USER_CODE,"删除用户失败");
		}
	}
	// 根据id获取用户
	public InvokeResult<UserDTO> getUserById(String userId) {
		try {
			return InvokeResult.success(UserAssembler.toDtoDetailNoPassword(userApplication.getUserById(userId)));
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_USER_CODE,"用户不能存在");
		}
	}
	public InvokeResult<SessionUser> doLogin(UserLoginCommand command) {
		try {
			User user = userApplication.doLogin(UserAssembler.toLoginEntity(command));
			if(user != null){
				return InvokeResult.success(UserAssembler.toSessionUser(user));
			}
			return InvokeResult.failure(ConstantsValue.ERROR_USER_CODE,null);
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_USER_CODE,null);
		}
		
	}
	

}
