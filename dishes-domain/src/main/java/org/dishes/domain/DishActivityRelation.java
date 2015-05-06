package org.dishes.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dish_activity")
public class DishActivityRelation implements Serializable{

	private static final long serialVersionUID = 5390970263559262046L;
	
	@Id
	@Column(name = "id")
	private String id = UUID.randomUUID().toString(); // 唯一标示
	
	@ManyToOne
	@JoinColumn(name = "dish_id")
	private Dish dish;
	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;
	
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
