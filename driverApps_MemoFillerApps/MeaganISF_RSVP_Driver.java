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
 * Launches an application that is a early iteration of the system - app
 * presents either the isf or the rsvp
 * 
 * The controller puts the relevant window to the front, depending on what is
 * needed
 * 
 * 
 * @author mb
 * 
 */
public class MeaganISF_RSVP_Driver {

	static RSVPLauncher rsvpLauncher;
	static IndirectSelectionFacilityLauncher isfLauncher;

	public static void main(String[] args) {
		final String RSVP_PARAMETER_FILENAME = "rsvp-parameters.txt";
		UserSpecificModel umRSVP = new RSVPUserModel(RSVP_PARAMETER_FILENAME);
		RSVPInvocationParameterModel paramsRSVP = (RSVPInvocationParameterModel) umRSVP
				.getInvocationParameters();

		final String ISF_PARAMETER_FILENAME = "isf-parameters.txt";
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
