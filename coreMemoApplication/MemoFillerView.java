package coreMemoApplication;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemoFillerView extends JFrame {

	// private JMemoDisplayer compositionArea;
	// private OnScreenKeyboardView onScreenKeyboard;
	// private TraversableEncodingTree encodingTree;

	// private JVOCAInfoWidget infoPanel;
	private JPanel mainPanel;
	private GraphicsDevice device;
	private MemoFillerInvocationParameterModel paramManager;
	private JTextField theMessage;

	public MemoFillerView(MemoFillerInvocationParameterModel paramManager,
			GraphicsDevice device) throws IllegalArgumentException {
		super(device.getDefaultConfiguration());
		this.paramManager = paramManager;

		System.out.println("Begin creation of " + this.getClass().getName()
				+ " ...");
		this.setTitle("Memo Composition");
		this.getContentPane().add(new JLabel("HEY"));
		this.add(new JLabel("HEY2"));

		mainPanel = new JPanel();

		Dimension dimension = new Dimension(100, 200);
		// dimension = Toolkit.getDefaultToolkit().getScreenSize();
		mainPanel.setSize(dimension);
		mainPanel.setPreferredSize(dimension);
		mainPanel.setMaximumSize(dimension);
		mainPanel.setMinimumSize(dimension);
		// mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		mainPanel.setBackground(Color.YELLOW);
		mainPanel.setBackground(paramManager.getBackgroundColour());

		theMessage = new JTextField();
		theMessage.setFont(paramManager.getDisplayFont());
		// theMessage.setText();

		mainPanel.add(theMessage);

		this.setContentPane(mainPanel);

		// this.setBackground(paramManager.getBackgroundColour());
		// JLabel theMessage = new JLabel("Quxxxxitting...");
		// theMessage.setFont(paramManager.getDisplayFont());
		// mainPanel.add(theMessage);

	}

	public void configureForDisplay(boolean isFullScreen) {
		if (!this.isDisplayable()) {
			this.setUndecorated(isFullScreen);
		}
		this.setResizable(!isFullScreen);
		if (isFullScreen) {
			// Full-screen mode
			device.setFullScreenWindow(this);
			this.validate();
		} else {
			// Windowed mode
			this.pack();
			this.setVisible(true);
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.requestFocusInWindow();
		}
	}

	public void showInstruction(String string) {
		// compositionArea.setDisplayWithAppropriateSubPortionOfGloss(string);
	}

	public void displayExitMessage() {
		// System.out.println("Trying to update display");
		theMessage.setText("Quitting...");
		// this.validate();
		// theMessage.setFont(paramManager.getDisplayFont());
		// System.out.println(paramManager.getDisplayFont());
		// mainPanel.add(theMessage);
		// mainPanel.revalidate();
		// this.pack();
		// this.repaint();
	}

	public void displayPDFLocationMessage() {
		theMessage.setFont(theMessage.getFont().deriveFont(14f));
		theMessage.setText("Location of PDF: "
				+ paramManager.getFullFileNameForPDFFile());
	}

}
