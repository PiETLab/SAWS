/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import treeDataStructure.Node;

/**
 * This class implements an encoding tree.
 * 
 * @author Melanie Baljko
 */
/**
 * @author mb
 * 
 */
public abstract class AbstractEncodingTree implements EncodingTree,
		Serializable {

	protected boolean IS_VERBOSE = false;

	private long timeStampStart;
	private long timeStampFinish;

	private Node root;

	protected AbstractEncodingTree() {
		timeStampStart = System.currentTimeMillis();
		// System.out.println("start: " + timeStampStart);
		this.root = null;
	}

	// private AbstractEncodingTree(Node _root) {
	// root = _root;
	// }

	public void registerEndOfConstructingTime() {
		timeStampFinish = System.currentTimeMillis();
		// System.out.println("finish: " + timeStampFinish);
	}

	public long getTimeToCreate() {
		return timeStampFinish - timeStampStart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTree#getRoot()
	 */
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node node) {
		this.root = node;
		// System.out.println(root.toStringLaTeXLispStyle(0));
	}

	public abstract String getCreatingClass();

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTree#getRootOutdegree()
	 */
	public int getRootOutdegree() {
		return root.getChildren().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTree#getDepth()
	 */
	public int getDepth() {
		return root.getEdgeCostToHere();
	}

	// /**
	// * This method tells us the number of binary considerations (BCs) the user
	// * must consider in order to select the passed target but (in it's leaf).
	// */
	// public int getNumBinaryConsiderations(Node<X> but) {
	// // the value is the number of traverse-down operations needed
	// // in order to reach the parent of the leaf,
	// // plus the number of left-hand siblings
	// return root.getNumBinaryConsiderations(but);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTree#toString()
	 */
	public String toString() {
		return root.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTree#toStringPlainTextLispStyle()
	 */
	public String toStringPlainTextLispStyle() {
		String depths = " ";
		for (int i = 0; i < 9; i++) {
			depths += "  " + i;
		}
		depths += "\n";
		return depths + root.toStringPlainTextLispStyle(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTree#getCode()
	 */
	public Code getCode() {

		// Node<SelectionGroupMember> is not recognized as a subclass of Node<X>
		// Code code = new Code((Node<X>) this.getRoot());
		Code code = new Code(this.getRoot());
		// Code code = new Code((Node<X>) this.getRoot());

		// code.addCodeWords(root.getCode());
		return code;
	}

	public double cost(Node leafNode) {
		CodeWord cw = getCode().getCodeWordForSelectable(
				leafNode.getRepresentative().toStringOneChar());
		if (cw == null)
			throw new RuntimeException("no corresponding codeword found for: "
					+ leafNode.getRepresentative().toStringOneChar() + " "
					+ leafNode.getRepresentative().toStringLaTeX());
		return cw.getCost();
	}

	public double cost(SourceSymbol sourceSymbol) {
		Node correspondingNode = this.getRoot().getChildLeafByContents(
				sourceSymbol);
		return this.cost(correspondingNode);
	}

	public double getConditionalProbability(SourceSymbol transmittedSymbol,
			SourceSymbol targetSymbol) {
		Node transmittedSymbolNode = this.getRoot().getChildLeafByContents(
				transmittedSymbol);

		double condProb = 1.0;
		List<Node> pathRootToLeaf = this.getRoot().getPath(
				transmittedSymbolNode, new Vector<Node>());
		for (Node n : pathRootToLeaf) {
			// System.out.println(n.getConditionalProbability(targetSymbol));
			condProb = condProb * n.getConditionalProbability(targetSymbol);
		}
		// System.out.println("Prob ( " + transmittedSymbol.toStringOneChar()
		// + " | " + targetSymbol.toStringOneChar() + " )=\t" + condProb);
		// + getCode().getCodeWordForSelectable(transmittedSymbol)
		// .toStringNumeric());
		return condProb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTree#toStringLaTeXLispStyle()
	 */
	public String toStringLaTeXLispStyle() {
		return root.toStringLaTeXLispStyle(0);
	}

	/**
	 * @param isShowNodeIDsOn
	 * @param isShowInternalEdgeCostsOn
	 *            whether or not to show the costs on incoming edges to internal
	 *            nodes
	 * @param isShowNonInternalEdgeCostsOn
	 *            whether or not to show the costs on incoming edges to leaf
	 *            nodes
	 * @return
	 */
	public String toStringLaTeXecltree(boolean isShowNodeIDsOn,
			boolean isShowInternalEdgeCostsOn,
			boolean isShowNonInternalEdgeCostsOn) {
		StringBuffer buf = new StringBuffer();
		buf.append("%% CONTAINMENT HIERARCHY %%\n");
		buf.append("\\noindent"
				+ "\\begin{tt}\n\\begin{small}\n\\begin{bundle}{}\n");
		buf.append(root.toStringLaTeXecltree(isShowNodeIDsOn,
				isShowInternalEdgeCostsOn, isShowNonInternalEdgeCostsOn));
		buf.append("\n\\end{bundle}\n\\end{small}\n\\end{tt}");
		buf.append("\n");
		buf.append("\\newpage");
		return buf.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTree#toStringLaTeXecltreeProbs()
	 */
	public String toStringLaTeXecltreeProbs() {
		StringBuffer buf = new StringBuffer();
		buf.append("\\noindent\\begin{tt}\\begin{small}\\begin{bundle}{}");
		buf.append(root.toStringProbLaTeX());
		buf.append("\\end{bundle}\\end{small}\\end{tt}");
		return buf.toString();
	}

	// /**
	// * This method constructs an encoding tree
	// *
	// * PRE : the keyboard layout has been specified
	// *
	// * Use the rows of the keyboard layout to derive the first level. Use the
	// * buttons within each row to derive the second level of the CH.
	// */
	// public void constructRowColEncodingFromKeyboardLayout(
	// KeyboardLayout keyboardLayout) {
	//
	// InternalNode root = new InternalNode();
	//
	// for (JPanel currRow : keyboardLayout.getRows()) {
	// InternalNode rootChild = new InternalNode();
	// Component[] x = currRow.getComponents();
	// for (int i = 0; i < x.length; i++) {
	// JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
	// if (tmpBut.isEnabled()) {
	// Node tmp = new LeafNode(tmpBut);
	// rootChild.addChild(tmp);
	// }
	// }
	// // test whether this row contains just one button
	// // if so, collapse the level and make the button directly a child of
	// // the root
	// if (rootChild.getChildren().size() == 1) {
	// root.addChild(new LeafNode(rootChild.getFirstChild()
	// .getNodeSelectionGroup().getFirst()));
	// } else {
	// root.addChild(rootChild);
	// }
	// }
	// this.root = root;
	// }

	// /**
	// * This method constructs an encoding tree
	// *
	// * PRE : the keyboard layout has been specified
	// *
	// * Use the rows of the keyboard layout to derive the first level. Use the
	// * buttons within each row to derive the second level of the CH.
	// */
	// public void constructLinearEncodingFromKeyboardLayout(
	// KeyboardLayout keyboardLayout) {
	//
	// InternalNode root = new InternalNode();
	// for (JPanel currRow : keyboardLayout.getRows()) {
	// Component[] x = currRow.getComponents();
	// for (int i = 0; i < x.length; i++) {
	// JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
	// if (tmpBut.isEnabled()) {
	// Node tmp = new LeafNode(tmpBut);
	// root.addChild(tmp);
	// }
	// }
	// }
	//
	// this.root = root;
	// }

	public List<SourceSymbol> getLeaves() {
		return root.getLeaves();
	}

	// public void constructBinaryEncodingFromKeyboardLayout(
	// KeyboardLayout onScreenKeyboard) {
	// InternalNode root = new InternalNode();
	// JPanel currRow = onScreenKeyboard.getRows().get(0);
	// Component[] x = currRow.getComponents();
	// for (int i = 0; i < x.length; i++) {
	// JIndirectSelectionButton tmpBut = ((JIndirectSelectionButton) x[i]);
	// if (tmpBut.isEnabled()) {
	// Node tmp = new LeafNode(tmpBut);
	// root.addChild(tmp);
	// }
	// }
	//
	// this.root = root;
	// }

	public abstract String getSourceSymbolSetIdentifier();

	public abstract SourceSymbolSet getSourceSymbolSet();

	// public void setSourceSymbolSetIdentifier(String name) {
	// this.sourceSymbolSet = name;
	// // System.out.println(".setName in MET........:" + name);
	// }
	//
	// public String getSourceSymbolSetIdentifier() {
	// // System.out.println(":getName in MET::::::::::" +
	// // this.sourceSymbolSet);
	// return this.sourceSymbolSet;
	// }

}
