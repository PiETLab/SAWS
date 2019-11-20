package IndirectSelectionFacilityCommands;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import customGUIComponentsISF.JIndirectSelectionButton;
import experimentHelper.ExperimentHelper;

import sourceSymbolSet.SourceSymbol;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import treeDataStructure.SelectionGroup;

public class EncodingTreeAttemptDescend extends IndirectSelectionFaciltyCommand {

	/**
	 * Descent to the child node that is in focus
	 * 
	 */
	public EncodingTreeAttemptDescend() {
		super();
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		getLogger().fine("1\tSELECT");
		// getLogger().finer(
		// "S(" + tcf.getFokusTimerStatusForCurrentSelectionGroup() + ")");
		getLogger()
				.finer(
						ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_SELECT_ACTION
								+ "("
								+ tcf
										.getDurationOfTimeCurrentFocusGroupHasBeenInFocus()
								+ ")");

		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ ":  Action on in-focus selection group: "
					+ tcf.getCurrentSelectionGroup());

		}

		if (tcf.isWithPassiveFocusAdvancement()) {
			// tcf.pausePassiveFocusAdvancement();

			tcf.highlightCurrentSelectionGroupAsSelected();
			// System.out.println("pink");

			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			// try {
			// SwingUtilities.invokeAndWait(new Runnable() {
			// public void run() {
			// for (int i = 0; i < 5000; i++) {
			// }
			// }
			// });
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// } catch (InvocationTargetException e) {
			// e.printStackTrace();
			// }

		}

		if (tcf.isCurrentSelectionGroupTrivial()) {

			IndirectSelectionFaciltyCommand command = tcf
					.getCurrentISFCommand();

			ActionEvent ae2 = new ActionEvent(command, 0, "" + command);
			((ActionListener) tcf).actionPerformed(ae2);
			// tcf.resetFokus();
			//			
			// tcf.getView().getEncodingTree().resetFokus();// reset();
		} else {
			tcf.descendFokus();

			if (tcf.isWithPassiveFocusAdvancement()) {
				// ActionEvent ae3 = new ActionEvent(new PauseForABeatCommand(),
				// 0, "xxx");
				// ((ActionListener) tcf).actionPerformed(ae3);
				// tcf.restartPassiveFocusAdvancement();
				//
				// PassiveFokusAdvancerSwing.initToStartOfFokusCycle();
				// PassiveFokusAdvancerSwing.initToStartOfFokusCycle2();
				tcf.initToStartOfFokusCycleAndLaunch();
			}
		}

		return false;
	}
}
