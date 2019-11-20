package IndirectSelectionFacilityCommands;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class GoToReviewModeCommand extends IndirectSelectionFaciltyCommand {

	private final String DELIM_CHAR = "*";

	private String stringSignifyingFinishCommand;

	public GoToReviewModeCommand(String _charToAppend) {
		stringSignifyingFinishCommand = _charToAppend;
		// super
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {

		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.pausePassiveFocusAdvancement();
		}
		tcf.resetFokus();
		return false;
	}

}
