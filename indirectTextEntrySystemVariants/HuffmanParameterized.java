/*
 * Created on 11-Aug-2004
 */
package indirectTextEntrySystemVariants;

import customGUIComponentsISF.JIndirectSelectionButton;
import keyboardLayouts.ButtonLayoutSpecification;
import keyboardLayouts.TamKeyboardLayout;
import UtilityClasses.ProbabilityDistribution;
import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts;
import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

/**
 * This class implements a text composition facility that has a containment
 * hierarchy that is derived using an Huffman encoding algorithm with the passed
 * values for k and the probability distribution.
 * 
 * @author Melanie Baljko
 */
public class HuffmanParameterized extends
		AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts {

	/**
	 * Three methods in parent abstract class will be invoked before this
	 * constructor:
	 */
	public HuffmanParameterized(
			ProbabilityDistribution pd,
			Integer encodingAlphabetSize) {
		super(new SourceSymbolSet(pd), encodingAlphabetSize);
	}

	// /**
	// * Three methods in parent abstract class will be invoked before this
	// * constructor:
	// */
	// public
	// HuffmanParameterized(UtilityClasses.ProbabilityDistribution<JVirtualKeyboardButton>
	// pd,
	// Integer k, KeyboardLayout layout) {
	// super(pd, k, layout);
	// this.k = k;
	// }

	@Override
	public ButtonLayoutSpecification setUpKeyboardLayout() {
		return new TamKeyboardLayout();
	}

}
