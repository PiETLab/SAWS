package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;


public class DeleteCommand extends IndirectSelectionFaciltyCommand {

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf, ActionEvent ae) {
		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.pausePassiveFocusAdvancement();
		}


		tcf.getView().getCompositionWidget().removeCharImmediatelyBeforeCaret();
		
		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.resetFokus();
			tcf.initToStartOfFokusCycleAndLaunch();
		}
		return false;
	}


}
