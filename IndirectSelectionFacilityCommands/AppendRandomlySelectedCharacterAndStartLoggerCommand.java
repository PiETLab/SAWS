package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class AppendRandomlySelectedCharacterAndStartLoggerCommand extends
		IndirectSelectionFaciltyCommand implements Serializable {

	String stringToAppend;

	public AppendRandomlySelectedCharacterAndStartLoggerCommand() {
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {

		stringToAppend = tcf.getRandomlySelectedSymbol();
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
		//
		// getLogger().finer("X" + "(" + stringToAppend + ")");

		return false;
	}

	public String toString() {
		return "AppendCommand, char to append: " + stringToAppend;
	}

	public String getStringToAppend() {
		return stringToAppend;
	}

}
