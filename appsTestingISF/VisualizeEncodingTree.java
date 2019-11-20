package appsTestingISF;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard;

/**
 * 
 * 
 * 
 * 
 */
public class VisualizeEncodingTree {
	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, ClassNotFoundException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		String packageName = "indirectTextEntrySystemVariants";
		String variant;
		// Venkatagiri99
		// A1:
		variant = "RowColUnigram";
		// A2:
		variant = "RowColModQWERTY";
		// A3:
		variant = "RowColModLexicographic";
		// A4:
		variant = "LinearUnigram";
		// A5:
		variant = "LinearModQWERTY";
		// A6:
		variant = "LinearModLexicographic";
		// Huffman
		variant = "Huffman_k10_obsolete";

		// The variants below are not working at present
		// A7:
		variant = "ThreeLevelUnigram";
		// A8:
		variant = "ThreeLevelModLexicographic";

		// OTHERS:
		// O1
		// huffman encoding
		variant = "Huffman_k2";
		variant = "Huffman_k7";
		variant = "Huffman_k8";
		// O2: Row-Col regular QWERTY
		variant = "Huffman_k2";
		// Golin:
		variant = "UnequalLetterCostOptimized";
		// need a better mechanism for this...
		variant = "UnequalLetterCostOptimized";

		variant = "HuffmanWithUnequalLetterCost_RamayKeyboard";

		// variant = "RowColModQWERTY";

		String keyboardVariant = packageName + "." + variant;

		Constructor<?> constr = Class.forName(keyboardVariant).getConstructor(
				(Class[]) null);
		AbstractOnScreenIndirectSelectionKeyboard keyboard = (AbstractOnScreenIndirectSelectionKeyboard) constr
				.newInstance((Object[]) null);
		keyboard.getEncodingTree().getRoot().propogateLabels(false);

		System.out.println(keyboard.getName());
		UtilityClassesISF.PrintingUtilities.generateLaTeXFile(keyboard, "tex");
		System.out.println("Done.");
	}

}
