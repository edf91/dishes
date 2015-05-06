package org.dishes.facade.dto;

public class DishDTO {
	private String id;
	private String name; // 菜名
	private String dishTypeId; // 类型
	private String dishTypeName; // 类型名称
	private double price; // 价格
	private boolean isActivity; // 是否特价
	private double activityPrice; // 处于特价时的价格
	private String description; // 描述
	private int num; // 库存数量
	private boolean disabled;
	
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
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
	public String getDishTypeName() {
		return dishTypeName;
	}
	public void setDishTypeName(String dishTypeName) {
		this.dishTypeName = dishTypeName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isActivity() {
		return isActivity;
	}
	public void setActivity(boolean isActivity) {
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
