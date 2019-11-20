package parameterSelectionWidgetsAndControllers;

import java.awt.AWTEventMulticaster;
import java.awt.Color;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import basicGuiParameters.FontFamilyParameter;

import SoftwareDeployment.InvocationParameter;

public class ColourSelectionController implements ActionListener,
		ParameterSelectionController {

	private static int NUMBER_OF_CONTROLLERS_INSTANTIATED = 0;
	private InvocationParameter associatedParameter;
	private ActionListener actionListenerList = null;

	private ColourSelectionView view;
	private Color theSelectedColor = getDefault();

	private static int DEFAULT_FAMILY_INDEX = 0;
	final int DEFAULT_SIZE_INDEX = 1;

	public ColourSelectionController(ColourSelectionView view,
			InvocationParameter parameter) {
		this.view = view;
		this.associatedParameter = parameter;
		view.getChooseColourButton().addActionListener(this);// fontList =
		// GraphicsEnvironment.getLocalGraphicsEnvironment()
		NUMBER_OF_CONTROLLERS_INSTANTIATED++;
		System.out.println(this.getClass().getName() + " : Done.");
		view.updateDisplay(theSelectedColor);

		// selectFontFamily("dialog");
		// view.getFontCombo().setSelectedIndex(defaultFamilyIndex);

		// this.restoreDefaults();

		// view.getSizeCombo().actionPerformed(new ItemEvent())
	}

	public void actionPerformed(ActionEvent e) {

		System.out.println(this.getClass().getName() + ": actionPerformed: "
				+ e.toString());

		// fontSize = Integer.parseInt((String) view.getSizeCombo()
		// .getSelectedItem());
		if (e.getSource() == view.getChooseColourButton()) {
			theSelectedColor = JColorChooser.showDialog(view, "Choose Colour",
					theSelectedColor);
			if (theSelectedColor != null) {
			}

			System.out.println(this.getClass().getName()
					+ ": updating display. ");
			view.updateDisplay(theSelectedColor);

			// + (String) view.getFontFamilyComboBox().getSelectedItem());
			// view.updateSample(new Font(this
			// .getFontFamilyThatIsPresentlySelected(), this
			// .getDefaultStyle(), this.getFontSize()));
			// view.updateSample(new Font((String) view.getFontFamilyComboBox()
			// .getSelectedItem(), this.getDefaultStyle(), this
			// .getFontSize()));
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
		theSelectedColor = getDefault();
		// view.getSizeCombo().setSelectedItem(fontList[0]);
		// // italicCheckBox.setSelected(false);
		// // boldCheckBox.setSelected(false);
		// view.getFontFamilyComboBox().setSelectedIndex(DEFAULT_FAMILY_INDEX);
		// fontFamily = fontList[defaultFamilyIndex].getName();
		// fontStyle = DEFAULT_STYLE;
		// fontSize = DEFAULT_SIZE;
		// sizeCombo.setSelectedIndex(DEFAULT_SIZE_INDEX);
		// italicCheckBox.setSelected(false);
		// boldCheckBox.setSelected(false);
		// fontCombo.setSelectedIndex(defaultFamilyIndex);
	}

	public static Color getDefault() {
		if (NUMBER_OF_CONTROLLERS_INSTANTIATED == 1) {
			return Color.WHITE;
		} else if (NUMBER_OF_CONTROLLERS_INSTANTIATED == 2) {
			return Color.BLACK;
		} else {
			return Color.RED;
		}
	}

	public synchronized void addActionListener(ActionListener l) {
		System.out.println("Adding action listener: " + l);
		actionListenerList = AWTEventMulticaster.add(actionListenerList, l);
	}

	public InvocationParameter getAssociatedInvocationParameter() {
		return associatedParameter;
	}

	public String getAssociatedValue() {
		return theSelectedColor.toString();
	}

	public static int[] getRGBValuesFromString(String string) {
		// string will be of the form: java.awt.Color[r=51,g=51,b=255]
		System.out.println("attempting to set value: " + string);
		String REGEX_RED = "r=\\w+";
		String REGEX_GREEN = "g=\\w+";
		String REGEX_BLUE = "b=\\w+";
		Pattern p1 = Pattern.compile(REGEX_RED);
		Matcher m1 = p1.matcher(string);
		// System.out.println("***");
		int red = 0;
		int green = 0;
		int blue = 0;
		if (m1.find()) {
			// System.out.println(m1.group());
			red = Integer.parseInt(m1.group().substring(2));
			// System.out.println(red);
		}
		Matcher m2 = Pattern.compile(REGEX_GREEN).matcher(string);
		if (m2.find()) {
			// System.out.println(m2.group());
			green = Integer.parseInt(m2.group().substring(2));
			// System.out.println(green);
		}
		Matcher m3 = Pattern.compile(REGEX_BLUE).matcher(string);
		if (m3.find()) {
			// System.out.println(m3.group());
			blue = Integer.parseInt(m3.group().substring(2));
			// System.out.println(blue);
		}
		int[] vals = new int[3];
		vals[0] = red;
		vals[1] = green;
		vals[2] = blue;
		return vals;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeselectionWidgetsAndControllers.ParameterSelectionController#
	 * setAssociatedValue(java.lang.String)
	 */
	public void setAssociatedValue(String string) {
		// string will be of the form: java.awt.Color[r=51,g=51,b=255]
		System.out.println("attempting to set value: " + string);
		int[] vals = getRGBValuesFromString(string);
		theSelectedColor = new Color(vals[0], vals[1], vals[2]);
		System.out.println(theSelectedColor);
		view.updateDisplay(theSelectedColor);

		// Font desiredFont = fontList[this.findFamilyIndex(string)];
		// System.out.println("attempting to set combo box value: " +
		// desiredFont);
		// if (!((String) view.getFontFamilyComboBox().getSelectedItem())
		// .equals(string)) {
		// // view.getFontCombo().setSelectedItem(string);
		// view.getFontFamilyComboBox().setSelectedItem(string);
		// //
		// view.getFontFamilyComboBox().setSelectedItem(parallelListOfFontNames.get(23));
		// // view.repaint();
		// // fontList[this.findFamilyIndex(string)]);
		// } else {
		// System.out.println("already selected. ");
		// }
	}
}
