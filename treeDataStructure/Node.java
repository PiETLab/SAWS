/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package treeDataStructure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;

import encodingTrees.CodeWord;

/**
 * This class implements a node (which will be placed in a tree).
 * 
 * Each node has (a) a value, which is an instance of a SelectionGroup, and (b)
 * an expected frequency
 * 
 * If a node is a leaf in the hierarchy, then its value <b>must</b> be a trivial
 * SelectionGroup. If a node is internal node in the hierarchy, then its value
 * <b>must</b> be a non-trivial SelectionGroup. The child classes of this class
 * ensure that this property is maintained.
 * 
 * @author Melanie Baljko
 */
// public abstract class Node<SelectionGroupMember extends AbstractButton> {
public abstract class Node implements Serializable {

	private static JIndirectSelectionButton FILLER_SOURCE_SYMBOL = JIndirectSelectionButton.VK_FILLER;

	private long timeStampOfFokusReception;
	private long timeStampOfFokusDeparture;

	// represent children in a vector, the children are also CHN's
	// private Vector<Node> children;

	public long getTimeStampOfFokusReception() {
		return timeStampOfFokusReception;
	}

	public long getTimeStampOfFokusDeparture() {
		return timeStampOfFokusDeparture;
	}

	// for this node, need to represent the likelihood that a user will
	// travel to it, given node/source symbol x was the target
	// for the root, this isn't defined.
	private Map<SourceSymbol, Double> incomingEdgeConditionalProbability;

	public Node parent;

	private double probabilityOfOccurence;

	private String nodeID;

	protected String symbolFromEncodingAlphabet;
	// the encoding alphabet that represents this node and was
	// assigned to this node during the process of finding code words
	// before assigning the actual words.

	protected SelectionGroup associatedNodeSet;

	// this value is the number of descendent levels from this node
	// if the node is not initialized, the value is -1
	private int numDescendentLevels;

	// this value is the depth of this node,
	// if this node is the root, its depth is 0;
	private int depth;

	// a feild introduced to facilitate layout creation, according
	// to no. of horizontal and vertical children
	private double percentageOfHDescendants;
	private double percentageOfVDescendants;

	protected Node() {
		incomingEdgeConditionalProbability = new HashMap<SourceSymbol, Double>();
	}

	public void setNumDescendentLevels(int val) {
		numDescendentLevels = val;
	}

	public int getNumDescendentLevels() {
		return numDescendentLevels;
	}

	/**
	 * @return the depth of this node in the containment hierarchy. The root has
	 *         depth 0.
	 */
	public int getEdgeCostToHere() {
		return depth;
	}

	public void setDepth(int _d) {
		depth = _d;
		if (!isLeaf()) {
			for (Node n : getChildren()) {
				n.setDepth(_d + ((InternalNode) this).getCost(n));
			}
		}
	}

	public void setPercentageOfHDescendants(double val) {
		percentageOfHDescendants = val;
	}

	public double getPercentageOfHDescendants() {
		return percentageOfHDescendants;
	}

	public void setPercentageOfVDescendants(double val) {
		percentageOfVDescendants = val;
	}

	public double getPercentageOfVDescendants() {
		return percentageOfVDescendants;
	}

	public void setNodeSeelctionGroup(SelectionGroup sg) {
		associatedNodeSet = sg;
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
	public abstract void propogateLabels(String _lab);

	public abstract void propogateLabels(boolean isLabelled);

	// public abstract void resortChildrenOrderByEdgeCosts();

	public abstract void propogateConditionalProbabilities(SourceSymbol goal,
			double probFollowCorrectEdge, double probFollowIncorrectEdge);

	public double getProbabilityofOccurrence() {
		return probabilityOfOccurence;
	}

	public double getConditionalProbability(SourceSymbol target) {
		return incomingEdgeConditionalProbability.get(target);
	}

	public double toStringExpectedFrequency() {
		// return expectedFrequency;
		return myRound(probabilityOfOccurence, 2);
	}

	public static double myRound(double d, int numPlaces) {
		// Might be a number like 0.899999999999 want to round to three decimal
		double d2 = d * Math.pow(10, numPlaces);
		return (1.0 * Math.round(d2)) / Math.pow(10, numPlaces);
	}

	public void setProbabilityofOccurrence(double val) {
		probabilityOfOccurence = val;
	}

	public void setConditionalProbability(SourceSymbol target, double val) {
		incomingEdgeConditionalProbability.put(target, val);
		// System.out.println(this.getNodeSelectionGroup().extractMembers() +
		// "\t"
		// + "| \t" + target.toStringOneChar() + " = \t"
		// + getConditionalProbability(target));
	}

	public String getEncodingAlphabet() {
		return symbolFromEncodingAlphabet;
	}

	public void setEncodingAlphabet(String s) {
		symbolFromEncodingAlphabet = s;
	}

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String s) {
		nodeID = s;
	}

