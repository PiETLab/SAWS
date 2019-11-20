/*
 * 
 * 
 */

package keyboardLayouts.toBeFixed;


import java.awt.Component;
import javax.swing.*;

import driverApplications_ISF.DriverApp;
import encodingTrees.ContainmentHierarchy;

import java.awt.event.*;
import java.awt.*;

import javax.swing.border.*;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import buttonLayouts.ButtonLayoutSpecification;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

import java.awt.GridBagLayout;

import IndirectSelectionFacility.TextEntrySystemFrame;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import TreeDataStructure.Node;
import TreeDataStructure.LeafNode; 
import TreeDataStructure.InternalNode;


import java.awt.GridBagConstraints;


/**
 * This class implements the algorithm to generate the keyboard layout automatically, 
 * based on the containment hierarchy which is passed as an input.  
 * The layout of the keyboard is generated automatically, and the main goal is to 
 * eliminate the painfull process of creating the keyboard layout manually. 
 * 
 * 
 * @author Fatima Ramay
 */


public class RamayKeyboardLayoutModified2 extends ButtonLayoutSpecification 
{
	
	List<JPanel> onScreenButtonRows;
	//List<ConvertHierarchyToLayout> layoutList;
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
	private static final int SCREEN_HEIGHT = 512; //VoiceOutputCommunicationAidApplication.frame.getHeight();
	
	private static final int MIN_BUTTON_HEIGHT = 32;
	private static final int MIN_BUTTON_WIDTH = 64;
	
	private static final String FONT_TYPE = "sanserif";
	
	
	public RamayKeyboardLayoutModified2(MyTraversableEncodingTree<JIndirectSelectionButton> root, SourceSymbolSet selectableKeyList) 
	{
		keyList = selectableKeyList;
				
		KBLNode<JIndirectSelectionButton> rootNode = new KBLNode<JIndirectSelectionButton>(root.getRoot());
		rootNode.setStartX(0);
		rootNode.setStartY(0);
		rootNode.setEndX(1*SCREEN_WIDTH);
		rootNode.setEndY(1*SCREEN_HEIGHT);
		rootNode.setDivideType(KBLNode.VERTICAL);
		
		//InternalNode<JVirtualKeyboardButton> temp =  (InternalNode)root.getRoot();
		//int maxDepth = temp.calculateMaximumLeafDepth(0);
		//int maxLevel = temp.calculateMaximumLeafLevel();
		//System.out.println("The depth............" +  maxDepth);
		//System.out.println("The level............" +  maxLevel);
		
		//System.out.println("Screen width" + TextCompositionFacilityFrame.SCREEN_WIDTH); 
		//System.out.println("Screen height" + TextCompositionFacilityFrame.SCREEN_HEIGHT);
		
		KBLNode<JIndirectSelectionButton> tmpRoot = preCalculation (rootNode);
		
		//JPanel keyboard = CHToKBL (rootNode);
		JPanel keyboard = CHToKBL (tmpRoot);
		onScreenButtonRows = new Vector<JPanel>();
		onScreenButtonRows.add(keyboard);
		
		
	}
	public KBLNode<JIndirectSelectionButton> preCalculation (KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		int k = children.size();
		float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
		float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
		int maxLevel = parentNode.calculateMaximumLeafLevel();
		float minWidth = 0;
		float minHeight = 0;
		int smallerLeafNodeSum = 0;
		
		if(maxLevel != -1)
		{
			int j = 0;
			for(j = maxLevel ; j >=1; j--)
			{
				minWidth = MIN_BUTTON_WIDTH * (float)Math.pow(k, Math.ceil((double)j/k));
				minHeight = MIN_BUTTON_HEIGHT * (float)Math.pow(k, Math.ceil((double)j/k));
				minHeight = minHeight / 2;
			
				if(minWidth > SCREEN_WIDTH || minHeight > SCREEN_HEIGHT)
				{
					//It means the displayed button size for the deepest buttons
					//would be less than the Minimum dimensions specified. 
					List<Node<JIndirectSelectionButton>> leafNodes = ((InternalNode<JIndirectSelectionButton>)parentNode).getleavesAtKBLLevel(j);
					smallerLeafNodeSum += leafNodes.size();
			
				}	
			}
			maxLevel = j;
		}
		if(smallerLeafNodeSum > 0)
		{
			//It means the displayed button size for the deepest buttons
			//would be less than the Minimum dimensions specified. 
			
		//	List<Node<JVirtualKeyboardButton>> leafNodes = ((InternalNode<JVirtualKeyboardButton>)parentNode).getleavesAtKBLLevel(maxLevel);
			
			//this is the maximum width and height which would be consumed by all the leaf nodes
			// at the deepest level
			float estimatedLeafWidth = smallerLeafNodeSum * MIN_BUTTON_WIDTH;
			float estimatedLeafHeight = smallerLeafNodeSum * MIN_BUTTON_HEIGHT;
			
			float leafWidthPercentage = estimatedLeafWidth/SCREEN_WIDTH * MAX_PERCENTAGE;
			float leafHeightPercentage = estimatedLeafHeight/SCREEN_HEIGHT * MAX_PERCENTAGE;
			
			float availableWidthPercentage = MAX_PERCENTAGE - leafWidthPercentage;
			float availbleHeightPercentage = MAX_PERCENTAGE - leafHeightPercentage;;
			
			parentLayoutNode.setAvaialbleWidth(availableWidthPercentage);
			parentLayoutNode.setAvaialbleHeight(availbleHeightPercentage);
			int level = 0;
			return preKBLCalculation(parentLayoutNode, level, maxLevel);
			
		}
		return parentLayoutNode;
		
	}
	
