package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;


//import keyboardLayouts.KeyboardLayout;

//import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard;

/**
 * This class represents the invocation parameter that controls the layout of
 * the on-screen keyboard advancement is to be used.
 * 
 * @author mb
 * 
 */
public class SourceSymbolSetParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.SourceSymbolSetParameter;
	//private String PARAMETER_PREFIX = "sl";


	private final String PACKAGE_NAME = "sourceSymbolSet";
	private final String METHOD_NAME = "getDescriptor";
	private final String SUPERCLASS_NAME = "SourceSymbolSet";

	private String DEFAULT = "Venkatagiri99Set";// SourceSymbolSet_Venkatagiri99_Hypothesized";

	// // modQWERTY

	@Override
	public String getVerboseExplaination() {
		// Class<?>[] sig = null;// { Class.forName(PACKAGE_NAME + "." +
		// // SUPERCLASS_NAME) };
		// Object[] args = null;// { new VenkatagiriKeyboardLayout_A1() };
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
		if (s.indexOf(".") == -1) {
			super.associatedValue = PACKAGE_NAME + "." + s;
		} else {
			super.associatedValue = s;
		}

	}

}
