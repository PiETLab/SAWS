package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import addressBook.Addressee;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class AddresseeSelectionCommand extends IndirectSelectionFaciltyCommand
		implements MemoApplicationCommand {

	private Addressee addressee;

	// private MemoField field;

	public AddresseeSelectionCommand(Addressee addressee) {
		this.addressee = addressee;
		// this.field = field;
	}

	public void performMemoFillerCommand(MemoFillerController controller) {
		if (IS_VERBOSE) {
			System.out.println("Addressee selected: " + addressee.toString());
			System.out.println("Switching via command: "
					+ this.getClass().getName());
		}

		controller.getTheMemo().updateField(addressee);
		controller.switchToSubController(controller
				.getMemoFieldSelectionController());
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.pausePassiveFocusAdvancement();
		}

		tcf.stopFocusAdvancement();
		// tcf.resetFokus();
		tcf.makeViewEmptyAndResetGloss();

		// if (tcf.isWithPassiveFocusAdvancement()) {
		// tcf.initFokusToStartOfFokusCycle();
		// }
		return false;
	}

	public Addressee getAddressee() {
		return addressee;
	}

}
