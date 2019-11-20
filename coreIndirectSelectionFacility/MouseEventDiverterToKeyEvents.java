package coreIndirectSelectionFacility;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class MouseEventDiverterToKeyEvents implements MouseListener {
	KeyListener recepientKeyListener;
	Component fakeSourceComponent;

	public MouseEventDiverterToKeyEvents(Component fakeSourceComponent,
			KeyListener recepientComponent) {
		this.recepientKeyListener = recepientComponent;
		this.fakeSourceComponent = fakeSourceComponent;
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		// now need to fire key event
		// System.out.println("XXXX!");
		KeyEvent ae2 = new KeyEvent(fakeSourceComponent, 0, System
				.currentTimeMillis(), 0, KeyEvent.VK_SPACE, ' ');
		// KeyEvent(Component source, int id, long when, int modifiers, int
		// keyCode, char keyChar)
		recepientKeyListener.keyPressed(ae2);

	}

	public void mouseReleased(MouseEvent arg0) {
	}

}