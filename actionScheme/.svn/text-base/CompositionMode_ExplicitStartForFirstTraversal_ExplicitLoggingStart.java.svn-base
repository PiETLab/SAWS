package actionScheme;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import IndirectSelectionFacilityCommands.AppendRandomlySelectedCharacterCommand;
import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.EncodingTreeAscend;
import IndirectSelectionFacilityCommands.EncodingTreeAttemptDescend;
import IndirectSelectionFacilityCommands.LaunchFocusAdvancementCommand;
import IndirectSelectionFacilityCommands.StartLoggerAndLaunchFocusAdvancementCommand;
import IndirectSelectionFacilityCommands.StopLoggerCommand;
import IndirectSelectionFacilityCommands.ToggleAdvanceFocusGeneratorOnOffCommand;
import experimentHelper.ExperimentHelper;
import experimentHelper.LoggingStateModel;
import experimentHelper.SelectMatchForPresentedTargetStateModel;

public class CompositionMode_ExplicitStartForFirstTraversal_ExplicitLoggingStart
		extends ModeOfOperation {

	LoggingStateModel loggingStateModel; // = new

	public CompositionMode_ExplicitStartForFirstTraversal_ExplicitLoggingStart() {
		loggingStateModel = new LoggingStateModel();
	}

	public ActionEvent doCommand(KeyEvent ke,
			ActionSchemeUsingOneInputDevice actionScheme, boolean isFocusAtRoot) {

		ActionEvent actionEvent = null;
		switch (loggingStateModel.getState()) {
		case LoggingStateModel.STATE_IS_NOT_LOGGING:
			if (this.isExperimentStartKeyEvent(ke)) {
				// actionEvent = new ActionEvent(
				// new StartLoggerAndLaunchFocusAdvancementCommand(
				// actionScheme.getParamManager().getUserId()), 0,
				// "StartLoggerCommand");
				actionEvent = loggingStateModel
						.getStartLoggerAndLaunchFocusAdvancementCommand(actionScheme
								.getParamManager());
				loggingStateModel.setState(LoggingStateModel.STATE_IS_LOGGING);
				// ExperimentHelper.setTrialStartedState(true);
			}
		case LoggingStateModel.STATE_IS_LOGGING:
			if (ke.getKeyCode() == actionScheme.ET_SELECT) {
				actionEvent = new ActionEvent(new EncodingTreeAttemptDescend(), 0, "");
			} else if (ke.getKeyCode() == actionScheme.ET_ASCEND) {
				// System.out.println("ascend " + isFocusAtRoot);
				if (!isFocusAtRoot) {
					actionEvent = new ActionEvent(new EncodingTreeAscend(), 0,
							"");
				} else if (isFocusAtRoot) {
					actionEvent = new ActionEvent(new DeleteCommand(), 0, "");
				}
			} else if (ke.getKeyCode() == actionScheme.TOGGLE_FA) {
				actionEvent = new ActionEvent(
						new ToggleAdvanceFocusGeneratorOnOffCommand(), 0, "");
			} else if (this.isExperimentStopKeyEvent(ke)) {
				//
				// IN ORDER TO STOP LOGGING (make it a tricky chord to avoid
				// accidental presses)
				//
				// actionEvent = new ActionEvent(new StopLoggerCommand(), 0,
				// "StopLoggerCommand");
				actionEvent = loggingStateModel.getStopLoggerCommand();

				loggingStateModel
						.setState(LoggingStateModel.STATE_IS_NOT_LOGGING);
				//
				// ExperimentHelper.setTrialStartedState(false);
			}
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

}
