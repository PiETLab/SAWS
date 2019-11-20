/*
 * Created on 12-Aug-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;

import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.Node;
import unequalLetterCostCode.ConstructCodeTree;
import unequalLetterCostCode.EncodingAlphabet;

// import VOCA.JVirtualKeyboardDummyButton;

/**
 * @author Melanie Baljko
 */
public class EncodingUtilities {

	/*
	 * This method returns the root of a Huffman encoding tree. It takes as
	 * parameters (1) a forest of ContainmentHierarchyNodes in the form of a
	 * vector and (2) a value for k, the out-degree.
	 */

	private static final boolean IS_VERBOSE = false;
	/**
	 * The following anonymous class is used for sake of brevity. It puts the
	 * nodes in order descending according to // expectedFrequency
	 * 
	 * This comparator returns a negative integer, zero, or a positive integer
	 * as the first argument is less than, equal to, or greater than the second.
	 * 
	 * node1 is less than node2 if the expected frequency of node1 is larger
	 * than the expected frequency of node2
	 * 
	 * The comparator sorts the nodes in descending order
	 */
	private static Comparator<Node> c = new Comparator<Node>() {
		public int compare(Node node1, Node node2) {
			double diff = node1.getProbabilityofOccurrence()
					- node2.getProbabilityofOccurrence();
			if (diff < 0) {
				return 1;
			} else if (diff > 0) {
				return -1;
			} else {
				// the two nodes have the same expected frequency
				// in this case, choose an order based on the lexicographic
				// order of the two node's first child (or only child, if the
				// node is a leaf node, which has a trivial selection group)
				return node1.getSelectionGroup().extractMembers().get(0)
						.getTextLabel().compareTo(
								node2.getSelectionGroup().extractMembers().get(
										0).getTextLabel());
			}
		}
	};

	/**
	 * This method returns the root of a tree that represents a prefix-free code
	 * over an alphabet of size k.
	 * 
	 * forest is a list of nodes, in no particular order.
	 * 
	 * The algorithm is the well-known greedy algorithm due to Huffman (1952).
	 * It runs in O(n log n) time [it would be O(n) if the nodes were sorted in
	 * advance]
	 * 
	 */
	public static Node encodeHuffmanTreeEqualCosts(List<Node> forest, int k,
			boolean doNotIncludeFillerNodes) {
		// first, we need to sort the vector by decreasing frequency
		// Now we need to repeat the following until the vector v has no
		// nodes left (i.e., until the forest is fully connected)
		List<Node> v = encodeHuffmanTreeEC(forest, k, doNotIncludeFillerNodes);
		v.get(0).propogateLabels("0");
		return v.get(0);
	}

	public static Node encodeHuffmanTreeUnequalCosts(
			List<Double> probabilitiesList,
			List<SourceSymbol> sourceSymbolList,
			EncodingAlphabet encodingAlphabet) {

		if (IS_VERBOSE)
			System.out.println("In method encodeHuffmanTreeUnequalCosts");
		ConstructCodeTree<JIndirectSelectionButton> code = new ConstructCodeTree<JIndirectSelectionButton>(
				probabilitiesList, encodingAlphabet.getAlphabetCostList(),
				sourceSymbolList.size(), encodingAlphabet.size());

		InternalNode codeTreeWithWords = code
				.constructCodeforUnequalLetterCosts(encodingAlphabet,
						sourceSymbolList);
		return codeTreeWithWords;
	}

