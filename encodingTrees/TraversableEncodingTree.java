/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees;

import treeDataStructure.Node;
import treeDataStructure.SelectionGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import jtextpro.JTextPro;

import quicktime.streaming.SettingsDialog;

import buttonLayouts.ButtonLayoutSpecification;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

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
 * <i>fokusCycle</i> and <i>fokusLevel</i> denotes the level of the hierarchy at
 * which the fokusNode/fokusCycle resides (the root is level 0 and increases
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
public class TraversableEncodingTree implements Serializable { // implements
	// TraversableEncodingTreeI {

	/**
	 * 
	 */
	private final long serialVersionUID = 7499048017097495603L;

	private EncodingTree encodingTree;

	private boolean isLastOpGenerationShift;

	// levelOfInFokusNode is the level at which the parent of the current focus
	// cycle is found
	int levelOfInFokusNode;

	Node fokusNode;

	protected Node getFokusNode() {
		return fokusNode;
	}

	protected void setFokusNode(Node fokusNode) {
		this.fokusNode = fokusNode;
	}

	Node fokusCycleParent;

	// currSib is the child number of inFokusNode among fokusCycleParent's
	// children
	int currSib = 0;

	// the level starts at 0 for the root and increases
	// for row column scanning, level 0 is the level of the rows
	// and level 1 is the level of the columns

	public TraversableEncodingTree(EncodingTree et) {
		this.encodingTree = et;
	}

	// public abstract EncodingTree constructEncodingTree();

	// protected TraversableEncodingTree(KeyboardLayout keyboardLayout) {
	// encodingTree = new MyEncodingTree(null);
	// }
	//	
	// protected TraversableEncodingTree(SourceSymbolSet sourceSymbols,
	// Integer encodingAlphabetSize) {
	// encodingTree = new MyEncodingTree(null);
	// //constructMyEncodingTree();
	// }

	/**
	 * (non-Javadoc)
	 * 
	 * @see encodingTrees.TraversableEncodingTreeInteface#descendFokus()
	 */
	public void descendFokus() {
		isLastOpGenerationShift = true;
		levelOfInFokusNode++;
		fokusCycleParent = fokusNode;
		fokusNode.markAsFokusDeparted();
		fokusNode = fokusCycleParent.getFirstChild();
		fokusNode.markAsFokusArrived();
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
		if (fokusCycleParent != this.getRoot()) {
			fokusNode = fokusCycleParent.parent;
			descendFokus();
			return true;
		} else {
			return false;
		}
	}

	public boolean isFokusAtRootCycle() {
		return fokusCycleParent == this.getRoot();
	}

	/**
	 * 
	 * PRE the encoding tree must not be null
	 * 
	 * (non-Javadoc)
	 * 
	 * @see encodingTrees.TraversableEncodingTreeInteface#resetFokus()
	 */
	public void resetFokus() {
		if (fokusNode != null) {
			fokusNode.markAsFokusDeparted();
		}
		fokusNode = this.getRoot();
		fokusNode.markAsFokusArrived();
		// System.out.println("****RESET*******");
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
	 * @see
	 * encodingPackage.TraversableEncodingTreeInteface#getRelevantEncodingTreeNode
	 * (TreeDataStructure.SourceSymbol)
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
	 * @see
	 * encodingPackage.TraversableEncodingTreeInteface#getCurrSelectionGroup()
	 */
	public SelectionGroup getCurrentSelectionGroup() {
		return fokusNode.getSelectionGroup();
	}

	public SelectionGroup getCurrentSelectionGroupParent() {
		return fokusCycleParent.getSelectionGroup();
	}

	public boolean isCurrentSelectionGroupImmediateChildOfRoot() {
		return fokusCycleParent.equals(encodingTree.getRoot());
	}

	public int getDurationOfgetCurrentSelectionGroupHasBeenInFocusAsOfNow() {
		return (int) (System.currentTimeMillis() - fokusNode
				.getTimeStampOfFokusReception());

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
		fokusNode.markAsFokusDeparted();
		fokusNode = fokusNode.getNextSibling();
		fokusNode.markAsFokusArrived();
		// System.out.println("fokux Node: " + fokusNode);
		// if (fokusNode.isDummyNode()) {
		// fokusNode = fokusNode.getNextSibling();
		// }
	}

	public void descendFokus(int i) {
		isLastOpGenerationShift = true;
		levelOfInFokusNode++;
		fokusCycleParent = fokusNode;
		fokusNode.markAsFokusDeparted();
		fokusNode = fokusCycleParent.getFirstChild();
		fokusNode.markAsFokusArrived();
	}

	// ///////////////////

	// public void constructRowColEncodingFromKeyboardLayout(
	// KeyboardLayout keyboardLayout) {
	// encodingTree.constructRowColEncodingFromKeyboardLayout(keyboardLayout);
	// }
	//
	// public void constructLinearEncodingFromKeyboardLayout(
	// KeyboardLayout keyboardLayout) {
	// encodingTree.constructLinearEncodingFromKeyboardLayout(keyboardLayout);
	// }
	//
	// public void constructBinaryEncodingFromKeyboardLayout(
	// KeyboardLayout keyboardLayout) {
	// encodingTree.constructBinaryEncodingFromKeyboardLayout(keyboardLayout);
	// }

	public Code getCode() {
		return getEncodingTree().getCode();
	}

	public double getConditionalProbability(SourceSymbol transmittedSymbolNode,
			SourceSymbol targetSymbol) {
		return getEncodingTree().getConditionalProbability(
				transmittedSymbolNode, targetSymbol);
	}

	public int getDepth() {
		return getEncodingTree().getDepth();
	}

	public Node getRoot() {
		return getEncodingTree().getRoot();
	}

	// public int getRootOutdegree() {
	// return getEncodingTree().getRootOutdegree();
	// }

	public String toStringLaTeXLispStyle() {
		return getEncodingTree().toStringLaTeXLispStyle();
	}

	/**
	 * @param isShowInternalEdgeCostsOn
	 *            whether or not to show the costs on incoming edges to internal
	 *            nodes
	 * @param isShowNonInternalEdgeCostsOn
	 *            whether or not to show the costs on incoming edges to leaf
	 *            nodes
	 */
	public String toStringLaTeXecltree(boolean isShowNodeIDsOn,
			boolean isShowInternalEdgeCostsOn,
			boolean isShowNonInternalEdgeCostsOn) {
		return getEncodingTree().toStringLaTeXecltree(isShowNodeIDsOn,
				isShowInternalEdgeCostsOn, isShowNonInternalEdgeCostsOn);
	}

	public String toStringLaTeXecltreeProbs() {
		return getEncodingTree().toStringLaTeXecltreeProbs();
	}

	public String toStringPlainTextLispStyle() {
		return getEncodingTree().toStringPlainTextLispStyle();
	}

	private EncodingTree getEncodingTree() {
		return encodingTree;
	}

	public void setEncodingTree(EncodingTree encodingTree) {
		this.encodingTree = encodingTree;
	}

	public List<SourceSymbol> getLeaves() {
		return getEncodingTree().getLeaves();
	}

	// public void setNameOfSourceSymbolSet(String name) {
	// //System.out.println("set name in TET: " + name);
	// getEncodingTree().setNameOfSourceSymbolSet(name);
	// }

	/**
	 * @return the name of the symbol set that is represented by the code
	 *         implicit in the encoding tree (as given by the subclass name,
	 *         with the package name stripped off)
	 */
	public String getSourceSymbolSetIdentifier() {
		// System.out.println("get name in TET: "
		// + getEncodingTree().getNameOfSourceSymbolSet());
		String name = getEncodingTree().getSourceSymbolSetIdentifier();
		name = name.substring(name.indexOf(".") + 1);
		return name;
	}

	/**
	 * @return the name of the particular manner in which this encoding tree was
	 *         generated (as given by the subclass name, with the package name
	 *         stripped off)
	 */
	public String getMannerOfCreationIdentifier() {
		String name = getEncodingTree().getCreatingClass();
		name = name.substring(name.indexOf(".") + 1);
		return name;
	}

	public long getTimeRequiredToCreate() {
		return getEncodingTree().getTimeToCreate();
	}

	public void toggleCapsOnOff() {
		List<SourceSymbol> theLeaves = getLeaves();
		for (SourceSymbol leaf : theLeaves) {
			leaf.getVOCACommand().toggleCapsOnOff();
			if (leaf.getTextLabel().toLowerCase().equals(leaf.getTextLabel())) {
				leaf.setTextLabel(leaf.getTextLabel().toUpperCase());
			} else {
				leaf.setTextLabel(leaf.getTextLabel().toLowerCase());
			}
		}
	}

	public void toggleCapsOn() {
		List<SourceSymbol> theLeaves = getLeaves();
		for (SourceSymbol leaf : theLeaves) {
			leaf.getVOCACommand().toggleCapsOn();
			leaf.setTextLabel(leaf.getTextLabel().toUpperCase());
		}
	}

	public void toggleCapsOff() {
		List<SourceSymbol> theLeaves = getLeaves();
		for (SourceSymbol leaf : theLeaves) {
			leaf.getVOCACommand().toggleCapsOff();
			leaf.setTextLabel(leaf.getTextLabel().toLowerCase());
		}
	}

	/**
	 * 
	 * 
	 * 
	 * @param pathToInstanceCachedInFile
	 *            ensure this isn't the same subdir as the source code (for
	 *            efficiency of creating jar files, etc)
	 * @param nameOfFileContainingCachedInstance
	 * @return
	 * @throws IOException
	 */
	public void cacheInstanceInFile(String pathToInstanceCachedInFile,
			String nameOfFileContainingCachedInstance) {
		// "JTextPro.obj"
		File theFile = new File(pathToInstanceCachedInFile,
				nameOfFileContainingCachedInstance);
		if (theFile.exists())
			theFile.delete();

		// long startTime = System.currentTimeMillis();
		System.out.println("Writing " + this.getClass().getName()
				+ " object to file...");
		FileOutputStream outFileStream;
		try {
			outFileStream = new FileOutputStream(theFile);
			ObjectOutputStream s = new ObjectOutputStream(outFileStream);
			// out.println("Object is in: " + objectFileName);
			System.out.println("Object will be in file: " + theFile);
			s.writeObject(this);
			s.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static TraversableEncodingTree getObjectFromFile(
			String pathToInstanceCachedInFile,
			String nameOfFileContainingCachedInstance) {
		TraversableEncodingTree et = null;
		File theObjectFile = new File(pathToInstanceCachedInFile,
				nameOfFileContainingCachedInstance);
		return getObjectFromFile(theObjectFile);
	}

	public static TraversableEncodingTree getObjectFromFile(
			String fullNameOfFileContainingCachedInstance) {
		File theObjectFile = new File(fullNameOfFileContainingCachedInstance);
		return getObjectFromFile(theObjectFile);
	}

	public static TraversableEncodingTree getObjectFromFile(
			File fileContainingCachedInstance) {
		TraversableEncodingTree et = null;
		System.out
				.println("Mode: use file cache of  object.  Determining whether file exists.");
		if (fileContainingCachedInstance.exists()) {
			System.out
					.println("File containing Traversable Encoding Tree object exists.  Reading from file...");
			FileInputStream inFileStream;
			try {
				inFileStream = new FileInputStream(fileContainingCachedInstance);
				ObjectInputStream i = new ObjectInputStream(inFileStream);
				et = (TraversableEncodingTree) i.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return et;
	}

}
