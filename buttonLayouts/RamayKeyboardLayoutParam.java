/*
 * 
 * 
 */

package buttonLayouts;

import java.awt.*;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

import keyboardLayouts.toBeFixed.ConvertHierarchyToLayout;

import encodingTrees.TraversableEncodingTree;
import encodingTrees.obsolete.TraversableEncodingTreeI;
import treeDataStructure.Node;
import treeDataStructure.InternalNode;

/**
 * This class implements the algorithm to generate the keyboard layout
 * automatically, based on the containment hierarchy which is passed as an
 * input. The layout of the keyboard is generated automatically, and the main
 * goal is to eliminate the painful process of creating the keyboard layout
 * manually.
 * 
 * 
 * @author Fatima Ramay
 */

public class RamayKeyboardLayoutParam extends ButtonLayoutSpecification {

	List<JPanel> onScreenButtonRows;
	// List<ConvertHierarchyToLayout> layoutList;
	SourceSymbolSet keyList;

	private static final int fontSize = 14;
	private static final int fontDivideSmall1 = 4;

	private static final int fontDivideSmall2 = 20;
	private static final int fontDivideSmall3 = 6;
	private static final int fontDivideSmall4 = 22;
	private static final int fontSub1 = 4;
	private static final int fontSub2 = 6 + 3;
	private static final int fontAdd2 = 4;
	private static final int fontAdd3 = 8;

	// private int k;

	// VoiceOutputCommunicationAidApplication.frame.
	// private final int width =
	// 1370;//VoiceOutputCommunicationAidApplication.frame.getWidth(); // 512;
	// private final int height = 800;

	private final int width = 1000;
	private final int height = 500; // VoiceOutputCommunicationAidApplication.frame.getHeight();

	private int divideLevels;

	public RamayKeyboardLayoutParam(TraversableEncodingTree root,
			SourceSymbolSet selectableKeyList) {

		System.out.println("Building keyboard layout...");

		// ContainmentHierarchyNode root = containmentHierarchy.getRoot();

		// k = containmentHierarchy.getRootOutdegree();
		// k = 4;

		keyList = selectableKeyList;

		// createKeyboardLayout(root);
		ConvertHierarchyToLayout rootNode = new ConvertHierarchyToLayout(root
				.getRoot());
		rootNode.setStartX(0);
		rootNode.setStartY(0);
		rootNode.setEndX(1 * width);
		rootNode.setEndY(1 * height);
		rootNode.setDivide(ConvertHierarchyToLayout.VERTICAL);

		divideLevels = 1;
		InternalNode temp = (InternalNode) root.getRoot();
		int maxDepth = temp.calculateMaximumLeafDepth(0);
		int maxLevel = temp.calculateMaximumLeafLevel();

		System.out.println("The depth............" + maxDepth);
		System.out.println("The level............" + maxLevel);
		int divideLevelCountH = 0;
		int divideLevelCountV = 0;
		JPanel keyboard = hierarchyToLayout(rootNode, divideLevelCountH,
				divideLevelCountV);
		// keyboard.setPreferredSize(new Dimension(width,height));
		// System.out.println("exiting sk");

		onScreenButtonRows = new Vector<JPanel>();

		onScreenButtonRows.add(keyboard);
		// System.out.println("exiting sk");
		System.out.println("Done.");

	}

