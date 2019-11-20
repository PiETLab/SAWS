package treeDataStructure;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;


import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import unequalLetterCostCode.Signature;


/**
 * This class implements a containment hierarchy node which can occur anywhere
 * except the leaves of the Containment Hierarchy. The selection group
 * associated with such a node must be a <b>non-trivial</b> selection group,
 * which means it must preserve containment (it must contain exactly the union
 * of the members of the selection group of each child).
 * 
 * @author Melanie Baljko
 */
// public class InternalNode<SelectionGroupMember extends AbstractButton>
// extends Node<SelectionGroupMember> {
public class InternalNode extends Node implements Serializable {

	private final static int DEFAULT_COST = 1;

	// represent children in a vector, the children are also CHN's
	private List<Node> children;

	private List<Integer> edgeCosts;

	// the position within the vector gives the child number, which also gives
	// which character from the encoding alphabet is used to label the edge in
	// this tree

	public InternalNode() {
		this(new NontrivialSelectionGroup());
	}

	/**
	 * This constructor is used when building a containment hierarchy. Nodes
	 * constructed in the following manner are internal nodes, to which other
	 * internal nodes or leaves are added (using the
	 * addChild(ContainmentHeirarchyNode) method). The value of this node (it's
	 * associated SelectionGroup) is updated whenever children are added to it.
	 */
	public InternalNode(NontrivialSelectionGroup ntsg) {
		children = new Vector<Node>();
		edgeCosts = new Vector<Integer>();
		parent = null;

		// associatedNodeSet = new NontrivialSelectionGroup();
		super.associatedNodeSet = ntsg;
		super.setNodeID("0");
		setProbabilityofOccurrence(0);
		setNumDescendentLevels(0);
		setDepth(0);
	}

	public InternalNode clone() {
		// List<Integer> edgeCostsCopy = new Vector<Integer>();
		// for (Integer i: edgeCosts) {
		// edgeCostsCopy.add(i);
		// }
		InternalNode nodeCopy = new InternalNode();
		nodeCopy.setProbabilityofOccurrence(this.getProbabilityofOccurrence());
		for (Node n : getChildren()) {
			nodeCopy.addChild(n.clone(), getCost(n));
		}

		return nodeCopy;
	}

	/**
	 * This method is used when building a containment hierarchy. It should only
	 * ever be invoked on an internal node.
	 * 
	 * The position of this child in the tree is given by the order in which
	 * this method was invoked for the children.
	 * 
	 * 
	 * @param node
	 */
	public void addChild(Node node) {
		addChild(node, DEFAULT_COST);
	}

	/**
	 * This method is used when building a containment hierarchy. It should only
	 * ever be invoked on an internal node.
	 * 
	 * The position of this child in the tree is given by the order in which
	 * this method was invoked for the children.
	 * 
	 * 
	 * @param node
	 */
	public void addChild(Node node, int edgeCost) {
		if (associatedNodeSet == null) {
			associatedNodeSet = new NontrivialSelectionGroup(node
					.getSelectionGroup());
			children = new Vector<Node>();
		} else {
			associatedNodeSet.add(node.getSelectionGroup());
		}
		Node tmpParent = parent;
		while (tmpParent != null) {
			tmpParent.associatedNodeSet = null;
			for (Node n : tmpParent.getChildren()) {
				if (tmpParent.associatedNodeSet == null) {
					tmpParent.associatedNodeSet = new NontrivialSelectionGroup(
							n.getSelectionGroup());
				} else {
					tmpParent.associatedNodeSet.add(n.getSelectionGroup());
				}
			}
			tmpParent = tmpParent.parent;
		}
		// children.insertElementAt(chn,0);
		children.add(node);
		edgeCosts.add(edgeCost);
		setNumDescendentLevels(node.getNumDescendentLevels() + 1);
		setProbabilityofOccurrence(getProbabilityofOccurrence()
				+ node.getProbabilityofOccurrence());
		// side effects
		node.setDepth(getEdgeCostToHere() + edgeCost);
		node.setParent(this);
		// super.propogateLabels(this.getNodeID());
	}

