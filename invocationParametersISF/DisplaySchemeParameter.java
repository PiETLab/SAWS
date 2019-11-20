package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

public class DisplaySchemeParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.DisplayerVariantParameter;

	private final String PACKAGE_NAME = "displayScheme";
	private final String SUPERCLASS_NAME = "DisplaySchemeForTraversableEncodingTreeStates";

	private String DEFAULT = "ShowOneByOneWholeTraversal"; // modQWERTY

	// return
	// "IndirectSelectionFacility.ShowKeyboardWithAsFewElementsAsPossible";

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
				+ " Characterizes how the keyboard display works (displayer for focus) "
				+ super.optionify(super.findAllSubclasses(PACKAGE_NAME,
						SUPERCLASS_NAME)) + " Default is \"" + DEFAULT + "\".";
	}

}
