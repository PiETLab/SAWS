package invocationParametersISF;

import javax.swing.JPanel;

import parameterSelectionWidgetsAndControllers.BooleanSelectionController;
import parameterSelectionWidgetsAndControllers.BooleanSelectionView;
import parameterSelectionWidgetsAndControllers.ParameterSelectionController;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

public class RemoveButtonDecoratorParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.RemoveButtonDecoratorParameter;

	//private String PARAMETER_PREFIX = "rb";
	

	private boolean DEFAULT = true;

	private BooleanSelectionView view;
	private BooleanSelectionController controller;

	public RemoveButtonDecoratorParameter() {
		view = new BooleanSelectionView();
		controller = new BooleanSelectionController(view, this);
	}

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " {true|false}, whether the button decorators shall be removed or not .  "
				+ "Default is " + DEFAULT + "";

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
