package org.dishes.facade.assembler;

import java.util.ArrayList;
import java.util.List;

import org.dishes.domain.DishType;
import org.dishes.facade.command.CreateDishesTypeCommand;
import org.dishes.facade.dto.DishTypeDTO;

/**
 * 分类装配类
 */
public class DishesTypeAssembler {
	
	public static DishType toEntity(CreateDishesTypeCommand dto){
		DishType result = new DishType();
		result.setName(dto.getName());
		return result;
	}
	
	public static DishTypeDTO toDTO(DishType entity){
		DishTypeDTO result = new DishTypeDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setDisabled(entity.isDisabled());
		return result;
	}
	public static List<DishTypeDTO> toDTOs(
			List<DishType> entities) {
		List<DishTypeDTO> results = new ArrayList<DishTypeDTO>();
		for (DishType entity : entities) {
			results.add(toDTO(entity));
		}
		return results;
	}
}
