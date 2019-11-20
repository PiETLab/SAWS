package actionScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import experimentHelper.ExperimentHelper;
import experimentHelper.SelectMatchForPresentedTargetStateModel;

import IndirectSelectionFacilityCommands.AppendRandomlySelectedCharacterCommand;
import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.EncodingTreeAscend;
import IndirectSelectionFacilityCommands.EncodingTreeAttemptDescend;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import IndirectSelectionFacilityCommands.LaunchFocusAdvancementCommand;
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
 * input action #4 results in the selection of the node that is in focus -->
 * reinit to first state, do not reinit fokus state
 * 
 * TRY THIS ONLY WITH 1-LEVEL ENCODING TREES
 * 
 * space for select, up cursor for ascend, p for toggling pause/unpause
 * 
 * @author mb
 * 
 */
public class PassiveFA_ActiveSelection_GameMode extends
		ActionSchemeUsingOneInputDevice {

	private ModeOfOperation modeOfOperation = new GameMode();

	// META_KEY = KeyEvent.VK_ENTER to start logging;

	// VisionAssessmentExperimentHelper experimentHelper;

	public PassiveFA_ActiveSelection_GameMode(
			IndirectSelectionFacilityInvocationParameterModel paramManager,
			IndirectSelectionFacilityController isfController) {
		super(paramManager, isfController);
	}

	@Override
	public ActionEvent interpretKeyPress(KeyEvent ke, boolean isFocusAtRoot) {
		ActionEvent actionEvent = modeOfOperation.doCommand(ke, this,
				isFocusAtRoot);
		return actionEvent;
	}

	@Override
	public ActionEvent interpretKeyRelease(KeyEvent ke) {
		return null;
	}

	public boolean shouldAutomaticallyReinitializeFokusAfterCompleteTraversal() {
		return false;
	}

	public boolean shouldAutomaticallyReinitializeGlossAfterCompleteTraversal() {
		return true;
	}

}
