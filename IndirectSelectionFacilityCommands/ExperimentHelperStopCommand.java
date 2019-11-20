package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import IndirectSelectionFacility.ExperimentHelper;
import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class ExperimentHelperStopCommand extends IndirectSelectionFaciltyCommand {

	public ExperimentHelperStopCommand() {
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {

		if (ExperimentHelper.isTurnedOn()) {
			while (getLogger().getHandlers().length != 0) {
				getLogger().getHandlers()[0].flush();
				getLogger().getHandlers()[0].close();
				getLogger().removeHandler(getLogger().getHandlers()[0]);
			}
			tcf.getView().getCompositionWidget().setDisplayWithAppropriateSubPortionOfGloss(
					ExperimentHelper.getGoodbyeMsg());
			// System.out.println("in ExperimentHelperStopCommand: trying to
			// stop.");
			PassiveFokusAdvancerSwing.hardStopEventGenerator();
		}

		// tcf.compositionArea.append(ae.getActionCommand());
		return false;
	}

}
