package parameterSelectionWidgetsAndControllers;

import java.awt.event.ActionListener;

import SoftwareDeployment.InvocationParameter;

public interface ParameterSelectionController {

	public abstract void addActionListener(ActionListener l);
	
	public abstract InvocationParameter getAssociatedInvocationParameter();

	public abstract String getAssociatedValue();
	
	public abstract void setAssociatedValue(String string);


}
