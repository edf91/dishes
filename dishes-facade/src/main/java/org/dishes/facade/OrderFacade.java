package org.dishes.facade;

import java.util.List;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.dto.OrderDetailDTO;

public interface OrderFacade {
	/**
	 * 提交订单
	 * @param boardId 餐桌号码
	 * @param activeIds 活动
	 * @param dishIds 常规
	 * @param remark 备注
	 * @return
	 */
	InvokeResult<OrderDetailDTO> makeOrder(String boardId, String[] activeIds,
			String[] dishIds, String remark);
	/**
	 * 
	 * 追加菜到订单
	 * @param boardId 餐桌号码
	 * @param activeIds 活动
	 * @param dishIds 常规
	 * @param remark 备注
	 * @param orderId 被追加的订单
	 * @return
	 */
	InvokeResult<OrderDetailDTO> makeOrderToAdd(String boardId,
			String[] activeIds, String[] dishIds, String remark, String orderId);
	
	InvokeResult<List<OrderDetailDTO>> listOrder();
	
	InvokeResult<String> pay(String orderId, double realPay);
	InvokeResult<OrderDetailDTO> getOrderDetailById(String orderId);

}
