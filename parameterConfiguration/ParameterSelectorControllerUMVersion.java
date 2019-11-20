package parameterConfiguration;

import invocationParametersRSVP.RSVPInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import SoftwareDeployment.UserSpecificModel;

public class ParameterSelectorControllerUMVersion implements ActionListener {

	private final String RSVP_PARAMETER_FILENAME = "Parameters/rsvp-parameters.txt";
	private final String ISF_PARAMETER_FILENAME = "Parameters/isf-parameters.txt";

	private ParameterSelectorView view;
	private FontSelectionController fontController;
	private UserSpecificModel userModel;

	private RSVPInvocationParameterModel rsvpParams;

	public ParameterSelectorControllerUMVersion(ParameterSelectorView view,
			UserSpecificModel um) {

		this.view = view;
		this.userModel = um;
		fontController = new FontSelectionController(view.getFontPanel());
		fontController.addActionListener(this);

		view.getSaveAndExitButton().addActionListener(this);
		view.getRestoreDefaultsButton().addActionListener(this);
		view.getExitButton().addActionListener(this);

		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.pack();
		// frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setVisible(true); // alternative: frame.show()

		// um.get

		rsvpParams = (RSVPInvocationParameterModel) um
				.getInvocationParameters();
		System.out.println("Retrieved parameters: \n"
				+ rsvpParams.generateSummary());
		fontController.selectFont(rsvpParams.getDisplayFont());
		// view.updateFont(rsvpParams.getDisplayFont());
	}

	// -------------------------------
	// implement ActionListener method
	// -------------------------------

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		// System.out.println(ae.getSource());

		// 'Command' - check if a command button was pressed
		if (source == view.getRestoreDefaultsButton()) {
			view.restoreDefaults();
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
		} else if (source == fontController) {
			view.updateFont(fontController.getFont());
			rsvpParams.setDisplayFont(fontController.getFont());
			// System.out.println("Parameter Selection Controller Action Listener: ");

		}
	}

	/**
	 * Serializes the data
	 */
	private void save() {
		try {
			File file = null;
			JFileChooser chooser = new JFileChooser(file);
			chooser.showSaveDialog(this.view);
			file = chooser.getSelectedFile();
			// ObjectOutputStream stream = new ObjectOutputStream(new
			// FileOutputStream(file));
			// stream.writeObject(this.userModel);
			userModel.writeParameterFile(rsvpParams, file.getAbsoluteFile().getName());

			//stream.close();
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
			JFileChooser chooser = new JFileChooser(file);
			chooser.showOpenDialog(this.view);
			file = chooser.getSelectedFile();
			ObjectInputStream stream = new ObjectInputStream(
					new FileInputStream(file));
			// this.model = new Model(stream);
			stream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.view, e.getMessage(),
					"Open Error", JOptionPane.ERROR_MESSAGE);
		}
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
