package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import treeDataStructure.SelectionGroup;

//import java.awt.event.ActionListener;

public class AdvanceFokusCommand extends IndirectSelectionFaciltyCommand {

	@Override
	public boolean execute(IndirectSelectionFacilityController isfController,
			ActionEvent ae) {

		getLogger().fine("1\tWAIT");
		// getLogger().finer(
		// "W("
		// + isfController.getFokusTimerStatus() + ")");
		getLogger()
				.finer(
						ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_WAIT_ACTION
								+ "("
								+ isfController
										.getDurationOfTimeCurrentFocusGroupHasBeenInFocus()
								+ ")");
		isfController.shiftFokusToNextSG();
		// tcf.getView().
		// SelectionGroup sg;
		// sg = tcf.getView().getEncodingTree().getCurrentSelectionGroup();
		// sg.putOutOfFokus();
		// // log.info(sg.getStats());
		//
		// getLogger().fine("1\tWAIT");
		// getLogger().finer("W(" + sg.getFokusTimerStatus() + ")");
		//
		// tcf.getView().getEncodingTree().advanceFokus();
		// sg = tcf.getView().getEncodingTree().getCurrentSelectionGroup();
		// // System.out.println(keyboard.getCurrentSelectionGroup());
		// sg.putInFokus();
		return false;
	}

}
