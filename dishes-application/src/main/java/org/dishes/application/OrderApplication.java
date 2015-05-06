package org.dishes.application;

import java.util.List;

import org.dishes.domain.Order;

public interface OrderApplication {
	
	Order makeOrder(String boardId, String[] activeIds, String[] dishIds,
			String remark);

	Order makeOrderToAdd(String boardId, String[] activeIds, String[] dishIds,
			String remark, String orderId);

	List<Order> listOrder();

	void pay(String orderId, double realPay);

	Order getOrderById(String orderId);


}