	public KBLNode<JIndirectSelectionButton> preKBLCalculation (KBLNode<JIndirectSelectionButton> parentLayoutNode, int level, int maxLevel)
	{
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		
		if(parentNode.isLeaf() )
		{
				//float parentWidth = ((parentLayoutNode.getEndX()- parentLayoutNode.getStartX()));
				//float parentHeight =((parentLayoutNode.getEndY()- parentLayoutNode.getStartY()));
				
				if(level >= maxLevel)
				{
					float tmpW = (float)MIN_BUTTON_WIDTH/SCREEN_WIDTH;
					float tmpH = (float) MIN_BUTTON_HEIGHT/SCREEN_HEIGHT;
					float leafWidthPercentage = tmpW * MAX_PERCENTAGE;
					float leafHeightPercentage = tmpH * MAX_PERCENTAGE;
					
					
					parentLayoutNode.setRequiredWidth(leafWidthPercentage);
					parentLayoutNode.setRequiredHeight(leafHeightPercentage);
				}
				else
				{
					parentLayoutNode.setRequiredWidth(parentLayoutNode.getAvaialbleWidth());
					parentLayoutNode.setRequiredHeight(parentLayoutNode.getAvaialbleHeight());
				}
				
		}
		else
		{
			if (parentLayoutNode.getDivideType() == KBLNode.VERTICAL)
			{
				
				
				int k = children.size();
				level = level +1;
				float maxHeight = 0;
				//All the children are assigned the avalaible percentages of the width 
				for (int i = 0; i < children.size(); i++)
				{
					Node<JIndirectSelectionButton> child = children.get(i);
					
					double childPercentage = 1.0/(double)k;
						
					KBLNode<JIndirectSelectionButton> newChildNode = new KBLNode<JIndirectSelectionButton>(child); 
					
					if(parentLayoutNode.getAvaialbleWidth() != -1)
					{
						float childWidth = (float)childPercentage * parentLayoutNode.getAvaialbleWidth();
					
						newChildNode.setDivideType(KBLNode.HORIZONTAL);
						newChildNode.setAvaialbleWidth(childWidth);
						newChildNode.setAvaialbleHeight(parentLayoutNode.getAvaialbleHeight());
						//level = level +1;
						KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculation(newChildNode,level, maxLevel);
						if(i == 0)
						{
							parentLayoutNode.setRequiredWidth(tmpChildNode.getRequiredWidth());
							//parentLayoutNode.setRequiredHeight(tmpChildNode.getRequiredHeight());
						}
						else
						{
							float tmp = parentLayoutNode.getRequiredWidth();
							parentLayoutNode.setRequiredWidth(tmp + tmpChildNode.getRequiredWidth());
							//parentLayoutNode.setRequiredHeight(tmpChildNode.getRequiredHeight());
						}
						if (tmpChildNode.getRequiredHeight() > maxHeight)
						{
							maxHeight = tmpChildNode.getRequiredHeight();
						}
						//parentLayoutNode.setRequiredHeight(parentLayoutNode.getAvaialbleHeight());
						parentLayoutNode.setRequiredHeight(maxHeight);
						parentLayoutNode.addChild(tmpChildNode);
					}
				}
			}
			else
			{
				int k = children.size();
				level = level +1;
				float maxWidth = 0;
				//All the children are assigned the avalaible percentages of the width 
				for (int i = 0; i < children.size(); i++)
				{
					Node<JIndirectSelectionButton> child = children.get(i);
					
					double childPercentage = 1.0/(double)k;
						
					KBLNode<JIndirectSelectionButton> newChildNode = new KBLNode<JIndirectSelectionButton>(child); 
					
					if(parentLayoutNode.getAvaialbleHeight() != -1)
					{
						float childHeight = (float)childPercentage * parentLayoutNode.getAvaialbleHeight();
					
						newChildNode.setDivideType(KBLNode.VERTICAL);
						newChildNode.setAvaialbleHeight(childHeight);
						newChildNode.setAvaialbleWidth(parentLayoutNode.getAvaialbleWidth());
						KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculation(newChildNode,level, maxLevel); 					
						if(i == 0)
						{
							parentLayoutNode.setRequiredHeight((tmpChildNode).getRequiredHeight());
							//parentLayoutNode.setRequiredWidth((tmpChildNode).getRequiredWidth());
						}
						else
						{
							float tmp = parentLayoutNode.getRequiredHeight();
							parentLayoutNode.setRequiredHeight(tmp + (tmpChildNode).getRequiredHeight());
							//parentLayoutNode.setRequiredWidth((tmpChildNode).getRequiredWidth());
						}
						//parentLayoutNode.setRequiredWidth(parentLayoutNode.getAvaialbleWidth());
						
						if (tmpChildNode.getRequiredWidth() > maxWidth)
						{
							maxWidth = tmpChildNode.getRequiredWidth();
						}
						parentLayoutNode.setRequiredWidth(maxWidth);
						parentLayoutNode.addChild(tmpChildNode);
					}
				}

			}
		}
		return parentLayoutNode;
	}

	
	
