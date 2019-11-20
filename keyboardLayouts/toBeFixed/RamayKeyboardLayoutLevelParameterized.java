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


public class RamayKeyboardLayoutLevelParameterized extends ButtonLayoutSpecification 
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
		
	private static final int SCREEN_WIDTH = 1024;
	private static final int SCREEN_HEIGHT = 512; //VoiceOutputCommunicationAidApplication.frame.getHeight();
	
	private static final int MIN_BUTTON_HEIGHT = 28;
	private static final int MIN_BUTTON_WIDTH = 16;
	
	private static final String FONT_TYPE = "sanserif";
	
	
	public RamayKeyboardLayoutLevelParameterized(MyTraversableEncodingTree<JIndirectSelectionButton> root, SourceSymbolSet selectableKeyList) 
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
		
		JPanel keyboard = CHToKBL (rootNode);
		onScreenButtonRows = new Vector<JPanel>();
		onScreenButtonRows.add(keyboard);
		
		
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
				
				//calculate the width of the parent, i.e. the avaialble share of real estate
				//width for all the children
				float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
				Vector<Double> childPercentage = new Vector<Double>();
				
				//Caclulate the maximum leaf level of each child 
				for (int i = 0; i < children.size(); i++)
				{
					Node<JIndirectSelectionButton> child = children.get(i);
					int maxLevel = child.calculateMaximumLeafLevel();
					
					float minChildWidth = 0;
					if(maxLevel != -1)
					{
						//maxLevel +=1;
						//calculate the minimum width needed by each child, in order to make sure that each leaf node 
						//at the maxLevel, gets the required MIN_BUTTON_WIDTH.  
						minChildWidth = MIN_BUTTON_WIDTH * (float)Math.pow(k, Math.ceil((double)maxLevel/k));
						
					}
					else
					{
						minChildWidth = MIN_BUTTON_WIDTH ;
					}
					//store all the percentage width values calculated for the children for future use. 
					childPercentage.add( (double)minChildWidth/parentWidth);
				}
				double percentageSum = 0.0;
				//Sum all the percentage width values to check if the total percentage is greater than 100% or less, or 
				//equal
				for (int i = 0; i < childPercentage.size(); i++)
				{
					percentageSum += Math.ceil(childPercentage.get(i) *100 );
				
				}
				//calculate the percentage difference
				double percentageDiff = (100-percentageSum);
				Vector<Double> childDiff = new Vector<Double>();
				
				double sumDiff = 0.0;
				// if percentage difference is > 0, it means if all the child nodes are assigned the min-width that they
				//need, then some of the real estate share of the parent width would still be left, and it needs to be 
				//divided among the children. 
				if(percentageDiff > 0)
				{
					sumDiff = 0.0;
					for (int i = 0; i < childPercentage.size(); i++)
					{
						//This is the ideal percentage of parent width that each child should be assigned,  
						//if there were no limitations i.e. the child nodes do not become very small, 
						//and hard to read in the deepest levels.  
						double idealPercentage = 1.0/k;
						
						//If the child has less than its ideal share, than this child would be later given an extra 
						//share of percentage afterwards, from the excessive percentage i.e. percentage difference
						
						if( (childPercentage.get(i) *100) < idealPercentage*100)
						{
							double diff  = idealPercentage*100  -  (childPercentage.get(i) *100) ;
							sumDiff += diff;
							childDiff.add(diff);
						}
						else
						{
							childDiff.add(0.0);
						}
					}
					//We calcualte what is the percentage of the excessive percentage, that each child with less than ideal 
					// share should be given. 
					for (int i = 0; i < childDiff.size(); i++)
					{
						double tmp = childDiff.get(i) / sumDiff ;
						childDiff.set(i,tmp);
					}
					
				}
