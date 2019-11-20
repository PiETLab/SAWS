package IndirectSelectionFacilityCommands;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import encodingTrees.Code;
import encodingTrees.CodeWord;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class LaunchFocusAdvancementCommand extends
		IndirectSelectionFaciltyCommand {

	private final String subjectID;

	public LaunchFocusAdvancementCommand(String _subjectID) {
		subjectID = _subjectID;
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {

		IndirectSelectionFaciltyCommand command = new ReinitializeFokusCommand();
		ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
		((ActionListener) tcf).actionPerformed(ae2);
		tcf.launchPassiveFocusAdvancement();

		return false;
	}

	private String getSubjectID() {
		return subjectID;
	}

}
