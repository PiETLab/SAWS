package IndirectSelectionFacilityCommands;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class SignifyFinishedCommand extends IndirectSelectionFaciltyCommand {

	private final String DELIM_CHAR = "*";

	private String stringSignifyingFinishCommand;

	public SignifyFinishedCommand(String _charToAppend) {
		stringSignifyingFinishCommand = _charToAppend;
		// super
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {

		tcf.appendToGloss("");
		ISFLoggerServices.getLogger().fine("1\t" + DELIM_CHAR);
		ISFLoggerServices.getLogger().finer(
				ExperimentHelper.LEAF_REACHED + "(" + stringSignifyingFinishCommand + ")");

		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.pausePassiveFocusAdvancement();
		}
		tcf.resetFokus();
		return false;
	}

}
