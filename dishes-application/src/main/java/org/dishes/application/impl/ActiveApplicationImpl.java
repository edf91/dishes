package org.dishes.application.impl;

import java.util.List;

import javax.inject.Named;

import org.dishes.application.ActiveApplication;
import org.dishes.domain.Activity;
import org.dishes.domain.Dish;
@Named
public class ActiveApplicationImpl implements ActiveApplication {

	public boolean isNameExists(String name) {
		return Activity.isNameExists(name);
	}

	public void createActive(Activity activity) {
		activity.save();
	}

	public List<Activity> listActivities() {
		List<Activity> results = Activity.findAllEntities();
		for (Activity activity : results) {
			for (Dish dish : activity.getDishs()) {
				dish.getId();
			}
		}
		return results;
	}

	public void deleteActivityById(String id) {
		Activity.get(Activity.class, id).delete();;
	}

}
