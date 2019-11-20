package RSVPStates;

import coreRSVP.RSVPController;

public class RSVPUnpausedState extends RSVPState {

	public RSVPUnpausedState(RSVPController rsvpController) {
		super(rsvpController);
	}

	@Override
	public void handleRequest() {
		rsvpController.unpause();
		rsvpController.setState(new RSVPWholeMultiWordDisplayState(
				rsvpController));
	}

}
