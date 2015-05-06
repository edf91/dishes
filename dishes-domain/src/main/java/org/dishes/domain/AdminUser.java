package org.dishes.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 管理员用户:职责管理系统
 */
@Entity
@DiscriminatorValue("ADMIN_USER")
public class AdminUser extends User{
	
	public AdminUser(){
		
	}
	public AdminUser(String userAccount) {
		super(userAccount);
	}

	private static final long serialVersionUID = 5966440467686879787L;
	
	/*************************staticc method start********************************/
	/*************************staticc method end*********************************/
}
