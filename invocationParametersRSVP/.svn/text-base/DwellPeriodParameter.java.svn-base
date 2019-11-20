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
public class DwellPeriodParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.DwellPeriodParameter;

	// static int DEFAULT_DWELL_TIME = 750;
	// static int DEFAULT_DWELL_TIME = 1250;
	private int DEFAULT_DWELL_TIME = 750;

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix()
				+ " X, where X is dwell time (in milliseconds).  Default is "
				+ DEFAULT_DWELL_TIME + " msec.";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT_DWELL_TIME;
	}

	@Override
	public String getParameterName() {
		return "Dwell Period";
	}
}
