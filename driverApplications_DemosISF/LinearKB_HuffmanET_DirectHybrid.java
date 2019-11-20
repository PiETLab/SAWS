package driverApplications_DemosISF;

import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;

public class LinearKB_HuffmanET_DirectHybrid {

	public static void main(String[] args) {
		// String args2 = "-eh false -kb VenkatagiriKeyboardLayout_A1 -fa
		// passive -te et_kb -ec HuffmanEqualCosts -ea 2";

		String args2 = "-eh false -fa direct -te et_kb "
				+ "-ec HuffmanEqualCosts -ea 2 -kb SingleRowKB";
		IndirectSelectionFacilityLauncher.start(args2);
	}

}
