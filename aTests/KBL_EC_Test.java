package aTests;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.io.PrintStream;

import coreIndirectSelectionFacility.IndirectSelectionFacilityView;

import IndirectSelectionFacility.OnScreenKeyboardView;

public class KBL_EC_Test {
	PrintStream output = System.out;


	public static void main(String[] args0) {
		String[] args = { "-kb VenkatagiriKeyboardLayout_A1 -eh false" };
		IndirectSelectionFacilityInvocationParameterModel paramManager = new IndirectSelectionFacilityInvocationParameterModel(
				args);
		IndirectSelectionFacilityView isf = new IndirectSelectionFacilityView(paramManager);
		isf.pack();
		isf.setVisible(true);
	}
}
