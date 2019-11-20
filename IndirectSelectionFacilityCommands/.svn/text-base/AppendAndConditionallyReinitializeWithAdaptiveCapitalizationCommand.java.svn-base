package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

public class AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand
		extends IndirectSelectionFaciltyCommand implements Serializable {

	String stringToAppend;
	private boolean IS_ADAPTIVE_CAPITALIZATION_ENABLED = false;

	public AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand(
			String _charToAppend) {
		stringToAppend = _charToAppend;
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		// System.out.print("TRYING TO APPEND");
		tcf.appendToGloss(stringToAppend);

		getLogger()
				.fine(
						"1\t"
								+ ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED);
		String tmp = stringToAppend.toUpperCase();
		if (tmp.indexOf(".") != -1) {
			tmp = ".";
		}

		getLogger().finer(
				ISFLoggerServices.getLoggingTokenForLeafSelection(tmp));

		tcf.pausePassiveFocusAdvancement();
		tcf.resetFokus();

		if (IS_ADAPTIVE_CAPITALIZATION_ENABLED) {

			if (tcf.shouldToggleCapsOn()) {
				IndirectSelectionFaciltyCommand command = new CapsOnCommand();
				ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
				((ActionListener) tcf).actionPerformed(ae2);
			} else if (tcf.shouldToggleCapsOff()) {
				IndirectSelectionFaciltyCommand command = new CapsOffCommand();
				ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
				((ActionListener) tcf).actionPerformed(ae2);
			}
		}

		if (tcf.shouldAutomaticallyReinitializeGlossAfterCompleteTraversal()) {
			tcf.makeViewEmptyAndResetGloss();
		}

		if (tcf.shouldAutomaticallyReinitializeFokusAfterCompleteTraversal()) {
			IndirectSelectionFaciltyCommand command = new ReinitializeFokusCommand();
			ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
			((ActionListener) tcf).actionPerformed(ae2);
		}

		return false;
	}

	public String toString() {
		return "AppendCommand, char to append: " + stringToAppend;
	}

	public String getStringToAppend() {
		return stringToAppend;
	}

	public void toggleCapsOnOff() {
		if (this.isCapsOn()) {
			this.toggleCapsOff();
		} else {
			this.toggleCapsOn();
		}
	}

	public void toggleCapsOn() {
		stringToAppend = stringToAppend.toUpperCase();
	}

	public void toggleCapsOff() {
		stringToAppend = stringToAppend.toLowerCase();
	}

	private boolean isCapsOn() {
		return stringToAppend.toUpperCase().equals(stringToAppend);
	}

}
