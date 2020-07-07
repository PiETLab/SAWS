package coreIndirectSelectionFacility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import IndirectSelectionFacilityCommands.StartLoggerCommand;

public class ISFLoggerServices {

	public static final String TAG_FOR_LOGGER_UNIQUELY_DENOTES_SELECT_ACTION = "S";

	public static final String TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED = "L";

	public static final String TAG_FOR_LOGGER_UNIQUELY_DENOTES_WAIT_ACTION = "W";

	public static final String TAG_FOR_LOGGER_UNIQUELY_DENOTES_ASCEND_ACTION = "A";

	public static final String TAG_FOR_LOGGER_UNIQUELY_DENOTES_ENCODING_TREE = "ENCODINGTREE";

	public static String TAG_FOR_LOGGER_UNIQUELY_DENOTES_TARGET_PROMPT = "T";

	private static final String SUB_DIR_NAME = "ISF_Logs";
	private static final String FILE_PREFIX = "ISFLogFile";
	private static final String SESSION_PREFIX = "Session";
	private static final String COMPUTER_NAME = ISFLoggerServices
			.getPropertyValueForComputerName();

	private static int trialNum = 0;

	public static boolean isThisInterfaceActionALeafSelection(String string) {
		return string.substring(0, 1).equals(
				ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED);
	}

	public static String extractSourceSymbolAtLeafSelection(String string) {

		// the string will be of the format:
		// return
		// ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED
		// + "(" + modifyAsNeeded(string) + ")";
		String res = string.replaceFirst(
				ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED,
				"");
		// strip off the first open paren
		res = res.replaceFirst("\\(", "");
		// strip off the closing paren
		res = res.substring(0, res.length() - 1);
		return res;
	}

	public static String extractTargetSelectableFromLoggingTokenForTargetPrompt(
			String string) {
		// the string will be of the format:
		// return
		// ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_TARGET_PROMPT
		// + "(" + modifyAsNeeded(string) + ")";
		String res = string
				.replaceFirst(
						ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_TARGET_PROMPT,
						"");
		// strip off the first open paren
		res = res.replaceFirst("\\(", "");
		// strip off the closing paren
		res = res.substring(0, res.length() - 1);
		return res;
	}

	public static String getLoggingTokenForLeafSelection(
			String sourceSymbolReached) {
		return ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED
				+ "("
				+ translateFromLaTeXSelectableFormatToAppendCharacterFormat(sourceSymbolReached)
				+ ")";
	}

	public static String translateFromLaTeXSelectableFormatToAppendCharacterFormat(
			String sel) {
		if (sel.equals("\u00bf")) {
			sel = "=";
		} else if (sel.equals("?`")) {
			sel = "=";
		} else if (sel.equals("$\\sqcup$")) {
			// replace "space" symbol (square cup in LaTeX) with an actual space
			sel = " ";
		} else if (sel.equals("$\\P$")) {
			// replace the pilcrow symbol ($\\P$ in LaTeX) with an @
			sel = "@";
		}
		return sel;
	}

	public static String getNormalizedVersionOfEmpiricallyObserved(String selectable) {
		if (selectable.equals("$\\sqcup$")) {
			// replace "space" symbol (square cup in LaTeX) with an actual space
			selectable = "$\\sqcup$";
		} else if (selectable.equals(" ")) {
			selectable = "$\\sqcup$";
		} else if (selectable.equals("_")) {
			selectable = "$\\sqcup$";
//		} else if (selectable.equals(" ")) {
//			selectable = "_";
		}
		return selectable;
	}

	public static String getLoggingTokenForTargetPrompt(String string) {
		return ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_TARGET_PROMPT
				+ "("
				+ translateFromLaTeXSelectableFormatToAppendCharacterFormat(string)
				+ ")";
	}

	public static File getSubdirectoryForLogfiles() {
		String subDir = System.getProperty("user.home") + File.separator
				+ SUB_DIR_NAME + File.separator + COMPUTER_NAME;
		// System.out.println(subDir);

		// File theSubDir = new File(subDir);
		// if (!theSubDir.exists())
		File subDirectory = new File(subDir);

		boolean success = subDirectory.mkdirs();
		if (success) {
			System.out.println("Directory: " + subDir + " created");
		}
		return subDirectory;
	}

	public static String getOperatingSystem() {
		return System.getProperty("os.name");
	}

	public static boolean isMacOSX() {
		return System.getProperty("os.name").equals("Mac OS X");
	}

