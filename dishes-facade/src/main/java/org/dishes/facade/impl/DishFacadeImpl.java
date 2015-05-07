package org.dishes.facade.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dishes.application.DishApplication;
import org.dishes.commons.ConstantsValue;
import org.dishes.commons.InvokeResult;
import org.dishes.domain.Dish;
import org.dishes.facade.DishFacade;
import org.dishes.facade.assembler.DishAssembler;
import org.dishes.facade.command.CreateDishCommand;
import org.dishes.facade.dto.DishDTO;
@Named
public class DishFacadeImpl implements DishFacade {
	@Inject
	private DishApplication dishApplication;
	public InvokeResult<String> add(CreateDishCommand command) {
		try {
			dishApplication.addDish(DishAssembler.toEntity(command));
			return InvokeResult.success("菜谱添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_DISH_CODE,"菜谱添加失败");
		}
	}
	/**
	 * 更新，先将原来的disabled，再添加
	 */
	public InvokeResult<String> update(CreateDishCommand command) {
		try {
			dishApplication.disabledDishById(command.getId());
			dishApplication.addDish(DishAssembler.toEntity(command));
			return InvokeResult.success("更新菜谱成功");
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_DISH_CODE,"更新菜谱失败");
		}
	}

	public InvokeResult<List<DishDTO>> listDish() {
		return InvokeResult.success(DishAssembler.toDTOs(dishApplication.listDish()));
	}

	public InvokeResult<String> deleteDishById(String id) {
		Dish dish = dishApplication.getDishById(id);
		if(dish == null) return InvokeResult.failure(ConstantsValue.ERROR_DISH_CODE,"该菜谱不存在");
		dishApplication.deleteDishById(id);
		return InvokeResult.success("删除成功");
	}

	public InvokeResult<List<DishDTO>> listAllAvailable() {
		return InvokeResult.success(DishAssembler.toDTOsForActivity(dishApplication.listAllAvailableDish()));
	}

	public InvokeResult<DishDTO> getDishById(String id) {
		return InvokeResult.success(DishAssembler.toDTO(dishApplication.getDishById(id)));
	}

}
