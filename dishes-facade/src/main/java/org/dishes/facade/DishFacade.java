package org.dishes.facade;

import java.util.List;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.command.CreateDishCommand;
import org.dishes.facade.dto.DishDTO;

public interface DishFacade {
	
	InvokeResult<String> add(CreateDishCommand command);

	InvokeResult<List<DishDTO>> listDish();

	InvokeResult<String> deleteDishById(String id);

	InvokeResult<List<DishDTO>> listAllAvailable();

	InvokeResult<DishDTO> getDishById(String id);

	InvokeResult<String> update(CreateDishCommand command);
}
