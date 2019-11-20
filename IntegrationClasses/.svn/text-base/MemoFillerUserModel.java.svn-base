package IntegrationClasses;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import coreMemoApplication.MemoFillerInvocationParameterModel;
import SoftwareDeployment.UserSpecificModel;
import SoftwareDeployment.InvocationParameterFile;
import SoftwareDeployment.InvocationParameterModel;

//import memoFiller.MemoFillerInvocationParameterModel;

public class MemoFillerUserModel extends UserSpecificModel {

	public MemoFillerUserModel(String paramFileName) {
		super(paramFileName, true);
	}

	public MemoFillerUserModel() {
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
			return new MemoFillerInvocationParameterModel(super
					.getParameterString());
		} else {
			System.out.println("file: " + super.getFileName());
			InvocationParameterFile f = new InvocationParameterFile(super
					.getFileName());
			return new MemoFillerInvocationParameterModel(f);
		}

	}

	// public static IndirectSelectionFacilityInvocationParameterModel
	// getISFParameters() {
	// // String isfArgs =
	// // "-eh false -kb MeaganKeyboardLayout -fa passive -dt 1000 -te kb_et "
	// // + "-ec RowColFromKB";
	// InvocationParameterFile f = new InvocationParameterFile(
	// ISF_PARAMETER_FILENAME);
	// return new IndirectSelectionFacilityInvocationParameterModel(f);
	// }

	// public static String getParameterFileRSVP() {
	// return RSVP_PARAMETER_FILENAME;
	// }
	//
	// public static String getParameterFileISF() {
	// return ISF_PARAMETER_FILENAME;
	// }

	public void writeParameterFile(InvocationParameterModel params) {
		// System.out.println(params.getClass());
		// if (params instanceof RSVPInvocationParameterModel) {
		// boolean isOverwriteMode = true;
		// InvocationParameterFile rsvp = new InvocationParameterFile(
		// RSVP_PARAMETER_FILENAME, isOverwriteMode);
		// // rsvp.println(params.generateVerboseExplaination());
		// rsvp.println(params.generateParameterString());
		// System.out.println("Written to parameter file:\n"
		// + params.generateParameterString());
		// }
	}
}
