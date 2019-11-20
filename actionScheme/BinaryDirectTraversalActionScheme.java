package actionScheme;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintStream;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import experimentHelper.ExperimentHelper;

import IndirectSelectionFacilityCommands.DeleteCommand;
import IndirectSelectionFacilityCommands.EncodingTreeAscend;
import IndirectSelectionFacilityCommands.EncodingTreeDescendWithDirectAction;
import IndirectSelectionFacilityCommands.ExperimentHelperStartCommand;
import IndirectSelectionFacilityCommands.SignifyAsSelectedCommand;

public class BinaryDirectTraversalActionScheme extends
		ActionSchemeUsingTwoInputDevices {

	private final int ET_SELECT_LEFT = KeyEvent.VK_A;
	private final int ET_SELECT_RIGHT = KeyEvent.VK_L;
	private final int ET_ASCEND = KeyEvent.VK_UP;

	PrintStream output = System.out;

	public BinaryDirectTraversalActionScheme(
			IndirectSelectionFacilityInvocationParameterModel paramManager,
			IndirectSelectionFacilityController isfController) {
		super(paramManager, isfController);
	}

	public ActionEvent interpretKeyPress(KeyEvent ke, boolean isFocusAtRoot) {
		// System.out.println("Key Press");
		ActionEvent actionEvent = null;
		if (ke.getKeyCode() == ET_SELECT_LEFT) {
			System.out.println("Descend left");
			int childIndex = 0;
			actionEvent = new ActionEvent(new SignifyAsSelectedCommand(
					childIndex), 0, "");
		} else if (ke.getKeyCode() == ET_SELECT_RIGHT) {
			System.out.println("Descend right");
			int childIndex = 1;
			actionEvent = new ActionEvent(new SignifyAsSelectedCommand(
					childIndex), 0, "");
		} else if (ke.getKeyCode() == ET_ASCEND) {
			// System.out.println("ascend " + isFocusAtRoot);
			if (!isFocusAtRoot) {
				actionEvent = new ActionEvent(new EncodingTreeAscend(), 0, "");
			} else if (isFocusAtRoot) {
				actionEvent = new ActionEvent(new DeleteCommand(), 0, "");
			}
		}
		return actionEvent;
	}

	public ActionEvent interpretKeyRelease(KeyEvent ke) {
		// System.out.println("Key Release"
		// + ExperimentHelper.metaKeyHasBeenPressed());
		ActionEvent ae3 = null;
		if (ke.getKeyCode() == ET_SELECT_LEFT) {
			// && ExperimentHelper.metaKeyHasBeenPressed()) {
			output.println("Descend left");
			ae3 = new ActionEvent(new EncodingTreeDescendWithDirectAction(0),
					0, "");
		} else if (ke.getKeyCode() == ET_SELECT_RIGHT) {
			// && ExperimentHelper.metaKeyHasBeenPressed()) {
			output.println("Descend right");
			ae3 = new ActionEvent(new EncodingTreeDescendWithDirectAction(1),
					0, "");
		} else if (ExperimentHelper.isTurnedOn()
				&& ke.getKeyChar() == ExperimentHelper.getStartKey()
				&& ExperimentHelper.metaKeyEnabled()) {
			System.out.println("start");
			ExperimentHelper.setMetaKeyPressed(true);
			ExperimentHelper.setMetaKeyEnabled(false);
			ae3 = new ActionEvent(new ExperimentHelperStartCommand(super
					.getParamManager().getUserId()), 0, "");
			((ActionListener) this).actionPerformed(ae3);
			// ActionEvent ae4 = new ActionEvent(
			// new EncodingTreePresentInitialView(), 0, "");
			// ((ActionListener) this).actionPerformed(ae4);
		}
		return ae3;
	}

	@Override
	public boolean shouldAutomaticallyReinitializeFokusAfterCompleteTraversal() {
		return false;
	}
}
