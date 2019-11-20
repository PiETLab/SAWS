package treeDataStructure;

import java.io.Serializable;
import java.util.Vector;

import sourceSymbolSet.SourceSymbol;


/**
 * The LeafNode class implements a containment hierarchy node which occurs at
 * the leaves of the Containment Hierarchy. The selection group associated with
 * such a node must be a <b>trivial</b> selection group, which is a set
 * consisting of a single JVirtualKeyboardButton selection group). If a
 * selection group is non-trivial, then it must preserve containment.
 * 
 * @author Melanie Baljko
 */
// public class LeafNode<SelectionGroupMember extends AbstractButton> extends
// Node<SelectionGroupMember> {
public class LeafNode extends Node implements Serializable {

	// private SelectionGroup associatedNodeSet;

	// private TrivialSelectionGroup associatedNodeSet;

	/**
	 * This constructor is used when building a containment heirarchy. Nodes
	 * constructed in the following manner are leaves. Such nodes are then added
	 * to internal nodes.
	 */
	// public LeafNode(JVirtualKeyboardButton but) {
	public LeafNode(SourceSymbol but) {
		this(new TrivialSelectionGroup(but));
		setProbabilityofOccurrence(but.getMarginalProbability());
	}

	public int calculateMaximumLeafLevel() {
		return -1;
	}

	public LeafNode(SelectionGroup sg) {
		setParent(null);
		super.associatedNodeSet = sg;

		// setExpectedFrequency(but.getExpectedFrequency());
		// setExpectedFrequency(but.getExpectedFrequency());
		this.setNumDescendentLevels(0);
		setDepth(0);
		super.setNodeID("-");
	}

	@Override
	public Node clone() {
		Node copy = new LeafNode(getSelectionGroup().clone());
		copy.setProbabilityofOccurrence(this.getProbabilityofOccurrence());
		return copy;
	}

	/*
	 * COnverts a leaf node to an internal node.
	 */
	public InternalNode ConvertToInternalNode() {
		// Node copy = this.clone();
		SelectionGroup sg = getSelectionGroup().clone();
		InternalNode internal = new InternalNode(new NontrivialSelectionGroup(
				sg));
		internal.setDepth(this.getEdgeCostToHere());

		return internal;

	}

	/**
	 * This method is used when building a containment hierarchy. It should only
	 * ever be invoked on an internal node. It validates that this passed node
	 * is not a leaf. If it is, the application crashes.
	 * 
	 * @param chn
	 */
	public void addChild(Node chn) {
		throw new RuntimeException(
				"Trying to add child to a node in an illegal way");
	}

	public SourceSymbol getRepresentative() {
		return (SourceSymbol) getSelectionGroup().extractMembers().get(0);
	}

	/**
	 * @return the first child of this node or null if it is a leaf (actually,
	 *         the app crashes in this case - fix this)
	 */
	public Node getFirstChild() {
		throw new RuntimeException("Trying to access child of a leaf");
	}

	public Vector<Node> getChildren() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	/**
	 * This method is used in order to label this node and its children with
	 * indices, using the notation "x1.x2.x3" (the number of dot-delimited
	 * segments indicates the level of this node, where x is the prefix, and i
	 * indicates this node's position among its siblings, starting from top
	 * level to bottom). It is meant to be invoked on the root, once the
	 * containment hierarchy has been completely constructed.
	 * 
	 * @param _lab
	 *            the prefix of the label to be given to the node.
	 */
	public void propogateLabels(String _lab) {
		setNodeID(_lab);
		associatedNodeSet.setID(_lab);
	}

	public void propogateLabels(boolean isLabelled) {
		if (!isLabelled) {
			setNodeID("");
			associatedNodeSet.setID("");
		}
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("*** Leaf Node:" + super.getNodeID());
		buf.append("\n\tDepth: " + super.getEdgeCostToHere());
		buf.append("\n\tButton: " + getSelectionGroup().toString());
		return buf.toString();

	}

	public String toStringProbLaTeX() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\chunk{" + "[]" + "$_{"
				+ super.toStringExpectedFrequency() + "}$}");
		// buf.append("\\chunk{" + super.getNodeID() + "$_{"
		// + super.toStringExpectedFrequency() + "}$}");
		return buf.toString();
	}

	@Override
	public void propogateConditionalProbabilities(SourceSymbol goal,
			double probFollowCorrectEdge, double probFollowIncorrectEdge) {
		//
		// if (this.getRepresentative().equals(Selectable.VK_DEL)
		// && goal.equals(Selectable.VK_DEL)) {
		// this.setConditionalProbability(goal, 1.00);
		// } else if (this.getRepresentative().equals(Selectable.VK_DEL)
		// && !goal.equals(Selectable.VK_DEL)) {
		// this.setConditionalProbability(goal, 0);
		// } else if (this.getRepresentative().equals(goal)) {
		// this.setConditionalProbability(goal, probFollowCorrectEdge);
		// } else {
		// this.setConditionalProbability(goal, probFollowIncorrectEdge);
		//		}
	}
}