	/**
	 * This method takes as input the root node of the containment hierarchy.
	 * The keyboard is created as follows:
	 * The available space for the keyboard layout is divided according the to number
	 * of the children of the root node. Each child node is assigned a specific space in the
	 * total avaialble space for the keyboard. 
	 * 
	 * The space assigned to every child node is recursively divided into further sub-spaces 
	 * according to their children, until a leaf node is reached.  
	 * 
	 *  Every time, the space is once divided horizontally , and once vertically. That is, 
	 *  if the space assigned to the parent was divided vertially, then the space assigned to its children 
	 *  will be divided horizontally, and every child node will be arranged horizontally in that space. 
	 *  
	 *  
	 * 
	 * @return
	 */
	
	public JPanel CHToKBL (KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		
		JPanel boxContainer = new JPanel();
		
		if(parentNode.isLeaf() )
		{
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.PAGE_AXIS));
			
				float parentWidth = ((parentLayoutNode.getEndX()- parentLayoutNode.getStartX()));
				float parentHeight =((parentLayoutNode.getEndY()- parentLayoutNode.getStartY()));
				
				
				int buttonWidth =(int) Math.ceil( parentWidth) ;
				int buttonHeight =(int) Math.ceil(parentHeight );
				
				JIndirectSelectionButton button = ((Node<JIndirectSelectionButton>)parentNode).getRepresentative();  
				button.setMinimumSize(new Dimension(buttonWidth, buttonHeight)); 
				button.setPreferredSize(new Dimension(buttonWidth, buttonHeight)); 
				button.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
				
				//Setting appropriate Font size for the selectables, according to their real estate share
				
