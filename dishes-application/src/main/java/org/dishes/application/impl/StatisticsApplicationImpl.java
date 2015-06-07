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
	
	/**
	 * 实现热销饼图
	 * topNum 为前几
	 */
	public List<DishTop> getTopDish(int topNum) {
		// 存放统计结果
		List<DishTop> results = new ArrayList<DishTop>();
		
		// 获取所有的订单信息
		List<Order> allOrder = Order.findAllEntities();
		// 通过map集合的特性（key不可重复），存放菜id--买出数的映射 
		Map<String, Integer> dishIdNum = new HashMap<String, Integer>();
		// 遍历所有订单，分析常规菜与活动，获取菜的销量
		for (Order order : allOrder) {
			String[] dishIds = DataUtil.toArrayStr(order.getDishIds());
			for (String dishId : dishIds) {
				if(null != dishId && !"".equals(dishId))
				dishIdNum.put(dishId, dishIdNum.get(dishId) == null ? 1 : dishIdNum.get(dishId) + 1);
			}
			String[] activeIds = DataUtil.toArrayStr(order.getActiveIds());
			for (String activeId : activeIds) {
				Activity activity = Activity.get(Activity.class, activeId);
				if(activity != null)
				for (Dish dish : activity.getDishs()) {
					dishIdNum.put(dish.getId(), dishIdNum.get(dish.getId()) == null ? 1 : dishIdNum.get(dish.getId()) + 1);
				}
			}
		}
		// 由于map为无序集合，因此通过自定义比较器实现map集合按照value值的顺序存放
		List<Map.Entry<String, Integer>> sortList = new ArrayList<Map.Entry<String,Integer>>(dishIdNum.entrySet());
		Collections.sort(sortList, new MapValueComparator());
		Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		for(Iterator<Map.Entry<String, Integer>> ite = sortList.iterator();ite.hasNext();){
			Map.Entry<String, Integer> entry = ite.next();
			sortMap.put(entry.getKey(), entry.getValue());
		}
		// 存放进最后的结果集
		int sum = 0;
		Set<Map.Entry<String,Integer>> sets = sortMap.entrySet();
		int i = 0; // 用户判断是否已经达到topNum
		for(Iterator<Map.Entry<String, Integer>> iter = sets.iterator(); iter.hasNext();){
			if(i++ == topNum) break;
			Map.Entry<String, Integer> entry = iter.next();
			Dish d = Dish.get(Dish.class, entry.getKey());
			DishTop dishTop = new DishTop();
			dishTop.setDishName(d.getName());
			dishTop.setDishType(d.getDishType().getName());
			dishTop.setNum(entry.getValue());
			sum += entry.getValue();
			results.add(dishTop);
		}
		// 计算比例
		for (DishTop d : results) {
			d.setSum(sum);
			if(sum != 0) d.setScale(Double.parseDouble(d.getNum() + "") / Double.parseDouble(sum + ""));
		}
		return results;
	}
	/**
	 * 近nearDay的日销量
	 */
	public NearDayStatis nearDayStatis(int nearDay) {
		// 结果集
		NearDayStatis result = new NearDayStatis();
		result.setNearDay(nearDay);
		result.setTimes(initTimeArrayList(nearDay));
		for(int i = 0; i < nearDay ; i++){
			// 获取这段时间的订单
			List<Order> orders = Order.findByOrderTimeBetween(result.getTimes().get(i + 1),result.getTimes().get(i));
			double realSumPay = 0.0;
			// 遍历订单
			for (Order order : orders) {
				realSumPay += order.getRealPrice();
			}
			result.getSalesCount().add(realSumPay);
		}
		return result;
	}
	/**
	 * 通过nearDay获取各个日期的日期时间戳
	 * @param nearDay
	 * @return
	 */
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
	
	/*public static void main(String[] args) throws ParseException {
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
	}*/
}
