/*
 * Created on 9-Sep-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package UtilityClassesISF;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;


// import java.util.StringTokenizer;

/**
 * This class implements a probability distribution over the set of all possible
 * selectables.
 * 
 * @author Melanie Baljko
 */
public abstract class OBSOLETE_ProbabilityDistribution {

	Hashtable<JIndirectSelectionButton, Double> values;

	public OBSOLETE_ProbabilityDistribution() {
		values = new Hashtable<JIndirectSelectionButton, Double>();
	}

	public void put(JIndirectSelectionButton s, double d) {
		values.put(s, new Double(d));
	}

	/**
	 * Get the probability associated with the action command given by the
	 * parameter s. If s is not present in the domain of this probability
	 * distribution, then a null pointer exception is thrown
	 * 
	 * PRE that s is present in the domain of this probability distribution
	 * 
	 * @param s
	 * @return as described above
	 */
	// public double get(String s) throws NullPointerException {
	// return ((Double) values.get(s)).doubleValue();
	// }
	public double get(JIndirectSelectionButton but) {
		return values.get(but);
	}

	public boolean isPresent(String s) {
		return values.get(s) != null;
	}

	public Iterator<JIndirectSelectionButton> getDomainIterator() {
		return values.keySet().iterator();
	}

	public Set<JIndirectSelectionButton> getDomain() {
		return values.keySet();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		Set<JIndirectSelectionButton> s = values.keySet();
		List<JIndirectSelectionButton> l = new Vector<JIndirectSelectionButton>();
		l.addAll(s);
		Collections.<JIndirectSelectionButton> sort(l);

		for (JIndirectSelectionButton but : l) {
			buf.append("" + but.toStringLaTeX() + "\t" + get(but) + "\n");
		}
		return buf.toString();
	}

	public String toStringLaTeX() {
		StringBuffer buf = new StringBuffer();
		Set<JIndirectSelectionButton> s = values.keySet();
		List<JIndirectSelectionButton> l = new Vector<JIndirectSelectionButton>();
		l.addAll(s);
		Collections.<JIndirectSelectionButton> sort(l);

		buf.append("\\begin{tabular}{l l}");
		for (JIndirectSelectionButton but : l) {
			buf
					.append("" + but.toStringLaTeX() + "\t&\t" + get(but)
							+ "\\\\\n");
		}
		buf.append("\\end{tabular}");
		return buf.toString();
	}

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
	public static boolean isSameDomain(OBSOLETE_ProbabilityDistribution p1,
			OBSOLETE_ProbabilityDistribution p2) {
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
	 */
	public static double getKLDivergence(OBSOLETE_ProbabilityDistribution p1,
			OBSOLETE_ProbabilityDistribution p2) {
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

}
