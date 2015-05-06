package org.dishes.controller;

import java.util.List;

import javax.inject.Inject;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.DishFacade;
import org.dishes.facade.command.CreateDishCommand;
import org.dishes.facade.dto.DishDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dish")
public class DishController {
	@Inject
	private DishFacade dishFacade;
	
	
	@ResponseBody
	@RequestMapping(value = "/listAvailable",method = RequestMethod.POST) 
	public InvokeResult<List<DishDTO>> listAvailable(){
		return dishFacade.listAllAvailable();
	}
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public InvokeResult<String> delete(String id){
		return dishFacade.deleteDishById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	public InvokeResult<List<DishDTO>> list(){
		return dishFacade.listDish();
	}
	@ResponseBody
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public InvokeResult<String> add(CreateDishCommand command){
		return dishFacade.add(command);
	}
}
