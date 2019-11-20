package encodingTrees;

import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import coreIndirectSelectionFacility.ISFLoggerServices;

import customGUIComponentsISF.JIndirectSelectionButton;

import encodingAlphabetsISF.EncodingSymbolAlphabet;

import sourceSymbolSet.SourceSymbol;

import PredictionModellingISF.UserModel;
import treeDataStructure.Node;

/**
 * This class implements a code, which is a set of $n$ prefix-free codewords
 * which correspond to each of $n$ source symbols (selectables).
 * 
 * The codewords are composed of the characters from $\Sigma$, the
 * \emph{encoding alphabet}. We assume that $\Sigma = \{ 1, ...,r\}$.
 * 
 * The code can be represented by a tree (containment hierarchy).
 * 
 * Moreover, for any codeword, there is (1) a correspondence between its
 * characters and a sequence of action events (either user- or system-initiated)
 * and (2) a correspondence between its characters and a sequence of user
 * actions (either wait (W), which means to wait for a system-initiated action
 * to occur, or select (S), which means to perform a select action, which
 * results in a user-initiated action.
 * 
 * @author mb
 * @param <X>
 * 
 */
public class Code {

	public static final String DELIM = "%";

	public static final String WASTED_CYCLE_REGEX_LIT = "\\(W\\{\\d+\\}\\)\\*";

	// private String WASTED_CYCLE_REGEX = "(W{\\d+\\}\\)\\*";

	public static final String ERRONEOUS_DESCENT_REGEX_LIT = "\\(W\\*SW\\*A\\)\\*";

	// regex (W*SW*A)* or (SW*A)

	// public static final String ERRONEOUS_DESCENT_REGEX = "(W*SW*A)*";
	// public static final String ERRONEOUS_DESCENT_REGEX = "(SW*A)";
	public static final String ERRONEOUS_DESCENT_REGEX = "W*SW*A";

	private Node rootOfEncodingTree;

	private List<CodeWord> codeWords = new Vector<CodeWord>();

	//
	// private List<Integer> levelRegexes;

	// public Code(Node<SelectionGroupMember> root) {
	public Code(Node node) {
		this.setRoot(node);
		getRoot().propogateLabels("0");
		this.addCodeWords(getRoot().getCode());
	}

	public Code(List<CodeWord> cw) {
		this.addCodeWords(cw);
	}

	public List<CodeWord> getCodeWords() {
		return codeWords;
	}

	public List<CodeWord> getCodeWordsExcludingCodeWordsForFillerSourceSymbols() {
		List<CodeWord> mod = new Vector<CodeWord>();
		for (CodeWord rule : getCodeWords()) {
			if (!rule.getSourceSymbol().equals(
					JIndirectSelectionButton.VK_FILLER)) {
				mod.add(rule);
			}
		}
		return mod;
	}

	/**
	 * This method adds codewords to this code. Each codeword is provided with
	 * its corresponding source symbol.
	 * 
	 * 
	 * 
	 * am buf one or more lines (delimited by end-of-line \n), of the form
	 * W*S*<x>, where x is a source symbol (Selectable). The portion "W*S*"
	 * corresponds to a sequence of user actions.
	 * 
	 */
	public void addRules(String buf) {
		StringTokenizer tok = new StringTokenizer(buf, "\n");
		String line;
		while (tok.hasMoreTokens()) {
			line = tok.nextToken();
			getCodeWords().add(new CodeWord(line));
		}
	}

	private void addCodeWords(List<CodeWord> codewords) {
		for (CodeWord cw : codewords) {
			getCodeWords().add(cw);
			// System.out.println(cw);
			// codeWords.add(new CodeWord(cw));
		}
	}

	/**
	 * 
	 * 
	 * @param selectable
	 *            the representation of the selectable
	 * @return
	 */
	public CodeWord getCodeWordForSelectable(String selectable) {
		for (CodeWord rule : getCodeWords()) {
			if (rule.getSourceSymbolAsString().equals(selectable)) {
				return rule;
			}
		}
		return null;
	}

	public CodeWord getCodeWordForSelectable(SourceSymbol selectable) {
		for (CodeWord rule : getCodeWords()) {
			// System.out.println(rule.getSourceSymbol() + "\t" + selectable);
			if (rule.getSourceSymbol().equals(selectable)) {
				// System.out.println(rule);
				return rule;
			}
		}
		return null;
	}