	public static String getPropertyValueForComputerName() {
		// java.net.InetAddress.getLocalHost().getHostName()
		String capturedLine = "";
		if (isMacOSX()) {
			String command = "more /Library/Preferences/SystemConfiguration/preferences.plist ";
			try {
				String outlist[] = runCommand(command);
				boolean shouldCaptureNextLine = false;
				for (int i = 0; i < outlist.length; i++) {
					if (shouldCaptureNextLine) {
						capturedLine = outlist[i];
						break;
					}
					// System.out.println(outlist[i]);
					shouldCaptureNextLine = (outlist[i].indexOf("ComputerName") != -1);
				}
				capturedLine = capturedLine.replaceAll("string", "");
				capturedLine = capturedLine.replaceAll("<", "");
				capturedLine = capturedLine.replaceAll(">", "");
				capturedLine = capturedLine.replaceAll("/", "");
				capturedLine = capturedLine.replaceAll("[ ]*", "");
				capturedLine = capturedLine.replaceAll("[\t]*", "");
				// System.out.println(">>>>" + capturedLine);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		} else {
			capturedLine = System.getenv("COMPUTERNAME");
			// capturedLine = "SUJATHA_WINDOWS_MACHINE";
		}
		return capturedLine;
	}

	/**
	 * Mostly taen from:
	 * http://java.sun.com/developer/TechTips/2000/tt0209.html#tip2
	 * 
	 * @param cmd
	 * @return
	 * @throws IOException
	 */
	static public String[] runCommand(String cmd) throws IOException {

		// set up list to capture command output lines

		ArrayList list = new ArrayList();

		// start command running

		Process proc = Runtime.getRuntime().exec(cmd);

		// get command's output stream and
		// put a buffered reader input stream on it

		InputStream istr = proc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(istr));

		// read output lines from command

		String str;
		while ((str = br.readLine()) != null)
			list.add(str);

		// wait for command to terminate

		try {
			proc.waitFor();
		} catch (InterruptedException e) {
			System.err.println("process was interrupted");
		}

		// check its exit value

		if (proc.exitValue() != 0)
			System.err.println("exit value was non-zero");

		// close stream

		br.close();

		// return list of strings to caller

		return (String[]) list.toArray(new String[0]);
	}

