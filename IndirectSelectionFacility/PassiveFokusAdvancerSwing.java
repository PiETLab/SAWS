/*
 * Created on 26-Jul-2004
 */
package IndirectSelectionFacility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.Timer;

import IndirectSelectionFacilityCommands.AdvanceFokusCommand;
import IndirectSelectionFacilityCommands.InitialFokusCommand;

/**
 * This class implements the functionality wherein an action event fires every x
 * milliseconds. The action event handlers for these events are on Swing's event
 * dispatching thread. This class also implements the functionality wherein it
 * is an ActionListener which is installed on the source of the action events.
 * When such action events are triggered, this class detects them and causes a
 * action to be performed on a TextCompositionFacilityFrame. The action
 * specifically is an advance focus action.
 * 
 * Thus, this class implements passive focus advancement. This class makes use
 * of the Timer class (see the Java API).
 * 
 * @author Melanie Baljko, 2004
 */
public class PassiveFokusAdvancerSwing implements ActionListener {

	private static final boolean IS_VERBOSE = false;

	private PrintStream output = System.out;

	// private static IndirectSelectionFacilityController frameController;
	private ActionListener frameController;

	private Timer fokusTransferTimer;

	private int dwellTime;

	private final int DEFAULT_ID = 0;

	private final int INITIAL_DELAY = 50;

	private final String ACTION_COMMAND = "advance fokus";

	private boolean isFokusInitialCond = true;

	private boolean isInPausedMode = false;

	private boolean isInHardStopMode = false;

	/**
	 * 
	 * @param frameController
	 */
	public PassiveFokusAdvancerSwing(ActionListener frameController,
			int _dwellTime) {
		this.frameController = frameController;
		dwellTime = _dwellTime;
		fokusTransferTimer = new Timer(dwellTime, this);
		// fokusTransferTimer.setInitialDelay(dwellTime);
		fokusTransferTimer.setInitialDelay(INITIAL_DELAY);
		fokusTransferTimer.setCoalesce(true);
		// setFokusInitialCond(true);
		if (IS_VERBOSE)
			System.out.println("Initialized Focus Advancer.  Class: "
					+ this.getClass().getName());
	}

	/**
	 * This method advances fokus on the TCFrame instance. When the delay (given
	 * in milliseconds) has passed, the Timer fires an action event to its
	 * listeners.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (IS_VERBOSE) {
			System.out.println("Passive Fokus Advancer dispatched event.");
		}
		/*
		 * output.println("actionPerformed on: " + toString());
		 * output.println("\t ActionEvent is: " + ae.toString());
		 * output.println("\t action command of ActionEvent is: " +
		 * ae.getActionCommand());
		 * output.println("\t source of ActionEvent is: " + ae.getSource());
		 */

