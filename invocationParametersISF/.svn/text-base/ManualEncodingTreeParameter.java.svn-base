package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

public class ManualEncodingTreeParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.ManualEncodingTreeParameter;

	// private final String PACKAGE_NAME = "manualEncodingTrees";
	private final String PACKAGE_NAME = "encodingTrees";
	private final String SUPERCLASS_NAME = "ManualEncodingTreeSpecification";

	private String DEFAULT = "UnigramBasedET_2Level";

	@Override
	public String getDefaultAssociatedValue() {
		return PACKAGE_NAME + "." + DEFAULT;
	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " "
				+ super.optionify(super.findAllSubclasses(PACKAGE_NAME,
						SUPERCLASS_NAME)) + " Default is \"" + DEFAULT + "\".";
	}

}
