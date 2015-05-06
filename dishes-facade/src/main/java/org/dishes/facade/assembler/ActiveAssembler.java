package org.dishes.facade.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dishes.domain.Activity;
import org.dishes.domain.Dish;
import org.dishes.facade.command.CreateActiveCommand;
import org.dishes.facade.dto.ActivityDTO;

public class ActiveAssembler {
	
	public static Activity toEntity(CreateActiveCommand dto){
		Activity result = new Activity();
		result.setName(dto.getName());
		result.setDescription(dto.getDescription());
//		result.setOriginalSumPrice(dto.getOriginalSumPrice());
		result.setActivitySumPrice(dto.getActivitySumPrice());
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if(!"".equals(dto.getActivityBeginTime()) && null != dto.getActivityBeginTime()){
				result.setActivityBeginTime(sdf.parse(dto.getActivityBeginTime()).getTime());
			}
			if(!"".equals(dto.getActivityEndTime()) && null != dto.getActivityEndTime()){
				result.setActivityEndTime(sdf.parse(dto.getActivityEndTime()).getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		result.setDisabled(!"on".equals(dto.getDisabled()));
		Set<Dish> dishs = new HashSet<Dish>();
		for (String dishId : dto.getDishIds().split(",")) {
			Dish dish = new Dish();
			dish.setId(dishId);
			dishs.add(dish);
		}
		result.setDishs(dishs);
		return result;
	}
	
	public static ActivityDTO toDTO(Activity entity){
		ActivityDTO result = new ActivityDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setDescription(entity.getDescription());
		result.setOriginalSumPrice(entity.getOriginalSumPrice());
		result.setActivitySumPrice(entity.getActivitySumPrice());
		result.setDisabled(entity.isDisabled());
		for (Dish dish : entity.getDishs()) {
			result.getDishNames().add(dish.getName());
		}
		return result;
	}
	public static List<ActivityDTO> toDTOs(List<Activity> entities) {
		List<ActivityDTO> results = new ArrayList<ActivityDTO>();
		for (Activity entity : entities) {
			results.add(toDTO(entity));
		}
		return results;
	}
}
