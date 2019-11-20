package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * @author mb
 * 
 */
public class xKeyboardParameter extends InvocationParameter {

	public static final String PASSIVE = "passive";
	public static final String ACTIVE = "active";
	public static final String NONE = "none";

	private String PARAMETER_PREFIX = "fa";

	private String DEFAULT = ACTIVE;

	// private boolean DEFAULT = true;

	private String[] values = { PASSIVE, ACTIVE, NONE };

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ "X, where X is one of passive/active/none and represents the type of focus advancement that should be used.  "
				+ "  Default is " + DEFAULT + ".";
	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return DEFAULT;
	}

	@Override
	public String getParameterName() {
		return "Focus Advancement Mode";
	}

}
