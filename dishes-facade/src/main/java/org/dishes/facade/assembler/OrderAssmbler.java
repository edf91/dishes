package org.dishes.facade.assembler;

import java.util.ArrayList;
import java.util.List;

import org.dishes.domain.Activity;
import org.dishes.domain.Dish;
import org.dishes.domain.Order;
import org.dishes.facade.dto.ActivityDTO;
import org.dishes.facade.dto.DishDTO;
import org.dishes.facade.dto.OrderDetailDTO;

public class OrderAssmbler {
	public static OrderDetailDTO toDetailDTO(Order entity) {
		OrderDetailDTO result = new OrderDetailDTO();
		result.setId(entity.getId());
		result.setDisabled(entity.isDisabled());
		result.setRealPay(entity.getRealPrice());
		result.setOrderTime(entity.getOrderTime());
		double bePay = 0.0;
		List<ActivityDTO> activities = new ArrayList<ActivityDTO>();
		for (Activity activity : entity.getActivities()) {
			bePay += activity.getActivitySumPrice();
			activities.add(ActiveAssembler.toDTO(activity));
		}
		List<DishDTO> dishs = new ArrayList<DishDTO>();
		for (Dish dish : entity.getDishes()) {
			bePay += dish.getPrice();
			dishs.add(DishAssembler.toDTO(dish));
		}
		result.setDishes(dishs);
		result.setBePay(bePay);
		result.setActivities(activities);
		result.setBoard(BoardAssembler.toDTO(entity.getBoard()));
		result.setRemark(entity.getRemark());
		return result;
	}
	
	public static OrderDetailDTO toDTO(Order entity){
		OrderDetailDTO result = toDetailDTO(entity);
		return result;
	}
	public static List<OrderDetailDTO> toDTOs(List<Order> entities) {
		List<OrderDetailDTO> results = new ArrayList<OrderDetailDTO>();
		for (Order entity : entities) {
			results.add(toDTO(entity));
		}
		return results;
	}

}
 