package org.dishes.application;

import java.util.List;

import org.dishes.domain.Dish;

public interface DishApplication {
	void addDish(Dish dish);

	List<Dish> listDish();

	Dish getDishById(String id);

	void deleteDishById(String id);

	List<Dish> listAllAvailableDish();

	void disabledDishById(String id);
}
