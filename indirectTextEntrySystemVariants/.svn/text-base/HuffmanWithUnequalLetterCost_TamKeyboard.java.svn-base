package indirectTextEntrySystemVariants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import buttonLayouts.ButtonLayoutSpecification;
import buttonLayouts.FifteenButtonKeyboardLayout;

import customGUIComponentsISF.JIndirectSelectionButton;

import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanWithUnequalLetterCost;

import keyboardLayouts.TamKeyBoardLayoutUnequal;

import probabilityDistributionsVOCA.ProbDist_Fig3GolinRote98;
import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import sourceSymbolSet.SourceSymbol;
import unequalLetterCostCode.ConstructCodeTree;
import unequalLetterCostCode.EncodingAlphabet;
import unequalLetterCostCode.Signature;
import TreeDataStructure.InternalNode;
import TreeDataStructure.LeafNode;
import TreeDataStructure.Node;
import UtilityClasses.ProbabilityDistribution;

/**
 * This class implements a text composition facility that uses a Containment
 * Hierarchy derived from the new algorithm of Huffman with unequal letter
 * costs. It is assumed that each selecatable displayed on the keyboard has a
 * different cost to reach, rather than the same cost. The difference in cost
 * arises due to different wait time, that is needed to reach different siblings
 * in a containmen Hierarchy. The Huffman encoding algorithm can be
 * parameterized for the value of k and the no. of selectables. For the time
 * being, we have used the Venkategari probability distribution with 43
 * selectables, and k = 2. derivation).
 * 
 * The keyboard layout for the text composition facility is generated manualy,
 * using the time-consuming and painful technique of Trial and Error.
 * 
 * @author Fatima
 * 
 */

