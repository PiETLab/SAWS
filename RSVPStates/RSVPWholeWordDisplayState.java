package RSVPStates;

import java.awt.Font;

import coreRSVP.RSVPController;


public class RSVPWholeWordDisplayState extends RSVPState {

	public RSVPWholeWordDisplayState(RSVPController controller) {
		super(controller);
		x();
	}

	@Override
	public void handleRequest() {
		x();
	}

	private void x() {
		RSVPState nextState;
		if (tok.hasNextToken()) {
			RSVPState.currentToken = tok.nextTokenNotStrict();
			String currentWord = currentToken.getWord();
			if (!rsvpDisplay.canFitOnDisplay(currentWord)) {
				// System.out.println(currentWord);
				// OPTION 1
				//nextState = new RSVPSubUnitDisplayState(rsvpController);
				//rsvpController.setState(nextState);

				//OPTION 2
				//
				//nextState = new RSVPShrinkWordToDisplayState(rsvpController);
				rsvpDisplay.display(this.getDisplayFont(), currentWord);

			} else {
				rsvpDisplay.display(currentWord);
				// nextState = new RSVPWholeWordDisplayState(rsvpController);
			}
		} else {
			nextState = new RSVPEndOfTextReachedState(rsvpController);
			rsvpController.setState(nextState);
		}
	}
	
	public Font getDisplayFont() {
		String fontFamily = super.rsvpDisplay.getFontMetrics().getFont()
				.getFamily();
		int fontStyle = super.rsvpDisplay.getFontMetrics().getFont().getStyle();
		int fontSize = RSVPState.currentToken.getMaxPossiblePointSize(
				super.rsvpDisplay, fontFamily, fontStyle);
		//System.out.println(fontSize);
		return new Font(fontFamily, fontStyle, fontSize);
	}

	

}
