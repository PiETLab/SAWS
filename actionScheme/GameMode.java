package actionScheme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import IndirectSelectionFacilityCommands.AppendRandomlySelectedCharacterCommand;
import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.EncodingTreeAscend;
import IndirectSelectionFacilityCommands.EncodingTreeAttemptDescend;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import IndirectSelectionFacilityCommands.LaunchFocusAdvancementCommand;
import IndirectSelectionFacilityCommands.PlaySuccessClip;
import IndirectSelectionFacilityCommands.ToggleAdvanceFocusGeneratorOnOffCommand;
import experimentHelper.LoggingStateModel;
import experimentHelper.SelectMatchForPresentedTargetStateModel;

/**
 * 
 * input action #1 causes randomly selected symbol from the selection set to
 * appear in the window. the first time this is done, logging is started
 * 
 * input action #2 causes a selection group (which one??) to appear, but with no
 * focus advancement
 * 
 * input action #3 causes focus advancement to start
 * 
 * input action #4 results in the selection of the node that is in focus -->
 * reinit to first state, do not reinit fokus state
 * 
 * HOW TO STOP???
 * 
 * @author mb
 * 
 */
public class GameMode extends ModeOfOperation {

	SelectMatchForPresentedTargetStateModel gameStateModel; // = new
	LoggingStateModel loggingStateModel; // = new

	private String target = "";

	// VisionAssessmentExperimentHelper();

	public GameMode() {
		gameStateModel = new SelectMatchForPresentedTargetStateModel();
		loggingStateModel = new LoggingStateModel();
	}

	public ActionEvent doCommand(KeyEvent ke,
			ActionSchemeUsingOneInputDevice actionScheme, boolean isFocusAtRoot) {

		ActionEvent actionEvent = null;

		/**
		 * This is a bit kludgey. When one iteration of the game is drawing to
		 * an end, the gameStateModel can't know directly whether the user has
		 * made a selection when in STATE_SHOWING_POTENTIAL_MATCHES, it can only
		 * know indirectly because the focusAdvancement has been stopped (which
		 * is caused by the Descend command, when a leaf is reached). This is
		 * convoluted.
		 * 
		 */
		if (!actionScheme.isfController.isFokusAdvancementActive()
				&& gameStateModel.getState() == SelectMatchForPresentedTargetStateModel.STATE_SHOWING_POTENTIAL_MATCHES) {
			gameStateModel
					.setState(SelectMatchForPresentedTargetStateModel.STATE_INIT_PAUSED);

			// can check to see whether just-made selection was the correct one
			String hit = actionScheme.isfController.getMostRecentlyAppended();
			System.out.println("Target: " + target + " hit: " + hit);
			if (target.equals(hit)) {
				System.out.println("Target hit!");
//				PlaySuccessClip command = new PlaySuccessClip(
//						actionScheme.isfController);
//
//				ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
//				((ActionListener) actionScheme.isfController)
//						.actionPerformed(ae2);

				// actionScheme.isfController.clearGloss();
				// actionScheme.isfController.appendToGloss("Yipee");
				// try {
				// Thread.sleep(1000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
				// actionScheme.isfController.clearGloss();
				// actionScheme.isfController.clearGloss();

				// PlaySuccessClip theCommand = new PlaySuccessClip(
				// actionScheme.isfController);
				// theCommand.execute(actionScheme.isfController, null);
				// while (theCommand.getThread().isAlive()) {
				// // spin until thread terminates
				// }

			}

		}

		if (gameStateModel.getState() == SelectMatchForPresentedTargetStateModel.STATE_INIT_PAUSED) {
			if (ke.getKeyCode() == actionScheme.ET_SELECT) {
				if (loggingStateModel.getState() == LoggingStateModel.STATE_IS_NOT_LOGGING) {
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
				}
				AppendRandomlySelectedCharacterCommand theCommand = new AppendRandomlySelectedCharacterCommand(
						actionScheme.isfController);
				target = theCommand.getStringToAppend();
				System.out.println("Target: " + target);

				actionEvent = new ActionEvent(theCommand, 0,
						"AppendRandomlySelectedCharacterCommand");
				gameStateModel
						.setState(SelectMatchForPresentedTargetStateModel.STATE_SHOWING_PROMPT);
				// System.out.println("ae: " + actionEvent);
			}
		} else if (gameStateModel.getState() == SelectMatchForPresentedTargetStateModel.STATE_SHOWING_PROMPT) {
			if (ke.getKeyCode() == actionScheme.ET_SELECT) {
				actionEvent = new ActionEvent(
						new LaunchFocusAdvancementCommand(actionScheme
								.getParamManager().getUserId()), 0,
						"LaunchFocusAdvancementCommand");
				gameStateModel
						.setState(SelectMatchForPresentedTargetStateModel.STATE_SHOWING_POTENTIAL_MATCHES);
			}
		} else if (gameStateModel.getState() == SelectMatchForPresentedTargetStateModel.STATE_SHOWING_POTENTIAL_MATCHES) {
			if (ke.getKeyCode() == actionScheme.ET_SELECT) {
				actionEvent = new ActionEvent(new EncodingTreeAttemptDescend(),
						0, "");
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
			}
		}

		return actionEvent;
	}
}
