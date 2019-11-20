package actionScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import experimentHelper.ExperimentHelper;

import IndirectSelectionFacilityCommands.ExperimentHelperStartCommand;
import IndirectSelectionFacilityCommands.StartLoggerAndLaunchFocusAdvancementCommand;

public abstract class ActionSchemeUsingOneInputDevice extends ActionScheme {

	protected final int ET_SELECT = KeyEvent.VK_SPACE;
	protected final int ET_ASCEND = KeyEvent.VK_UP;
	protected final int TOGGLE_FA = KeyEvent.VK_P;

	public ActionSchemeUsingOneInputDevice(
			IndirectSelectionFacilityInvocationParameterModel paramManager,
			IndirectSelectionFacilityController isfController) {
		super(paramManager, isfController);
	}

	public abstract ActionEvent interpretKeyPress(KeyEvent ke,
			boolean isFocusAtRoot);

	public abstract ActionEvent interpretKeyRelease(KeyEvent ke);

	// public ActionEvent interpretKeyPress2(KeyEvent ke) {
	// ActionEvent ae3 = null;
	// if (ke.getKeyChar() == ExperimentHelper.getStartKey()
	// && ExperimentHelper.metaKeyEnabled()) {
	// // if (IS_VERBOSE)
	// // System.out.println("Starting experiment helper.");
	// ExperimentHelper.setMetaKeyPressed(true);
	// ExperimentHelper.setMetaKeyEnabled(false);
	// // ae3 = new ActionEvent(new ExperimentHelperStartCommand(
	// // getParamManager().getUserId()), 0,
	// // "ExperimentHelperStartCommand");
	// ae3 = new ActionEvent(
	// new StartLoggerAndLaunchFocusAdvancementCommand(
	// getParamManager().getUserId()), 0,
	// "StartLoggerCommand");
	// }
	// return ae3;
	// }

	public boolean shouldAutomaticallyReinitializeFokusAfterCompleteTraversal() {
		return true;
	}
}
