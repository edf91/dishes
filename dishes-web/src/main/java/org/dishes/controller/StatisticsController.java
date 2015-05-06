package org.dishes.controller;

import java.util.List;

import javax.inject.Inject;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.StatisticsFacade;
import org.dishes.facade.dto.DishTopDTO;
import org.dishes.facade.dto.NearDayStatisDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/statis")
public class StatisticsController {
	@Inject
	private StatisticsFacade statisticsFacade;
	/**
	 * 近nearDay的日销售额
	 * @param nearDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/nearDayStatis",method = RequestMethod.POST)
	public InvokeResult<NearDayStatisDTO> nearDayStatis(int nearDay){
		return statisticsFacade.nearDayStatis(nearDay);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTopDish", method = RequestMethod.POST)
	public InvokeResult<List<DishTopDTO>> getTopDish(int topNum){
		return statisticsFacade.getTopDish(topNum);
	}
}
