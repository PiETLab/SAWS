package parameterSelectionWidgetsAndControllers;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BooleanSelectionView extends JPanel {

	private JCheckBox booleanSelectionButton;

	public BooleanSelectionView() {
		booleanSelectionButton = new JCheckBox();

		// JPanel southPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		// mainPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		// mainPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		// mainPanel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		// mainPanel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		// mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		// mainPanel.setBackground(Color.BLUE);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		this.add(mainPanel);

		mainPanel.add(new JLabel("Activate Feature? (check for yes)"));
		mainPanel.add(booleanSelectionButton);
		System.out.println(this.getClass().getName() + " : Done.");

		// mainPanel.add(sizePanel);
		// southPanel.add(commandPanel);
		// southPanel.add(stylePanel);

	}

	public JCheckBox getBooleanSelectionButton() {
		return booleanSelectionButton;
	}

	public void updateDisplay(Boolean isTrueValueAssigned) {
		booleanSelectionButton.setSelected(isTrueValueAssigned);
	}

}
