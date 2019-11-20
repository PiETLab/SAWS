/*
 * Created on 1-Aug-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package customGUIComponentsISF;

/**
 * This class implements a dummy button on a JVirtualKeyboard. Such buttons are
 * used as places to park focus in between shifts from one level of the
 * containment hierarchy to another, in order to ensure adequate dwell time on
 * each button. Such buttons cannot be selected (they shouldn't, but can be at
 * present - NEED TO FIX!) and do not have an associated command.
 * 
 * @author Melanie Baljko
 */
public class JVirtualKeyboardDummyButton extends JIndirectSelectionButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6239894403166671644L;

	public JVirtualKeyboardDummyButton(String _id) {
		super(_id, null);
		setText("");
		setBorderPainted(false);
		// setEnabled(true);
		setEnabled(true);
		setActionCommand("dummyBut" + _id);
	}

	public boolean isDummy() {
		return true;
	}
}