	/**
	 * Helper method for encodeTree
	 */
	private static List<Node> encodeHuffmanTreeEC(List<Node> v, int k,
			boolean doNotIncludeFillerNodes) {
		// k-ary Huffman Encoding Algorithm
		// 1. Begin with forest of one-node trees.
		// 2. Repeat until the forest is full connected
		// 2a. Choose a set of trees T with the k smallest weights.
		// 2b. Create a new tree whose children sub-trees are T, arranged from
		// left
		// to right with decreasing weights.

		if (v.size() == 1) {
			return v;
		} else if (v.size() <= k) {
			Collections.sort(v, c);

			InternalNode node = new InternalNode();
			// System.out.println(v);
			// System.out.println(numIters + " " + v.size());

			// remove all of the nodes
			for (int i = v.size(); i > 0; i--) {
				// System.out.println(v.get(0));
				if (v.get(0).isFillerNode()) {
					v.remove(0);
				} else {
					node.addChild(v.remove(0));
				}
				// node.addChild((Node)
				// v.remove(v.size()-1));
			}
			// node.addChild(new LeafContainmentHierarchyNode(
			// new JVirtualKeyboardDummyButton("dummy")));

			v.add(node);

			return encodeHuffmanTreeEC(v, k, doNotIncludeFillerNodes);
		} else {
			Collections.sort(v, c);

			InternalNode node = new InternalNode();
			int offset = v.size() - k;
			for (int i = 0; i < k; i++) {
				if (IS_VERBOSE) {
					// System.out.println(i + " " + v.size());
					System.out.println("examining node: " + v.get(offset));
				}
				if (v.get(offset).isFillerNode()) {
					v.remove(offset);
				} else {
					// node.addChild(v.remove(0));
					node.addChild(v.remove(offset));
				}

				// node.addChild((Node)
				// v.remove(v.size()-1));
			}
			// node.addChild(new LeafContainmentHierarchyNode(
			// new JVirtualKeyboardDummyButton("dummy")));
			v.add(node);
			return encodeHuffmanTreeEC(v, k, doNotIncludeFillerNodes);
		}

	}

	// private static List<Node> encodeHuffmanTreeUC(List<Node> v, int k,
	// List<Integer> encodingAlphabetCosts) {
	// // k-ary Huffman Encoding Algorithm
	// // 1. Begin with forest of one-node trees.
	// // 2. Repeat until the forest is full connected
	// // 2a. Choose a set of trees T with the k smallest weights.
	// // 2b. Create a new tree whose children sub-trees are T, arranged from
	// // left
	// // to right with descreasing weights.
	//
	// if (v.size() == 1) {
	// return v;
	// } else if (v.size() <= k) {
	// Collections.sort(v, c);
	//
	// InternalNode node = new InternalNode();
	// // System.out.println(v);
	// // System.out.println(numIters + " " + v.size());
	//
	// int childNum = 0;
	// // remove all of the nodes
	// for (int i = v.size(); i > 0; i--) {
	// // System.out.println(v.get(0));
	// node.addChild(v.remove(0), encodingAlphabetCosts.get(childNum));
	// // node.addChild((Node)
	// // v.remove(v.size()-1));
	// childNum++;
	// }
	// // node.addChild(new LeafContainmentHierarchyNode(
	// // new JVirtualKeyboardDummyButton("dummy")));
	// v.add(node);
	// return encodeHuffmanTreeUC(v, k, encodingAlphabetCosts);
	// } else {
	// Collections.sort(v, c);
	//
	// InternalNode node = new InternalNode();
	// int offset = v.size() - k;
	// for (int i = 0; i < k; i++) {
	// // System.out.println(numIters +" "+v.size());
	// // System.out.println(v.get(offset));
	// node.addChild(v.remove(offset), encodingAlphabetCosts.get(i));
	// // node.addChild((Node)
	// // v.remove(v.size()-1));
	// }
	// // node.addChild(new LeafContainmentHierarchyNode(
	// // new JVirtualKeyboardDummyButton("dummy")));
	// v.add(node);
	// return encodeHuffmanTreeUC(v, k, encodingAlphabetCosts);
	// }
	//
	// }

	/**
	 * This method returns the root of a tree that represents a binary tree of
	 * the passed list of nodes
	 */
	public static Node encodeKaryTree(List<Node> forest, int k) {
		List<Node> v = encodeKaryTreeR(forest, k);
		v.get(0).propogateLabels("0");
		return v.get(0);
	}

