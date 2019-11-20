package customGUIComponentsISF;


import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class JVOCAInfoWidget extends JPanel {
	private JTextField kbVariantName, dwellTimeDisplay;
	private final int NUM_COLUMNS = 40;

	public JVOCAInfoWidget(IndirectSelectionFacilityInvocationParameterModel paramManager) {

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setSize(NUM_COLUMNS, 3);

		this.setBorder(new TitledBorder("Details:"));

		kbVariantName = new JTextField("Keyboard Variant: "
				+ paramManager.getKeyboardVariant());// ,
														// paramManager.getKeyboardVariant().length());
		kbVariantName.setEditable(false);
		kbVariantName.setBorder(null);

		dwellTimeDisplay = new JTextField("Dwell Time (msec): "
				+ paramManager.getDwellTime());// , NUM_COLUMNS);
		// dwellTimeDisplay.setEditable(false);
		dwellTimeDisplay.setBorder(null);
		this.add(kbVariantName);
		this.add(dwellTimeDisplay);
	}

	public void updateDwellTime(String newValue) {
		dwellTimeDisplay = new JTextField("Dwell Time (msec): " + newValue
				+ "XXX", NUM_COLUMNS);
	}

}
