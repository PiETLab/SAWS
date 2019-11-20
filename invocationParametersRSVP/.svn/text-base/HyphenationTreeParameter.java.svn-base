package invocationParametersRSVP;

import java.io.File;

import PredictionModellingISF.UserModel;
import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;
import SoftwareDeployment.UserSpecificModel;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * @author mb
 * 
 */
public class HyphenationTreeParameter extends InvocationParameter {

	private String HYPHENATION_TREE_FILE = UserSpecificModel
			.getDefaultDirectoryForParameterFiles()
			+ File.separator + "en_US.xml";// "/Users/mb/Documents/workspace/Prj-RSVP/en_US.xml";

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.HyphenationTreeParameter;

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix()
				+ getParameterPrefix()
				+ " X, where X is the file of the hyphenation tree.  Default is "
				+ HYPHENATION_TREE_FILE + "";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + HYPHENATION_TREE_FILE;
	}

	@Override
	public String getParameterName() {
		return "Hyphenation Tree";
	}
}
