package treeDataStructure;

import java.util.List;
import java.util.Vector;

import sourceSymbolSet.SourceSymbol;

/**
 * This class implements a <i>trivial selection group</i>, which is a set
 * consisting of a single JVirtualKeyboardButton.
 * 
 * @author Melanie Baljko
 */

// public class TrivialSelectionGroup<SelectionGroupMember extends AbstractButton> extends
// SelectionGroup<SelectionGroupMember> {
public class TrivialSelectionGroup extends
		SelectionGroup {

	SourceSymbol selectable;
	//SelectionGroupMember selectable;

	// private double prob;

	// used to create a trivial SelectionGroup
	public TrivialSelectionGroup(SourceSymbol but) {
	//public TrivialSelectionGroup(SelectionGroupMember but) {
		// constructor creates a Vector with default size=1,
		// since this Vector will be size one, used the constructor to specify
		// this
		selectable = but;
	}

	@Override
	public boolean isTrivial() {
		return true;
	}

	/*
	 * Return true when this SG is trivial and contains a dummy keyboard button
	 */
	// public boolean isDummy() {
	// return selectable instanceof JVirtualKeyboardDummyButton;
	// }
	public String toStringLaTeX() {
		StringBuffer buf = new StringBuffer();
		// if (!(selectable instanceof JVirtualKeyboardDummyButton)) {
		buf.append(selectable.toStringLaTeX());
		// if ("SP".equals(selectable.getText()))
		// buf.append("$\\sqcup$");
		// //tmp = tmp.replaceAll("sqcup", "\\$\\\\sqcup\\$");
		//			
		// else if ("<html>\\u02fd</html>".equals(selectable.getText()))
		// buf.append("***");
		// else if ("\u00b6".equals(selectable.getText()))
		// buf.append("$\\P$");
		// else if ("DEL".equals(selectable.getText()))
		// buf.append("?`");
		// else
		// buf.append(selectable.getText());
		// }
		return buf.toString();
	}

	/*
	 * Returns an iterator of the JVirtualButtons in this selection group
	 */
	// public Iterator<JVirtualKeyboardButton> iterator() {
	// Vector<JVirtualKeyboardButton> v = new Vector<JVirtualKeyboardButton>();
	// v.add(selectable);
	// return v.iterator();
	// }
	/*
	 * 
	 */
	public List<SourceSymbol> extractMembers() {
		List<SourceSymbol> v = new Vector<SourceSymbol>();
		v.add(selectable);
		return v;
	}

	public void add(SelectionGroup sg) {
		throw new RuntimeException("Trying to add to trivial selection group");
	}

	public SelectionGroup clone() {
		SourceSymbol clone = (SourceSymbol) selectable.clone();
		return new TrivialSelectionGroup(clone);
	}

}