	/**
	 * This method takes as input the root node of the containment hierarchy.
	 * The keyboard is created as follows: The available space for the keyboard
	 * layout is divided according the to number of the children of the root
	 * node. Each child node is assigned a specific space in the total avaialble
	 * space for the keyboard.
	 * 
	 * The space assigned to every child node is recursively divided into
	 * further sub-spaces according to their children, until a leaf node is
	 * reached.
	 * 
	 * Every time, the space is once divided horizontally , and once vertically.
	 * That is, if the space assigned to the parent was divided vertially, then
	 * the space assigned to its children will be divided horizontally, and
	 * every child node will be arranged horizontally in that space.
	 * 
	 * 
	 * 
	 * @return
	 */
	public JPanel hierarchyToLayout(ConvertHierarchyToLayout parentLayoutNode,
			int divideLevelCountV, int divideLevelCountH) {
		Node parentNode = parentLayoutNode.getNode();
		List<Node> children = parentNode.getChildren();

		JPanel boxContainer = new JPanel();

		if (parentNode.isLeaf()) {
			boxContainer.setLayout(new BoxLayout(boxContainer,
					BoxLayout.PAGE_AXIS));
			// since parent was divided vertically, these nodes have to be
			// placed
			// horizontaly

			// float parentWidth = ((parentLayoutNode.getEndX()-
			// parentLayoutNode.getStartX())* width);
			// float parentHeight =((parentLayoutNode.getEndY()-
			// parentLayoutNode.getStartY())* height);

			float parentWidth = ((parentLayoutNode.getEndX() - parentLayoutNode
					.getStartX()));
			float parentHeight = ((parentLayoutNode.getEndY() - parentLayoutNode
					.getStartY()));

			int buttonWidth = (int) Math.ceil(parentWidth);
			int buttonHeight = (int) Math.ceil(parentHeight);

			// String buttonText =
			// ((LeafContainmentHierarchyNode)parentNode).getRepresentative();

			SourceSymbol symbol = parentNode.getRepresentative();
			JIndirectSelectionButton button = (JIndirectSelectionButton) symbol;
			// will it work ??????
			// JButton button = new JButton(but.toString());
			// button = but;
			button.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
			button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			button.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
			// button.setAlignmentX(JButton.LEFT_ALIGNMENT);
			// button.setAlignmentY(JButton.TOP_ALIGNMENT);

			// button.setLocation((int)parentLayoutNode.getStartX(),(int)
			// parentLayoutNode.getStartY());

			/*
			 * boxContainer.setMinimumSize(new Dimension(buttonWidth,
			 * buttonHeight)); boxContainer.setPreferredSize(new
			 * Dimension(buttonWidth, buttonHeight));
			 * boxContainer.setMaximumSize(new Dimension(buttonWidth,
			 * buttonHeight));
			 */

			// button.setBorder(new LineBorder(Color.BLACK));
			if (buttonWidth >= width / fontDivideSmall1
					|| buttonHeight >= height / fontDivideSmall1) {
				button.setFont(new Font("sanserif", Font.BOLD, fontSize
						+ fontAdd3));
				Insets insets = new Insets(1, 1, 1, 1);
				button.setMargin(insets);
			} else if (buttonWidth >= width / fontDivideSmall3
					|| buttonHeight >= height / fontDivideSmall3) {
				button.setFont(new Font("sanserif", Font.BOLD, fontSize
						+ fontAdd2));
				Insets insets = new Insets(1, 1, 1, 1);
				button.setMargin(insets);
			} else if (buttonWidth <= (int) width / fontDivideSmall2
					&& buttonHeight <= (int) height / fontDivideSmall2) {
				Insets insets = new Insets(0, 0, 0, 0);
				button.setMargin(insets);
				// if(buttonWidth <= (int)width/24.0 || buttonHeight <=
				// (int)height/24.0)
				if (button.getText().length() == 1) {
					button.setFont(new Font("sanserif", Font.PLAIN, fontSize
							- fontSub1));

					// button.setBorder(new LineBorder(Color.BLACK));
				} else {

					button.setFont(new Font("sanserif", Font.PLAIN, fontSize
							- fontSub2));
					// button.setFont(new Font("sanserif", Font.PLAIN, 2));
					// button.setText("d");
				}
			} else {
				button.setFont(new Font("sanserif", Font.BOLD, fontSize));
				Insets insets = new Insets(1, 1, 1, 1);
				button.setMargin(insets);

			}

			if (button.getText().contains("FillerLabel")) {
				button.setText("");
				button.setEnabled(false);
			}

			// System.out.println(button + " size " + button.getFont());
			boxContainer.add(button);

			return boxContainer;
		} else {
			if (parentLayoutNode.getDivide() == ConvertHierarchyToLayout.VERTICAL) {
				boxContainer.setLayout(new BoxLayout(boxContainer,
						BoxLayout.LINE_AXIS));
				// if (parentLayoutNode.getDivide() ==
				// ConvertHierarchyToLayout.VERTICAL)

				int totalInterval = 0;
				int totalNodeSize = 0;

				// if (firstTime)
				// if (count < 2)
				// {
				// Vector<Double> tempChildren = new Vector<Double>();
				Vector<Double> childNodeSum = new Vector<Double>();
				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i);
					totalNodeSize += child.getSelectionGroupSize();
					childNodeSum
							.add((double) child.getSelectionGroupSize());

				}

				Vector<Double> childrenPercentage = new Vector<Double>();
				boolean first = true;
				int noLeaf = 0;
				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i);

