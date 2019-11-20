package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class StopLoggerCommand extends IndirectSelectionFaciltyCommand {

	public StopLoggerCommand() {
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		getLogger().info("Stopping Experiment Logging.");

		while (getLogger().getHandlers().length != 0) {
			getLogger().getHandlers()[0].flush();
			getLogger().getHandlers()[0].close();
			getLogger().removeHandler(getLogger().getHandlers()[0]);
		}
		tcf.stopFocusAdvancement();
		return false;
	}

}
