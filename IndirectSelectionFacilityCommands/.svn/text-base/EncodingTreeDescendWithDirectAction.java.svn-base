package IndirectSelectionFacilityCommands;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import customGUIComponentsISF.JIndirectSelectionButton;
import experimentHelper.ExperimentHelper;

import sourceSymbolSet.SourceSymbol;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import treeDataStructure.SelectionGroup;

public class EncodingTreeDescendWithDirectAction extends
		IndirectSelectionFaciltyCommand {

	private boolean isVerbose = true;

	private int childIndex;

	private PrintStream output = System.out;

	/**
	 * Descent to the child node that is given by index childIndex
	 * 
	 */
	public EncodingTreeDescendWithDirectAction(int childIndex) {
		this.childIndex = childIndex;
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		if (isVerbose) {
			System.out.println("Descending to child branch :" + childIndex);
		}

		// if (!InvocationParameterManager.isWithPassiveFocusAdvancement()) {

		SelectionGroup sg = tcf.getCurrentSelectionGroup();
		// sg.putInFokus();
		for (int i = 0; i < childIndex; i++) {
			getLogger().fine("1\tWAIT");
			getLogger().finer("W(" + sg.getFokusTimerStatus() + ")");
			sg.putOutOfFokus();
			tcf.getEncodingTree().advanceFokus();
			sg = tcf.getEncodingTree().getCurrentSelectionGroup();
			sg.putInFokus();
		}

		// sg.modify(Color.BLACK);
		// tcf.getView().repaint();

		// try {
		// Thread.sleep(4000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		// System.out.println(sg);
		getLogger().fine("1\tSELECT");
		// log.finer("S" + childIndex + "(" + sg.getFokusDuration() + ")");
		getLogger().finer("S(" + sg.getFokusTimerStatus() + ")");
		// System.out.println(sg.getFokusDuration());
		if (sg.isTrivial()) {
			System.out.println("selected trivial: " + sg);
			if (isVerbose) {
				output
						.println("JVirtualKeyboard: Action on trivial, in-focus selection group");
			}

			// tcf.getView().repaint();

			ActionEvent ae2 = new ActionEvent(sg.getFirst().getVOCACommand(),
					0, "");

			((ActionListener) tcf).actionPerformed(ae2);

			ActionEvent ae3 = new ActionEvent(
					new EncodingTreePresentInitialView(), 0, "");
			((ActionListener) tcf).actionPerformed(ae3);

		} else {
			// System.out.println("reset: " + s);
			List<SelectionGroup> fokusCycle1 = tcf.getEncodingTree()
					.getFokusCycle();

			for (SelectionGroup s : fokusCycle1) {
				// s.resetDefaultAppearance();
				s.startFokusTimerDuration();
				// System.out.println("reset: " + s);
			}
			if (isVerbose) {
				System.out
						.println("JVirtualKeyboard: Action on non-trivial, in-focus selection group");
			}
			tcf.descendFokus();
			System.out.println("descended focus.");
			// use etController is add'l info req'd +
			// tcf.getView().getEncodingTree().getFokusCycle());

			List<SelectionGroup> fokusCycle = tcf.getEncodingTree()
					.getFokusCycle();
			int i = 0;
			for (SelectionGroup s : fokusCycle) {
				s.changeBackgroundColour(super.coloursForFokusCycleElements[i]);
				i++;
			}
			for (SelectionGroup sg2 : tcf.getEncodingTree().getFokusCycle()) {
				sg2.startFokusTimerDuration();
			}

			// System.out.println(fokusCycle);

		}

		// }

		return false;
	}
}
