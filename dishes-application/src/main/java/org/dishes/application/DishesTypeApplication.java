package org.dishes.application;

import java.util.List;

import org.dishes.domain.DishType;

/**
 * 分类应用
 */
public interface DishesTypeApplication {
	/**
	 * 名称是否存在
	 * @param name
	 * @return
	 */
	boolean isNameExists(String name);
	/**
	 * 创建分类
	 * @param dishesType
	 */
	void createDishesType(DishType dishesType);
	/**
	 * 分类列表
	 * @return
	 */
	List<DishType> listDisthType();
	DishType getDishTypeById(String dishId);
	void deletDishTypeById(String dishId);
	List<DishType> listAvailableDishType();
}
