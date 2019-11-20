package IntegrationClasses;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.RSVPController;


/**
 * 
 * Press s/S to start keyboard focus advancement
 * 
 * Press t/T to toggle between isf and rsvp
 * 
 * 
 * @author mb
 * 
 */
public class IntegratedController implements KeyListener {

	private RSVPController rsvpController;
	private IndirectSelectionFacilityController isfController;

	public IntegratedController(RSVPController rsvpController,
			IndirectSelectionFacilityController isfController) {

		// PassiveFokusAdvancerSwing fokusController) {
		this.rsvpController = rsvpController;
		this.isfController = isfController;
		// this.fokusController = fokusController;

		// whatever the various controllers are listening to, the integrated
		// controller should listen to as well

		// what is the isfController listening to?
		isfController.getView().getFocusReceivingComponent().addKeyListener(
				this);
		// what is the rsvp controller listening to?
		rsvpController.getView().addKeyListener(this);

	}

	public void keyPressed(KeyEvent e) {
		// System.out.println(this.getClass().getName() + " listening.  Heard: "
		// + e);
		// s to start focus advancement
		if (e.getKeyCode() == KeyEvent.VK_S) {
			isfController.togglePassiveFocusAdvancementOnOff();

			// isfController.launchPassiveFocusAdvancement();

			// t to toggle between isf and rsvp
		} else if (e.getKeyCode() == KeyEvent.VK_T) {
			System.out.println("trying to toggle between rsvp and isf");
			if (rsvpController.isActive()) {
				rsvpController.pause();
				isfController.putViewToFront();
				isfController.unpausePassiveFocusAdvancement();
			} else if (isfController.isActive()) {
				isfController.pausePassiveFocusAdvancement();
				rsvpController.replaceContent(isfController.getTextGloss());
				rsvpController.resetToStartOfText();
				rsvpController.unpause();
				rsvpController.putViewToFront();
			}

			// if (fokusController.
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
