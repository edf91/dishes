package org.dishes.facade.dto;
/**
 * 分类dto
 */
public class DishTypeDTO {
	
	private String id;
	private String name;
	private boolean disabled;
	
	public DishTypeDTO(){
		
	}
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
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
}
