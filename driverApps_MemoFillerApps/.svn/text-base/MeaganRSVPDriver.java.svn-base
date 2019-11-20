package driverApps_MemoFillerApps;

import invocationParametersRSVP.RSVPInvocationParameterModel;
import invocationParametersRSVP.RSVPUserModel;
import RSVP.EBook;
import RSVP.RSVPLauncher;
import SoftwareDeployment.UserSpecificModel;

//-mx1024M
public class MeaganRSVPDriver {

	public static void main(String[] args) {

		final String RSVP_PARAMETER_FILENAME = "/Users/mb/Documents/workspace/Prj-Meagan/Parameters/rsvp-parameters.txt";
		UserSpecificModel um = new RSVPUserModel(RSVP_PARAMETER_FILENAME);
		
		RSVPLauncher launcher = new RSVPLauncher(
				(RSVPInvocationParameterModel) um.getInvocationParameters(),
				EBook.getDefaultEbook());
		javax.swing.SwingUtilities.invokeLater(launcher);
	}

}
