package org.dishes.controller;

import java.util.List;

import javax.inject.Inject;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.OrderFacade;
import org.dishes.facade.dto.OrderDetailDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单控制器
 * create by wxd on 15/05/05
 */
@Controller
@RequestMapping(value="/order")
public class OrderController {
	@Inject
	private OrderFacade orderFacade;
	
	/**
	 * 提交订单或追加餐，根据orderId进行判断
	 * @param boardId 餐桌号码
	 * @param activeIds 活动
	 * @param dishIds 常规
	 * @param remark 备注
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/makeOrder",method = RequestMethod.POST)
	public InvokeResult<OrderDetailDTO> makeOrder(String boardId,String[] activeIds,String[] dishIds,String remark,String orderId){
		if("".equals(orderId))
			return orderFacade.makeOrder(boardId,activeIds,dishIds,remark);
		else
			return orderFacade.makeOrderToAdd(boardId,activeIds,dishIds,remark,orderId);
	}
	@ResponseBody
	@RequestMapping(value = "/get",method = RequestMethod.POST)
	public InvokeResult<OrderDetailDTO> get(String orderId){
		return orderFacade.getOrderDetailById(orderId);
	}
	@ResponseBody
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	public InvokeResult<List<OrderDetailDTO>> list(){
		return orderFacade.listOrder();
	}
	@ResponseBody
	@RequestMapping(value = "/pay",method = RequestMethod.POST)
	public InvokeResult<String> pay(String orderId,double realPay){
		return orderFacade.pay(orderId,realPay);
	}
}
