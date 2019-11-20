package coreMemoApplication;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;

/**
 * This class represents the invocation parameter that controls whether focus
 * advancement is to be used.
 * 
 * RESTRICTION: font family must be expressible as a single word (so "serif"
 * works, but not "Academy Letter")
 * 
 * @author mb
 * 
 */
public class PDFLocationParameter extends InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.PDFLocationParameter;
	
	private String DEFAULT_LOCATION = "/";

	@Override
	public String getVerboseExplaination() {
		return super.getArgumentPrefix() + getParameterPrefix()
				+ " X, where X is the file location of the address book.  "
				+ "Default is " + DEFAULT_LOCATION + "";

	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return "" + DEFAULT_LOCATION;
	}

	@Override
	public String getParameterName() {
		return "Address Book Location";
	}
}
