package sourceSymbolSet;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

/**
 * This class implements a set of source symbols, which for this domain boils
 * down to a set of letters (for the keyboard) based on the passed value of the
 * probability distribution. Each source symbol is associated with its
 * likelihood of occurrence.
 * 
 * For all intents a purposes, the sum of the probabilities is considered to be
 * 1 if Math.abs(1 - this.getSumOfProbabilities()) < EPSILON
 * 
 * 
 * Right now, the assumption is made that all the source symbols have unique
 * probability values
 * 
 * @author Fatima Ramay
 * @author M. Baljko
 * 
 */

public abstract class SourceSymbolSet implements Serializable {

	protected final boolean IS_VERBOSE = false;

	public final double EPSILON = 1e-4;

	// private List<JVirtualKeyboardButton> allButtons;
	// private List<SourceSymbol> sourceSymbols;

	// we use a Map so that the keys are kept in order according to...
	// private TreeMap<SourceSymbol, Double> sourceSymbols;
	private SortedSet<SourceSymbol> sourceSymbols;

	protected SourceSymbolSet() {
		sourceSymbols = new TreeSet<SourceSymbol>(
				new SourceSymbolProbabilityBasedComparator());
		// sourceSymbols = new TreeMap<SourceSymbol, Double>();
	}

	public String getIdentifier() {
		return this.getClass().getName();
	}

	// public void sort() {
	// Collections.sort(sourceSymbols, c);
	// }

	/**
	 * use anonymous class for sake of brevity
	 * 
	 * This comparator returns a negative integer, zero, or a positive integer
	 * as the first argument is less than, equal to, or greater than the second.
	 * 
	 * but1 is less than but2 if the expected frequency of but1 is larger than
	 * the expected frequency of but2
	 * 
	 * The comparator sorts the nodes in descending order
	 */

	private static Comparator<SourceSymbol> c = new Comparator<SourceSymbol>() {
		public int compare(SourceSymbol but1, SourceSymbol but2) {
			double diff = but1.getMarginalProbability()
					- but2.getMarginalProbability();
			if (diff < 0) {
				return 1;
			} else if (diff > 0) {
				return -1;
			} else {
				// the two nodes have the same expected frequency
				// in this case, choose an order based on the lexicographic
				// order of the two node's first child (or only child, if the
				// node is a leaf node, which has a trivial selection group)
				return but1.compareTo(but2);
			}
		}
	};

	public void addToSymbolSet(SourceSymbol s, double d) {
		// sourceSymbols.put(s, new Double(d));
		// System.out.println("Sorted set so far: " + this.toString());
		// System.out.println("tring to add: " + s + " " + s.toStringOneChar());
		s.setMarginalProbability(d);
		boolean isAdded = sourceSymbols.add(s);
		if (!isAdded) {
			// System.out.println("PROBLEM, symbol NOT ADDED: " + s);
			throw new RuntimeException("PROBLEM, symbol NOT ADDED: " + s);
		}
		// System.out.println("Added.  Sorted set now is: " + this.toString());

	}

	/**
	 * This method derives the sum of the probabilities of all the source
	 * symbols in this set and determines whether |1-sum| < EPSILON, where
	 * EPSILON = 1e-5
	 * 
	 * (i.e., the sum is valid if it is close enough to 1)
	 * 
	 * [MB]
	 */
	public boolean isSumsTo1() {
		return (Math.abs(1 - this.getSumOfProbabilities()) < EPSILON);
	}

	/**
	 * This method modifies all of the probability values so that the sum of the
	 * probabilities in this symbol set sums to 1.
	 * 
	 * @return whether the renormalization was successful
	 */
	public boolean renormalize() {
		// System.out.println(this.getSumOfProbabilities());
		int numIterations = 0;
		while (!isSumsTo1() && numIterations < 10) {
			double multiplier = 1.0 / this.getSumOfProbabilities();
			// System.out.println("multiplier:" + multiplier);
			for (SourceSymbol s : sourceSymbols) {
				// System.out.println(s + "\told/new:"
				// + s.getMarginalProbability() + "\t"
				// + s.getMarginalProbability() * multiplier);
				s.setMarginalProbability(s.getMarginalProbability()
						* multiplier);
				// sourceSymbols.put(s, sourceSymbols.get(s) * multiplier);
			}
			numIterations++;
			// System.out.println("sum: " + this.getSumOfProbabilities());
		}
		return isSumsTo1();
	}

	protected Double getSumOfProbabilities() {
		Double sum = 0.0;
		for (SourceSymbol s : sourceSymbols) {
			sum += s.getMarginalProbability();
		}
		return sum;
	}

	/**
	 * THIS METHOD NEEDS DOCUMENTATION - WHY NOT JUST USE
	 * getAllButtons().length() ????
	 * 
	 * It seems that this method is used to trim down the set of selectables to
	 * the top-n most likely candidates
	 * 
	 * @param newSize
	 */
	// public void trimDownToSize(int newSize) {
	// // int size = selectableKeys.size();
	// // JVirtualKeyboardButton but ;
	// if (newSize < sourceSymbols.size()) {
	// List<SourceSymbol> tempKeys = new Vector<SourceSymbol>();
	// // List<JVirtualKeyboardButton> allButtons = getAllButtons();
	// for (int i = 0; i < newSize; i++) {
	// tempKeys.add(sourceSymbols.get(i));
	//
	// }
	//
	// sourceSymbols = tempKeys;
	// // List<JVirtualKeyboardButton> allButtons2 = getAllButtons();
	//
	// }
	//
	// }
	public Set<SourceSymbol> getAllSourceSymbols() {
		Set<SourceSymbol> set = new HashSet<SourceSymbol>();
		for (SourceSymbol s : sourceSymbols) {
			set.add(s);
		}
		return set;
		/*
		 * List<JVirtualKeyboardButton> allButtons = new
		 * Vector<JVirtualKeyboardButton>();
		 * 
		 * for (JVirtualKeyboardButton but : selectableKeys) {
		 * allButtons.add(but); } return allButtons;
		 */
	}

