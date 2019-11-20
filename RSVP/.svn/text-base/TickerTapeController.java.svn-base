package RSVP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import coreRSVP.RSVPDisplayer;

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
public class TickerTapeController implements ActionListener {

	private RSVPDisplayer display;
	private String string;

	private Timer systemEventGenerator;

	public TickerTapeController(int eventRateMSec) {
		systemEventGenerator = new Timer(eventRateMSec, this);
	}

	public void pause() {
		systemEventGenerator.stop();
	}

	public void launchTicker(RSVPDisplayer display, String string) {
		this.display = display;
		this.string = string;
		systemEventGenerator.restart();
	}

	public void actionPerformed(ActionEvent e) {
		string = string.substring(2, string.length());
		// display.forceDisplay(string);
		if (display.canFitOnDisplay(string))
			pause();
	}

	public boolean isRunning() {
		return systemEventGenerator.isRunning();
	}

}