//				 if percentage difference is < 0, it means that if all the child nodes are assigned the min-width that they
				//need, then the sum of thier percentages would become greater than the real estate share of the parent width 
				//that is available, and hence it is not possible- so the percentages needed to be altered to fit within 
				//the actual parent width
				
				else if(percentageDiff < 0)
				{
					sumDiff = 0.0;
					for (int i = 0; i < childPercentage.size(); i++)
					{
						double idealPercentage = 1.0/k;
						
						//we calculate the ideal percentage for this child, according to 
						// the original algorithm - this ideal percentage however would result in smaller button sizes 
						// for the buttons present at leaf nodes. 
						if( (childPercentage.get(i) *100) > idealPercentage*100)
						{
							double diff  = idealPercentage*100  -  (childPercentage.get(i) *100) ;
							sumDiff += Math.abs(diff);
							childDiff.add(diff);
						}
						else
						{
							childDiff.add(0.0);
						}
					}
					//We calcualte what is the percentage that should be subtracted from each child's width 
					// percentage to make sure the total width percentage remains == 100, i.e the child width 
					//sum up tp the width of the parent, and is not more that it. 
								
					for (int i = 0; i < childDiff.size(); i++)
					{
						double tmp = childDiff.get(i) / sumDiff ;
						childDiff.set(i,tmp);
					}
					
				}
				else
				{
					for (int i = 0; i < childPercentage.size(); i++)
					{
						childDiff.add(0.0);
					}
				}
				
				//All the above lines of code just calculated the percentage of width which each child should be given, 
				//in order to make sure that each button  would be visible on screen, and no button would have width
				// smaller than Min-width. 
				for (int i = 0; i < childPercentage.size(); i++)
				{
					double tmp = (( childPercentage.get(i) *100 )+ ( Math.abs(percentageDiff) * childDiff.get(i) )) / 100;
					childPercentage.set(i, tmp);
				}
				int tmpSum = 0;
				
				//We calculate here if the child actually needs adjustment in width percentage or not 
				//may be this step is redundant... ? needs to be looked into
				for (int i = 0; i < childPercentage.size(); i++)
				{
					double tmp1 = Math.round ((childPercentage.get(i) * 100));
					double tmp2 = Math.round ((1.0/k) * 100) ;
					if( Math.abs(tmp1 - tmp2) <= pixelMargin)
					//if (Math.round(childPercentage.get(i)* 100) == (buttonWidth/k));
					{
						tmpSum++;
					}
				}
				

				if(tmpSum < k)
				{
					adjustmentNeeded = true;
				}
				
				//All the children are assigned the percentages of the width 
				for (int i = 0; i < children.size(); i++)
				{
					Node<JIndirectSelectionButton> child = children.get(i);
					
					double nodeSizePercentage = 0.0;
					if(adjustmentNeeded)
					{
						nodeSizePercentage = childPercentage.get(i);
						
					}
					else
					{
						nodeSizePercentage = 1.0/(double)k;
						
					}
										
					KBLNode<JIndirectSelectionButton> newChildNode = new KBLNode<JIndirectSelectionButton>(child); 
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
					newChildNode.setDivideType(KBLNode.HORIZONTAL);
					newChildNode.setStartX(i* interval);
					newChildNode.setEndX((i+1)* interval);
					newChildNode.setStartY(parentLayoutNode.getStartY());
					newChildNode.setEndY(parentLayoutNode.getEndY());
					
					JPanel subBoxContainer = CHToKBL (newChildNode);
					boxContainer.add(subBoxContainer);
					boxContainer.setAlignmentX(JPanel.LEFT_ALIGNMENT);
				}
			}
			else
			{
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.PAGE_AXIS));
				
				int totalInterval = 0;
				int k = children.size();
				boolean adjustmentNeeded = false;
				//calculate the height of the parent, i.e. the available share of real estate
				//height for all the children of this parent node.
				float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
				
				Vector<Double> childPercentage = new Vector<Double>();
				
				for (int i = 0; i < children.size(); i++)
				{
					Node<JIndirectSelectionButton> child = children.get(i);
					
					int maxLevel = child.calculateMaximumLeafLevel();					
					
					float minChildHeight = 0;
					
					if(maxLevel != -1)
					{
						//calculate the minimum height needed by each child in order to make sure that each leaf node 
						//at the maxLevel, gets the required MIN_BUTTON_Height   
						minChildHeight = MIN_BUTTON_HEIGHT * (float)Math.pow(k, Math.ceil((double)maxLevel/k));
						minChildHeight = minChildHeight / 2;
					}
					else
					{
						minChildHeight = MIN_BUTTON_HEIGHT ;
					}
					//
					//store all the percentage height values calculated for the children for future use. 
					childPercentage.add( (double)minChildHeight/parentHeight);
				}
				double percentageSum = 0.0;
				
				//Sum all the percentage height values to check if the total percentage 
				//is greater than 100% or less, or equal
				for (int i = 0; i < childPercentage.size(); i++)
				{
					percentageSum += Math.ceil(childPercentage.get(i) *100 );
				
				}
