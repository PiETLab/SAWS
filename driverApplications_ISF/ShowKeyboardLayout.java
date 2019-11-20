package driverApplications_ISF;

import indirectTextEntrySystemVariants.LinearUnigram;
import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import javax.swing.*;

import experimentHelper.ExperimentHelper;

import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import IndirectSelectionFacility.TextEntrySystemFrame;
import IndirectSelectionFacility.TextEntrySystemFrameController;

import simulatedUser.VOCASimUser;

import java.io.*;
import java.util.logging.Logger;

/**
 * 
 * @author M. Baljko, 2004
 * @version 1.0
 * 
 */

public class ShowKeyboardLayout {

	static TextEntrySystemFrame view;
	static TextEntrySystemFrameController controller;

	private static Logger logger = Logger.getLogger("voca.vocaApp");

	public static void main(String[] args) throws InterruptedException,
			IOException {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					// } catch (EngineException e) {
					// e.printStackTrace();
				}
			}
		});

	}

	private static void createAndShowGUI() throws IllegalArgumentException {
		// private static void createAndShowGUI() throws
		// IllegalArgumentException,
		// EngineException {
		// Make sure we have nice window decorations.
		// JFrame.setDefaultLookAndFeelDecorated(true);
		
		String[] args = {""};
		IndirectSelectionFacilityInvocationParameterModel paramManager = new IndirectSelectionFacilityInvocationParameterModel(args);
		
		TextEntrySystemFrame view = null;
		try {
			view = new TextEntrySystemFrame(paramManager);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			/*
			 * } catch (EngineException e) { e.printStackTrace(); } catch
			 * (AudioException e) { Auto-generated catch block
			 * e.printStackTrace(); } catch (EngineStateError e) {
			 * e.printStackTrace();
			 */
		}

		view.setTitle("Keyboard");
		// frame.requestFocusInWindow();
		// frame.requestFocus();
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window.
		view.pack();
		view.setVisible(true);
		view.requestFocusInWindow();
		view.assignFocus();
	}
}
