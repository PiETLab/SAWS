package coreIndirectSelectionFacility;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class implements a listener that attends to mouse events. If a
 * mousePressed event is detected, then this listener instantiates a keyevent
 * and dispatches it to the key listener that is specified in the constructor of
 * this class.
 * 
 * 
 * @author mb
 * 
 */
public class AssistiveTechnologyMouseLikeDeviceListener implements
		MouseListener {
	private KeyListener keyListener;
	private Component fakeSourceOfEvent;

	public AssistiveTechnologyMouseLikeDeviceListener(KeyListener keyListener,
			Component fakeSourceOfEvent) {
		this.keyListener = keyListener;
		this.fakeSourceOfEvent = fakeSourceOfEvent;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		// System.out.println("ENTERED!");
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// System.out.println("XXXX!");
		KeyEvent ae2 = new KeyEvent(fakeSourceOfEvent, 0, System
				.currentTimeMillis(), 0, KeyEvent.VK_SPACE, ' ');
		// KeyEvent(Component source, int id, long when, int modifiers, int
		// keyCode, char keyChar)
		keyListener.keyPressed(ae2);
	}

	public void mouseReleased(MouseEvent e) {
		// System.out.println("YYYY!");
		// KeyEvent ae2 = new KeyEvent(view, 0, System
		// .currentTimeMillis(), 0, KeyEvent.VK_SPACE, ' ');
		// // KeyEvent(Component source, int id, long when, int modifiers, int
		// // keyCode, char keyChar)
		// this.keyPressed(ae2);
	}

}
