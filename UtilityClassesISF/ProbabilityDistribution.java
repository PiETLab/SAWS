/*
 */
package UtilityClassesISF;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;

import sourceSymbolSet.SourceSymbol;


// import java.util.StringTokenizer;

/**
 * This class implements a probability distribution over a given, discrete
 * domain.
 * 
 * @author Melanie Baljko
 */
// public class ProbabilityDistribution<SourceSymbol> {
public class ProbabilityDistribution {

	private Hashtable<SourceSymbol, Double> values;

	private String identifier;

	public ProbabilityDistribution() {
		values = new Hashtable<SourceSymbol, Double>();
		this.identifier = this.getClass().getName();
	}

	public void put(SourceSymbol s, double d) {
		values.put(s, new Double(d));
		// s.getClass();
	}

	/**
	 * Get the probability associated with the action command given by the
	 * parameter s. If s is not present in the domain of this probability
	 * distribution, then a null pointer exception is thrown
	 * 
	 * PRE that s is present in the domain of this probability distribution that
	 * a cast from Object to the run-time type of this generic class does not
	 * cause a run-time error
	 * 
	 * @param s
	 * @return as described above
	 */
	public double get(SourceSymbol x) {
		// X xCast = (X) x;
		// do we even need the cast?
		return values.get(x);
	}

	public boolean isPresent(String s) {
		return values.get(s) != null;
	}

	public boolean isWellFormed() {
		double EPSILON = 1e-5;
		double sum = 0.0;
		for (SourceSymbol o : values.keySet()) {
			sum += this.get(o);
		}
		return Math.abs(sum - 1.0) < EPSILON;
	}

	public Iterator<SourceSymbol> getDomainIterator() {
		return values.keySet().iterator();
	}

	public Iterable<SourceSymbol> getDomainIterable() {
		Set<SourceSymbol> s = values.keySet();
		TreeSet<SourceSymbol> ts = new TreeSet<SourceSymbol>(s);
		return ts;
	}

	public Set<SourceSymbol> getDomain() {
		return values.keySet();
	}

	public int size() {
		return values.keySet().size();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(getIdentifier() + "\n");
		List<String> l = new Vector<String>();
		for (SourceSymbol x : values.keySet()) {
			l.add("" + get(x) + "\t" + x.toString());
		}
		Collections.sort(l);
		StringTokenizer tok;
		for (String str : l) {
			tok = new StringTokenizer(str);
			buf.append(tok.nextToken() + "\t" + tok.nextToken() + "\n");
		}
		return buf.toString();
	}

	public String toStringLaTeX() {

		StringBuffer buf = new StringBuffer();
		buf.append("\\noindent \\url{" + this.getIdentifier() + "}\n\n");

		Set<SourceSymbol> s = values.keySet();
		// Collection<Double> v = values.values();
		List<SourceSymbol> l = new Vector<SourceSymbol>();
		l.addAll(s);
		// Collections.sort(l);

		buf.append("\\begin{tabular}{l l}");
		for (SourceSymbol x : l) {
			// buf.append("" + x.toStringLaTeX() + "\t&\t" + get(x) + "\\\\\n");
			buf.append("" + x.toString() + "\t&\t" + get(x) + "\\\\\n");
		}
		buf.append("\\end{tabular}");
		return buf.toString();
	}

	// public String toStringLaTeX() {
	// StringBuffer buf = new StringBuffer();
	// Set<JVirtualKeyboardButton> s = values.keySet();
	// List<JVirtualKeyboardButton> l = new Vector<JVirtualKeyboardButton>();
	// l.addAll(s);
	// Collections.<JVirtualKeyboardButton>sort(l);
	//
	// buf.append("\\begin{tabular}{l l}");
	// for (JVirtualKeyboardButton but : l) {
	// buf.append("" + but.toStringLaTeX() + "\t&\t" + get(but) + "\\\\\n");
	// }
	// buf.append("\\end{tabular}");
	// return buf.toString();
	// }

	/**
	 * This method takes as input a specially formatted input file consisting of
	 * a list of enumerated text glosses and returns the probability
	 * distribution over the set of characters in the text glosses (the tags for
	 * each text gloss are not included). All lower-case letters are converted
	 * to upper-case letters.
	 * 
	 * @param inputFileName
	 *            the name of an input file
	 * @return a probability distribution over the characters contained in the
	 *         input file whose name was passed as the parameter to this metho
	 * @throws FileNotFoundException
	 */

