package driverApplications_DemosISF;

import coreIndirectSelectionFacility.Driver;

public class Huffman_Meagan {

	public static void main(String[] args) {
		String args3 = "-eh false "
				+ "-kb RowColumnSizedForTwoLevelEncodingTree -vi  " + ""
				+ "-fa passive  -bd true -ff sansserif -kp 0.75" + "";

		String args2 = "-eh false "
				+ "-te alg "
				+ "-ec HuffmanEqualCosts "
				+ "-ea 6 "
				+ "-sl MeaganCompositionSet "
				+ "-di IndirectSelectionFacility.ShowKeyboardWithAsFewElementsAsPossible "
				+ "-di IndirectSelectionFacility.ShowFullKeyboardAtAllTimes "
				+ "-di IndirectSelectionFacility.ShowMaxKeyboardInAvailableRealEstate "
				+ "-kb MeaganKeyboardLayout -vi  " + ""
				+ "-fa passive  -rb true -ff sansserif -kp 0.30 -fs true -ha 0"
				+ "";

		Driver.launch(args2);
	}
}
