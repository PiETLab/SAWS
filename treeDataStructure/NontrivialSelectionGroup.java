package treeDataStructure;

import java.util.List;
import java.util.Vector;

import sourceSymbolSet.SourceSymbol;

/**
 * This class implements a <i>non-trivial selection group</i>, which is a set
 * consisting of two or more selection groups. Such selection groups <b>must</b>
 * preserve containment, which means it must contain exactly the union of the
 * members of the selection group of each child.
 * 
 * @author Melanie Baljko
 */

// public class NontrivialSelectionGroup<SelectionGroupMember extends
// AbstractButton> extends
// SelectionGroup<SelectionGroupMember> {
public class NontrivialSelectionGroup extends SelectionGroup {
	private List<SelectionGroup> selectables;

	// the only place this constrcutor should be called is from
	// Node,
	// when an internal node is being created
	public NontrivialSelectionGroup() {
		super();
		// super<SelectionGroupMember>();
		selectables = new Vector<SelectionGroup>();
	}

	// the only place this constrcutor should be called is from
	// Node,
	// when an internal node is being created
	public NontrivialSelectionGroup(SelectionGroup sg) {
		super();
		selectables = new Vector<SelectionGroup>();
		selectables.add(sg);
	}

	@Override
	public boolean isTrivial() {
		return false;
	}

	public void add(SelectionGroup sg) {
		// selectables.insertElementAt(sg,0);
		selectables.add(sg);
	}

	public List<SourceSymbol> extractMembers() {
		List<SourceSymbol> v = new Vector<SourceSymbol>();
		for (SelectionGroup sg : selectables) {
			for (SourceSymbol but : sg.extractMembers()) {
				v.add(but);
			}
		}
		return v;
	}

	public String toStringLaTeX() {
		StringBuffer buf = new StringBuffer();
		for (SourceSymbol but : extractMembers()) {
			buf.append(but.toStringLaTeX());
		}
		return buf.toString();
	}

	@Override
	public SelectionGroup clone() {
		SelectionGroup copy = new NontrivialSelectionGroup();
		for (SourceSymbol x : extractMembers()) {
			SourceSymbol clone = (SourceSymbol) x.clone();
			copy.add(new TrivialSelectionGroup(clone));
		}
		return new NontrivialSelectionGroup(copy);
	}
}