	/*
	 * public InternalNode ConvertToInternalNode() { }
	 */
	/**
	 * 
	 * @param childNum :
	 *            starting from 0
	 * 
	 * PRE that childNum is well defined i.e., 0<=childNum<getChildren().size()
	 * @return
	 */
	public int getCost(int childNum) {
		return edgeCosts.get(childNum);
	}

	public int getNumOfChildren() {
		return children.size();
	}

	public int getCost(Node childNode) {
		// find position of childNode among the children
		int pos = getChildren().indexOf(childNode);
//		System.out
//				.println("cost of " + childNode + " is " + edgeCosts.get(pos));
		return edgeCosts.get(pos);
	}

	public SourceSymbol getRepresentative() {
		return children.get(0).getRepresentative();
	}

	/**
	 * @return the first child of this node or null if it is a leaf (actually,
	 *         the app crashes in this case - fix this)
	 */
	public Node getFirstChild() {
		return children.get(0);
	}

	public List<Node> getChildren() {
		return children;
	}

	public Node getChild(int index) {
		return children.get(index);
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
		associatedNodeSet.setID(_lab);
		setNodeID(_lab);
		for (int i = 0; i < getChildren().size(); i++) {
			getChildren().get(i).propogateLabels(_lab + "." + i);
		}
	}

	public void propogateLabels(boolean isLabelled) {
		if (!isLabelled) {
			associatedNodeSet.setID("");
			setNodeID("");
			for (int i = 0; i < getChildren().size(); i++) {
				getChildren().get(i).propogateLabels(false);
			}
		}
	}

	@Override
	public void propogateConditionalProbabilities(SourceSymbol goal,
			double probFollowCorrectEdge, double probFollowIncorrectEdge) {

		if (goal.equals(JIndirectSelectionButton.VK_DEL)) {
			// the goal is delete; we assume that there is no chance of
			// incorrectly selecting another leaf when the goal is delete
			for (Node child : this.getChildren()) {
				if (child.getSelectionGroup().contains(
						JIndirectSelectionButton.VK_DEL)) {
					child.setConditionalProbability(goal, 1.00);
				} else {
					child.setConditionalProbability(goal, 0.0);
				}
				child.propogateConditionalProbabilities(goal,
						probFollowCorrectEdge, probFollowIncorrectEdge);
			}
		} else if (!this.getSelectionGroup().contains(goal)) {
			// the goal is not in this node's selection set
			// we assume there is no chance of delete being selected when the
			// goal is a letter
			// there is no goal letter to be found rooted at this node; we
			// assume the user just selects the first possible child (that isn't
			// delete)
			List<Node> editedChildren = new Vector<Node>();
			for (Node child : this.getChildren()) {
				if (child.getRepresentative().equals(
						JIndirectSelectionButton.VK_DEL)
						&& child.isLeaf()) {
					child.setConditionalProbability(goal, 0.0);
				} else {
					editedChildren.add(child);
				}
				child.propogateConditionalProbabilities(goal,
						probFollowCorrectEdge, probFollowIncorrectEdge);
			}
			if (editedChildren.size() == 1) {
				// System.out.println("***" + editedChildren.get(0));
				editedChildren.get(0).setConditionalProbability(goal, 1.00);
			} else {
				for (Node child : this.getChildren()) {
					if (child == this.getFirstChild()) {
						child.setConditionalProbability(goal,
								probFollowCorrectEdge);
					} else {
						child.setConditionalProbability(goal,
								probFollowIncorrectEdge
										/ (editedChildren.size() - 1));
					}
				}
			}
		} else if (this.getSelectionGroup().contains(goal)) {
			// the goal is in this node's selection set
			// we assume there is no chance of delete being selected when the
			// goal is a letter
			List<Node> editedChildren = new Vector<Node>();
			for (Node child : this.getChildren()) {
				if (child.getRepresentative().equals(
						JIndirectSelectionButton.VK_DEL)
						&& child.isLeaf()) {
					child.setConditionalProbability(goal, 0.0);
				} else {
					editedChildren.add(child);
				}
				child.propogateConditionalProbabilities(goal,
						probFollowCorrectEdge, probFollowIncorrectEdge);
			}
			if (editedChildren.size() == 1) {
				// System.out.println("***" + editedChildren.get(0));
				editedChildren.get(0).setConditionalProbability(goal, 1.00);
			} else {
				for (Node child : this.getChildren()) {
					if (child.getSelectionGroup().contains(goal)) {
						// System.out.println("y");
						child.setConditionalProbability(goal,
								probFollowCorrectEdge);
					} else {
						// System.out.println("n");
						child.setConditionalProbability(goal,
								probFollowIncorrectEdge
										/ (editedChildren.size() - 1));
					}
				}
			}
		}

	}