	public SelectionGroup getSelectionGroup() {
		return associatedNodeSet;
	}

	public int getSelectionGroupSize() {
		return (associatedNodeSet.extractMembers()).size();
	}

	public void setParent(Node chn) {
		parent = chn;
	}

	public abstract boolean isLeaf();

	public abstract int calculateMaximumLeafLevel();

	/**
	 * This method finds the subtree of the tree rooted here that has the
	 * largest outdegree
	 * 
	 * @param currMax
	 * @return
	 */
	public int findLargestOutdegree(int currMax) {
		if (isLeaf()) {
			return 0;
		} else {
			int numEdges = getChildren().size();
			if (numEdges > currMax) {
				currMax = numEdges;
			}
			for (Node c : getChildren()) {
				numEdges = c.findLargestOutdegree(currMax);
				if (numEdges > currMax) {
					currMax = numEdges;
				}
			}
			return currMax;
		}
	}

	/**
	 * This method finds the subtree of the tree rooted here that has the
	 * largest outdegree, excluding those nodes which are parents nodes for
	 * which all children are leaves
	 * 
	 * @param currMax
	 * @return
	 */
	public int findLargestSelectionGroup(int currMax) {
		int newMax = 0;
		if (isLeaf()) {
			newMax = currMax;
		} else {
			boolean isParentOfOnlyLeaves = true;
			for (Node c : getChildren()) {
				if (!c.isLeaf()) {
					isParentOfOnlyLeaves = false;
					break;
				}
			}
			if (isParentOfOnlyLeaves) {
				newMax = currMax;
			} else {
				int possibleNewMax = this.findLargestSelectionGroup(currMax);
				if (possibleNewMax > currMax) {
					newMax = possibleNewMax;
				}
				for (Node c : getChildren()) {
					possibleNewMax = c.findLargestSelectionGroup(currMax);
					if (possibleNewMax > currMax) {
						currMax = possibleNewMax;
					}
				}
			}
		}
		return newMax;
	}

	/**
	 * @return the first child of this node or null if it is a leaf (actually,
	 *         the app crashes in this case - fix this)
	 */
	public abstract Node getFirstChild();

	public abstract List<Node> getChildren();

	public abstract SourceSymbol getRepresentative();

	/**
	 * This method returns the immediate child whose selection group contains
	 * the source symbol.
	 * 
	 * @param sourceSymbol
	 * @return
	 */
	public Node getChildImmediateNodeByContents(SourceSymbol sourceSymbol) {
		Node found = null;
		for (Node currChild : this.getChildren()) {
			SelectionGroup sg = currChild.getSelectionGroup();
			if (sg.contains(sourceSymbol)) {
				found = currChild;
			}
		}
		if (found == null) {
			throw new RuntimeException("SG not found for button: "
					+ sourceSymbol);
		}
		return found;
	}

	/**
	 * This method returns the immediate child whose selection group contains
	 * the source symbol. If no such child is found, then a RuntimeException is
	 * thrown.
	 * 
	 * @param sourceSymbol
	 * @return
	 */
	public Node getChildLeafByContents(SourceSymbol sourceSymbol) {
		Node found = null;
		for (Node currChild : getChildren()) {
			SelectionGroup sg = currChild.getSelectionGroup();
			if (sg.contains(sourceSymbol)) {
				found = currChild;
				if (found.isLeaf()) {
					return found;
				} else {
					return found.getChildLeafByContents(sourceSymbol);
				}
			}
		}
		if (found == null) {
			throw new RuntimeException("SG not found for button: "
					+ sourceSymbol);
		}
		return found;
	}

