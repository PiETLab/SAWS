package RSVPStates;

import org.apache.fop.hyphenation.HyphenationException;
import org.apache.fop.hyphenation.HyphenationTree;

import coreRSVP.RSVPController;
import coreRSVP.RSVPDisplayer;

import RSVP.LexicalToken;
import RSVP.TwoLevelTokenizer;

public abstract class RSVPState {

	protected RSVPController rsvpController;
	protected RSVPDisplayer rsvpDisplay;
	protected TwoLevelTokenizer tok;
	HyphenationTree ht = new HyphenationTree();

	protected static LexicalToken currentToken;
	protected static String currentSubToken;

	public RSVPState(RSVPController controller) {
		this.rsvpController = controller;
		rsvpDisplay = controller.getView();
		tok = controller.getTokenizer();
		ht = rsvpDisplay.getParameterModel().getHyphenationTree();
		// try {
		// ht.loadPatterns("en_US.xml");
		// } catch (HyphenationException e) {
		// e.printStackTrace();
		// }
		//
	}

	public abstract void handleRequest();

	public String toString() {
		return "RSVP state subclass: " + this.getClass().getName();
	}

}
