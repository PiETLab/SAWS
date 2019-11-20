package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

import userComposedMemo.UserComposedMemo;

import memoField.MemoField;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class StopTheMemoCompositionCommand extends DefaultMemoFillerCommand {

	private String selectedString;

	private MemoField theField;

	public StopTheMemoCompositionCommand(String _charToAppend, MemoField theField) {
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
		memoFillerController.stop();
		memoFillerController.showMessageAndTerminate();
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		super.execute(tcf, ae);
		tcf.stopFocusAdvancement();
		return false;
	}

}
