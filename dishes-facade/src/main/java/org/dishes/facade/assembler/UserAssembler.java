package org.dishes.facade.assembler;

import java.util.ArrayList;
import java.util.List;

import org.dishes.domain.User;
import org.dishes.facade.command.CreateUserCommand;
import org.dishes.facade.command.UserLoginCommand;
import org.dishes.facade.dto.SessionUser;
import org.dishes.facade.dto.UserDTO;

/**
 * 用户装配类
 */
public class UserAssembler {
	/**
	 * 实体转dto不包含密码
	 */
	public static UserDTO toDto(User entity){
		UserDTO result = new UserDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setUserAccount(entity.getUserAccount());
		result.setRoleName(entity.getType());
		result.setDisabled(entity.isDisabled());
		return result;
	}
	/**
	 * 批量装换，不包含密码
	 */
	public static List<UserDTO> toDtos(List<User> entities){
		List<UserDTO> results = new ArrayList<UserDTO>();
		for (User entity : entities) {
			results.add(toDto(entity));
		}
		return results;
	}
	/**
	 * 根据roleName创建特定的user实际类型
	 * @param command
	 * @return
	 */
	public static User toEntity(CreateUserCommand command) {
		User result = new User();
		result.setType(command.getRoleName());
		result.setName(command.getName());
		return result;
	}
	/**
	 * 获取用户详情，排除密码
	 */
	public static UserDTO toDtoDetailNoPassword(User entity) {
		UserDTO result = new UserDTO();
		result.setDisabled(entity.isDisabled());
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setRoleName(entity.getType());
		result.setUserAccount(entity.getUserAccount());
		result.setTelPhone(entity.getTelPhone());
		return result;
	}
	public static User toLoginEntity(UserLoginCommand command) {
		User user = new User();
		user.setPassword(command.getPassword());
		user.setUserAccount(command.getUserAccount());
		return user;
	}
	public static SessionUser toSessionUser(User entity) {
		SessionUser result = new SessionUser();
		result.setId(entity.getId());
		result.setName(entity.getName());
		return result;
	}
}
