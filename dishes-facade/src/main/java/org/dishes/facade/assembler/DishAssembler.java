package org.dishes.facade.assembler;

import java.util.ArrayList;
import java.util.List;

import org.dishes.domain.Dish;
import org.dishes.domain.DishType;
import org.dishes.facade.command.CreateDishCommand;
import org.dishes.facade.dto.DishDTO;

public class DishAssembler {
	
	public static Dish toEntity(CreateDishCommand dto){
		Dish result = new Dish();
		result.setName(dto.getName());
		result.setDescription(dto.getDescription());
		result.setPrice(dto.getPrice());
		result.setNum(dto.getNum());
		if("on".equals(dto.getIsActivity())){
			result.setActivity(true);
			result.setActivityPrice(dto.getActivityPrice());
		}
		DishType type = new DishType();
		type.setId(dto.getDishTypeId());
		result.setDishType(type);
		return result;
	}
	
	public static DishDTO toDTO(Dish entity){
		DishDTO result = new DishDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setPrice(entity.getPrice());
		result.setDescription(entity.getDescription());
		result.setDishTypeId(entity.getDishType().getId());
		result.setDishTypeName(entity.getDishType().getName());
		result.setNum(entity.getNum());
		result.setActivity(entity.isActivity());
		result.setActivityPrice(entity.getActivityPrice());
		result.setDisabled(entity.isDisabled());
		return result;
	}
	public static List<DishDTO> toDTOs(List<Dish> listDish) {
		List<DishDTO> results = new ArrayList<DishDTO>();
		for (Dish entity : listDish) {
			results.add(toDTO(entity));
		}
		return results;
	}
	/**
	 * 添加活动所用dto
	 * @param entities
	 * @return
	 */
	public static List<DishDTO> toDTOsForActivity(
			List<Dish> entities) {
		List<DishDTO> results = new ArrayList<DishDTO>();
		for (Dish entity : entities) {
			DishDTO dto = new DishDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setPrice(entity.getPrice());
			results.add(dto);
		}
		return results;
	}
}
