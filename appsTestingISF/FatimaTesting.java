/*
 * Created on 10-Aug-2004
 *
 */
package appsTestingISF;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;

import encodingTrees.TraversableEncodingTreeGenerator;
import encodingTrees.obsolete.TraversableEncodingTreeI;

import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;
import unequalLetterCostCode.ConstructCodeTree;
import unequalLetterCostCode.Signature;

import IndirectSelectionFacilityCommands.AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand;
import TreeDataStructure.InternalNode;
import TreeDataStructure.LeafNode;
import TreeDataStructure.Node;

/**
 * 
 * This application is used to visualize a containment hierarchy that is
 * constructed for a virtual keyboard, so that it can be examined and validated.
 * Presently, the particular virtual keyboard is hard-coded in this app.
 * 
 * The output from this application is meant to be pasted into a LaTeX file. The
 * output for this visualization is constructed through the invocation of one or
 * more toString*() variant methods on the instance of the text composition
 * facility. See the API for IndirectSelectionKeyboard to see the possible
 * toString* variants.
 * 
 * [Nothing more specific is given here, since this application is modified on
 * an ad-hoc basis]
 * 
 * @author Melanie Baljko, 2004
 * 
 */
public class FatimaTesting {

	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, ClassNotFoundException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
		TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();

		System.out.println("Starting 1");

		SourceSymbolSet sourceSymbols;
		sourceSymbols = sssGen.createInstance("sourceSymbolSet."
				+ "SourceSymbolSet_Venkatagiri99_Hypothesized");

		String str;
		str = "HuffmanEqualCosts";
		str = "encodingTrees." + str;
		TraversableEncodingTreeI encodingTree = tetGen.createInstance(str,
				sourceSymbols, new Integer(2));

//		String kbVariant = "containmentHierarchyVariants.UnequalLetterCostOptimized";
//		// String kbVariant = "containmentHierarchyVariants.LopsidedSample";
//		Constructor constr = Class.forName(kbVariant).getConstructor(
//				(Class[]) null);
//		AbstractOnScreenIndirectSelectionKeyboard_KBLthenET keyboard = (AbstractOnScreenIndirectSelectionKeyboard_KBLthenET) constr
//				.newInstance((Object[]) null);
//		keyboard.getContainmentHierarchy().getRoot().propogateLabels(false);

		//
		// IndirectSelectionKeyboard keyboard = new IndirectSelectionKeyboard()
		//System.out.println(keyboard.getName());
		//generateLaTeXFile(keyboard);

		List<Integer> encodingAlphabetCosts = new Vector<Integer>();
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(2);

		List<JIndirectSelectionButton> sourceSymbols = new Vector<JIndirectSelectionButton>();
		for (JIndirectSelectionButton but : (new ProbDist_Venkatagiri99_Hypothesized())
				.getDomain()) {
			sourceSymbols.add(but);
		}