public class HuffmanWithUnequalLetterCost_TamKeyboard extends
		AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanWithUnequalLetterCost {

	private final static int SIZE_OF_ENCODING_ALPHABET = 2;

	private final static ProbabilityDistribution<SourceSymbol> PROBABILITY_DISTRIBUTION = new ProbDist_Venkatagiri99_Hypothesized();

	public HuffmanWithUnequalLetterCost_TamKeyboard() {
		super(PROBABILITY_DISTRIBUTION, SIZE_OF_ENCODING_ALPHABET);
	}

	@Override
	public ButtonLayoutSpecification setUpKeyboardLayout() {
		return new TamKeyBoardLayoutUnequal(containmentHierarchy,
				selectableKeyList);
	}

	@Override
	public Node buildContainmentHierarchy(int encodingAlphabetSize,
			List<Node> allNodes) {
		// TODO Auto-generated method stub
		return null;
	}

	// private final int k;
	// private final int n;
	// // no. of words
	//
	// private List<Integer> encodingAlphabetCosts;
	//
	// /**
	// *
	// * @param pd
	// * probability distribution
	// * @param k
	// */
	// public HuffmanWithUnequalLetterCostTam() {
	// // this(new ProbDist_Fig2GolinRote98(), 3, new
	// // FiveButtonKeyboardLayout());
	// this(new ProbDist_Venkatagiri99_Hypothesized(), 2, 43);
	// }
	//
	// public HuffmanWithUnequalLetterCostTam(
	// UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton> pd) {
	// // this(new ProbDist_Fig2GolinRote98(), 3, new
	// // FiveButtonKeyboardLayout());
	// this(pd, 2, 43);
	// }
	//
	// /**
	// *
	// * @param pd
	// * probability distribution
	// * @param k
	// */
	// // public UnequalLetterCostOptimized(
	// // UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton> pd,
	// // Integer k) {
	// // this(pd, k, new FiveButtonKeyboardLayout());
	// // }
	// /**
	// * Three methods in parent abstract class will be invoked before this
	// * constructor:
	// */
	// public HuffmanWithUnequalLetterCostTam(
	// UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton> pd,
	// Integer k, Integer n) {
	// super(pd, k, n);
	// this.k = k;
	// this.n = n;
	// }
	//
	// /**
	// * This method builds the CH bottom-up, which does not produce the
	// optimized
	// * solution. Need to implement the algorithm by Golin & Rote (1998)
	// *
	// */
	//
	//
	// /**
	// * This method builds the containment hierarchy based on the Huffman
	// * algorithm for unequal letter costs. The code trees in the unequal
	// Huffman
	// * algorithm are constructed from the top down (in cotrast to the Standard
	// * Huffman algorithm where the trees are constructed bottom-up). The code
	// * trees are expanded them level by level using a dynamic programming
	// * approach, and the minimal cost tree is chosen as the optimal tree, and
	// it
	// * represents the containment Hierarchy. The containment hierarchy is
	// build
	// * first, and the keyboard layout is created afterwards.
	// *
	// *
	// * @param k
	// * the outdegree of the containment Hierarchy
	// * @return the root node of the containment hierarchy
	// * @author Fatima Ramay
	// */
	// public Node<JVirtualKeyboardButton> buildContainmentHierarchy(int k) {
	//
	// List<Double> enabledProbabilityList = selectableKeyList
	// .getEnabledFrequncyList();
	// List<JVirtualKeyboardButton> enabledWords = selectableKeyList
	// .getAllEnabledButtons();
	//
	// List<Integer> vector = new Vector<Integer>();
	// vector.add(0);
	// vector.add(1);
	// vector.add(1);
	// // vector.add(1);
	// /*
	// * vector.add(1); vector.add(1); vector.add(1); vector.add(1);
	// */
	// // Characterisitc vector representing c1 = 1, c2 = 2;
	// Signature vectorD = new Signature(vector);
	//
	// int wordsNum = enabledWords.size();
	// EncodingAlphabet encodingAlphabet = new EncodingAlphabet(k);
	//
	// int rChildren = encodingAlphabet.size();
	//
	// ConstructCodeTree<JVirtualKeyboardButton> code = new
	// ConstructCodeTree<JVirtualKeyboardButton>(
	// enabledProbabilityList, encodingAlphabet.getAlphabetCostList(),
	// wordsNum, rChildren);
	//
	// InternalNode<JVirtualKeyboardButton> codeTreeWithWords = code
	// .constructCodeforUnequalLetterCosts(encodingAlphabet
	// .getAlphabetList(), encodingAlphabet
	// .getAlphabetCostList(), enabledWords);
	//
	// // System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
	// // System.out.println(codeTreeWithWords.toStringPlainTextLispStyle(0));
	//
	// return codeTreeWithWords;
	//
	// /*
	// * System.out.println("------------Printing Code ---------------");
	// *
	// * code.toString();
	// *
	// * InternalNode<JVirtualKeyboardButton> codeTree =
	// * code.buildMinCostTree(encodingAlphabet.getAlphabetList(),
	// * encodingAlphabet.getAlphabetCostList()); System.out.println("Printing
	// * Code Tree ");
	// *
	// * System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
	// * System.out.println(codeTree.toStringPlainTextLispStyle(0));
	// *
	// * InternalNode<JVirtualKeyboardButton> codeTreeWithWords =
	// * code.assignActualProbabilities(codeTree, enabledWords);
	// *
	// *
	// * System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
	// * System.out.println(codeTreeWithWords.toStringPlainTextLispStyle(0));
	// *
	// *
	// * //System.out.println("Min : " + code.searchMinCostTree());
	// */
	// }
	//
	// /**
	// * This method returns a collection of source symbols needed so that the
	// * prefix-free coding tree will be full. But this method is not used
	// * currently.
	// *
	// * @return
	// */
	// public List<JVirtualKeyboardButton> getSourceSymbolsToFill() {
	// List<JVirtualKeyboardButton> fillers = new
	// Vector<JVirtualKeyboardButton>();
	// int numFiller = getNumFiller(encodingAlphabetCosts.size(), this
	// .getProbDist().getDomain().size());
	// for (int i = 0; i < numFiller; i++) {
	// fillers.add(getFillerSourceSymbol(i));
	// }
	// return fillers;
	// }
	//
	// private JVirtualKeyboardButton getFillerSourceSymbol(int identifier) {
	// final double FILLER_FREQ = 0.0;
	// JVirtualKeyboardButton fillerButton = new JVirtualKeyboardButton(
	// "FillerLabel" + identifier, null, "$\\emptyset$", FILLER_FREQ);
	// getProbDist().put(fillerButton, FILLER_FREQ);
	// return fillerButton;
	// }
	//
	// public int getHuffmank() {
	// return k;
	// }
	//
	// private static String getHeader() {
	// StringBuffer buf = new StringBuffer();
	// buf.append("\\documentclass[12pt]{article}" + "\n"
	// + "\\usepackage{palatino}" + "\\usepackage{fullpage}" + "\n"
// + "\\usepackage{url}" + "\n" + "\\usepackage{amsmath}" + "\n"
// + "\\usepackage{epic}" + "\n" + "\\usepackage{ecltree}" + "\n"
// + "\\renewcommand{\\ttdefault}{cmtt}" + "\n"
	// + "\\begin{document}" + "\n");
	// return buf.toString();
	// }
	//
	// private static String getFooter() {
	// StringBuffer buf = new StringBuffer();
	// buf.append("\\end{document}");
	// return buf.toString();
	// }
	//
	// private static void generateLaTeXFile(IndirectSelectionKeyboard keyboard)
	// {
	// try {
	// File file = new File("tex" + File.separator + "ch-"
	// + keyboard.getName() + ".tex");
	// FileWriter outputFile = new FileWriter(file);
	// StringBuffer buf = new StringBuffer();
	//
	// buf.append(getHeader());
	// buf.append("\\noindent \\url{" + keyboard.getName() + "}\n\n");
// buf.append("\\noindent \\url{"
// + keyboard.getProbDist().getIdentifier() + "}\n\n");
	// buf.append(keyboard.toStringLaTeX());
	// buf.append("\\newpage" + "\n");
	// buf.append(keyboard.getContainmentHierarchy().getCode()
	// .toStringLaTeX());
	// buf.append(getFooter());
	// outputFile.write(buf.toString());
	// outputFile.flush();
	// System.out.println("Done.");
	// } catch (IOException e) {
	// System.out.println("Problems creating file");
	// e.printStackTrace();
	// System.exit(0);
	// }
	// }
	//
	// /*
	// * public KeyboardLayout setUpKeyboardLayout() {
	// *
	// *
	// * return new RamayKeyboardLayout(containmentHierarchy,
	// selectableKeyList); }
	// *
	// *
	// * /** This method builds the containment hierarchy based on the list of
	// * selectables.
	// *
	// * @return the root node of the containment hierarchy @author Fatima Ramay
	// */
	//
	// /*
	// * public ContainmentHierarchyNode buildContainmentHierarchy() {
	// *
	// * List<Double> enabledProbabilityList =
	// * selectableKeyList.getEnabledFrequncyList();
	// List<JVirtualKeyboardButton>
	// * enabledWords = selectableKeyList.getAllEnabledButtons() ;
	// *
	// * List<Integer> vector = new Vector<Integer>(); vector.add(0);
	// * vector.add(1); vector.add(1); //Characterisitc vector representing c1 =
	// * 1, c2 = 2;
	// *
	// * Signature vectorD = new Signature(vector);
	// *
	// * int wordsNum = enabledWords.size(); EncodingAlphabet encodingAlphabet =
	// * new EncodingAlphabet(k);
	// *
	// * int rChildren = encodingAlphabet.size();
	// *
	// * ConstructCode<JVirtualKeyboardButton> code = new
	// ConstructCode<JVirtualKeyboardButton>(enabledProbabilityList,
	// * vectorD, wordsNum, rChildren);
	// *
	// * InternalNode<JVirtualKeyboardButton> codeTreeWithWords =
	// *
	// code.constructCodeforUnequalLetterCosts(encodingAlphabet.getAlphabetList(),
	// * encodingAlphabet.getAlphabetCostList(), enabledWords);
	// *
	// * //containemnt hierarchy ContainmentHierarchyNode chNode =
	// * convertToCHN(codeTreeWithWords);
	// *
	// * System.out.println(chNode.toString());
	// *
	// * return chNode;
	// *
	// * /*System.out.println("------------Printing Code ---------------");
	// *
	// * code.toString();
	// *
	// * InternalNode<JVirtualKeyboardButton> codeTree =
	// * code.buildMinCostTree(encodingAlphabet.getAlphabetList(),
	// * encodingAlphabet.getAlphabetCostList()); System.out.println("Printing
	// * Code Tree ");
	// *
	// * System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
	// * System.out.println(codeTree.toStringPlainTextLispStyle(0));
	// *
	// * InternalNode<JVirtualKeyboardButton> codeTreeWithWords =
	// * code.assignActualProbabilities(codeTree, enabledWords);
	// *
	// *
	// * System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
	// * System.out.println(codeTreeWithWords.toStringPlainTextLispStyle(0));
	// *
	// *
	// * //System.out.println("Min : " + code.searchMinCostTree());
	// */
	//
	// /*
	// * }
	// *
	// * private ContainmentHierarchyNode
	// convertToCHN(Node<JVirtualKeyboardButton>
	// * treeNode) { if(treeNode.isLeaf()) { LeafContainmentHierarchyNode
	// * newCHNode = new
	// * LeafContainmentHierarchyNode(treeNode.getRepresentative()); return
	// * newCHNode; } else { InternalContainmentHierarchyNode newCHNode = new
	// * InternalContainmentHierarchyNode();
	// *
	// * for(Node<JVirtualKeyboardButton> child:
	// ((InternalNode<JVirtualKeyboardButton>)treeNode).getChildren()) {
	// * newCHNode.addChild(convertToCHN(child)); } return newCHNode; } }
	// *
	// *
	// */
}
