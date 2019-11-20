package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

public class TitleParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.TitleParameter;

	//private String PARAMETER_PREFIX = "tt";

	private String DEFAULT = "default";

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " X, where X is a string that will appear at the top of the window. "
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
