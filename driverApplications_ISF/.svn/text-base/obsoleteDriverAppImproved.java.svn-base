package driverApplications_ISF;


import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import javax.swing.*;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreIndirectSelectionFacility.IndirectSelectionFacilityView;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import IndirectSelectionFacility.TextEntrySystemFrame;
import IndirectSelectionFacility.TextEntrySystemFrameController;
import IndirectSelectionFacilityCommands.EncodingTreePresentInitialView;

import simulatedUser.VOCASimUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * This application instantiates an on-screen facility that allows a user to
 * perform indirect text entry, with support for: data logging (for subsequent
 * analysis); choice from among a number of multiple keyboards and scanning
 * patterns (see the XXXX class). Invoke with -h for a listing of all available
 * parameters.
 * 
 * <p>
 * The app consists of a text composition facility (TCF) that has an on-screen
 * keyboard. The on-screen keyboard has passive focus advancement. When the
 * target key is highlighted, the user presses the button. The passive focus
 * advancement thread(*) highlights the buttons on the keyboard in a pattern
 * dictated by a particular containment hierarchy. The name of the particular
 * variant is presently hard-coded within the app (for ease-of-switching during
 * this development phase).
 * 
 * <p>
 * This app can be invoked with a simulated user. The simulated user monitors
 * which buttons are highlighted and when the one gains focus, the user selects
 * it (the simulated user is actïually implemented as a separate thread and it
 * places a action event on the event-dispatching thread). THIS FEATURE
 * PRESENTLY BROKEN.
 * 
 * <p>
 * Invoke this application with the following command line arguments:
 * <ol>
 * <li>argument 1: dwell time (in milliseconds)
 * <li>argument 2: true/false value representing whether passive focus
 * advancement should be used.
 * <li>argument 3: true/false value representing whether simulated user should
 * be used. PRESENTLY BROKEN --- use value "false"
 * <li>argument 4: name of file containing test glosses to be entered (only
 * needed if argument 2 is true
 * </ol>
 * 
 * <p>
 * The app single-threaded, but behaves as a multiple-threaded application.
 * (Multi-threaded GUIs are notoriously difficult to design, implement and
 * debug, so instead I have implemented Runnables that place events on the
 * event-dispatching thread.) **Actually, this is a quasi-lie. The simulated
 * user is implemented as a separate thread, but the creation of the thread
 * occurs in the event-dispatching thread (as opposed to the main program
 * thread).
 * 
 * @author M. Baljko, 2004
 * @version 1.0
 * 
 */

public class obsoleteDriverAppImproved {

	static IndirectSelectionFacilityView view;
	static IndirectSelectionFacilityController controller;

	private static Logger logger = Logger.getLogger("voca.vocaApp");
	private static IndirectSelectionFacilityInvocationParameterModel invHelper;

	public static void main(String[] args) throws InterruptedException,
			IOException {

		// String args2 = "-eh false -kb VenkatagiriKeyboardLayout_A2A5 -fa
		// passive -t kb_et -ec RowColFromKB";
		// String args2 = "-eh false -kb VenkatagiriKeyboardLayout_A1 -fa
		// passive -t kb_et -ec LinearFromKB";
		// String args2 = "-eh false -kb TamKeyboardLayout -fa passive -te et_kb
		// -ec HuffmanEqualCosts -ea 2";
		// String args2 = "-eh false -kb VenkatagiriKeyboardLayout_A1 -fa
		// passive -te et_kb -ec HuffmanEqualCosts -ea 2";
		String args2 = "-eh false -kb RamayKeyboardLayoutParam -fa passive -te et_kb "
				+ "-ec HuffmanEqualCosts -sl SourceSymbolSet_Venkatagiri99_Hypothesized -ea 2";
		StringTokenizer tok = new StringTokenizer(args2);
		String[] args3 = new String[tok.countTokens()];
		List<String> l = new Vector<String>();
		while (tok.hasMoreTokens()) {
			l.add(tok.nextToken());
		}
		args3 = l.toArray(args3);

		invHelper = new IndirectSelectionFacilityInvocationParameterModel(args3);

		logger.info(ExperimentHelper.KEYBOARD_KEYWORD + ":"
				+ invHelper.getKeyboardVariant());
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI(invHelper);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					// } catch (EngineException e) {
					// e.printStackTrace();
				}
			}
		});

		final int dwellTime = invHelper.getDwellTime();
		if (invHelper.isWithPassiveFocusAdvancement()) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new PassiveFokusAdvancerSwing(controller, dwellTime);
					PassiveFokusAdvancerSwing.startEventGenerator();
				}
			});
		}

	}

	private static void createAndShowGUI(IndirectSelectionFacilityInvocationParameterModel paramManager)
			throws IllegalArgumentException {
		try {
			view = new IndirectSelectionFacilityView(paramManager);
			view.pack();
			view.setVisible(true);

			// view = new TextEntrySystemFrame(paramManager);
			controller = new IndirectSelectionFacilityController(view,
					paramManager);
			view.PLACE_TO_PARK_FOCUS.addKeyListener(controller);
			ActionEvent ae4 = new ActionEvent(
					new EncodingTreePresentInitialView(), 0, "");
			((ActionListener) controller).actionPerformed(ae4);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		// view.setTitle("Text Composition Frame");
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// view.pack();
		// view.setVisible(true);
		view.requestFocusInWindow();
		view.PLACE_TO_PARK_FOCUS.requestFocus();
	}
}