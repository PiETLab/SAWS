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

public class BooleanSelectionController implements ActionListener,
		ParameterSelectionController {

	private InvocationParameter associatedParameter;
	private ActionListener actionListenerList = null;

	private BooleanSelectionView view;
	private Boolean isValueAssignedTrue = getDefault();

	public BooleanSelectionController(BooleanSelectionView view,
			InvocationParameter parameter) {
		this.view = view;
		this.associatedParameter = parameter;
		view.getBooleanSelectionButton().addActionListener(this);
		System.out.println(this.getClass().getName() + " : Done.");
		view.updateDisplay(isValueAssignedTrue);

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
		if (e.getSource() == view.getBooleanSelectionButton()) {
			isValueAssignedTrue = !isValueAssignedTrue;

			System.out.println(this.getClass().getName()
					+ ": updating display. ");
			view.updateDisplay(isValueAssignedTrue);

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
		isValueAssignedTrue = getDefault();
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

	public static boolean getDefault() {
		return false;
	}

	public synchronized void addActionListener(ActionListener l) {
		System.out.println("Adding action listener: " + l);
		actionListenerList = AWTEventMulticaster.add(actionListenerList, l);
	}

	public InvocationParameter getAssociatedInvocationParameter() {
		return associatedParameter;
	}

	public String getAssociatedValue() {
		return isValueAssignedTrue.toString();
	}

	// 

	/*
	 * (non-Javadoc)
	 * 
	 * @seeselectionWidgetsAndControllers.ParameterSelectionController#
	 * setAssociatedValue(java.lang.String)
	 */
	public void setAssociatedValue(String string) {
		// string will be of the form: java.awt.Color[r=51,g=51,b=255]
		System.out.println("attempting to set value: " + string);
		isValueAssignedTrue = Boolean.parseBoolean(string);
		System.out.println(isValueAssignedTrue);
		view.updateDisplay(isValueAssignedTrue);
	}
}
