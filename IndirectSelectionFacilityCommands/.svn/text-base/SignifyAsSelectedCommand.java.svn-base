package IndirectSelectionFacilityCommands;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Logger;

import treeDataStructure.SelectionGroup;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

public class SignifyAsSelectedCommand extends IndirectSelectionFaciltyCommand {
	Logger log = Logger.getLogger("voca.vocaApp");

	private final String DELIM_CHAR = "*";

	private SelectionGroup sg;
	private int childIndex;

	public SignifyAsSelectedCommand(int childIndex) {
		// this.sg = sg;
		this.childIndex = childIndex;
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		List<SelectionGroup> list2 = tcf.getEncodingTree().getFokusCycle();
				
		list2.get(childIndex).changeBackgroundColour(Color.RED);
		return false;
	}

	public String toString() {
		return "SignifyAsSelected: " + sg;
	}

}
