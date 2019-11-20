package buttonLayouts;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import customGUIComponentsISF.JIndirectSelectionButton;
import displayScheme.DisplaySchemeForTraversableEncodingTreeStates;
import displayScheme.DisplaySchemeForTraversableEncodingTreeStatesGenerator;
import displayScheme.ShowAllAtOnceWithHighlighting;
import displayScheme.ShowOneByOneWholeTraversal;

import encodingTrees.TraversableEncodingTree;

import IndirectSelectionFacility.OnScreenKeyboardView;

/**
 * Provides services for clients so that they can render a display of a
 * "keyboard". A good keyboard display will enable the user to navigate the
 * encoding tree. If focus is used, then the focus state and state changes
 * should be reflected in the display. The client must explicitly invoke the
 * update method; if the focus state changes, then this will not be
 * automatically detected by this provider (TO DO: make the display controller
 * listen for focus changes of state).
 * 
 * 
 * @author mb
 * 
 */
public abstract class ButtonLayoutSpecification {

	// a component of the display scheme is how the focus state impacts the
	// display, but the display scheme requires knowledge of the encoding tree

	private DisplaySchemeForTraversableEncodingTreeStates displayScheme;
	private TraversableEncodingTree encodingTree;
	private OnScreenKeyboardView onScreenKeyboardRepresentation;
	private IndirectSelectionFacilityInvocationParameterModel paramModel;

	private final int NUM_ROWS = setNumRows();

	private final int NUM_COLS = setNumCols();

	private final int BUTTON_WIDTH = setButtonWidth();

	private final int BUTTON_HEIGHT = setButtonHeight();

	public abstract List<JIndirectSelectionButton> getAllEnabledButtons();

	public abstract List<JPanel> getRows();

	public abstract int setNumRows();

	public abstract int setNumCols();

	public abstract int setButtonWidth();

	public abstract int setButtonHeight();

	//MB trying to fix
	protected ButtonLayoutSpecification() {}
	
	// public abstract String getDescriptor();
	protected ButtonLayoutSpecification(TraversableEncodingTree encodingTree,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		this.encodingTree = encodingTree;
		this.paramModel = paramModel;
		// displayScheme = new ShowKeyboardWithAsFewElementsAsPossible(
		// encodingTree, this);

		// if (IS_SHOW_FULL_KEYBOARD) {
		// displayScheme = new ShowFullKeyboardAtAllTimes(encodingTree, this);
		// } else {
		// // displayScheme = new ShowMaxKeyboardInAvailableRealEstate(
		// // encodingTree, this);
		// displayScheme = new ShowKeyboardWithAsFewElementsAsPossible(
		// encodingTree, this);
		// }

	}

	public void setOnScreenKeyboardView(
			OnScreenKeyboardView onScreenKeyboardRepresentation) {
		this.onScreenKeyboardRepresentation = onScreenKeyboardRepresentation;
		// displayScheme = new ShowFullKeyboardAtAllTimes(encodingTree,
		// onScreenKeyboardRepresentation, fontFamilyToUse);
		// displayScheme = new ShowFullKeyboardAtAllTimes(encodingTree,
		// onScreenKeyboardRepresentation, fontFamilyToUse);
		// displayScheme = new ShowKeyboardWithAsFewElementsAsPossible(
		// encodingTree, onScreenKeyboardRepresentation, paramModel);
		DisplaySchemeForTraversableEncodingTreeStatesGenerator dg = new DisplaySchemeForTraversableEncodingTreeStatesGenerator();
		DisplaySchemeForTraversableEncodingTreeStates displayScheme2 = dg
				.createInstance(encodingTree, onScreenKeyboardRepresentation,
						paramModel);

		this.displayScheme = displayScheme2;

	}

	public int getButtonHeight() {
		return BUTTON_HEIGHT;
	}

	public int getButtonWidth() {
		return BUTTON_WIDTH;
	}

	public int getNumCols() {
		return NUM_COLS;
	}

	public int getNumRows() {
		return NUM_ROWS;
	}

	/**
	 * 
	 * This method returns the (first*) JVirtualKeyboardButton that is
	 * associated with the passed label.
	 * 
	 * *in case of non-unique JVirtualKeyboardButtons
	 * 
	 * The constructor for JVirtualKeyboardButton ensures that every button has
	 * a unique label
	 * 
	 * @param stringLabel
	 * @return
	 */
	public JIndirectSelectionButton getButtonByLabel(String stringLabel) {
		JIndirectSelectionButton foundBut = null;
		List<JIndirectSelectionButton> l = getAllEnabledButtons();
		for (JIndirectSelectionButton but : l) {
			System.out.println(but + " " + stringLabel);
			if (stringLabel.equals(but.getText()))
				foundBut = but;
		}
		return foundBut;
	}

	public void updateAppearance() {
		displayScheme.updateAppearance();
	}

	public void highlightCurrentSelectionGroupAsSelected() {
		displayScheme.highlightCurrentSelectionGroupAsSelected();
	}

	public void emptyPanel() {
		displayScheme.emptyDisplay();
	}

	public void resizeOptimizedComponents() {
		displayScheme.resizeOptimizedComponents();
	}


}