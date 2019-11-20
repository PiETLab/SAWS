package IndirectSelectionFacilityCommands;


import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;


import IndirectSelectionFacility.ExperimentHelper;
import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class ExperimentHelperStartCommand extends IndirectSelectionFaciltyCommand {

	private static int trialNum = 0;

	private final String subjectID;

	public ExperimentHelperStartCommand(String _subjectID) {
		subjectID = _subjectID;
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf, ActionEvent ae)
			throws SecurityException, IOException {

		if (ExperimentHelper.isTurnedOn()) {
			if (ExperimentHelper.hasTargetText()) {
				while (getLogger().getHandlers().length != 0) {
					getLogger().getHandlers()[0].flush();
					getLogger().getHandlers()[0].close();
					getLogger().removeHandler(getLogger().getHandlers()[0]);
				}
				FileHandler fh = new FileHandler(getFileName());
				getLogger().addHandler(fh);
				getLogger().setLevel(Level.ALL);
				getLogger().info(ExperimentHelper.KEYBOARD_KEYWORD + ":"
						+ tcf.getView().getOnScreenKeyboard().getName());
				getLogger().info(ExperimentHelper.TARGETTEXT_KEYWORD + ":"
						+ ExperimentHelper.getTargetTextPlain());

				tcf.getView().getCompositionWidget().resetText();
				tcf.getView().getCompositionWidget().setDisplayWithAppropriateSubPortionOfGloss(ExperimentHelper.getTargetTextHTMLized());
				
//				tcf.getView().getOnScreenKeyboard().highlightSelectable(ExperimentHelper.getCurrentChar(), ExperimentHelper.get);
//				ExperimentHelper
//						.highlightTarget(tcf.getView().onScreenKeyboard.keyboard);
				if (tcf.isWithPassiveFocusAdvancement()) {
					//PassiveFokusAdvancerSwing.restart();
					//PassiveFokusAdvancerSwing.
					PassiveFokusAdvancerSwing.initToStartOfFokusCycle();
				}
			} else {
				ActionEvent ae2 = new ActionEvent(
						new ExperimentHelperStopCommand(), 0, "");
				((ActionListener) tcf).actionPerformed(ae2);
			}
			ActionEvent ae3 = new ActionEvent(
					new EncodingTreePresentInitialView(), 0, "");
			((ActionListener) tcf).actionPerformed(ae3);


		}

		// tcf.compositionArea.append(ae.getActionCommand());
		trialNum++;
		return false;
	}

	private String getDatestamp() {
		final String MONTHS = "JanFebMarAprMayJunJulAugSepOctNovDec";
		final String DAYS = "SunMonTueWedThuFriSat";
		final int LENGTH = 3;

		Calendar rightNow = Calendar.getInstance();
		String month = MONTHS.substring(
				LENGTH * (rightNow.get(Calendar.MONTH)), LENGTH
						* (rightNow.get(Calendar.MONTH)) + LENGTH);
		String day = DAYS.substring(LENGTH
				* (rightNow.get(Calendar.DAY_OF_WEEK) - 1), LENGTH
				* (rightNow.get(Calendar.DAY_OF_WEEK) - 1) + LENGTH);
		// return day + month + rightNow.get(Calendar.DAY_OF_MONTH) + "_"
		// + rightNow.get(Calendar.HOUR_OF_DAY) + ":"
		// + rightNow.get(Calendar.MINUTE) + ":"
		// + rightNow.get(Calendar.SECOND);
		return day + month + rightNow.get(Calendar.DAY_OF_MONTH);
	}

	private String getTimestamp() {
		Calendar rightNow = Calendar.getInstance();
		return +rightNow.get(Calendar.HOUR_OF_DAY) + ":"
				+ rightNow.get(Calendar.MINUTE) + ":"
				+ rightNow.get(Calendar.SECOND);
	}

	private String getSubjectID() {
		return subjectID;
	}

	private int getTrialNum() {
		return trialNum;
	}

	private String getFileName() {
		return "vocaLog." + getSubjectID() + ".Trial" + getTrialNum() + "."
				+ getDatestamp() + ".xml";
	}
}