	/**
	 * This method returns a list of nodes that represents the path from this
	 * node to the terminus, appended to pathSoFar. If the terminus is a child
	 * of this node, then the list will be appended with those two elements. If
	 * terminus cannot be reached from this node, then pathSoFar is returned.
	 * 
	 * @param terminus
	 * @return
	 */
	public List<Node> getPath(Node terminus, List<Node> pathSoFar) {
		if (this.getSelectionGroup().contains(terminus.getRepresentative())) {
			// find the child whose SG contains terminus's symbol
			Node n = this.getChildImmediateNodeByContents(terminus
					.getRepresentative());
			pathSoFar.add(n);
			// System.out.println(pathSoFar);
			if (n.isLeaf()) {
				return pathSoFar;
			} else {
				return n.getPath(terminus, pathSoFar);
			}
		} else {
			return pathSoFar;
		}
	}

	/**
	 * @param sourceSymbol
	 * @return true if and only if the passed SelectionGroupMember is associated
	 *         with this node.
	 */
	public boolean contains(SourceSymbol sourceSymbol) {
		return getSelectionGroup().contains(sourceSymbol);
	}

	public abstract Node clone();

	// return false if chn is not a child of this instance
	public boolean isChild(Node chn) {
		return getChildren().contains(chn);
	}

	/**
	 * 
	 * @return the next sibling of this node, if it has one. If not, returns
	 *         null.
	 */
	public Node getNextSibling() {
		Node found = null;
		// find which sibling number this instance is
		// System.out.println("CHN: getting next sib for:\n" + this);
		List<Node> v = parent.getChildren();

		int childNum = v.indexOf(this);
		// if we are the last sibling, start again
		if (childNum == v.size() - 1)
			found = (Node) v.get(0);
		else
			found = (Node) v.get(childNum + 1);

		// if found is a filler node, then call
		return found;
	}

	public Node getPrevSibling() {
		Node found = null;
		// find which sibling number this instance is
		// System.out.println("CHN: Getting prev sib for:\n" + this);
		List<Node> v = parent.getChildren();

		int childNum = v.indexOf(this);
		// if we are the last sibling, start again
		if (childNum == 0)
			found = (Node) v.get(v.size() - 1);
		else
			found = (Node) v.get(childNum - 1);
		return found;
	}

	public Node getLastSibling() {
		Node found = null;
		// find which sibling number this instance is
		// System.out.println("CHN: Getting last sib for:\n" + this);
		List<Node> v = parent.getChildren();
		found = (Node) v.get(v.size() - 1);
		return found;
	}

	public Node getFirstSibling() {
		Node found = null;
		// find which sibling number this instance is
		// System.out.println("CHN: Getting first sib for:\n" + this);
		List<Node> v = parent.getChildren();
		found = (Node) v.get(0);
		return found;
	}

	/**
	 * This method returns the Code that is implicitly represented by the
	 * subtree that is rooted at this node.
	 * 
	 * @return
	 */
	public List<CodeWord> getCode() {
		final List<String> PREFIX1 = new Vector<String>();
		final List<String> PREFIX2 = new Vector<String>();
		final List<Integer> PREFIX3 = new Vector<Integer>();
		return getCodeRootedHere(PREFIX1, PREFIX2, PREFIX3);
		// return null;
	}

	/**
	 * This method returns the Code that is implicitly represented by the
	 * subtree that is rooted at this node, where each codeword is prepended
	 * with the passed prefix
	 * 
	 * @param prefix1
	 * @return
	 */
	private List<CodeWord> getCodeRootedHere(List<String> prefix1,
			List<String> prefix2, List<Integer> prefix3) {
		List<CodeWord> list = new Vector<CodeWord>();
		if (isLeaf()) {
			list.add(new CodeWord(prefix1, prefix2, prefix3,
					getSelectionGroup().getFirst(),
					getProbabilityofOccurrence()));
		} else {
			int childNum = 0;
			for (Node c : getChildren()) {
				List<String> newPrefix1 = new Vector<String>();
				newPrefix1.addAll(prefix1);
				newPrefix1.add("" + childNum);
				List<String> newPrefix2 = new Vector<String>();
				newPrefix2.addAll(prefix2);
				newPrefix2.add("" + getChildren().size());
				List<Integer> newPrefix3 = new Vector<Integer>();
				newPrefix3.addAll(prefix3);
				newPrefix3.add(((InternalNode) this).getCost(c));
				// System.out.println(((InternalContainmentHierarchyNode)
				// this).getCost(c));
				list.addAll(c.getCodeRootedHere(newPrefix1, newPrefix2,
						newPrefix3));
				childNum++;
			}
		}
		return list;
	}

