package parameterConfiguration;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import parameterSelectionWidgetsAndControllers.ParameterSelectionController;

import SoftwareDeployment.InvocationParameter;
import SoftwareDeployment.InvocationParameterFile;
import SoftwareDeployment.UserSpecificModel;

public class ParameterSelectorController implements ActionListener {

	private ParameterSelectorView view;
	// private FontController fontController;
	// private InvocationParameterModel userModel;
	private ParameterSelectionController param1Controller;

	private IndirectSelectionFacilityInvocationParameterModel parameterModel;

	public ParameterSelectorController(ParameterSelectorView view,
			IndirectSelectionFacilityInvocationParameterModel paramModel) {

		this.view = view;
		this.parameterModel = paramModel;

		this.reinitFromParamModel(parameterModel);

		// testGUISelector.addEventListener(test.getController());
		// .addActionListener(this);

		// this.userModel = um;
		// fontController = new FontController(view.getFontPanel());
		// fontController.addActionListener(this);

		// um.get

		// parameterModel = paramModel;
		System.out.println("Retrieved parameters: \n"
				+ parameterModel.generateSummary());
		// fontController.selectFontFamily((parameterModel.getFontFamily()));
		// view.updateFont(rsvpParams.getDisplayFont());
	}

	// -------------------------------
	// implement ActionListener method
	// -------------------------------

	private void reinitFromParamModel(
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		view.reinitAndArrangeComponents();

		for (InvocationParameter param1 : paramModel.getAllParameters()) {
			// InvocationParameter param1 = paramModel.fontFamilyParam;
			JComponent param1View = param1.getGUIWidget();
			if (param1View != null) {
				view.addTabbedPane(param1.getParameterName(), param1View);
				param1Controller = param1.getGUIController();
				param1Controller.addActionListener(this);
				param1Controller
						.setAssociatedValue(param1.getAssociatedValue());
			}
		}

		view.getSaveAndExitButton().addActionListener(this);
		view.getRestoreDefaultsButton().addActionListener(this);
		view.getExitButton().addActionListener(this);
		view.getOpenFileButton().addActionListener(this);

		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.pack();
		view.setExtendedState(Frame.MAXIMIZED_BOTH);

		// frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setVisible(true); // alternative: frame.show()

	}

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		System.out.println(this.getClass().getName() + " : " + ae.getSource());

		// 'Command' - check if a command button was pressed
		if (source == view.getRestoreDefaultsButton()) {
			this.restoreDefaults();
		} else if (source == view.getOpenFileButton()) {
			this.open();
		} else if (source == view.getExitButton()) {
			System.exit(0);
		} else if (source == view.getSaveAndExitButton()) {
			// write options to parameter file
			System.out.println("Writing to file...");
			this.save();

			// um.writeParameterFile(rsvpParams);
			System.out.println("Done.");
			System.exit(0);
			// } else if (source == frame.getSizeCombo()) {
			// frame.updateFont();
			// } else if (source == frame.getFontCombo()) {
			// frame.updateFont();
			// } else if (source == fontController) {
			// view.updateFont(fontController.getFont());
			// parameterModel.setDisplayFont(fontController.getFont());
			// System.out.println("Parameter Selection Controller Action Listener: ");

		} else if (source instanceof ParameterSelectionController) {
			ParameterSelectionController theParamController = (ParameterSelectionController) source;
			InvocationParameter theParam = theParamController
					.getAssociatedInvocationParameter();
			System.out.println(this.getClass().getName()
					+ " : current parameter: " + theParam);
			theParam
					.setAssociatedValue(theParamController.getAssociatedValue());

		}
	}

	/**
	 * Serializes the data
	 */
	private void save() {
		try {
			File file = null;
			JFileChooser chooser = new JFileChooser(UserSpecificModel
					.getDefaultDirectoryForParameterFiles());
			chooser.showSaveDialog(this.view);
			file = chooser.getSelectedFile();
			String fileName = file.getAbsolutePath();
			// ObjectOutputStream stream = new ObjectOutputStream(new
			// FileOutputStream(file));
			// stream.writeObject(this.userModel);
			// userModel.writeParameterFile(parameterModel,
			// file.getAbsoluteFile()
			// .getName());
			boolean isOverwriteMode = true;
			// System.out.println(params.getClass());
			// if (params instanceof RSVPInvocationParameterModel) {
			InvocationParameterFile theFile = new InvocationParameterFile(
					fileName, isOverwriteMode);
			// rsvp.println(params.generateVerboseExplaination());
			theFile.println(parameterModel.generateParameterString());
			theFile.println("#");
			// theFile.println("#" +
			// parameterModel.generateVerboseExplaination());
			System.out.println("Written to parameter file:\n" + fileName + "\n"
					+ parameterModel.generateParameterString());

			// stream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.view,
					"Could not save the file!", "Save Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initializes the model from a file.
	 */
	private void open() {
		try {
			File file = null;
			JFileChooser chooser = new JFileChooser(UserSpecificModel
					.getDefaultDirectoryForParameterFiles());
			chooser.showOpenDialog(this.view);
			file = chooser.getSelectedFile();
			boolean isClobberMode = false;
			InvocationParameterFile isfFile = new InvocationParameterFile(file
					.getAbsolutePath(), isClobberMode);
			System.out.println("*");
			parameterModel = new IndirectSelectionFacilityInvocationParameterModel(
					isfFile);
			reinitFromParamModel(parameterModel);
			// System.out.println("Retrieved parameters: \n"
			// + parameterModel.generateSummary());

			// ObjectInputStream stream = new ObjectInputStream(
			// new FileInputStream(file));
			// this.model = new Model(stream);
			// stream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.view, e.getMessage(),
					"Open Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initializes the model from a file.
	 */
	private void restoreDefaults() {
		parameterModel = new IndirectSelectionFacilityInvocationParameterModel();
		reinitFromParamModel(parameterModel);
	}

	private void updateView() {
		// controller1.
	}

	// // -----------------------------
	// // implement ItemListener method
	// // -----------------------------
	//
	// public void itemStateChanged(ItemEvent ie) {
	// Object source = ie.getSource();
	//
	// // 'Style' - check if the font style was changed via a checkbox
	// if (source == frame.getItalicCheckBox()) {
	// if (frame.getItalicCheckBox().isSelected())
	// frame.turnOnItalics();
	// else
	// frame.turnOffItalics();
	// } else if (source == frame.getBoldCheckBox()) {
	// if (frame.getBoldCheckBox().isSelected())
	// frame.turnOnBold();
	// else
	// frame.turnOffBold();
	// }
	// frame.updateFont();
	// }
}
