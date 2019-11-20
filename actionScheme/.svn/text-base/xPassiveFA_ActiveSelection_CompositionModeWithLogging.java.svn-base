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
 * space for select, up cursor for ascend, p for toggling pause/unpause
 * 
 * @author mb
 * 
 */
public class xPassiveFA_ActiveSelection_CompositionModeWithLogging extends
		ActionSchemeUsingOneInputDevice {

	// private final int ET_SELECT = KeyEvent.VK_SPACE;
	// private final int ET_ASCEND = KeyEvent.VK_UP;
	// private final int TOGGLE_FA = KeyEvent.VK_P;
	
	private ModeOfOperation modeOfOperation = new CompositionMode_ExplicitStartForFirstTraversal_ExplicitLoggingStart();


	// META_KEY = KeyEvent.VK_ENTER to start logging;

	public xPassiveFA_ActiveSelection_CompositionModeWithLogging(
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

}
