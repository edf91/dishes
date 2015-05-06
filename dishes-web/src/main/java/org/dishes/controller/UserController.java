package org.dishes.controller;

import java.util.List;

import javax.annotation.Resource;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.UserFacade;
import org.dishes.facade.command.CreateUserCommand;
import org.dishes.facade.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Resource
	private UserFacade userFacade;
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public InvokeResult<String> delete(String userId){
		return userFacade.deleteUser(userId);
	}
	/**
	 * 添加用户
	 */
	@ResponseBody
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public InvokeResult<String> add(CreateUserCommand command){
		return userFacade.createUser(command);
	}
	/**
	 * 获取用户列表
	 */
	@ResponseBody
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	public InvokeResult<List<UserDTO>> list(){
		return userFacade.listUser();
	}
	/**
	 * 根据用户ID获取用户
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get",method = RequestMethod.POST)
	public InvokeResult<UserDTO> get(String userId){
		return userFacade.getUserById(userId);
	}
}
