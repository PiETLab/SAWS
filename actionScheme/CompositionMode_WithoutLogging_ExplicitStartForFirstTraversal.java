package actionScheme;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import IndirectSelectionFacilityCommands.AppendRandomlySelectedCharacterCommand;
import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.EncodingTreeAscend;
import IndirectSelectionFacilityCommands.EncodingTreeAttemptDescend;
import IndirectSelectionFacilityCommands.LaunchFocusAdvancementCommand;
import IndirectSelectionFacilityCommands.ToggleAdvanceFocusGeneratorOnOffCommand;
import experimentHelper.SelectMatchForPresentedTargetStateModel;

public class CompositionMode_WithoutLogging_ExplicitStartForFirstTraversal extends
		ModeOfOperation {

	public CompositionMode_WithoutLogging_ExplicitStartForFirstTraversal() {
	}

	public ActionEvent doCommand(KeyEvent ke, ActionSchemeUsingOneInputDevice actionScheme,
			boolean isFocusAtRoot) {

		ActionEvent actionEvent = null;
		if (ke.getKeyCode() == actionScheme.ET_SELECT) {
			if (actionScheme.isfController.isFokusAdvancementActive()) {
				actionEvent = new ActionEvent(new EncodingTreeAttemptDescend(), 0, "");
			} else {
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
