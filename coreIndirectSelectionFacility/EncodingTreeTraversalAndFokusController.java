package coreIndirectSelectionFacility;

import encodingTrees.Code;
import encodingTrees.TraversableEncodingTree;

import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import treeDataStructure.SelectionGroup;

import java.util.Random;

/**
 * 
 * @author M. Baljko, 2004
 */
public class EncodingTreeTraversalAndFokusController {

	Random randomGenerator = new Random();

	// private IndirectSelectionFacilityInvocationParameterModel paramManager;
	// private ActionScheme inputActionScheme = null;
	// private final boolean IS_VERBOSE = false;
	// private PrintStream output = System.out;
	private IndirectSelectionFacilityView view;
	private TraversableEncodingTree encodingTree;

	public EncodingTreeTraversalAndFokusController(
			IndirectSelectionFacilityView view,
			TraversableEncodingTree encodingTree) {
		this.view = view;
		this.encodingTree = encodingTree;
	}

	public void placeFocusCorrectly() {
		getView().getFocusReceivingComponent().requestFocus();
	}

	private IndirectSelectionFacilityView getView() {
		return view;
	}

	public void shiftFokusToNextSG() {
		// SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
		// sg.putOutOfFokus();
		getEncodingTree().advanceFokus();
		// sg = getEncodingTree().getCurrentSelectionGroup();
		// sg.putInFokus();

		getView().updateAppearance();
	}

	public void highlightCurrentSelectionGroupAsSelected() {
		this.getView().highlightCurrentSelectionGroupAsSelected();
		// this.getView().updateAppearance();
	}

	public void descendFokus() {
		getEncodingTree().descendFokus();
		// this.getView().updateAppearance();
	}

	public void resetFokus() {
		getEncodingTree().resetFokus();
		// this.getView().updateAppearance();
	}

	public boolean ascendFokus() {
		boolean result = getEncodingTree().ascendFokus();
		this.getView().updateAppearance();
		return result;
	}

	public boolean isFokusAtRootCycle() {
		return getEncodingTree().isFokusAtRootCycle();
	}

	public void putCurrentSelectionGroupInFocus() {
		SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
		// System.out.println(keyboard.getCurrentSelectionGroup());
		sg.putInFokus();
	}

	public TraversableEncodingTree getEncodingTree() {
		return encodingTree;
	}

	public String getFokusTimerStatus() {
		return getEncodingTree().getCurrentSelectionGroup()
				.getFokusTimerStatus();
	}

	public String getFokusTimerStatusForCurrentSelectionGroup() {
		SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
		return sg.getFokusTimerStatus();
	}

	public boolean isCurrentSelectionGroupTrivial() {
		SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
		return sg.isTrivial();
	}

	public IndirectSelectionFaciltyCommand getCurrentISFCommand() {
		SelectionGroup sg = getEncodingTree().getCurrentSelectionGroup();
		return sg.getFirst().getVOCACommand();
	}

	public SelectionGroup getCurrentSelectionGroup() {
		return getEncodingTree().getCurrentSelectionGroup();
	}

	public int getDurationOfTimeCurrentFocusGroupHasBeenInFocus() {
		return getEncodingTree()
				.getDurationOfgetCurrentSelectionGroupHasBeenInFocusAsOfNow();
	}

	public Code getCode() {
		return getEncodingTree().getCode();
	}

	public String getRandomlySelectedSymbol() {
		// generate random number from 0 to leaves-1
		int maxIndex = this.getEncodingTree().getRoot().getLeaves().size() - 1;
		int randomInt = randomGenerator.nextInt(maxIndex);
		String s = this.getEncodingTree().getRoot().getLeaves().get(randomInt)
				.getTextLabel();
		return s;
	}

	public void toggleCapsOnOff() {
		encodingTree.toggleCapsOnOff();
	}

	public void toggleCapsOn() {
		encodingTree.toggleCapsOn();
	}

	public void toggleCapsOff() {
		encodingTree.toggleCapsOff();
	}

}
