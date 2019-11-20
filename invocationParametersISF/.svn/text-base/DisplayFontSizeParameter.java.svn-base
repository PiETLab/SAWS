package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * @author mb
 * 
 */
public class DisplayFontSizeParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.DisplayFontSizeParameter;

	//private String PARAMETER_PREFIX = "fs";

	private int DEFAULT_SIZE = 30;

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix()
				+ " X, where X is the size of the font.  "
				+ "Default is " + DEFAULT_SIZE + "";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT_SIZE;
	}

}
