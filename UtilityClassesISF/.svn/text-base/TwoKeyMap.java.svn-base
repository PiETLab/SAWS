package UtilityClassesISF;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

public class TwoKeyMap {

	private final int NUM_PLACES = 3;

	private Map<TwoElementKey, Double> matrix;

	public TwoKeyMap() {
		matrix = new HashMap<TwoElementKey, Double>();
	}

	public void put(Double key1, Double key2, Double value) {
		matrix.put(TwoElementKey.getTwoElementKey(key1, key2), value);
	}

	public Double get(Double key1, Double key2) {
		if (TwoElementKey.exists(key1, key2)) {
			return matrix.get(TwoElementKey.getTwoElementKey(key1, key2));
		} else {
			return null;
		}
	}

	public String getPretty(Double key1, Double key2) {
		if (TwoElementKey.exists(key1, key2)) {
			if (Double.isNaN(matrix.get(TwoElementKey.getTwoElementKey(key1,
					key2)))) {
				return "nan";
			} else if (Double.isInfinite(matrix.get(TwoElementKey
					.getTwoElementKey(key1, key2)))) {
				return "inf";
			} else {
				return ""
						+ myRound(matrix.get(TwoElementKey.getTwoElementKey(
								key1, key2)), NUM_PLACES);
			}
		} else {
			return "";
		}
	}

	public SortedSet<Double> iterableMajor() {
		return TwoElementKey.iterableMajor();
	}

	public SortedSet<Double> iterableMinor() {
		return TwoElementKey.iterableMinor();
	}

	public static double myRound(double d, int numPlaces) {
		// Might be a number like 0.899999999999 want to round to three decimal
		double d2 = d * Math.pow(10, numPlaces);
		return (1.0 * Math.round(d2)) / Math.pow(10, numPlaces);
	}

}
