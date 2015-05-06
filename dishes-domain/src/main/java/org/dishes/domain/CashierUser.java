package org.dishes.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 收银员
 */
@Entity
@DiscriminatorValue("CASHIER_USER")
public class CashierUser extends User{
	public CashierUser(){
		
	}
	public CashierUser(String userAccount) {
		super(userAccount);
	}

	private static final long serialVersionUID = -5929288692604364001L;
	
	/*************************staticc method start********************************/
	/*************************staticc method end*********************************/
}
