package org.dishes.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 餐桌
 */
@Entity
@Table(name = "t_board")
public class Board extends BaseEntity{

	private static final long serialVersionUID = -169738049139693171L;
	
	private String name; // 餐桌名称:可以为序号
	
	private boolean isUse; // 是否已经被订
	
	@OneToMany(mappedBy = "board",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Order> orders = new HashSet<Order>(); // 该餐桌有过的订单
	
	@Column(name = "last_order_id")
	private String lastOrderId; // 最近一次下订单的id;以方便在下订单后加餐可以提高性能的知道是那张单要加菜。
	public Board(){
		
	}
	
	
	/****************static method start*************************/
	/**
	 * 判断名称是否存在
	 * @param name
	 * @return
	 */
	public static boolean isNameExists(String name){
		return !findByHQL("FROM Board b WHERE b.name=?", name).isEmpty();
	}
	/**
	 * 获取餐桌列表
	 * @return
	 */
	public static List<Board> findAllEntities() {
		return findByHQL("FROM Board");
	}
	/****************static method end*************************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUse() {
		return isUse;
	}

	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	

	public String getLastOrderId() {
		return lastOrderId;
	}


	public void setLastOrderId(String lastOrderId) {
		this.lastOrderId = lastOrderId;
	}


	@Override
	public String toString() {
		return "Board [name=" + name + ", isUse=" + isUse + ", orders="
				+ orders + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isUse ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (isUse != other.isUse)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		return true;
	}

	
	
}