	public double getMeanOutDegree() {
		int tmp1 = getRoot().getNumEdges();
		int tmp2 = getRoot().getNumNodesInternalOnly();
		double meanOutDegree = tmp1 * 1.0 / tmp2;
		return meanOutDegree;
	}

	public Node getRoot() {
		return rootOfEncodingTree;
	}

	public void setRoot(Node encodingTree) {
		this.rootOfEncodingTree = encodingTree;
	}

	/**
	 * returns the mean number of reactive actions (selects)
	 * 
	 * @return
	 */
	public double getMeanNumRA() {
		double meanNumRA = 0.0;
		for (CodeWord rule : this.getCodeWords()) {
			double prob = rule.getProbability();
			meanNumRA += (prob * rule.getNumReactiveActions());
		}
		return meanNumRA;
	}

	/**
	 * returns the mean number of passive actions (waits)
	 * 
	 * @return
	 */
	public double getMeanNumPA() {
		double meanNumPA = 0.0;
		for (CodeWord rule : this.getCodeWords()) {
			double prob = rule.getProbability();
			meanNumPA += (prob * rule.getNumPassiveActions());
		}
		return meanNumPA;
	}

	/**
	 * returns the mean number of actions (waits + selects)
	 * 
	 * @return
	 */
	public double getMeanNumActions() {
		double meanNumActions = 0.0;
		for (CodeWord rule : this.getCodeWords()) {
			double prob = rule.getProbability();
			meanNumActions += prob
					* (rule.getNumPassiveActions() + rule
							.getNumReactiveActions());
		}
		return meanNumActions;
	}

	/**
	 * returns the mean number of operations per selectable (waits + selects)
	 * 
	 * @return
	 */
	public double getMeanEncodingCost() {
		double meanLength = 0.0;
		for (CodeWord codeWord : this.getCodeWords()) {
			double prob = codeWord.getProbability();
			meanLength += prob * (codeWord.getCost());
		}
		return meanLength;
	}

	public double getMeanEncodingCost(EncodingSymbolAlphabet encodingAlphabet) {
		double meanLength = 0.0;
		for (CodeWord codeWord : this.getCodeWords()) {
			double prob = codeWord.getProbability();
			meanLength += prob * (codeWord.getCost(encodingAlphabet));
		}
		return meanLength;
	}

	/**
	 * This method finds the size of the encoding alphabet that is used in this
	 * code; the encoding alphabet that is actually used (i.e., functionally),
	 * which may be different from the specified encoding alphabet (in cases in
	 * which this code was generated by an algorithm, a largerer encoding
	 * alphabet may have been specified, but the resulting tree may not end up
	 * using all of the encoding symbols from that alphabet, depending on the
	 * costs of the symbols).
	 * 
	 * @return
	 */
	public double getEncodingAlphabetSizeFunctional() {
		return getRoot().findLargestOutdegree(0);
		// return encodingAlphabetSize;
	}

	/**
	 * returns the mean number of reactive actions (selects)
	 * 
	 * @return
	 */
	public double getMeanTime(UserModel um) {
		double meanTime = 0.0;
		for (CodeWord rule : this.getCodeWords()) {
			double prob = rule.getProbability();
			meanTime += (prob * rule
					.getTimeToTransmitSourceSymbolLowerBound(um));
		}
		return meanTime;
	}

	public String toStringLaTeX() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\noindent\n");
		buf.append("\\noindent Mean Cost: " + getMeanEncodingCost() + "\\\\\n");

		// buf.append(CodeWord.toStringLaTeXHeader());
		// for (CodeWord rule : codeWords) {
		// buf.append(rule.toStringLaTeX() + "\\\\\n");
		// }

		buf.append("\\begin{tabular}{l l l l l}\n");
		buf.append("Source Symbol" +
		// "User Action Seq." + "\t&\t"
				// + "Encoding" + "\t&\t" +
				"\t&\t" + "Cost" + "\\\\\n");

		for (CodeWord codeWord : getCodeWords()) {
			// buf.append(rule.toStringLaTeX() + "\\\\\n");
			// buf.append("" + codeWord.getSourceSymbolAsString() + "\t&\t"
			// + codeWord.getEncoding() + "\t&\t"
			// + codeWord.toStringLaTeXShortForm() + "\t&\t"
			// + codeWord.getCost() + "\\\\\n");
			buf.append("" + codeWord.getSourceSymbolAsString() + "\t&\t"
					+ codeWord.getRHSGeneral() + "\t&\t" + codeWord.getCost()
					+ "\t&\t" + codeWord.getProbability() + "\\\\\n");
			// buf.append("" + codeWord.toStringLaTeXShortForm() + "\t&\t"
			// + codeWord.getCost() + "\t&\t" + codeWord.getProbability()
			// + "\\\\\n");
		}
		buf.append("\\end{tabular}");

