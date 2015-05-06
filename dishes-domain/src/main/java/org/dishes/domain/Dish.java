package org.dishes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 菜
 */
@Entity
@Table(name = "t_dish")
public class Dish extends BaseEntity{
	private static final long serialVersionUID = -6028510693669117126L;
	@Column(name = "name")
	private String name; // 菜名
	
	/*optional为false表示外键不能为空*/
	@ManyToOne(cascade = CascadeType.REFRESH,optional = true)
	@JoinColumn(name = "dish_type_id")
	private DishType dishType; // 类型
	
	@Column(name = "price")
	private double price; // 价格
	
	@Column(name = "is_activity")
	private boolean isActivity; // 是否特价
	
	@Column(name = "activity_price")
	private double activityPrice; // 处于特价时的价格
	
	@Column(name = "disabled")
	private boolean disabled; // 状态:1上架,0下架
	
	@Column(name = "description")
	private String description; // 描述
	
	@Column(name = "num")
	private int num; // 库存数量
	
	/*************************staticc method start********************************/
	public static List<Dish> findAllEntities() {
		return findByHQL("FROM Dish");
	}
	public static List<Dish> listAllAvailable() {
		return findByHQL("FROM Dish d WHERE d.disabled = ?", false);
	}
	/*************************staticc method end*********************************/
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DishType getDishType() {
		return dishType;
	}
	public void setDishType(DishType dishType) {
		this.dishType = dishType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
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
	
	
}
