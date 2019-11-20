package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

import userComposedMemo.UserComposedMemo;

import memoField.MemoField;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class CompleteTheMemoBodyFieldCommand extends DefaultMemoFillerCommand {

	public CompleteTheMemoBodyFieldCommand() {
		super();
	}

	public void performMemoFillerCommand(
			MemoFillerController memoFillerController) {
		// memoFieldSelectionController.stopFocusAdvancement();
		// addresseeSelectionController.putViewToFront();
		// addresseeSelectionController.initToStartOfFokusCycleAndLaunch();
		// memoFillerController.switchToAddressSelectionController();
		memoFillerController.switchToSubController(memoFillerController
				.getMemoBodyController());

	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		super.execute(tcf, ae);
		tcf.stopFocusAdvancement();
		return false;
	}

}