					if (!child.isLeaf()) {
						List<Node> nextLevelChildren = child.getChildren();

						for (int j = 0; j < nextLevelChildren.size(); j++) {
							Node tempChild = nextLevelChildren.get(j);
							if (first) {
								double temp = tempChild
										.getSelectionGroupSize();
								childrenPercentage.add(tempChild
										.getSelectionGroupSize()
										/ childNodeSum.get(i));
							} else {
								double prevPercentage = childrenPercentage
										.get(j);
								double newPercentage = tempChild
										.getSelectionGroupSize()
										/ childNodeSum.get(i);
								childrenPercentage.set(j,
										(prevPercentage + newPercentage));
							}

						}
						first = false;
					} else {
						noLeaf++;
					}
				}

				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i);
					if (!child.isLeaf()) {

						List<Node> nextLevelChildren = child.getChildren();

						for (int j = 0; j < nextLevelChildren.size(); j++) {
							Node tempChild2 = nextLevelChildren.get(j);

							double estimatedChildren = 0.0;
							if (noLeaf == 0) {
								estimatedChildren = (double) childrenPercentage
										.get(j)
										/ (double) nextLevelChildren.size();
							} else {
								int newSize = nextLevelChildren.size() - noLeaf;
								estimatedChildren = (double) childrenPercentage
										.get(j)
										/ (double) newSize;
							}
							tempChild2
									.setPercentageOfVDescendants(estimatedChildren);
							nextLevelChildren.set(j, tempChild2);

						}
					}
				}

				// }
				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i);
					int k = children.size();
					double nodeSizePercentage = 0;

					if (divideLevelCountV >= divideLevels) {
						nodeSizePercentage = 1.0 / (double) k;
					} else {
						if (child.getPercentageOfHDescendants() == 0) {
							nodeSizePercentage = (child
									.getSelectionGroupSize() / (float) totalNodeSize);
						} else {
							nodeSizePercentage = child
									.getPercentageOfHDescendants();
						}
					}
					divideLevelCountV++;

					ConvertHierarchyToLayout newChildNode = new ConvertHierarchyToLayout(
							child);

					newChildNode.setDivide(ConvertHierarchyToLayout.HORIZONTAL);
					float buttonWidth = parentLayoutNode.getEndX()
							- parentLayoutNode.getStartX();
					float interval = 0;
					//
					// if (firstTime )
					// if (count < 2 )
					// {
					interval = Math.round(buttonWidth * nodeSizePercentage);
					// count++;
					totalInterval += interval;
					// }
					/*
					 * else { interval = Math.round(buttonWidth/(float)k); float
					 * remainder = buttonWidth - (interval*k);
					 */
					if (i == children.size() - 1) {
						float remainder = buttonWidth - totalInterval;
						interval += remainder;
					}

					/*
					 * }
					 */

					//
					// System.out.println("Selection group :" +
					// child.getSelectionGroupNodeSize());
					newChildNode.setStartX(i * interval);
					newChildNode.setEndX((i + 1) * interval);
					newChildNode.setStartY(parentLayoutNode.getStartY());
					newChildNode.setEndY(parentLayoutNode.getEndY());

					JPanel subBoxContainer = hierarchyToLayout(newChildNode,
							divideLevelCountV, divideLevelCountH);

					/*
					 * if(i > 0) { Dimension tmpDimension =
					 * subBoxContainer.getSize();
					 * 
					 * if( ) { } }
					 * 
					 * prevBox = subBoxContainer;
					 */

					// correct
					/*
					 * if( (subBoxContainer.getSize()).width < interval ) {
					 * subBoxContainer.setSize((int)Math.ceil(interval),(subBoxContainer.getSize()).height ); } //
					 */
					boxContainer.add(subBoxContainer);
					boxContainer.setAlignmentX(JPanel.LEFT_ALIGNMENT);
				}
			} else {
				boxContainer.setLayout(new BoxLayout(boxContainer,
						BoxLayout.PAGE_AXIS));
				int totalNodeSize = 0;
				int totalInterval = 0;

				Vector<Double> childNodeSum = new Vector<Double>();
				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i);
					totalNodeSize += child.getSelectionGroupSize();
					childNodeSum
							.add((double) child.getSelectionGroupSize());
					/*
					 * if(!child.isLeaf()) { List <Node<JVirtualKeyboardButton>>
					 * nextLevelChildren = child.getChildren(); double
					 * tmpChildSum = 0; for(int j = 0; j <
					 * nextLevelChildren.size();j++ ) { Node<JVirtualKeyboardButton>
					 * tempChild = nextLevelChildren.get(j); tmpChildSum +=
					 * tempChild.getSelectionGroupNodeSize(); }
					 * childNodeSum.add(tmpChildSum); } else {
					 * childNodeSum.add(0.0); }
					 */
				}

				Vector<Double> childrenPercentage = new Vector<Double>();
				boolean first = true;
				int noLeaf = 0;
				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i);

					if (!child.isLeaf()) {
						List<Node> nextLevelChildren = child.getChildren();

						for (int j = 0; j < nextLevelChildren.size(); j++) {
							Node tempChild = nextLevelChildren.get(j);
							if (first) {
								double temp = tempChild
										.getSelectionGroupSize();
								childrenPercentage.add(tempChild
										.getSelectionGroupSize()
										/ childNodeSum.get(i));
							} else {
								double prevPercentage = childrenPercentage
										.get(j);
								double newPercentage = tempChild
										.getSelectionGroupSize()
										/ childNodeSum.get(i);
								childrenPercentage.set(j,
										(prevPercentage + newPercentage));
							}

						}
						first = false;
					} else {
						noLeaf++;
					}
				}

				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i);
					if (!child.isLeaf()) {

						List<Node> nextLevelChildren = child.getChildren();

						for (int j = 0; j < nextLevelChildren.size(); j++) {
							Node tempChild2 = nextLevelChildren.get(j);

							double estimatedChildren = 0.0;
							if (noLeaf == 0) {
								estimatedChildren = (double) childrenPercentage
										.get(j)
										/ (double) nextLevelChildren.size();
							} else {
								int tempSize = nextLevelChildren.size()
										- noLeaf;
								estimatedChildren = (double) childrenPercentage
										.get(j)
										/ (double) tempSize;
							}

							tempChild2
									.setPercentageOfHDescendants(estimatedChildren);
							nextLevelChildren.set(j, tempChild2);

						}
					}
				}

				/*
				 * for (int i = 0; i < children.size(); i++) { Node<JVirtualKeyboardButton>
				 * child = children.get(i); totalNodeSize +=
				 * child.getSelectionGroupNodeSize(); }
				 */
				for (int i = 0; i < children.size(); i++) {
					Node child = children.get(i);
					ConvertHierarchyToLayout newChildNode = new ConvertHierarchyToLayout(
							child);

					int k = children.size();
					// int nodeSize = child.getSelectionGroupNodeSize();

					double nodeSizePercentage = 0;

					if (divideLevelCountH >= divideLevels) {
						nodeSizePercentage = 1.0 / (double) k;
					} else {
						if (child.getPercentageOfVDescendants() == 0) {
							nodeSizePercentage = child
									.getSelectionGroupSize()
									/ (float) totalNodeSize;
						} else {
							nodeSizePercentage = child
									.getPercentageOfVDescendants();
						}
					}
					divideLevelCountH++;
					newChildNode.setDivide(ConvertHierarchyToLayout.VERTICAL);
					float buttonHeight = parentLayoutNode.getEndY()
							- parentLayoutNode.getStartY();
					/*
					 * float interval = Math.round(buttonHeight/(float)k);
					 * 
					 * 
					 * float remainder = buttonHeight - (interval*k);
					 * 
					 * if(i == 0) { interval += remainder; }
					 */

					float interval = 0;
					//
					// if (firstTime )
					// if (count < 2 )
					// {
					interval = Math.round(buttonHeight * nodeSizePercentage);

					totalInterval += interval;
					if (i == children.size() - 1) {
						float remainder = buttonHeight - totalInterval;
						interval += remainder;
					}

					newChildNode.setStartY(i * interval);
					newChildNode.setEndY((i + 1) * interval);

					newChildNode.setStartX(parentLayoutNode.getStartX());
					newChildNode.setEndX(parentLayoutNode.getEndX());

					JPanel subBoxContainer = hierarchyToLayout(newChildNode,
							divideLevelCountV, divideLevelCountH);

					/*
					 * if( (subBoxContainer.getSize()).height < interval ) {
					 * subBoxContainer.setSize((subBoxContainer.getSize()).width,
					 * (int)Math.ceil(interval) ); }
					 */

					boxContainer.add(subBoxContainer);

				}
			}

			return boxContainer;
		}

	}

	/*
	 * public JPanel hierarchyToLayout (ConvertHierarchyToLayout
	 * parentLayoutNode ) { ContainmentHierarchyNode parentNode =
	 * parentLayoutNode.getNode(); List <ContainmentHierarchyNode> children =
	 * parentNode.getChildren();
	 * 
	 * JPanel boxContainer = new JPanel();
	 * 
	 * if(children.get(0).isLeaf() ) { if (parentLayoutNode.getDivide() ==
	 * ConvertHierarchyToLayout.VERTICAL) { boxContainer.setLayout(new
	 * BoxLayout(boxContainer, BoxLayout.PAGE_AXIS)); //since parent was divided
	 * vertically, these nodes have to be placed //horizontaly
	 * 
	 * float parentWidth = ((parentLayoutNode.getEndX()-
	 * parentLayoutNode.getStartX())* width); float parentHeight
	 * =((parentLayoutNode.getEndY()- parentLayoutNode.getStartY())* height);
	 * 
	 * int buttonWidth = (int) parentWidth ; int buttonHeight = (int)(
	 * parentHeight / (float)k);
	 * 
	 * for (int i = 0; i < children.size(); i++) { ContainmentHierarchyNode
	 * child = children.get(i);
	 * 
	 * JButton button =
	 * ((LeafContainmentHierarchyNode)child).getRepresentative();
	 * button.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
	 * button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
	 * button.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	 * 
	 * boxContainer.add(button); } } else { boxContainer.setLayout(new
	 * BoxLayout(boxContainer, BoxLayout.LINE_AXIS)); //since parent was divided
	 * horizontally, these nodes have to be placed vertaically
	 * 
	 * float parentWidth = ((parentLayoutNode.getEndX()-
	 * parentLayoutNode.getStartX())* width); float parentHeight
	 * =((parentLayoutNode.getEndY()- parentLayoutNode.getStartY())* height);
	 * 
	 * int buttonWidth = (int) (parentWidth / (float)k); int buttonHeight =
	 * (int) parentHeight;
	 * 
	 * for (int i = 0; i < children.size(); i++) { ContainmentHierarchyNode
	 * child = children.get(i);
	 * 
	 * JButton button =
	 * ((LeafContainmentHierarchyNode)child).getRepresentative();
	 * button.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
	 * button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
	 * button.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	 * 
	 * boxContainer.add(button); } }
	 * 
	 * 
	 * return boxContainer; } else { if (parentLayoutNode.getDivide() ==
	 * ConvertHierarchyToLayout.VERTICAL) { boxContainer.setLayout(new
	 * BoxLayout(boxContainer, BoxLayout.LINE_AXIS)); //if
	 * (parentLayoutNode.getDivide() == ConvertHierarchyToLayout.VERTICAL) for
	 * (int i = 0; i < children.size(); i++) { ContainmentHierarchyNode child =
	 * children.get(i); ConvertHierarchyToLayout newChildNode = new
	 * ConvertHierarchyToLayout(child);
	 * 
	 * newChildNode.setDivide(ConvertHierarchyToLayout.HORIZONTAL); float
	 * buttonWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
	 * float interval = (buttonWidth/(float)k); newChildNode.setStartX(i*
	 * interval); newChildNode.setEndX((i+1)* interval);
	 * newChildNode.setStartY(parentLayoutNode.getStartY());
	 * newChildNode.setEndY(parentLayoutNode.getEndY());
	 * 
	 * JPanel subBoxContainer = hierarchyToLayout (newChildNode);
	 * 
	 * boxContainer.add(subBoxContainer); } } else { boxContainer.setLayout(new
	 * BoxLayout(boxContainer, BoxLayout.PAGE_AXIS)); for (int i = 0; i <
	 * children.size(); i++) { ContainmentHierarchyNode child = children.get(i);
	 * ConvertHierarchyToLayout newChildNode = new
	 * ConvertHierarchyToLayout(child);
	 * 
	 * newChildNode.setDivide(ConvertHierarchyToLayout.VERTICAL); float
	 * buttonHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
	 * float interval = (buttonHeight/(float)k); newChildNode.setStartY(i*
	 * interval); newChildNode.setEndY((i+1)* interval);
	 * 
	 * newChildNode.setStartX(parentLayoutNode.getStartX());
	 * newChildNode.setEndX(parentLayoutNode.getEndX());
	 * 
	 * 
	 * JPanel subBoxContainer = hierarchyToLayout (newChildNode);
	 * 
	 * boxContainer.add(subBoxContainer); } }
	 * 
	 * return boxContainer; } }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see containmentHierarchyVariants.KeyboardLayout#getAllButtons()
	 */
	public List<JIndirectSelectionButton> getAllEnabledButtons() {
		List<JIndirectSelectionButton> allButtons = new Vector<JIndirectSelectionButton>();

		for (SourceSymbol s : keyList.getSourceSymbolsByRankOrder()) {
			JIndirectSelectionButton but = (JIndirectSelectionButton) s;
			allButtons.add(but);
		}

		return allButtons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see containmentHierarchyVariants.KeyboardLayout#getRows()
	 */
	public List<JPanel> getRows() {
		return onScreenButtonRows;
	}

	@Override
	public int setNumRows() {
		final int NUM_ROWS = 1;
		return NUM_ROWS;
	}

	@Override
	public int setNumCols() {
		final int NUM_COLS = 1;
		return NUM_COLS;
	}

	@Override
	public int setButtonWidth() {
		final int BUTTON_WIDTH = 1;
		return BUTTON_WIDTH;
	}

	@Override
	public int setButtonHeight() {
		final int BUTTON_HEIGHT = 1;
		return BUTTON_HEIGHT;
	}

}
