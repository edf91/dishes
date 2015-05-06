package org.dishes.facade.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dishes.application.StatisticsApplication;
import org.dishes.commons.InvokeResult;
import org.dishes.facade.StatisticsFacade;
import org.dishes.facade.assembler.StatisAssembler;
import org.dishes.facade.dto.DishTopDTO;
import org.dishes.facade.dto.NearDayStatisDTO;
@Named
public class StatisticsFacadeImpl implements StatisticsFacade {
	@Inject
	private StatisticsApplication statisticsApplication;
	
	public InvokeResult<NearDayStatisDTO> nearDayStatis(int nearDay) {
		
		return InvokeResult.success(StatisAssembler.toDTO(statisticsApplication.nearDayStatis(nearDay)));
	}

	public InvokeResult<List<DishTopDTO>> getTopDish(int topNum) {
		
		return InvokeResult.success(StatisAssembler.toTopDTOs(statisticsApplication.getTopDish(topNum)));
	}

}
