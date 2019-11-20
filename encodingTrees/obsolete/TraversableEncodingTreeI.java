package encodingTrees.obsolete;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import customGUIComponentsISF.JIndirectSelectionButton;

import encodingTrees.EncodingTree;

import sourceSymbolSet.SourceSymbol;


//import IndirectSelectionFacility.TextEntrySystemFrame;
//import IndirectSelectionFacility.TextEntrySystemFrameController;
import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.Node;
import treeDataStructure.SelectionGroup;

public interface TraversableEncodingTreeI extends EncodingTree {

	/**
	 * The method causes fokus to descend to the first child of the current
	 * node.
	 */
	public abstract void descendFokus();

	/**
	 * The method causes fokus to descend to the ith child of the current node.
	 */
	public abstract void descendFokus(int i);

	/**
	 * The method causes fokus to descend to the first child of the current
	 * node.
	 * 
	 * 
	 * Can't ascend from first generation!!! retunr false for unsuc. attempts
	 * 
	 */
	public abstract boolean ascendFokus();

	/**
	 * The method causes fokus to descend to the first child of the current
	 * node.
	 * 
	 * 
	 * Can't ascend from first generation!!! return false for unsuc. attempts
	 * 
	 * Modified version: It was modified by fatima. If the ascend happens at the
	 * root, it dels the previous letter from the composition area.
	 */
	// public abstract boolean ascendFokus(IndirectSelectionFacilityController
	// tcf,
	// ActionEvent ae);

	/**
	 * The method puts the first child of the root node in fokus.
	 */
	public abstract void reset();

	public abstract boolean isLastOpDescend();

	/**
	 * This method returns the node in containment hierarchy --- at the same
	 * level as the inFokusNode --- that contains the passed parameter.
	 */
	public abstract Node getRelevantEncodingTreeNode(SourceSymbol but);

	/**
	 * @return the selection group of the node that is presently in fokus.
	 */
	public abstract SelectionGroup getCurrentSelectionGroup();

	/**
	 * The method causes focus to advance to the next sibling of the current
	 * node.
	 */
	public abstract void advanceFokus();

	public abstract List<SelectionGroup> getFokusCycle();

	public abstract boolean isFokusAtRootCycle();


}