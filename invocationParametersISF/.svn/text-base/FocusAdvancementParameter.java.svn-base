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
public class FocusAdvancementParameter extends InvocationParameter {

	public static final String PASSIVE = "passive";
	public static final String ACTIVE = "active";
	public static final String DIRECT = "direct";

	//private String PARAMETER_PREFIX = "fa";
	private String PARAMETER_PREFIX = InvocationParameterPrefixes.FocusAdvancementParameter;

	private String DEFAULT = PASSIVE;

	// private boolean DEFAULT = true;

	private String[] values = { PASSIVE, ACTIVE, DIRECT };

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix() + " "
				+ super.optionify(values)
				+ ", the type of focus advancement to be used. "
				+ "Default is " + DEFAULT + ".";
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
