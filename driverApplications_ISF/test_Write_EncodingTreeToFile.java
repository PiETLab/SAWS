package driverApplications_ISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;

import java.util.List;
import java.util.Vector;

import SoftwareDeployment.UserSpecificModel;
import coreIndirectSelectionFacility.Driver;
import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;
import encodingTrees.Code;
import encodingTrees.CodeWord;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

public class test_Write_EncodingTreeToFile {

	public static void main(String[] args) {
		String args2 = "-el true "
				+ "-mt encodingTrees.UnigramBasedET_MeaganSubset_2Level -ec EncodingTreeFromManualSpecification "
				//+ "-mt encodingTrees.UnigramBasedET_2Level -ec EncodingTreeFromManualSpecification "
				+ "-di IndirectSelectionFacility.ShowMaxKeyboardInAvailableRealEstate "
				+ "-di IndirectSelectionFacility.ShowKeyboardWithAsFewElementsAsPossible "
				+ "-di IndirectSelectionFacility.ShowFullKeyboardAtAllTimes "
				+ "-kb RowColumnSizedForTwoLevelEncodingTree -vi  " + ""
				+ "-fa passive  -rb true -ff sansserif -kp 0.75" + "";

		UserSpecificModel um = new IndirectSelectionFacilityUserModel(args2,
				false);
		IndirectSelectionFacilityInvocationParameterModel paramModel = (IndirectSelectionFacilityInvocationParameterModel) um
				.getInvocationParameters();

		TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();
		TraversableEncodingTree encodingTree = tetGen
				.generateEncodingTree(paramModel);

		Code c = encodingTree.getCode();

		List<CodeWord> cw2 = new Vector<CodeWord>();

		for (CodeWord cw : c.getCodeWords()) {
			// getLogger().finer("R(" + cw.toString() + ")");
			System.out
					.println("1." + cw.getSourceSymbol().getClass().getName());
			System.out.println(cw.toStringParseableFormat());
			CodeWord cwCopy = new CodeWord(cw.toStringParseableFormat());
			System.out.println("2."
					+ cwCopy.getSourceSymbol().getClass().getName());
			cw2.add(cwCopy);
			System.out.println(cwCopy.toStringParseableFormat());
		}
		Code other = new Code(cw2);

		System.out.println();
		// Driver.launch(args2);

	}
}
