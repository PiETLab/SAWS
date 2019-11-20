package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * @author mb
 * 
 */
public class xPassiveActiveFocusAdvancementParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = "fa";

	private boolean DEFAULT = true;

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ "X, where X is true/false value representing whether passive focus advancement should be used.  "
				+ "  Default is "
				+ DEFAULT
				+ ".  This parameter only makes sense if focus advancement is true";
	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT;
	}

	@Override
	public String getParameterName() {
		return "Use Focus Advancement";
	}

}
