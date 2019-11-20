package keyboardLayouts.toBeFixed;

import java.util.List;

import sourceSymbolSet.SourceSymbol;

import treeDataStructure.Node;

/**
 * This class implements a an istance of a Node, which facilitates the creation
 * of keyboard layout by storing the x and y coordintaes (locaiton) of this node
 * in the kayboard layout space.
 * 
 * 
 * @author Fatima Ramay
 */

public class ConvertHierarchyToLayout {

	// public ConvertHierarchyToLayout<X> parent;
	private Node currentNode;

	// private List<ConvertHierarchyToLayout<X>> children;

	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;

	private int divide;

	private float startX;
	private float startY;

	private float endX;
	private float endY;

	// private boolean isLeaf =
	public ConvertHierarchyToLayout(Node node) {
		currentNode = node;
	}

	public int getDivide() {
		return divide;
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

	public void setDivide(int d) {
		divide = d;
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
