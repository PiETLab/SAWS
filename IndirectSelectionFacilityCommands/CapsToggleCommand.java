package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

public class CapsToggleCommand extends IndirectSelectionFaciltyCommand {

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		tcf.toggleCapsOnOff();
		IndirectSelectionFaciltyCommand command = new ReinitializeFokusCommand();
		ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
		((ActionListener) tcf).actionPerformed(ae2);
		return false;
	}

}
