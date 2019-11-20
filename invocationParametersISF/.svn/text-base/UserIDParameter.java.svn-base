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
public class UserIDParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.UserIDParameter;

	private String DEFAULT = "default";

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " X, where X is a string identifier for the user who is using this sw (for data collection purposes). "
				+ "Default is \"" + DEFAULT + "\".";
	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return DEFAULT;
	}
	
}

