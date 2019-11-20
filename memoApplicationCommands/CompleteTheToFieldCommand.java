package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

import userComposedMemo.UserComposedMemo;

import memoField.MemoField;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class CompleteTheToFieldCommand extends DefaultMemoFillerCommand {

	private String selectedString;

	private MemoField theField;

	public CompleteTheToFieldCommand(String _charToAppend, MemoField theField) {
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
		// memoFillerController.switchToAddressSelectionController();
		memoFillerController.switchToSubController(memoFillerController
				.getAddresseeSelectionController());

	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		super.execute(tcf, ae);
		tcf.stopFocusAdvancement();
		return false;
	}

}
