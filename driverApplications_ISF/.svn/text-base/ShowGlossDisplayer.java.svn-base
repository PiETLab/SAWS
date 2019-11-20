package driverApplications_ISF;

import javax.swing.*;

import customGUIComponentsISF.JTextDisplayer;
import customGUIComponentsISF.JTextDisplayerWithFontInformation;

import sourceSymbolSet.MeaganCompositionSet;
import sourceSymbolSet.MiniSet_Size1;
import sourceSymbolSet.MiniSet_Size8;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

import IndirectSelectionFacilityCommands.AppendAndConditionallyReinitializeWithAdaptiveCapitalizationCommand;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.io.*;
import java.util.logging.Logger;

/**
 * 
 * @author M. Baljko, 2004
 * @version 1.0
 * 
 */

public class ShowGlossDisplayer {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					// } catch (EngineException e) {
					// e.printStackTrace();
				}
			}
		});

	}

	private static void createAndShowGUI() throws IllegalArgumentException {
		// private static void createAndShowGUI() throws
		// IllegalArgumentException,
		// EngineException {
		// Make sure we have nice window decorations.
		// JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}

		JFrame.setDefaultLookAndFeelDecorated(false);

		TmpFrame frame = new TmpFrame();
		// frame.setTitle("Tester");
		// frame.requestFocusInWindow();
		// frame.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window.
		frame.pack();
		frame.setLocationRelativeTo(null); // center it
		frame.setVisible(true);
		frame.requestFocusInWindow();

		// frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setExtendedState(Frame.MAXIMIZED_HORIZ);

		// System.out.println("Size: " + frame.compositionArea2.getSize());

		SourceSymbolSet ss = new MiniSet_Size1();
		for (SourceSymbol s : ss.getAllSourceSymbols()) {
			// if (s.getVOCACommand() instanceof AppendCommand) {
			// AppendCommand ac = (AppendCommand) s.getVOCACommand();
			// compositionArea.augment(ac.getStringToAppend());
			// }
			frame.compositionArea.augmentGloss(s.getTextLabel());

		}
		// frame.compositionArea.augment("O");
		// frame.compositionArea.augment("Q");
		// frame.compositionArea.augment("J");

		frame.compositionArea.setDisplayFontAsLargesAsPossible(ss
				.getSourceSymbolsByRankOrder());

	}
}

class TmpFrame extends JFrame {
	public JTextDisplayer compositionArea;
	public JTextDisplayerWithFontInformation compositionArea2;

	Dimension availableRealEstateForGlossWidget = new Dimension((int) Toolkit
			.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit
			.getDefaultToolkit().getScreenSize().getHeight() / 2);

	public TmpFrame() {
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();

		boolean isFullScreen = device.isFullScreenSupported();
		setUndecorated(isFullScreen);
		setResizable(!isFullScreen);
		if (isFullScreen) {
			// Full-screen mode
			device.setFullScreenWindow(this);
			validate();
		} else {
			// Windowed mode
			pack();
			setVisible(true);
		}

		Font DEFAULT_FONT;
		DEFAULT_FONT = new Font("bauhaus93", Font.PLAIN, 16);
		DEFAULT_FONT = new Font("times-roman", Font.PLAIN, 16);
		DEFAULT_FONT = new Font("lucida-sans", Font.PLAIN, 16);
		DEFAULT_FONT = new Font("sansserif", Font.PLAIN, 16);
		DEFAULT_FONT = new Font("serif", Font.PLAIN, 16);

		compositionArea = new JTextDisplayer(
				availableRealEstateForGlossWidget, DEFAULT_FONT);
		this.add(compositionArea);
		// compositionArea2 = new JTextDisplayerWithFontInformation(
		// availableRealEstateForGlossWidget);
		// this.add(compositionArea2);

	}
}

// class TextDisplayerWithFontInformation extends JComponent {
//
// private String message = "Java2s";
//
// private Font font = new Font("Dialog", Font.PLAIN, 96);
//
// private boolean showHints = true;
//
// public void paint(Graphics g) {
// Graphics2D g2 = (Graphics2D) g;
// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
// RenderingHints.VALUE_ANTIALIAS_ON);
//
// g2.setFont(font);
// int width = getSize().width;
// int height = getSize().height;
//
// FontRenderContext frc = g2.getFontRenderContext();
// LineMetrics metrics = font.getLineMetrics(message, frc);
// float messageWidth = (float) font.getStringBounds(message, frc)
// .getWidth();
//
// // center text
// float ascent = metrics.getAscent();
// float descent = metrics.getDescent();
// float x = (width - messageWidth) / 2;
// float y = (height + metrics.getHeight()) / 2 - descent;
//
// int PAD = 25;
// g2.setPaint(getBackground());
// g2.fillRect(0, 0, width, height);
//
// g2.setPaint(getForeground());
// g2.drawString(message, x, y);
//
// if (showHints) {
// g2.setPaint(Color.white); // Base lines
// drawLine(g2, x - PAD, y, x + messageWidth + PAD, y);
// drawLine(g2, x, y + PAD, x, y - ascent - PAD);
// g2.setPaint(Color.green); // Ascent line
// drawLine(g2, x - PAD, y - ascent, x + messageWidth + PAD, y
// - ascent);
// g2.setPaint(Color.red); // Descent line
// drawLine(g2, x - PAD, y + descent, x + messageWidth + PAD, y
// + descent);
// }
// }
//
// private void drawLine(Graphics2D g2, double x0, double y0, double x1,
// double y1) {
// Shape line = new java.awt.geom.Line2D.Double(x0, y0, x1, y1);
// g2.draw(line);
// }
//
// }
