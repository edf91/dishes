package org.dishes.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.dishes.commons.ConstantsValue;
import org.dishes.commons.InvokeResult;
import org.dishes.facade.UserFacade;
import org.dishes.facade.command.UpdatePasswordCommand;
import org.dishes.facade.command.UserLoginCommand;
import org.dishes.facade.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登陆控制器
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	@Inject
	private UserFacade userFacade;
	
	@ResponseBody
	@RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
	public InvokeResult<String> updatePassword(UpdatePasswordCommand command,HttpSession session){
		SessionUser user = (SessionUser) session.getAttribute(ConstantsValue.USER_SESSION_NAME);
		if(user == null) return InvokeResult.failure(ConstantsValue.ERROR_USER_CODE,"用户不存在");
		InvokeResult<String> result = userFacade.updatePassword(user.getId(),command);
		if(!result.isHasError()) session.invalidate();
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/logout",method = RequestMethod.POST)
	public InvokeResult<String> logout(HttpSession session){
		session.invalidate();
		return InvokeResult.success("退出系统成功");
	}
	@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public InvokeResult<SessionUser> login(UserLoginCommand command,HttpSession session){
		InvokeResult<SessionUser> result = null;
		String checkNum = (String) session.getAttribute(ConstantsValue.CHECK_NUM_NAME_SESSION);
		if(checkNum != null && command.getCheckNum() != null && command.getCheckNum().toLowerCase().equals(checkNum.toLowerCase())){
			result = userFacade.doLogin(command);
			if(!result.isHasError()){
				session.setAttribute(ConstantsValue.USER_SESSION_NAME, result.getData());
			}
		}else{
			result = InvokeResult.failure(ConstantsValue.ERROR_USER_CODE,"验证码错误");
		}
		
		return result;
	}
}
