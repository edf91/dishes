package org.dishes.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 活动：包含套餐、特价等
 */
@Entity
@Table(name = "t_activity")
public class Activity extends BaseEntity{

	private static final long serialVersionUID = 1066958151937022603L;
	
	@Column(name = "name")
	private String name; // 活动名称
	
	@Column(name = "description")
	private String description; // 活动描述
	
	@Column(name = "disabled")
	private boolean disabled; // 状态:0取消,1进行中
	
	@Column(name = "activity_begin_time")
	private long activityBeginTime; // 活动开始时间
	
	@Column(name = "activity_end_time")
	private long activityEndTime; // 活动结束时间
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "dish_activity", 
		joinColumns = { @JoinColumn(name = "activity_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "dish_id") })
	private Set<Dish> dishs = new HashSet<Dish>(); // 参与的菜

	@Column(name = "original_sum_price")
	private double originalSumPrice; // 未活动时的总价
	
	@Column(name = "activitySumPrice")
	private double activitySumPrice; // 活动时的总价
	
	/*************************static method*************************/
	public static boolean isNameExists(String name) {
		return !findByHQL("FROM Activity a WHERE a.name = ?", name).isEmpty();
	}
	public static List<Activity> findAllEntities() {
		return findByHQL("FROM Activity");
	}
	/*************************static method end*************************/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public long getActivityBeginTime() {
		return activityBeginTime;
	}

	public void setActivityBeginTime(long activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}

	public long getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(long activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public Set<Dish> getDishs() {
		return dishs;
	}

	public void setDishs(Set<Dish> dishs) {
		this.dishs = dishs;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
	
	
	
}
