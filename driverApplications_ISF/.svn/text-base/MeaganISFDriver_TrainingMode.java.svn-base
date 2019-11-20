package driverApplications_ISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;
import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;

import SoftwareDeployment.UserSpecificModel;

public class MeaganISFDriver_TrainingMode {

	public static void main(String[] args) {
		final String PARAM_FILE = "isf-parameters-game.txt";
		
		final String ISF_PARAMETER_FILENAME = UserSpecificModel
				.getDefaultDirectoryForParameterFiles()
				+ PARAM_FILE;

		UserSpecificModel um = new IndirectSelectionFacilityUserModel(
				ISF_PARAMETER_FILENAME);

		IndirectSelectionFacilityLauncher isfLauncher = new IndirectSelectionFacilityLauncher(
				(IndirectSelectionFacilityInvocationParameterModel) um
						.getInvocationParameters());

		javax.swing.SwingUtilities.invokeLater(isfLauncher);

		// while (isfLauncher.getController() == null) {
		// // wait until the event dispatching thread has dealt with the
		// // previous method invocation
		// }

		// final IndirectSelectionFacilityController isfController = isfLauncher
		// .getController();
		// javax.swing.SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		// isfController.launchPassiveFocusAdvancement();
		// }
		// });
	}
}