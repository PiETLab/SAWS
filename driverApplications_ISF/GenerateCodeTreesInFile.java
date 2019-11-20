/*
 * Created on 10-Aug-2004
 *
 */
package driverApplications_ISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import coreIndirectSelectionFacility.IndirectSelectionFacilityView;

import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;

import IndirectSelectionFacility.OnScreenKeyboardView;
import SoftwareDeployment.UserSpecificModel;

import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

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
public class GenerateCodeTreesInFile {

	private static IndirectSelectionFacilityInvocationParameterModel paramModel;

	static boolean isShowNodeIDsOn = false;
	static boolean isShowDepthsOn = false;

	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, ClassNotFoundException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		String args2 = "-eh false "
				+ "-mt encodingTrees.UnigramBasedET_2Level -ec EncodingTreeFromManualSpecification "
				+ "-di IndirectSelectionFacility.ShowMaxKeyboardInAvailableRealEstate "
				+ "-di IndirectSelectionFacility.ShowKeyboardWithAsFewElementsAsPossible "
				+ "-di IndirectSelectionFacility.ShowFullKeyboardAtAllTimes "
				+ "-kb RowColumnSizedForTwoLevelEncodingTree -vi  " + ""
				+ "-fa passive  -rb true -ff sansserif -kp 0.75" + "";

		String ROW_COL_QWERTY = "-eh false -kb VenkatagiriKeyboardLayout_A1 -fa passive -te kb_et "
				+ "";

		String tmp = "-eh false -kb SingleRowLexico -fa passive -te et_kb "
				+ "-ec HuffmanEqualCosts -sl SourceSymbolSet_10SymbolsOnly -ea 10";

		String args4 = "-eh false "
				+ "-te manual "
//				+ "-ec HuffmanEqualCosts "
				+ "-ec encodingTrees.RowColFromKB "
				+ "-ea 4 "
//				+ "-sl MiniSet_Size8 "
				+ "-sl MeaganCompositionSet "
				+ "-mt encodingTrees.AlphabeticET_MeaganSubset_2Level "
				+ "-di IndirectSelectionFacility.ShowMaxKeyboardInAvailableRealEstate "
				+ "-kb RowColumnSizedForTwoLevelEncodingTree -vi  " + ""
				+ "-fa passive  -rb true -ff sansserif -kp 0.75" + "";

		UserSpecificModel um = new IndirectSelectionFacilityUserModel(args4,
				false);
		paramModel = (IndirectSelectionFacilityInvocationParameterModel) um
				.getInvocationParameters();
		TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();
		TraversableEncodingTree encodingTree = tetGen
				.generateEncodingTree(paramModel);

		System.out.println(encodingTree.getClass());
		generateLaTeXFile(encodingTree);

		// for (String kbVariant : TCFVariants.getVariants()) {
		// Constructor constr = Class.forName(kbVariant).getConstructor(
		// (Class[]) null);
		// IndirectSelectionKeyboard keyboard = (IndirectSelectionKeyboard)
		// constr
		// .newInstance((Object[]) null);
		// keyboard.getContainmentHierarchy().getRoot().propogateLabels(false);
		//
		// System.out.println(keyboard.getName());
		// generateLaTeXFile(keyboard);
		// }
		System.out.println("Done.");
	}

	private static String getHeader() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\documentclass[12pt]{article}" + "\n"
				+ "\\usepackage{palatino}\n" + "\\usepackage{fullpage}" + "\n"
				+ "\\usepackage{url}" + "\n" + "\\usepackage{amsmath}" + "\n"
				+ "\\usepackage{epic}" + "\n" + "\\usepackage{ecltree}" + "\n"
				+ "\\usepackage{eepic}" + "\n"
				+ "\\renewcommand{\\ttdefault}{cmtt}" + "\n"
				+ "\\begin{document}" + "\n");
		return buf.toString();
	}

	private static String getFooter() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\end{document}");
		return buf.toString();
	}

	/**
	 * @param et
	 */
	private static void generateLaTeXFile(TraversableEncodingTree et) {
		try {
			File file = new File("tex" + File.separator + "ET-"
					+ et.getClass().getName() + ".tex");
			FileWriter outputFile = new FileWriter(file);
			System.out.println("Creating file: " + file);
			StringBuffer buf = new StringBuffer();

			buf.append(getHeader());
			buf.append("\\noindent \\url{" + et.getClass().getName() + "}\n\n");
			// buf.append("\\noindent \\url{" +
			// isf.getProbDist().getIdentifier()
			// + "}\n\n");
			buf.append(et.toStringLaTeXecltree(isShowNodeIDsOn, isShowDepthsOn,
					false));
			buf.append("\\newpage" + "\n");
			buf.append(et.getCode().toStringLaTeX());
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
