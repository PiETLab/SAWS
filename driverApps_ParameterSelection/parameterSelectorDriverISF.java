package driverApps_ParameterSelection;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersRSVP.RSVPUserModel;
import parameterConfiguration.*;
import RSVP.TwoLevelTokenizer;
import SoftwareDeployment.InvocationParameterModel;
import SoftwareDeployment.UserSpecificModel;

//-mx1024M
public class parameterSelectorDriverISF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	private static void createAndShowGUI() {
		System.out.println("Initializing. ");

		IndirectSelectionFacilityInvocationParameterModel paramModel = new IndirectSelectionFacilityInvocationParameterModel();

		ParameterSelectorController controller;
		ParameterSelectorView frame = null;
		frame = new ParameterSelectorView();
		controller = new ParameterSelectorController(frame, paramModel);
		frame.installController(controller);
	}

}