	/**
	 * Helper method for encodeTree
	 */
	private static List<Node> encodeKaryTreeR(List<Node> v, int k) {
		// Straightforward building of k-ary tree
		// 1. Begin with forest of one-node trees.
		// 2. Repeat until the forest is full connected
		// 2a. Divide forest into k sets (the same size, or at least close
		// enough given the total number avail)
		// 2b. If set is size 1, construct leaf node
		// 2c. If set is > 1, repeat

		if (v.size() == 1) {
			return v;
		} else if (v.size() <= k) {
			InternalNode node = new InternalNode();
			// System.out.println(v);
			// System.out.println(numIters + " " + v.size());

			// remove all of the nodes
			for (int i = v.size(); i > 0; i--) {
				// System.out.println(v.get(0));
				node.addChild(v.remove(0));
				// node.addChild((Node)
				// v.remove(v.size()-1));
			}
			v.add(node);
			return encodeKaryTreeR(v, k);
		} else {
			InternalNode rootNode = new InternalNode();
			double subtreeSize = Math.ceil(v.size() / (double) k);
			System.out.println("splitting nodes, group size: " + subtreeSize);
			while (v.size() > 0) {
				List<Node> subTree = new Vector<Node>();
				for (int i = 0; i < subtreeSize; i++) {
					if (v.size() > 0) {
						subTree.add(v.remove(0));
					}
				}
				rootNode.addChild(encodeKaryTreeR(subTree, k).get(0));
			}
			List<Node> root = new Vector<Node>();
			root.add(rootNode);
			return root;
		}

	}

	/**
	 * This method determines the number of "filler" source symbols (buttons)
	 * needed so that the eventual Huffman encoding tree is full
	 * 
	 * @param sizeOfEncodingAlphabet
	 * @return
	 */
	public static int getNumFiller(int sizeOfEncodingAlphabet,
			int initialNumberOfSourceSymbols) {
		int numFiller = (sizeOfEncodingAlphabet - 1)
				- ((initialNumberOfSourceSymbols % (sizeOfEncodingAlphabet - 1)) - 1);
		if ((initialNumberOfSourceSymbols % (sizeOfEncodingAlphabet - 1)) == 1)
			numFiller = 0;
		if (sizeOfEncodingAlphabet == 2)
			numFiller = 0;
		return numFiller;
	}

	/**
	 * 
	 * 
	 * @param allNodes
	 *            the list of nodes, side effect that filler nodes are added to
	 *            it
	 * @param encodingAlphabetSize
	 * @param initialNumberOfSourceSymbols
	 * @param normalizedProbDistribution
	 *            side effect: if nodes are added, then an entry is made to the
	 *            prob distribution
	 */
	public static void addFillerNodes(List<Node> allNodes,
			int encodingAlphabetSize, int initialNumberOfSourceSymbols) {
		final double FILLER_FREQ = 0.0;
		int numFiller = getNumFiller(encodingAlphabetSize,
				initialNumberOfSourceSymbols);
		for (int i = 0; i < numFiller; i++) {
			// JIndirectSelectionButton fillerButton = new
			// JIndirectSelectionButton(
			// "FillerLabel" + i, null, "$\\emptyset$", FILLER_FREQ);
			Node newNode = Node.generateFillerNode();
			newNode.setProbabilityofOccurrence(FILLER_FREQ);
			// normalizedProbDistribution.put(fillerButton, FILLER_FREQ);
			allNodes.add(newNode);
		}
	}

	public static Node huffmanEqualLetterCosts(int encodingAlphabetSize,
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
		boolean shouldInsertFillerNodes = true;
		if (shouldInsertFillerNodes) {
			EncodingUtilities.addFillerNodes(allNodes, encodingAlphabetSize,
					initialNumberOfSourceSymbols);
		}
		Node rootOfHuffmanTree = EncodingUtilities.encodeHuffmanTreeEqualCosts(
				allNodes, encodingAlphabetSize, shouldInsertFillerNodes);
		// if (removeFillerNodes) {
		//			
		// }

		return rootOfHuffmanTree;
	}

	// public static Node huffmanUnequalLetterCosts(int encodingAlphabetSize,
	// List<Node> allNodes, EncodingAlphabet encodingAlphabet) {
	//
	//
	// return EncodingUtilities.encodeHuffmanTreeUnequalCosts(
	// enabledProbabilityList, enabledWords, encodingAlphabet);
	// }

}
