package coreRSVP;

import invocationParametersRSVP.RSVPInvocationParameterModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.lang.reflect.Field;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import RSVP.LexicalToken;
import RSVP.TickerTapeController;

public class RSVPDisplayer extends JFrame {

	private static final boolean IS_VERBOSE = true;

	private TickerTapeController tickerTape;

	private JScrollPane pane;

	private JLabel displayText;

	private JLabel displaStatusInfo;

	private RSVPInvocationParameterModel paramModel;

	private GraphicsDevice device;

	// private Font currentFont;

	public RSVPDisplayer(Font font) {
		// don't use this - OBSOLETE
	}

	public RSVPDisplayer(RSVPInvocationParameterModel paramModel,
			GraphicsDevice device) {
		super(device.getDefaultConfiguration());
		this.device = device;
		this.paramModel = paramModel;

		this.setTitle(this.getClass().getName());

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// panel.setComponentOrientation(ComponentOrientation.)
		displayText = new JLabel();
		displayText.setFont(getDefaultFont());
		displayText.setOpaque(true);

		pane = new JScrollPane(displayText);
		// pane
		// .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		// panel.add(displayText);// BorderLayout.LINE_START);
		panel.add(pane);// BorderLayout.LINE_START);

		displaStatusInfo = new JLabel();
		displaStatusInfo.setFont(new Font("Serif", Font.PLAIN, 16));
		displaStatusInfo.setOpaque(true);
		panel.add(displaStatusInfo);// , BorderLayout.PAGE_END);

		this.getContentPane().add(panel);
		tickerTape = new TickerTapeController(1000);

	}

	private Font getDefaultFont() {
		return paramModel.getDisplayFont();
	}

	public void display(LexicalToken token) {
		this.display(token.getWord());
	}

	public void display(String string) {
		displayText.setText("");
		displayText.setForeground(Color.BLACK);
		displayText.setFont(getDefaultFont());
		displayText.setText(string);
		displaStatusInfo.setText(" ");
		// displayText.setText("<html>" + string + "</html>");
		// System.out.println(string);
	}

	public void indicatePausedState() {
		displayText.setBackground(Color.PINK);
		displayText.setForeground(Color.RED);
		displaStatusInfo.setText("paused");
	}

	public void indicateUnpausedState() {
		displayText.setBackground(paramModel.getBackgroundColour());
		displayText.setForeground(Color.BLACK);
		displaStatusInfo.setText(" ");
		// this.repaint();
	}

	// boolean isTooLargeToDisplay(String string) {
	// // displayText2.setText(arg0);
	// if (IS_VERBOSE)
	// displayText2.setText("" + fm.stringWidth(string) + "/"
	// + this.getWidth());
	//
	// return fm.stringWidth(string) > this.getWidth();
	// }

	public boolean canFitOnDisplay(String string) {
		// displayText2.setText(arg0);
		if (IS_VERBOSE)
			displaStatusInfo.setText(""
					+ this.getFontMetrics().stringWidth(string) + "/"
					+ this.getWidth());

		return this.getFontMetrics().stringWidth(string) <= this.getWidth();
	}

	// /**
	// * this display needs to know about its controller - if a word is too
	// large,
	// * then the display needs to turn off externally-generated events so that
	// it
	// * can conduct its own handling (via the controller's services, of course)
	// *
	// * @param rsvpController
	// */
	// public void identifyController(RSVPController rsvpController) {
	// this.rsvpController = rsvpController;
	// }

	public void scrollViewportToRight() {

		JViewport viewport = pane.getViewport();

		Point pt = viewport.getViewPosition();

		int numPixelsForShift = 50;

		pt.x += numPixelsForShift; // * xmove;

		// pt.y += m_pgVert * ymove;

		// pt.x = Math.max(0, pt.x);

		// the total width of the word to be shown:
		int totalWordWidth = viewport.getView().getWidth();
		int maxExtent = totalWordWidth - viewport.getWidth();
		// don't extend x past the right edge of the word
		pt.x = Math.min(maxExtent, pt.x);

		// pt.y = Math.max(0, pt.y);

		// pt.y = Math.min(getMaxYExtent(), pt.y);

		viewport.setViewPosition(pt);

	}

	public boolean isEndOfWordInView() {
		// get the viewport's position and extent to figure out where right-hand
		// edge is, then compare to width of word
		Rectangle rect = pane.getViewport().getViewRect();
		double rightEdge = rect.getX() + rect.getWidth();
		return pane.getViewport().getView().getWidth() <= rightEdge;
	}

	public FontMetrics getFontMetrics() {
		return this.getFontMetrics(paramModel.getDisplayFont());
	}

	public Font getFont() {
		return paramModel.getDisplayFont();
	}

	/**
	 * Displays the passed word using the passed font
	 * 
	 * @param font
	 * @param currentWord
	 */
	public void display(Font font, String currentWord) {
		System.out.println("new font size: " + font.getSize());
		// displayText.setForeground(Color.CYAN);
		displayText.setFont(font);
		// displayText.setText("<html>" + currentWord + "</html>");
		displayText.setText(currentWord);

		displaStatusInfo.setText("Temp font size: " + font.getSize());

	}

	public void indicateInitState() {
		displaStatusInfo.setText("Initialized.  Press space to start.");
		System.out.println("Initialized");
	}

	public RSVPInvocationParameterModel getParameterModel() {
		return paramModel;
	}

	public void configureForDisplay(boolean isFullScreen) {
		System.out.println(this.getClass().getName() + ": is in full screen? "
				+ isFullScreen);

		// rsvpView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// rsvpView.pack();
		// rsvpView.setExtendedState(Frame.MAXIMIZED_BOTH);
		// rsvpView.setVisible(true); // alternative: frame.show()

		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setTitle(this.getClass().getName());
		// this.pack();
		// this.setVisible(true);
		// this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		// below is possibly optional
		// GraphicsEnvironment env = GraphicsEnvironment
		// .getLocalGraphicsEnvironment();
		// GraphicsDevice device = env.getDefaultScreenDevice();
		// boolean isFullScreen = device.isFullScreenSupported();
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

}
// public class TightJLabel extends JLabel {
// public Dimension getPreferredSize() {
// FontMetrics fm = getFontMetrics(getFont());
// return new Dimension(fm.stringWidth(getText()),
// fm.getAscent()+fm.getDescent());
// }
// }
