package RSVPStates;

import coreRSVP.RSVPController;

public class RSVPSubUnitDisplayState extends RSVPState {

	public RSVPSubUnitDisplayState(RSVPController rsvpController) {
		super(rsvpController);
		RSVPState.currentSubToken = RSVPState.currentToken
				.getFirstHyphenSubstring(rsvpDisplay.getWidth(), rsvpDisplay
						.getFontMetrics(), ht);
		rsvpDisplay.display(RSVPState.currentSubToken);
	}

	@Override
	public void handleRequest() {
		if (RSVPState.currentToken.hasMoreHyphenSubstring()) {
			RSVPState.currentSubToken = currentToken.getNextHyphenSubstring();
			rsvpDisplay.display(RSVPState.currentSubToken);
			// nextState = new RSVPSubUnitDisplayState(rsvpController);
		} else {
			// if (tok.hasNextToken()) {
			// RSVPState.currentToken = tok.nextTokenAmalgamatePunctuation();
			rsvpController.setState(new RSVPWholeWordDisplayState(
					rsvpController));
			// }
		}

	}

}
