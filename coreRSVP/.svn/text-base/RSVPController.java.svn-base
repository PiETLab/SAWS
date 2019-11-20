package coreRSVP;

import invocationParametersRSVP.RSVPInvocationParameterModel;

import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.apache.fop.hyphenation.HyphenationTree;

import coreIndirectSelectionFacility.AssistiveTechnologyMouseLikeDeviceListener;

import RSVP.EBook;
import RSVP.TwoLevelTokenizer;
import RSVPStates.RSVPEndOfTextReachedState;
import RSVPStates.RSVPState;
import RSVPStates.RSVPPausedState;
import RSVPStates.RSVPUnpausedState;
import RSVPStates.RSVPWholeMultiWordDisplayState;

/**
 * 
 * This class implements a controller that has three commands: pause, unpause,
 * and back.
 * 
 * The behaviour of the commands is a
 * 
 * @author mb
 * 
 */
public class RSVPController implements ActionListener, KeyListener {

	// private static int NEXT_WORD_MODE = 0;
	// private static int TICKER_MODE = 1;
	// private static int SMOOTH_SCROLL_MODE = 2;
	// private static int HYPHENATION_MODE = 3;
	// private int mode;

	private RSVPInvocationParameterModel paramModel;

	private ActionListener actionListenerList = null;

	private TwoLevelTokenizer tok;

	private RSVPDisplayer rsvpDisplay;

	private HyphenationTree ht;

	private RSVPTimerWrapper nextWordEventGenerator;

	private Timer scrollAdvanceGenerator;

	private RSVPState currentState;

	public RSVPController(RSVPDisplayer rsvpDisplay, TwoLevelTokenizer tok,
			RSVPInvocationParameterModel paramModel) {
		this.paramModel = paramModel;
		this.rsvpDisplay = rsvpDisplay;
		this.tok = tok;
		this.ht = paramModel.getHyphenationTree();
		nextWordEventGenerator = new RSVPTimerWrapper(paramModel, this);
		scrollAdvanceGenerator = new Timer(50, this);
		currentState = null;
		rsvpDisplay.indicateInitState();

		rsvpDisplay.addKeyListener(this);

		this.putViewToFront();

		if (this.paramModel.isUsingAssistiveTechnologyMouseLikeDevice()) {
			this
					.installAssistiveTechnologyMouseLikeDeviceListener(new AssistiveTechnologyMouseLikeDeviceListener(
							this, rsvpDisplay));
		}

	}

	public void pause() {
		nextWordEventGenerator.stop();
		rsvpDisplay.indicatePausedState();
	}

	public void unpause() {
		nextWordEventGenerator.restart();
		rsvpDisplay.indicateUnpausedState();
	}

	public void back() {
		tok.reinit();
		rsvpDisplay.display(tok.nextTokenNotStrict());
	}

	public void setState(RSVPState state) {
		currentState = state;
	}

	public void setStateToUnpaused() {
		System.out.println("unpausing");
		currentState = new RSVPUnpausedState(this);
		this.actionPerformed(new ActionEvent(this, 0, currentState.getClass()
				.getName()));
	}

	public void setStateToPaused() {
		System.out.println("pausing");
		currentState = new RSVPPausedState(this);
		this.actionPerformed(new ActionEvent(this, 0, currentState.getClass()
				.getName()));
	}

	public void actionPerformed(ActionEvent e) {
		// System.out.println("RSVPController: actionPerformed: " +
		// e.getSource()
		// + " current state: " + currentState.toString());

		if (currentState == null) {
			// currentState = new RSVPWholeWordDisplayState(this);
			currentState = new RSVPWholeMultiWordDisplayState(this);
		} else {
			currentState.handleRequest();
		}

		if (actionListenerList != null) {
			actionListenerList.actionPerformed(e);
		}

		// if (mode == RSVPController.NEXT_WORD_MODE) {
		// displayNextWord();
		// } else if (mode == RSVPController.TICKER_MODE) {
		// currentWord = currentWord.substring(2, currentWord.length());
		// rsvpDisplay.display(currentWord);
		// if (rsvpDisplay.canFitOnDisplay(currentWord)) {
		// mode = RSVPController.NEXT_WORD_MODE;
		// }
		// } else if (mode == RSVPController.SMOOTH_SCROLL_MODE) {
		// rsvpDisplay.scrollViewportToRight();
		// if (rsvpDisplay.isEndOfWordInView()) {
		// mode = RSVPController.NEXT_WORD_MODE;
		// scrollAdvanceGenerator.stop();
		// nextWordEventGenerator.restart();
		// }
		// } else if (mode == RSVPController.HYPHENATION_MODE) {
		// if (currentToken.hasMoreHyphenSubstring()) {
		// currentWord = currentToken.getNextHyphenSubstring();
		// rsvpDisplay.display(currentWord);
		// } else {
		// mode = RSVPController.NEXT_WORD_MODE;
		// displayNextWord();
		// }
		// }
	}

