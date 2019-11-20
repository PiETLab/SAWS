package invocationParametersISF;

import SoftwareDeployment.InvocationParameterFile;
import SoftwareDeployment.InvocationParameterModel;
import SoftwareDeployment.UserSpecificModel;

public class IndirectSelectionFacilityUserModel extends UserSpecificModel {

	public IndirectSelectionFacilityUserModel(String isfParameterFileName) {
		super(isfParameterFileName, true);
	}

	public IndirectSelectionFacilityUserModel(String parameterString,
			boolean passedStringIsFileName) {
		super(parameterString, false);
	}

	public IndirectSelectionFacilityUserModel() {
		super("", false);
	}

	public InvocationParameterModel getInvocationParameters() {

		System.out.println(this.getClass().getName() + ": Verbose report.");
		System.out.print("Reading invocation parameters from... ");

		if (super.getFileName() == null) {
			System.out.println("string: " + super.getParameterString());
			return new IndirectSelectionFacilityInvocationParameterModel(super
					.getParameterString());
		} else {
			System.out.println("file that is located at: " + super.getFileName());
			InvocationParameterFile f = new InvocationParameterFile(super
					.getFileName());
			return new IndirectSelectionFacilityInvocationParameterModel(f);
		}
	}

}
