package org.dishes.facade.command;


public class CreateActiveCommand {
	private String name; // 活动名称
	private String description; // 活动描述
	private String disabled; // 状态:0取消,1进行中
//	private String activityBeginTime; // 活动开始时间
//	private String activityEndTime; // 活动结束时间
	private String dishIds; // 参与的菜的id以,隔开
//	private double originalSumPrice; // 未活动时的总价
	private double activitySumPrice; // 活动时的总价
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	/*public String getActivityBeginTime() {
		return activityBeginTime;
	}
	public void setActivityBeginTime(String activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}
	public String getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}*/
	public String getDishIds() {
		return dishIds;
	}
	public void setDishIds(String dishIds) {
		this.dishIds = dishIds;
	}
	/*public double getOriginalSumPrice() {
		return originalSumPrice;
	}
	public void setOriginalSumPrice(double originalSumPrice) {
		this.originalSumPrice = originalSumPrice;
	}*/
	public double getActivitySumPrice() {
		return activitySumPrice;
	}
	public void setActivitySumPrice(double activitySumPrice) {
		this.activitySumPrice = activitySumPrice;
	}
	
	
}
