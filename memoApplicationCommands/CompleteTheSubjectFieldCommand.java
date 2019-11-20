package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

import userComposedMemo.UserComposedMemo;

import memoField.MemoField;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class CompleteTheSubjectFieldCommand extends DefaultMemoFillerCommand {

	private String selectedString;

	private MemoField theField;

	public CompleteTheSubjectFieldCommand(String _charToAppend, MemoField theField) {
		super();
		selectedString = _charToAppend;
		this.theField = theField;
	}

	public String getStringToAppend() {
		return selectedString;
	}

	public MemoField getTheField() {
		return theField;
	}

	public void performMemoFillerCommand(
			MemoFillerController memoFillerController) {
		// memoFieldSelectionController.stopFocusAdvancement();
		// addresseeSelectionController.putViewToFront();
		// addresseeSelectionController.initToStartOfFokusCycleAndLaunch();
		// memoFillerController
		// .switchToSubjectLineSelectionController();
		// memoFillerController.switchToSubController(memoFillerController
		// .getSubjectLineSelectionController());
		memoFillerController.switchToSubController(memoFillerController
				.getSubjectLineSelectionController());
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		super.execute(tcf, ae);
		tcf.stopFocusAdvancement();

		return false;
	}

}
