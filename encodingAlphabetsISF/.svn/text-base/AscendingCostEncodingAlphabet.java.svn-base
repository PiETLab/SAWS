package encodingAlphabetsISF;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a set of encoding symbols.
 * 
 * Each encoding symbol has an index (where the indices start at 0 and are
 * numbered consecutively
 * 
 * Each encoding symbol has a cost (which is given by a double value
 * 
 * @author mb
 * 
 */
public class AscendingCostEncodingAlphabet extends EncodingSymbolAlphabet {

	private Map<Integer, Double> costs;

	public AscendingCostEncodingAlphabet(int size) {
		costs = new HashMap<Integer, Double>();
		for (int i = 0; i < size; i++) {
			costs.put(new Integer(i), new Double(i + 1));
		}
	}

	public double getCost(int index) {
		return costs.get(index);
	}

}
