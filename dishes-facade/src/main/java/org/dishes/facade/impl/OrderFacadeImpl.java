package org.dishes.facade.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dishes.application.OrderApplication;
import org.dishes.commons.ConstantsValue;
import org.dishes.commons.InvokeResult;
import org.dishes.facade.OrderFacade;
import org.dishes.facade.assembler.OrderAssmbler;
import org.dishes.facade.dto.OrderDetailDTO;

@Named
public class OrderFacadeImpl implements OrderFacade{
	@Inject
	private OrderApplication orderApplication;
	
	public InvokeResult<OrderDetailDTO> makeOrder(String boardId, String[] activeIds,
			String[] dishIds, String remark) {
		try {
			OrderDetailDTO dto = OrderAssmbler.toDetailDTO(orderApplication.makeOrder(boardId,activeIds,dishIds,remark));
			return InvokeResult.success(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_ORDER_CODE,"下订单失败");
		}
	}
	
	public InvokeResult<OrderDetailDTO> makeOrderToAdd(String boardId,
			String[] activeIds, String[] dishIds, String remark, String orderId) {
		try {
			OrderDetailDTO dto = OrderAssmbler.toDetailDTO(orderApplication.makeOrderToAdd(boardId,activeIds,dishIds,remark,orderId));
			return InvokeResult.success(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_ORDER_CODE,"追加菜失败");
		}
	}

	public InvokeResult<List<OrderDetailDTO>> listOrder() {
		return InvokeResult.success(OrderAssmbler.toDTOs(orderApplication.listOrder()));
	}

	public InvokeResult<String> pay(String orderId, double realPay) {
		try {
			orderApplication.pay(orderId,realPay);
			return InvokeResult.success("付款成功");
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_ORDER_CODE,"付款失败");
		}
	}

	public InvokeResult<OrderDetailDTO> getOrderDetailById(String orderId) {
		return InvokeResult.success(OrderAssmbler.toDetailDTO(orderApplication.getOrderById(orderId)));
	}

}
