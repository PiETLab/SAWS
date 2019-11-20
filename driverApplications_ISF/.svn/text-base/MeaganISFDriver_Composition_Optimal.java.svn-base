package driverApplications_ISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;
import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;

import SoftwareDeployment.UserSpecificModel;

public class MeaganISFDriver_Composition_Optimal {

	public static void main(String[] args) {

		// final String PARAM_FILE = "isf-parameters-compose-for-memo.txt";
		final String PARAM_FILE = "isf-parameters-compose-huffman.txt";
		final String ISF_PARAMETER_FILENAME = UserSpecificModel
				.getDefaultDirectoryForParameterFiles()
				+ PARAM_FILE;

		UserSpecificModel um = new IndirectSelectionFacilityUserModel(
				ISF_PARAMETER_FILENAME);

		IndirectSelectionFacilityLauncher isfLauncher = new IndirectSelectionFacilityLauncher(
				(IndirectSelectionFacilityInvocationParameterModel) um
						.getInvocationParameters());

		javax.swing.SwingUtilities.invokeLater(isfLauncher);

		while (isfLauncher.getController() == null) {
			// wait until the event dispatching thread has dealt with the
			// previous method invocation
		}
		isfLauncher.getController().initToStartOfFokusCycleAndLaunch();
		// System.out.println(isfLauncher.getController().getCode());

		//
		// final IndirectSelectionFacilityController isfController = isfLauncher
		// .getController();
		// javax.swing.SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		// isfController.launchPassiveFocusAdvancement();
		// }
		// });
	}

}