/*
 * 
 * 
 */

package keyboardLayouts.toBeFixed;


import java.awt.Component;
import javax.swing.*;

import driverApplications_ISF.DriverApp;
//import encodingPackage.ContainmentHierarchy;
import encodingTrees.obsolete.MyTraversableEncodingTree;
import encodingTrees.obsolete.TraversableEncodingTreeI;

import java.awt.event.*;
import java.awt.*;

import javax.swing.border.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import unequalLetterCostCode.ConstructCodeTree;

import java.awt.GridBagLayout;

import IndirectSelectionFacility.TextEntrySystemFrame;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import treeDataStructure.Node;
import treeDataStructure.LeafNode;
import treeDataStructure.InternalNode;


import java.awt.GridBagConstraints;

import analysisUserStudy.Graph;

/**
 * This class implements the algorithm to generate the keyboard layout
 * automatically, based on the containment hierarchy which is passed as an
 * input. The layout of the keyboard is generated automatically, and the main
 * goal is to eliminate the painfull process of creating the keyboard layout
 * manually.
 * 
 * 
 * @author Fatima Ramay
 */

public class EqualRealEstateRowbasedKBL extends ButtonLayoutSpecification {

	List<JPanel> onScreenButtonRows;
	// List<ConvertHierarchyToLayout> layoutList;
	SourceSymbolSet keyList;

	private static final int QUARTER = 4;
	private static final int HALF = 2;
	private static final int EIGTH = 8;
	private static final int SIXTH = 6;
	private static final int TWELVETH = 12;

	private static final int TINY_FONT_SIZE = 8;
	private static final int MIN_BUTTON_FONT_SIZE = 12;
	private static final int SMALLER_FONT_SIZE = 14;
	private static final int SMALL_FONT_SIZE = 16;
	private static final int REGULAR_FONT_SIZE = 18;
	private static final int LARGE_FONT_SIZE = 20;
	private static final int LARGER_FONT_SIZE = 22;
	private static final int LARGEST_FONT_SIZE = 24;
	private static final int pixelMargin = 2;
	private static final int MAX_PERCENTAGE = 100;

	private static final int SCREEN_WIDTH = 1024;
	private static final int SCREEN_HEIGHT = 512; // VoiceOutputCommunicationAidApplication.frame.getHeight();

	private static final int MIN_BUTTON_HEIGHT = 64;
	private static final int MIN_BUTTON_WIDTH = 128;

	private static final int TECHNIQUE1 = 1;
	private static final int TECHNIQUE2 = 2;
	private static final int TECHNIQUE3 = 3;

	private static final boolean EQUAL_SIZED = true;
	// private static final boolean EQUAL_SIZED = false;

	private List<Float> widthPercentageByLevel;
	private List<Float> heightPercentageByLevel;
	List<Integer> numOfNodesAtDepth;
	List<JIndirectSelectionButton> allButtons;
	// List <KBLNode<JVirtualKeyboardButton>> rowNodes ;

	private int technique;
	// private static final int W = 1;
	// private static final int H = 2;

	private static final String FONT_TYPE = "sanserif";

	public EqualRealEstateRowbasedKBL(
			TraversableEncodingTreeI root,
			SourceSymbolSet selectableKeyList) {
		keyList = selectableKeyList;

		KBLNode<JIndirectSelectionButton> rootNode = new KBLNode<JIndirectSelectionButton>(
				root.getRoot());
		rootNode.setStartX(0);
		rootNode.setStartY(0);
		rootNode.setEndX(1 * SCREEN_WIDTH);
		rootNode.setEndY(1 * SCREEN_HEIGHT);
		rootNode.setDivideType(KBLNode.HORIZONTAL);

		technique = TECHNIQUE1;
		widthPercentageByLevel = new Vector<Float>();
		heightPercentageByLevel = new Vector<Float>();
		numOfNodesAtDepth = new Vector<Integer>();
		allButtons = new Vector<JIndirectSelectionButton>();
		// rowNodes = new Vector <KBLNode<JVirtualKeyboardButton>> ();

		KBLNode<JIndirectSelectionButton> tmpRoot = new KBLNode<JIndirectSelectionButton>(
				root.getRoot());
		if (technique == TECHNIQUE1) {
			tmpRoot = preCalculationRowBased1(rootNode);
		}
		/*
		 * else if(technique == TECHNIQUE2) { tmpRoot = preCalculationRowBased2
		 * (rootNode); } else if(technique == TECHNIQUE3) { tmpRoot =
		 * preCalculationRowBased3 (rootNode); }
		 */
		JPanel keyboard = CHToKBLRowBased(tmpRoot, technique);
		onScreenButtonRows = new Vector<JPanel>();
		onScreenButtonRows.add(keyboard);

		Graph.constructGraph(allButtons, "RowBased_Technique3_Aug29.txt");

	}