	/**
	 * This method returns a list of probabilities of all the source symbols,
	 * given in the rank order of the source symbols.
	 * 
	 * @return
	 */
	public List<Double> getProbabilitiesByRankOrder() {
		// Map<SourceSymbol, Double> map = this.buildMapSortedByProbabilities();
		List<Double> l = new Vector<Double>();
		for (SourceSymbol s : sourceSymbols) {
			l.add(s.getMarginalProbability());
		}
		return l;
	}

	/**
	 * This method returns a list of all the source symbols in rank order, as
	 * determined by their probabilities (highest to lowest). In the case of
	 * ties, the secondary sort key is lexicographical order.
	 * 
	 * @return
	 */
	public List<SourceSymbol> getSourceSymbolsByRankOrder() {
		List<SourceSymbol> l = new Vector<SourceSymbol>();
		for (SourceSymbol s : sourceSymbols) {
			l.add(s);
		}
		return l;

		// Map<SourceSymbol, Double> map = this.buildMapSortedByProbabilities();
		// List<SourceSymbol> l = new Vector<SourceSymbol>();
		// for (SourceSymbol s : map.keySet()) {
		// l.add(s);
		// }
		// return l;
	}

	private Map<SourceSymbol, Double> buildMapSortedByProbabilities() {
		Map<SourceSymbol, Double> map = new TreeMap<SourceSymbol, Double>(
				new SourceSymbolProbabilityBasedComparator());
		for (SourceSymbol s : sourceSymbols) {
			map.put(s, s.getMarginalProbability());
			System.out.println(s + "\t" + s.getMarginalProbability());
		}
		return map;
	}

	public double getEntropy() {
		double entropy = 0.0;
		for (SourceSymbol s : sourceSymbols) {
			double term = s.getMarginalProbability()
					* logBase(s.getMarginalProbability(), 2.0);
			// System.out.println(s.getMarginalProbability());
			// System.out.println(term);
			entropy = entropy + term;
		}
		return -1 * entropy;
	}

	public double getMinExpectedCostLowerBound(int encodingAlphabetSize) {
		return this.getEntropy() / logBase(encodingAlphabetSize, 2.0);
	}

	public double getMinExpectedCostUpperBound(int encodingAlphabetSize) {
		return (this.getEntropy() / logBase(encodingAlphabetSize, 2.0)) + 1;
	}

	// public List<SourceSymbol> getAllEnabledButtons() {
	//
	// List<SourceSymbol> allEnabledButtons = new Vector<SourceSymbol>();
	//
	// for (SourceSymbol but : sourceSymbols) {
	// if (but.isEnabled()) {
	// allEnabledButtons.add(but);
	// }
	//
	// }
	// return allEnabledButtons;
	//
	// }

	// public List<Double> getEnabledFrequncyList() {
	//
	// List<Double> probList = new Vector<Double>();
	//
	// for (SourceSymbol but : getAllEnabledButtons()) {
	// probList.add(but.getProbabilityOfOccurrence());
	//
	// }
	// return probList;
	//
	// }

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(this.getIdentifier() + "\n");
		List<String> l = new Vector<String>();
		for (SourceSymbol x : getSourceSymbolsByRankOrder()) {
			l.add("" + x.toStringOneChar() + "\t" + x.getMarginalProbability());
		}
		// Collections.sort(l);
		StringTokenizer tok;
		for (String str : l) {
			tok = new StringTokenizer(str);
			buf.append(tok.nextToken() + "\t" + tok.nextToken() + "\n");
		}
		buf.append(this.getClass().getName() + "  end of toString()");
		return buf.toString();
	}

	public String toStringLaTeX() {

		StringBuffer buf = new StringBuffer();
		// buf.append("\\noindent \\url{" + this.getIdentifier() + "}\n\n");
		//
		// Set<SourceSymbol> s = values.keySet();
		// // Collection<Double> v = values.values();
		// List<SourceSymbol> l = new Vector<SourceSymbol>();
		// l.addAll(s);
		// // Collections.sort(l);
		//
		// buf.append("\\begin{tabular}{l l}");
		// for (SourceSymbol x : l) {
		// // buf.append("" + x.toStringLaTeX() + "\t&\t" + get(x) + "\\\\\n");
		// buf.append("" + x.toString() + "\t&\t" + get(x) + "\\\\\n");
		// }
		// buf.append("\\end{tabular}");
		return buf.toString();
	}

	/**
	 * This method returns the log base 2 of the passed value d.
	 */
	public static double logBase(double d, double base) {
		final double EPSILON = 0.0001;
		if (Math.abs(d - 1.0) < EPSILON) {
			return 0;
		} else {
			// System.out.println("log_{" + base + "}(" + d + ") = "
			// + (Math.log(d) / Math.log(base)));
			return Math.log(d) / Math.log(base);
		}
	}

	public int size() {
		return sourceSymbols.size();
	}
}
