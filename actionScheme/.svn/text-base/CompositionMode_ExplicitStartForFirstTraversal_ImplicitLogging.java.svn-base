package actionScheme;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import IndirectSelectionFacilityCommands.AppendRandomlySelectedCharacterCommand;
import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.EncodingTreeAscend;
import IndirectSelectionFacilityCommands.EncodingTreeAttemptDescend;
import IndirectSelectionFacilityCommands.LaunchFocusAdvancementCommand;
import IndirectSelectionFacilityCommands.ToggleAdvanceFocusGeneratorOnOffCommand;
import experimentHelper.LoggingStateModel;
import experimentHelper.SelectMatchForPresentedTargetStateModel;

public class CompositionMode_ExplicitStartForFirstTraversal_ImplicitLogging
		extends ModeOfOperation {

	LoggingStateModel loggingStateModel; // = new

	public CompositionMode_ExplicitStartForFirstTraversal_ImplicitLogging() {
		loggingStateModel = new LoggingStateModel();
	}

	public ActionEvent doCommand(KeyEvent ke,
			ActionSchemeUsingOneInputDevice actionScheme, boolean isFocusAtRoot) {
		ActionEvent actionEvent = null;
		if (ke.getKeyCode() == actionScheme.ET_SELECT) {
			if (loggingStateModel.getState() == LoggingStateModel.STATE_IS_LOGGING) {
				// System.out.println("logging, descend");
				actionEvent = new ActionEvent(new EncodingTreeAttemptDescend(), 0, "");
			} else if (loggingStateModel.getState() == LoggingStateModel.STATE_IS_NOT_LOGGING) {
				System.out.println("not logging, start logger");
				try {
					loggingStateModel.getStartLoggerCommand(
							actionScheme.getParamManager()).execute(
							actionScheme.isfController, null);
					loggingStateModel
							.setState(LoggingStateModel.STATE_IS_LOGGING);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// now that the logger is started, launch the focus advancement
				// System.out.println("logging, start FA");
				actionEvent = new ActionEvent(
						new ToggleAdvanceFocusGeneratorOnOffCommand(), 0, "");
			}
		} else if (ke.getKeyCode() == actionScheme.ET_ASCEND) {
			// System.out.println("ascend " + isFocusAtRoot);
			if (!isFocusAtRoot) {
				actionEvent = new ActionEvent(new EncodingTreeAscend(), 0, "");
			} else if (isFocusAtRoot) {
				actionEvent = new ActionEvent(new DeleteCommand(), 0, "");
			}
		} else if (ke.getKeyCode() == actionScheme.TOGGLE_FA) {
			actionEvent = new ActionEvent(
					new ToggleAdvanceFocusGeneratorOnOffCommand(), 0, "");
		}
		return actionEvent;

	}
}
