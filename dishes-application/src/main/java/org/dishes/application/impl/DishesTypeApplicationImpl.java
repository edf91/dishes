package org.dishes.application.impl;

import java.util.List;

import javax.inject.Named;

import org.dishes.application.DishesTypeApplication;
import org.dishes.domain.DishType;
/**
 * 分类应用实现
 */
@Named
public class DishesTypeApplicationImpl implements DishesTypeApplication{

	public boolean isNameExists(String name) {
		return DishType.isNameExists(name);
	}

	public void createDishesType(DishType dishesType) {
		dishesType.save();
	}

	public List<DishType> listDisthType() {
		return DishType.findAllEntities();
	}

	public DishType getDishTypeById(String dishId) {
		return DishType.get(DishType.class, dishId);
	}

	public void deletDishTypeById(String dishId) {
		getDishTypeById(dishId).delete();
	}

	public List<DishType> listAvailableDishType() {
		return DishType.findAllAvailableDishType();
	}

}
