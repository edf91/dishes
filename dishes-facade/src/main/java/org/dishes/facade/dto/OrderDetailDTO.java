package org.dishes.facade.dto;

import java.util.List;

public class OrderDetailDTO {
	private String id;
	private BoardDTO board;
	private List<DishDTO> dishes;
	private List<ActivityDTO> activities;
	private double bePay;
	private String remark;
	private boolean disabled;
	private double realPay;
	private long orderTime;
	
	public OrderDetailDTO(){
		
	}
	
	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public BoardDTO getBoard() {
		return board;
	}
	public void setBoard(BoardDTO board) {
		this.board = board;
	}
	public List<DishDTO> getDishes() {
		return dishes;
	}
	public void setDishes(List<DishDTO> dishes) {
		this.dishes = dishes;
	}
	public List<ActivityDTO> getActivities() {
		return activities;
	}
	public void setActivities(List<ActivityDTO> activities) {
		this.activities = activities;
	}
	public double getBePay() {
		return bePay;
	}
	public void setBePay(double bePay) {
		this.bePay = bePay;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public double getRealPay() {
		return realPay;
	}

	public void setRealPay(double realPay) {
		this.realPay = realPay;
	}
	
	
}
