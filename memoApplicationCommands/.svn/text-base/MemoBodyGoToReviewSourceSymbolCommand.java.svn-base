package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

import userComposedMemo.UserComposedMemo;

import memoField.MemoField;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class MemoBodyGoToReviewSourceSymbolCommand extends
		IndirectSelectionFaciltyCommand implements
		MemoApplicationCommand {

	// private String memoBodyString;

	// private MemoField theField;

	public MemoBodyGoToReviewSourceSymbolCommand() {
		// super(_charToAppend, theField);
		// this.memoBodyString = memoBodyString;
		// this.theField = theField;
	}

	// public String getStringToAppend() {
	// return memoBodyString;
	// }

	public void performMemoFillerCommand(
			MemoFillerController memoFillerController) {

		memoFillerController.getTheMemo().updateField(
				memoFillerController.getMemoBodyController().getTextGloss());
		// memoFillerController.
		// memoFieldSelectionController.stopFocusAdvancement();
		// addresseeSelectionController.putViewToFront();
		// addresseeSelectionController.initToStartOfFokusCycleAndLaunch();
		memoFillerController.getRSVPController().replaceContent(
				memoFillerController.getTheMemo().getBody());

		memoFillerController.switchToSubController(memoFillerController
				.getRSVPController());
		memoFillerController.getRSVPController().setStateToUnpaused();

	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		return false;
	}

	public static MemoBodyGoToReviewSourceSymbolCommand getInstance(
			String memoBodyString) {
		MemoBodyGoToReviewSourceSymbolCommand command = new MemoBodyGoToReviewSourceSymbolCommand();
		return command;
	}

}
