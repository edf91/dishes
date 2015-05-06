package org.dishes.facade.command;


public class CreateDishCommand {
	private String name; // 菜名
	private String dishTypeId; // 类型
	private double price; // 价格
	private String isActivity; // 是否特价
	private double activityPrice; // 处于特价时的价格
	private String description; // 描述
	private int num; // 库存数量
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDishTypeId() {
		return dishTypeId;
	}
	public void setDishTypeId(String dishTypeId) {
		this.dishTypeId = dishTypeId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIsActivity() {
		return isActivity;
	}
	public void setIsActivity(String isActivity) {
		this.isActivity = isActivity;
	}
	public double getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(double activityPrice) {
		this.activityPrice = activityPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
