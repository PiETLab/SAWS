package actionScheme;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

public abstract class ActionSchemeUsingTwoInputDevices extends ActionScheme {

	public ActionSchemeUsingTwoInputDevices(
			IndirectSelectionFacilityInvocationParameterModel paramManager,
			IndirectSelectionFacilityController isfController) {
		super(paramManager, isfController);
	}

	public abstract ActionEvent interpretKeyPress(KeyEvent ke,
			boolean isFocusAtRoot);

	public abstract ActionEvent interpretKeyRelease(KeyEvent ke);

}
