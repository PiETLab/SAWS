package driverApplications_ISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sourceSymbolSet.MeaganCompositionSet;
import sourceSymbolSet.SourceSymbolSet;

import SoftwareDeployment.UserSpecificModel;
import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;
import coreIndirectSelectionFacility.MySizeChangeListener;
import customGUIComponentsISF.JTextDisplayerWithFontInformation;
import encodingTrees.AlphabeticET_MeaganSubset_2Level;

/**
 * JFC/Swing program to translate English into Pig Latin -- GUI version.
 * <p>
 * 
 * The user is prompted to type phrases into a <code>JTextArea</code> component.
 * Each time the ENTER key is pressed, the words on the current line are
 * translated into Pig Latin, with the result appearing on the following line.
 * <p>
 * 
 * Since this is a GUI application, an event-driven programming strategy is
 * used. The design includes the three basic steps necessary for event-driven
 * programming. First, the extended <code>JFrame</code> signature includes the
 * clause <code>implements KeyListener</code>. Second, the method
 * <code>addKeyListener</code> is invoked on the text area object. This
 * registers the text area to listen for key events. Third, an implementation is
 * provided for each of the three <code>KeyListener</code> methods.
 * <p>
 * 
 * The work is performed in the <code>keyPressed</code> method, while dummy
 * (i.e., empty) implementations are provided for <code>keyReleased</code> and
 * <code>keyTyped</code>.
 * <p>
 * 
 * Screen snap...<br>
 * <center><img src = "img/DemoTranslateEnglishGUI-1.gif"></center>
 * <p>
 * 
 * @see <a href="../DemoTranslateEnglishGUI.java">source code</a>
 * @author Scott MacKenzie, 2001
 */
public class test_FontSizes {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {

		JFrame frame = new TestingFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(frame.getClass().getName());
		//frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true); // alternative: frame.show()
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.requestFocusInWindow();

		Dimension totalRealEstate = new Dimension(frame.getSize().width,
				(int) Math.floor(frame.getSize().height
						* (1 - TestingFrame.paramModel
								.getKeyboardRealEstateProportion())));
		System.out.println(totalRealEstate);
		TestingFrame.fontDisplayer.setSize(totalRealEstate);
		TestingFrame.fontDisplayer.setScreenSize(totalRealEstate);
		TestingFrame.fontDisplayer.setPreferredSize(totalRealEstate);
		// TestingFrame.fontDisplayer.setMinimumSize(totalRealEstate);
		// TestingFrame.fontDisplayer.setMaximumSize(totalRealEstate);
		//

		AlphabeticET_MeaganSubset_2Level et = new AlphabeticET_MeaganSubset_2Level();
		TestingFrame.fontDisplayer.setDisplayFontAsLargesAsPossible(et
				.getRootOfEncodingTree().getLeaves());

	}
}

class TestingFrame extends JFrame implements KeyListener {

	final String BANNER = "JTextDisplayerWithFontInformation\n";
	// final String PROMPT = "Watch here for instructions:\n";

	static IndirectSelectionFacilityInvocationParameterModel paramModel;

	static JTextDisplayerWithFontInformation fontDisplayer;
	// private MyProgressBar progressBar;

	long startTime;

	public TestingFrame() {

		final String PARAM_FILE = "isf-parameters-game.txt";

		final String ISF_PARAMETER_FILENAME = UserSpecificModel
				.getDefaultDirectoryForParameterFiles()
				+ PARAM_FILE;

		UserSpecificModel um = new IndirectSelectionFacilityUserModel(
				ISF_PARAMETER_FILENAME);
		paramModel = (IndirectSelectionFacilityInvocationParameterModel) um
				.getInvocationParameters();

		fontDisplayer = new JTextDisplayerWithFontInformation(paramModel);

		this.addWindowListener(new WindowCloser());
		fontDisplayer.addKeyListener(this);
		this.addKeyListener(this);

		// ------------------
		// arrange components
		// ------------------

		// add component to panel

		Container c = getContentPane();
		//c.setSize(fontDisplayer.getScreenSize());
		c.add(fontDisplayer, "North");

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

		fontDisplayer.insertStringImmediatelyAfterCaret("" + ke.getKeyChar());
		fontDisplayer.repaint();
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