	public int[] getMeanOutDegree(int[] numerDenom) {
		// numer is total number of {grand}*children of this node
		// denom is total number of children of this node that are not leaves

		// numerDenom[1]++;
		// numerDenom[0] += getChildren().size();
		for (Node c : getChildren()) {
			if (c.isLeaf()) {
				numerDenom[0]++;
			} else {
				int[] numerDenom2 = c.getMeanOutDegree(numerDenom);
				numerDenom[0] += numerDenom2[0];
				numerDenom[1] += numerDenom2[1];
			}
		}
		return numerDenom;
	}

	/**
	 * Returns the total number of nodes in the tree rooted at this node
	 */
	public int getNumNodesInternalOnly() {
		if (isLeaf()) {
			return 0;
		} else {
			int tmp = 1;
			for (Node c : getChildren()) {
				tmp += c.getNumNodesInternalOnly();
			}
			return tmp;
		}
	}

	/**
	 * Returns the total number of edges from this node
	 */
	public int getNumEdges() {
		if (isLeaf()) {
			return 0;
		} else {
			int numEdges = getChildren().size();
			for (Node c : getChildren()) {
				numEdges += c.getNumEdges();
			}
			return numEdges;
		}
	}

	// ////////////////////// VARIOUS toString METHODS /////////////////////////

	public abstract String toString();

	public abstract String toStringProbLaTeX();

	public String toStringLaTeXLispStyle(int lev) {
		StringBuffer buf = new StringBuffer();
		if (isLeaf()) {
			// buf.append("(" + getNodeValue().toString() + ")");
			buf.append("(" + getSelectionGroup().toStringLaTeX() + ")$_{"
					+ nodeID + "}$");
		} else {
			// buf.append( getChildren().size());
			buf.append("\\\\");
			for (int i = 0; i <= lev; i++) {
				buf.append("\\hspace*{3mm}");
			}
			buf.append("(");
			for (Node c : getChildren()) {
				// if (!c.isDummyNode())
				buf.append("" + c.toStringLaTeXLispStyle(lev + 1));
			}
			buf.append("\\\\");
			for (int i = 0; i <= lev; i++) {
				buf.append("\\hspace*{3mm}");
			}
			buf.append(")" + "$_{" + nodeID + "}$");
		}
		return buf.toString();
	}

