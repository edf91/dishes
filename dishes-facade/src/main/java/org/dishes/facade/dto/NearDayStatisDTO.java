package org.dishes.facade.dto;

import java.util.List;

public class NearDayStatisDTO {
	private String name; // 统计名称
	private int nearDay; // 近多少日,如果为10，表示近10日
	private List<Long> time; // 近nearDay日期时间戳
	private List<Double> salesCount; // 日销售额
	public String getName() {
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
	public List<Long> getTime() {
		return time;
	}
	public void setTime(List<Long> time) {
		this.time = time;
	}
	public List<Double> getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(List<Double> salesCount) {
		this.salesCount = salesCount;
	}
	
}
