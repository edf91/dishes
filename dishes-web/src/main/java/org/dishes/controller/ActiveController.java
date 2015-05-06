package org.dishes.controller;

import java.util.List;

import javax.inject.Inject;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.ActiveFacade;
import org.dishes.facade.command.CreateActiveCommand;
import org.dishes.facade.dto.ActivityDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/active")
public class ActiveController {
	@Inject
	private ActiveFacade activeFacade;
	
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public InvokeResult<String> delete(String id){
		return activeFacade.deleteActivityById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	public InvokeResult<List<ActivityDTO>> list(){
		return activeFacade.listActivity();
	}
	
	@ResponseBody
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public InvokeResult<String> createActive(CreateActiveCommand command){
		return activeFacade.createActive(command);
	}
}
