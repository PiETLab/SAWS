package actionScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import experimentHelper.ExperimentHelper;
import experimentHelper.LoggingStateModel;

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
public class PassiveFA_ActiveSelection_CompositionMode_AutomaticStartForTraversal_ImplicitLogging
		extends ActionSchemeUsingOneInputDevice {

	// private ModeOfOperation modeOfOperation = new
	// CompositionMode_WithoutLogging_ExplicitStartForFirstTraversal();
	private CompositionMode_AutoStartForTraversals_ImplicitLogging modeOfOperation = new CompositionMode_AutoStartForTraversals_ImplicitLogging();

	public PassiveFA_ActiveSelection_CompositionMode_AutomaticStartForTraversal_ImplicitLogging(
			IndirectSelectionFacilityInvocationParameterModel paramManager,
			IndirectSelectionFacilityController isfController) {
		super(paramManager, isfController);
		try {
			modeOfOperation.getLoggingStateModel().getStartLoggerCommand(
					paramManager).execute(isfController, null);
			modeOfOperation.getLoggingStateModel().setState(
					LoggingStateModel.STATE_IS_LOGGING);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

}
