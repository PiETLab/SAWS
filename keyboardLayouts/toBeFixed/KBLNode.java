package keyboardLayouts.toBeFixed;

import java.util.List;
import java.util.Vector;

import sourceSymbolSet.SourceSymbol;

import treeDataStructure.Node;
import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.NontrivialSelectionGroup;

/**
 * This class implements a an istance of a Node, which facilitates the creation
 * of keyboard layout by storing the x and y coordintaes (locaiton) of this node
 * in the kayboard layout space.
 * 
 * 
 * @author Fatima Ramay
 */

public class KBLNode {

	public KBLNode parent;
	private Node currentNode;

	private List<KBLNode> children;

	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;

	public static final int WIDTH = 3;
	public static final int HEIGHT = 4;
	public static final int NONE = -1;

	private int divideType;

	private float startX;
	private float startY;

	private float endX;
	private float endY;

	private boolean leafNode;
	// private boolean smallSizedButton;
	// private boolean containSmallButton;
	// boolean sizeUpdated;
	// private float needX;
	// private float needY;

	private float requiredWidth;
	private float requiredHeight;

	private float avaialbleWidth;
	private float avaialbleHeight;

	public KBLNode(Node node) {

		currentNode = node;
		leafNode = false;
		// smallSizedButton = false;
		// containSmallButton = false;
		children = new Vector<KBLNode>();
		requiredWidth = -1;
		requiredHeight = -1;

		avaialbleWidth = -1;
		avaialbleHeight = -1;

		// sizeUpdated = false;
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

	public void addChild(KBLNode node) {
		node.setParent(this);
		children.add(node);
	}

	public void setChild(int index, KBLNode node) {
		node.setParent(this);
		children.set(index, node);
	}

	public KBLNode getChild(int i) {
		return children.get(i);
	}

	public List<KBLNode> getChildren() {
		return children;
	}

	public void setParent(KBLNode chn) {
		parent = chn;
	}

	/*
	 * public boolean isSizeUpdated() { return sizeUpdated; }
	 */
	/*
	 * public void setSizeUpdated(boolean val) { sizeUpdated = val; }
	 */
	/*
	 * public boolean isSmallSizedButton() { return smallSizedButton; }
	 */
	/*
	 * public void setSmallSizedButton(boolean b) { smallSizedButton = b; }
	 */
	/*
	 * public boolean containsSmallButton() { return containSmallButton; }
	 */
	/*
	 * public void setContainsSmallButton(boolean b) { containSmallButton = b; }
	 */
	public float getAvaialbleWidth() {
		return avaialbleWidth;
	}

	public float getAvaialbleHeight() {
		return avaialbleHeight;
	}

	public void setAvaialbleWidth(float x) {
		avaialbleWidth = x;
	}

	public void setAvaialbleHeight(float y) {
		avaialbleHeight = y;
	}

	public float getRequiredDimension(int flag) {
		if (flag == WIDTH)
			return requiredWidth;
		else if (flag == HEIGHT)
			return requiredHeight;
		else
			return -1;

	}

	public void setRequiredDimension(int flag, float val) {
		if (flag == WIDTH)
			requiredWidth = val;
		else if (flag == HEIGHT)
			requiredHeight = val;

	}

	public float getAvailableDimension(int flag) {
		if (flag == WIDTH)
			return avaialbleWidth;
		else if (flag == HEIGHT)
			return avaialbleHeight;
		else
			return -1;

	}

	public float getRequiredWidth() {
		return requiredWidth;
	}

	public float getRequiredHeight() {
		return requiredHeight;
	}

	public void setRequiredWidth(float x) {
		requiredWidth = x;
	}

	public void setRequiredHeight(float y) {
		requiredHeight = y;
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

	public Node getNode() {
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

	public void setNode(Node node) {
		currentNode = node;
	}

}
