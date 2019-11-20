package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import userComposedMemo.UserComposedMemo;

import memoField.MemoField;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class DefaultMemoFillerCommand extends IndirectSelectionFaciltyCommand
		implements MemoApplicationCommand {

	private static DefaultMemoFillerCommand standBy;

	public DefaultMemoFillerCommand() {
	}

	/**
	 * Implements actions to be taken and is invoked by indirect selection
	 * facility
	 * 
	 * 
	 */
	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		// tcf.appendToGloss(selectedString);
		// System.out
		// .println("For IndirectSelectionFacilityController.  Execute command associated with: "
		// + this.getClass().getName());

		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.pausePassiveFocusAdvancement();
		}

		tcf.resetFokus();
		tcf.makeViewEmptyAndResetGloss();

		// if (tcf.isWithPassiveFocusAdvancement()) {
		// tcf.initFokusToStartOfFokusCycle();
		// }
		return false;
	}

	public void performMemoFillerCommand(MemoFillerController controller) {
		controller.switchToSubController(controller
				.getMemoFieldSelectionController());
	}

	public static DefaultMemoFillerCommand getInstance() {
		if (standBy == null) {
			standBy = new DefaultMemoFillerCommand();
		}
		return standBy;
	}

	public String toString() {
		return this.getClass().getName();
	}

}
