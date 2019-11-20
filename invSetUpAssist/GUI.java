package invSetUpAssist;

import javax.swing.JFrame;

//This app starts the GUI by initializing the model and passing it to the view 
public class GUI {

	static final int WIDTH = 350;
	static final int HEIGHT = 250;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		VOCAAttributeView view = new VOCAAttributeView(WIDTH, HEIGHT, new VOCAAtrributeModel());
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setTitle(view.getClass().getName());
		//view.pack();  // why do we need to invoke the pack method - prepare answer...
		view.setVisible(true);
	}

}
