package abstractOnScreenIndirectSelectionKeyboard;

import UtilityClassesISF.ProbabilityDistribution;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

import encodingTrees.Code;
import encodingTrees.CodeWord;
import encodingTrees.EncodingUtilities;
import encodingTrees.obsolete.MyTraversableEncodingTree;
//import indirectSelectionFacility.TextEntrySystemFrame;
import treeDataStructure.LeafNode;
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
public abstract class AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts
		extends AbstractOnScreenIndirectSelectionKeyboard_ETthenKBL {

	/**
	 * This constructor is used only for Huffman encodings
	 * 
	 * @param pd
	 *            the probability distribution over the selectables
	 * 
	 * @param encodingAlphabetSize
	 *            the desired outdegree of the Huffman coding tree
	 * 
	 * @param keyboard
	 *            the keyboard layout (KBL) to be used
	 * 
	 * PRE the domain of the PD corresponds to the active buttons on the KBL
	 * 
	 */
	public AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts(
			SourceSymbolSet pd, int encodingAlphabetSize) {
		super(pd);
		// KeyboardLayout keyboard) {
		// this.keyboardLayout = keyboard;

		// initialProbDistribution = pd;
		// assignProbabilitiesToCommands(initialProbDistribution);
		List<Node> allNodes = new Vector<Node>();
		for (SourceSymbol but : super.getSelectableSet().getAllSourceSymbols()) {
			allNodes.add(new LeafNode(but));
			//System.out.println(but.getProbabilityOfOccurrence());
		}

		this.containmentHierarchy = new MyTraversableEncodingTree(
				buildContainmentHierarchy(encodingAlphabetSize, allNodes));
		// this.code = buildGrammar();
		super.setKeyboardLayout(setUpKeyboardLayout());
	}

	/**
	 * This method builds the containment hierarchy
	 * 
	 * @return the root node of the containment hierarchy
	 */
	// public abstract Node buildContainmentHierarchy();
	// public Node<JVirtualKeyboardButton> buildContainmentHierarchy() {
	// // don't need to implement anything - just use method provided in parent
	// // class
	// return this.buildContainmentHierarchy(getHuffmank());
	// }
	//
	// public abstract int getHuffmank();
	// /**
	// * This method builds an encoding tree using Huffman coding with equal
	// * letter costs. The parameter is the size of the encoding algorithm.
	// *
	// * @param k
	// * @return
	// */
	// public abstract Node<JVirtualKeyboardButton>
	// buildContainmentHierarchy(int k);
	/**
	 * This method builds a containment hierarchy using Huffman coding.
	 * 
	 * @param encodingAlphabetSize
	 * @return
	 */
	public Node buildContainmentHierarchy(int encodingAlphabetSize,
			List<Node> allNodes) {
		return huffmanEqualLetterCosts(encodingAlphabetSize, allNodes);
	}

	public Node huffmanEqualLetterCosts(int encodingAlphabetSize,
			List<Node> allNodes) {

		// List<Node> allNodes = new Vector<Node>();
		// for (SourceSymbol but : getKeyboardLayout().getAllEnabledButtons()) {
		// allNodes.add(new LeafNode(but));
		// }

		// for (SourceSymbol but :
		// super.selectableKeyList.getAllEnabledButtons()) {
		// allNodes.add(new LeafNode(but));
		// }

		// int initialNumberOfSourceSymbols = getKeyboardLayout()
		// .getAllEnabledButtons().size();

		int initialNumberOfSourceSymbols = allNodes.size();

		// add filler nodes
		EncodingUtilities.addFillerNodes(allNodes, encodingAlphabetSize,
				initialNumberOfSourceSymbols, getProbDist());
		return EncodingUtilities.encodeHuffmanTreeEqualCosts(allNodes, encodingAlphabetSize);
	}
}
