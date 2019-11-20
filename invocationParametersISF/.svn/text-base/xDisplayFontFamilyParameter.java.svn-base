package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * RESTRICTION: font family must be expressible as a single word (so "serif"
 * works, but not "Academy Letter")
 * 
 * @author mb
 * 
 */
public class xDisplayFontFamilyParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = "ff";

	private String DEFAULT_FAMILY = "SansSerif";
	//private String DEFAULT_FAMILY = "SansSerif";

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix()
				+ " X, where X is the name of the font family.  "
				+ "Default is " + DEFAULT_FAMILY + "";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT_FAMILY;
	}

}
