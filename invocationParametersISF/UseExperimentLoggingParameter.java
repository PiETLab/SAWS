package invocationParametersISF;

import javax.swing.JPanel;

import parameterSelectionWidgetsAndControllers.BooleanSelectionController;
import parameterSelectionWidgetsAndControllers.BooleanSelectionView;
import parameterSelectionWidgetsAndControllers.ParameterSelectionController;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

/**
 * This class represents the invocation parameter that controls whether
 * experiment logging is to be used. This parameter must be used with the
 * appropriate ActionScheme (namely, one that further specifies how and when the
 * logging is started and stopped).
 * 
 * This parameter will be made obsolete once the action scheme parameter is
 * implemented more thoroughly
 * 
 * @author mb
 * 
 */
public class UseExperimentLoggingParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.UseExperimentLoggingParameter;

	private boolean DEFAULT = false;
	
	private BooleanSelectionView view;
	private BooleanSelectionController controller;
	
	public UseExperimentLoggingParameter() {
		view = new BooleanSelectionView();
		controller = new BooleanSelectionController(view, this);
	}



	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix()
				+ " {true|false}, whether the ExperimentHelper is to be used."
				+ "  Default is " + DEFAULT + ".";
	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT;
	}
	
	public JPanel getGUIWidget() {
		return view;
	}

	public ParameterSelectionController getGUIController() {
		return controller;
	}


}
