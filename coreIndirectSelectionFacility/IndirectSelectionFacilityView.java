package coreIndirectSelectionFacility;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import buttonLayouts.ButtonLayoutSpecification;
import buttonLayouts.ButtonLayoutSpecificationGenerator;

import customGUIComponentsISF.JTextDisplayer;
import customGUIComponentsISF.JVOCAExpHelperWidget;
import customGUIComponentsISF.JVOCAInfoWidget;

import IndirectSelectionFacility.OnScreenKeyboardView;

import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;

import encodingTrees.AbstractEncodingTree;
import encodingTrees.EncodingTree;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;
import encodingTrees.obsolete.MyTraversableEncodingTree;
import encodingTrees.obsolete.MyTraversableEncodingTree2;
import encodingTrees.obsolete.TraversableEncodingTreeI;

/**
 * This class implements the visual display for a indirect selection facility.
 * 
 * The view requires (1) some sort of view of the on-screen indirect-selection
 * "keyboard" and (2) optionally, the "composition" area; that is, a area in
 * which the sequence of already-selected elements are displayed.
 * 
 * The view also has a small amount of real estate at the bottom of the frame
 * that displays certain pieces of information about the status of the system
 * (such as whether a passive focus advancer is being used). It is up to the
 * controller to implement what information is shown in the status displayer.
 * 
 * There are several alternatives for the display
 * 
 * default layout is for (1) to occupy the bottom half of the screen and for (2)
 * to occupy the top half of the screen.
 * 
 * Alternative layouts: (View b) reverse allocation of (1) and (2), so the
 * display is at the bottom and the "keyboard" is at the upper half.
 * 
 * View c is to show the "keyboard only" and does not allocate any real estate
 * for the composition area.
 * 
 * The view of the keyboard, whichever display variant is chosen, alway supports
 * "on-the-fly" alterations to the symbol set; the text label for each source
 * symbol can be changed dynamically.
 * 
 * 
 * @author M. Baljko, 2004
 */
public class IndirectSelectionFacilityView extends JFrame {

	private JTextDisplayer compositionArea;
	private JVOCAExpHelperWidget targetArea;
	private OnScreenKeyboardView onScreenKeyboard;
	// private TraversableEncodingTree encodingTree;

	private JVOCAInfoWidget infoPanel;
	private JPanel mainPanel;

	private static final boolean IS_VERBOSE = true;

	// private static Dimension DEFAULT_availableRealEstateForKB = new
	// Dimension(
	// (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
	// (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
	//
	// private static Dimension DEFAULT_availableRealEstateForGlossWidget = new
	// Dimension(
	// (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
	// (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

	// private IndirectSelectionFacilityController controller;

	// public JComponent PLACE_TO_PARK_FOCUS;// = compositionArea;

	// private GraphicsDevice device;

	/**
	 * onScreenKeyboard is passed as a param. Its size is already specified and
	 * the other components must take this into account
	 * 
	 * 
	 */
	public IndirectSelectionFacilityView(
			IndirectSelectionFacilityInvocationParameterModel paramManager,
			OnScreenKeyboardView onScreenKeyboard, GraphicsDevice device)
			throws IllegalArgumentException {
		super(device.getDefaultConfiguration());
		// this.device = device;

		this.setBackground(paramManager.getBackgroundColour());

		if (IS_VERBOSE)
			System.out.println("Begin creation of " + this.getClass().getName()
					+ " ...");
		this.setTitle(paramManager.getTitle());

		this.onScreenKeyboard = onScreenKeyboard;

		infoPanel = new JVOCAInfoWidget(paramManager);

		// boolean isWithFontHintsOn = paramManager.is
		compositionArea = new JTextDisplayer(paramManager);

		// Step 3: Arrange components in contentPane
		mainPanel = new JPanel();

		// mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		// mainPanel.setBackground(Color.BLUE);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		// mainPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		// panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		// if (paramManager.isWithLogging()) {
		// mainPanel.add(targetArea);
		// }
		if (paramManager.isKeyboardAtTop()) {
			mainPanel.add(onScreenKeyboard);
			if (paramManager.getKeyboardRealEstateProportion() != 1.00) {
				mainPanel.add(compositionArea);
			}
		} else {
			if (paramManager.getKeyboardRealEstateProportion() != 1.00) {
				mainPanel.add(compositionArea);
			}
			mainPanel.add(onScreenKeyboard);

		}

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("TCF+TTS", mainPanel);
		tabbedPane.setDoubleBuffered(true);
		// panel1.addFocusListener(this);
		// setContentPane(tabbedPane);

		setContentPane(mainPanel);

		if (IS_VERBOSE)
			System.out.println(this.getClass().getName()
					+ ": completed creation.  Size:" + this.getSize());
		// configureForDisplay();
	}

