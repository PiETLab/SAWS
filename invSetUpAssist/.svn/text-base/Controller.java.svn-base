package invSetUpAssist;

import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.*;

// The is the GUI's controller. It receives requests (from the end user) and passes
// them to the model and/or the view via method invocation
public class Controller implements ActionListener {
	private VOCAAttributeView view;
	private VOCAAtrributeModel model;

	/*
	 * Creates a controller for the given view and model. @param view the view
	 * of the GUI. @param model the model of the GUI.
	 */
	public Controller(VOCAAttributeView view, VOCAAtrributeModel model) {
		this.view = view;
		this.model = model;
	}

	/**
	 * Performs the action relation to the given event. Five different events
	 * are supported:
	 * <ul>
	 * <li>View.EXIT: exit the GUI,</li>
	 * <li>View.SAVE: save to a file,</li>
	 * <li>View.OPEN: open from a file,</li>
	 * <li>View.ADD: add a person, and </li>
	 * <li>View.FIND: find a person.</li>
	 * </ul>
	 * 
	 * @param event
	 *            the event that triggered the action.
	 */
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		if (action.equals(VOCAAttributeView.EXIT)) {
			System.exit(0);
		} else if (action.equals(VOCAAttributeView.SAVE)) {
			this.save();
		} else if (action.equals(VOCAAttributeView.OPEN)) {
			this.open();
		} else if (action.equals(VOCAAttributeView.ADD)) {
			try {
				int age = this.view.getAge();
				String name = this.view.getPersonName();
				if (name.length() == 0) {
					throw new RuntimeException("Name cannot be empty!");
				} else if (age < 0) {
					throw new RuntimeException("Age cannot be negative!");
				}
				Person person = new Person(name, age);
				this.model.put(name, person);
				this.view.clearName();
				this.view.clearAge();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.view, "Invalid Entry!", e
						.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		} else if (action.equals(VOCAAttributeView.FIND)) {
			String name = this.view.getPersonName();
			Person result = this.model.get(name);
			if (result == null) {
				JOptionPane.showMessageDialog(this.view, "No such name!",
						"Not Found", JOptionPane.ERROR_MESSAGE);
			} else {
				this.view.setAge(result.getAge());
			}
		}
		this.view.setCount(this.model.size());
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
			ObjectOutputStream stream = new ObjectOutputStream(
					new FileOutputStream(file));
			stream.writeObject(this.model);
			stream.close();
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
			this.model = new VOCAAtrributeModel(stream);
			stream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.view, e.getMessage(),
					"Open Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
