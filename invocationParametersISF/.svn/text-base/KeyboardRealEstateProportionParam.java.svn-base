package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

public class KeyboardRealEstateProportionParam extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.KeyboardRealEstateProportionParam;

	private double DEFAULT_REAL_ESTATE_PROPORTION = 0.50;

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT_REAL_ESTATE_PROPORTION;
	}

	@Override
	public String getParameterName() {
		return "Proportion of screen real estate (vertically) for on-screen keyboard";
	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " X, where X is the proportion ([0,1]) of the vertical dimension of the screen that is to be allocated for the on-screen keyboard proportion.  Default is "
				+ DEFAULT_REAL_ESTATE_PROPORTION + " msec.";
	}

}