		InternalNode<JIndirectSelectionButton> rootOfT0 = new InternalNode<JIndirectSelectionButton>();
		rootOfT0.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(0)), encodingAlphabetCosts.get(0));
		rootOfT0.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(1)), encodingAlphabetCosts.get(1));
		rootOfT0.addChild(new LeafNode<JIndirectSelectionButton>(sourceSymbols
				.get(2)), encodingAlphabetCosts.get(2));
		Signature signature = new Signature();

		signature = rootOfT0.getSignature(0);

		// System.out.println(keyboard.getContainmentHierarchy().toStringPlainTextLispStyle());

		System.out.println("Signature :");
		System.out.println(signature);

		System.out.println("Printing line 1");

		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(rootOfT0.toStringPlainTextLispStyle(0));
		// generateLatexFile(new UnequalLetterCostOptimized());

		System.out.println("Printing line 2");

		InternalNode<JIndirectSelectionButton> rootOfT1 = rootOfT0.clone();
		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(rootOfT1.toStringPlainTextLispStyle(0));

		// rootOfT1.getSignature( );
		System.out.println("Printing line 3 Expanded");
		InternalNode<JIndirectSelectionButton> nodeExpanded = rootOfT1.expand(
				0, 1, sourceSymbols, encodingAlphabetCosts);

		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(nodeExpanded.toStringPlainTextLispStyle(0));

		// System.out.println(rootOfT1.getChildren().get(0)==rootOfT0.getChildren().get(0));
		// System.out.println(rootOfT1.getChildByContents(sourceSymbols.get(0)));

		System.out.println("Done.");

		List<Integer> sig1 = new Vector<Integer>();
		sig1.add(16);
		sig1.add(14);
		sig1.add(8);
		sig1.add(2);
		sig1.add(0);

		List<Integer> d1 = new Vector<Integer>();
		d1.add(-1);
		d1.add(0);
		d1.add(0);
		d1.add(2);
		d1.add(2);

		int q1 = 2;

		List<Integer> sig2 = rootOfT1.getNextLevelSignature(sig1, d1, q1);

		System.out.println("Signature 2: ");
		System.out.println(sig2);

		System.out.println("------------New Code ---------------");

		/*
		 * List<Integer> d2 = new Vector<Integer>(); d2.add(0); d2.add(2);
		 * d2.add(1);
		 * 
		 * Signature d = new Signature(d2);
		 */
		List<Integer> encodingCosts = new Vector<Integer>();
		encodingCosts.add(1);
		encodingCosts.add(1);
		encodingCosts.add(2);

		List<Double> prob = new Vector<Double>();
		prob.add(0.0);
		prob.add(0.2);
		prob.add(0.2);
		prob.add(0.2);
		prob.add(0.2);
		prob.add(0.2);

		List<JIndirectSelectionButton> wordList = new Vector<JIndirectSelectionButton>();
		wordList
				.add(new JIndirectSelectionButton("W1", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("W1")));
		wordList
				.add(new JIndirectSelectionButton("W2", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("W2")));
		wordList
				.add(new JIndirectSelectionButton("W3", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("W3")));
		wordList
				.add(new JIndirectSelectionButton("W4", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("W4")));
		wordList
				.add(new JIndirectSelectionButton("W5", new AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand("W5")));

		System.out.println("------------Start ---------------");
		ConstructCodeTree code = new ConstructCodeTree(prob, encodingCosts, 5,
				3);
		// ConstructCodeTree(List <Double> probabilities, List <Integer>
		// costList, int n, int r)

		code.toString();

		System.out.println("------------Finish ---------------");

		System.out.println("Min : " + code.searchMinCostTree());

		// //////////////////////////

		InternalNode<JIndirectSelectionButton> root = code.buildMinCostTree(
				sourceSymbols, encodingAlphabetCosts);
		System.out.println("Printing Tree ");

		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(root.toStringPlainTextLispStyle(0));

		System.out.println("Printing Word Tree 1/5.................. ");
		System.out.println("Printing Word Tree 1/5 depth = int depth = "
				+ root.calculateMaximumLeafDepth(root.getEdgeCostToHere()));
		InternalNode<JIndirectSelectionButton> rootWord1 = code
				.assignActualProbabilities(root, wordList);

		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(rootWord1.toStringPlainTextLispStyle(0));
		System.out.println("*******************************");

		// --------------

		List<Double> prob2 = new Vector<Double>();
		prob2.add(0.0);
		prob2.add(9.0 / 10.0);
		prob2.add(1.0 / 40.0);
		prob2.add(1.0 / 40.0);
		prob2.add(1.0 / 40.0);
		prob2.add(1.0 / 40.0);

		System.out.println("------------Start With Words... ---------------");
		ConstructCodeTree code2 = new ConstructCodeTree(prob2, encodingCosts,
				5, 3);
		code2.toString();

		System.out.println("------------Finish ---------------");

		System.out.println("Min : " + code2.searchMinCostTree());

		// //////////////////////////

		InternalNode<JIndirectSelectionButton> root2 = code2.buildMinCostTree(
				sourceSymbols, encodingAlphabetCosts);
		System.out.println("Printing Tree ");

		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(root2.toStringPlainTextLispStyle(0));

		System.out.println("Printing Word Tree 1/40 depth = int depth = "
				+ root2.calculateMaximumLeafDepth(root2.getEdgeCostToHere()));
		InternalNode<JIndirectSelectionButton> rootWord2 = code2
				.assignActualProbabilities(root2, wordList);

		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(rootWord2.toStringPlainTextLispStyle(0));

		// ---------------- m = 10

		List<Double> prob3 = new Vector<Double>();
		prob3.add(0.0);
		prob3.add(1 / 10.0);
		prob3.add(1 / 10.0);
		prob3.add(1 / 10.0);
		prob3.add(1 / 10.0);
		prob3.add(1 / 10.0);
		prob3.add(1 / 50.0);

		List<Integer> encodingCosts3 = new Vector<Integer>();
		encodingCosts3.add(1);
		encodingCosts3.add(1);
		encodingCosts3.add(1);
		encodingCosts3.add(1);
		encodingCosts3.add(1);
		encodingCosts3.add(1);

		/*
		 * List<Integer> d3 = new Vector<Integer>(); d3.add(0); d3.add(6);
		 */
		// Signature d3_sig = new Signature(d3);
		System.out.println("------------Start Code 3---------------");
		ConstructCodeTree code3 = new ConstructCodeTree(prob3, encodingCosts3,
				6, 6);
		code2.toString();

		System.out.println("------------Finish 3 ---------------");

		System.out.println("Min : " + code3.searchMinCostTree());

		// //////////////////////////

		List<Integer> encodingAlphabetCosts4 = new Vector<Integer>();
		encodingAlphabetCosts4.add(1);
		encodingAlphabetCosts4.add(1);
		encodingAlphabetCosts4.add(1);
		encodingAlphabetCosts4.add(1);
		encodingAlphabetCosts4.add(1);
		encodingAlphabetCosts4.add(1);

		List<JIndirectSelectionButton> sourceSymbols3 = new Vector<JIndirectSelectionButton>();
		for (JIndirectSelectionButton but : (new ProbDist_Venkatagiri99_Hypothesized())
				.getDomain()) {
			sourceSymbols3.add(but);
		}

		InternalNode<JIndirectSelectionButton> root3 = code3.buildMinCostTree(
				sourceSymbols3, encodingAlphabetCosts4);
		System.out.println("Printing Tree ");

		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(root3.toStringPlainTextLispStyle(0));

		// --------------- m = 20

		/*
		 * int m = 10;
		 * 
		 * List<Double> prob4 = new Vector<Double>(); prob4.add(0.0);
		 * 
		 * for(int j = 1; j< m; j++ ) prob4.add(1.0/m);
		 * 
		 * List<Integer> d4 = new Vector<Integer>(); d4.add(0); d4.add(2);
		 * 
		 * Signature d4_sig = new Signature(d4);
		 * System.out.println("------------Start Code 4 m = 20---------------");
		 * ConstructCode code4 = new ConstructCode(prob4, d4_sig, m, m);
		 * code2.toString();
		 * 
		 * 
		 * System.out.println("------------Finish 4---------------");
		 * 
		 * System.out.println("Min : " + code4.searchMinCostTree());
		 * 
		 * ////////////////////////////
		 * 
		 * List<Integer> encodingAlphabetCosts4 = new Vector<Integer>();
		 * 
		 * for (int i = 0; i < 2; i++ ) encodingAlphabetCosts4.add(1);
		 * 
		 * InternalNode<JVirtualKeyboardButton> root4 =
		 * code4.buildMinCostTree(sourceSymbols3, encodingAlphabetCosts4);
		 * System.out.println("Printing Tree ");
		 * 
		 * System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		 * System.out.println(root4.toStringPlainTextLispStyle(0));
		 * 
		 * 
		 * //InternalNode node = new InternalNode(); //node.
		 * //--------------------------
		 * 
		 * //--------------- m = 20
		 */
		int m = 2;
		int encode = 2;

		List<Double> prob5 = new Vector<Double>();
		prob5.add(0.0);

		for (int j = 1; j < m; j++)
			prob5.add(1.0 / m);

		/*
		 * List<Integer> d5 = new Vector<Integer>(); d5.add(0); d5.add(1);
		 * d5.add(1); //d5.add(2);
		 */

		List<Integer> encodingCosts5 = new Vector<Integer>();
		encodingCosts5.add(1);
		encodingCosts5.add(1);

		// Signature d5_sig = new Signature(d5);
		System.out.println("------------Start Code 5 m = 43--------------");
		ConstructCodeTree code5 = new ConstructCodeTree(prob5, encodingCosts5,
				m, m);
		code5.toString();

		System.out.println("------------Finish 5---------------");

		System.out.println("Min : " + code5.searchMinCostTree());

		// //////////////////////////

		System.out.println("Printing tree 5: ");
		List<Integer> encodingAlphabetCosts5 = new Vector<Integer>();

		// for (int i = 0; i < encode; i++ )
		encodingAlphabetCosts5.add(1);
		encodingAlphabetCosts5.add(2);

		// encodingAlphabetCosts5.add(3);
		// encodingAlphabetCosts5.add(3);
		InternalNode<JIndirectSelectionButton> root5 = code5.buildMinCostTree(
				sourceSymbols3, encodingAlphabetCosts5);
		System.out.println("Printing Tree ");

		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(root5.toStringPlainTextLispStyle(0));

		// */

	}

	private static String getHeader() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\documentclass[12pt]{article}" + "\n"
				+ "\\usepackage{palatino}" + "\\usepackage{fullpage}" + "\n"
				+ "\\usepackage{url}" + "\n" + "\\usepackage{amsmath}" + "\n"
				+ "\\usepackage{epic}" + "\n" + "\\usepackage{ecltree}" + "\n"
				+ "\\renewcommand{\\ttdefault}{cmtt}" + "\n"
				+ "\\begin{document}" + "\n");
		return buf.toString();
	}

	private static String getFooter() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\end{document}");
		return buf.toString();
	}

	private static void generateLaTeXFile(
			AbstractOnScreenIndirectSelectionKeyboard_KBLthenET keyboard) {
		try {
			File file = new File("tex" + File.separator + "ch-"
					+ keyboard.getName() + ".tex");
			FileWriter outputFile = new FileWriter(file);
			StringBuffer buf = new StringBuffer();

			buf.append(getHeader());
			buf.append("\\noindent \\url{" + keyboard.getName() + "}\n\n");
			buf.append("\\noindent \\url{"
					+ keyboard.getProbDist().getIdentifier() + "}\n\n");
			buf.append(keyboard.toStringLaTeX());
			buf.append("\\newpage" + "\n");
			buf.append(keyboard.getContainmentHierarchy().getCode()
					.toStringLaTeX());

			//

			buf.append(getFooter());
			outputFile.write(buf.toString());
			outputFile.flush();
			System.out.println("Done.");
		} catch (IOException e) {
			System.out.println("Problems creating file");
			e.printStackTrace();
			System.exit(0);
		}
	}

}
