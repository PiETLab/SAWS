package keyboardLayouts.toBeFixed;

import java.util.List;
import java.util.Vector;

import sourceSymbolSet.SourceSymbol;

import TreeDataStructure.Node;
import TreeDataStructure.InternalNode;
import TreeDataStructure.LeafNode;
import TreeDataStructure.NontrivialSelectionGroup;

/**
 * This class implements a an istance of a Node, which facilitates the creation
 * of keyboard layout by storing the x and y coordintaes (locaiton) of this node
 * in the kayboard layout space.
 * 
 * 
 * @author Fatima Ramay
 */

public class KBLNodeOld<X extends SourceSymbol> {

	public KBLNode<X> parent;
	private Node<X> currentNode;

	private List<KBLNode<X>> children;

	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;

	private int divideType;

	private float startX;
	private float startY;

	private float endX;
	private float endY;

	private boolean leafNode;
	private boolean smallSizedButton;
	private boolean containSmallButton;
	boolean sizeUpdated;
	private float needX;
	private float needY;

	public KBLNodeOld(Node<X> node) {

		currentNode = node;
		leafNode = false;
		smallSizedButton = false;
		containSmallButton = false;
		children = new Vector<KBLNode<X>>();
		sizeUpdated = false;
	}

	public boolean isLeaf() {
		return leafNode;
	}

	public int size() {
		return children.size();
	}

	public void setLeafNode(boolean val) {
		leafNode = val;
	}

	public void addChild(KBLNodeOld<X> node) {
		node.setParent(this);
		children.add(node);
	}

	public void setChild(int index, KBLNodeOld<X> node) {
		node.setParent(this);
		children.set(index, node);
	}

	public KBLNode<X> getChild(int i) {
		return children.get(i);
	}

	public List<KBLNode<X>> getChildren() {
		return children;
	}

	public void setParent(KBLNode<X> chn) {
		parent = chn;
	}

	public boolean isSizeUpdated() {
		return sizeUpdated;
	}

	public void setSizeUpdated(boolean val) {
		sizeUpdated = val;
	}

	public boolean isSmallSizedButton() {
		return smallSizedButton;
	}

	public void setSmallSizedButton(boolean b) {
		smallSizedButton = b;
	}

	public boolean containsSmallButton() {
		return containSmallButton;
	}

	public void setContainsSmallButton(boolean b) {
		containSmallButton = b;
	}

	public float getNeedX() {
		return needX;
	}

	public float getNeedY() {
		return needY;
	}

	public void setNeedX(float x) {
		needX = x;
	}

	public void setNeedY(float y) {
		needY = y;
	}

	public int getDivideType() {
		return divideType;
	}

	public float getStartX() {
		return startX;
	}

	public float getStartY() {
		return startY;
	}

	public float getEndX() {
		return endX;
	}

	public float getEndY() {
		return endY;
	}

	public Node<X> getNode() {
		return currentNode;
	}

	public void setDivideType(int d) {
		divideType = d;
	}

	public void setStartX(float x) {
		startX = x;
	}

	public void setStartY(float y) {
		startY = y;
	}

	public void setEndX(float x) {
		endX = x;
	}

	public void setEndY(float y) {
		endY = y;
	}

	public void setNode(Node<X> node) {
		currentNode = node;
	}

}
