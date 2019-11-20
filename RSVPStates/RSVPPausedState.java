package RSVPStates;

import coreRSVP.RSVPController;

public class RSVPPausedState extends RSVPState {

	public RSVPPausedState(RSVPController rsvpController) {
		super(rsvpController);
	}

	@Override
	public void handleRequest() {
		rsvpController.pause();
	}

}
