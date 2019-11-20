/*
 * Created on 11-Aug-2004
 */
package indirectTextEntrySystemVariants;

import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import sourceSymbolSet.SourceSymbolSet;
import keyboardLayouts.ButtonLayoutSpecification;
import keyboardLayouts.TamKeyboardLayout;
import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts;

/**
 * This class implements a text composition facility that duplicates the row
 * column modified QWERTY (RowColModQWERTY), except it's containment hierarchy
 * is derived using an Huffman encoding algorithm with the value k=10 and the
 * probability distribution called "Prcm" (see excel spreadsheet for
 * derivation).
 * 
 * @author Melanie
 */
public class Huffman_k6 extends
		AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts {

	final int k = 6;
	private final static int ENCODING_ALPHABET_SIZE = 6;

	public Huffman_k6() {
		super(new SourceSymbolSet(new ProbDist_Venkatagiri99_Hypothesized()),
				ENCODING_ALPHABET_SIZE);
	}

	@Override
	public ButtonLayoutSpecification setUpKeyboardLayout() {
		// probDistribution = new ProbDist_KonheimMod();
		// assignProbabilitiesToCommands(probDistribution);
		return new TamKeyboardLayout();
	}

}
