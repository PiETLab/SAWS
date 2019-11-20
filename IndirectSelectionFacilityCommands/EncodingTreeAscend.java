package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import treeDataStructure.SelectionGroup;

//import java.awt.event.ActionListener;

public class EncodingTreeAscend extends IndirectSelectionFaciltyCommand {

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		// get rid of all previous highlighting

		getLogger().fine("1\tASCEND");
		getLogger().finer(
				ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_ASCEND_ACTION
						+ "("
						+ tcf.getFokusTimerStatusForCurrentSelectionGroup()
						+ ")");

		// tcf.getView().onScreenKeyboard.resetAppearance();
		// tcf.getView().getEncodingTree().getRoot().getNodeSelectionGroup()
		// .resetDefaultAppearance();

		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.pausePassiveFocusAdvancement();
		}

		boolean wasAscendSuccessful = tcf.ascendFokus();// getView().getEncodingTree()
		// .ascendFokus();

		// MB - NEED TO INSERT TEST TO SEE WHETHER THIS IS ROOT OR STIPULATE
		// PRE-COND
		// boolean wasAscendSuccessful = true;// tcf.getView().getEncodingTree()
		// .ascendFokus(tcf, ae);

		// System.out.println(wasAscendSuccessful);

		// if (wasAscendSuccessful == true &&
		// tcf.isWithPassiveFocusAdvancement()) {
		// PassiveFokusAdvancerSwing.restartEventGenerator();
		// }

		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.initToStartOfFokusCycleAndLaunch();
			//
			// PassiveFokusAdvancerSwing.restartEventGenerator();
		}

		return false;
	}

}
