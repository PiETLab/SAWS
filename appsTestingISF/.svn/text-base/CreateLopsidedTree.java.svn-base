/*
 * Created on 10-Aug-2004
 *
 */
package appsTestingISF;

import indirectTextEntrySystemVariants.IndirectSelectionKeyboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;

import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;

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
public class CreateLopsidedTree {

	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, ClassNotFoundException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		String kbVariant = "containmentHierarchyVariants.UnequalLetterCostOptimized";
		// String kbVariant = "containmentHierarchyVariants.LopsidedSample";
		// Constructor constr = Class.forName(kbVariant).getConstructor(
		// (Class[]) null);
		// IndirectSelectionKeyboard keyboard = (IndirectSelectionKeyboard)
		// constr
		// .newInstance((Object[]) null);
		// keyboard.getContainmentHierarchy().getRoot().propogateLabels(false);
		//
		// System.out.println(keyboard.getName());
		// generateLaTeXFile(keyboard);

		List<Integer> encodingAlphabetCosts = new Vector<Integer>();
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(1);
		encodingAlphabetCosts.add(3);

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

		// System.out.println(keyboard.getContainmentHierarchy().toStringPlainTextLispStyle());
		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(rootOfT0.toStringPlainTextLispStyle(0));
		
		InternalNode<JIndirectSelectionButton> rootOfT1 = rootOfT0.clone();
		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(rootOfT1.toStringPlainTextLispStyle(0));
		//rootOfT1.getSignature(   );
		//rootOfT1.expand(   );
		//System.out.println(rootOfT1.getChildren().get(0)==rootOfT0.getChildren().get(0));
		//System.out.println(rootOfT1.getChildByContents(sourceSymbols.get(0)));
		
		
		System.out.println("Done.");
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

	private static void generateLaTeXFile(AbstractOnScreenIndirectSelectionKeyboard_KBLthenET keyboard) {
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
