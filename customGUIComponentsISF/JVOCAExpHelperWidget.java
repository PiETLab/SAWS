package customGUIComponentsISF;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import experimentHelper.ExperimentHelper;


public class JVOCAExpHelperWidget extends JLabel {
	
	private Font DEFAULT_FONT = new Font("sanserif", Font.PLAIN, 16);
	private final int DEFAULT_WIDTH = 250;
	private final int DEFAULT_HEIGHT = 100;

	public JVOCAExpHelperWidget() {		
		
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setMaximumSize(new Dimension(DEFAULT_WIDTH + 500, DEFAULT_HEIGHT));
		this.setMinimumSize(new Dimension(DEFAULT_WIDTH + 500, DEFAULT_HEIGHT));

		this.setFont(DEFAULT_FONT);
		this.setBorder(new TitledBorder(new EtchedBorder(), "Target Text"));
		// System.out.println(ExperimentHelper.hasTargetText());
		//PassiveFokusAdvancerSwing.pauseEventGenerator();

		if (ExperimentHelper.hasTargetText()) {
			this.setText(ExperimentHelper.getInstructions());
			// System.out.println(ExperimentHelper.htmlize());
		}
		this.setAlignmentX(Component.RIGHT_ALIGNMENT);

	}
}
