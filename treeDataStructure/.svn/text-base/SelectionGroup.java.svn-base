/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package treeDataStructure;

import java.awt.Color;
import java.io.Serializable;
import java.util.*;

import sourceSymbolSet.SourceSymbol;

// import javax.swing.AbstractButton;

/**
 * This class implements a <i>selection group</i>, which is a either a set
 * consisting of a single JVirtualKeyboardButton (a <b>trivial</b> selection
 * group) or a set consisting of two or more selection groups (a
 * <b>non-trivial</b> selection group). If a selection group is non-trivial,
 * then it must preserve containment.
 * <p>
 * Each node in the containment hierarchy is associated with a SelectionGroup.
 * 
 * @see encodingTrees for more information, especially with respect to the
 *      properties that must be maintained.
 * 
 * @author Melanie Baljko
 */
// public abstract class SelectionGroup<SelectionGroupMember extends
// AbstractButton> {
public abstract class SelectionGroup implements Serializable {

	// private static final Color DEFAULT_COLOUR = Color.LIGHT_GRAY;
	//
	// private final Color IN_FOKUS_INITIAL_COLOUR = Color.BLACK;
	//
	// // private final Color IN_FOKUS_COLOUR = Color.black;
	// private final Color IN_FOKUS_COLOUR = Color.GREEN;
	//
	// private final Color SELECTED_COLOUR = Color.blue;
	//
	// private final Color SELECTED_COLOUR2 = Color.MAGENTA;

	private boolean isVerbose = false;

	private boolean isInFokus = false;

	private String uniqueID = "";

	private long start = System.currentTimeMillis();

	public SelectionGroup() {
	}

	public abstract void add(SelectionGroup sg);

	public abstract SelectionGroup clone();

	public abstract boolean isTrivial();

	/**
	 * Returns true when this SG is trivial and contains a dummy keyboard button
	 */
	// public abstract boolean isDummy();
	public abstract String toStringLaTeX();

	/**
	 * 
	 */
	public abstract List<SourceSymbol> extractMembers();

	public SourceSymbol getFirst() {
		return (SourceSymbol) extractMembers().get(0);
	}

	public SourceSymbol getLast() {
		return (SourceSymbol) extractMembers().get(extractMembers().size() - 1);
	}

	/**
	 * Returns an iterator of the JVirtualButtons in this selection group
	 */
	// public abstract Iterator<JVirtualKeyboardButton> iterator();
	// public static Color getDefaultColor() {
	// return DEFAULT_COLOR;
	// }
	public void setIsInFokus(boolean val) {
		isInFokus = val;
	}

	public boolean isInFokus() {
		return isInFokus;
	}

	public void putInFokus() {
		setIsInFokus(true);
		startFokusTimerDuration();
		// changeBackgroundColour(IN_FOKUS_COLOUR);
	}

	public void putInFokusInitial() {
		setIsInFokus(true);
		startFokusTimerDuration();
		// changeBackgroundColour(IN_FOKUS_INITIAL_COLOUR);
	}

	public void putOutOfFokus() {
		setIsInFokus(false);
		// changeBackgroundColour(DEFAULT_COLOUR);
		if (isVerbose)
			System.out.println(getStats());
	}

	public void highlightAsSelected() {
		// System.out.println("xxxxxxxx");
		setIsInFokus(false);
		// changeBackgroundColour(SELECTED_COLOUR);
	}

	public void highlightAsSelected2() {
		setIsInFokus(false);
		// changeBackgroundColour(SELECTED_COLOUR2);
	}

	// public void resetDefaultAppearance() {
	// changeBackgroundColour(DEFAULT_COLOUR);
	// }

	public void changeBackgroundColour(Color colour) {
		for (SourceSymbol but : extractMembers()) {
			// but.modifyBkgd(colour);
			// System.out.println("trying to mod: " + but + colour);
			but.setBackground(colour);
		}
	}

	public boolean contains(SourceSymbol but) {
		List<SourceSymbol> v = extractMembers();
		return v.contains(but);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		Iterator<SourceSymbol> it = extractMembers().iterator();
		while (it.hasNext()) {
			buf.append(((SourceSymbol) it.next()).toString());
		}
		return buf.toString();
	}

	public String toStringStripped() {
		StringBuffer buf = new StringBuffer();
		for (SourceSymbol but : extractMembers()) {
			// if (!(but instanceof JVirtualKeyboardDummyButton))
			buf.append(but.toStringOneChar());
			// prev was: buf.append(but.getText());
		}
		return buf.toString();
	}

	public String getID() {
		return uniqueID;
	}

	public void setID(String _lab) {
		uniqueID = _lab;
	}

	public String getStats() {
		return getID() + "\t in Fokus (msec): \t" + getDuration();
	}

	/**
	 * Get the duration of time (milliseconds) from the time that this selection
	 * group was put in focus until the time of this method's invocation.
	 * Doesn't care if this selection group has been put out of fokus. If this
	 * selection group has never been put into fokus, this method returns the
	 * current time in millis.
	 * 
	 * @return
	 */
	public String getFokusTimerStatus() {
		return "" + getDuration();
	}

	public void startFokusTimerDuration() {
		start = System.currentTimeMillis();
	}

	private long getDuration() {
		return (System.currentTimeMillis() - start);
	}

}