		// output.println("Passive Fokus Advancer: action event detected.");
		// source: ae's source, id: 0, command: ??
		// VOCAActionEvent ae2 = new VOCAActionEvent(ae.getSource(),
		// VOCAActionEvent.getAdvanceFokusID(), VOCAActionEvent
		// .getAdvanceFokusCommand());
		ActionEvent ae2;
		if (isFokusInitialCond()) {
			setFokusInitialCond(false);
			ae2 = new ActionEvent(new InitialFokusCommand(), 99,
					"Initial Fokus Command");
		} else {
			ae2 = new ActionEvent(new AdvanceFokusCommand(), 88,
					"Advance Fokus Command");
		}
		if (!isInHardStopMode) {
			((ActionListener) frameController).actionPerformed(ae2);
		} else {
			System.out
					.println("Generated timer event consumed in PassiveFokusAdvancer.");
			getFokusTransferTimer().stop();
		}
	}

	/**
	 * This method starts the Timer.
	 */
	public void startEventGenerator() {
		// if (IS_VERBOSE)
		output
				.println("Passive Fokus Advancer: starting passive fokus advancement.");
		// if (!isInPausedMode) {
		// if (IS_VERBOSE)
		// output.println("Updating dwell time command.");
		//
		// // this is how we update the JComponents about the state of this
		// // instance.
		// ActionEvent ae = new ActionEvent(new UpdateDwellTimeCommand(""
		// + dwellTime), 0, "");
		// ((ActionListener) frameController).actionPerformed(ae);
		//
		// } else {
		// if (IS_VERBOSE)
		// output.println("Updating dwell time command: paused.");
		// ActionEvent ae = new ActionEvent(new UpdateDwellTimeCommand(
		// "paused"), 0, "");
		// ((ActionListener) frameController).actionPerformed(ae);
		// }

		// getFokusTransferTimer().start();
		getFokusTransferTimer().restart();
		if (IS_VERBOSE)
			System.out.println("fokusTransferTimer.isRunning(): "
					+ getFokusTransferTimer().isRunning());

	}

	/**
	 * Whether this is in a paused state or not, this method causes events to
	 * start firing again.
	 * 
	 */
	// public static void initToStartOfFokusCycle() {
	// if (IS_VERBOSE)
	// output
	// .println("Passive Fokus Advancer: restarting passive fokus advancement.");
	// setFokusInitialCond(true);
	// if (!isInPausedMode) {
	// output.println("***Passive Fokus Advancer: isInPausedMode ? "
	// + isInPausedMode);
	// // ActionEvent ae = new ActionEvent(new UpdateDwellTimeCommand(""
	// // + dwellTime), 0, "UpdateDwellTimeCommand");
	// ActionEvent ae = new ActionEvent(new PauseForABeatCommand(), 0,
	// "UpdateDwellTimeCommand");
	//
	// ((ActionListener) frameController).actionPerformed(ae);
	// fokusTransferTimer.restart();
	// } else {
	// ActionEvent ae = new ActionEvent(new UpdateDwellTimeCommand(
	// "paused"), 0, "UpdateDwellTimeCommand: paused");
	// ((ActionListener) frameController).actionPerformed(ae);
	// }
	// }
	// public static void initToStartOfFokusCycle2() {
	// // setFokusInitialCond(true);
	// // fokusTransferTimer.restart();
	// }
	public void initToStartOfFokusCycle() {
		setFokusInitialCond(true);
		// // isInPausedMode = false;
		fokusTransferTimer.start();
	}

	public void pause() {
		// isInPausedMode = true;
		getFokusTransferTimer().stop();
	}

	public void unpause() {
		getFokusTransferTimer().restart();
	}

	/**
	 * This method ensures that the firing of ActionEvents stops, even if the
	 * start or restart method is invoked subsequently
	 * 
	 */
	// public static void hardStopEventGenerator() {
	// isInHardStopMode = true;
	// }
	/**
	 * This method stops the firing of events
	 */
	// public static void stop() {
	// output
	// .println("Passive Fokus Advancer: halting passive focus advancement.");
	// fokusTransferTimer.stop();
	// }
	/**
	 * This method updates the delay used by the timer (the interval, in
	 * milliseconds, between the firing of action events).
	 */
	// public static void updateDwellTime(int _dwellTime) {
	// dwellTime = _dwellTime;
	// fokusTransferTimer.setDelay(_dwellTime);
	// }
	// public static void pauseEventGenerator() {
	// isInPausedMode = true;
	// if (fokusTransferTimer != null) {
	// ActionEvent ae = new ActionEvent(new UpdateDwellTimeCommand(
	// "paused"), 0, "");
	// ((ActionListener) frameController).actionPerformed(ae);
	// fokusTransferTimer.stop();
	// }
	// }
	// public static int getDwellTime() {
	// return dwellTime;
	// }
	public String toStringAbbr() {
		String tmp = this.toString();
		return tmp.substring(0, tmp.indexOf("["));
	}

	private boolean isFokusInitialCond() {
		return isFokusInitialCond;
	}

	private void setFokusInitialCond(boolean isFokusResetCond) {
		isFokusInitialCond = isFokusResetCond;
	}

	public boolean isEventGenerationActive() {
		if (IS_VERBOSE)
			System.out.println("fokusTransferTimer.isRunning(): "
					+ fokusTransferTimer.isRunning());
		return fokusTransferTimer.isRunning();
	}

	private Timer getFokusTransferTimer() {
		return fokusTransferTimer;
	}
}
