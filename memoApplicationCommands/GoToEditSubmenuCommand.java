package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

import userComposedMemo.UserComposedMemo;

import memoField.MemoField;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class GoToEditSubmenuCommand extends IndirectSelectionFaciltyCommand
		implements MemoApplicationCommand {

	public GoToEditSubmenuCommand() {
	}

	public void performMemoFillerCommand(
			MemoFillerController memoFillerController) {
		memoFillerController.switchToSubController(memoFillerController
				.getEditSubMenuSelectionController());
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {
		return false;
	}

	public static GoToEditSubmenuCommand getInstance() {
		GoToEditSubmenuCommand command = new GoToEditSubmenuCommand();
		return command;
	}

}
