package IntegrationClasses;


import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersRSVP.RSVPInvocationParameterModel;

import java.awt.Frame;

import javax.swing.JFrame;

import obsolete.RSVPParameterModel_obsolete;

import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;


import RSVP.EBook;
import RSVP.RSVPLauncher;
import SoftwareDeployment.UserSpecificModel;

/**
 * 
 * This class implements a runnable that can be used as the parameter to the
 * javax.swing.SwingUtilities.invokeLater(Runnable) method.
 * 
 * @author mb
 * 
 */
public class Integrated_RSVP_ISF_Launcher implements Runnable {

	private RSVPLauncher rsvpLauncher;
	private IndirectSelectionFacilityLauncher isfLauncher;

	public Integrated_RSVP_ISF_Launcher(RSVPInvocationParameterModel paramsRSVP,
			IndirectSelectionFacilityInvocationParameterModel paramsISF) {

		// the rsvp module is initialized to a state in which it is paused
		rsvpLauncher = new RSVPLauncher(
				paramsRSVP, EBook.getDefaultEbook());
		javax.swing.SwingUtilities.invokeLater(rsvpLauncher);
		// the tcf initializes to a state in which the focus advancement is
		// engaged
		// MeaganTCFDriver.launchApp(args2);

		isfLauncher = new IndirectSelectionFacilityLauncher(paramsISF);
		javax.swing.SwingUtilities.invokeLater(isfLauncher);

		// Integrated_RSVP_ISF_Launcher launcher = new
		// Integrated_RSVP_ISF_Launcher();
		// javax.swing.SwingUtilities.invokeLater(launcher);

	}

	public void run() {
		IntegratedController controller = new IntegratedController(rsvpLauncher
				.getController(), isfLauncher.getController());
		// isfLauncher.getController().getView().getFocusReceivingComponent()
		// .addKeyListener(controller);
		// // what is the rsvp controller listening to?
		// rsvpLauncher.getController().getRSVPDisplay()
		// .addKeyListener(controller);
	}

}
