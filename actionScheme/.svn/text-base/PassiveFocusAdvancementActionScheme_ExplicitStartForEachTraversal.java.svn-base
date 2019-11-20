package actionScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import experimentHelper.ExperimentHelper;

import IndirectSelectionFacilityCommands.AppendRandomlySelectedCharacterCommand;
import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.EncodingTreeAscend;
import IndirectSelectionFacilityCommands.EncodingTreeAttemptDescend;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import IndirectSelectionFacilityCommands.StartLoggerAndLaunchFocusAdvancementCommand;
import IndirectSelectionFacilityCommands.StopLoggerCommand;
import IndirectSelectionFacilityCommands.ToggleAdvanceFocusGeneratorOnOffCommand;

/**
 * Enter to start logging, shift-Q to stop logging (but doesn't quit
 * application)
 * 
 * input action #1 causes randomly selected symbol from the selection set to
 * appear in the window
 * 
 * input action #2 causes a selection group (which one??) to appear, but with no
 * focus advancement
 * 
 * input action #3 causes focus advancement to start
 * 
 * input action #4 results in the selection of the node that is in focus
 * 
 * 
 * TRY THIS ONLY WITH 1-LEVEL ENCODING TREES
 * 
 * 
 * space for select, up cursor for ascend, p for toggling pause/unpause
 * 
 * @author mb
 * 
 */
public class PassiveFocusAdvancementActionScheme_ExplicitStartForEachTraversal
		extends ActionSchemeUsingOneInputDevice {


	public PassiveFocusAdvancementActionScheme_ExplicitStartForEachTraversal(
			IndirectSelectionFacilityInvocationParameterModel paramManager,
			IndirectSelectionFacilityController isfController) {
		super(paramManager, isfController);

	}

	@Override
	public ActionEvent interpretKeyPress(KeyEvent ke, boolean isFocusAtRoot) {
		ActionEvent actionEvent = null;
		if (ke.getKeyCode() == ET_SELECT) {
			if (super.isfController.isFokusAdvancementActive()) {
				actionEvent = new ActionEvent(new EncodingTreeAttemptDescend(), 0, "");
			} else {
				actionEvent = new ActionEvent(
						new ToggleAdvanceFocusGeneratorOnOffCommand(), 0, "");
			}
		} else if (ke.getKeyCode() == ET_ASCEND) {
			// System.out.println("ascend " + isFocusAtRoot);
			if (!isFocusAtRoot) {
				actionEvent = new ActionEvent(new EncodingTreeAscend(), 0, "");
			} else if (isFocusAtRoot) {
				actionEvent = new ActionEvent(new DeleteCommand(), 0, "");
			}
		} else if (ke.getKeyCode() == TOGGLE_FA) {
			actionEvent = new ActionEvent(
					new ToggleAdvanceFocusGeneratorOnOffCommand(), 0, "");
		}
		return actionEvent;
	}

	private boolean isExperimentStartKeyEvent(KeyEvent keyEvent) {
		return keyEvent.getKeyChar() == ExperimentHelper.getStartKey();
	}

	private boolean isExperimentStopKeyEvent(KeyEvent keyEvent) {
		return keyEvent.getKeyChar() == ExperimentHelper.getStopKey()
				&& keyEvent.isShiftDown();
	}

	@Override
	public ActionEvent interpretKeyRelease(KeyEvent ke) {
		return null;
	}

	public boolean shouldAutomaticallyReinitializeFokusAfterCompleteTraversal() {
		return false;
	}

}
