package invocationParametersRSVP;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * @author mb
 * 
 */
public class DisplayColourBackgroundPausedParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.DisplayColourBackgroundPausedParameter;//"kb";

	//private String PARAMETER_PREFIX = "bg";

	private String DEFAULT_COLOUR = "pink";

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix()
				+ " X, where X is the colour of the background.  "
				+ "Default is " + DEFAULT_COLOUR + "";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT_COLOUR;
	}

	@Override
	public String getParameterName() {
		return "Display Background Colour";
	}
}
