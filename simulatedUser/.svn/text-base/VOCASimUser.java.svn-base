/*
 * Created on 8-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package simulatedUser;

// import java.awt.EventQueue;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import customGUIComponentsISF.JIndirectSelectionButton;


import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import IndirectSelectionFacility.RecordingModule;
import IndirectSelectionFacility.TextEntrySystemFrame;
import treeDataStructure.SelectionGroup;

// import javax.swing.SwingUtilities;

/**
 * This class implements a simulated VOCA user.
 * <p>
 * This class implements the FocusListener interface, so that it can be
 * installed as a listener on the text composition facility. The simulated user
 * fires action event, conditioned on the focus gained events, which it is
 * monitoring. The action events emmulate someone actually clicking a button.
 * <p>
 * It implements the Runnable interface, so that an instance of this class can
 * be passed to the Thread constructor.
 * <p>
 * How the simulated user knows what actions to perform:<br>
 * The simulated VOCA user "consults" a file that contains a corpus of text
 * glosses. This is how the simulated VOCA user "knows" what to type. When the
 * target selectable gains focus (i.e., a button that is labelled with a
 * character, or, to be implemented later, word), then it fires an action event.
 * <br>
 * The name of the file containing the text glosses is passed to the constructor
 * of this class. <br>
 * Once a text gloss has been entered by the simulated user, information is
 * printed to an output file
 * <p>
 * Issue: after the focus gained event is broadcast, will it ever be the case
 * that focus will advance once more in the duration it takes the simulated user
 * to respond? (e.g., it will fire an action event, but focus has advanced and
 * thus, the action will be applied to an unintended target). THIS DOESN"T
 * HAPPEN, because the whole focusGained method has been placed in a
 * synchronized block, meaning that...
 * 
 * 
 * @author Melanie Baljko
 */
public class VOCASimUser implements Runnable, FocusListener {

	// we need an instance of a frame so that we can install
	// a focus listener on it, which is done in the method run()
	final String CHAR_INFO_PREFIX = "c=";

	final String WORD_INFO_PREFIX = "w=";

	final String WORD_DELIMITERS = " \u00b6";

	TextEntrySystemFrame frame;

	long inFocusDwell;

	RecordingModule outputFile;

	BufferedReader in;

	String currentTextGloss;

	int currCaretPos = 0;

	String currentTextGlossID;

	String currTarget = "";

	JIndirectSelectionButton targetButton;

	StringTokenizer st;

	long startTG, finishTG;

	int numIA = 0; // number of input actions

	int numDec = 0; // number of decisions

	int numDecCurrWord = 0;

	int numDecCurrChar = 0;

	String charInfo = CHAR_INFO_PREFIX;

	String wordInfo = WORD_INFO_PREFIX;

	public VOCASimUser(TextEntrySystemFrame _frame,
			String _inputFileName) throws IOException {
		frame = _frame;
		inFocusDwell = PassiveFokusAdvancerSwing.getDwellTime();
		try {
			in = new BufferedReader(new FileReader(_inputFileName));
			String className = frame.onScreenKeyboard.keyboard.getClass().toString();
			className = className.substring(className.lastIndexOf(".") + 1);
			outputFile = new RecordingModule(_inputFileName, className);
		} catch (IOException ie) {
			System.out.println("Could not create simUser, could not read: "
					+ _inputFileName);
			ie.printStackTrace();
			System.exit(0);
		}

		// write header information
		outputFile
				.recordData("(1) GlossID\n(2)num(words)\n(3)num(chars)\n(4)num(dec)\n(5)num(dec|wi)\n(6)num(dec|cj)\n");
		outputFile.recordData("(1)\t(2)\t(3)\t(4)\t(5)\t(6)\n");

		if (getNextTextGloss() != null) {
			startTG = System.currentTimeMillis();
			numIA = 0;
			currTarget = getCurrTarget();
			targetButton = frame.onScreenKeyboard.keyboard
					.getButtonByLabel(currTarget);
		} else {
			System.out.println("No more input from corpus.");
			currTarget = "";
		}
		printTarget();
	}

	public void run() {
		frame.addFocusListener(this);
		System.out.println("VOCASimUser: SimUser awake!!!!");
		// frame.focusNextComponent();
		// advanceFocus.run();
	}

