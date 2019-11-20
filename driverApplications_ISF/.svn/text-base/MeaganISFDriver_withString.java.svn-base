package driverApplications_ISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;

import SoftwareDeployment.UserSpecificModel;

public class MeaganISFDriver_withString {

	public static void main(String[] args) {
		final String ISF_PARAMETER_FILENAME = "/Users/mb/Documents/workspace/Prj-Meagan/Parameters/isf-parameters.txt";

		UserSpecificModel um;
		um = new IndirectSelectionFacilityUserModel(
				"-eh false -kb MeaganKeyboardLayout2 -sl MeaganCompositionSet -fa passive -dt 500 -te et_kb -ec HuffmanEqualCosts",
				false);

		um = new IndirectSelectionFacilityUserModel(
				"-eh false -kb MeaganKeyboardLayout2 -sl MemoFiller.MeaganAddressBookSet -fa passive -dt 500 -te et_kb -ec HuffmanEqualCosts -ea 6",
				false);

		IndirectSelectionFacilityLauncher isfLauncher = new IndirectSelectionFacilityLauncher(
				(IndirectSelectionFacilityInvocationParameterModel) um
						.getInvocationParameters());

		javax.swing.SwingUtilities.invokeLater(isfLauncher);

		while (isfLauncher.getController() == null) {
			// wait until the event dispatching thread has dealt with the
			// previous method invocation
		}

		final IndirectSelectionFacilityController isfController = isfLauncher
				.getController();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				isfController.launchPassiveFocusAdvancement();
			}
		});
	}

}