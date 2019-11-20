package RSVPStates;

import coreRSVP.RSVPController;

public class RSVPEndOfTextReachedState extends RSVPState {

	public RSVPEndOfTextReachedState(RSVPController rsvpController) {
		super(rsvpController);
	}

	@Override
	public void handleRequest() {
		this.alternativeShowBlankScreenAndStop();

	}

	public void alternativeRepeatFromBeginning() {
		rsvpDisplay.display("...");
		RSVPState nextState;
		nextState = new RSVPWholeMultiWordDisplayState(rsvpController);
		rsvpController.setState(nextState);
		rsvpController.resetToStartOfText();
	}

	public void alternativeShowBlankScreenAndStop() {
		rsvpDisplay.display("");
		RSVPState nextState;
		nextState = new RSVPPausedState(rsvpController);
		rsvpController.setState(nextState);
		// rsvpController.resetToStartOfText();
	}

}
