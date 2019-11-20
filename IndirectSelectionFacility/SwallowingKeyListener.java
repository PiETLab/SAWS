package IndirectSelectionFacility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwallowingKeyListener implements KeyListener {

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
		arg0.consume();
	}

}