	public static void initializeLogFile(Logger theLogger, String subjectID) {

		while (theLogger.getHandlers().length != 0) {
			theLogger.getHandlers()[0].flush();
			theLogger.getHandlers()[0].close();
			theLogger.removeHandler(theLogger.getHandlers()[0]);
		}

		File subDirectory = ISFLoggerServices.getSubdirectoryForLogfiles();

		String fileName = subDirectory.getAbsolutePath() + File.separator
				+ getFileName(subjectID);
		
		FileHandler fh;
		try {
			fh = new FileHandler(fileName);
			theLogger.addHandler(fh);
			theLogger.setLevel(Level.ALL);
			theLogger.info("Starting  Logging.  Using filename: " + fileName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		trialNum++;
	}

	// ISFLogFile.Meagan.Session0.26May2009_10:23.xml
	public static String getFileName(String subjectID) {
		// GregorianCalendar calendar = new GregorianCalendar();
		// SimpleDateFormat fileNameFormat = new
		// SimpleDateFormat("dMMMyyyy_HH:mm");

		String theName = FILE_PREFIX + "." + subjectID + "." + SESSION_PREFIX
				+ trialNum + "." + getDateAndTimeStamp() + "." + "xml";
		System.out.println("Filename: " + theName);
		// + fileNameFormat.format(calendar.getTime()));
		// return "%h" + "/" + subDirName + "/" + "vocaLog."
		// + fileNameFormat.format(calendar.getTime()) + ".xml";

		// return "vocaLog." + fileNameFormat.format(calendar.getTime()) +
		// ".xml";
		return theName;
	}

	// ISFLogFile.Meagan.Session0.26May2009_10:23.xml
	public static String extractDateStampFromFileName(String string) {
		// strip off the prefix
		String res = string.replaceFirst(FILE_PREFIX + ".", "");
		// strip off the subject id
		res = res.replaceFirst("\\w+" + ".", "");
		// strip off the session id
		res = res.replaceFirst("Session\\d+" + ".", "");
		// strip off the closing file extension
		res = res.replaceFirst("\\.xml", "");
		return res;
	}

	// ISFLogFile.Meagan.Session0.26May2009_10:23.xml
	public static String extractSubjectIDFromFileName(String string) {
		// strip off the prefix
		String res = string.replaceFirst(FILE_PREFIX + ".", "");
		// strip off the session id and everything after
		res = res.replaceFirst("\\.Session\\d+.*", "");
		return res;
	}

	private static String getDateAndTimeStamp() {
		GregorianCalendar calendar = new GregorianCalendar();
//		SimpleDateFormat fileNameFormat = new SimpleDateFormat("dMMMyyyy_HH:mm");
		SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyMMddHHmmssZ");
		// System.out.println("Filename: "
		// + fileNameFormat.format(calendar.getTime()));
		// return "%h" + "/" + subDirName + "/" + "vocaLog."
		// + fileNameFormat.format(calendar.getTime()) + ".xml";

		return fileNameFormat.format(calendar.getTime());
	}

	// private String getDatestamp() {
	// final String MONTHS = "JanFebMarAprMayJunJulAugSepOctNovDec";
	// final String DAYS = "SunMonTueWedThuFriSat";
	// final int LENGTH = 3;
	//
	// Calendar rightNow = Calendar.getInstance();
	// String month = MONTHS.substring(
	// LENGTH * (rightNow.get(Calendar.MONTH)), LENGTH
	// * (rightNow.get(Calendar.MONTH)) + LENGTH);
	// String day = DAYS.substring(LENGTH
	// * (rightNow.get(Calendar.DAY_OF_WEEK) - 1), LENGTH
	// * (rightNow.get(Calendar.DAY_OF_WEEK) - 1) + LENGTH);
	// // return day + month + rightNow.get(Calendar.DAY_OF_MONTH) + "_"
	// // + rightNow.get(Calendar.HOUR_OF_DAY) + ":"
	// // + rightNow.get(Calendar.MINUTE) + ":"
	// // + rightNow.get(Calendar.SECOND);
	// return day + month + rightNow.get(Calendar.DAY_OF_MONTH);
	// }
	//
	// private String getTimestamp() {
	// Calendar rightNow = Calendar.getInstance();
	// return +rightNow.get(Calendar.HOUR_OF_DAY) + ":"
	// + rightNow.get(Calendar.MINUTE) + ":"
	// + rightNow.get(Calendar.SECOND);
	// }

	private static String getLabel(String fileName) {
		// IN: vocaLog.Subject11H.Trial1.FriApr7.xml
		// OUT: Subject11HTrial1

		// strip off prefix
		String s = fileName.replaceAll("vocaLog.", "");
		// remove period before "Trial"
		s = s.replaceAll(".Trial", "Trial");
		// Strip off date at end
		s = s.substring(0, s.indexOf("."));
		return s;
	}

	/**
	 * 
	 * @param fileName
	 *            - of the form vocaLog.Subject11H.Trial1.FriApr7.xml (assume
	 *            the Subject number is always 2 digits)
	 * @return Subjectxx
	 */
	private static String getSubjectID(String fileName) {
		// IN: vocaLog.Subject11H.Trial1.FriApr7.xml
		// OUT: Subject11

		// strip off prefix
		int LENGTH = 9; // 7 for "Subject" and 2 for digits
		String s = fileName.replaceAll("vocaLog.", "");
		s = s.substring(0, LENGTH);
		return s;
	}

	/**
	 * 
	 * @param fileName
	 *            - of the form vocaLog.Subject11H.Trial1.FriApr7.xml (assume
	 *            the Subject number is always 2 digits)
	 * @return either H or RC
	 */
	private static String getTypeOfCond(String fileName) {
		// IN: vocaLog.Subject11H.Trial1.FriApr7.xml
		// OUT: Subject11

		// strip off prefix
		int LENGTH = 9; // 7 for "Subject" and 2 for digits
		String s = fileName.replaceAll("vocaLog.", "");
		s = s.substring(LENGTH, LENGTH + 2);
		s = s.replace(".", "");
		return s;
	}

	/**
	 * 
	 * @param fileName
	 *            - of the form vocaLog.Subject11H.Trial1.FriApr7.xml (assume
	 *            the Subject number is always 2 digits)
	 * @return either H or RC
	 */
	private static String getTrialNumber(String fileName) {
		// IN: vocaLog.Subject11H.Trial1.FriApr7.xml
		// OUT: Subject11

		// strip off prefix
		// -> vocaLog.Subject11H.Trial1.FriApr7.xml
		String s = fileName.replaceAll("vocaLog.", "");
		// -> Subject11H.Trial1.FriApr7.xml
		// Strip off date at end
		s = s.substring(s.indexOf(".") + 1);
		// -> Trial1.FriApr7.xml
		s = s.substring(0, s.indexOf("."));
		// -> Trial1.
		s = s.replace("Trial", "");
		// -> 1.
		return s;
	}

}