				if(buttonWidth >= SCREEN_WIDTH/QUARTER && buttonHeight >= SCREEN_HEIGHT/QUARTER)
				{
					if(button.getText().length() == 1)
					{
					
						button.setFont(new Font(FONT_TYPE, Font.BOLD, LARGEST_FONT_SIZE ));
					}
					else
					{
						button.setFont(new Font(FONT_TYPE, Font.PLAIN, LARGEST_FONT_SIZE ));
				
					}
					Insets insets=new Insets(1,1,1,1);
					button.setMargin(insets);
				}
				else if(buttonWidth >= SCREEN_WIDTH/EIGTH && buttonHeight >= SCREEN_HEIGHT/EIGTH)
				{
					if(button.getText().length() == 1)
					{
					
						button.setFont(new Font(FONT_TYPE, Font.BOLD, LARGER_FONT_SIZE ));
					}
					else
					{
						button.setFont(new Font(FONT_TYPE, Font.PLAIN, LARGER_FONT_SIZE ));
				
					}
					Insets insets=new Insets(1,1,1,1);
					button.setMargin(insets);
				}
				else if(buttonWidth >= SCREEN_WIDTH/TWELVETH || buttonHeight >= SCREEN_HEIGHT/TWELVETH)
				{
					if(button.getText().length() == 1)
					{
					
					button.setFont(new Font(FONT_TYPE, Font.BOLD, SMALL_FONT_SIZE ));
					}
					else
					{
						button.setFont(new Font(FONT_TYPE, Font.PLAIN, SMALL_FONT_SIZE ));
						//System.out.println("First2");
					}
					Insets insets=new Insets(1,1,1,1);
					button.setMargin(insets);
				}
				else if(buttonWidth >= SCREEN_WIDTH/TWELVETH && buttonHeight >= SCREEN_HEIGHT/TWELVETH)
				{
					if(button.getText().length() == 1)
					{
					
					button.setFont(new Font(FONT_TYPE, Font.BOLD, LARGE_FONT_SIZE ));
					}
					else
					{
						button.setFont(new Font(FONT_TYPE, Font.PLAIN, LARGE_FONT_SIZE ));
					
					}
					Insets insets=new Insets(1,1,1,1);
					button.setMargin(insets);
				}
				else if(buttonWidth <= MIN_BUTTON_WIDTH && buttonHeight <= MIN_BUTTON_HEIGHT)
				{
					Insets insets=new Insets(0,0,0,0);
					button.setMargin(insets);
					//if(buttonWidth <= (int)width/24.0 || buttonHeight <= (int)height/24.0)
					if(button.getText().length() == 1)
					{
						button.setFont(new Font(FONT_TYPE, Font.BOLD, MIN_BUTTON_FONT_SIZE));
					}
					else
					{
						button.setFont(new Font(FONT_TYPE, Font.PLAIN, TINY_FONT_SIZE));
					}
				}
				else if(buttonWidth <= MIN_BUTTON_WIDTH || buttonHeight <= MIN_BUTTON_HEIGHT)
				{
					Insets insets=new Insets(0,0,0,0);
					button.setMargin(insets);
					if(button.getText().length() == 1)
					{
						button.setFont(new Font(FONT_TYPE, Font.BOLD, SMALLER_FONT_SIZE));
					}
					else
					{
						button.setFont(new Font(FONT_TYPE, Font.PLAIN, SMALLER_FONT_SIZE));
					}
				}
				else 
				{
					if(button.getText().length() == 1)
					{
						button.setFont(new Font(FONT_TYPE, Font.BOLD, REGULAR_FONT_SIZE));
					}
					else
					{
						button.setFont(new Font(FONT_TYPE, Font.PLAIN, SMALL_FONT_SIZE));
					}
					Insets insets=new Insets(1,1,1,1);
					button.setMargin(insets);
				}
				if(button.getText().contains("FillerLabel") )
				{
					button.setText("");
					button.setEnabled(false);
				}
				boxContainer.add(button);
			return boxContainer;
		}
		else
		{
			if (parentLayoutNode.getDivideType() == KBLNode.VERTICAL)
			{
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.LINE_AXIS));
				int totalInterval= 0;
				int k = children.size();
				boolean adjustmentNeeded = false;
				List <KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode.getChildren();
				
				//calculate the width of the parent, i.e. the avaialble share of real estate
				//width for all the children
				float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
				//Vector<Double> childPercentage = new Vector<Double>();
							
				Vector<Float> childWidth = new Vector<Float>();
				Vector<Float> childWidthPercentage = new Vector<Float>();
				//Vector<Float> childHeightPercentage = new Vector<Float>();
				
				for (int i = 0; i < kblChildren.size(); i++)
				{
					KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
					float tmpChildWidth = kblChild.getRequiredWidth()/MAX_PERCENTAGE * SCREEN_WIDTH;
					childWidth.add(tmpChildWidth);
					childWidthPercentage.add((tmpChildWidth/parentWidth)* MAX_PERCENTAGE );
					
				}
				
				double percentageSum = 0.0;
				//Sum all the percentage width values to check if the total percentage is greater than 100% or less, or 
				//equal
				for (int i = 0; i < childWidthPercentage.size(); i++)
				{
					percentageSum += childWidthPercentage.get(i);
				
				}
				//calculate the percentage difference
				double percentageDiff = (MAX_PERCENTAGE-percentageSum);
				Vector<Double> childDiff = new Vector<Double>();
				double sumDiff = 0.0;
				if(percentageDiff > 0)
				{
					sumDiff = 0.0;
					for (int i = 0; i < childWidthPercentage.size(); i++)
					{
						double idealPercentage = 1.0/k;
						if( (childWidthPercentage.get(i)  ) < idealPercentage*MAX_PERCENTAGE)
						{
							double diff  = idealPercentage*MAX_PERCENTAGE  -  childWidthPercentage.get(i) ;
							sumDiff += diff;
							childDiff.add(diff);
						}
						else
						{
							childDiff.add(0.0);
						}
					}
					for (int i = 0; i < childDiff.size(); i++)
					{
						double tmp = childDiff.get(i) / sumDiff ;
						childDiff.set(i,tmp);
					}
					
				}
				else
				{
					for (int i = 0; i < childWidthPercentage.size(); i++)
					{
						childDiff.add(0.0);
					}
				}
				
				//All the above lines of code just calculated the percentage of width which each child should be given, 
				//in order to make sure that each button  would be visible on screen, and no button would have width
				// smaller than Min-width. 
				for (int i = 0; i < childWidthPercentage.size(); i++)
				{
					double tmp = ( childWidthPercentage.get(i) + ( Math.abs(percentageDiff) * childDiff.get(i) )) / MAX_PERCENTAGE;
					childWidthPercentage.set(i, (float)tmp);
				}
								//All the children are assigned the percentages of the width 
				for (int i = 0; i < kblChildren.size(); i++)
				{
					KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
					
					double nodeSizePercentage = 0.0;
					nodeSizePercentage = childWidthPercentage.get(i);
					
						System.out.println("Width: calc : " + nodeSizePercentage*parentWidth + " requ: "  +(kblChild.getRequiredWidth()/100)*SCREEN_WIDTH);
						System.out.println(" At" + kblChild.getNode().toString());
					
					
										
					//KBLNode<JVirtualKeyboardButton> newChildNode = new KBLNode<JVirtualKeyboardButton>(child); 
					parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
					float interval = 0;
					interval = Math.round(parentWidth*  nodeSizePercentage);
					totalInterval += interval;
					if(i == children.size() -1)
					{
						float remainder = parentWidth - totalInterval;
						interval += remainder;
					}
					//New child node is created and dimensions are assigned to it accordingly, and the 
					//divide type is set. 
					kblChild.setDivideType(KBLNode.HORIZONTAL);
					kblChild.setStartX(i* interval);
					kblChild.setEndX((i+1)* interval);
					kblChild.setStartY(parentLayoutNode.getStartY());
					kblChild.setEndY(parentLayoutNode.getEndY());
					
					JPanel subBoxContainer = CHToKBL (kblChild);
					boxContainer.add(subBoxContainer);
					boxContainer.setAlignmentX(JPanel.LEFT_ALIGNMENT);
				}
			}
			else
			{
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.PAGE_AXIS));
				
				int totalInterval= 0;
				int k = children.size();
				boolean adjustmentNeeded = false;
				List <KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode.getChildren();
				
				//calculate the width of the parent, i.e. the avaialble share of real estate
				//width for all the children
				float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
				//Vector<Double> childPercentage = new Vector<Double>();
							
				//Vector<Float> childWidthPercentage = new Vector<Float>();
				Vector<Float> childHeightPercentage = new Vector<Float>();
				Vector<Float> childHeight = new Vector<Float>();
				
				for (int i = 0; i < kblChildren.size(); i++)
				{
					KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
					float tmpChildHeight = kblChild.getRequiredHeight()/MAX_PERCENTAGE * SCREEN_HEIGHT;
					childHeight.add(tmpChildHeight);
					childHeightPercentage.add((tmpChildHeight/parentHeight) * MAX_PERCENTAGE);
					//childHeightPercentage.add(kblChild.getRequiredWidth());
				}
				
				double percentageSum = 0.0;
				//Sum all the percentage width values to check if the total percentage is greater than 100% or less, or 
				//equal
				for (int i = 0; i < childHeightPercentage.size(); i++)
				{
					percentageSum += childHeightPercentage.get(i);
				
				}
				//calculate the percentage difference
				double percentageDiff = (MAX_PERCENTAGE-percentageSum);
				Vector<Double> childDiff = new Vector<Double>();
				double sumDiff = 0.0;
				if(percentageDiff > 0)
				{
					sumDiff = 0.0;
					for (int i = 0; i < childHeightPercentage.size(); i++)
					{
						double idealPercentage = 1.0/k;
						if( (childHeightPercentage.get(i) ) < idealPercentage*MAX_PERCENTAGE)
						{
							double diff  = idealPercentage*MAX_PERCENTAGE  -  childHeightPercentage.get(i) ;
							sumDiff += diff;
							childDiff.add(diff);
						}
						else
						{
							childDiff.add(0.0);
						}
					}
					for (int i = 0; i < childDiff.size(); i++)
					{
						double tmp = childDiff.get(i) / sumDiff ;
						childDiff.set(i,tmp);
					}
					
				}
				else
				{
					for (int i = 0; i < childHeightPercentage.size(); i++)
					{
						childDiff.add(0.0);
					}
				}
				
				//All the above lines of code just calculated the percentage of width which each child should be given, 
				//in order to make sure that each button  would be visible on screen, and no button would have width
				// smaller than Min-width. 
				for (int i = 0; i < childHeightPercentage.size(); i++)
				{
					double tmp = ( childHeightPercentage.get(i) + ( Math.abs(percentageDiff) * childDiff.get(i) )) / MAX_PERCENTAGE;
					childHeightPercentage.set(i, (float)tmp);
				}
				//All the children are assigned the percentages of the width 
				for (int i = 0; i < kblChildren.size(); i++)
				{
					KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
					
					double nodeSizePercentage = 0.0;
					nodeSizePercentage = childHeightPercentage.get(i);
					
						System.out.println("Height: calc : " + nodeSizePercentage*parentHeight + " requ: "  +(kblChild.getRequiredHeight()/100)*SCREEN_HEIGHT);
						System.out.println(" At" + kblChild.getNode().toString());
					
				
					
					//KBLNode<JVirtualKeyboardButton> newChildNode = new KBLNode<JVirtualKeyboardButton>(child); 
					parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
					float interval = 0;
					interval = Math.round(parentHeight*  nodeSizePercentage);
					totalInterval += interval;
					if(i == children.size() -1)
					{
						float remainder = parentHeight - totalInterval;
						interval += remainder;
					}
					//New child node is created and dimensions are assigned to it accordingly, and the 
					//divide type is set. 
					kblChild.setDivideType(KBLNode.VERTICAL);
					kblChild.setStartY(i* interval);
					kblChild.setEndY((i+1)* interval);
					kblChild.setStartX(parentLayoutNode.getStartX());
					kblChild.setEndX(parentLayoutNode.getEndX());
					JPanel subBoxContainer = CHToKBL (kblChild);
					
					boxContainer.add(subBoxContainer);
					
					
				}
			}
			
			return boxContainer;
		}
			
	}
		

	
	
	
	/*	
	public JPanel hierarchyToLayout (KBLNode parentLayoutNode )
	{
		ContainmentHierarchyNode parentNode = parentLayoutNode.getNode();
		List <ContainmentHierarchyNode> children = parentNode.getChildren();
		
		JPanel boxContainer = new JPanel();
		
		if(children.get(0).isLeaf() )
		{
			if (parentLayoutNode.getDivide() == KBLNode.VERTICAL)
			{
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.PAGE_AXIS));
				//since parent was divided vertically, these nodes have to be placed
				//horizontaly
				
				float parentWidth = ((parentLayoutNode.getEndX()- parentLayoutNode.getStartX())* width);
				float parentHeight =((parentLayoutNode.getEndY()- parentLayoutNode.getStartY())* height);
				
				int buttonWidth = (int) parentWidth ;
				int buttonHeight = (int)( parentHeight / (float)k);
				
				for (int i = 0; i < children.size(); i++)
				{
					ContainmentHierarchyNode child = children.get(i);
					
					JButton button = ((LeafContainmentHierarchyNode)child).getRepresentative();
					button.setMinimumSize(new Dimension(buttonWidth, buttonHeight)); 
					button.setPreferredSize(new Dimension(buttonWidth, buttonHeight)); 
					button.setMaximumSize(new Dimension(Short.MAX_VALUE, 
					                                  Short.MAX_VALUE));
					
					boxContainer.add(button);
					
				}
			}
			else
			{
					boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.LINE_AXIS));
					//since parent was divided horizontally, these nodes have to be placed vertaically
					
					float parentWidth = ((parentLayoutNode.getEndX()- parentLayoutNode.getStartX())* width);
					float parentHeight =((parentLayoutNode.getEndY()- parentLayoutNode.getStartY())* height);
					
					int buttonWidth = (int) (parentWidth / (float)k);
					int buttonHeight = (int) parentHeight;
					
					for (int i = 0; i < children.size(); i++)
					{
						ContainmentHierarchyNode child = children.get(i);
						
						JButton button = ((LeafContainmentHierarchyNode)child).getRepresentative();
						button.setMinimumSize(new Dimension(buttonWidth, buttonHeight)); 
						button.setPreferredSize(new Dimension(buttonWidth, buttonHeight)); 
						button.setMaximumSize(new Dimension(Short.MAX_VALUE, 
						                                  Short.MAX_VALUE));
						
						boxContainer.add(button);
						
					}
				}
			
		
			return boxContainer;
		}
		else
		{
			if (parentLayoutNode.getDivide() == KBLNode.VERTICAL)
			{
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.LINE_AXIS));
				//if (parentLayoutNode.getDivide() == KBLNode.VERTICAL)
				for (int i = 0; i < children.size(); i++)
				{
					ContainmentHierarchyNode child = children.get(i);
					KBLNode newChildNode = new KBLNode(child); 
								
					newChildNode.setDivide(KBLNode.HORIZONTAL);
					float buttonWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
					float interval = (buttonWidth/(float)k);
					newChildNode.setStartX(i* interval);
					newChildNode.setEndX((i+1)* interval);
					newChildNode.setStartY(parentLayoutNode.getStartY());
					newChildNode.setEndY(parentLayoutNode.getEndY());
					
					JPanel subBoxContainer = hierarchyToLayout (newChildNode);
					
					boxContainer.add(subBoxContainer);
					
				}
			}
			else
			{
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.PAGE_AXIS));
				for (int i = 0; i < children.size(); i++)
				{
					ContainmentHierarchyNode child = children.get(i);
					KBLNode newChildNode = new KBLNode(child); 
				
					newChildNode.setDivide(KBLNode.VERTICAL);
					float buttonHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
					float interval = (buttonHeight/(float)k);
					newChildNode.setStartY(i* interval);
					newChildNode.setEndY((i+1)* interval);
										
					newChildNode.setStartX(parentLayoutNode.getStartX());
					newChildNode.setEndX(parentLayoutNode.getEndX());
					
					
					JPanel subBoxContainer = hierarchyToLayout (newChildNode);
					
					boxContainer.add(subBoxContainer);
					
					
				}
			}
			
			return boxContainer;
		}
			
	}
*/		

	/*
	 * (non-Javadoc)
	 * 
	 * @see containmentHierarchyVariants.KeyboardLayout#getAllButtons()
	 */
	public List<JIndirectSelectionButton> getAllEnabledButtons() {
		List<JIndirectSelectionButton> allButtons = new Vector<JIndirectSelectionButton>();
		
		for (JIndirectSelectionButton but : keyList.getSourceSymbolsByRankOrder()) 
		{
			allButtons.add( but);
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


