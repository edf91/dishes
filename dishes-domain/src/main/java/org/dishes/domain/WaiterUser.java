package org.dishes.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 服务员
 */
@Entity
@DiscriminatorValue("WAITER_USER")
public class WaiterUser extends User{
	public WaiterUser(){
		
	}
	public WaiterUser(String userAccount) {
		super(userAccount);
	}

	private static final long serialVersionUID = -8310238451694721264L;
	
	/*************************staticc method start********************************/
	/*************************staticc method end*********************************/
}
