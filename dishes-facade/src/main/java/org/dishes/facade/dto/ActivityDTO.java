package org.dishes.facade.dto;

import java.util.ArrayList;
import java.util.List;

public class ActivityDTO {
	
	private String id;
	private String name; // 活动名称
	private String description; // 活动描述
	private boolean disabled; // 状态:0取消,1进行中
	private List<String> dishNames = new ArrayList<String>(); // 参与的菜
	private double originalSumPrice; // 未活动时的总价
	private double activitySumPrice; // 活动时的总价
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public List<String> getDishNames() {
		return dishNames;
	}
	public void setDishNames(List<String> dishNames) {
		this.dishNames = dishNames;
	}
	public double getOriginalSumPrice() {
		return originalSumPrice;
	}
	public void setOriginalSumPrice(double originalSumPrice) {
		this.originalSumPrice = originalSumPrice;
	}
	public double getActivitySumPrice() {
		return activitySumPrice;
	}
	public void setActivitySumPrice(double activitySumPrice) {
		this.activitySumPrice = activitySumPrice;
	}
}
