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
import customGUIComponentsISF.JTextDisplayerWithFontInformation;
import encodingTrees.AlphabeticET_MeaganSubset_2Level;

/**
 */
public class test_ScreenDimensions {

	public static void main(String[] args) {

		Dimension totalRealEstate = new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

		System.out.println(totalRealEstate);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {

		JFrame frame = new TestingFrame2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(frame.getClass().getName());
		frame.pack();
		// frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true); // alternative: frame.show()

		AlphabeticET_MeaganSubset_2Level et = new AlphabeticET_MeaganSubset_2Level();
		TestingFrame2.fontDisplayer.setDisplayFontAsLargesAsPossible(et.getRootOfEncodingTree().getLeaves());

		System.out.println(frame.getSize());

	}
}

class TestingFrame2 extends JFrame implements KeyListener {

	final String BANNER = "JTextDisplayerWithFontInformation\n";
	// final String PROMPT = "Watch here for instructions:\n";

	static IndirectSelectionFacilityInvocationParameterModel paramModel;

	static JTextDisplayerWithFontInformation fontDisplayer;
	// private MyProgressBar progressBar;

	long startTime;

	public TestingFrame2() {

		final String PARAM_FILE = "isf-parameters-game.txt";

		final String ISF_PARAMETER_FILENAME = UserSpecificModel.getDefaultDirectoryForParameterFiles() + PARAM_FILE;

		UserSpecificModel um = new IndirectSelectionFacilityUserModel(ISF_PARAMETER_FILENAME);
		paramModel = (IndirectSelectionFacilityInvocationParameterModel) um.getInvocationParameters();

		int NUM_PIXELS_FOR_WINDOW_DECORATION = 0;

		int heightOfKeyboard = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()
				* paramModel.getKeyboardRealEstateProportion());
		heightOfKeyboard = 0;

		Dimension availableRealEstateForGlossWidget = new Dimension(
				(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - heightOfKeyboard
						- NUM_PIXELS_FOR_WINDOW_DECORATION);

//		fontDisplayer = new JTextDisplayerWithFontInformation(
//				availableRealEstateForGlossWidget, paramModel);
		fontDisplayer = new JTextDisplayerWithFontInformation(paramModel);

		this.addWindowListener(new WindowCloser());
		fontDisplayer.addKeyListener(this);
		this.addKeyListener(this);

		// ------------------
		// arrange components
		// ------------------

		// add component to panel

		Container c = getContentPane();
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
