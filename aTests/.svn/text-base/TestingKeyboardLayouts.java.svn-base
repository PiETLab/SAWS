package aTests;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import javax.swing.JFrame;


import IndirectSelectionFacility.OnScreenKeyboardView;

public class TestingKeyboardLayouts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String[] s = { "-kb VenkatagiriKeyboardLayout_A1" };
		 String[] s = { "-kb VenkatagiriKeyboardLayout_A2A5" };
		//String[] s = { "-kb VenkatagiriKeyboardLayout_A3A6" };
		// String[] s = { "-kb VenkatagiriKeyboardLayout_A4" };
		IndirectSelectionFacilityInvocationParameterModel paramManager = new IndirectSelectionFacilityInvocationParameterModel(
				s);
		OnScreenKeyboardView kb = new OnScreenKeyboardView(paramManager
				.getKeyboardVariant());
		JFrame f = new JFrame();
		f.setContentPane(kb);
		f.pack();
		f.setVisible(true);

		//kb.pack();
		//kb.setVisible(true);
	}

}
