package memoApplicationCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import addressBook.Addressee;

import subjectLine.SubjectLine;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.MemoFillerController;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class SubjectLineSelectedCommand extends IndirectSelectionFaciltyCommand
		implements MemoApplicationCommand {

	private SubjectLine subjectLine;

	// private MemoField field;

	public SubjectLineSelectedCommand(SubjectLine addressee) {
		this.subjectLine = addressee;
		// this.field = field;
	}

	public void performMemoFillerCommand(MemoFillerController controller) {
		if (IS_VERBOSE) {
			System.out.println("Subject Line selected: "
					+ subjectLine.toString());
			System.out.println("Switching via command: "
					+ this.getClass().getName());
		}
		controller.getTheMemo().updateField(subjectLine);
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

	public SubjectLine getSubjectLine() {
		return subjectLine;
	}

}
