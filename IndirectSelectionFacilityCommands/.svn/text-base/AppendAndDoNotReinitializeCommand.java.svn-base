package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.swing.JFrame;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class AppendAndDoNotReinitializeCommand extends
		IndirectSelectionFaciltyCommand implements Serializable {

	String stringToAppend;

	public AppendAndDoNotReinitializeCommand(String _charToAppend) {
		stringToAppend = _charToAppend;
	}

	public AppendAndDoNotReinitializeCommand(String _charToAppend, Object source) {
		stringToAppend = _charToAppend;
		// super
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		// System.out.print("TRYING TO APPEND");
		tcf.appendToGloss(stringToAppend);

		getLogger()
				.fine(
						"1\t"
								+ ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED);
		getLogger().finer(
				ISFLoggerServices
						.getLoggingTokenForLeafSelection(stringToAppend));
		//
		// getLogger().finer(
		// ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED
		// + "(" + stringToAppend + ")");

		if (tcf.isWithPassiveFocusAdvancement()) {
			tcf.pausePassiveFocusAdvancement();
		}

		tcf.resetFokus();

		// start of kludge

		// MyRunnable runnable = new MyRunnable(tcf);
		// new Thread(runnable).start();

		// tcf.clearGloss();
		// IndirectSelectionFaciltyCommand command = new
		// AppendRandomlySelectedCharacterCommand();
		// ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
		// ((ActionListener) tcf).actionPerformed(ae2);
		// Runnable doWait = new Runnable() {
		// public void run() {
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// };
		// new Thread(doWait).start();
		//
		// // //// end of kludge

		if (tcf.isWithPassiveFocusAdvancement()) {
			// tcf.restartPassiveFocusAdvancement();
			// PassiveFokusAdvancerSwing.initToStartOfFokusCycle2();
			tcf.initToStartOfFokusCycleAndLaunch();
		}
		//
		// if (ExperimentHelper.isTurnedOn()) {
		// if (ExperimentHelper.isTargetHighlightingOn()) {
		// // we reset the colour of the button that is the target
		// tcf.getView().onScreenKeyboard.keyboard.getButtonByLabel(
		// ExperimentHelper.getCurrentChar()).resetTextColor();
		// }
		// // if (!charToAppend.equals(Selectable.VK_DEL)) {
		// ExperimentHelper.incrementCharIndex();
		// // }
		// // is current target text completed?
		// if (ExperimentHelper.hasMoreChars()) {
		// tcf.getView().targetArea.setText(ExperimentHelper
		// .getTargetTextHTMLized());
		// ExperimentHelper
		// .highlightTarget(tcf.getView().onScreenKeyboard.keyboard);
		// } else {
		//
		// tcf.getView().onScreenKeyboard.resetAppearance();
		// if (InvocationParameterManager.isWithPassiveFocusAdvancement()) {
		// PassiveFokusAdvancerSwing.pauseEventGenerator();
		// }
		// tcf.getView().compositionArea
		// .setText(tcf.getView().DEFAULT_CARAT);
		// ExperimentHelper.advanceListIndex();
		// if (ExperimentHelper.hasTargetText()) {
		// tcf.getView().targetArea.setText(ExperimentHelper
		// .getBetweenTaskMsg());
		// // ActionEvent ae2 = new ActionEvent(
		// // new ExperimentHelperStartCommand(), 0, "");
		// // ((ActionListener) tcf).actionPerformed(ae2);
		// ExperimentHelper.setMetaKeyEnabled(true);
		// ExperimentHelper.setMetaKeyPressed(false);
		// } else {
		// ActionEvent ae2 = new ActionEvent(
		// new ExperimentHelperStopCommand(), 0, "");
		// ((ActionListener) tcf).actionPerformed(ae2);
		// }
		// }
		// }

		// tcf.compositionArea.append(ae.getActionCommand());
		return false;
	}

	public String toString() {
		return "AppendCommand, char to append: " + stringToAppend;
	}

	public String getStringToAppend() {
		return stringToAppend;
	}

}
