package parameterSelectionWidgetsAndControllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ColourSelectionView extends JPanel {

	private JButton chooseColourButton;

	public ColourSelectionView() {
		chooseColourButton = new JButton("Choose Colour");
		chooseColourButton.setOpaque(true);

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

		mainPanel.add(chooseColourButton);
		System.out.println(this.getClass().getName() + " : Done.");

		// mainPanel.add(sizePanel);
		// southPanel.add(commandPanel);
		// southPanel.add(stylePanel);

	}

	public JButton getChooseColourButton() {
		return chooseColourButton;
	}

	public void updateDisplay(Color theSelectedColor) {
		chooseColourButton.setBackground(theSelectedColor);
	}

}
