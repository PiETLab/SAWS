package experimentHelper;

import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

/**
 * This class implements an "Experiment Helper", which is a facility that
 * provides support for experiments in which the software needs to keep track of
 * different states and key combinations (trial started, trial stopped, etc)
 * 
 * 
 * 
 * the user is prompted to compose a target text.
 * 
 * It maintains a list of strings, which represent the target texts that are to
 * be presented to the user (one after another, where each trial has one target
 * text).
 * 
 * The client can specify whether target highlighting in on or off (if on, then
 * the target is "highlighted" from the other keys, to help the user in his/her
 * visual scan) [this isn't true yet, since the value is hard-coded, but change
 * in future]
 * 
 * @author mb
 * 
 */
public abstract class ExperimentHelper {

	private static boolean isTurnedOn = true;

	private static boolean isTargetHighlightingOn = false;

	// used for logging purposes
	public static final String KEYBOARD_KEYWORD = "keyboardVariant";

	public static final String TARGETTEXT_KEYWORD = "targetText";

//	public static final String LEAF_REACHED = "*";

	private static final String HTML_START = "<html>";

	private static final String HTML_STOP = "</html>";

	private static final String FONT_START = "<font color = red>";

	private static final String FONT_STOP = "</font>";

	private static final String MODIFIER_LETTER_SHELF = "\u02FD";

	public static final Color TARGET_COLOUR = Color.RED;

	private static final char START_KEY = KeyEvent.VK_ENTER;
	private static final char STOP_KEY = KeyEvent.VK_Q;

	private static boolean META_KEY_ENABLED = true;
	private static boolean META_KEY_PRESSED = false;
	private static List<String> targetTexts = populate();

	private static int currCharIndex = 0;

	private static int currPos = 0;

	private static boolean stateIsTrialStarted = false;

	public static boolean hasTargetText() {
		return currPos <= targetTexts.size() - 1;
	}

	public static boolean hasMoreChars() {
		return currCharIndex <= getTargetText().length() - 1;
	}

	private static String getTargetText() {
		return targetTexts.get(currPos);
	}

	/*
	 * public static boolean getMetaKeyEnabled() { return META_KEY_ENABLED; }
	 */
	public static boolean metaKeyEnabled() {
		return META_KEY_ENABLED;
	}

	public static boolean metaKeyHasBeenPressed() {
		return META_KEY_PRESSED;
	}

	public static void setMetaKeyPressed(boolean val) {
		META_KEY_PRESSED = val;
	}

	public static void setMetaKeyEnabled(boolean val) {
		META_KEY_ENABLED = val;
	}

	/**
	 * This method changes the appearance of one of the buttons (the target) on
	 * the keyboard in order to distinguish it from the others (e.g., to show
	 * the user what the intended target is). This effect takes place only if
	 * isTargetHighlightingOn is set to true.
	 * 
	 * @param keyboard
	 */
	public static void highlightTarget(
			AbstractOnScreenIndirectSelectionKeyboard keyboard) {
		if (isTargetHighlightingOn) {
			keyboard.getButtonByLabel(getCurrentChar()).modifyTextColor(
					ExperimentHelper.TARGET_COLOUR);
		}
	}

	public static void advanceListIndex() {
		currCharIndex = 0;
		currPos++;
	}

	// public static abstract String getSubjectID();

	public static char getStartKey() {
		return START_KEY;
	}

	public static char getStopKey() {
		return STOP_KEY;
	}

	public static int getCurrentCharPos() {
		return currCharIndex;
	}

	/**
	 * This method returns the character in the target text that is currently
	 * the target.
	 * 
	 * @return
	 */
	public static String getCurrentChar() {
		String tmp = getTargetText()
				.substring(currCharIndex, currCharIndex + 1);
		if (isSpaceChar(tmp)) {
			tmp = getSubstituteSpaceChar();
		}
		return tmp;
	}

	private static boolean isSpaceChar(String s) {
		return s.equals(" ");
	}

	private static String getSubstituteSpaceChar() {
		return HTML_START + "\u02fd" + HTML_STOP;
	}

	public static void incrementCharIndex() {
		currCharIndex++;
	}

	public static String getGoodbyeMsg() {
		return "<html>Finished all tasks.  Quit the application now.</html>";
	}

	public static String getInstructions() {
		return "<html>INSTRUCTIONS: <put instruction msg here.> Press ENTER to begin<html>";
	}

	public static String getBetweenTaskMsg() {
		return "<html>Completed sentence.  Press ENTER to begin.<html>";
	}

	/**
	 * This method is used to populate the list of target texts (presently, just
	 * hard-coded)
	 * 
	 * 
	 * PRE : the characters in the target text must be present on the virtual
	 * keyboard.
	 * 
	 * @return
	 */
	private static List<String> populate() {
		List<String> list = new Vector<String>();
		/*
		 * list.add("ABCDE"); list.add("ABCDE"); list.add("ABCDE");
		 * list.add("ABCDE"); list.add("ABCDE");
		 */
		/*
		 * list.add("TH ERF QUI"); /list.add("THE R"); list.add("THE R");
		 * list.add("THE R"); list.add("THE R");
		 */

		// list.add("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOGS");
		// list.add("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOGS");
		list.add("TEST");
		// list.add("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOGS");
		// list.add("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOGS");
		// list.add("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOGS");
		// list.add("THE QUICK BROWN FOX");
		// list.add("JUMPED OVER");
		// list.add("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOGS");
		// list.add("THE BROWN DOG WAGGED HIS TAIL.");
		// list.add("HI");
		// list.add("BYE");

		return list;
	}

	public static boolean isTurnedOn() {
		boolean tmp = isTurnedOn;
		return isTurnedOn;
	}

	public static boolean isTargetHighlightingOn() {
		return isTargetHighlightingOn;
	}

	public static void setTargetHighlightingOn(boolean isTargetHighlightingOn) {
		ExperimentHelper.isTargetHighlightingOn = isTargetHighlightingOn;
	}

	public static void setTurnedOn(boolean isTurnedOn) {
		ExperimentHelper.isTurnedOn = isTurnedOn;
	}

	public static String getTargetTextHTMLized() {
		return htmlize(getTargetText(), getCurrentCharPos());
	}

	public static String getTargetTextPlain() {
		return getTargetText();
	}

	private static String htmlize(String text, int charPos) {
		// all spaces should be converted to the "MODIFIER LETTER SHELF"
		// character, unicode 02fd
		String myText = text.replaceAll(" ", MODIFIER_LETTER_SHELF);
		return HTML_START + myText.substring(0, charPos) + FONT_START
				+ myText.substring(charPos, charPos + 1) + FONT_STOP
				+ myText.substring(charPos + 1) + HTML_STOP;
	}

	public static void decrementCharIndex() {
		currCharIndex--;
	}

	public static void setTrialStartedState(boolean b) {
		stateIsTrialStarted = b;
	}

	public static boolean isTrialStarted() {
		return stateIsTrialStarted == true;
	}

}
