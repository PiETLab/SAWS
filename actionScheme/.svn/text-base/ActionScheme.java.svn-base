package actionScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import experimentHelper.ExperimentHelper;

import IndirectSelectionFacilityCommands.ExperimentHelperStartCommand;
import IndirectSelectionFacilityCommands.StartLoggerAndLaunchFocusAdvancementCommand;

public abstract class ActionScheme {

	private IndirectSelectionFacilityInvocationParameterModel paramManager;
	protected IndirectSelectionFacilityController isfController;

	public ActionScheme(
			IndirectSelectionFacilityInvocationParameterModel paramManager,
			IndirectSelectionFacilityController isfController) {
		this.paramManager = paramManager;
		this.isfController = isfController;
	}

	public IndirectSelectionFacilityInvocationParameterModel getParamManager() {
		return paramManager;
	}

	public abstract ActionEvent interpretKeyPress(KeyEvent ke,
			boolean isFocusAtRoot);

	public abstract ActionEvent interpretKeyRelease(KeyEvent ke);

	public abstract boolean shouldAutomaticallyReinitializeFokusAfterCompleteTraversal();

	public boolean shouldAutomaticallyReinitializeGlossAfterCompleteTraversal() {
		return false;
	};

}
