package org.dishes.facade.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.dishes.application.ActiveApplication;
import org.dishes.application.DishApplication;
import org.dishes.commons.InvokeResult;
import org.dishes.domain.Activity;
import org.dishes.domain.Dish;
import org.dishes.facade.ActiveFacade;
import org.dishes.facade.assembler.ActiveAssembler;
import org.dishes.facade.command.CreateActiveCommand;
import org.dishes.facade.dto.ActivityDTO;
@Named
public class ActiveFacadeImpl implements ActiveFacade {
	
	@Inject
	private ActiveApplication activeApplication;
	@Inject
	private DishApplication dishApplication;
	
	public InvokeResult<String> deleteActivityById(String id) {
		activeApplication.deleteActivityById(id);
		return InvokeResult.success("删除成功");
	}
	public InvokeResult<List<ActivityDTO>> listActivity() {
		return InvokeResult.success(ActiveAssembler.toDTOs(activeApplication.listActivities()));
	}
	public InvokeResult<String> createActive(CreateActiveCommand command) {
		Activity activity = ActiveAssembler.toEntity(command);
		// 计算原来总价
		calcOriginalSumPrice(activity);
		activeApplication.createActive(activity);
		return InvokeResult.success("添加成功");
	}
	private void calcOriginalSumPrice(Activity activity){
		if(!activity.getDishs().isEmpty()){
			double sum = 0.0;
			Set<Dish> newDish = new HashSet<Dish>();
			for (Dish dish : activity.getDishs()) {
				Dish temp = dishApplication.getDishById(dish.getId());
				newDish.add(temp);
				sum += temp.getPrice();
			}
			activity.setDishs(newDish);
			activity.setOriginalSumPrice(sum);
		}
	}

}
