package coreIndirectSelectionFacility;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;
import SoftwareDeployment.UserSpecificModel;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;

public class Driver {

	public static void launch(String args) {
		UserSpecificModel um = new IndirectSelectionFacilityUserModel(args,
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
