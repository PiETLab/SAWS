package basicGuiParameters;

import javax.swing.SwingConstants;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

public class HorizontalAlignmentParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.HorizontalAlignmentParameter;//"ha";

	private String DEFAULT = "" + SwingConstants.CENTER;

	// // but.setHorizontalAlignment(SwingConstants.LEFT);
	// but.setHorizontalAlignment(SwingConstants.CENTER);

	@Override
	public String getVerboseExplaination() {
		// Class<?>[] sig = { Class.forName("keyboardLayouts.KeyboardLayout") };
		// Object[] args = { new VenkatagiriKeyboardLayout_A1() };
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " X, where X is int representing horiz alignment (LEFT, CENTER, RIGHT = 2, 0, 4 resp'y).  Default is "
				+ DEFAULT + "";

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
