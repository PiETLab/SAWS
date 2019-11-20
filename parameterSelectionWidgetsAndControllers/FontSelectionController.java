package parameterSelectionWidgetsAndControllers;


import java.awt.AWTEventMulticaster;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import basicGuiParameters.FontFamilyParameter;

import SoftwareDeployment.InvocationParameter;

public class FontSelectionController implements ActionListener,
		ParameterSelectionController {

	private InvocationParameter associatedParameter;
	private ActionListener actionListenerList = null;

	private FontSelectionView view;
	public static Font[] fontList = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getAllFonts();
	private Map<String, Font> fontMap = new HashMap<String, Font>();
	private List<String> parallelListOfFontNames = new Vector<String>();
	private String[] fontSizes = { "150", "200", "250", "300", "350" };

	private int fontStyle;

	private static int DEFAULT_FAMILY_INDEX = 0;
	final int DEFAULT_SIZE_INDEX = 1;

	public FontSelectionController(FontSelectionView view,
			InvocationParameter parameter) {
		this.view = view;
		this.associatedParameter = parameter;
		view.getSizeCombo().addActionListener(this);
		view.getFontFamilyComboBox().addActionListener(this);
		// fontList = GraphicsEnvironment.getLocalGraphicsEnvironment()
		// .getAllFonts();
		// Font[] tmp = {fontList[1], fontList[2]};
		// tmp = {fontList[1], fontList[2]};
		for (int i = 0; i < fontList.length; ++i) {
			// System.out.println(fontList[i].getName());
			fontMap.put(fontList[i].getName(), fontList[i]);// getFontFamilyComboBox().addItem(fontList[i].getName());
			parallelListOfFontNames.add(fontList[i].getName());
			// fontListRenderer.addItem(fontList[i].getName(), fontList[i]);
		}

		// view.populateFontListUsingStrings(fontMap.keySet());
		view.populateFontListUsingStrings(parallelListOfFontNames);
		view.populateFontSizeList(fontSizes);
		// view.getFontCombo();

		System.out.println(this.getClass().getName()
				+ " : Initialize combo box to: "
				+ parallelListOfFontNames.get(DEFAULT_FAMILY_INDEX));
		view.getFontFamilyComboBox().setSelectedItem(
				parallelListOfFontNames.get(DEFAULT_FAMILY_INDEX));

		System.out.println(this.getClass().getName() + " : Done.");

		// selectFontFamily("dialog");
		// view.getFontCombo().setSelectedIndex(defaultFamilyIndex);

		// this.restoreDefaults();

		// view.getSizeCombo().actionPerformed(new ItemEvent())
	}

	public void selectFontFamily(String fontFamilyName) {
		int familyIndex = findFamilyIndex(fontFamilyName);
		if (familyIndex == -1) // not found!
		{
			JOptionPane.showMessageDialog(view, "Selected Font Family \""
					+ fontFamilyName + "\" not found!\n" + "Will use '"
					+ fontList[0].getName() + "' as default.",
					"Information Message", JOptionPane.INFORMATION_MESSAGE);
			familyIndex = 0;
		}
		view.getFontFamilyComboBox().setSelectedIndex(familyIndex);
	}

	private void selectFontSize(int size) {
		int fontSizeIndex = findSizeIndex(size);
		if (fontSizeIndex == -1) // not found!
		{
			JOptionPane.showMessageDialog(view, "Selected Font Size \"" + size
					+ "\" not available!\n" + "Will use default font size: "
					+ fontSizes[0], "Information Message",
					JOptionPane.INFORMATION_MESSAGE);
			fontSizeIndex = 0;
		}
		System.out.println("index found:" + fontSizeIndex);

		view.getSizeCombo().setSelectedIndex(fontSizeIndex);

	}

	private int findSizeIndex(int size) {
		int sizeIndex = -1;
		System.out.println("****" + fontSizes.length);
		for (int i = 0; i < fontSizes.length; ++i) {
			System.out.println("****" + fontSizes[i] + "\t" + size);
			if (fontSizes[i].equals("" + size)) {
				sizeIndex = i;
				break;
			}
		}
		return sizeIndex;
	}

	private int findFamilyIndex(String fontFamily) {
		int familyIndex = -1;
		for (int i = 0; i < fontList.length; ++i) {
			if (fontList[i].getName().toLowerCase().equals(
					fontFamily.toLowerCase())) {
				familyIndex = i;
				break;
			}
		}
		return familyIndex;
	}

	public String getFontFamilyThatIsPresentlySelected() {
		return view.getFontFamilyThatIsPresentlySelected();
	}

	public int getDefaultStyle() {
		return fontStyle;
	}

	public int getFontSize() {
		if (view.getSelectedSize() == null) {
			return Integer.parseInt(fontSizes[DEFAULT_SIZE_INDEX]);

		} else
			return view.getSelectedSize();
	}

	public Font getFont() {
		return new Font(this.getFontFamilyThatIsPresentlySelected(), this
				.getDefaultStyle(), this.getFontSize());
	}

	public static Font getDefaultValue() {
		return fontList[DEFAULT_FAMILY_INDEX];
	}

	public void actionPerformed(ActionEvent e) {

		System.out.println(this.getClass().getName() + ": actionPerformed: "
				+ e.toString());

		// fontSize = Integer.parseInt((String) view.getSizeCombo()
		// .getSelectedItem());
		if (e.getSource() == view.getFontFamilyComboBox()) {
			System.out.println(this.getClass().getName()
					+ ": updating display. "
					+ (String) view.getFontFamilyComboBox().getSelectedItem());
			// view.updateSample(new Font(this
			// .getFontFamilyThatIsPresentlySelected(), this
			// .getDefaultStyle(), this.getFontSize()));
			view.updateDisplay(new Font((String) view.getFontFamilyComboBox()
					.getSelectedItem(), this.getDefaultStyle(), this
					.getFontSize()));
			// fontFamily =
			// fontList[view.getFontCombo().getSelectedIndex()].getName();
		}

		// System.out.println("Font Controller Action Listener: "
		// + new Font(fontFamily, fontStyle, fontSize));

		if (actionListenerList != null) {
			ActionEvent ae = new ActionEvent(this, 0, this.getClass().getName());
			System.out.println(this.getClass().getName() + ": dispatch event: "
					+ ae);

			actionListenerList.actionPerformed(ae);
		}

		// if (controller != null) {
		// // System.out.println("Trying to signal to meta controller");
		// controller.actionPerformed(new ActionEvent(this, e.getID(), e
		// .getActionCommand()));
		// }
	}

	public void restoreDefaults() {

		// fontFamily = fontList[defaultFamilyIndex].getName();
		// fontStyle = DEFAULT_STYLE;
		// fontSize = DEFAULT_SIZE;
		// view.getSizeCombo().setSelectedIndex(DEFAULT_SIZE_INDEX);
		view.getSizeCombo().setSelectedItem(fontList[0]);
		// italicCheckBox.setSelected(false);
		// boldCheckBox.setSelected(false);
		view.getFontFamilyComboBox().setSelectedIndex(DEFAULT_FAMILY_INDEX);
		// fontFamily = fontList[defaultFamilyIndex].getName();
		// fontStyle = DEFAULT_STYLE;
		// fontSize = DEFAULT_SIZE;
		// sizeCombo.setSelectedIndex(DEFAULT_SIZE_INDEX);
		// italicCheckBox.setSelected(false);
		// boldCheckBox.setSelected(false);
		// fontCombo.setSelectedIndex(defaultFamilyIndex);
	}

	public synchronized void addActionListener(ActionListener l) {
		System.out.println("Adding action listener: " + l);
		actionListenerList = AWTEventMulticaster.add(actionListenerList, l);
	}

	public void selectFont(Font displayFont) {
		this.selectFontFamily(displayFont.getFamily());
		this.selectFontSize(displayFont.getSize());
	}

	public InvocationParameter getAssociatedInvocationParameter() {
		return associatedParameter;
	}

	public String getAssociatedValue() {
		return this.getFontFamilyThatIsPresentlySelected();
	}

	public void setAssociatedValue(String string) {
		System.out.println("attempting to set combo box value: " + string);
		// Font desiredFont = fontList[this.findFamilyIndex(string)];
		// System.out.println("attempting to set combo box value: " +
		// desiredFont);
		if (!((String) view.getFontFamilyComboBox().getSelectedItem())
				.equals(string)) {
			// view.getFontCombo().setSelectedItem(string);
			view.getFontFamilyComboBox().setSelectedItem(string);
			// view.getFontFamilyComboBox().setSelectedItem(parallelListOfFontNames.get(23));
			// view.repaint();
			// fontList[this.findFamilyIndex(string)]);
		} else {
			System.out.println("already selected. ");
		}
	}
}
