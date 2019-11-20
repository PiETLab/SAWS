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
public class UseSimulatedUserParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.UseSimulatedUserParameter;

	//private String PARAMETER_PREFIX = "su";

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " indicates that a simulated user is to be used. [presently broken]";
		// output
		// .println("argument 6: name of file containing test glosses to be
		// entered (only needed if argument 2 is true");

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "false";
	}
	
}
