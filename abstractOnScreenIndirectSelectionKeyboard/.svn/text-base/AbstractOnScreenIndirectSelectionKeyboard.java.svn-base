package abstractOnScreenIndirectSelectionKeyboard;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

import UtilityClassesISF.ProbabilityDistribution;
import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

import encodingTrees.Code;
import encodingTrees.CodeWord;
import encodingTrees.obsolete.MyTraversableEncodingTree;
import encodingTrees.obsolete.TraversableEncodingTreeI; //import IndirectSelectionFacility.TextEntrySystemFrame;
//import IndirectSelectionFacility.TextEntrySystemFrameController;
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
public abstract class AbstractOnScreenIndirectSelectionKeyboard {

	private ButtonLayoutSpecification keyboardLayout = null;

	protected TraversableEncodingTreeI encodingTree = null;

	// private final Code code = null;

	// added by Fatima
	private SourceSymbolSet selectableSet;

	/**
	 * This is the constructor used to build an indirect selection keyboard
	 * based on a CH derived from Huffman algorithm with Unequal letter cost.
	 * 
	 * @author Fatima
	 */
	public AbstractOnScreenIndirectSelectionKeyboard(SourceSymbolSet pd) {
		this.selectableSet = pd;
		// UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton> pd,
		// int encodingAlphabetSize) {
	}

	public AbstractOnScreenIndirectSelectionKeyboard() {
		this.selectableSet = null; // this.getKeyboardLayout().getSelectables();
	}

	public void setKeyboardLayout(ButtonLayoutSpecification kbl) {
		this.keyboardLayout = kbl;
	}

	public ButtonLayoutSpecification getKeyboardLayout() {
		return keyboardLayout;
	}

	/**
	 * This method builds an encoding tree using Huffman coding with equal
	 * letter costs. The parameter is the size of the encoding algorithm.
	 * 
	 * @param k
	 * @return
	 */
	// public abstract Node<JVirtualKeyboardButton>
	// buildContainmentHierarchy(int k);
	/**
	 * This method returns the probability distribution that was used to derive
	 * the TCF's containment hierarchy, if any. If a probability distribution
	 * was not used, return null.
	 * 
	 * @return as described above
	 */
	public abstract ProbabilityDistribution getProbDist();

	public Code getCode() {
		return getEncodingTree().getCode();
	}

	/*
	 * public ActionGrammar getGrammar() { return grammar; }
	 */

	/**
	 * This method returns all enablied buttons on this indirect selection
	 * keyboard
	 * 
	 * @return
	 */
	public List<JIndirectSelectionButton> getAllButtons() {
		return getKeyboardLayout().getAllEnabledButtons();
	}

	/**
	 * This method returns the containment hierarchy.
	 * 
	 * @return the root node of the containment hierarchy
	 */
	public TraversableEncodingTreeI getEncodingTree() {
		return encodingTree;
	}

	/**
	 * 
	 * This method returns the (first*) JVirtualKeyboardButton that is
	 * associated with the passed label.
	 * 
	 * *in case of non-unique JVirtualKeyboardButtons
	 * 
	 * The constructor for JVirtualKeyboardButton ensures that every button has
	 * a unique label
	 * 
	 * @param stringLabel
	 * @return
	 */
	public JIndirectSelectionButton getButtonByLabel(String stringLabel) {
		JIndirectSelectionButton foundBut = null;
		List<JIndirectSelectionButton> l = getAllButtons();
		for (JIndirectSelectionButton but : l) {
			System.out.println(but + " " + stringLabel);
			if (stringLabel.equals(but.getText()))
				foundBut = but;
		}
		return foundBut;
	}

	public String getName() {
		// return this.getClass().getName() + "(" +
		// getProbDist().getIdentifier() + ")";
		return this.getClass().getName();
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
	 * This method finds the Selection Group that contains the passed parameter
	 * (at the correct level in the containment hierarchy) and returns it.
	 */
	public SelectionGroup getSelectionGroupByButton(SourceSymbol containedButton) {
		Node node = getEncodingTree().getRelevantEncodingTreeNode(
				containedButton);
		return node.getSelectionGroup();
	}

	/**
	 * @return the SelectionGroup that is presently inFokus.
	 * 
	 */
	public SelectionGroup getCurrentSelectionGroup() {
		return getEncodingTree().getCurrentSelectionGroup();
	}

	// ///////////////////////////////////////////////////////////
	// FOKUS Methods
	// ///////////////////////////////////////////////////////////

	/**
	 * This method resets the inFokus node of the containment hierarchy to be
	 * the root.
	 */
	public void resetFokus() {
		getEncodingTree().reset();
	}

	/**
	 * This method causes fokus to move to the first child of the node that
	 * presently has fokus.
	 */
	public void descendFokus() {
		getEncodingTree().descendFokus();
	}

	public List<SelectionGroup> getFokusCycle() {
		return getEncodingTree().getFokusCycle();
	}

	/**
	 * This method causes fokus to move to the first child of the node that
	 * presently has fokus.
	 */
	public boolean ascendFokus() {
		return getEncodingTree().ascendFokus();
	}

	/**
	 * This method causes fokus to move to the first child of the node that
	 * presently has fokus.
	 */
	// public boolean ascendFokus(TextEntrySystemFrameController tcf,
	// ActionEvent ae) {
	// return getEncodingTree().ascendFokus(tcf, ae);
	// }
	/**
	 * This method causes fokus to move to the next sibling of the node that
	 * presently has fokus.
	 */
	public void advanceFokus() {
		getEncodingTree().advanceFokus();
	}

	// ////////// /////////////////////////////////////////////////////////////
	// toString METHODS
	// ////////// /////////////////////////////////////////////////////////////

	public String toString() {
		return getEncodingTree().toStringLaTeXLispStyle();
	}

	public String toStringLispStyle() {
		StringBuffer buf = new StringBuffer();
		buf.append(getEncodingTree().toStringLaTeXLispStyle());
		// the next line is nec. because recursive function adds spurious line
		// break to start of output
		buf.delete(0, 2);
		buf.insert(0, "\\noindent\\begin{tt}\\begin{small}");
		// buf.append(ch.toStringNodeView());
		buf.append("\\end{small}\\end{tt}");
		return buf.toString();
	}

	public String toStringLaTeX() {
		StringBuffer buf = new StringBuffer();
		buf.append("%%%% INFO FOR KEYBOARD VARIANT: " + getName() + "\n");
		final boolean SHOW_NODE_IDS = false;
		final boolean SHOW_NODE_DEPTHS = false;
		final boolean SHOW_NODE_DEPTHS2 = false;
		buf.append(getEncodingTree().toStringLaTeXecltree(SHOW_NODE_IDS,
				SHOW_NODE_DEPTHS, SHOW_NODE_DEPTHS2));
		if (getProbDist() != null) {
			buf.append("%% PROB DIST %%\n");
			buf.append(getProbDist().toStringLaTeX());
		} else {
			buf.append("%% NO ASSOCIATED PROB DIST %%\n");
		}
		return buf.toString();
	}

	public SourceSymbolSet getSelectableSet() {
		return selectableSet;
	}

	public void setSelectableSet(SourceSymbolSet selectableSet) {
		this.selectableSet = selectableSet;
	}

}
