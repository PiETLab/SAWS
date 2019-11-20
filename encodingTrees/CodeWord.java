package encodingTrees;

import PredictionModellingISF.UserModel;

import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import encodingAlphabetsISF.EncodingSymbolAlphabet;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolParsedFromLogfile;

/**
 * 
 * This class implements a codeword, which is composed of the characters from
 * $\Sigma$, the \emph{encoding alphabet}.
 * 
 * Each codeword corresponds to a source symbol.
 * 
 * Suppose we have a sequence of atomic actions that culminates in the selection
 * of a selectable (c_i) (traversal of the encoding tree from root to the leaf
 * that contains c_i). The sequence consists of waits, selects, and (possibly)
 * ascends. Once incorrect descents and wasted cycles are stripped away, the
 * sequence can be matched to a codeword. The codeword's corresponding source
 * symbol should be c_i. If it isn't then there is a discrepency between the
 * code and the encoding tree that was used during the generation of the user's
 * sequence of actions. The atomic actions can be either user- or
 * system-initiated.
 * 
 * sourceSymbol is a selectable, actionEventSequence is regexp that matches
 * 
 * @author mb
 * 
 */
public class CodeWord {

	private final static String LATEX_LEFT_ARROW = " $\\leftarrow$ ";
	private final static String LATEX_RIGHT_ARROW = " $\\rightarrow$ ";

	// <Selectable> <=== actionEventSequence WWSWWSS

	private SourceSymbol theSourceSymbol;

	private List<String> encoding;

	private List<Integer> costsByLevel;

	private String actionEventSequence;

	// the probability of the given selectable
	// private double sourceSymbolProbability;

	private List<String> wastedCycleRegexesByLevel;

	private List<String> levelRegexes;
	private boolean IS_VERBOSE = false;

	// private String wastedCycleRegexesByLevel;

