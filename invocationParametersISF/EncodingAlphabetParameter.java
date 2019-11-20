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
public class EncodingAlphabetParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.EncodingAlphabetParameter;


	private String DEFAULT = "2";

	@Override
	public String getVerboseExplaination() {
		// Class<?>[] sig = { Class.forName("keyboardLayouts.KeyboardLayout") };
		// Object[] args = { new VenkatagiriKeyboardLayout_A1() };
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " X, where X is the size of the encoding alphabet.  Default is "
				+ DEFAULT + " msec.";

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
