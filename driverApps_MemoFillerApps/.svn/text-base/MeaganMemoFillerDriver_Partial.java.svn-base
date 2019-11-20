package driverApps_MemoFillerApps;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;
import invocationParametersRSVP.RSVPInvocationParameterModel;
import invocationParametersRSVP.RSVPUserModel;
import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;
import IntegrationClasses.Integrated_RSVP_ISF_Launcher;
import RSVP.RSVPLauncher;
import SoftwareDeployment.UserSpecificModel;

/**
 * 
 * Both the ISF and the RSVP must use the same 
 */
public class MeaganMemoFillerDriver_Partial {

	static RSVPLauncher rsvpLauncher;
	static IndirectSelectionFacilityLauncher isfLauncher;

	public static void main(String[] args) {

		final String RSVP_PARAMETER_FILENAME = "/Users/mb/Documents/workspace-new2/Prj-Meagan/Parameters/rsvp-parameters.txt";
		UserSpecificModel umRSVP = new RSVPUserModel(RSVP_PARAMETER_FILENAME);
		RSVPInvocationParameterModel paramsRSVP = (RSVPInvocationParameterModel) umRSVP
				.getInvocationParameters();

		final String ISF_PARAMETER_FILENAME = "/Users/mb/Documents/workspace-new2/Prj-Meagan/Parameters/isf-parameters.txt";
		UserSpecificModel um = new IndirectSelectionFacilityUserModel(
				ISF_PARAMETER_FILENAME);
		IndirectSelectionFacilityInvocationParameterModel paramsISF = (IndirectSelectionFacilityInvocationParameterModel) um
				.getInvocationParameters();
		Integrated_RSVP_ISF_Launcher launcher = new Integrated_RSVP_ISF_Launcher(
				paramsRSVP, paramsISF);
		// place the launcher on the event dispatching thread
		javax.swing.SwingUtilities.invokeLater(launcher);

	}
}
