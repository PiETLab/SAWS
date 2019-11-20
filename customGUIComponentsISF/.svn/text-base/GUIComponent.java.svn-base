package customGUIComponentsISF;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

/**
 */
public class GUIComponent {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		JFrame frame = new GUITestingFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(frame.getClass().getName());
		frame.pack();
		// frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true); // alternative: frame.show()
	}
}

class GUITestingFrame extends JFrame implements KeyListener {

	final String BANNER = this.getClass().getName();// "Tester for F2/F3 clicker\n";
	// final String PROMPT = "Watch here for instructions:\n";

	private JTextArea enterArea, timeElapsedDisplay;
	private JButton myButton;

	long startTime;

	public GUITestingFrame() {
		myButton = new JButton();
		// myButton.setText("test");
		String imageFile = System.getProperty("user.home")
				+ "/Documents/workspace/Prj-AssistiveTechnology/images/cat.jpg";
		ImageIcon img = new ImageIcon(imageFile);
		System.out.println(imageFile);
		System.out.println(img.getIconHeight());
		myButton.setIcon(img);

		Dimension maxPossibleSize = this.getToolkit().getScreenSize();
		enterArea = new JTextArea(10, 30);
		// enterArea = new JTextArea(maxPossibleSize);
		enterArea.setFont(new Font("serif", Font.PLAIN, 16));
		enterArea.setText(BANNER);
		// enterArea.append(PROMPT);
		enterArea.setCaretPosition(enterArea.getText().length());
		enterArea.setEditable(true);

		JScrollPane pane = new JScrollPane(enterArea);
		pane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		timeElapsedDisplay = new JTextArea(10, 4);

		// -------------
		// add listeners
		// -------------

		this.addWindowListener(new WindowCloser());
		enterArea.addKeyListener(this);
		enterArea.addMouseListener(new MyMouseListener(enterArea, this));

		// ------------------
		// arrange components
		// ------------------

		// add component to panel

		JPanel mainPanel = new JPanel();
		mainPanel.add(myButton);
		// textPanel.add(enterArea);
		// textPanel.add(pane);

		// add panel to content pane

		this.setContentPane(mainPanel);
		// Container c = getContentPane();
		// c.add(mainPanel, "North");

		startTime = System.currentTimeMillis();
		timeElapsedDisplay.setText("");

	}

	// ---------------------------------
	// implement KeyListener methods (3)
	// ---------------------------------

	public void keyReleased(KeyEvent ke) {
	}

	public void keyTyped(KeyEvent ke) {
		ke.consume();
	}

	public void keyPressed(KeyEvent ke) {

		enterArea.append("getkeyChar: " + ke.getKeyChar() + "\n");
		long timestamp = -1 * startTime + System.currentTimeMillis();

		// if (ke.getKeyCode() == KeyEvent.VK_F2) {
		// enterArea.setForeground(Color.RED);
		// } else if (ke.getKeyCode() == KeyEvent.VK_F3) {
		// enterArea.setForeground(Color.BLUE);
		// } else {
		// enterArea.setForeground(Color.BLACK);
		// }
		enterArea.append(KeyEvent.getKeyText(ke.getKeyCode()) + " received: "
				+ timestamp + " msec elapsed\n");

		startTime = System.currentTimeMillis();

		// enterArea.append(ke.getKeyCode() + "\n");
		// enterArea.append((ke.getKeyCode() == KeyEvent.VK_F2) + "\n");
		// System.out.println(ke);
		// enterArea.append(ke.toString() + "\n");
		// System.out.println(ke);
		// if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
		// String s = enterArea.getText();
		//
		// // find index of new phrase
		// int idx = s.length() - 1;
		// while (s.charAt(idx) != '\n')
		// --idx;
		//
		// // get phrase
		// s = s.substring(idx, s.length());
		// // translate phrase
		// s = translatePhrase(s);
		//
		// // output translated phrase
		// enterArea.append("\n" + s);
		// }
	}

	// -------------
	// inner classes
	// -------------

	// Note: WindowAdapter implements WindowListener

	private class WindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent event) {
			System.exit(0);
		}
	}
}

class MyMouseListener implements MouseListener {
	KeyListener recepientKeyListener;
	JComponent fakeSourceComponent;

	public MyMouseListener(JComponent fakeSourceComponent,
			KeyListener recepientComponent) {
		this.recepientKeyListener = recepientComponent;
		this.fakeSourceComponent = fakeSourceComponent;
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// now need to fire key event
		System.out.println("XXXX!");
		KeyEvent ae2 = new KeyEvent(fakeSourceComponent, 0, System
				.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n');
		// KeyEvent(Component source, int id, long when, int modifiers, int
		// keyCode, char keyChar)
		recepientKeyListener.keyPressed(ae2);

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
