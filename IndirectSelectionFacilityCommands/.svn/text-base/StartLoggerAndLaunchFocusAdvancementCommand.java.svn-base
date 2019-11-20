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

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import encodingTrees.Code;
import encodingTrees.CodeWord;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class StartLoggerAndLaunchFocusAdvancementCommand extends
		IndirectSelectionFaciltyCommand {

	private static int trialNum = 0;
	String subDirName = "Logs";
	File subDirectory = new File(subDirName);

	private final String subjectID;

	public StartLoggerAndLaunchFocusAdvancementCommand(String _subjectID) {
		subjectID = _subjectID;
		// if (!subDirectory.exists()) {
		// try {
		// subDirectory.createNewFile();
		// } catch (IOException e) {
		// e.printStackTrace();
		// System.exit(0);
		// }
		// }
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {

		(new StartLoggerCommand(tcf.getParameterModel().getUserId())).execute(
				tcf, ae);

		while (getLogger().getHandlers().length != 0) {
			getLogger().getHandlers()[0].flush();
			getLogger().getHandlers()[0].close();
			getLogger().removeHandler(getLogger().getHandlers()[0]);
		}
		String fileName = getFileNameUpdatedVersion();
		FileHandler fh = new FileHandler(fileName);
		getLogger().addHandler(fh);
		getLogger().setLevel(Level.ALL);
		getLogger().info(
				"Starting Experiment Logging.  Using filename: " + fileName);

		Code c = tcf.getCode();
		getLogger().finer(c.toStringForLoggerFileToBeParsed());

		// IndirectSelectionFaciltyCommand command = new
		// AppendRandomlySelectedCharacterCommand();
		// ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
		// ((ActionListener) tcf).actionPerformed(ae2);

		// List<CodeWord> cw2 = new Vector<CodeWord>();
		// Code other = new Code();
		// for (CodeWord cw : c.getCodeWords()) {
		// getLogger().finer("R(" + cw.toString() + ")");
		// System.out.println(cw.toString());
		// }

		trialNum++;
		tcf.launchPassiveFocusAdvancement();
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

	private String getFileNameUpdatedVersion() {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat fileNameFormat = new SimpleDateFormat("dMMMyyyy_HH:mm");
		System.out.println("Filename: "
				+ fileNameFormat.format(calendar.getTime()));
		// return "%h" + "/" + subDirName + "/" + "vocaLog."
		// + fileNameFormat.format(calendar.getTime()) + ".xml";

		// return "vocaLog." + fileNameFormat.format(calendar.getTime()) +
		// ".xml";
		return "vocaLog." + getSubjectID() + ".Trial" + getTrialNum() + "."
				+ getDateAndTimeStamp() + ".xml";
	}

	private String getDateAndTimeStamp() {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat fileNameFormat = new SimpleDateFormat("dMMMyyyy_HH:mm");
		System.out.println("Filename: "
				+ fileNameFormat.format(calendar.getTime()));
		// return "%h" + "/" + subDirName + "/" + "vocaLog."
		// + fileNameFormat.format(calendar.getTime()) + ".xml";

		return fileNameFormat.format(calendar.getTime());
	}

}
