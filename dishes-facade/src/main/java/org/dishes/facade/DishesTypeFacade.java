package org.dishes.facade;

import java.util.List;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.command.CreateDishesTypeCommand;
import org.dishes.facade.command.DishTypeUpdateCommand;
import org.dishes.facade.dto.DishTypeDTO;

/**
 * 分类门面层
 */
public interface DishesTypeFacade {
	/**
	 * 创建分类
	 * @param command
	 * @return
	 */
	public InvokeResult<String> createDishesType(CreateDishesTypeCommand command);
	/**
	 * 分类列表
	 * @return
	 */
	public InvokeResult<List<DishTypeDTO>> listDishType();
	public InvokeResult<String> deleteDishTypeById(String dishId);
	public InvokeResult<DishTypeDTO> getDishTypeById(String dishId);
	public InvokeResult<List<DishTypeDTO>> listAvailableDishType();
	public InvokeResult<String> updateById(DishTypeUpdateCommand command);
}