	public KBLNode<JIndirectSelectionButton> preCalculationRowBased1(
			KBLNode<JIndirectSelectionButton> parentLayoutNode) {
		parentLayoutNode.setAvaialbleWidth(MAX_PERCENTAGE);
		parentLayoutNode.setAvaialbleHeight(MAX_PERCENTAGE);

		return preKBLCalculationRowBased1(parentLayoutNode);
	}

	public KBLNode<JIndirectSelectionButton> preKBLCalculationRowBased1(
			KBLNode<JIndirectSelectionButton> parentLayoutNode) {
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List<Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		// technique = TECHNIQUE1;
		if (parentNode.isLeaf()) {
			parentLayoutNode.setRequiredWidth(parentLayoutNode
					.getAvaialbleWidth());
			parentLayoutNode.setRequiredHeight(parentLayoutNode
					.getAvaialbleHeight());
		} else {
			if (parentLayoutNode.getDivideType() == KBLNode.VERTICAL) {
				float parentWidth = parentLayoutNode.getAvaialbleWidth();
				int parentAssociatedNodeSize = (((parentLayoutNode.getNode())
						.getSelectionGroup()).extractMembers()).size();

				float widthInterval = parentWidth
						/ (float) parentAssociatedNodeSize;
				float maxHeight = 0;

				for (int i = 0; i < children.size(); i++) {
					Node<JIndirectSelectionButton> child = children.get(i);
					int childAssociatedNodeSize = ((child
							.getSelectionGroup()).extractMembers()).size();
					KBLNode<JIndirectSelectionButton> newChildNode = new KBLNode<JIndirectSelectionButton>(
							child);
					newChildNode.setDivideType(KBLNode.VERTICAL);
					float width = childAssociatedNodeSize * widthInterval;
					newChildNode.setAvaialbleWidth(width);
					newChildNode.setRequiredWidth(width);
					newChildNode.setAvaialbleHeight(parentLayoutNode
							.getAvaialbleHeight());
					newChildNode.setRequiredHeight(parentLayoutNode
							.getAvaialbleHeight());
					KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculationRowBased1(newChildNode);
					if (i == 0) {
						parentLayoutNode.setRequiredWidth(tmpChildNode
								.getRequiredWidth());
						// parentLayoutNode.setRequiredHeight(tmpChildNode.getRequiredHeight());
					} else {
						float tmp = parentLayoutNode.getRequiredWidth();
						parentLayoutNode.setRequiredWidth(tmp
								+ tmpChildNode.getRequiredWidth());
						// parentLayoutNode.setRequiredHeight(tmpChildNode.getRequiredHeight());
					}
					if (tmpChildNode.getRequiredHeight() > maxHeight) {
						maxHeight = tmpChildNode.getRequiredHeight();
					}
					// parentLayoutNode.setRequiredHeight(parentLayoutNode.getAvaialbleHeight());
					parentLayoutNode.setRequiredHeight(maxHeight);
					parentLayoutNode.addChild(tmpChildNode);
				}
			} else {
				float maxWidth;
				for (int i = 0; i < children.size(); i++) {
					maxWidth = 0;
					Node<JIndirectSelectionButton> child = children.get(i);

					KBLNode<JIndirectSelectionButton> newChildNode = new KBLNode<JIndirectSelectionButton>(
							child);

					newChildNode.setDivideType(KBLNode.HORIZONTAL);
					int associatedNodeSize = (((newChildNode.getNode())
							.getSelectionGroup()).extractMembers()).size();
					if (SCREEN_WIDTH / (float) associatedNodeSize >= MIN_BUTTON_WIDTH) {
						newChildNode.setDivideType(KBLNode.VERTICAL);
						newChildNode.setAvaialbleHeight(MIN_BUTTON_HEIGHT);
						newChildNode.setAvaialbleWidth(SCREEN_WIDTH);
						newChildNode.setRequiredHeight(MIN_BUTTON_HEIGHT);
						// rowNodes.add(newChildNode);
					}
					KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculationRowBased1(newChildNode);
					if (i == 0) {
						parentLayoutNode.setRequiredHeight((tmpChildNode)
								.getRequiredHeight());
						// parentLayoutNode.setRequiredWidth((tmpChildNode).getRequiredWidth());
					} else {
						float tmp = parentLayoutNode.getRequiredHeight();
						parentLayoutNode.setRequiredHeight(tmp
								+ (tmpChildNode).getRequiredHeight());
						// parentLayoutNode.setRequiredWidth((tmpChildNode).getRequiredWidth());
					}
					// float prevWidth = 0;
					if (tmpChildNode.getRequiredWidth() > maxWidth) {
						maxWidth = tmpChildNode.getRequiredWidth();
					}
					parentLayoutNode.setRequiredWidth(maxWidth);
					parentLayoutNode.addChild(tmpChildNode);

				}
			}
		}
		return parentLayoutNode;
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

	public JPanel CHToKBLRowBased(
			KBLNode<JIndirectSelectionButton> parentLayoutNode, int technique) {
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List<Node<JIndirectSelectionButton>> children = parentNode.getChildren();

		JPanel boxContainer = new JPanel();

		if (parentNode.isLeaf()) {
			this.configureBoxContainer(boxContainer, parentLayoutNode);
			return boxContainer;
		} else {
			if (parentLayoutNode.getDivideType() == KBLNode.VERTICAL) {
				int totalInterval = 0;
				List<KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode
						.getChildren();
				// All the children are assigned the percentages of the width
				// */

				KBLNode<JIndirectSelectionButton> grandParent = parentLayoutNode.parent;
				List<Float> childWidthPercentage = calculateDimensionPercentageRowBased(
						parentLayoutNode, KBLNode.WIDTH, technique);
				JPanel subBoxContainer = new JPanel();

				float parentHeight = parentLayoutNode.getEndY()
						- parentLayoutNode.getStartY();
				float parentWidth = parentLayoutNode.getEndX()
						- parentLayoutNode.getStartX();
				KBLNode<JIndirectSelectionButton> prevChild = null;
				for (int i = 0; i < kblChildren.size(); i++) {
					KBLNode<JIndirectSelectionButton> kblChild = kblChildren
							.get(i);
					double nodeSizePercentage = childWidthPercentage.get(i);
					subBoxContainer = new JPanel();
					float interval = 0;
					interval = Math.round(parentWidth * nodeSizePercentage);
					totalInterval += interval;
					boolean fillerButtonNeeded = false;
					float remainder = 0;
					if (i == children.size() - 1) {
						remainder = parentWidth - totalInterval;

						if (EQUAL_SIZED) {
							if (grandParent.getDivideType() == KBLNode.HORIZONTAL) {
								if (remainder > 0) {
									fillerButtonNeeded = true;
								}
							}
						} else {
							interval += remainder;
						}
					}
					// New child node is created and dimensions are assigned to
					// it accordingly, and the
					// divide type is set.
					setChildLayoutNode2(parentLayoutNode, kblChild,
							KBLNode.WIDTH, interval, i, KBLNode.VERTICAL,
							prevChild);
					prevChild = kblChild;
					subBoxContainer = CHToKBLRowBased(kblChild, technique);
					boxContainer.add(subBoxContainer);
					if (fillerButtonNeeded) {

						JPanel tmpPanel = addFillerButton(remainder, kblChild,
								parentLayoutNode);
						boxContainer.add(tmpPanel);
					}

					this.configureBoxContainer(boxContainer, parentLayoutNode);
				}
			} else if (parentLayoutNode.getDivideType() == KBLNode.HORIZONTAL) {
				int totalInterval = 0;
				List<KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode
						.getChildren();
				// All the children are assigned the percentages of the width
				// */
				List<Float> childHeightPercentage = calculateHeightPercentage(parentLayoutNode);
				float parentHeight = parentLayoutNode.getEndY()
						- parentLayoutNode.getStartY();
				// float parentWidth = parentLayoutNode.getEndX()-
				// parentLayoutNode.getStartX();
				JPanel subBoxContainer = new JPanel();

				for (int i = 0; i < kblChildren.size(); i++) {
					KBLNode<JIndirectSelectionButton> kblChild = kblChildren
							.get(i);
					double nodeSizePercentage = 0.0;
					nodeSizePercentage = childHeightPercentage.get(i);
					// System.out.println("Height: calc : " +
					// nodeSizePercentage*parentHeight + " requ: "
					// +(kblChild.getRequiredHeight()/100)*SCREEN_HEIGHT);
					// System.out.println(" At" +
					// kblChild.getNode().toString());
					subBoxContainer = new JPanel();

					float interval = 0;
					interval = Math.round(parentHeight * nodeSizePercentage);
					totalInterval += interval;
					if (i == children.size() - 1) {
						float remainder = parentHeight - totalInterval;
						interval += remainder;
					}
					// New child node is created and dimensions are assigned to
					// it accordingly, and the
					// divide type is set.
					setChildLayoutNode(parentLayoutNode, kblChild,
							KBLNode.HEIGHT, interval, i, KBLNode.HORIZONTAL);

					float childHeight = kblChild.getEndY()
							- kblChild.getStartY();
					float childWidth = kblChild.getEndX()
							- kblChild.getStartX();
					int associatedNodeSize = (((kblChild.getNode())
							.getSelectionGroup()).extractMembers()).size();
					if (childHeight < MIN_BUTTON_HEIGHT
							|| (childWidth / (float) associatedNodeSize) >= MIN_BUTTON_WIDTH) {
						kblChild.setDivideType(KBLNode.VERTICAL);
						kblChild.setRequiredWidth(SCREEN_WIDTH);

					}
					subBoxContainer = CHToKBLRowBased(kblChild, technique);
					boxContainer.add(subBoxContainer);
					this.configureBoxContainer(boxContainer, parentLayoutNode);
				}

				// }

			}
			return boxContainer;
		}

	}

	private JPanel addFillerButton(float remainder,
			KBLNode<JIndirectSelectionButton> kblChild,
			KBLNode<JIndirectSelectionButton> parentLayoutNode) {

		JPanel tmpPanel = new JPanel();
		// tmpPanel.setLayout(new BoxLayout(tmpPanel,
		// BoxLayout.PAGE_AXIS));
		float mod = remainder % MIN_BUTTON_WIDTH;
		int div = (int) remainder / MIN_BUTTON_WIDTH;

		// int interval = MIN_BUTTON_WIDTH;

		float startX = kblChild.getEndX();
		float endX = startX + MIN_BUTTON_WIDTH;
		float startY = parentLayoutNode.getStartY();
		float endY = parentLayoutNode.getEndY();

		for (int i = 0; i < div; i++) {
			JIndirectSelectionButton but = sourceSymbolSet.VK_EMPTY.clone();
			but.setEnabled(false);
			LeafNode<JIndirectSelectionButton> leaf = new LeafNode<JIndirectSelectionButton>(
					but);
			KBLNode<JIndirectSelectionButton> tmpNode = new KBLNode<JIndirectSelectionButton>(
					leaf);
			tmpNode.setDivideType(KBLNode.VERTICAL);
			tmpNode.setStartX(startX);
			tmpNode.setEndX(endX);
			tmpNode.setStartY(startY);
			tmpNode.setEndY(endY);
			startX = endX;
			endX = endX + MIN_BUTTON_WIDTH;
			JPanel tmpPanel2 = new JPanel();
			this.configureBoxContainer(tmpPanel2, tmpNode);

			tmpPanel.add(tmpPanel2);

		}

		if (mod > 0) {
			JIndirectSelectionButton but = sourceSymbolSet.VK_EMPTY.clone();
			but.setEnabled(false);
			LeafNode<JIndirectSelectionButton> leaf = new LeafNode<JIndirectSelectionButton>(
					but);
			KBLNode<JIndirectSelectionButton> tmpNode = new KBLNode<JIndirectSelectionButton>(
					leaf);
			tmpNode.setDivideType(KBLNode.VERTICAL);
			tmpNode.setStartX(startX);
			tmpNode.setEndX(parentLayoutNode.getEndX());
			tmpNode.setStartY(startY);
			tmpNode.setEndY(endY);
			JPanel tmpPanel2 = new JPanel();
			this.configureBoxContainer(tmpPanel2, tmpNode);

			tmpPanel.add(tmpPanel2);

		}

		tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.LINE_AXIS));
		// boxContainer.add(subBoxContainer);
		tmpPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