//				calculate the percentage difference
				double percentageDiff = (100-percentageSum);
				Vector<Double> childDiff = new Vector<Double>();
				
				double sumDiff = 0.0;
				// if percentage difference is > 0, it means that if all the child nodes are assigned the min-height that they
				//need, then some of the real estate share of the parent height would still be left, and it needs to be 
				//divided among the children. 

				if(percentageDiff > 0)
				{
					for (int i = 0; i < childPercentage.size(); i++)
					{
						////This is the ideal percentage of parent height that each child should be assigned according to the 
						//original algorithm,   
						//if there were no limitations i.e. the child nodes do not become very small, 
						//and hard to read in the deepest levels.  

						double idealPercentage = 1.0/k;
						
//						If the child has less than its ideal share, than this child would be later given an extra 
						//share of percentage from the excessive percentage i.e. percentage difference
						
						if( (childPercentage.get(i) *100) < idealPercentage*100)
						{
							double diff  = idealPercentage*100  -  (childPercentage.get(i) *100) ;
							sumDiff += diff;
							childDiff.add(diff);
						}
						else
						{
							childDiff.add(0.0);
						}
					}
					//We calcualte what is the percentage of the excessive percentage, that each child with less than ideal 
					// share of height should be given. 

					for (int i = 0; i < childDiff.size(); i++)
					{
						double tmp = childDiff.get(i) / sumDiff ;
						childDiff.set(i,tmp);
					}
					
				}
//				 if percentage difference is < 0, it means that if all the child nodes are assigned the min-height that they
				//need, then the sum of their percentages would become greater than the real estate share of the parent height 
				//that is available, and hence it is not possible - so the percentages needed to be altered to fit within 
				//the actual parental height. 

				else if(percentageDiff < 0)
				{
					for (int i = 0; i < childPercentage.size(); i++)
					{
						double idealPercentage = 1.0/k;
						//we calculate the ideal percentage for this child, according to 
						// the original algorithm - this ideal percentage however would result in smaller button sizes 
						// for the buttons present at leaf nodes. 

						if( (childPercentage.get(i) *100) > idealPercentage*100)
						{
							double diff  = idealPercentage*100  -  (childPercentage.get(i) *100) ;
							sumDiff += Math.abs(diff);
							childDiff.add(diff);
						}
						else
						{
							childDiff.add(0.0);
						}
					}
					//We calcualte what is the percentage that should be subtracted from each child's height 
					// percentage to make sure the total height percentage remains == 100, i.e the all children's height 
					//sum up to the width of the parent, and is not more than it. 

					for (int i = 0; i < childDiff.size(); i++)
					{
						double tmp = childDiff.get(i) / sumDiff ;
						childDiff.set(i,tmp);
					}
					
				}
				else
				{
					for (int i = 0; i < childPercentage.size(); i++)
					{
						childDiff.add(0.0);
					}
				}
				//All the above lines of code just calculated the percentage of height which each child should be given, 
				//in order to make sure that each button  would be visible on screen, and no button would have height
				// smaller than Min-width. 
					for (int i = 0; i < childPercentage.size(); i++)
				{
					double tmp = (( childPercentage.get(i) *100 )+ (Math.abs(percentageDiff) * childDiff.get(i) )) / 100;
					childPercentage.set(i, tmp);
				}
//					We calculate here if the child actually needs adjustment in height percentage or not 
					//may be this step is redundant... ? needs to be looked into

				int tmpSum = 0;
				for (int i = 0; i < childPercentage.size(); i++)
				{
					double tmp1 = Math.round( childPercentage.get(i) * 100);
					double tmp2 = Math.round(1.0/k * 100);
					if(Math.abs(tmp1 - tmp2) <= pixelMargin)
					
					//if (Math.round(childPercentage.get(i)* 100) == (buttonHeight/k))
					{
						tmpSum++;
					}
				}
				

				if(tmpSum < k)
				{
					adjustmentNeeded = true;
				}
				

				//All the children are assigned the percentages of height
				for (int i = 0; i < children.size(); i++)
				{
					Node<JIndirectSelectionButton> child = children.get(i);
					KBLNode<JIndirectSelectionButton> newChildNode = new KBLNode<JIndirectSelectionButton>(child); 
				
					double nodeSizePercentage = 0.0;
					if(adjustmentNeeded)
					{
						nodeSizePercentage = childPercentage.get(i);
						
					}
					else
					{
						nodeSizePercentage = 1.0/(double)k;
						
					}

					
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
				
					newChildNode.setStartY(i* interval);
					newChildNode.setEndY((i+1)* interval);
					newChildNode.setDivideType(KBLNode.VERTICAL);					
					newChildNode.setStartX(parentLayoutNode.getStartX());
					newChildNode.setEndX(parentLayoutNode.getEndX());
					
					JPanel subBoxContainer = CHToKBL (newChildNode);
					
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


