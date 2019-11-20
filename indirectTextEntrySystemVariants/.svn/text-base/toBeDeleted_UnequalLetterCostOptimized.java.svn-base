/*
 * Created on 11-Aug-2004
 */
package indirectTextEntrySystemVariants;

import java.util.List;
import java.util.Vector;

import buttonLayouts.ButtonLayoutSpecification;
import buttonLayouts.FifteenButtonKeyboardLayout;
import buttonLayouts.FiveButtonKeyboardLayout;

import customGUIComponentsISF.JIndirectSelectionButton;

import encodingTrees.EncodingUtilities;

import probabilityDistributionsVOCA.ProbDist_Fig2GolinRote98;
import probabilityDistributionsVOCA.ProbDist_Fig3GolinRote98;
import TreeDataStructure.InternalNode;
import TreeDataStructure.LeafNode;
import TreeDataStructure.Node;

/**
 * This class implements a text composition facility that has a containment
 * hierarchy that is derived using an Huffman encoding algorithm with the passed
 * values for k and the probability distribution.
 * 
 * @author Melanie Baljko
 */
public class toBeDeleted_UnequalLetterCostOptimized extends AbstractOnScreenIndirectSelectionKeyboard_KBLthenET {

	private final int k;

	private List<Integer> encodingAlphabetCosts;

	/**
	 * 
	 * @param pd
	 *            probability distribution
	 * @param k
	 */
	public toBeDeleted_UnequalLetterCostOptimized() {
		//this(new ProbDist_Fig2GolinRote98(), 3, new FiveButtonKeyboardLayout());
		this(new ProbDist_Fig3GolinRote98(), 3,
				new FifteenButtonKeyboardLayout());
	}

	/**
	 * 
	 * @param pd
	 *            probability distribution
	 * @param k
	 */
//	public UnequalLetterCostOptimized(
//			UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton> pd,
//			Integer k) {
//		this(pd, k, new FiveButtonKeyboardLayout());
//	}

	/**
	 * Three methods in parent abstract class will be invoked before this
	 * constructor:
	 */
	public toBeDeleted_UnequalLetterCostOptimized(
			UtilityClasses.ProbabilityDistribution<JIndirectSelectionButton> pd,
			Integer k, ButtonLayoutSpecification layout) {
		super(pd, k, layout);
		this.k = k;
	}

	@Override
	public ButtonLayoutSpecification setUpKeyboardLayout() {
		// return new FiveButtonKeyboardLayout(); // this goes with Fig2
		return new FifteenButtonKeyboardLayout(); // this goes with Fig3
	}

	/**
	 * 
	 */
	public Node buildContainmentHierarchy() {
		// don't need to implement anything - just use method provided in parent
		// class
		return this.buildContainmentHierarchy(k);
	}

	/**
	 * This method builds the CH bottom-up, which does not produce the optimized
	 * solution. Need to implement the algorithm by Golin & Rote (1998)
	 * 
	 */
	public Node buildContainmentHierarchy(int k) {
		// return useHuffman(k);
		// return constructFig2();
		return constructFig3();
	}

	private Node<JIndirectSelectionButton> useHuffman(int k) {
		encodingAlphabetCosts = new Vector<Integer>();
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(2);
		// for (int i = 1; i <= k; i++) {
		// encodingAlphabetCosts.add(i);
		// }

		List<JIndirectSelectionButton> sourceSymbols = new Vector<JIndirectSelectionButton>();
		for (JIndirectSelectionButton but : this.getProbDist().getDomain()) {
			sourceSymbols.add(but);
		}
		sourceSymbols.addAll(getSourceSymbolsToFill());
		System.out.println(sourceSymbols);

		List<Node<JIndirectSelectionButton>> forest = new Vector<Node<JIndirectSelectionButton>>();
		for (JIndirectSelectionButton but : sourceSymbols) {
			forest.add(new LeafNode<JIndirectSelectionButton>(but));
		}
		return EncodingUtilities.encodeHuffmanTreeUnequalCosts(forest, k, encodingAlphabetCosts);
	}

