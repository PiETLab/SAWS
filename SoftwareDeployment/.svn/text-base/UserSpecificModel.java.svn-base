package SoftwareDeployment;

import java.io.File;

public abstract class UserSpecificModel {

	private String fileName;
	private String parameterString;

	protected UserSpecificModel(String s, boolean passedStringIsFileName) {
		if (passedStringIsFileName) {
			fileName = s;
		} else {
			parameterString = s;
		}
	}

	public void writeParameterFile(InvocationParameterModel params,
			String fileName) {
		boolean isOverwriteMode = true;
		System.out.println(params.getClass());
		// if (params instanceof RSVPInvocationParameterModel) {
		InvocationParameterFile rsvp = new InvocationParameterFile(fileName,
				isOverwriteMode);
		// rsvp.println(params.generateVerboseExplaination());
		rsvp.println(params.generateParameterString());
		System.out.println("Written to parameter file:\n" + fileName + "\n"
				+ params.generateParameterString());
		// }
	}

	public abstract InvocationParameterModel getInvocationParameters();

	protected String getFileName() {
		return fileName;
	}

	protected String getParameterString() {
		return parameterString;
	}

	public static String getDefaultDirectoryForParameterFiles() {
		String HOME_DIR = System.getProperty("user.home");
		String USER_RELATIVE_DIR = File.separator + "Dropbox" + File.separator
				+ "Library" + File.separator + "Preferences" + File.separator
				+ "AT-Applications" + File.separator;
		// String USER_RELATIVE_DIR = File.separator + "Library" +
		// File.separator
		// + "Preferences" + File.separator + "AT-Applications"
		// + File.separator;
		return HOME_DIR + USER_RELATIVE_DIR;
	}

}
