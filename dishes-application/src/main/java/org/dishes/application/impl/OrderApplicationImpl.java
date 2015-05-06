package org.dishes.application.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.dishes.application.OrderApplication;
import org.dishes.commons.DataUtil;
import org.dishes.domain.Activity;
import org.dishes.domain.Board;
import org.dishes.domain.Dish;
import org.dishes.domain.Order;

@Named
public class OrderApplicationImpl implements OrderApplication{

	public Order makeOrder(String boardId, String[] activeIds,
			String[] dishIds, String remark) {
		Order order = new Order();
		List<Dish> dishs = getDishes(dishIds);
		
		List<Activity> activities = getActivities(activeIds);
		order.setDishes(dishs);
		order.setActivities(activities);
		
		Board board = Board.get(Board.class, boardId);
		dealBoard(board,order.getId());
		order.setBoard(board);
		
		order.setActiveIds(DataUtil.toStr(activeIds));
		order.setDishIds(DataUtil.toStr(dishIds));
		order.setOrderTime(new Date().getTime());
		order.setRemark(remark);

		order.save();
		return order;
	}
	private void dealBoard(Board board,String orderId){
		board.setUse(true);
		board.setLastModifyTime(new Date().getTime());
		board.setLastOrderId(orderId);
	}
	private List<Activity> getActivities(String[] activeIds){
		List<Activity> activities = new ArrayList<Activity>();
		for (String activeId : activeIds) {
			if(activeId.equals("")) continue;
			// 解决懒加载
			Activity re = Activity.get(Activity.class, activeId);
			re.getDishs().size();
			activities.add(re);
		}
		return activities;
	}
	private List<Dish> getDishes(String[] dishIds){
		List<Dish> dishs = new ArrayList<Dish>();
		for (String dishId : dishIds) {
			if(!dishId.equals(""))
				dishs.add(Dish.get(Dish.class, dishId));
		}
		return dishs;
	}
	public Order makeOrderToAdd(String boardId, String[] activeIds,
			String[] dishIds, String remark, String orderId) {
		Order order = Order.get(Order.class, orderId);
		
		Board board = order.getBoard();
		dealBoard(board,orderId);
		String[] oldDishids = DataUtil.toArrayStr(order.getDishIds());
		String[] oldActivies = DataUtil.toArrayStr(order.getActiveIds());
		List<Dish> oldDish = getDishes(oldDishids);
		List<Activity> oldActivities = getActivities(oldActivies);
		
		oldDish.addAll(getDishes(dishIds));
		oldActivities.addAll(getActivities(activeIds));
		
		order.setActivities(oldActivities);
		order.setDishes(oldDish);
		String newDishIds = "";
		String newActiveIds = "";
		if(!"".equals(order.getActiveIds()) && null != order.getActiveIds()){
			newActiveIds = order.getActiveIds() + ",";
		}
		if(!"".equals(order.getDishIds()) && null != order.getDishIds()){
			newDishIds = order.getDishIds() + ",";
		}
		newActiveIds += DataUtil.toStr(activeIds);
		newDishIds += DataUtil.toStr(dishIds);
		
		order.setActiveIds(newActiveIds);
		order.setDishIds(newDishIds);
		order.setLastModifyTime(new Date().getTime());
		if(!"".equals(order.getRemark()) && null != order.getRemark()){
			order.setRemark(order.getRemark() + ";" + remark);
		}else{
			order.setRemark(remark);
		}
		order.update();
		
		return order;
	}
	public List<Order> listOrder() {
		List<Order> results = Order.findAllEntities();
		// 解决懒加载
		for (Order order : results) {
			initGetOrderInfo(order);
		}
		return results;
	}
	/**
	 * 设置order的菜、活动、餐桌值以及解决懒加载
	 * @param order
	 */
	private void initGetOrderInfo(Order order){
		order.getBoard();
		// 获取食品信息
		String[] dishids = DataUtil.toArrayStr(order.getDishIds());
		String[] activies = DataUtil.toArrayStr(order.getActiveIds());
		List<Dish>  dishes= getDishes(dishids);
		List<Activity> activities = getActivities(activies);
		order.setActivities(activities);
		order.setDishes(dishes);
	}
	
	public void pay(String orderId, double realPay) {
		Order order = Order.get(Order.class, orderId);
		order.setRealPrice(realPay);
		order.setDisabled(true);
		order.setLastModifyTime(new Date().getTime());
		order.getBoard().setUse(false);
	}
	public Order getOrderById(String orderId) {
		Order order = Order.get(Order.class, orderId);
		initGetOrderInfo(order);
		return order;
	}

}