	/**
	 * This method implements the truncate operation as described by Golin &
	 * Rote, 1998, p.1773
	 * 
	 * Basically, the method returns the tree rooted at this node such that each
	 * node's parent is at depth \leq level.
	 * 
	 * Trunc_{0} refers to the root and all of its children
	 * 
	 * If a node's parent is placed at a depth greater than the passed level,
	 * then that node is removed.
	 * 
	 * The depth of a node is the sum of the lengths of the edges on the path
	 * conecting the root (this node) to the node.
	 * 
	 * PRE we assume that this method is being invoked on the root, so that the
	 * getDepth() method returns a valid value
	 * 
	 * The resulting truncated tree may not necessarily have the containment
	 * hierarchy property (i.e., where, among other things, the selection groups
	 * that are associated with leaves must be trivial).
	 * 
	 * @param level
	 *            \geq 0
	 * @return
	 */
	public Node truncate(int level) {
		Node rootOfTruncatedTree = null;
		return rootOfTruncatedTree;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("*** Internal Node:" + super.getNodeID());
		buf.append("\n\tDepth: " + super.getEdgeCostToHere());
		buf.append("\n\tMembers: " + getSelectionGroup().toString());
		return buf.toString();
	}

	public String toStringProbLaTeX() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\chunk{\\begin{bundle}{" + "[]" + "$_{"
				+ toStringExpectedFrequency() + "}$" + "}");
		// buf.append("\\chunk{\\begin{bundle}{" + getNodeID() + "$_{"
		// + toStringExpectedFrequency() + "}$" + "}");
		for (Node c : getChildren()) {
			// if (!c.isDummyNode())
			buf.append(" " + c.toStringProbLaTeX());
		}
		buf.append("\\end{bundle}}");
		return buf.toString();
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	/*
	 * This method returns the signature for a tree, as described on page 1773
	 * of Golin 98. It assumes that the node for which it is called is the root
	 * of a tree. It calculates the ith-level signature starting from that node.
	 * 
	 * @param i i is the ith level. @return A list <m, l1, l2, ......, lc>
	 * @author Fatima
	 */
	// public List<Integer> getSignature(int i)
	public Signature getSignature(int i) {
		int C = getMaxEdgeCost();
		// List<Integer> signature = new Vector<Integer>();
		Signature signature = new Signature();
		// initialize
		for (int j = 0; j < (C + 1); j++) {
			signature.add(0);
		}

		for (Node child : children) {
			getSubSignature(child, i, signature);
		}

		return signature;

	}

	// private List<Integer> getSubSignature(Node<SelectionGroupMember> node,
	// int i, List<Integer>
	// sig )
	// private void getSubSignature(Node<SelectionGroupMember> node, int i,
	// List<Integer> sig )
	private void getSubSignature(Node node, int i, Signature sig) {

		if (node.isLeaf() && node.getEdgeCostToHere() <= i) {

			int m = 1 + sig.get(0);
			sig.setM(m);
			// sig.add(0, m);
			return;
		} else if (node.isLeaf() && node.getEdgeCostToHere() > i) {
			int index = node.getEdgeCostToHere() - i;

			int Li;
			if (index < sig.size()) {
				Li = 1 + sig.get(index);
			} else {
				Li = 1;
			}
			sig.set(index, Li);
			return;
		} else if (!node.isLeaf()) {
			for (Node child : children) {
				getSubSignature(child, i, sig);
			}
			return;
		}

		return;
	}

	/*
	 * It returns the maximum Cost C of a node in the tree, rooted at this
	 * internal node.
	 */
	public int getMaxEdgeCost() {
		int max = -1;
		for (int i : edgeCosts) {
			if (i > max) {
				max = i;
			}

		}
		return max;
	}

	/*
	 * This method implements that Expand function as described on page 1775 of
	 * Golin'98. The qth expansion of tree T at level i+1 is the tree
	 * constructed by making any q of the leaves at level i+1 of T internal
	 * nodes with r children.
	 * 
	 * We will take the first q leaves and make them internal nodes
	 * 
	 * @pre condition 0 <= q <= l1
	 * 
	 */
	public InternalNode expand(int i, int q, List<SourceSymbol> rChildSymbols,
			List<Integer> rChildCosts) {
		InternalNode nodeExpanded = this.clone();
		Signature signature = getSignature(i);
		int l1 = signature.get(1);
		if (q >= 0 && q <= l1) {
			subExpand(nodeExpanded, i, q, 0, rChildSymbols, rChildCosts);

		}

		return nodeExpanded;

	}

	public InternalNode getTreeAtLevel0(List<SourceSymbol> rChildSymbols,
			List<Integer> rChildCosts) {
		InternalNode newNode = new InternalNode();
		for (int j = 0; j < rChildCosts.size(); j++) {
			newNode.addChild(new LeafNode(rChildSymbols.get(j)), rChildCosts
					.get(j));

		}
		return newNode;

	}

	public List<Node> getleavesAtKBLLevel(int level) {
		List<Node> leaves = new Vector<Node>();
		subGetleavesAtKBLLevel(this, 0, level, leaves);
		return leaves;
	}

	public int subGetleavesAtKBLLevel(Node node, int curMaxLevel, int level,
			List<Node> list) {
		if (node.isLeaf()) {
			if (curMaxLevel == level) {
				list.add(node);
			}
			return curMaxLevel;

		} else {
			int maxLevel = 0;
			int tempMaxLevel = curMaxLevel + 1;
			// curMaxLevel += 1;
			for (Node child : node.getChildren()) {
				maxLevel = subGetleavesAtKBLLevel(child, tempMaxLevel, level,
						list);

				if (curMaxLevel < maxLevel) {
					curMaxLevel = maxLevel;
				}

			}
			return curMaxLevel;

		}

	}

	public List<Node> getNodesAtKBLLevel(int level) {
		List<Node> leaves = new Vector<Node>();
		subGetNodesAtKBLLevel(this, 0, level, leaves);
		return leaves;
	}

	public int subGetNodesAtKBLLevel(Node node, int curMaxLevel, int level,
			List<Node> list) {
		if (node.isLeaf()) {
			if (curMaxLevel == level) {
				list.add(node);
			}
			return curMaxLevel;

		} else {

			if (curMaxLevel == level) {
				list.add(node);
			} else {
				int maxLevel = 0;
				int tempMaxLevel = curMaxLevel + 1;
				// curMaxLevel += 1;

				for (Node child : node.getChildren()) {

					maxLevel = subGetNodesAtKBLLevel(child, tempMaxLevel,
							level, list);

					if (curMaxLevel < maxLevel) {
						curMaxLevel = maxLevel;
					}

				}
			}
			return curMaxLevel;

		}

	}

	public List<Node> getleavesAtLevel(int level) {
		List<Node> leaves = new Vector<Node>();
		subGetleavesAtLevel(this, level, leaves);
		return leaves;
	}

	public List<Node> subGetleavesAtLevel(Node node, int level, List<Node> list) {
		if (node.isLeaf()) {
			if (node.getEdgeCostToHere() == level) {
				list.add(node);
			}
			return list;
		} else {
			if (node.getEdgeCostToHere() < level) {

				for (Node child : node.getChildren()) {
					// List<Node<SelectionGroupMember>> subList =
					// subGetleavesAtLevel(child,
					// level,list);
					subGetleavesAtLevel(child, level, list);
					// if(!subList.isEmpty())
					// list.addAll(subList);
				}

			}
			return list;
		}

	}

	public List<InternalNode> getInternalNodesAtLevel(int level) {
		List<InternalNode> nodes = new Vector<InternalNode>();
		subGetInternalNodesAtLevel(this, level, nodes);
		return nodes;
	}

	public List<InternalNode> subGetInternalNodesAtLevel(Node node, int level,
			List<InternalNode> list) {
		if (node.isLeaf()) {
			return list;
		} else {
			if (node.getEdgeCostToHere() < level) {

				for (Node child : node.getChildren()) {
					// List<Node<SelectionGroupMember>> subList =
					// subGetleavesAtLevel(child,
					// level,list);
					subGetInternalNodesAtLevel(child, level, list);
					// if(!subList.isEmpty())
					// list.addAll(subList);
				}

			} else if (node.getEdgeCostToHere() == level) {
				list.add((InternalNode) node);
			}

			return list;
		}

	}

	/*
	 * 
	 */
	public InternalNode expandTreeAtLevelI2(int i, int q,
			List<SourceSymbol> rChildSymbols, List<Integer> rChildCosts) {
		InternalNode nodeExpanded = this.clone();
		int depth = nodeExpanded.getEdgeCostToHere();
		Node nextNode = nodeExpanded;
		// Node<SelectionGroupMember> parent = nodeExpanded;

		System.out.println("entring expand");

		while (depth < i) {
			// parent = child;
			if (!nextNode.isLeaf()) {
				nextNode = ((InternalNode) nextNode).getFirstChild();
			} else {
				nextNode = nextNode.getNextSibling();
			}
			depth = nextNode.getEdgeCostToHere();
		}

		for (int index = 1; index <= q; index++) {
			while (!nextNode.isLeaf()) {
				nextNode = nextNode.getNextSibling();

			}

			List<Node> v = (nextNode.parent).getChildren();
			int childNum = v.indexOf(nextNode);

			InternalNode newNode = ((LeafNode) nextNode)
					.ConvertToInternalNode();
			// newNode.setDepth(nextNode.getDepth()) ;
			for (int j = 0; j < rChildCosts.size(); j++) {

				newNode.addChild(new LeafNode(rChildSymbols.get(j)),
						rChildCosts.get(j));
			}
			setChildToParent(((InternalNode) nextNode.parent), newNode,
					childNum);
			nextNode = newNode.getNextSibling();
		}
		System.out.println("leaving expand");
		return nodeExpanded;

	}

	/*
	 * 
	 */
	public InternalNode expandTreeAtLevelI(int i, int q,
			List<SourceSymbol> rChildSymbols, List<Integer> rChildCosts) {
		InternalNode nodeExpanded = this.clone();

		int oldlevel = i;
		// int maxLeafLevel = nodeExpanded.calculateMaximumLeafLevel();
		int maxLeafDepth = nodeExpanded.calculateMaximumLeafDepth(0);

		if (maxLeafDepth < i) {
			i = maxLeafDepth;

			System.out
					.println("Max level changed to " + i + "from " + oldlevel);
		}

		List<Node> leaves = nodeExpanded.getleavesAtLevel(i);
		for (int index = 0; index < q; index++) {
			Node prevLeaf = leaves.get(index);
			List<Node> v = (prevLeaf.parent).getChildren();
			int childNum = v.indexOf(prevLeaf);

			InternalNode newNode = ((LeafNode) prevLeaf)
					.ConvertToInternalNode();
			// newNode.setDepth(nextNode.getDepth()) ;
			for (int j = 0; j < rChildCosts.size(); j++) {

				newNode.addChild(new LeafNode(rChildSymbols.get(j)),
						rChildCosts.get(j));
			}
			setChildToParent(((InternalNode) prevLeaf.parent), newNode,
					childNum);

		}
		return nodeExpanded;

		/*
		 * while(depth < i) { //parent = child; if(!nextNode.isLeaf()) {
		 * nextNode = ((InternalNode)nextNode).getFirstChild(); } else {
		 * nextNode = nextNode.getNextSibling(); } depth = nextNode.getDepth(); }
		 * 
		 * for(int index = 1; index <=q; index ++) { while(!nextNode.isLeaf() ) {
		 * nextNode = nextNode.getNextSibling(); }
		 * 
		 * List<Node<SelectionGroupMember>> v =
		 * (nextNode.parent).getChildren(); int childNum = v.indexOf(nextNode);
		 * 
		 * InternalNode newNode = ((LeafNode<SelectionGroupMember>)nextNode).ConvertToInternalNode();
		 * //newNode.setDepth(nextNode.getDepth()) ; for(int j = 0; j <
		 * rChildCosts.size(); j++) {
		 * 
		 * newNode.addChild(new LeafNode<SelectionGroupMember>(rChildSymbols
		 * .get(j)), rChildCosts.get(j)); } setChildToParent((
		 * (InternalNode)nextNode.parent), newNode, childNum); nextNode =
		 * newNode.getNextSibling(); } System.out.println("leaving expand");
		 * return nodeExpanded;
		 */

	}

	public int subExpand(Node node, int i, int q, int nodePosition,
			List<SourceSymbol> rChildSymbols, List<Integer> rChildCosts) {
		if (q <= 0) {
			return q;
		} else if (node.isLeaf() && node.getEdgeCostToHere() == (i + 1) && q > 0) {
			InternalNode newNode = ((LeafNode) node).ConvertToInternalNode();
			for (int index = 0; index < rChildCosts.size(); index++) {
				newNode.addChild(new LeafNode(rChildSymbols.get(index)),
						rChildCosts.get(index));
			}

			setChildToParent(((InternalNode) node.parent), newNode,
					nodePosition);

			q = q - 1;

			return q;
		} else if (!node.isLeaf()) {
			int newQ = q;

			for (int index = 0; index < ((InternalNode) node)
					.getNumOfChildren(); index++) {
				Node child = ((InternalNode) node).getChild(index);

				int valQ = subExpand(child, i, newQ, index, rChildSymbols,
						rChildCosts);
				newQ = valQ;

				if (newQ == 0)
					break;

			}

		}

		return q;
	}

	public void setChildToParent(InternalNode parent, Node child, int index) {
		parent.setChild(index, child);
		child.parent = parent;
	}

	public void setChild(int index, Node node) {
		children.set(index, node);
	}

	public int getNumberOfChildren() {
		return children.size();
	}

	public InternalNode updateAssociatedNodeSet() {
		InternalNode copyNode = this.clone();
		SelectionGroup tempSg = subUpdateAssociatedNodeSet(copyNode);
		copyNode.setNodeSeelctionGroup(tempSg);

		return copyNode;

	}

	public SelectionGroup subUpdateAssociatedNodeSet(Node node) {
		if (node.isLeaf()) {
			return node.getSelectionGroup();
		} else {
			SelectionGroup combinedGroup = new NontrivialSelectionGroup();
			for (int i = 0; i < ((InternalNode) node).getNumberOfChildren(); i++) {
				SelectionGroup tempSg = subUpdateAssociatedNodeSet(((InternalNode) node)
						.getChild(i));
				(((InternalNode) node).getChild(i))
						.setNodeSeelctionGroup(tempSg);
				combinedGroup.add(tempSg);
			}
			return combinedGroup;
		}
	}

	/*
	 * This method implements the Signature(i+1) of T as described on pare 1775
	 * of Golin '98)
	 */
	public List<Integer> getNextLevelSignature(List<Integer> iSignature,
			List<Integer> characteristicVec, int q) {
		List<Integer> nextSignature = new Vector<Integer>();
		nextSignature.add(0, iSignature.get(0) + iSignature.get(1)
				+ (q * characteristicVec.get(0)));
		// (m+l1;....) + q.(-1;....)

		for (int index = 1; index < (iSignature.size() - 1); index++) {
			nextSignature.add(index, iSignature.get(index + 1)
					+ (q * characteristicVec.get(index)));

		}
		nextSignature.add((iSignature.size() - 1), 0 + (q * characteristicVec
				.get(characteristicVec.size() - 1)));
		return nextSignature;
	}

	public int calculateMaximumLeafDepth(int maxDepth) {

		int result = subMaximumDepth(this, maxDepth);
		return result;

	}

	public int subMaximumDepth(Node node, int curMaxDepth) {
		if (node.isLeaf()) {
			int maxDepth = node.getEdgeCostToHere();
			if (curMaxDepth < maxDepth) {
				curMaxDepth = maxDepth;
			}
			return curMaxDepth;
		} else {
			int maxDepth = 0;
			for (Node child : node.getChildren()) {
				maxDepth = subMaximumDepth(child, curMaxDepth);
				if (curMaxDepth < maxDepth) {
					curMaxDepth = maxDepth;
				}

			}
			return curMaxDepth;
		}

	}

	/*
	 * It calculates the maximum level for a leaf, considering each an increment
	 * of 1 for each level, and not considering the actual cost and length of
	 * each edge.
	 * 
	 * This method facilitates the keyboard layout method It is assumed that the
	 * level of root is 0, and each child node is considered to be at level =
	 * level +1
	 */

	public int calculateMaximumLeafLevel() {
		int rootLevel = 0;
		int result = subMaximumLevel(this, rootLevel);
		return result;

	}

	public int subMaximumLevel(Node node, int curMaxLevel) {
		if (node.isLeaf()) {

			return curMaxLevel;
		} else {
			int maxLevel = 0;
			int tempMaxLevel = curMaxLevel + 1;
			// curMaxLevel += 1;
			for (Node child : node.getChildren()) {
				maxLevel = subMaximumLevel(child, tempMaxLevel);
				if (curMaxLevel < maxLevel) {
					curMaxLevel = maxLevel;
				}

			}
			return curMaxLevel;

		}

	}

	/**
	 * substitute a leaf node with an internal node
	 * 
	 * @param prevNode
	 * @param newNode
	 */
	public void removeLeafChild(LeafNode prevNode) {
		int pos = getChildren().indexOf(prevNode);
		// System.out.println(edgeCosts.get(pos));
		children.remove(pos);
		edgeCosts.remove(pos);
		associatedNodeSet = null;
		for (Node node : getChildren()) {
			if (associatedNodeSet == null) {
				associatedNodeSet = new NontrivialSelectionGroup(node
						.getSelectionGroup());
			} else {
				associatedNodeSet.add(node.getSelectionGroup());
			}
		}
		//System.out.println("ass node set:" + associatedNodeSet);

	}
	/*
	 * ( private void getSubSignature(Node<SelectionGroupMember> node, int i,
	 * List<Integer> sig ) {
	 * 
	 * if(i == -1 ) { //return 0; getSubSignatureL(node, 1 , sig ); return; }
	 * else if( node.isLeaf() && i >=0 ) {
	 * 
	 * int sum = 1; sum = sum + sig.get(0); sig.add(0, sum); return; } else if
	 * (!node.isLeaf()) { int sum = 0; for(Node<SelectionGroupMember> child :
	 * children) { getSubSignature(child, i-1, sig); } return; }
	 * 
	 * return; }
	 */
	/*
	 * private void getSubSignatureL(Node<SelectionGroupMember> node, int i,
	 * List<Integer> sig ) { if( node.isLeaf()) {
	 * 
	 * int sum = 1; sum = sum + sig.get(0); sig.add(0, sum); return; } else if
	 * (!node.isLeaf()) { int sum = 0; for(Node<SelectionGroupMember> child :
	 * children) { getSubSignature(child, i-1, sig); } return; }
	 * 
	 * return; }
	 */
	/*
	 * private int getSubSignature(Node<SelectionGroupMember> node, int i ) {
	 * 
	 * if(i < 0) { return 0; } else if( node.isLeaf() && i >=0 ) {
	 * 
	 * return 1; } else if (!node.isLeaf()) { int sum = 0; for(Node<SelectionGroupMember>
	 * child : children) { sum += getSubSignature(child, i-1); } return sum; }
	 * 
	 * return 0; }
	 */
}