	private Node<JIndirectSelectionButton> constructFig2() {
		encodingAlphabetCosts = new Vector<Integer>();
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(2);
		// for (int i = 1; i <= k; i++) {
		// encodingAlphabetCosts.add(i);
		// }

		List<JIndirectSelectionButton> sourceSymbols = new Vector<JIndirectSelectionButton>();

		for (JIndirectSelectionButton but : this.getProbDist().getDomain()) {
			sourceSymbols.add(but);
		}

		InternalNode<JIndirectSelectionButton> internal1 = new InternalNode<JIndirectSelectionButton>();
		internal1.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(0)), encodingAlphabetCosts.get(0));
		internal1.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(1)), encodingAlphabetCosts.get(1));
		internal1.addChild(new LeafNode<JIndirectSelectionButton>(
				getFillerSourceSymbol(1)), encodingAlphabetCosts.get(2));

		InternalNode<JIndirectSelectionButton> internal2 = new InternalNode<JIndirectSelectionButton>();
		internal2.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(2)), encodingAlphabetCosts.get(0));
		internal2.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(3)), encodingAlphabetCosts.get(1));
		internal2.addChild(new LeafNode<JIndirectSelectionButton>(
				getFillerSourceSymbol(2)), encodingAlphabetCosts.get(2));

		InternalNode<JIndirectSelectionButton> root = new InternalNode<JIndirectSelectionButton>();
		root.addChild(internal1, encodingAlphabetCosts.get(0));
		root.addChild(internal2, encodingAlphabetCosts.get(1));
		root.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols.get(4)),
				encodingAlphabetCosts.get(2));

		return root;
	}

	private Node<JIndirectSelectionButton> constructFig3() {
		encodingAlphabetCosts = new Vector<Integer>();
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(3);
		// for (int i = 1; i <= k; i++) {
		// encodingAlphabetCosts.add(i);
		// }

		List<JIndirectSelectionButton> sourceSymbols = new Vector<JIndirectSelectionButton>();

		for (JIndirectSelectionButton but : this.getProbDist().getDomain()) {
			sourceSymbols.add(but);
		}

		InternalNode<JIndirectSelectionButton> internal01 = new InternalNode<JIndirectSelectionButton>();
		internal01.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(0)), encodingAlphabetCosts.get(0));
		internal01.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(1)), encodingAlphabetCosts.get(1));
		internal01.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(2)), encodingAlphabetCosts.get(2));

		InternalNode<JIndirectSelectionButton> internal10 = new InternalNode<JIndirectSelectionButton>();
		internal10.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(3)), encodingAlphabetCosts.get(0));
		internal10.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(4)), encodingAlphabetCosts.get(1));
		internal10.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(5)), encodingAlphabetCosts.get(2));

		InternalNode<JIndirectSelectionButton> internal11 = new InternalNode<JIndirectSelectionButton>();
		internal11.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(6)), encodingAlphabetCosts.get(0));
		internal11.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(7)), encodingAlphabetCosts.get(1));
		internal11.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(8)), encodingAlphabetCosts.get(2));

		InternalNode<JIndirectSelectionButton> internal2 = new InternalNode<JIndirectSelectionButton>();
		internal2.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(9)), encodingAlphabetCosts.get(0));
		internal2.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(10)), encodingAlphabetCosts.get(1));
		internal2.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(11)), encodingAlphabetCosts.get(2));

		InternalNode<JIndirectSelectionButton> internal0 = new InternalNode<JIndirectSelectionButton>();
		internal0.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(12)), encodingAlphabetCosts.get(0));
		internal0.addChild(internal01, encodingAlphabetCosts.get(1));
		internal0.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(13)), encodingAlphabetCosts.get(2));

		InternalNode<JIndirectSelectionButton> internal1 = new InternalNode<JIndirectSelectionButton>();
		internal1.addChild(internal10, encodingAlphabetCosts.get(0));
		internal1.addChild(internal11, encodingAlphabetCosts.get(1));
		internal1.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(14)), encodingAlphabetCosts.get(2));

		InternalNode<JIndirectSelectionButton> root = new InternalNode<JIndirectSelectionButton>();
		root.addChild(internal0, encodingAlphabetCosts.get(0));
		root.addChild(internal1, encodingAlphabetCosts.get(1));
		root.addChild(internal2, encodingAlphabetCosts.get(2));

		return root;
	}

	/**
	 * This method returns a collection of source symbols needed so that the
	 * prefix-free coding tree will be full
	 * 
	 * @return
	 */
	public List<JIndirectSelectionButton> getSourceSymbolsToFill() {
		List<JIndirectSelectionButton> fillers = new Vector<JIndirectSelectionButton>();
		int numFiller = getNumFiller(encodingAlphabetCosts.size(), this
				.getProbDist().getDomain().size());
		for (int i = 0; i < numFiller; i++) {
			fillers.add(getFillerSourceSymbol(i));
		}
		return fillers;
	}

	private JIndirectSelectionButton getFillerSourceSymbol(int identifier) {
		final double FILLER_FREQ = 0.0;
		JIndirectSelectionButton fillerButton = new JIndirectSelectionButton(
				"FillerLabel" + identifier, null, "$\\emptyset$", FILLER_FREQ);
		getProbDist().put(fillerButton, FILLER_FREQ);
		return fillerButton;
	}

	public int getHuffmank() {
		return k;
	}

}
