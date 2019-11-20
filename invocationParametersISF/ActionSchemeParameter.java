package invocationParametersISF;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

public class ActionSchemeParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.ActionSchemeParameter;

	private final String PACKAGE_NAME = "actionScheme";
	private final String SUPERCLASS_NAME = "ActionScheme";

	private String DEFAULT = "PassiveFocusAdvancementActionScheme_TrialMode2"; // modQWERTY

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
				+ " Characterizes the action scheme (mapping of user actions to application commands. "
				+ super.optionify(super.findAllSubclasses(PACKAGE_NAME,
						SUPERCLASS_NAME)) + " Default is \"" + DEFAULT + "\".";
	}

}
