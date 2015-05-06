package org.dishes.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 订单
 */
@Entity
@Table(name = "t_order")
public class Order extends BaseEntity{

	private static final long serialVersionUID = 8376633978775301600L;
	
	@ManyToOne(cascade = CascadeType.REFRESH,optional = false)
	@JoinColumn(name = "board_id")
	private Board board; // 在那个餐桌
	
	@Column(name = "dishIds",columnDefinition="TEXT")
	private String dishIds; // 用逗号分隔
	@Transient
	private List<Dish> dishes = new ArrayList<Dish>(); // 不存进数据库，只为了获取时根据id获取实体存放
	
	@Column(name = "activeIds",columnDefinition="TEXT")
	private String activeIds; // 用逗号分隔
	@Transient
	private List<Activity> activities = new ArrayList<Activity>(); // 不存进数据库，只为了获取时根据id获取实体存放
	@Column(name = "order_time")
	private long orderTime; // 下单时间
	@Column(name = "remark")
	private String remark; // 备注，如加急、等人、少放辣之类的信息
	@Column(name = "real_price")
	private double realPrice; // 真实付款，即如果实际收款是有再另外打折
	@Column(name = "disabled")
	private boolean disabled; // 状态，false表示未付款，true表示付款
	
	public static List<Order> findAllEntities() {
		return findByHQL("FROM Order");
	}
	
	
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
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



	public String getDishIds() {
		return dishIds;
	}

	public void setDishIds(String dishIds) {
		this.dishIds = dishIds;
	}

	public String getActiveIds() {
		return activeIds;
	}

	public void setActiveIds(String activeIds) {
		this.activeIds = activeIds;
	}


	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}

	
}
