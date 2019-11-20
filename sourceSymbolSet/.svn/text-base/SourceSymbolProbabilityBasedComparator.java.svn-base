package sourceSymbolSet;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 
 * Implements descending probability order
 * 
 * @author mb
 * 
 */
public class SourceSymbolProbabilityBasedComparator implements
		Comparator<SourceSymbol>, Serializable {

	private final boolean IS_VERBOSE = false;

	public int compare(SourceSymbol arg0, SourceSymbol arg1) {
		// return this.getTextLabel().compareTo(other.getTextLabel());
		// System.out.println(arg0.toString() + " being compared to " + arg1);
		if (IS_VERBOSE) {
			System.out.println(arg0.getTextLabel() + " ("
					+ arg0.getMarginalProbability() + ") being compared to "
					+ arg1.getTextLabel() + " ("
					+ arg1.getMarginalProbability() + ")");
		}
		int result = -1
				* (arg0.getMarginalProbability()).compareTo(arg1
						.getMarginalProbability());
		if (result == 0) {
			result = arg0.getTextLabel().compareTo(arg1.getTextLabel());
		}
		return result;
	}
}
