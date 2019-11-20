/*
 * Created on 26-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package indirectTextEntrySystemVariants;

import abstractOnScreenIndirectSelectionKeyboard.AbstractLinearIndirectSelectionKeyboard;
import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import keyboardLayouts.ButtonLayoutSpecification;
import keyboardLayouts.VenkatagiriKeyboardLayout_A4;

/**
 * @author Melanie Baljko
 */
public class LinearUnigram extends AbstractLinearIndirectSelectionKeyboard {

	public LinearUnigram() {
		super();
	}

	@Override
	public ButtonLayoutSpecification deriveKeyboardLayout() {
		// return new TamKeyboardLayout();
		return new VenkatagiriKeyboardLayout_A4();
	}

}
