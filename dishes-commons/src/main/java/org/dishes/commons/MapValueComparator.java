package org.dishes.commons;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * map value比较器
 */
public class MapValueComparator implements Comparator<Map.Entry<String, Integer>>{

	public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
		return entry1.getValue() - entry2.getValue();
	}

}
