package abstractOnScreenIndirectSelectionKeyboard;

import java.util.List;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

import UtilityClassesISF.ProbabilityDistribution;

import encodingTrees.Code;
import encodingTrees.CodeWord;
import encodingTrees.obsolete.TraversableEncodingTreeI;
import treeDataStructure.Node;
import treeDataStructure.SelectionGroup;

/**
 * This class implements an on-screen indirect selection keyboard.
 * 
 * This object has (1) a encoding tree (ET) and (2) a keyboard layout (KBL).
 * 
 * (1) One technique is to specify the KBL first, then build the ET from it. The
 * ET can be derived directly from the KBL (e.g., using the rows and columns as
 * the basis for the structure of the ET and the order of the nodes within it).
 * 
 * (2) Another technique is to build the ET. We implement Huffman encoding (both
 * with equal and unequal encoding symbol costs). The Huffman algorithms require
 * specification of the encoding alphabet and a probability distribution over
 * the source symbols (the selectable set). Then, the KBL is specified
 * separately (subject to the condition that it affords access to the
 * selectables in the domain of the PD). Various techniques for automatic KBL
 * derivation have been implemented (see that class)
 * 
 * 
 * The KBL is a set of buttons, arranged in a particular fashion on the screen.
 * 
 * It is desirable that every button on the KBL appear in the CH (otherwise, it
 * cannot be selected).
 * 
 * 
 * 
 * <p>
 * A keyboardLayout layout consists of a grid upon which buttons, which can take
 * horizontally one or more grid units, are placed. The buttons are denoted by
 * their (i,j) position, where i and j are relative to other buttons (and not to
 * the grid unit)
 * 
 * This class implements case(3), where the CH is build first using the Huffman
 * encoding algorithm with unequal letter costs. The KBL is build afterwards
 * using this CH.
 * 
 * @author Melanie Baljko
 * @author Fatima (modified the original version of the class, written by Baljko
 *         as IndirectSelecitonKeyboard)
 */
