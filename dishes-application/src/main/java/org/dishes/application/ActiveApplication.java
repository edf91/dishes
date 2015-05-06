package org.dishes.application;

import java.util.List;

import org.dishes.domain.Activity;

public interface ActiveApplication {
	
	boolean isNameExists(String name);
	void createActive(Activity activity);
	List<Activity> listActivities();
	void deleteActivityById(String id);
}
