package invocationParametersRSVP;

import SoftwareDeployment.InvocationParameterFile;
import SoftwareDeployment.InvocationParameterModel;
import SoftwareDeployment.UserSpecificModel;

public class RSVPUserModel extends UserSpecificModel {

	public RSVPUserModel(String rsvpParameterFileName) {
		super(rsvpParameterFileName, true);
	}

	public RSVPUserModel(String parameterString, boolean passedStringIsFileName) {
		super(parameterString, false);
	}

	public RSVPUserModel() {
		super("", false);
	}

	public InvocationParameterModel getInvocationParameters() {

		System.out.println(this.getClass().getName() + ": Verbose report.");
		System.out.print("Reading invocation parameters from... ");

		// String isfArgs =
		// "-eh false -kb MeaganKeyboardLayout -fa passive -dt 1000 -te kb_et "
		// + "-ec RowColFromKB";
		if (super.getFileName() == null) {
			System.out.println("string: " + super.getParameterString());
			return new RSVPInvocationParameterModel(super.getParameterString());
		} else {
			System.out.println("file: " + super.getFileName());
			InvocationParameterFile f = new InvocationParameterFile(super
					.getFileName());
			return new RSVPInvocationParameterModel(f);
		}
	}

}