	public void focusGained(FocusEvent fe) {
		System.out.println("VOCASimUser:\focus gain detected");
		// once a focus gain is detected on the text composition
		// facility,
		// we want to prevent the passive focus advancer from
		// changing the focus until the body of this method is
		// completely finished
		synchronized (frame) {
			JIndirectSelectionButton but = (JIndirectSelectionButton) fe
					.getSource();
			String s = but.getActionCommand();
			SelectionGroup sg = frame.onScreenKeyboard.keyboard
					.getSelectionGroupByButton(but);
			System.out.println("VOCASimUser:\tFocusGained detected for SG:\t"
					+ sg);
			// try {
			// System.out.println("TEST: " + EventQueue.isDispatchThread());
			// Thread.sleep(200);
			// } catch (Exception e) {
			// }

			// System.out.println("VOCASimUser: focusGained detected \n\t" +
			// fe.getSource());

			// SUCCESSIVE ' (initial) CHARACTERS NEED AN INTERMEDIATE
			// COMPLETE CYCLE
			// ( for focus to be gained the second time)
			// outputFile.recordData(currTarget);
			// s is a representative of the current SG; determine whether the SG
			// contains the target
			// JVirtualKeyboardButton target = frame.getButtonByTextLabel(s);
			if (sg.isDummy())
				return;
			numDecCurrChar++;
			numDecCurrWord++;
			numDec++;
			if (!sg.contains(targetButton)) {
				printTarget();
				System.out
						.println("VOCASimUser:\tNOT generating event for the SG that is in focus");
			} else {
				// Selection Group in focus contains target Button
				printTarget();
				System.out
						.println("VOCASimUser:\tGENERATING event for the SG that is in focus");
				// ensure the source of the Action Event is the
				// JVirtualKeyboardButton
				// frame.fireActionEvent(but);
				// 1001 = ACTION_PERFORMED
				/*
				 * but.actionPerformed( new ActionEvent( but, 1001,
				 * but.getActionCommand(), System.currentTimeMillis(), 0));
				 */
				// try {
				// Thread.sleep(200);
				// } catch (Exception e){}
				but.doClick();
				/*
				 * SwingUtilities.invokeLater(new Runnable() { public void run() {
				 * but.actionPerformed( new ActionEvent( but, 1001,
				 * but.getActionCommand(), System.currentTimeMillis(), 0)); }
				 * });
				 */
				numIA++;
				if (sg.isTrivial()) {
					// have found target
					if (CHAR_INFO_PREFIX.equals(charInfo))
						charInfo = charInfo + currTarget + ":" + numDecCurrChar;
					else {
						charInfo = charInfo + "," + currTarget + ":"
								+ numDecCurrChar;
					}
					numDecCurrChar = 0;
					// a word is defined as any sequence of characters
					// terminated by a space or the pilcrow
					if (WORD_DELIMITERS
							.indexOf(targetButton.getActionCommand()) != -1) {
						if (WORD_INFO_PREFIX.equals(wordInfo))
							wordInfo = wordInfo + numDecCurrWord;
						else
							wordInfo = wordInfo + "," + numDecCurrWord;
						numDecCurrWord = 0;
					}
					if (!isCurrTargetLastOne()) {
						currCaretPos++;
						currTarget = getCurrTarget();
						targetButton = frame
								.getButtonByActionCommand(currTarget);
						printTarget();
					} else {
						// Record stats for the text gloss we have
						// just finished processing
						finishTG = System.currentTimeMillis();
						outputFile.recordData(currentTextGlossID + "\t");
						// outputFile.recordData((finishTG - startTG) + "\t");
						// outputFile.recordData(numIA + "\n");
						outputFile.recordData(getNumWords(currentTextGloss)
								+ "\t");
						outputFile.recordData(getNumChars(currentTextGloss)
								+ "\t");
						outputFile.recordData(numDec + "\t");
						outputFile.recordData(wordInfo + "\t");
						outputFile.recordData(charInfo);
						outputFile.recordData("\n");
						// Now, get the next text gloss, if there
						// is one
						if (getNextTextGloss() != null) {
							numDec = 0;
							wordInfo = WORD_INFO_PREFIX;
							numDecCurrWord = 0;
							charInfo = CHAR_INFO_PREFIX;
							numDecCurrChar = 0;
							startTG = System.currentTimeMillis();
							numIA = 0;
							currTarget = getCurrTarget();
							targetButton = frame
									.getButtonByActionCommand(currTarget);
							printTarget();
						} else {
							System.out.println("");
							System.out
									.println("VOCASimUser:\tNo more input from corpus.");
							PassiveFokusAdvancerSwing.pause();

						} // end of if-else block to get next text gloss
					} // end of if block for terminal targets
				} // end of if block for trivial SG's
			} // end of if block for on-target selection groups
		} // end of synchronized block
	} // end of method

	/*
	 * The method focusLost will never be invoked because the component that
	 * this focus listener is installed on never generates focus lost events.
	 */
	public void focusLost(FocusEvent fe) {
		// System.out.println("VOCASimUser - focus lost " + fe.getSource() );
	}

	/**
	 * This method gets the next text gloss from the input file or null if the
	 * end of file has been reached.
	 * 
	 * @return
	 */
	private String getNextTextGloss() {
		try {
			// in.readLine() returns null if the end of stream has been
			// reached
			currentTextGloss = in.readLine();
			System.out.print("VOCASimUser: currentTextGloss>");
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		if (currentTextGloss == null)
			return null;
		else {
			st = new StringTokenizer(currentTextGloss, "\t");
			currentTextGlossID = st.nextToken();
			currentTextGloss = st.nextToken();
			currCaretPos = 0;
			System.out.println(currentTextGloss);
			return currentTextGloss;
		}
	}

	private String getCurrTarget() {
		return getCurrentTextGloss().substring(currCaretPos, currCaretPos + 1)
				.toUpperCase();
	}

	/**
	 * 
	 */
	private String getCurrentTextGloss() {
		return currentTextGloss;
	}

	public boolean isCurrTargetLastOne() {
		if (currentTextGloss == null)
			return false;
		return (currCaretPos == currentTextGloss.length() - 1);
	}

	public int getNumWords(String gloss) {
		return (new StringTokenizer(gloss, " ")).countTokens();
	}

	public int getNumChars(String gloss) {
		return gloss.length();
	}

	public void printTarget() {
		System.out.println("VOCASimUser: current target button: "
				+ targetButton);
	}

}
