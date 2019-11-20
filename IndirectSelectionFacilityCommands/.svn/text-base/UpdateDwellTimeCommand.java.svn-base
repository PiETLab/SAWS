package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;


public class UpdateDwellTimeCommand extends IndirectSelectionFaciltyCommand {

	private String info;

	public UpdateDwellTimeCommand(String _info) {
		info = _info;
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf, ActionEvent ae) {
		tcf.getView().getInfoPanel().updateDwellTime(info);
		return false;
	}

}
