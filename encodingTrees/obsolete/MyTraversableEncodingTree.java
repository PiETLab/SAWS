/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees.obsolete;

import IndirectSelectionFacility.TextEntrySystemFrame;
import IndirectSelectionFacility.TextEntrySystemFrameController;
import IndirectSelectionFacilityCommands.DeleteCommand;
import treeDataStructure.Node;
import treeDataStructure.SelectionGroup;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import encodingTrees.AbstractEncodingTree;
import encodingTrees.EncodingTree;

import sourceSymbolSet.SourceSymbol;


/**
 * This class extends an encoding tree to add support for traversal using
 * advance focus, drill down, drill up.
 * <p>
 * Leaf nodes must be associated with trivial selection groups. Internal nodes
 * must be associated with non-trivial selection groups. Moreover, the selection
 * group of a parent must contain exactly the union of the members of the
 * selection group of each child.
 * <p>
 * This class provides makes use of the notion of "fokus" (deliberately
 * misspelled to distinguish it from focus, which is defined in JFC/Swing).
 * Exactly one node of the hierarchy may be in fokus at a time, called the
 * <i>fokusNode</i>, the parent of that node is the parent of the current
 * <i>fokusCycle</i> and <i>fokusLevel</i> denotes the level of the hierarchy
 * at which the fokusNode/fokusCycle resides (the root is level 0 and increases
 * from there).
 * <p>
 * This class provides methods for traversing the nodes of this heirarchy.
 * <p>
 * By default, the initial fokusNode is the first child of the root.
 * 
 * was previously EncodingTree
 * 
 * @author Melanie Baljko
 * @param <X>
 */
public class MyTraversableEncodingTree extends AbstractEncodingTree implements
		TraversableEncodingTreeI {

	// EncodingTree encodingTree;

	private boolean isLastOpGenerationShift;

	// levelOfInFokusNode is the level at which the parent of the current focus
	// cycle is found
	int levelOfInFokusNode;

	Node fokusNode;

	Node fokusCycleParent;

	// currSib is the child number of inFokusNode among fokusCycleParent's
	// children
	int currSib = 0;

	// the level starts at 0 for the root and increases
	// for row column scanning, level 0 is the level of the rows
	// and level 1 is the level of the columns

	public MyTraversableEncodingTree(Node _root) {
		super(_root);
		fokusNode = super.root;
		levelOfInFokusNode = -1;
		descendFokus();
	}

	public MyTraversableEncodingTree(EncodingTree et) {
		super(et.getRoot());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.TraversableEncodingTreeInteface#descendFokus()
	 */
	public void descendFokus() {
		isLastOpGenerationShift = true;
		levelOfInFokusNode++;
		fokusCycleParent = fokusNode;
		fokusNode = fokusCycleParent.getFirstChild();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.TraversableEncodingTreeInteface#ascendFokus()
	 */
	public boolean ascendFokus() {
		isLastOpGenerationShift = true;
		levelOfInFokusNode--;
		levelOfInFokusNode--;
		if (fokusCycleParent != super.root) {
			fokusNode = fokusCycleParent.parent;
			descendFokus();
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.TraversableEncodingTreeInteface#ascendFokus(VOCA.TextEntrySystemFrame,
	 *      java.awt.event.ActionEvent)
	 */
//	public boolean ascendFokus(IndirectSelectionFacilityController tcf,
//			ActionEvent ae) {
//		isLastOpGenerationShift = true;
//		levelOfInFokusNode--;
//		levelOfInFokusNode--;
//		if (fokusCycleParent != super.root) {
//			fokusNode = fokusCycleParent.parent;
//			descendFokus();
//			return true;
//		} else {
//			// modified by Fatima
//			DeleteCommand del = new DeleteCommand();
//			try {
//				del.execute(tcf, ae);
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return false;
//		}
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.TraversableEncodingTreeInteface#reset()
	 */
	public void reset() {
		fokusNode = super.root;
		levelOfInFokusNode = -1;
		descendFokus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.TraversableEncodingTreeInteface#isLastOpDescend()
	 */
	public boolean isLastOpDescend() {
		return isLastOpGenerationShift;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.TraversableEncodingTreeInteface#getRelevantEncodingTreeNode(TreeDataStructure.SourceSymbol)
	 */
	public Node getRelevantEncodingTreeNode(SourceSymbol but) {
		Node node = null;
		// node = super.root;
		for (int i = 0; i <= levelOfInFokusNode; i++) {
			node = node.getChildImmediateNodeByContents(but);
		}
		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.TraversableEncodingTreeInteface#getCurrSelectionGroup()
	 */
	public SelectionGroup getCurrentSelectionGroup() {
		return fokusNode.getSelectionGroup();
	}

	public List<SelectionGroup> getFokusCycle() {
		List<SelectionGroup> list = new Vector<SelectionGroup>();
		for (Node n : fokusCycleParent.getChildren()) {
			list.add(n.getSelectionGroup());
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.TraversableEncodingTreeInteface#advanceFokus()
	 */
	public void advanceFokus() {
		isLastOpGenerationShift = false;
		// skip if next sib is a dummy
		fokusNode = fokusNode.getNextSibling();
		// if (fokusNode.isDummyNode()) {
		// fokusNode = fokusNode.getNextSibling();
		// }
	}

	public void descendFokus(int i) {
		isLastOpGenerationShift = true;
		levelOfInFokusNode++;
		fokusCycleParent = fokusNode;
		fokusNode = fokusCycleParent.getFirstChild();
	}

	public boolean isFokusAtRootCycle() {
		// TODO Auto-generated method stub
		return false;
	}

}
