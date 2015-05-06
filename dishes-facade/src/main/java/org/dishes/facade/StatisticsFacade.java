package org.dishes.facade;

import java.util.List;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.dto.DishTopDTO;
import org.dishes.facade.dto.NearDayStatisDTO;

public interface StatisticsFacade {

	InvokeResult<NearDayStatisDTO> nearDayStatis(int nearDay);

	InvokeResult<List<DishTopDTO>> getTopDish(int topNum);

}
