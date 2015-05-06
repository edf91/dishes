package org.dishes.application.impl;

import java.util.List;

import javax.inject.Named;

import org.dishes.application.DishApplication;
import org.dishes.domain.Dish;

@Named
public class DishApplicationImpl implements DishApplication {

	public void addDish(Dish dish) {
		dish.save();
	}

	public List<Dish> listDish() {
		return Dish.findAllEntities();
	}

	public Dish getDishById(String id) {
		return Dish.get(Dish.class, id);
	}

	public void deleteDishById(String id) {
		getDishById(id).delete();
	}

	public List<Dish> listAllAvailableDish() {
		return Dish.listAllAvailable();
	}
	
}
