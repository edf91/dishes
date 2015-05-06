package org.dishes.facade.dto;
/**
 * 餐桌dto
 */
public class BoardDTO {
	
	private String id;
	private String name;
	private boolean isUse;
	private String lastOrderId;
	

	public void setLastOrderId(String lastOrderId) {
		this.lastOrderId = lastOrderId;
	}

	public BoardDTO(){
		
	}

	public String getLastOrderId() {
		return lastOrderId;
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

	public boolean isUse() {
		return isUse;
	}

	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}
	
	
}
