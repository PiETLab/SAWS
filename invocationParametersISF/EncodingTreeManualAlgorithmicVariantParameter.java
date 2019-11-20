package invocationParametersISF;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterPrefixes;
import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard;

/**
 * This class represents the invocation parameter that controls the layout of
 * the on-screen keyboard advancement is to be used.
 * 
 * @author mb
 * 
 */
public class EncodingTreeManualAlgorithmicVariantParameter extends
		InvocationParameter {

	private String PARAMETER_PREFIX = InvocationParameterPrefixes.EncodingTreeManualAlgorithmicVariantParameter;

	public static final String MANUAL = "manual";
	public static final String ALGORITHMIC = "alg";
	public static final String FILE_CACHE = "file";

	private String[] allOps = { MANUAL, ALGORITHMIC, FILE_CACHE };// V_A3, V_A4
																	// };

	private String DEFAULT = MANUAL;

	@Override
	public String getVerboseExplaination() {
		try {
			for (String s : allOps) {
				// Method method = Class.forName(PACKAGE_NAME + "." + s)
				// .getMethod(METHOD_NAME, (Class[]) null);
				// System.out.println(method);
				System.out.println(s);// + ": "
				// + method.invoke(null, (Class[]) null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.getArgumentPrefix() + getParameterPrefix() + " "
				+ super.optionify(allOps) + "Default is \"" + DEFAULT + "\".";
	}

	@Override
	public String getParameterPrefix() {
		return PARAMETER_PREFIX;
	}

	@Override
	public String getDefaultAssociatedValue() {
		return DEFAULT;
	}

	@Override
	public void setAssociatedValue(String s) {
		super.associatedValue = s;
	}

}
