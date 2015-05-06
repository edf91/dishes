package org.dishes.application;

import java.util.List;

import org.dishes.domain.statistics.DishTop;
import org.dishes.domain.statistics.NearDayStatis;

public interface StatisticsApplication {

	NearDayStatis nearDayStatis(int nearDay);

	List<DishTop> getTopDish(int topNum);
	
}