	/**
	 * 
	 * 
	 * @param str
	 *            of the form (W...W)*W*(W...W)*S*%x%0.999876, where
	 * 
	 *            the first token is the rule
	 * 
	 *            the second token in the selectable x
	 * 
	 *            the thrid token is the prob of that selectable
	 */
	public CodeWord(String str) {
		// eg: for the selectable "F"
		// str=
		// str=
		// "(W{2})*S(W{2})*S(W{2})*S(W{2})*S(W{2})*WS(W{2})*S%F%0.0214952992978698"
		// Syst

		StringTokenizer tok = new StringTokenizer(str, Code.DELIM);
		String sourceSymbol;
		double sourceSymbolProbability;
		try {
			actionEventSequence = tok.nextToken();
			sourceSymbol = tok.nextToken();
			// sourceSymbol = sourceSymbol.replaceAll("\\*", "");
			sourceSymbolProbability = Double.parseDouble(tok.nextToken());
			this.theSourceSymbol = new SourceSymbolParsedFromLogfile(
					sourceSymbol, sourceSymbolProbability);
		} catch (Exception e) {
			System.out.println("Trouble parsing grammar rule");
			e.printStackTrace();
		}

		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ " deriving code word from : " + str);
			System.out.println(this.getClass().getName() + " so far:  "
					+ this.toStringPlain());
		}

		levelRegexes = new Vector<String>();
		// HERE WE DETERMINE WHAT RULE APPLIES FOR EACH LEVEL OF THE CH
		// THE GRAMMAR RULE DOES NOT INCLUDE ERRONEOUS DESCENTS

		// search for S and take everything up to and including S
		String[] splitBySelectAction = actionEventSequence
				.split(AtomicAction.SELECT);
		// now we have a sequence of the form (W{k})*(W{k})*...(W{k})* (with
		// various possible values of k, including duplicates
		// there might be different values of k, though
		for (int i = 0; i < splitBySelectAction.length; i++) {
			levelRegexes.add(splitBySelectAction[i] + AtomicAction.SELECT);
		}
		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ " level regexes derived: " + levelRegexes);
		}

		// split for W*S, which occur between the wasted cycle regexes
		String[] tmp5 = actionEventSequence.split(AtomicAction.WAIT + "*"
				+ AtomicAction.SELECT);
		// now we have a sequence of the form (W{k})*(W{k})*...(W{k})* (with
		// various possible values of k, including duplicates
		// there might be different values of k, though
		wastedCycleRegexesByLevel = new Vector<String>();
		// elements will be of the form (W{8})* or (W{6})*W{1}
		// want to extract just the W{8} or W{6} parts
		for (int i = 0; i < tmp5.length; i++) {
			if (IS_VERBOSE) {
				System.out.println(this.getClass().getName()
						+ " wc regexes extraction: " + tmp5[i]);
			}
			wastedCycleRegexesByLevel.add(tmp5[i].substring(1, tmp5[i]
					.indexOf("*") - 1));
		}
		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ " wc regexes derived: " + wastedCycleRegexesByLevel);
		}
	}

	/**
	 * @param encoding
	 *            : of the form n1(t1)n2(t2)...nk(tk) where ni is the child
	 *            number (or character from the encoding alphabet) and ti is the
	 *            total number of characters used from the encoding alphabet at
	 *            the level in the encoding tree at which ni has been used
	 * @param sourceSymbol
	 * @param expectedFreq
	 */
	public CodeWord(List<String> encoding, List<String> cycleSizes,
			List<Integer> levelCosts, SourceSymbol sourceSymbol,
			double expectedFreq) {
		this.costsByLevel = levelCosts;
		// System.out.println(getLevelCosts());
		this.encoding = encoding;
		this.theSourceSymbol = sourceSymbol;
		// this.sourceSymbol = sourceSymbol2.toStringLaTeX();
		// this.sourceSymbolProbability = expectedFreq;
		// modified by Fatima
		// this.userActionSequence = genearateUserActionSequence(cycleSizes);

		this.actionEventSequence = genearateUserActionSequence(cycleSizes);

		levelRegexes = new Vector<String>();
		// HERE WE DETERMINE WHAT RULE APPLIES FOR EACH LEVEL OF THE CH
		// THE GRAMMAR RULE DOES NOT INCLUDE ERRONEOUS DESCENTS

		// search for S and take everything up to and including S
		String[] splitBySelectAction = actionEventSequence
				.split(AtomicAction.SELECT);
		// now we have a sequence of the form (W{k})*(W{k})*...(W{k})* (with
		// various possible values of k, including duplicates
		// there might be different values of k, though
		for (int i = 0; i < splitBySelectAction.length; i++) {
			levelRegexes.add(splitBySelectAction[i] + AtomicAction.SELECT);
		}

		// split for W*S, which occur between the wasted cycle regexes
		String[] tmp5 = actionEventSequence.split(AtomicAction.WAIT + "*"
				+ AtomicAction.SELECT);
		// now we have a sequence of the form (W{k})*(W{k})*...(W{k})* (with
		// various possible values of k, including duplicates
		// there might be different values of k, though

		// added by Fatima

		for (int i = 0; i < tmp5.length; i++) {
			String tmp6 = tmp5[i];
			int index = tmp6.indexOf("*");
			tmp5[i] = tmp6.substring(0, index + 1);

		}

		wastedCycleRegexesByLevel = new Vector<String>();
		for (int i = 0; i < tmp5.length; i++) {
			wastedCycleRegexesByLevel.add(tmp5[i].substring(1,
					tmp5[i].length() - 2));
		}
	}

	/**
	 * This method generates the sequence of user events that corresponds to the
	 * production of the given encoding (which is given as a sequence of
	 * characters from the encoding alphabet, which corresponds to a traversal
	 * to be followed in the encoding tree).
	 * 
	 * @param cycleSizes
	 * @return
	 */
	private String genearateUserActionSequence(List<String> cycleSizes) {
		// final String REGEX1 = "\\d+\\("; // will match any string of the form
		// final String REGEX2 = "\\)"; // will match any string of the form
		// "n1...nk(", where ni are digits, at least one
		String userActionSequence = "";

		int currLevel = 0;
		for (String s : cycleSizes) {
			String tmp = s;
			// tmp = tmp.replaceAll(REGEX1, "");
			// tmp = tmp.replaceAll(REGEX2, "");
			String wastedCycle = "(" + AtomicAction.WAIT + "{"
					+ Integer.parseInt(tmp) + "})*";
			userActionSequence += wastedCycle;
			int pos = Integer.parseInt(getEncoding().get(currLevel));
			String waiting = "";
			if (pos != 0) {
				waiting = AtomicAction.WAIT + "{" + pos + "}";
			}
			userActionSequence += waiting + AtomicAction.SELECT;
			currLevel++;
		}
		return userActionSequence;
	}

	/**
	 * This method returns a List, the i-th element gives the regular expression
	 * for a wasted cycle at the i-th level of the containment hierarchy
	 * 
	 * @return
	 */
	public List<String> getWastedCycleDescriptions() {
		return wastedCycleRegexesByLevel;
	}

	public double getCost() {
		double cost = 0;
		for (Integer d : getCostsByLevel()) {
			cost += d;
		}
		return cost;
	}

	public double getCost(EncodingSymbolAlphabet encodingAlphabet) {
		double cost = 0;

		double costSpecificToLevel = 0;
		for (int level = 0; level < this.getLevelDescriptionsOptimal().size(); level++) {
			costSpecificToLevel = encodingAlphabet.getCost(getPosition(level));
			cost += costSpecificToLevel;
		}
		return cost;
	}

	public List<String> getEncoding() {
		return this.encoding;
	}

	public List<Integer> getCostsByLevel() {
		return this.costsByLevel;
	}

	public List<String> getLevelDescriptions() {
		return levelRegexes;
	}

	public List<String> getLevelDescriptionsOptimal() {
		List<String> list = new Vector<String>();
		for (String level : getLevelDescriptions()) {
			list.add(level.replaceAll(Code.WASTED_CYCLE_REGEX_LIT, ""));
		}
		return list;
	}

	/**
	 * @param level
	 * @return the sibling position for the encoding symbol at the passed level
	 */
	public int getPosition(int level) {
		int pos;
		List<String> list = this.getLevelDescriptionsOptimal();
		String levelDescriptor = list.get(level);
		// will be of the form S or W{n}S,
		if (levelDescriptor.equals(AtomicAction.SELECT)) {
			pos = 0;
		} else {
			StringTokenizer tok = new StringTokenizer(levelDescriptor, "{}");
			String t = tok.nextToken();
			t = tok.nextToken();
			pos = Integer.parseInt(t);
		}
		return pos;
	}

	public double getMeanPositionPastFirstLevel() {
		int maxLevels = this.getLevelDescriptionsOptimal().size();
		double pos = 0;
		for (int level = 1; level < maxLevels; level++) {
			pos += getPosition(level);
		}
		return pos / maxLevels;
	}

	// public String identify(AtomicActionSequence aas) {
	public String identify(String incorrectDescentsRemoved) {
		// // String stripped =
		// aas.removeIncorrectDescents(aas.getTimeStripped());

		// String stripped = aas.removeIncorrectDescents(aas.toString());
		// // can't simply use regular expression matching because erroneous
		// // descents may be nested.
		// boolean shouldContinue = Pattern.matches(".*"
		// + Code.ERRONEOUS_DESCENT_REGEX + ".*", stripped);
		// while (shouldContinue) {
		// stripped = stripped.replaceAll(
		// Code.ERRONEOUS_DESCENT_REGEX, "");
		// // output.println(tmp3);
		// shouldContinue = Pattern.matches(".*"
		// + Code.ERRONEOUS_DESCENT_REGEX + ".*", stripped);
		// }
		// if (stripped.matches(actionEventSequence))
		if (incorrectDescentsRemoved.matches(actionEventSequence))
			return getSourceSymbolAsString();
		else
			return null;
	}

	public String getLHS() {
		return getSourceSymbolAsString();
	}

	public double getProbability() {
		return this.theSourceSymbol.getMarginalProbability();
	}

	public String getSourceSymbolAsString() {
		// return this.sourceSymbol;
		return theSourceSymbol.toStringLaTeX();
	}

	public SourceSymbol getSourceSymbol() {
		return this.theSourceSymbol;
	}

	// public SourceSymbol getSourceSymbol() {
	// return this.sourceSymbol;
	// }
	//

	public String getRHSGeneral() {
		return actionEventSequence;
	}

	public String getRHSOptimal() {
		String tmp3 = actionEventSequence.replaceAll(
				Code.ERRONEOUS_DESCENT_REGEX_LIT, "");
		tmp3 = tmp3.replaceAll(Code.WASTED_CYCLE_REGEX_LIT, "");
		return tmp3;
	}

	public String getRHSOptimalLaTeX() {
		String tmp3 = getRHSOptimal();
		String res = "";
		for (int i = 0; i < tmp3.length(); i++) {
			String tmp = tmp3.substring(i, i + 1);
			try {
				Integer.parseInt(tmp);
				res = res + "$^{" + tmp + "}$";
			} catch (NumberFormatException e) {
				res = res + tmp;

			}

		}
		return res;
	}

	public int getNumReactiveActions() {
		String str = getRHSOptimal().replaceAll(AtomicAction.SELECT, "%");
		str = str.replaceAll("[^%]", "");
		return str.length();
	}

	public int getNumPassiveActions() {
		String str = getRHSOptimal().replaceAll(AtomicAction.WAIT, "%");
		str = str.replaceAll("[^%]", "");
		return str.length();
	}

	/**
	 * 
	 * @param um
	 * @return A lower bound on the time to transmit a source symbol, based on
	 *         the model in which the user must wait for focus advances and
	 *         reacts to select target when the target is in focus with the
	 *         speed that is indicated in the user model. No wasted cycles or
	 *         incorrect descents occur
	 */
	public double getTimeToTransmitSourceSymbolLowerBound(UserModel um) {
		return getNumPassiveActions() * um.getDwellTime()
				+ getNumReactiveActions() * um.getReactionTime();
	}

	/**
	 * 
	 * @param um
	 * @return A lower bound on the time to transmit a source symbol, based on
	 *         the model in which the user must wait for focus advances and
	 *         reacts to select target when the target is in focus with the
	 *         speed that is indicated in the user model. Also, it factors in
	 *         the occurrence of wasted cycles, as indicated in the stochastic
	 *         model in the user model. **** NOT YET IMPLEMENTED****
	 */
	public double getTimeToTransmitSourceSymbolUpperBound(UserModel um) {
		return getNumPassiveActions() * um.getDwellTime()
				+ getNumReactiveActions() * um.getReactionTime();
	}

	/**
	 * 
	 * @return amount of time, assuming 0msec reaction time for select actions
	 *         and no wasted cycles
	 */
	// public int getLowerBound() {
	// for (int i = 0; i < actionEventSequence)
	//		
	// }
	public static String toStringLaTeXHeader() {
		return "\\noindent sourceSymbol" + LATEX_LEFT_ARROW
				+ "userActionSequence" + LATEX_LEFT_ARROW
				+ "actionEventSequence" + LATEX_LEFT_ARROW + "encoding"
				+ "\\\\\n";
	}

	public String toStringLaTeX() {
		StringBuffer buf = new StringBuffer();
		buf.append(getSourceSymbolAsString() + LATEX_LEFT_ARROW
				+ getRHSGeneral() + LATEX_LEFT_ARROW + encoding);
		return buf.toString();
	}

	public String toStringLaTeXShortForm() {
		StringBuffer buf = new StringBuffer();
		buf.append(getSourceSymbolAsString() + LATEX_RIGHT_ARROW
				+ getRHSOptimalLaTeX());
		return buf.toString();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(getSourceSymbolAsString() + LATEX_LEFT_ARROW
				+ getRHSGeneral());
		return buf.toString();
	}

	public String toStringParseableFormat() {
		// "(W{2})*S(W{2})*S(W{2})*S(W{2})*S(W{2})*WS(W{2})*S%F%0.0214952992978698"

		StringBuffer buf = new StringBuffer();
		buf.append(getRHSGeneral() + "%" + getSourceSymbolAsString() + "%"
				+ this.getProbability());
		return buf.toString();
	}

	public String toStringPlain() {
		StringBuffer buf = new StringBuffer();
		buf.append(getRHSGeneral());
		return buf.toString();
	}

	public String toStringNumeric() {
		StringBuffer buf = new StringBuffer();
		String str = "";
		for (int level = 0; level < this.getLevelDescriptionsOptimal().size(); level++) {
			str = str + getPosition(level);
		}
		buf.append(str);
		return buf.toString();
	}

	public String toStringElab() {
		StringBuffer buf = new StringBuffer();
		buf.append(getSourceSymbolAsString() + "\t" + this.getEncoding() + "\t"
				+ getRHSGeneral() + "\t" + getWastedCycleDescriptions() + "\t"
				+ getLevelDescriptions());
		return buf.toString();
	}

	public String toStringCostInfo() {
		StringBuffer buf = new StringBuffer();
		buf.append("Pr(" + getSourceSymbolAsString() + ")=" + getProbability()
				+ "\tcodeword(" + getSourceSymbolAsString() + ")="
				+ this.getEncoding() + "\t" + "cost("
				+ getSourceSymbolAsString() + ")=" + getCost());
		return buf.toString();
	}
}