	/**
	 * Effect of this method is solely through side-effects to the UIManager.
	 * Needs to be invoked *BEFORE* any button components are instantiated.
	 * 
	 */
	public static void removeButtonDecorators() {
		// http://java.sun.com/developer/TechTips/txtarchive/2002/Sept24_JohnZ.txt

		// need to do this even before encoding tree instantiation, since
		// the source symbols are also Jbuttons, and they get instantiated
		// there; the UIManager needs to be updated before all of this
		Color DEFAULT_COLOUR = Color.WHITE;
		UIManager.put("Button.highlight", DEFAULT_COLOUR);
		UIManager.put("Button.darkShadow", DEFAULT_COLOUR);
		UIManager.put("Button.background", DEFAULT_COLOUR);
		UIManager.put("Button.shadow", DEFAULT_COLOUR);
		UIManager.put("Button.border", DEFAULT_COLOUR);
	}

	// public TraversableEncodingTree getEncodingTree() {
	// return encodingTree;
	// }

	public void configureForDisplay(boolean isFullScreen) {
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
			this.getGraphicsConfiguration().getDevice().setFullScreenWindow(
					this);
			// device.setFullScreenWindow(this);
			this.validate();
		} else {
			// Windowed mode
			this.pack();
			this.setVisible(true);
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.requestFocusInWindow();
		}

		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ " : total size of display: " + this.getSize());
			System.out.println(this.getClass().getName()
					+ " : OnScreenKeyboard of display: "
					+ onScreenKeyboard.getSize());
			System.out.println(this.getClass().getName()
					+ " : CompositionWidget size : "
					+ compositionArea.getSize());
		}
	}

	public JVOCAInfoWidget getInfoPanel() {
		return infoPanel;
	}

	public OnScreenKeyboardView getOnScreenKeyboard() {
		return onScreenKeyboard;
	}

	public JTextDisplayer getCompositionWidget() {
		return compositionArea;
	}


	// controller.getView().getCompositionWidget().setMinimumSize(
	// compositionWidgetRealEstate);
	// controller.getView().getOnScreenKeyboard().setMinimumSize(
	// keyboardRealEstate);
	// controller.getView().getCompositionWidget().setMaximumSize(
	// compositionWidgetRealEstate);
	// controller.getView().getOnScreenKeyboard().setMaximumSize(
	// keyboardRealEstate);

	// /**
	// *
	// * @param paramMgr
	// * @return list with 2 elements, the first is the onScreenKeyboard, the
	// * second is the ec
	// */
	// public static List<Object> generateKB_and_ET(
	// IndirectSelectionFacilityInvocationParameterModel paramManager,
	// Dimension availableRealEstateForKB) {
	// List<Object> objs = new Vector<Object>();
	// OnScreenKeyboardView onScreenKeyboard = null;
	//
	// TraversableEncodingTree encodingTree = null;
	//
	// String first;
	// String second;
	// if (paramManager.isEncodingTreeFromManualSpecification()) {
	// first = "onScreenKeyboard";
	// second = "encodingTree";
	// } else {
	// second = "onScreenKeyboard";
	// first = "encodingTree";
	// }
	// System.out.println("Generating " + first + " then " + second);
	//
	// TraversableEncodingTreeGenerator tetGen = new
	// TraversableEncodingTreeGenerator();
	// if (paramManager.isEncodingTreeFromManualSpecification()) {
	// KeyboardLayoutGenerator kbg = new KeyboardLayoutGenerator();
	// KeyboardDisplayScheme keyboard = kbg.createInstance(paramManager
	// .getKeyboardVariant());
	// onScreenKeyboard = new OnScreenKeyboardView(keyboard,
	// availableRealEstateForKB);
	//
	// // onScreenKeyboard = new OnScreenKeyboard(paramManager
	// // .getKeyboardVariant());
	// encodingTree = tetGen.createInstance(paramManager
	// .getETConstructTechnique(), onScreenKeyboard.getKeyboard());
	// } else if (paramManager.isETThenKB()) {
	// SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
	// SourceSymbolSet sourceSymbols = sssGen.createInstance(paramManager
	// .getSourceSymbolSet());
	// encodingTree = tetGen.createInstance(paramManager
	// .getETConstructTechnique(), sourceSymbols, paramManager
	// .getEncodingAlphabetSize());
	// KeyboardLayoutGenerator kbg = new KeyboardLayoutGenerator();
	// KeyboardDisplayScheme keyboard = kbg.createInstance(paramManager
	// .getKeyboardVariant(), encodingTree, sourceSymbols);
	// onScreenKeyboard = new OnScreenKeyboardView(keyboard,
	// availableRealEstateForKB);
	// }
	//
	// objs.add(onScreenKeyboard);
	// objs.add(encodingTree);
	// return objs;
	// }

	// public static List<Object> generateKB_and_ET(
	// IndirectSelectionFacilityInvocationParameterModel paramManager) {
	// List<Object> objs = new Vector<Object>();
	// OnScreenKeyboardView onScreenKeyboard = null;
	//
	// TraversableEncodingTree encodingTree = null;
	//
	// TraversableEncodingTreeGenerator tetGen = new
	// TraversableEncodingTreeGenerator();
	// if (paramManager.isEncodingTreeFromManualSpecification()) {
	// KeyboardLayoutGenerator kbg = new KeyboardLayoutGenerator();
	// KeyboardDisplayScheme keyboard = kbg.createInstance(paramManager
	// .getKeyboardVariant());
	// onScreenKeyboard = new OnScreenKeyboardView(keyboard);
	//
	// // onScreenKeyboard = new OnScreenKeyboard(paramManager
	// // .getKeyboardVariant());
	// encodingTree = tetGen.createInstance(paramManager
	// .getETConstructTechnique(), onScreenKeyboard.getKeyboard());
	// } else if (paramManager.isETThenKB()) {
	// SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
	// SourceSymbolSet sourceSymbols = sssGen.createInstance(paramManager
	// .getSourceSymbolSet());
	// encodingTree = tetGen.createInstance(paramManager
	// .getETConstructTechnique(), sourceSymbols, paramManager
	// .getEncodingAlphabetSize());
	// KeyboardLayoutGenerator kbg = new KeyboardLayoutGenerator();
	// KeyboardDisplayScheme keyboard = kbg.createInstance(paramManager
	// .getKeyboardVariant(), encodingTree, sourceSymbols);
	// onScreenKeyboard = new OnScreenKeyboardView(keyboard);
	// }
	//
	// objs.add(onScreenKeyboard);
	// objs.add(encodingTree);
	// return objs;
	// }

	/**
	 * Service offered by this class to tell the view to update itself, on the
	 * basis of the current state
	 */
	public void updateAppearance() {
		onScreenKeyboard.updateAppearance();
	}

	public void highlightCurrentSelectionGroupAsSelected() {
		onScreenKeyboard.highlightCurrentSelectionGroupAsSelected();
	}

	public JComponent getFocusReceivingComponent() {
		return onScreenKeyboard;
		// return compositionArea.getTextArea();
	}

	public void setCompositionAreaDisplayFontAsLargesAsPossible(
			List<SourceSymbol> list) {
		compositionArea.setDisplayFontAsLargesAsPossible(list);
	}

	public void makeEntireDisplayEmptyAndResetGloss() {
		onScreenKeyboard.makeDisplayEmpty();
		compositionArea.resetText();
		// onScreenKeyboard.updateAppearance();
	}

	public void repositionCaret(int newPosition) {
		compositionArea.repositionCaret(newPosition);
	}

	public void clearGloss() {
		compositionArea.clearGloss();
		// onScreenKeyboard.updateAppearance();
	}

}
