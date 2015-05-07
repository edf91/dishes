package org.dishes.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.dishes.commons.ConstantsValue;
import org.dishes.commons.InvokeResult;
import org.dishes.facade.UserFacade;
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
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public InvokeResult<SessionUser> login(UserLoginCommand command,HttpSession session){
		InvokeResult<SessionUser> result = userFacade.doLogin(command);
		if(!result.isHasError()){
			session.setAttribute(ConstantsValue.USER_SESSION_NAME, result.getData());
		}
		return result;
	}
}
