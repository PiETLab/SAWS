package coreRSVP;

import invocationParametersRSVP.RSVPInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class RSVPTimerWrapper implements ActionListener {

	private RSVPInvocationParameterModel paramModel;
	private Timer nextWordEventGenerator;
	private ActionListener theActionListenerBeingTricked;

	public RSVPTimerWrapper(RSVPInvocationParameterModel paramModel,
			ActionListener theActionListener) {
		theActionListenerBeingTricked = theActionListener;
		this.paramModel = paramModel;
		nextWordEventGenerator = new Timer(paramModel.getEventRateInMSec(),
				this);
	}

	public void stop() {
		nextWordEventGenerator.stop();
	}

	public void restart() {
		nextWordEventGenerator.restart();
	}

	public boolean isRunning() {
		return nextWordEventGenerator.isRunning();
	}

	public int getDelay() {
		return nextWordEventGenerator.getDelay();
	}

	public void actionPerformed(ActionEvent e) {
		theActionListenerBeingTricked.actionPerformed(new ActionEvent(this, 0,
				this.getClass().getName()));
	}

}
