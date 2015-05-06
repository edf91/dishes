package org.dishes.facade.command;
/**
 * 添加餐桌dto
 */
public class CreateBoardCommand {
	
	private String name; // 餐桌名称

	
	public CreateBoardCommand(){
		
	}
	public CreateBoardCommand(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
