package IntegrationClasses;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreMemoApplication.RSVPController;

//import org.apache.fop.hyphenation.HyphenationTree;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import RSVPStates.RSVPState;
import RSVPStates.RSVPWholeMultiWordDisplayState;

/**
 * 
 * This class implements a controller that has three commands: pause, unpause,
 * and back.
 * 
 * The behaviour of the commands is a
 * 
 * @author mb
 * 
 */
public class AppController implements ActionListener, KeyListener {

	private RSVPController rsvpController;
	private IndirectSelectionFacilityController isfController;

	public AppController(RSVPController rsvpController,
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
				isfController.putViewToFront();
				isfController.unpausePassiveFocusAdvancement();
			} else if (isfController.isActive()) {
				isfController.pausePassiveFocusAdvancement();
				rsvpController.replaceContent(isfController.getTextGloss());
				rsvpController.putViewToFront();
			}

			// if (fokusController.
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void actionPerformed(ActionEvent arg0) {
	}
	
	
}
