package parameterSelectionWidgetsAndControllers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import customGUIComponentsISF.JTextDisplayer;

public class FontSelectionView extends JPanel {

	private JComboBox fontFamilyComboBox;
	private FontListRenderer fontListRenderer;

	private JComboBox sizeCombo;

	private JPanel fontPanel;
	private JPanel sizePanel;

	private JTextField displayForSample;
	// private JTextDisplayer displayForSample2;

	private String theSampleText = "THE QUICK BROWN FOX JUMPED OVER THE LAZY DOG";// The

	// Quick
	// Brown
	// Fox
	// Jumped
	// Over
	// the
	// Lazy
	// Dog.";

	// public FontSelectionView(Dimension d) {
	// this();
	// fontPanel.setPreferredSize(new Dimension(d.width / 3, d.height));
	// sizePanel.setPreferredSize(new Dimension(d.width / 6, d.height));
	//
	// }

	public <displayForSample2> FontSelectionView() {

		// create the size combo box, using the size array as the list
		this.sizeCombo = new JComboBox();
		// center align the entries

		((JLabel) getSizeCombo().getRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);

		// getSizeCombo().setPreferredSize(new Dimension(50, 1));
		this.sizeCombo.setEditable(false);

		// create the font combobox

		setFontFamilyComboBox(new JComboBox());

		fontPanel = new JPanel();
		fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.Y_AXIS));
		fontPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		// fontPanel.setLayout(new GridLayout(2, 1));
		fontPanel.add(this.getFontFamilyComboBox());
		getFontFamilyComboBox().setPreferredSize(new Dimension(150, 50));

		// String TITLE = "Font (Serif Recommended)";
		String TITLE = "Font Family";
		fontPanel.setBorder(new TitledBorder(new EtchedBorder(), TITLE));
		fontListRenderer = new FontListRenderer();
		this.getFontFamilyComboBox().setRenderer(fontListRenderer);

		// fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.PAGE_AXIS));
		displayForSample = new JTextField(theSampleText);
		displayForSample.setCaretPosition(0);
		displayForSample.setLayout(new BoxLayout(displayForSample,
				BoxLayout.Y_AXIS));
		displayForSample.setAlignmentX(Component.LEFT_ALIGNMENT);

		// displayForSample2 = new JTextDisplayer();
		// displayForSample2.augmentGloss(theSampleText);
		// displayForSample2.showFontHints();

		JPanel mainPanel = new JPanel();
		// mainPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		// mainPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		// mainPanel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		// mainPanel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		// mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		// mainPanel.setBackground(Color.BLUE);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(mainPanel);

		mainPanel.add(fontPanel);
		// mainPanel.add(displayForSample2);
		mainPanel.add(displayForSample);
		System.out.println(this.getClass().getName() + " : Done.");

		// mainPanel.add(sizePanel);
		// southPanel.add(commandPanel);
		// southPanel.add(stylePanel);

	}

	public JComboBox getSizeCombo() {
		return sizeCombo;
	}

	public void setFontFamilyComboBox(JComboBox fontCombo) {
		this.fontFamilyComboBox = fontCombo;
	}

	public JComboBox getFontFamilyComboBox() {
		return fontFamilyComboBox;
	}

	public void populateFontListUsingStrings(List<String> listItems) {
		// for (int i = 0; i < fontList.length; ++i) {
		for (String s : listItems) {
			// System.out.println(fontList[i].getName());
			getFontFamilyComboBox().addItem(s);
			// fontListRenderer.addItem(fontList[i].getName(), fontList[i]);
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
		return (String) getFontFamilyComboBox().getSelectedItem();
	}

	public void updateDisplay(Font f) {
		System.out.println(this.getClass().getName() + ": updating display. "
				+ f.getFamily() + "\t" + f.getName());
		displayForSample.setFont(f);
		// displayForSample2.setFont(f);
		this.repaint();
		// displayForSample.setFont(this.getFontFamilyComboBox().getSelectedItem());
	}

	// public Font getFontThatIsCurrentlySelected() {
	// return new Font(this.getFontFamily(), this.getDefaultStyle(), this
	// .getFontSize());

	// }

}
