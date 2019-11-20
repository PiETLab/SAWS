package invSetUpAssist;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

//This class sets up the view and register listeners 
public class VOCAAttributeView extends JFrame {
	private JTextField dwellTime;
	private JTextField name;
	private JTextField age;

	private static final long serialVersionUID = 2L; // needed by serializers

	public static final String OPEN = "OPEN";
	public static final String SAVE = "SAVE";
	public static final String EXIT = "EXIT";
	public static final String ADD = "ADD";
	public static final String FIND = "FIND";

	/**
	 * Creates a view with the given width, height and model.
	 * 
	 * @param width
	 *            the width of the GUI.
	 * @param height
	 *            the height of the GUI.
	 * @param model
	 *            the model of the GUI.
	 */
	public VOCAAttributeView(int width, int height, VOCAAtrributeModel model) {
		super();
		this.setSize(width, height);

		Controller controller = new Controller(this, model);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Open Config File...", KeyEvent.VK_O);
		menu.add(menuItem);
		menuItem.addActionListener(controller);
		menuItem.setActionCommand(VOCAAttributeView.OPEN);
		// ---------------------------------
		menuItem = new JMenuItem("Save Config File...", KeyEvent.VK_S);
		menu.add(menuItem);
		menuItem.addActionListener(controller);
		menuItem.setActionCommand(VOCAAttributeView.SAVE);
		// ---------------------------------
		menu.addSeparator();
		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menu.add(menuItem);
		menuItem.addActionListener(controller);
		menuItem.setActionCommand(VOCAAttributeView.EXIT);
		

		final int GRID_ROWS = 4;
		final int GRID_COLS = 2;
//		this.setLayout(new GridLayout(GRID_ROWS, GRID_COLS));

		this.dwellTime = new JTextField("" + model.getDwellTimeInMSec());
		// this.name = new JTextField();
		// this.age = new JTextField();

//		JButton add = new JButton("Add");
//		add.setActionCommand(View.ADD);
//		JButton find = new JButton("Find");
//		find.setActionCommand(View.FIND);

		this.add(new JLabel("Dwell Time : "));
		this.add(this.dwellTime);
//		this.add(new JLabel("Name: "));
//		this.add(this.name);
//		this.add(new JLabel("Age: "));
//		this.add(this.age);
//		this.add(find);
//		this.add(add);

	}

	/**
	 * Returns the name.
	 * 
	 * @return the name.
	 */
	public String getPersonName() {
		return this.name.getText().trim();
	}

	/**
	 * Returns the age.
	 * 
	 * @return the age.
	 * @throws NumberFormatException
	 *             if the age is not a parsable int.
	 */
	public int getAge() throws NumberFormatException {
		return Integer.parseInt(this.age.getText().trim());
	}

	/**
	 * Sets the age to the given age.
	 * 
	 * @param age
	 *            the new age.
	 */
	public void setAge(int age) {
		this.age.setText("" + age);
	}

	/**
	 * Clears the age.
	 */
	public void clearAge() {
		this.age.setText("");
	}

	/**
	 * Clears the name.
	 */
	public void clearName() {
		this.name.setText("");
	}

	/**
	 * Returns the count.
	 * 
	 * @return the count.
	 */
	public int getCount() {
		return Integer.parseInt(this.dwellTime.getText());
	}

	/**
	 * Sets the count to the given count.
	 * 
	 * @param count
	 *            the new count.
	 */
	public void setCount(int count) {
		this.dwellTime.setText("" + count);
	}
}