	// / MIGRATED FROM UTILITY CLASS - SEE WHETHER THIS CAN BE SALVAGED LATER
	// public static ProbabilityDistribution getProbabilityDistribution(
	// String inputFileName) throws IOException {
	// BufferedReader in = new BufferedReader(new FileReader(inputFileName));
	// String currLine = in.readLine();
	// ProbabilityDistribution dist = new ProbabilityDistribution();
	// int totChars = 0;
	// dist.put("\u00b6", 0);
	// while (currLine != null) {
	// StringTokenizer st = new StringTokenizer(currLine, "\t");
	// // String currentTextGlossID = st.nextToken();
	// String currentTextGloss = st.nextToken();
	// for (int i = 0; i < currentTextGloss.length(); i++) {
	// String tmp = currentTextGloss.substring(i, i + 1);
	// tmp = tmp.toUpperCase();
	// totChars++;
	// // check whether this character has already occurred
	// // in the prob distribution
	// if (dist.isPresent(tmp)) {
	// dist.put(tmp, dist.get(tmp) + 1);
	// } else {
	// dist.put(tmp, 1);
	// }
	// }
	// dist.put("\u00b6", dist.get("\u00b6") + 1);
	// totChars++;
	// currLine = in.readLine();
	// }
	// ProbabilityDistribution normDist = new ProbabilityDistribution();
	// Iterator it = dist.getDomainIterator();
	// while (it.hasNext()) {
	// String currKey = (String) it.next();
	// normDist.put(currKey, dist.get(currKey) / totChars);
	// }
	// return normDist;
	// }
	public static boolean isSameDomain(ProbabilityDistribution p1,
			ProbabilityDistribution p2) {
		return p1.getDomain().containsAll(p2.getDomain())
				&& p2.getDomain().containsAll(p1.getDomain());
	}

	/**
	 * This method returns the Kullback-Leibler divergence between the
	 * probability distributions p1 and p2. p1 and p2 must both be probability
	 * distributions over the same set of observables A={a1, ... an}.<br>
	 * By convention, we'll always put the prob distribution from corpora or
	 * other empirical observation as the first argument, and the prob
	 * distributions used for keyboard layouts as the second. That way, we'll
	 * assume the domain of p1 is the set A (e.g., the keyboard may have
	 * selectables that never occur in the empirical observations).
	 * 
	 * Pre-processing of the text corpus ensures that no character occurs that
	 * is not also present in the TCF.
	 * 
	 * With probability distributions, we can guarantee that pi(a)!=0
	 * 
	 * KLD: D(p1,p2) = sum_{ai \in A} p1(a) log ( p1(a)/p2(a) )
	 * 
	 * return null if domains not the same
	 */
	public static Double getKLDivergence(ProbabilityDistribution p1,
			ProbabilityDistribution p2) {

		// check that the domains of the probability distributions are the same

		// for now, I'm not going to double-check that the domains
		// of the probability distributions are the same
		Iterator it = p1.getDomainIterator();
		double cumVal = 0;
		double tmpVal = 0;
		while (it.hasNext()) {
			// String a = (String) it.next();
			// tmpVal = p1.get(a) * Math.log(p1.get(a) / p2.get(a));
			cumVal += tmpVal;
		}
		return cumVal;
	}

	public String getIdentifier() {
		// return identifier;
		return this.identifier;

	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * For each element e_i in this probability distribution, this method
	 * determines the term E_i such that E_i is a power of k, where k is the
	 * passed integer and where E_i is as close as possible to e_i.
	 * 
	 * @param k
	 * @return
	 */
	public double getResidual(int k) {
		final double EPSILON = 1e-5;
		double prob;
		double newProb1, newProb2;
		double trueExponent;
		long intExponentLower, intExponentHigher;
		double delta1, delta2;
		double residual = 0.0;
		for (SourceSymbol o : this.getDomain()) {
			prob = this.get(o);
			trueExponent = logBaseK(prob, k);
			if (Math.abs(trueExponent - Math.round(trueExponent)) <= EPSILON) {
				// true exponent is practically an integer
			}
			intExponentLower = Math.round(trueExponent - 0.5);
			intExponentHigher = intExponentLower + 1;
			newProb1 = Math.pow(k, intExponentLower);
			newProb2 = Math.pow(k, intExponentHigher);
			delta1 = Math.abs(newProb1 - prob);
			delta2 = Math.abs(newProb2 - prob);
			if (delta1 < delta2) {
				residual += delta1;
			} else {
				residual += delta2;
			}
			// System.out.println(prob + "\t" + trueExponent + "\t"
			// + intExponentLower + "\t" + newProb1 + "\t" + delta1 + "\t"
			// + intExponentHigher + "\t" + newProb2 + "\t" + delta2 + "\t" +
			// (delta1>delta2));
		}
		return residual;
	}

	/**
	 * Added by Fatima Check Probability
	 * 
	 * @param s
	 * @return
	 */
	public Double getSumOfProbabilities() {
		Iterator<Double> it = (values.values()).iterator();
		Double sum = 0.0;
		while (it.hasNext()) {
			Double d = it.next();
			sum += d;
		}
		return sum;
	}

	public void renormalize() {
		double sum = getSumOfProbabilities();
		for (SourceSymbol sourceSymbol : values.keySet()) {
			values.put(sourceSymbol, values.get(sourceSymbol) / sum);
		}
	}

	/**
	 * This method checks the sum of the probabilities. Returns whether |1-sum| <
	 * epsilon
	 * 
	 * (i.e., the sum is valid if it is close enough to 1)
	 * 
	 * [MB]
	 */
	public boolean isProbabilitySumValid() {
		final double EPSILON = 1e-5;
		return (Math.abs(1 - this.getSumOfProbabilities()) > EPSILON);
	}

	/**
	 * This method returns the log base k of the passed value d.
	 * 
	 * what to the exponent k will yield d?
	 * 
	 * PRE k should be => d geq 2, d should be gt 1
	 */
	public static double logBaseK(double d, int k) {
		final double EPSILON = 1e-5;
		if (Math.abs(d - 1.0) < EPSILON) {
			return 0;
		} else
			return Math.log(d) / Math.log(k);
	}

}
