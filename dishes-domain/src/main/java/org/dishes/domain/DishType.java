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
 * 菜的类型
 */
@Entity
@Table(name = "t_dish_type")
public class DishType extends BaseEntity{

	private static final long serialVersionUID = 3302677028787732498L;
	@Column(name = "name")
	private String name; // 类型名称
	
	@Column(name = "disabled")
	private boolean disabled;// 状态:1使用,0不使用
	
	@OneToMany(mappedBy = "dishType",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Dish> dishs = new HashSet<Dish>(); // 菜，一对多

	
	/***********************static method************************/
	/**
	 * 名称是否存在
	 * @param name
	 * @return
	 */
	public static boolean isNameExists(String name) {
		return !findByHQL("FROM DishType dt WHERE dt.name=?", name).isEmpty();
	}
	/**
	 * 分类列表
	 * @return
	 */
	public static List<DishType> findAllEntities() {
		return findByHQL("FROM DishType");
	}
	public static List<DishType> findAllAvailableDishType() {
		return findByHQL("FROM DishType dt WHERE dt.disabled = ?", false);
	}
	/***********************static method end************************/
	public String getName() {
		return name;
	}

	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Set<Dish> getDishs() {
		return dishs;
	}

	public void setDishs(Set<Dish> dishs) {
		this.dishs = dishs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dishs == null) ? 0 : dishs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DishType other = (DishType) obj;
		if (dishs == null) {
			if (other.dishs != null)
				return false;
		} else if (!dishs.equals(other.dishs))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DishType [name=" + name + ", dishs="
				+ dishs + "]";
	}
	
	
}
