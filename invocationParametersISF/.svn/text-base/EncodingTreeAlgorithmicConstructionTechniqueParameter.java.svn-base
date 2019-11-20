package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

/**
 * This class represents the invocation parameter that controls the layout of
 * the on-screen keyboard advancement is to be used.
 * 
 * @author mb
 * 
 */
public class EncodingTreeAlgorithmicConstructionTechniqueParameter extends
		InvocationParameter {

	// private String PARAMETER_PREFIX = "ec";
	private String PARAMETER_PREFIX = InvocationParameterPrefixes.EncodingTreeAlgorithmicConstructionTechniqueParameter;

	private final String PACKAGE_NAME = "encodingTrees";
	private final String METHOD_NAME = "getDescriptor";
	private final String SUPERCLASS_NAME = "AbstractEncodingTree";
	private final String DEFAULT = "RowColFromKB";

	@Override
	public String getVerboseExplaination() {
		// Class<?>[] sig = { Class.forName("keyboardLayouts.KeyboardLayout") };
		// Object[] args = { new VenkatagiriKeyboardLayout_A1() };
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " "
				+ super.optionify(super.findAllSubclasses(PACKAGE_NAME,
						SUPERCLASS_NAME)) + " Default is \"" + DEFAULT + "\".";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return PACKAGE_NAME + "." + DEFAULT;
	}

	@Override
	public void setAssociatedValue(String s) {
		// super.associatedValue = PACKAGE_NAME + "." + s;
		super.associatedValue = s;
	}

}
