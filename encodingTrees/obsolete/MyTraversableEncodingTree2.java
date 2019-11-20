/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees.obsolete;

import IndirectSelectionFacility.OnScreenKeyboardView;
import IndirectSelectionFacility.TextEntrySystemFrame;
import IndirectSelectionFacility.TextEntrySystemFrameController;
import IndirectSelectionFacilityCommands.DeleteCommand;
import TreeDataStructure.Node;
import TreeDataStructure.SelectionGroup;

import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import buttonLayouts.ButtonLayoutSpecification;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

import encodingTrees.AbstractEncodingTree;
import encodingTrees.Code;
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
public class MyTraversableEncodingTree2 implements TraversableEncodingTreeI {

	public static final int CONSTRUCT_ROW_COL = 0;

	private EncodingTree encodingTree;

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

	public MyTraversableEncodingTree2(Node _root) {
		encodingTree = new AbstractEncodingTree(_root);
		fokusNode = encodingTree.getRoot();
		levelOfInFokusNode = -1;
		descendFokus();
	}

	/**
	 * 
	 * 
	 * @param kb
	 * @param variant
	 *            must be the name of a method from OnScreenKeyboard that
	 *            returns a TraversableEncodingTree
	 */
	public MyTraversableEncodingTree2(OnScreenKeyboardView kb, String variant) {
		encodingTree = new AbstractEncodingTree(null);
		try {
			Class<?>[] params = { Class.forName("OnScreenKeyboard") };
			Method method = Class.forName(this.toString()).getMethod(variant,
					params);
			method.invoke(this, kb);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		// }

		// Constructor<?> constr = Class.forName(variant).getConstructor(
		// (Class[]) null);
		// keyboard = (KeyboardLayout) constr.newInstance((Object[]) null);
		//
		// if (true) {
		// this.constructRowColEncodingFromKeyboardLayout(kb);
		// } else {
		// this.constructLinearEncodingFromKeyboardLayout(kb);
		// }
		fokusNode = encodingTree.getRoot();
		levelOfInFokusNode = -1;
		descendFokus();
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
		if (fokusCycleParent != encodingTree.getRoot()) {
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
	 * @see encodingPackage.TraversableEncodingTreeInteface#reset()
	 */
	public void reset() {
		fokusNode = encodingTree.getRoot();
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

	// ///////////////////

	public Code getCode() {
		return encodingTree.getCode();
	}

	public double getConditionalProbability(SourceSymbol transmittedSymbolNode,
			SourceSymbol targetSymbol) {
		return encodingTree.getConditionalProbability(transmittedSymbolNode,
				targetSymbol);
	}

	public int getDepth() {
		return encodingTree.getDepth();
	}

	public Node getRoot() {
		return encodingTree.getRoot();
	}

//	public int getRootOutdegree() {
//		return encodingTree.getRootOutdegree();
//	}

	public String toStringLaTeXLispStyle() {
		return encodingTree.toStringLaTeXLispStyle();
	}

	public String toStringLaTeXecltree(boolean isShowNodeIDsOn,
			boolean isShowDepthsOn) {
		return encodingTree.toStringLaTeXecltree(isShowNodeIDsOn,
				isShowDepthsOn);
	}

	public String toStringLaTeXecltreeProbs() {
		return encodingTree.toStringLaTeXecltreeProbs();
	}

	public String toStringPlainTextLispStyle() {
		return encodingTree.toStringPlainTextLispStyle();
	}

	public void constructLinearEncodingFromKeyboardLayout(
			ButtonLayoutSpecification keyboardLayout) {
		encodingTree.constructLinearEncodingFromKeyboardLayout(keyboardLayout);
	}

	public void constructRowColEncodingFromKeyboardLayout(
			ButtonLayoutSpecification keyboardLayout) {
		encodingTree.constructRowColEncodingFromKeyboardLayout(keyboardLayout);
	}

	public boolean isFokusAtRootCycle() {
		// TODO Auto-generated method stub
		return false;
	}

	public void constructBinaryEncodingFromKeyboardLayout(
			ButtonLayoutSpecification onScreenKeyboard) {
		// TODO Auto-generated method stub
		
	}

	public List<SourceSymbol> getLeaves() {
		// TODO Auto-generated method stub
		return null;
	}

}
