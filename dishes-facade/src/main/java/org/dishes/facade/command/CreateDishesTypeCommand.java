package org.dishes.facade.command;
/**
 * 创建分类dto
 */
public class CreateDishesTypeCommand {
	
	private String name;
	
	public CreateDishesTypeCommand(){
		
	}
	public CreateDishesTypeCommand(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
