package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

public class ReinitializeFokusCommand extends IndirectSelectionFaciltyCommand
		implements Serializable {

	private static final long serialVersionUID = 1L;


	public ReinitializeFokusCommand() {
	}


	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		tcf.resetFokus();
		tcf.initToStartOfFokusCycleAndLaunch();
		return false;
	}

}
