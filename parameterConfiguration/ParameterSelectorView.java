package parameterConfiguration;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ParameterSelectorView extends JFrame {

	final int DEFAULT_STYLE = Font.PLAIN;
	final int DEFAULT_SIZE = 260;
	final int DEFAULT_SIZE_INDEX = 3;
	final String[] SZ = { "10", "14", "18", "22", "26", "32", "38", "48" };

	private JTextField message;
	private JButton exitButton;
	private JButton openButton;
	private JButton saveAndExitButton;
	private JButton restoreDefaultsButton;

	private JTabbedPane tabbedPane;

	// private FontController fontMgr;
	// private JPanel fontPanel;

	ParameterSelectorController controller;

	public ParameterSelectorView() {
		System.out.println("Initializing: " + this.getClass().getName());

		this.reinitAndArrangeComponents();

	}

	public void installController(ParameterSelectorController controller) {
		this.controller = controller;
		// fontMgr.addActionListener(controller);
	}

	public JTextField getMessage() {
		return message;
	}

	public void restoreDefaults() {
		// fontFamily = fontList[defaultFamilyIndex].getName();
		// fontStyle = DEFAULT_STYLE;
		// fontSize = DEFAULT_SIZE;
		// sizeCombo.setSelectedIndex(DEFAULT_SIZE_INDEX);
		// italicCheckBox.setSelected(false);
		// boldCheckBox.setSelected(false);
		// fontCombo.setSelectedIndex(defaultFamilyIndex);
	}

	public JButton getOpenFileButton() {
		return openButton;
	}

	public JButton getRestoreDefaultsButton() {
		return restoreDefaultsButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JButton getSaveAndExitButton() {
		return saveAndExitButton;
	}

	public void updateFont(Font font) {
		// fontSize = Integer.parseInt((String) sizeCombo.getSelectedItem());
		// fontFamily = fontList[fontCombo.getSelectedIndex()].getName();
		message.setFont(font);
		System.out.println(font);

	}

	public void reinitAndArrangeComponents() {
		// Dimension availableRealEstate = Toolkit.getDefaultToolkit()
		// .getScreenSize();

		// this.removeAll();

		// this.setSize(availableRealEstate);
		this.setTitle(this.getClass().getName());

		// ----------------------------------
		// construct and configure components
		// ----------------------------------

		message = new JTextField("Hello");
		message.setEditable(false);
		message.setBackground(Color.white);
		message.setForeground(Color.black);

		// message.setHorizontalAlignment(SwingConstants.CENTER);
		// message.setPreferredSize(new Dimension(availableRealEstate.width,
		// availableRealEstate.height / 2));
		// availableRealEstate.height));
		// message.setPreferredSize(new Dimension(500, 500));

		restoreDefaultsButton = new JButton("Restore defaults");
		exitButton = new JButton("Quit (Do not save)");
		saveAndExitButton = new JButton("Save and quit");
		openButton = new JButton("Open Parameter File");

		// ------------------
		// arrange components
		// ------------------

		// add components to panels

		JPanel commandPanel = new JPanel();
		// commandPanel.setLayout(new GridLayout(2, 1));
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));

		commandPanel.add(openButton);
		commandPanel.add(saveAndExitButton);
		commandPanel.add(restoreDefaultsButton);
		commandPanel.add(exitButton);
		// commandPanel
		// .setBorder(new TitledBorder(new EtchedBorder(), "Commands"));

		JPanel mainPanel = new JPanel();
		// Container contentPane = getContentPane();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		// tabbedPane = new JTabbedPane(SwingConstants.TOP,
		// JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.WRAP_TAB_LAYOUT);
		// tabbedPane.setTabPlacement(SwingConstants.LEFT);

		// this.add(message);
		// this.add(fontPanel);
		mainPanel.add(commandPanel);
		mainPanel.add(tabbedPane);

		// mainPanel.add(fontPanel);
		this.setContentPane(mainPanel);

		// contentPane.add(message, "Center");
		// contentPane.add(fontPanel, "South");
	}

	public void addTabbedPane(String tabTitle, JComponent component) {
		tabbedPane.addTab(tabTitle, component);
	}

	// public FontPanel getFontPanel() {
	// return (FontPanel) fontPanel;
	// }

}