	// private void doTickerTape(String string) {
	// rsvpController.pause();
	// tickerTape.launchTicker(this, string);
	// while (tickerTape.isRunning()) {
	// }
	// rsvpController.unpause();
	// displayText.setForeground(Color.BLACK);
	// }

	// private void displayNextWord() {
	// if (tok.hasNextToken()) {
	// currentToken = tok.nextTokenAmalgamatePunctuation();
	// currentWord = currentToken.getWord();
	// if (!rsvpDisplay.canFitOnDisplay(currentWord)) {
	// // System.out.println(currentWord);
	// mode = RSVPController.HYPHENATION_MODE;
	// // mode = RSVPController.TICKER_MODE;
	// // mode = RSVPController.SMOOTH_SCROLL_MODE;
	// } else {
	// rsvpDisplay.display(currentWord);
	// }
	//
	// if (mode == RSVPController.HYPHENATION_MODE) {
	// currentWord = currentToken.getFirstHyphenSubstring(rsvpDisplay
	// .getWidth(), rsvpDisplay.getFontMetrics(), ht);
	// rsvpDisplay.display(currentWord);
	// } else if (mode == RSVPController.SMOOTH_SCROLL_MODE) {
	// nextWordEventGenerator.stop();
	// scrollAdvanceGenerator.restart();
	// } else if (mode == RSVPController.TICKER_MODE) {
	// rsvpDisplay.display(currentWord);
	// }
	//
	// } else {
	// rsvpDisplay.display("<end>");
	// }
	// }

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			// togglePauseUnpause();
			if (nextWordEventGenerator.isRunning()) {
				setStateToPaused();
			} else {
				setStateToUnpaused();
			}
		}
		// else if (ke.getKeyCode() == KeyEvent.VK_F3) {
		//
		// }
	}

	public void togglePauseUnpause() {
		if (nextWordEventGenerator.isRunning()) {
			pause();
		} else {
			unpause();
		}

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public int getSystemEventRate() {
		return nextWordEventGenerator.getDelay();
	}

	public RSVPDisplayer getView() {
		return rsvpDisplay;
	}

	public TwoLevelTokenizer getTokenizer() {
		return tok;
	}

	public void resetToStartOfText() {
		tok.reinit();
	}

	public void putViewToFront() {
		// ((JFrame) rsvpDisplay).requestFocusInWindow();//grabFocus();
		// ((JFrame) rsvpDisplay).toFront();// grabFocus();

		((JFrame) rsvpDisplay).toFront();// grabFocus();
		rsvpDisplay.configureForDisplay(paramModel.isFullScreen());
		// rsvpDisplay.getFocusReceivingComponent().requestFocus();

	}

	public boolean isActive() {
		return ((JFrame) rsvpDisplay).isActive();// grabFocus();
	}

	public void replaceContent(String textGloss) {
		System.out.println("for rsvp display: " + textGloss);
		this.tok = new TwoLevelTokenizer(new EBook(textGloss, false));
		currentState = null;
		rsvpDisplay.indicateInitState();
	}

	public synchronized void addActionListener(ActionListener l) {
		actionListenerList = AWTEventMulticaster.add(actionListenerList, l);
	}

	public void installAssistiveTechnologyMouseLikeDeviceListener(
			MouseListener mouseListener) {
		rsvpDisplay.getGlassPane().setVisible(true);
		rsvpDisplay.getGlassPane().addMouseListener(mouseListener);
	}

	public boolean isCurrentStateEndOfTextReachedState() {
		return currentState instanceof RSVPEndOfTextReachedState;
	}

	public boolean isCurrentStatePausedState() {
		return currentState instanceof RSVPPausedState;
	}

	public int getCurrentPositionWithText() {
		return tok.getCurrentIndexPositionIntraToken();
	}

}
