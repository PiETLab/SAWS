package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;


public class PauseForABeatCommand extends IndirectSelectionFaciltyCommand {

	public PauseForABeatCommand() {
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		return false;
	}

}
