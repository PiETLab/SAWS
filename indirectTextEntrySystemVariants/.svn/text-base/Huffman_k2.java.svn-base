/*
 * Created on 11-Aug-2004
 */
package indirectTextEntrySystemVariants;

import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts;
import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import sourceSymbolSet.SourceSymbolSet;
import keyboardLayouts.ButtonLayoutSpecification;
import keyboardLayouts.TamKeyboardLayout;

/**
 * This class implements a text composition facility that has a containment
 * hierarchy that is derived using an Huffman encoding algorithm with k-2 and
 * the passed value for the probability distribution.
 * 
 * @author Melanie Baljko
 */
public class Huffman_k2 extends
		AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts {

	private final static int ENCODING_ALPHABET_SIZE = 2;

	public Huffman_k2() {
		super(new SourceSymbolSet(new ProbDist_Venkatagiri99_Hypothesized()),
				ENCODING_ALPHABET_SIZE);
	}

	// public Huffman_k2(UtilityClasses.ProbabilityDistribution pd) {
	// super(pd, k, new TamKeyboardLayout());
	// }

	@Override
	public ButtonLayoutSpecification setUpKeyboardLayout() {
		// probDistribution = new ProbDist_KonheimMod();
		// assignProbabilitiesToCommands(probDistribution);
		return new TamKeyboardLayout();
	}

}