		return buf.toString();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (CodeWord rule : getCodeWords()) {
			buf.append(rule.toStringElab() + "\n");
		}
		return buf.toString();
	}

	public String toStringCostTable() {
		StringBuffer buf = new StringBuffer();
		for (CodeWord rule : getCodeWords()) {
			buf.append(rule.toStringCostInfo() + "\n");
		}
		return buf.toString();
	}

	/**
	 * 
	 * 
	 * @param um
	 * @return predicted mean time to enter a single character (msec)
	 */
	public double getMeanTimeToTransmitSourceSymbolLowerBound(UserModel um) {
		double meanTime = 0.0;
		for (CodeWord codeword : getCodeWords()) {
			double prob = codeword.getProbability();
			meanTime += (prob * codeword
					.getTimeToTransmitSourceSymbolLowerBound(um));
		}
		return meanTime;
	}

	public double getMeanTimeToTransmitSourceSymbolUpperBound(UserModel um1) {
		double meanTime = 0.0;
		for (CodeWord codeword : getCodeWords()) {
			double prob = codeword.getProbability();
			meanTime += (prob * codeword
					.getTimeToTransmitSourceSymbolUpperBound(um1));
		}
		return meanTime;
	}

	public double getPredictedWPMUpperBound(UserModel um) {
		// get mean time to enter, but only over characters
		String CHARACTER_REGEX = "[a-zA-Z]";
		double meanTimeCharactersOnly = getMeanTimeToTransmitSourceSymbolLowerBound(um);
		for (CodeWord codeword : getCodeWords()) {
			double prob = codeword.getProbability();
			if (codeword.getSourceSymbolAsString().matches(CHARACTER_REGEX))
				meanTimeCharactersOnly += (prob * codeword
						.getTimeToTransmitSourceSymbolLowerBound(um));
		}
		double meanTimePerSpace = 0.0;

		// System.out.println(JIndirectSelectionButton.VK_SPACE_TEXT
		// .toStringLaTeX());

		CodeWord spaceCodeword = this
				.getCodeWordForSelectable(JIndirectSelectionButton.VK_SPACE_TEXT
						.toStringLaTeX());

		if (spaceCodeword != null) {
			meanTimePerSpace = spaceCodeword
					.getTimeToTransmitSourceSymbolLowerBound(um);
		} else {
			System.out
					.println("***********NO SPACE CHARACTER FOUND FOR WPM CALCULATIONS************");
		}

		// System.out.println("# mean time per word char: "
		// + meanTimeCharactersOnly);
		// System.out.println("# mean time per space char: " +
		// meanTimePerSpace);
		double meanTimePerWord = meanTimeCharactersOnly * 5 + meanTimePerSpace;

		// meanTimePerWord is in msec, each minute has 60 seconds * 1000 msec
		return (60.0 * 1000) / meanTimePerWord;
	}

	public String toStringForLoggerFileToBeParsed() {
		StringBuffer buf = new StringBuffer();
		buf
				.append(ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_ENCODING_TREE);
		for (CodeWord cw : this.getCodeWords()) {
			// getLogger().finer("R(" + cw.toString() + ")");
			buf.append(cw.toStringParseableFormat() + "\n");
			// System.out
			// .println("1." + cw.getSourceSymbol().getClass().getName());
			// System.out.println(cw.toStringParseableFormat());
			// CodeWord cwCopy = new CodeWord(cw.toStringParseableFormat());
			// System.out.println("2."
			// + cwCopy.getSourceSymbol().getClass().getName());
			// cw2.add(cwCopy);
			// System.out.println(cwCopy.toStringParseableFormat());
		}
		return buf.toString();
	}

	public static String getLoggerFileUniqueIdentifier() {
		return "ENCODINGTREE";
	}

	// public CodeWord getCodeWord(AtomicActionSequence atomicActionSequence) {
	// // CodeWord rule = theCode.getCodeWordForSelectable(this
	// // .getSelectableEmpirical());
	//
	// return
	// this.getCodeWordForSelectable(atomicActionSequence.getSourceSymbol());
	// }
}
