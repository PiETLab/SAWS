package IndirectSelectionFacilityCommands;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

public abstract class IndirectSelectionFaciltyCommand implements Serializable {

	protected final boolean IS_VERBOSE = false;

	private transient Logger theLogger;

	protected IndirectSelectionFaciltyCommand() {
		theLogger = Logger.getLogger("voca.vocaApp");
	}

	public Logger getLogger() {
		 if (theLogger == null) {
			theLogger = Logger.getLogger("voca.vocaApp");
		}
		return theLogger;
	}

	// private Color[] coloursForFokusCycleElements = { Color.CYAN, Color.RED };
	protected Color[] coloursForFokusCycleElements = { Color.YELLOW,
			Color.GREEN };

	public abstract boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException;

	public String toString() {
		return this.getClass().getSuperclass().getName() + ": command "
				+ this.getClass().getName();
	}

	public void toggleCapsOnOff() {
	}

	public void toggleCapsOn() {
	}

	public void toggleCapsOff() {
	}

}
