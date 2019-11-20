package UtilityClassesISF;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class TwoElementKey implements Comparable<TwoElementKey> {

	private Double key1;
	private Double key2;
	private Double majorKey;
	private Double minorKey;

	private static Map<String, TwoElementKey> pool = new HashMap<String, TwoElementKey>();

	private TwoElementKey(Double key1, Double key2) {
		this.key1 = key1;
		this.key2 = key2;
		this.majorKey = key1;
		this.minorKey = key2;
	}

	public static TwoElementKey getTwoElementKey(Double key1, Double key2) {
		String key = key1 + "*" + key2;
		if (pool.containsKey(key)) {
			return pool.get(key);
		} else {
			pool.put(key, new TwoElementKey(key1, key2));
			return pool.get(key);
		}
	}

	public static boolean exists(Double key1, Double key2) {
		String key = key1 + "*" + key2;
		return pool.containsKey(key);
	}

	public void setKey1Major() {
		this.majorKey = key1;
		this.minorKey = key2;
	}

	public void setKey2Major() {
		this.majorKey = key2;
		this.minorKey = key1;
	}

	private Double getMajorKey() {
		return majorKey;
	}

	private Double getMinorKey() {
		return minorKey;
	}

	public int compareTo(TwoElementKey arg0) {
		int val = getMajorKey().compareTo(arg0.getMajorKey());
		if (val != 0) {
			return val;
		} else {
			return getMinorKey().compareTo(arg0.getMinorKey());
		}
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TwoElementKey) {
			TwoElementKey other = (TwoElementKey) arg0;
			return this.compareTo(other) == 0;
		} else {
			return false;
		}
	}

	public static SortedSet<Double> iterableMajor() {
		SortedSet<Double> set = new TreeSet<Double>();
		for (TwoElementKey key : pool.values()) {
			set.add(key.getMajorKey());
		}
		return set;
	}

	public static SortedSet<Double> iterableMinor() {
		SortedSet<Double> set = new TreeSet<Double>();
		for (TwoElementKey key : pool.values()) {
			set.add(key.getMinorKey());
		}
		return set;
	}

}
