package org.dishes.facade.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dishes.application.DishesTypeApplication;
import org.dishes.commons.ConstantsValue;
import org.dishes.commons.InvokeResult;
import org.dishes.facade.DishesTypeFacade;
import org.dishes.facade.assembler.DishesTypeAssembler;
import org.dishes.facade.command.CreateDishesTypeCommand;
import org.dishes.facade.command.DishTypeUpdateCommand;
import org.dishes.facade.dto.DishTypeDTO;
/**
 * 分类控制器实现类
 */
@Named
public class DishesTypeFacadeImpl implements DishesTypeFacade{
	@Inject
	private DishesTypeApplication dishesTypeApplication;
	/**
	 * 创建分类
	 */
	public InvokeResult<String> createDishesType(CreateDishesTypeCommand command) {
		if(dishesTypeApplication.isNameExists(command.getName())) return InvokeResult.failure(ConstantsValue.ERROR_DISHES_TYPE_CODE,"名称已经存在");
		dishesTypeApplication.createDishesType(DishesTypeAssembler.toEntity(command));
		return InvokeResult.success("添加成功");
	}
	/**
	 * 分类列表
	 */
	public InvokeResult<List<DishTypeDTO>> listDishType() {
		return InvokeResult.success(DishesTypeAssembler.toDTOs(dishesTypeApplication.listDisthType()));
	}
	public InvokeResult<String> deleteDishTypeById(String dishId) {
		DishTypeDTO dto = DishesTypeAssembler.toDTO(dishesTypeApplication.getDishTypeById(dishId));
		if(dto == null) return InvokeResult.failure(ConstantsValue.ERROR_DISHES_TYPE_CODE,"分类不存在");
		dishesTypeApplication.deletDishTypeById(dishId);
		return InvokeResult.success("删除成功");
	}
	public InvokeResult<DishTypeDTO> getDishTypeById(String dishId) {
		DishTypeDTO dto = DishesTypeAssembler.toDTO(dishesTypeApplication.getDishTypeById(dishId));
		if(dto == null) return InvokeResult.failure(ConstantsValue.ERROR_DISHES_TYPE_CODE,"分类不存在");
		return InvokeResult.success(dto);
	}
	public InvokeResult<List<DishTypeDTO>> listAvailableDishType() {
		return InvokeResult.success(DishesTypeAssembler.toDTOs(dishesTypeApplication.listAvailableDishType()));
	}
	
	public InvokeResult<String> updateById(DishTypeUpdateCommand command) {
//		if(dishesTypeApplication.isNameExists(command.getName())) return InvokeResult.failure(ConstantsValue.ERROR_DISHES_TYPE_CODE,"名称已经存在");
		dishesTypeApplication.updateDishType(DishesTypeAssembler.toUpdateEntity(command));
		return InvokeResult.success("更新分类成功");
	}


}