public abstract class AbstractOnScreenIndirectSelectionKeyboard_ETthenKBL
		extends AbstractOnScreenIndirectSelectionKeyboard {

	// protected KeyboardLayout keyboardLayout = null;

	protected TraversableEncodingTreeI containmentHierarchy = null;

	protected final Code code = null;

	protected UtilityClassesISF.ProbabilityDistribution initialProbDistribution = null;

	protected UtilityClassesISF.ProbabilityDistribution normalizedProbDistribution = null;

	// added by Fatima
	protected SourceSymbolSet selectableKeyList;

	/**
	 * This is the constructor used to build an indirect selection keyboard
	 * based on a CH derived from Huffman algorithm with Unequal letter cost.
	 * 
	 * @author Fatima
	 */
	public AbstractOnScreenIndirectSelectionKeyboard_ETthenKBL(SourceSymbolSet pd) {
		super(pd);
		// UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton> pd) {
		// int encodingAlphabetSize) {
	}

	public abstract ButtonLayoutSpecification setUpKeyboardLayout();

	public ButtonLayoutSpecification getKeyboardLayout() {
		return super.getKeyboardLayout();
	}

	/**
	 * This method builds an encoding tree using an encoding alphabet of the
	 * size indicated. The costs of the encoding alphabet is stipulated by the
	 * implementer.
	 * 
	 * @param k
	 * @return
	 */
	public abstract Node buildContainmentHierarchy(int encodingAlphabetSize,
			List<Node> allNodes);

	private Code buildGrammar() {
		return containmentHierarchy.getCode();
	}

	/**
	 * This method returns the probability distribution that was used to derive
	 * the TCF's containment hierarchy, if any. If a probability distribution
	 * was not used, return null.
	 * 
	 * @return as described above
	 */
	public UtilityClassesISF.ProbabilityDistribution getProbDist() {
		return normalizedProbDistribution;
	}

	/**
	 * This method returns the containment hierarchy.
	 * 
	 * @return the root node of the containment hierarchy
	 */
	public TraversableEncodingTreeI getEncodingTree() {
		return containmentHierarchy;
	}

	public double getMeanEncodingLength() {
		double encodingLength = 0;
		Code code = getCode();
		for (CodeWord rule : code.getCodeWords()) {
			double prob = rule.getProbability();
			// System.out.println(rule.getNumReactiveActions());
			encodingLength += (prob * rule.getNumReactiveActions());
		}
		return encodingLength;
	}

	/**
	 * This method finds the Selection Group that contains the passed parameter
	 * (at the correct level in the containment hierarchy) and returns an
	 * iterator of all of the JVirtualKeyboardButtons contained within it.
	 * 
	 */
	// public Iterator getKeysOfSelectionGroup(
	// JVirtualKeyboardButton containedButton) {
	// Node node = getContainmentHierarchy()
	// .getRelevantContainmentHierarchyNode(containedButton);
	// // Iterator it = node.extractMembers().iterator();
	// // return it;
	// return node.getNodeSelectionGroup().iterator();
	// }
	/**
	 * @return the SelectionGroup that is presently inFokus.
	 * 
	 */
	public SelectionGroup getCurrentSelectionGroup() {
		return getEncodingTree().getCurrentSelectionGroup();
	}

	/**
	 * This method takes the passed probability distribution and uses it to
	 * assign probabilities to the button of the particular keyboard layout that
	 * is being used for this indirect selection keyboard.
	 * 
	 * 
	 * 
	 * @param pd
	 *            The probability distribution. There must be a probability for
	 *            each of the buttons in the keyboard layout (if there
	 *            probabilities in the distribution for which no button exists
	 *            on the keyboard, we have a situation in which the sum of the
	 *            probs on the keyboard will not equal one. In this case, we
	 *            normalize.)
	 */
	public void assignProbabilitiesToCommands(ProbabilityDistribution pd) {
		double sum = 0.0;
		for (JIndirectSelectionButton but : getKeyboardLayout()
				.getAllEnabledButtons()) {
			// If action command is not in domain of probability distribution, a
			// null pointer exception is thrown. Need to improve this code to
			// protect agaisnt this possible run-time error.
			try {
				// double expectedFreq = getProbDist().get(but);
				// double expectedFreq = initialProbDistribution.get(but);
				double expectedFreq = pd.get(but);
				// System.out.println(expectedFreq);
				but.setMarginalProbability(expectedFreq);
				sum += expectedFreq;
			} catch (NullPointerException e) {
				// throw new RuntimeException("Button " + but + " not found in
				// probability distribution");
				System.out.println("Button " + but
						+ " not found in probability distribution");
				e.printStackTrace();
			}
		}
		// now we need to normalize the probability distribution wrt to the set
		// of buttons on this particular layout (the pd may contain buttons that
		// are not on this layout)
		normalizedProbDistribution = initialProbDistribution;
		// normalizedProbDistribution = new
		// UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton>();
		// for (JVirtualKeyboardButton but : getKeyboardLayout()
		// .getAllEnabledButtons()) {
		// normalizedProbDistribution.put(but, but.getExpectedFrequency()
		// / sum);
		// }
		// normalizedProbDistribution = initialProbDistribution;
	}

	public void assignProbabilitiesToCommands(ProbabilityDistribution pd,
			int dummy) {
		double sum = 0.0;
		for (SourceSymbol but : selectableKeyList.getSourceSymbolsByRankOrder()) {
			// If action command is not in domain of probability distribution, a
			// null pointer exception is thrown. Need to improve this code to
			// protect agaisnt this possible run-time error.
			try {
				// double expectedFreq = getProbDist().get(but);
				// double expectedFreq = initialProbDistribution.get(but);
				double expectedFreq = pd.get(but);
				// System.out.println(expectedFreq);
				but.setMarginalProbability(expectedFreq);
				sum += expectedFreq;
			} catch (NullPointerException e) {
				// throw new RuntimeException("Button " + but + " not found in
				// probability distribution");
				System.out.println("Button " + but
						+ " not found in probability distribution");
				e.printStackTrace();
			}
		}
		// now we need to normalize the probability distribution wrt to the set
		// of buttons on this particular layout (the pd may contain buttons that
		// are not on this layout)
		normalizedProbDistribution = initialProbDistribution;
		// normalizedProbDistribution = new
		// UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton>();
		// for (JVirtualKeyboardButton but : getKeyboardLayout()
		// .getAllEnabledButtons()) {
		// normalizedProbDistribution.put(but, but.getExpectedFrequency()
		// / sum);
		// }
		// normalizedProbDistribution = initialProbDistribution;
	}

}
