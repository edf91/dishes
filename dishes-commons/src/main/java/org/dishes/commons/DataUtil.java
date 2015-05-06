package org.dishes.commons;

public class DataUtil {
	/**
	 * 字符数组转换成以逗号分隔的字符串
	 * @param ids
	 * @return
	 */
	public static String toStr(String[] ids){
		StringBuffer result = new StringBuffer();
		for (String re : ids) {
			result.append(re).append(",");
		}
		if(result.length() > 0) result.deleteCharAt(result.length() - 1);
		return result.toString();
	}
	/**
	 * 将以逗号分隔的字符串转换成数组
	 * @param str
	 * @return
	 */
	public static String[] toArrayStr(String str){
		return str.split(",");
	}
}