		return tmpPanel;

	}

	private void setChildLayoutNode(
			KBLNode<JIndirectSelectionButton> parentLayoutNode,
			KBLNode<JIndirectSelectionButton> kblChild, int flag, float interval,
			int i, int divideType) {
		// KBLNode<JVirtualKeyboardButton> parentLayoutNode = kblChild.parent;
		if (flag == KBLNode.WIDTH) {
			kblChild.setDivideType(divideType);
			kblChild.setStartX(i * interval);
			kblChild.setEndX((i + 1) * interval);
			kblChild.setStartY(parentLayoutNode.getStartY());
			kblChild.setEndY(parentLayoutNode.getEndY());
		} else if (flag == KBLNode.HEIGHT) {
			kblChild.setDivideType(divideType);
			kblChild.setStartY(i * interval);
			kblChild.setEndY((i + 1) * interval);
			kblChild.setStartX(parentLayoutNode.getStartX());
			kblChild.setEndX(parentLayoutNode.getEndX());
		}
		// return kblChild;
	}

	private void setChildLayoutNode2(
			KBLNode<JIndirectSelectionButton> parentLayoutNode,
			KBLNode<JIndirectSelectionButton> kblChild, int flag, float interval,
			int i, int divideType, KBLNode<JIndirectSelectionButton> prevChild) {
		// KBLNode<JVirtualKeyboardButton> parentLayoutNode = kblChild.parent;
		if (flag == KBLNode.WIDTH) {
			kblChild.setDivideType(divideType);
			if (i == 0) {
				kblChild.setStartX(parentLayoutNode.getStartX());
			} else {
				kblChild.setStartX(prevChild.getEndX());
			}
			kblChild.setEndX(kblChild.getStartX() + interval);
			kblChild.setStartY(parentLayoutNode.getStartY());
			kblChild.setEndY(parentLayoutNode.getEndY());
		} else if (flag == KBLNode.HEIGHT) {
			kblChild.setDivideType(divideType);
			if (i == 0) {
				kblChild.setStartY(parentLayoutNode.getStartY());
			} else {
				kblChild.setStartY(prevChild.getEndY());
			}
			kblChild.setEndY(kblChild.getStartY() + interval);

			kblChild.setStartX(parentLayoutNode.getStartX());
			kblChild.setEndX(parentLayoutNode.getEndX());
		}
		// return kblChild;
	}

	private List<Float> calculateHeightPercentage(
			KBLNode<JIndirectSelectionButton> parentLayoutNode) {
		List<KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode
				.getChildren();
		List<Node<JIndirectSelectionButton>> children = parentLayoutNode
				.getNode().getChildren();

		Vector<Float> childDimPercentage = new Vector<Float>();
		int k = children.size();

		float heightSum = 0;
		for (int i = 0; i < kblChildren.size(); i++) {
			KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
			heightSum += kblChild.getRequiredHeight();
		}

		for (int i = 0; i < kblChildren.size(); i++) {
			KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
			float scalingFactor = (float) kblChild.getRequiredHeight()
					/ (float) heightSum;
			childDimPercentage.add(scalingFactor);
		}
		return childDimPercentage;
	}

	private List<Float> scalingTechnique1(
			KBLNode<JIndirectSelectionButton> parentLayoutNode) {
		List<KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode
				.getChildren();
		List<Node<JIndirectSelectionButton>> children = parentLayoutNode
				.getNode().getChildren();

		Vector<Float> childDimPercentage = new Vector<Float>();
		int k = children.size();

		for (int i = 0; i < kblChildren.size(); i++) {
			float scalingFactor = (float) 1 / (float) k;
			childDimPercentage.add(scalingFactor);

		}
		return childDimPercentage;

	}

	private List<Float> calculateDimensionPercentageRowBased(
			KBLNode<JIndirectSelectionButton> parentLayoutNode, int flag,
			int technique) {
		if (technique == TECHNIQUE1) {
			return calculateDimensionPercentageRowBased1(parentLayoutNode,
					flag, technique);
		}
		return calculateDimensionPercentageRowBased1(parentLayoutNode, flag,
				technique);
		/*
		 * else if(technique == TECHNIQUE2) { return
		 * calculateDimensionPercentageRowBased1Or2(parentLayoutNode, flag,
		 * technique); } else if(technique == TECHNIQUE3) { return
		 * calculateWidthPercentageRowBased3(parentLayoutNode, flag, technique); }
		 * return calculateDimensionPercentageRowBased1Or2(parentLayoutNode,
		 * flag, technique);
		 */
	}

	private List<Float> calculateDimensionPercentageRowBased1(
			KBLNode<JIndirectSelectionButton> parentLayoutNode, int flag,
			int technique) {
		List<KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode
				.getChildren();
		// List <Node<JVirtualKeyboardButton>> children =
		// parentLayoutNode.getNode().getChildren();

		KBLNode<JIndirectSelectionButton> grandParent = parentLayoutNode.parent;

		Vector<Float> childDimPercentage = new Vector<Float>();
		int parentAssociatedNodeSize = (((parentLayoutNode.getNode())
				.getSelectionGroup()).extractMembers()).size();
		float minWidth = parentAssociatedNodeSize * MIN_BUTTON_WIDTH;
		float parentWidth = parentLayoutNode.getRequiredWidth();
		if (EQUAL_SIZED && grandParent.getDivideType() == KBLNode.HORIZONTAL
				&& minWidth <= parentWidth) {
			for (int i = 0; i < kblChildren.size(); i++) {
				KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
				int childAssociatedNodeSize = (((kblChild.getNode())
						.getSelectionGroup()).extractMembers()).size();
				float scalingFactor = (float) (childAssociatedNodeSize * MIN_BUTTON_WIDTH)
						/ (float) parentWidth;
				childDimPercentage.add(scalingFactor);
			}

		} else {
			for (int i = 0; i < kblChildren.size(); i++) {
				KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
				int childAssociatedNodeSize = (((kblChild.getNode())
						.getSelectionGroup()).extractMembers()).size();
				float scalingFactor = (float) childAssociatedNodeSize
						/ (float) parentAssociatedNodeSize;
				childDimPercentage.add(scalingFactor);
			}
		}
		return childDimPercentage;
	}

	private List<Float> calculateDimensionPercentage(
			KBLNode<JIndirectSelectionButton> parentLayoutNode, int flag,
			int technique) {
		if (technique == TECHNIQUE1) {
			return scalingTechnique1(parentLayoutNode);
		}
		/*
		 * else if(technique == TECHNIQUE2) { return
		 * scalingTechnique2Or3(parentLayoutNode, flag,technique); } else
		 * if(technique == TECHNIQUE3) { return
		 * scalingTechnique2Or3(parentLayoutNode, flag,technique); }
		 */
		else {
			// default is technique 1
			return scalingTechnique1(parentLayoutNode);
		}
	}

	/*
	 * In this techinique, the Dimension percentage is assigned accordign to the
	 * cost of each child of the node. For example, if the letter cost of the
	 * alphabet {a1,a2,a3,a4,a5} = {1,2,2,1,4} then the percentage would be
	 * assigned such that the children with lower cost gets the highest share of
	 * percentage i.e. in this case, the percentage assigned would be {4/13,
	 * 2/13,2/13,4/13,1/13}
	 */

	private void configureBoxContainer(JPanel boxContainer,
			KBLNode<JIndirectSelectionButton> layoutNode) {
		Node<JIndirectSelectionButton> parentNode = layoutNode.getNode();

		if (parentNode.isLeaf()) {
			boxContainer.setLayout(new BoxLayout(boxContainer,
					BoxLayout.PAGE_AXIS));
			JIndirectSelectionButton button = this.getButton(layoutNode);
			boxContainer.add(button);
		} else {
			if (layoutNode.getDivideType() == KBLNode.VERTICAL) {// parent
																	// node
																	// Vertical
				// JPanel subBoxContainer = CHToKBL (layoutNode);

				// this.configureBoxContainer(boxContainer, parentLayoutNode);
				// return boxContainer;
				boxContainer.setLayout(new BoxLayout(boxContainer,
						BoxLayout.LINE_AXIS));
				// boxContainer.add(subBoxContainer);
				boxContainer.setAlignmentX(JPanel.LEFT_ALIGNMENT);

			} else if (layoutNode.getDivideType() == KBLNode.HORIZONTAL) {

				boxContainer.setLayout(new BoxLayout(boxContainer,
						BoxLayout.PAGE_AXIS));
				// JPanel subBoxContainer = CHToKBL (layoutNode);

				// boxContainer.add(subBoxContainer);
			}
		}
	}

	private JIndirectSelectionButton getButton(
			KBLNode<JIndirectSelectionButton> layoutNode) {
		Node<JIndirectSelectionButton> parentNode = layoutNode.getNode();

		float parentWidth = ((layoutNode.getEndX() - layoutNode.getStartX()));
		float parentHeight = ((layoutNode.getEndY() - layoutNode.getStartY()));

		int buttonWidth = (int) Math.ceil(parentWidth);
		int buttonHeight = (int) Math.ceil(parentHeight);

		JIndirectSelectionButton button = ((Node<JIndirectSelectionButton>) parentNode)
				.getRepresentative();
		button.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		button.setMaximumSize(new Dimension(buttonWidth, buttonHeight));

		this.modifyButton(button, buttonWidth, buttonHeight);

		if (button.getText().contains("FillerLabel")) {
			button.setText("");
			button.setEnabled(false);
		}
		allButtons.add(button);
		return button;
	}

	private void createSpecialButtonForDigit(
			KBLNode<JIndirectSelectionButton> kblDigit,
			Node<JIndirectSelectionButton> digitNode) {

		TraversableEncodingTreeI digitCH = createDigitCH(digitNode);
	}

	private TraversableEncodingTreeI createDigitCH(
			Node<JIndirectSelectionButton> digitNode) {
		InternalNode<JIndirectSelectionButton> root = new InternalNode<JIndirectSelectionButton>();

		LeafNode<JIndirectSelectionButton> node1 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_1);
		LeafNode<JIndirectSelectionButton> node2 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_2);
		LeafNode<JIndirectSelectionButton> node3 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_3);
		LeafNode<JIndirectSelectionButton> node4 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_4);
		LeafNode<JIndirectSelectionButton> node5 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_5);
		LeafNode<JIndirectSelectionButton> node6 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_6);
		LeafNode<JIndirectSelectionButton> node7 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_7);
		LeafNode<JIndirectSelectionButton> node8 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_8);
		LeafNode<JIndirectSelectionButton> node9 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_9);
		LeafNode<JIndirectSelectionButton> node0 = new LeafNode<JIndirectSelectionButton>(
				sourceSymbolSet.VK_0);

		InternalNode<JIndirectSelectionButton> node12 = new InternalNode<JIndirectSelectionButton>();
		node12.addChild(node1);
		node12.addChild(node2);

		InternalNode<JIndirectSelectionButton> node45 = new InternalNode<JIndirectSelectionButton>();
		node45.addChild(node4);
		node45.addChild(node5);

		InternalNode<JIndirectSelectionButton> node345 = new InternalNode<JIndirectSelectionButton>();
		node345.addChild(node3);
		node345.addChild(node45);

		InternalNode<JIndirectSelectionButton> node12345 = new InternalNode<JIndirectSelectionButton>();

		node12345.addChild(node12);
		node12345.addChild(node345);

		InternalNode<JIndirectSelectionButton> node67 = new InternalNode<JIndirectSelectionButton>();
		node67.addChild(node6);
		node67.addChild(node7);

		InternalNode<JIndirectSelectionButton> node90 = new InternalNode<JIndirectSelectionButton>();
		node90.addChild(node9);
		node90.addChild(node0);

		InternalNode<JIndirectSelectionButton> node890 = new InternalNode<JIndirectSelectionButton>();
		node890.addChild(node8);
		node890.addChild(node90);

		InternalNode<JIndirectSelectionButton> node67890 = new InternalNode<JIndirectSelectionButton>();

		node67890.addChild(node67);
		node67890.addChild(node890);

		root.addChild(node12345);
		root.addChild(node67890);

		root.updateAssociatedNodeSet();
		return new MyTraversableEncodingTree<JIndirectSelectionButton>(root);
	}

	/**
	 * Method has side-effects: it modifies the passed button
	 * 
	 * @param button
	 */
	private void modifyButton(JIndirectSelectionButton button, int buttonWidth,
			int buttonHeight) {

		// Setting appropriate Font size for the selectables, according to
		// their real estate share

		// System.out.println(button);

		System.out.println(button.getText() + " H: "
				+ button.getPreferredSize().height + " W: "
				+ button.getPreferredSize().width);

		if (buttonWidth >= SCREEN_WIDTH / QUARTER
				&& buttonHeight >= SCREEN_HEIGHT / QUARTER) {
			if (button.getText().length() == 1) {

				button
						.setFont(new Font(FONT_TYPE, Font.BOLD,
								LARGEST_FONT_SIZE));

			} else {
				button
						.setFont(new Font(FONT_TYPE, Font.BOLD,
								LARGEST_FONT_SIZE));
			}
			Insets insets = new Insets(1, 1, 1, 1);
			button.setMargin(insets);
		} else if (buttonWidth >= SCREEN_WIDTH / EIGTH
				&& buttonHeight >= SCREEN_HEIGHT / EIGTH) {
			if (button.getText().length() == 1) {

				button
						.setFont(new Font(FONT_TYPE, Font.BOLD,
								LARGER_FONT_SIZE));
			} else {
				button
						.setFont(new Font(FONT_TYPE, Font.BOLD,
								LARGER_FONT_SIZE));

			}
			Insets insets = new Insets(1, 1, 1, 1);
			button.setMargin(insets);
		} else if (buttonWidth >= SCREEN_WIDTH / TWELVETH
				|| buttonHeight >= SCREEN_HEIGHT / TWELVETH) {
			if (button.getText().length() == 1) {

				button.setFont(new Font(FONT_TYPE, Font.BOLD, SMALL_FONT_SIZE));
			} else {
				button
						.setFont(new Font(FONT_TYPE, Font.PLAIN,
								SMALL_FONT_SIZE));
				// System.out.println("First2");
			}
			Insets insets = new Insets(1, 1, 1, 1);
			button.setMargin(insets);
		} else if (buttonWidth >= SCREEN_WIDTH / TWELVETH
				&& buttonHeight >= SCREEN_HEIGHT / TWELVETH) {
			if (button.getText().length() == 1) {

				button.setFont(new Font(FONT_TYPE, Font.BOLD, LARGE_FONT_SIZE));
			} else {
				button
						.setFont(new Font(FONT_TYPE, Font.PLAIN,
								LARGE_FONT_SIZE));

			}
			Insets insets = new Insets(1, 1, 1, 1);
			button.setMargin(insets);
		} else if (buttonWidth <= MIN_BUTTON_WIDTH
				&& buttonHeight <= MIN_BUTTON_HEIGHT) {
			Insets insets = new Insets(0, 0, 0, 0);
			button.setMargin(insets);
			// if(buttonWidth <= (int)width/24.0 || buttonHeight <=
			// (int)height/24.0)
			if (button.getText().length() == 1) {
				button.setFont(new Font(FONT_TYPE, Font.BOLD,
						MIN_BUTTON_FONT_SIZE));
			} else {
				button.setFont(new Font(FONT_TYPE, Font.PLAIN, TINY_FONT_SIZE));
			}
		} else if (buttonWidth <= MIN_BUTTON_WIDTH
				|| buttonHeight <= MIN_BUTTON_HEIGHT) {
			Insets insets = new Insets(0, 0, 0, 0);
			button.setMargin(insets);
			if (button.getText().length() == 1) {
				button
						.setFont(new Font(FONT_TYPE, Font.BOLD,
								SMALLER_FONT_SIZE));
			} else {
				button.setFont(new Font(FONT_TYPE, Font.PLAIN,
						SMALLER_FONT_SIZE));
			}
		} else {
			if (button.getText().length() == 1) {
				button
						.setFont(new Font(FONT_TYPE, Font.BOLD,
								REGULAR_FONT_SIZE));
			} else {
				button
						.setFont(new Font(FONT_TYPE, Font.PLAIN,
								SMALL_FONT_SIZE));
			}
			Insets insets = new Insets(1, 1, 1, 1);
			button.setMargin(insets);
		}
		// System.out.println(button + " size " + button.getFont());
	}

	/*
	 * public JPanel hierarchyToLayout (KBLNode parentLayoutNode ) {
	 * ContainmentHierarchyNode parentNode = parentLayoutNode.getNode(); List
	 * <ContainmentHierarchyNode> children = parentNode.getChildren();
	 * 
	 * JPanel boxContainer = new JPanel();
	 * 
	 * if(children.get(0).isLeaf() ) { if (parentLayoutNode.getDivide() ==
	 * KBLNode.VERTICAL) { boxContainer.setLayout(new BoxLayout(boxContainer,
	 * BoxLayout.PAGE_AXIS)); //since parent was divided vertically, these nodes
	 * have to be placed //horizontaly
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
	 * boxContainer.add(button);
	 *  } } else { boxContainer.setLayout(new BoxLayout(boxContainer,
	 * BoxLayout.LINE_AXIS)); //since parent was divided horizontally, these
	 * nodes have to be placed vertaically
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
	 * boxContainer.add(button);
	 *  } }
	 * 
	 * 
	 * return boxContainer; } else { if (parentLayoutNode.getDivide() ==
	 * KBLNode.VERTICAL) { boxContainer.setLayout(new BoxLayout(boxContainer,
	 * BoxLayout.LINE_AXIS)); //if (parentLayoutNode.getDivide() ==
	 * KBLNode.VERTICAL) for (int i = 0; i < children.size(); i++) {
	 * ContainmentHierarchyNode child = children.get(i); KBLNode newChildNode =
	 * new KBLNode(child);
	 * 
	 * newChildNode.setDivide(KBLNode.HORIZONTAL); float buttonWidth =
	 * parentLayoutNode.getEndX()- parentLayoutNode.getStartX(); float interval =
	 * (buttonWidth/(float)k); newChildNode.setStartX(i* interval);
	 * newChildNode.setEndX((i+1)* interval);
	 * newChildNode.setStartY(parentLayoutNode.getStartY());
	 * newChildNode.setEndY(parentLayoutNode.getEndY());
	 * 
	 * JPanel subBoxContainer = hierarchyToLayout (newChildNode);
	 * 
	 * boxContainer.add(subBoxContainer);
	 *  } } else { boxContainer.setLayout(new BoxLayout(boxContainer,
	 * BoxLayout.PAGE_AXIS)); for (int i = 0; i < children.size(); i++) {
	 * ContainmentHierarchyNode child = children.get(i); KBLNode newChildNode =
	 * new KBLNode(child);
	 * 
	 * newChildNode.setDivide(KBLNode.VERTICAL); float buttonHeight =
	 * parentLayoutNode.getEndY()- parentLayoutNode.getStartY(); float interval =
	 * (buttonHeight/(float)k); newChildNode.setStartY(i* interval);
	 * newChildNode.setEndY((i+1)* interval);
	 * 
	 * newChildNode.setStartX(parentLayoutNode.getStartX());
	 * newChildNode.setEndX(parentLayoutNode.getEndX());
	 * 
	 * 
	 * JPanel subBoxContainer = hierarchyToLayout (newChildNode);
	 * 
	 * boxContainer.add(subBoxContainer);
	 * 
	 *  } }
	 * 
	 * return boxContainer; }
	 *  }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see containmentHierarchyVariants.KeyboardLayout#getAllButtons()
	 */
	public List<JIndirectSelectionButton> getAllEnabledButtons() {
		List<JIndirectSelectionButton> allButtons = new Vector<JIndirectSelectionButton>();

		for (JIndirectSelectionButton but : keyList.getSourceSymbolsByRankOrder()) {
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
