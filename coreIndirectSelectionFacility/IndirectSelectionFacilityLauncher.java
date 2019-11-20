package coreIndirectSelectionFacility;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import javax.swing.*;

import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

import IndirectSelectionFacility.OnScreenKeyboardView;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.logging.Logger;

/**
 * 
 * This class implements a runnable that can be used as the parameter to the
 * javax.swing.SwingUtilities.invokeLater(Runnable) method.
 * 
 * 
 * @author mb
 * 
 */
public class IndirectSelectionFacilityLauncher implements Runnable {

	static IndirectSelectionFacilityView view;
	public IndirectSelectionFacilityController isfController;
	private IndirectSelectionFacilityInvocationParameterModel paramModel;
	private final static boolean IS_VERBOSE = true;

	public IndirectSelectionFacilityLauncher(
			IndirectSelectionFacilityInvocationParameterModel defaultISFParameters) {
		this.paramModel = defaultISFParameters;
	}

	public void run() {
		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ ": Running the launcher: " + this.getClass().getName());
		}
		// logger.info(ExperimentHelper.KEYBOARD_KEYWORD + ":"
		// + paramModel.getKeyboardVariant());
		if (paramModel.isRemoveButtonDecorators()) {
			IndirectSelectionFacilityView.removeButtonDecorators();
		}
		// instantiation of the encodingTree entails instantiations of JButtons,
		// since the source symbols are also Jbuttons
		TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();
		TraversableEncodingTree encodingTree = tetGen
				.generateEncodingTree(paramModel);
		OnScreenKeyboardView onScreenKeyboard = IndirectSelectionFacilityController
				.generateOnScreenKeyboardView(paramModel, encodingTree);
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		view = new IndirectSelectionFacilityView(paramModel, onScreenKeyboard,
				device);

		view.configureForDisplay(paramModel.isFullScreen());

		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ ": Is Full screen?: " + paramModel.isFullScreen());
		}

		// this needs to happen after the view is displayed
		isfController = new IndirectSelectionFacilityController(view,
				paramModel, encodingTree);

	}

	public IndirectSelectionFacilityController getController() {
		return isfController;
	}

	public IndirectSelectionFacilityView getView() {
		return view;
	}
}