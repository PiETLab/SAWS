package indirectTextEntrySystemVariants;

import keyboardLayouts.ButtonLayoutSpecification;
import keyboardLayouts.VenkatagiriKeyboardLayout_A2A5;
import abstractOnScreenIndirectSelectionKeyboard.AbstractLinearIndirectSelectionKeyboard;

public class LinearModQWERTY extends AbstractLinearIndirectSelectionKeyboard {

	public LinearModQWERTY() {
		super();
	}

	@Override
	public ButtonLayoutSpecification deriveKeyboardLayout() {
		// return new TamKeyboardLayout();
		return new VenkatagiriKeyboardLayout_A2A5();
	}
}
