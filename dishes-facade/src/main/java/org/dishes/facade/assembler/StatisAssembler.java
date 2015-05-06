package org.dishes.facade.assembler;

import java.util.ArrayList;
import java.util.List;

import org.dishes.domain.statistics.DishTop;
import org.dishes.domain.statistics.NearDayStatis;
import org.dishes.facade.dto.DishTopDTO;
import org.dishes.facade.dto.NearDayStatisDTO;

public class StatisAssembler {
	public static NearDayStatisDTO toDTO(NearDayStatis entity) {
		NearDayStatisDTO result = new NearDayStatisDTO();
		result.setName(entity.getName());
		result.setNearDay(entity.getNearDay());
		result.setTime(entity.getTimes());
		result.setSalesCount(entity.getSalesCount());
		return result;
	}

	public static DishTopDTO toTopDTO(DishTop entity) {
		DishTopDTO result = new DishTopDTO();
		result.setDishName(entity.getDishName());
		result.setDishType(entity.getDishType());
		result.setNum(entity.getNum());
		result.setScale(entity.getScale());
		result.setSum(entity.getSum());
		return result;
	}

	public static List<DishTopDTO> toTopDTOs(List<DishTop> entities) {
		List<DishTopDTO> results = new ArrayList<DishTopDTO>();
		for (DishTop entity : entities) {
			results.add(toTopDTO(entity));
		}
		return results;
	}

}
