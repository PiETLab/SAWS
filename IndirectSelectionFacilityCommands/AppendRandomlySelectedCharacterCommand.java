package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class AppendRandomlySelectedCharacterCommand extends
		IndirectSelectionFaciltyCommand implements Serializable {

	private String stringToAppend;

	public AppendRandomlySelectedCharacterCommand(
			IndirectSelectionFacilityController tcf) {
		stringToAppend = tcf.getRandomlySelectedSymbol();
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {

		if (IS_VERBOSE)
			System.out.println("TRYING TO APPEND: " + stringToAppend);
		tcf.appendToGloss(stringToAppend);

		getLogger()
				.fine(
						"1\t"
								+ ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_TARGET_PROMPT);
		getLogger().finer(
				ISFLoggerServices
						.getLoggingTokenForTargetPrompt(stringToAppend));
		return false;
	}

	public String toString() {
		return "AppendCommand, char to append: " + stringToAppend;
	}

	public String getStringToAppend() {
		return stringToAppend;
	}

}

class MyRunnable implements Runnable {
	IndirectSelectionFacilityController tcf;

	public MyRunnable(IndirectSelectionFacilityController tcf) {
		this.tcf = tcf;
	}

	public void run() {
		tcf.clearGloss();
		tcf.makeViewEmptyAndResetGloss();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		IndirectSelectionFaciltyCommand command = new AppendRandomlySelectedCharacterCommand(tcf);
		ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
		((ActionListener) tcf).actionPerformed(ae2);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (tcf.isWithPassiveFocusAdvancement()) {
			// tcf.restartPassiveFocusAdvancement();
			// PassiveFokusAdvancerSwing.initToStartOfFokusCycle2();
			tcf.initToStartOfFokusCycleAndLaunch();
		}

	}

}