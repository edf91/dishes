package org.dishes.domain.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * 近期统计模型，不存进数据库
 */
public class NearDayStatis {
	private String name;
	public int nearDay;
	private List<Long> times = new ArrayList<Long>();
	private List<Double> salesCount = new ArrayList<Double>();
	
	public NearDayStatis(){
		
	}
	
	public String getName() {
		if(null == this.name || "".equals(name)){
			this.name = "近"+nearDay+"销售额统计情况";
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNearDay() {
		return nearDay;
	}
	public void setNearDay(int nearDay) {
		this.nearDay = nearDay;
	}
	public List<Long> getTimes() {
		return times;
	}
	public void setTimes(List<Long> times) {
		this.times = times;
	}
	public List<Double> getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(List<Double> salesCount) {
		this.salesCount = salesCount;
	}
	
	
	
}
