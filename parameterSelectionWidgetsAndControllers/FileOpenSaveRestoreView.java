package parameterSelectionWidgetsAndControllers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
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

public class FileOpenSaveRestoreView extends JPanel {

	private JComboBox fontCombo;
	private FontListRenderer fontListRenderer;

	private JCheckBox italicCheckBox;
	private JCheckBox boldCheckBox;
	private JComboBox sizeCombo;

	private JPanel fontPanel;
	private JPanel sizePanel;

	private JTextField displayForSample;

	private String theSampleText = "The Quick Brown Fox Jumped Over the Lazy Dog.";

	public FileOpenSaveRestoreView(Dimension d) {
		this();
		fontPanel.setPreferredSize(new Dimension(d.width / 3, d.height));
		sizePanel.setPreferredSize(new Dimension(d.width / 6, d.height));

	}

	public FileOpenSaveRestoreView() {
		setItalicCheckBox(new JCheckBox("Italic"));
		setBoldCheckBox(new JCheckBox("Bold"));

		// create the size combo box, using the size array as the list
		this.sizeCombo = new JComboBox();
		// center align the entries

		((JLabel) getSizeCombo().getRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);

		// getSizeCombo().setPreferredSize(new Dimension(50, 1));
		this.sizeCombo.setEditable(true);

		// create the font combobox

		setFontCombo(new JComboBox());

		fontPanel = new JPanel();
		fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.PAGE_AXIS));
		fontPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		//fontPanel.setLayout(new GridLayout(2, 1));
		fontPanel.add(this.getFontCombo());
		getFontCombo().setPreferredSize(new Dimension(150, 1));

		fontPanel.setBorder(new TitledBorder(new EtchedBorder(),
				"Font (Serif Recommended)"));
		fontListRenderer = new FontListRenderer();
		this.getFontCombo().setRenderer(fontListRenderer);

		//fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.PAGE_AXIS));
		displayForSample = new JTextField(theSampleText);
		displayForSample.setCaretPosition(0);
		displayForSample.setAlignmentX(Component.LEFT_ALIGNMENT);

		// fontPanel.setPreferredSize(new Dimension(d.width / 3, d.height));

		sizePanel = new JPanel();
		sizePanel.add(new JLabel("Maximum font size for selected family"));
//		sizePanel.setLayout(new GridLayout(2, 1));
//		sizePanel.add(getSizeCombo());
		sizePanel.setBorder(new TitledBorder(new EtchedBorder(),
				"Size (Points)"));
		// sizePanel.setPreferredSize(new Dimension(d.width / 6, d.height));

		JPanel stylePanel = new JPanel();
		stylePanel.setLayout(new GridLayout(2, 1));
		stylePanel.add(getItalicCheckBox());
		stylePanel.add(getBoldCheckBox());
		stylePanel.setBorder(new TitledBorder(new EtchedBorder(), "Style"));
		// stylePanel.setPreferredSize(new Dimension(d.width / 3, d.height));

		// JPanel southPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainPanel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainPanel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		// mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		// mainPanel.setBackground(Color.BLUE);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		this.add(mainPanel);

		mainPanel.add(fontPanel);mainPanel.add(displayForSample);
		
		//mainPanel.add(sizePanel);
		// southPanel.add(commandPanel);
		// southPanel.add(stylePanel);

	}

	public void setItalicCheckBox(JCheckBox italicCheckBox) {
		this.italicCheckBox = italicCheckBox;
	}

	public JCheckBox getItalicCheckBox() {
		return italicCheckBox;
	}

	public void setBoldCheckBox(JCheckBox boldCheckBox) {
		this.boldCheckBox = boldCheckBox;
	}

	public JCheckBox getBoldCheckBox() {
		return boldCheckBox;
	}

	public JComboBox getSizeCombo() {
		return sizeCombo;
	}

	public void setFontCombo(JComboBox fontCombo) {
		this.fontCombo = fontCombo;
	}

	public JComboBox getFontCombo() {
		return fontCombo;
	}

	public void populateFontList(Font[] fontList) {
		for (int i = 0; i < fontList.length; ++i) {
			// System.out.println(fontList[i].getName());
			getFontCombo().addItem(fontList[i].getName());
			fontListRenderer.addItem(fontList[i].getName(), fontList[i]);
		}
	}

	public void populateFontSizeList(String[] fontSizes) {
		for (int i = 0; i < fontSizes.length; ++i)
			getSizeCombo().addItem(fontSizes[i]);
	}

	public Integer getSelectedSize() {
		if (this.getSizeCombo().getSelectedItem() == null)
			return null;
		else
			return Integer.parseInt((String) this.getSizeCombo()
					.getSelectedItem());
	}

	public String getFontFamilyThatIsPresentlySelected() {
		return (String) getFontCombo().getSelectedItem();
	}

	public void updateSample(Font f) {
		displayForSample.setFont(f);
	}

	// public Font getFontThatIsCurrentlySelected() {
	// return new Font(this.getFontFamily(), this.getDefaultStyle(), this
	// .getFontSize());

	// }

}
