package RSVP;

import java.awt.Font;

import coreRSVP.RSVPController;

import RSVPStates.RSVPState;
import RSVPStates.RSVPWholeWordDisplayState;

public class RSVPShrinkWordToDisplayState extends RSVPState {

	public RSVPShrinkWordToDisplayState(RSVPController rsvpController) {
		super(rsvpController);
		x();
	}

	@Override
	public void handleRequest() {
		x();
	}

	private void x() {
		
		//super.rsvpDisplay.setFont(this.getDisplayFont());
		String currentWord = currentToken.getWord();
		rsvpDisplay.display(this.getDisplayFont(), currentWord);
		
		rsvpController.setState(new RSVPWholeWordDisplayState(rsvpController));
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
