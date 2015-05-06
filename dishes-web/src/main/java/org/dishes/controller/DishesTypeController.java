package org.dishes.controller;

import java.util.List;

import javax.inject.Inject;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.DishesTypeFacade;
import org.dishes.facade.command.CreateDishesTypeCommand;
import org.dishes.facade.dto.DishTypeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 添加分类控制器
 */
@Controller
@RequestMapping(value = "/dishesType")
public class DishesTypeController {
	@Inject
	private DishesTypeFacade dishesTypeFacade;
	/**
	 * 获取有效的分类
	 */
	@ResponseBody
	@RequestMapping(value = "/listAvailableDishType",method = RequestMethod.POST)
	public InvokeResult<List<DishTypeDTO>> listAvailableDishType(){
		return dishesTypeFacade.listAvailableDishType();
	}
	@ResponseBody
	@RequestMapping(value = "get",method = RequestMethod.POST)
	public InvokeResult<DishTypeDTO> get(String dishId){
		return dishesTypeFacade.getDishTypeById(dishId);
	}
	/**
	 * 删除分类
	 */
	@ResponseBody
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	public InvokeResult<String> delete(String dishId){
		return dishesTypeFacade.deleteDishTypeById(dishId);
	}
	/**
	 * 添加分类
	 */
	@ResponseBody
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public InvokeResult<String> add(CreateDishesTypeCommand command){
		return dishesTypeFacade.createDishesType(command);
	}
	/**
	 * 分类列表
	 */
	@ResponseBody
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	public InvokeResult<List<DishTypeDTO>> list(){
		return dishesTypeFacade.listDishType();
	}
}
