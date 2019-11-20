package RSVP;

import invocationParametersRSVP.RSVPInvocationParameterModel;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import coreRSVP.RSVPController;
import coreRSVP.RSVPDisplayer;

import SoftwareDeployment.RSVPParameterModel_obsolete;

/**
 * 
 * This class implements a runnable that starts up the RSVP app. For the default
 * initial state of the RSVP app, see the RSVPController class docs.
 * 
 * This runnable is intended to serve as the parameter to the
 * javax.swing.SwingUtilities.invokeLater(Runnable) method. This ensures that
 * this RSVP app is placed on the event dispatching thread).
 * 
 * @author mb
 * 
 */
public class RSVPLauncher implements Runnable {

	private RSVPInvocationParameterModel params;
	private EBook ebook;
	private RSVPController rsvpController;

	public RSVPLauncher(RSVPInvocationParameterModel params, EBook ebook) {
		this.params = params;
		this.ebook = ebook;
	}

	public void run() {
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		
		RSVPDisplayer rsvpView = new RSVPDisplayer(params, device);
		//System.out.println("rsvpView...");
		rsvpController = new RSVPController(rsvpView, new TwoLevelTokenizer(
				ebook), params);
		//System.out.println("RSVPController...");
		
		//rsvpView.
		//System.out.println("addKeyListener...");
	}

	public RSVPController getController() {
		return rsvpController;
	}

}
