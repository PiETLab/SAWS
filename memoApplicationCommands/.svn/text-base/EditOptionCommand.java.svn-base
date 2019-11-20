package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import memoControl.EditMenuOption;

import addressBook.Addressee;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class EditOptionCommand extends IndirectSelectionFaciltyCommand
		implements MemoApplicationCommand {

	// private MemoField field;

	public EditOptionCommand(EditMenuOption editMenuOption) {
		// this.field = field;
	}

	public void performMemoFillerCommand(MemoFillerController controller) {
		// System.out.println("Addressee selected: " + addressee.toString());
		// controller.getTheMemo().updateField(addressee);
		controller.getMemoBodyController().repositionCaret(
				controller.getRSVPController().getCurrentPositionWithText());
		controller.switchToSubController(controller.getMemoBodyController());
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.pausePassiveFocusAdvancement();
		}

		// tcf.stopFocusAdvancement();
		tcf.resetFokus();
		tcf.makeViewEmptyAndResetGloss();

		// if (tcf.isWithPassiveFocusAdvancement()) {
		// tcf.initFokusToStartOfFokusCycle();
		// }
		return false;
	}

}
