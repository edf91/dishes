package org.dishes.application.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Named;

import org.dishes.application.StatisticsApplication;
import org.dishes.commons.DataUtil;
import org.dishes.commons.MapValueComparator;
import org.dishes.domain.Activity;
import org.dishes.domain.Dish;
import org.dishes.domain.Order;
import org.dishes.domain.statistics.DishTop;
import org.dishes.domain.statistics.NearDayStatis;

@Named
public class StatisticsApplicationImpl implements StatisticsApplication{
	
	// 一天的毫秒数
	private static final long ONE_DAY_TIMESTAP = 86400000;
	
	public List<DishTop> getTopDish(int topNum) {
		List<DishTop> results = new ArrayList<DishTop>();
		List<Order> allOrder = Order.findAllEntities();
		Map<String, Integer> dishIdNum = new HashMap<String, Integer>();
		for (Order order : allOrder) {
			String[] dishIds = DataUtil.toArrayStr(order.getDishIds());
			for (String dishId : dishIds) {
				dishIdNum.put(dishId, dishIdNum.get(dishId) == null ? 0 : dishIdNum.get(dishId) + 1);
			}
			String[] activeIds = DataUtil.toArrayStr(order.getActiveIds());
			for (String activeId : activeIds) {
				Activity activity = Activity.get(Activity.class, activeId);
				for (Dish dish : activity.getDishs()) {
					dishIdNum.put(dish.getId(), dishIdNum.get(dish.getId()) == null ? 0 : dishIdNum.get(dish.getId()) + 1);
				}
			}
		}
		
		List<Map.Entry<String, Integer>> sortList = new ArrayList<Map.Entry<String,Integer>>(dishIdNum.entrySet());
		Collections.sort(sortList, new MapValueComparator());
		Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		for(Iterator<Map.Entry<String, Integer>> ite = sortList.iterator();ite.hasNext();){
			Map.Entry<String, Integer> entry = ite.next();
			sortMap.put(entry.getKey(), entry.getValue());
		}
		
		int sum = 0;
		Set<Map.Entry<String,Integer>> sets = sortMap.entrySet();
		for(Iterator<Map.Entry<String, Integer>> iter = sets.iterator(); iter.hasNext();){
			Map.Entry<String, Integer> entry = iter.next();
			Dish d = Dish.get(Dish.class, entry.getKey());
			DishTop dishTop = new DishTop();
			dishTop.setDishName(d.getName());
			dishTop.setDishType(d.getDishType().getName());
			dishTop.setNum(entry.getValue());
			sum += entry.getValue();
			results.add(dishTop);
		}
		
		for (DishTop d : results) {
			d.setSum(sum);
			if(sum != 0) d.setScale(Double.parseDouble(d.getNum() + "") / Double.parseDouble(sum + ""));
		}
		return results;
	}
	public NearDayStatis nearDayStatis(int nearDay) {
		NearDayStatis result = new NearDayStatis();
		result.setNearDay(nearDay);
		result.setTimes(initTimeArrayList(nearDay));
		for(int i = 0; i < nearDay ; i++){
			List<Order> orders = Order.findByOrderTimeBetween(result.getTimes().get(i + 1),result.getTimes().get(i));
			double realSumPay = 0.0;
			for (Order order : orders) {
				realSumPay += order.getRealPrice();
			}
			result.getSalesCount().add(realSumPay);
		}
		return result;
	}
	private List<Long> initTimeArrayList(int nearDay){
		// 根据nearDay 初始化 times
		List<Long> times = new ArrayList<Long>();
		times.add(new Date().getTime());
		// 获取当前年月日时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTimeStr = sdf.format(new Date());
		// 获取当前时间的年月日时间戳
		long currentDateStap = 0;
		try {
			currentDateStap = sdf.parse(currentTimeStr).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i = 0; i < nearDay; i++){
			times.add(currentDateStap);
			currentDateStap -= ONE_DAY_TIMESTAP;
		}
		return times;
	}
	
	public static void main(String[] args) throws ParseException {
		// 获取当前年月日时间
		int nearDay = 10;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String currentTimeStr = sdf.format(new Date());
				
				// 获取当前时间的年月日时间戳
				long currentDateStap = 0;
				try {
					currentDateStap = sdf.parse(currentTimeStr).getTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<Long> times = new ArrayList<Long>();
				for(int i = 0; i < nearDay; i++){
					times.add(currentDateStap);
					currentDateStap -= ONE_DAY_TIMESTAP;
				}
		for (Long dateStap : times) {
			System.out.println(new Date(dateStap));
		}
	}
}