	public String toStringPlainTextLispStyle(int lev) {
		final String INDENT = "   ";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i <= lev; i++) {
			buf.append(INDENT);
		}
		if (isLeaf()) {
			// buf.append("(" + getNodeValue().toString() + ")");
			buf.append("(" + getSelectionGroup().toStringStripped() + ")\n");
		} else {
			buf.append("(" + getSelectionGroup().toStringStripped() + ")\n");
			for (Node n : getChildren()) {
				buf.append(""
						+ n.toStringPlainTextLispStyle(n.getEdgeCostToHere()));
			}
			// buf.append("\n");
		}
		return buf.toString();
	}

	public static String toStringPlainTextLispStyleHeader() {
		String depths = " ";
		for (int i = 0; i < 9; i++) {
			depths += "  " + i;
		}
		// depths += "\n";
		return depths;
	}

	/**
	 * Formats each node so that the node identifier appears as subscript
	 * 
	 */
	public String toStringLaTeXecltree() {
		final boolean SHOW_NODE_IDS = true;
		final boolean SHOW_NODE_INTERNAL_EDGES = false;
		final boolean SHOW_NODE_NONINTERNAL_EDGES = true;
		return toStringLaTeXecltree(SHOW_NODE_IDS, SHOW_NODE_INTERNAL_EDGES,
				SHOW_NODE_NONINTERNAL_EDGES);
	}

	/**
	 * 
	 * @param showNodeID
	 *            whether to print the node identifier as a subscript for each
	 *            node
	 * @param isShowInternalEdgeCostsOn
	 *            whether or not to show the costs on incoming edges to internal
	 *            nodes
	 * @param isShowNonInternalEdgeCostsOn
	 *            whether or not to show the costs on incoming edges to leaf
	 *            nodes
	 * @return
	 */
	public String toStringLaTeXecltree(boolean showNodeID,
			boolean isShowInternalEdgeCostsOn,
			boolean isShowNonInternalEdgeCostsOn) {
		String INTERNAL_EDGE_FORMATTING_OPEN;
		String INTERNAL_EDGE_FORMATTING_CLOSE;
		String NONINTERNAL_EDGE_FORMATTING_OPEN;
		String NONINTERNAL_EDGE_FORMATTING_CLOSE;

		INTERNAL_EDGE_FORMATTING_OPEN = "\\tiny{\\textit{";
		INTERNAL_EDGE_FORMATTING_OPEN = "{{";
		INTERNAL_EDGE_FORMATTING_CLOSE = "}}";
		NONINTERNAL_EDGE_FORMATTING_OPEN = "\\tiny{\\textit{[";
		NONINTERNAL_EDGE_FORMATTING_OPEN = "{{[";
		NONINTERNAL_EDGE_FORMATTING_CLOSE = "]}}";

		String LEAF_FORMATTING_OPEN = "\\framebox{";
		String LEAF_FORMATTING_CLOSE = "}";

		StringBuffer buf = new StringBuffer();
		if (isLeaf()) {
			buf.append("%% SUBTREE FOR LEAF" + "\n");
			buf.append("\\chunk" + "[");
			if (isShowNonInternalEdgeCostsOn) {
				buf.append(INTERNAL_EDGE_FORMATTING_OPEN
						+ this.getEdgeCostToHere()
						+ INTERNAL_EDGE_FORMATTING_CLOSE);
			}
			buf.append("]" + "{" + LEAF_FORMATTING_OPEN
					+ getSelectionGroup().toStringLaTeX()
					+ LEAF_FORMATTING_CLOSE + "}\n");

		} else {
			buf.append("%% SUBTREE FOR node rooted at: "
					+ this.getSelectionGroup() + "\n");
			buf.append("\\chunk" + "[");
			if (isShowInternalEdgeCostsOn) {
				buf.append(NONINTERNAL_EDGE_FORMATTING_OPEN
						+ this.getEdgeCostToHere()
						+ NONINTERNAL_EDGE_FORMATTING_CLOSE);
			}
			buf.append("]" + "\n{\\begin{bundle}{"
					+ getSelectionGroup().toStringLaTeX());
			if (showNodeID) {
				buf.append("$_{" + getNodeID() + "}$");
			}
			buf.append("}\n");

			for (Node c : getChildren()) {
				// if (!c.isDummyNode())
				buf.append(" "
						+ c.toStringLaTeXecltree(showNodeID,
								isShowInternalEdgeCostsOn,
								isShowNonInternalEdgeCostsOn));
			}
			// buf.append("\\end{bundle}}$_{" + nodeID + "}$");
			buf.append("\\end{bundle}}\n");
		}
		return buf.toString();
	}

	public List<SourceSymbol> getLeaves() {
		List<SourceSymbol> list = new Vector<SourceSymbol>();
		if (isLeaf()) {
			// buf.append("(" + getNodeValue().toString() + ")");
			list.add(getSelectionGroup().getFirst());
		} else {
			for (Node n : getChildren()) {
				List<SourceSymbol> list2 = n.getLeaves();
				list.addAll(list2);
			}
		}
		return list;
	}

	public int getMaxOutdegree() {
		return this.findLargestOutdegree(0);
	}

	/**
	 * if the root is parent *only* to leaves, then this method returns 0. If
	 * the node is a parent to nodes, at least one of which is not a leaf, then
	 * the method considers this node's outdegree
	 * 
	 * @return
	 */
	public int getMaxOutdegreeExcludingParentsOfLeaves() {
		// int num = this.findLargestOutdegreeExcludngParentsOfLeaves(0);
		return this.findLargestSelectionGroup(0);
	}

	public boolean isFillerNode() {
		return this.getSelectionGroup().getFirst().equals(FILLER_SOURCE_SYMBOL);
	}

	public static Node generateFillerNode() {
		Node newNode = new LeafNode(FILLER_SOURCE_SYMBOL);
		return newNode;
	}

	public void markAsFokusArrived() {
		this.timeStampOfFokusReception = System.currentTimeMillis();
	}

	public void markAsFokusDeparted() {
		this.timeStampOfFokusDeparture = System.currentTimeMillis();
	}

}
