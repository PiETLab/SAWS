package basicGuiParameters;

import javax.swing.JPanel;

import parameterSelectionWidgetsAndControllers.ColourSelectionController;
import parameterSelectionWidgetsAndControllers.ColourSelectionView;
import parameterSelectionWidgetsAndControllers.ParameterSelectionController;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * @author mb
 * 
 */
public class TextColourParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.TextColourParameter;

	private String DEFAULT_COLOUR = "black";

	private ColourSelectionView fp;
	private ColourSelectionController fc;

	public TextColourParameter() {
		// Dimension availableRealEstate = Toolkit.getDefaultToolkit()
		// .getScreenSize();
		fp = new ColourSelectionView();// new
		// Dimension(availableRealEstate.width,availableRealEstate.height
		// / 12));
		fc = new ColourSelectionController(fp, this);
	}

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix()
				+ " X, where X is the colour of the text.  " + "Default is "
				+ DEFAULT_COLOUR + "";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT_COLOUR;
	}

	// @Override
	// public String getParameterName() {
	// return "Display Text Colour";
	// }
	
	public JPanel getGUIWidget() {
		return fp;

	}

	public ParameterSelectionController getGUIController() {
		return fc;
	}

}
