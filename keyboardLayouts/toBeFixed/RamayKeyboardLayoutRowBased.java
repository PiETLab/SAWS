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
import TreeDataStructure.Node;
import TreeDataStructure.LeafNode; 
import TreeDataStructure.InternalNode;


import java.awt.GridBagConstraints;

import analysisUserStudy.Graph;

/**
 * This class implements the algorithm to generate the keyboard layout automatically, 
 * based on the containment hierarchy which is passed as an input.  
 * The layout of the keyboard is generated automatically, and the main goal is to 
 * eliminate the painfull process of creating the keyboard layout manually. 
 * 
 * 
 * @author Fatima Ramay
 */


public class RamayKeyboardLayoutRowBased extends ButtonLayoutSpecification 
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
	
	private static final int TECHNIQUE1 = 1;
	private static final int TECHNIQUE2 = 2;
	private static final int TECHNIQUE3 = 3;
	private List <Float> widthPercentageByLevel;
	private List <Float> heightPercentageByLevel;
	List <Integer> numOfNodesAtDepth ;
	List <JIndirectSelectionButton> allButtons ;
	
	private int technique;
	//private static final int W = 1;
//	private static final int H = 2;
	
	private static final String FONT_TYPE = "sanserif";
	
	
	public RamayKeyboardLayoutRowBased(MyTraversableEncodingTree<JIndirectSelectionButton> root, SourceSymbolSet selectableKeyList) 
	{
		keyList = selectableKeyList;
				
		KBLNode<JIndirectSelectionButton> rootNode = new KBLNode<JIndirectSelectionButton>(root.getRoot());
		rootNode.setStartX(0);
		rootNode.setStartY(0);
		rootNode.setEndX(1*SCREEN_WIDTH);
		rootNode.setEndY(1*SCREEN_HEIGHT);
		rootNode.setDivideType(KBLNode.HORIZONTAL);
		
		//InternalNode<JVirtualKeyboardButton> temp =  (InternalNode)root.getRoot();
		//int maxDepth = temp.calculateMaximumLeafDepth(0);
		//int maxLevel = temp.calculateMaximumLeafLevel();
		//System.out.println("The depth............" +  maxDepth);
		//System.out.println("The level............" +  maxLevel);
		
		//System.out.println("Screen width" + TextCompositionFacilityFrame.SCREEN_WIDTH); 
		//System.out.println("Screen height" + TextCompositionFacilityFrame.SCREEN_HEIGHT);
		
		technique = TECHNIQUE1;
		widthPercentageByLevel = new Vector<Float>();
		heightPercentageByLevel = new Vector<Float>();
		numOfNodesAtDepth = new Vector<Integer>();
		allButtons = new Vector <JIndirectSelectionButton> () ;
		//if(technique == TECHNIQUE1)
		
		KBLNode<JIndirectSelectionButton> tmpRoot = new KBLNode<JIndirectSelectionButton>(root.getRoot());
		
		if(technique == TECHNIQUE1)
		{
			tmpRoot = preCalculationRowBased1 (rootNode);
		}else if(technique == TECHNIQUE2)
		{
			tmpRoot = preCalculationRowBased2 (rootNode);
		}
		else if(technique == TECHNIQUE3)
		{
			tmpRoot = preCalculationRowBased3 (rootNode);
		}
		
		//KBLNode<JVirtualKeyboardButton> tmpRoot = preCalculation3 (rootNode);
		
		
		
		//KBLNode<JVirtualKeyboardButton> tmpRoot = preCalculation (rootNode);
		
		//JPanel keyboard = CHToKBLRowBased (rootNode,technique);
		JPanel keyboard = CHToKBLRowBased (tmpRoot, technique);
		onScreenButtonRows = new Vector<JPanel>();
		onScreenButtonRows.add(keyboard);
		
		Graph.constructGraph(allButtons, "RowBased_Technique3_Aug29.txt");
		
	}
	
	public KBLNode<JIndirectSelectionButton> preCalculationRowBased1 (KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		parentLayoutNode.setAvaialbleWidth(MAX_PERCENTAGE);
		parentLayoutNode.setAvaialbleHeight(MAX_PERCENTAGE);
				
		return preKBLCalculationRowBased1(parentLayoutNode);
			
		
		
	}
	
	
	public KBLNode<JIndirectSelectionButton> preCalculationRowBased2 (KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		int k = children.size();
		//float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
		//float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
		parentLayoutNode.setAvaialbleWidth(MAX_PERCENTAGE);
		parentLayoutNode.setAvaialbleHeight(MAX_PERCENTAGE);
		
		int maxLevel = parentNode.calculateMaximumLeafLevel();
		
		float level = (float)Math.log(SCREEN_HEIGHT/MIN_BUTTON_HEIGHT)/(float)Math.log(k);
		level = (float)Math.ceil((double)level* (double)k);
		
		if(maxLevel != -1  )
		{
			if(level >= maxLevel)
			{
				return preKBLCalculationRowBased1(parentLayoutNode);
			}
			else 
			{
				maxLevel = (int)level;
				return preKBLCalculationRowBased2(parentLayoutNode, 0, maxLevel);
				/*
				List<Node<JVirtualKeyboardButton>> nodes = ((InternalNode<JVirtualKeyboardButton>)parentNode).getNodesAtKBLLevel((int)level);
				List <Integer> numOfAssociatedNodes = new Vector<Integer>(); 
				for(int i = 0; i < nodes.size(); i++)
				{
					numOfAssociatedNodes.add(nodes.get(i).getSelectionGroupNodeSize());
					
				}
				
				int max = ConstructCodeTree.getMax(numOfAssociatedNodes);
				if(SCREEN_WIDTH/max >= MIN_BUTTON_WIDTH)
				{
					return preKBLCalculationRowBased1(parentLayoutNode);
				}
				else
				{
					maxLevel = (int)level;
				//}
					return preKBLCalculationRowBased2(parentLayoutNode, 0, maxLevel);
				}*/
				
			}
			
			
		}
		return preKBLCalculationRowBased1(parentLayoutNode);
	}
	
	public KBLNode<JIndirectSelectionButton> preCalculationRowBased3 (KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		//Here level means the depth, and two child nodes can be at different depths, 
		//and each edge is not calculated having depth 1 only. 
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		int k = children.size();
		float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
		float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
		//int maxLevel = parentNode.calculateMaximumLeafLevel();
		
		parentLayoutNode.setAvaialbleWidth(MAX_PERCENTAGE);
		parentLayoutNode.setAvaialbleHeight(MAX_PERCENTAGE);
		
		int maxDepth = ((InternalNode<JIndirectSelectionButton>)parentNode).calculateMaximumLeafDepth(0);
		//This is a list that determines the size of the button by each level. 
		for(int i = 0; i <= maxDepth; i++)
		{
			int num = (((InternalNode<JIndirectSelectionButton>)parentNode).getleavesAtLevel(i)).size();
			numOfNodesAtDepth.add(num);
		}
		for(int i = 0; i < numOfNodesAtDepth.size(); i++)
		{
			widthPercentageByLevel.add((float)0);
			heightPercentageByLevel.add((float)0);
		
		}

		float scalingFactor = (float)k;
		
		float minWidthPercentage = (MIN_BUTTON_WIDTH/SCREEN_WIDTH)* MAX_PERCENTAGE;
		float minHeightPercentage = (MIN_BUTTON_HEIGHT/SCREEN_HEIGHT)* MAX_PERCENTAGE;
		
		int count = numOfNodesAtDepth.size();
		for(int i = 0; i <numOfNodesAtDepth.size(); i++ )
		{
			widthPercentageByLevel.set(count-1,minWidthPercentage);
			heightPercentageByLevel.set(count-1,minHeightPercentage);
			minWidthPercentage = minWidthPercentage * scalingFactor;
			minHeightPercentage = minHeightPercentage * scalingFactor;
			count--;
		}
		
		return preKBLCalculationRowBased3(parentLayoutNode,numOfNodesAtDepth ,widthPercentageByLevel, heightPercentageByLevel);
	}
		
	public KBLNode<JIndirectSelectionButton> preKBLCalculationRowBased3 (KBLNode<JIndirectSelectionButton> parentLayoutNode, List <Integer> numOfNodesAtDepth , List <Float> widthPercentageByLevel, List <Float> heightPercentageByLevel)
	{
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		
		if(parentNode.isLeaf() )
		{
			int depth = parentNode.getEdgeCostToHere();
			
			float tmpW = (float)getInitialDimPercentage(depth,KBLNode.WIDTH);
			float tmpH = (float)getInitialDimPercentage(depth,KBLNode.HEIGHT);
			
			parentLayoutNode.setRequiredWidth(tmpW);
			parentLayoutNode.setRequiredHeight(tmpH);
				
		}
		else
		{
			if (parentLayoutNode.getDivideType() == KBLNode.VERTICAL)
			{
				float maxHeight = 0;
				float prevHeight = 0;
				//All the children are assigned the avalaible percentages of the width 
				for (int i = 0; i < children.size(); i++)
				{
					Node<JIndirectSelectionButton> child = children.get(i);
					KBLNode<JIndirectSelectionButton> newChildNode = new KBLNode<JIndirectSelectionButton>(child); 
					newChildNode.setDivideType(KBLNode.VERTICAL);
					KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculationRowBased3(newChildNode,numOfNodesAtDepth ,widthPercentageByLevel, heightPercentageByLevel);
					if(i == 0)
					{
						parentLayoutNode.setRequiredWidth(tmpChildNode.getRequiredWidth());
						maxHeight = tmpChildNode.getRequiredHeight();
					}
					else
					{
						float tmp = parentLayoutNode.getRequiredWidth();
						parentLayoutNode.setRequiredWidth(tmp + tmpChildNode.getRequiredWidth());
						maxHeight = tmpChildNode.getRequiredHeight();
					}
					if(maxHeight < prevHeight)
					{
						maxHeight = prevHeight;
					}
					prevHeight = maxHeight;
					//maxHeight = tmpChildNode.getRequiredHeight();
					parentLayoutNode.setRequiredHeight(maxHeight);
					parentLayoutNode.addChild(tmpChildNode);
					
				}
			}
			else
			{
				float maxWidth = 0;
				//All the children are assigned the avalaible percentages of the width 
				for (int i = 0; i < children.size(); i++)
				{
					Node<JIndirectSelectionButton> child = children.get(i);
					KBLNode<JIndirectSelectionButton> newChildNode = new KBLNode<JIndirectSelectionButton>(child); 
					newChildNode.setDivideType(KBLNode.HORIZONTAL);
					
					//float childHeight = kblChild.getEndY()- kblChild.getStartY();
					//float childWidth = kblChild.getEndX()- kblChild.getStartX();
									
					KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculationRowBased3(newChildNode,numOfNodesAtDepth ,widthPercentageByLevel, heightPercentageByLevel);
					
					int associatedNodeSize = ((child.getSelectionGroup()).extractMembers()).size();
					float prevWidth = 0;
					float childHeight = tmpChildNode.getRequiredHeight();
					//float childWidth = tmpChildNode.getRequiredWidth();
					float minButtonPercentage = (MIN_BUTTON_HEIGHT/SCREEN_HEIGHT) * MAX_PERCENTAGE; 				
					float associatedNodePercentage = (SCREEN_WIDTH/(float)associatedNodeSize );
					
					if( childHeight < minButtonPercentage || associatedNodePercentage >= MIN_BUTTON_WIDTH)
					{
						newChildNode = new KBLNode<JIndirectSelectionButton>(child); 
						newChildNode.setDivideType(KBLNode.VERTICAL);
						tmpChildNode = preKBLCalculationRowBased3(newChildNode,numOfNodesAtDepth ,widthPercentageByLevel, heightPercentageByLevel);
					}
					
					if(i == 0)
					{
						parentLayoutNode.setRequiredHeight(tmpChildNode.getRequiredHeight());
						maxWidth = tmpChildNode.getRequiredWidth();
					}
					else
					{
						float tmp = parentLayoutNode.getRequiredHeight();
						parentLayoutNode.setRequiredHeight(tmp + tmpChildNode.getRequiredHeight());
						maxWidth = tmpChildNode.getRequiredWidth();
					}
					if(maxWidth < prevWidth)
					{
						maxWidth = prevWidth;
					}
					parentLayoutNode.setRequiredHeight(maxWidth);
					prevWidth = maxWidth;
					parentLayoutNode.addChild(tmpChildNode);
					
				}
				
			}
		}
		return parentLayoutNode;
	}

	private float getInitialDimPercentage(int depth, int flag)
	{
		if(depth < numOfNodesAtDepth.size())
		{
			if(flag == KBLNode.WIDTH)
			{
				return widthPercentageByLevel.get(depth);
			}
			else if(flag == KBLNode.HEIGHT)
			{
				return heightPercentageByLevel.get(depth);
			}
			
		}
		else
		{
			System.out.println("Wrong depth");
			
		}
		return -1;
	}
	/*
	public KBLNode<JVirtualKeyboardButton> preKBLCalculation (KBLNode<JVirtualKeyboardButton> parentLayoutNode, int level, int maxLevel)
	{
		Node<JVirtualKeyboardButton> parentNode = parentLayoutNode.getNode();
		List <Node<JVirtualKeyboardButton>> children = parentNode.getChildren();
		
		if(parentNode.isLeaf() )
		{
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
					Node<JVirtualKeyboardButton> child = children.get(i);
					
					double childPercentage = 1.0/(double)k;
						
					KBLNode<JVirtualKeyboardButton> newChildNode = new KBLNode<JVirtualKeyboardButton>(child); 
					
					if(parentLayoutNode.getAvaialbleWidth() != -1)
					{
						float childWidth = (float)childPercentage * parentLayoutNode.getAvaialbleWidth();
					
						newChildNode.setDivideType(KBLNode.HORIZONTAL);
						newChildNode.setAvaialbleWidth(childWidth);
						newChildNode.setAvaialbleHeight(parentLayoutNode.getAvaialbleHeight());
						//level = level +1;
						KBLNode<JVirtualKeyboardButton> tmpChildNode = preKBLCalculation(newChildNode,level, maxLevel);
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
					Node<JVirtualKeyboardButton> child = children.get(i);
					
					double childPercentage = 1.0/(double)k;
						
					KBLNode<JVirtualKeyboardButton> newChildNode = new KBLNode<JVirtualKeyboardButton>(child); 
					
					if(parentLayoutNode.getAvaialbleHeight() != -1)
					{
						float childHeight = (float)childPercentage * parentLayoutNode.getAvaialbleHeight();
					
						newChildNode.setDivideType(KBLNode.VERTICAL);
						newChildNode.setAvaialbleHeight(childHeight);
						newChildNode.setAvaialbleWidth(parentLayoutNode.getAvaialbleWidth());
						KBLNode<JVirtualKeyboardButton> tmpChildNode = preKBLCalculation(newChildNode,level, maxLevel); 					
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
*/
	public KBLNode<JIndirectSelectionButton> preKBLCalculationRowBased2 (KBLNode<JIndirectSelectionButton> parentLayoutNode, int level, int maxLevel)
	{
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		
		if(parentNode.isLeaf() )
		{
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
					
						newChildNode.setDivideType(KBLNode.VERTICAL);
						newChildNode.setAvaialbleWidth(childWidth);
						newChildNode.setAvaialbleHeight(parentLayoutNode.getAvaialbleHeight());
						//level = level +1;
						KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculationRowBased2(newChildNode,level, maxLevel);
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
			else if (parentLayoutNode.getDivideType() == KBLNode.HORIZONTAL)
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
					
						newChildNode.setDivideType(KBLNode.HORIZONTAL);
						newChildNode.setAvaialbleHeight(childHeight);
						newChildNode.setAvaialbleWidth(parentLayoutNode.getAvaialbleWidth());
						if( level == maxLevel)
						{
							newChildNode.setDivideType(KBLNode.VERTICAL);
							
						}
						else 
						{
							float tmpWidthPercent = newChildNode.getAvaialbleWidth();
							Node<JIndirectSelectionButton> tmpChildNode = newChildNode.getNode();
							int numOfAssociateNodes = tmpChildNode.getSelectionGroupSize(); 
							
							float widthRatio = tmpWidthPercent /(float)numOfAssociateNodes;
							float minScreenRatio = (float)MIN_BUTTON_WIDTH/SCREEN_WIDTH *MAX_PERCENTAGE;
							
							if(widthRatio >= minScreenRatio)
							{
								newChildNode.setDivideType(KBLNode.VERTICAL);
							}
						}
						KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculationRowBased2(newChildNode,level, maxLevel); 					
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


	
	public KBLNode<JIndirectSelectionButton> preKBLCalculationRowBased1 (KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		technique = TECHNIQUE1;
		if(parentNode.isLeaf() )
		{
					parentLayoutNode.setRequiredWidth(parentLayoutNode.getAvaialbleWidth());
					parentLayoutNode.setRequiredHeight(parentLayoutNode.getAvaialbleHeight());
				
				
		}
		else
		{
			if (parentLayoutNode.getDivideType() == KBLNode.VERTICAL)
			{
				int k = children.size();
				
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
						KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculationRowBased1(newChildNode);
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
					
						newChildNode.setDivideType(KBLNode.HORIZONTAL);
						newChildNode.setAvaialbleHeight(childHeight);
						newChildNode.setAvaialbleWidth(parentLayoutNode.getAvaialbleWidth());
						KBLNode<JIndirectSelectionButton> tmpChildNode = preKBLCalculationRowBased1(newChildNode); 					
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
	
	public JPanel CHToKBLRowBased (KBLNode<JIndirectSelectionButton> parentLayoutNode, int technique)
	{
		Node<JIndirectSelectionButton> parentNode = parentLayoutNode.getNode();
		List <Node<JIndirectSelectionButton>> children = parentNode.getChildren();
		
		JPanel boxContainer = new JPanel();
		
		if(parentNode.isLeaf() )
		{
			this.configureBoxContainer(boxContainer, parentLayoutNode);
			return boxContainer;
		}
		else
		{
			if (parentLayoutNode.getDivideType() == KBLNode.VERTICAL)
			{
				int totalInterval= 0;
				List <KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode.getChildren();
				//All the children are assigned the percentages of the width 
				//*/
				
				List<Float> childWidthPercentage = calculateDimensionPercentageRowBased(parentLayoutNode, KBLNode.WIDTH, technique);
				JPanel subBoxContainer = new JPanel();	
				
				float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
				float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
				//List<JVirtualKeyboardButton> buttonList = ((parentLayoutNode.getNode()).getNodeSelectionGroup()).extractMembers()
		
				for (int i = 0; i < kblChildren.size(); i++)
				{
					KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
					double nodeSizePercentage = childWidthPercentage.get(i);
					subBoxContainer = new JPanel();	
					float interval = 0;
					interval = Math.round(parentWidth *  nodeSizePercentage);
					totalInterval += interval;
					if(i == children.size() -1)
					{
						float remainder = parentWidth - totalInterval;
						interval += remainder;
					}
						//New child node is created and dimensions are assigned to it accordingly, and the 
					//divide type is set. 
					setChildLayoutNode(parentLayoutNode,kblChild, KBLNode.WIDTH,interval, i , KBLNode.VERTICAL);				
					subBoxContainer = CHToKBLRowBased (kblChild, technique);
					boxContainer.add(subBoxContainer);
					this.configureBoxContainer(boxContainer, parentLayoutNode);
				}
			}
			else if(parentLayoutNode.getDivideType() == KBLNode.HORIZONTAL)
			{
				int totalInterval= 0;
				List <KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode.getChildren();
				//All the children are assigned the percentages of the width 
				//*/
				List<Float> childHeightPercentage = calculateDimensionPercentage(parentLayoutNode, KBLNode.HEIGHT, technique);
				float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
				//float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
				JPanel subBoxContainer = new JPanel();	
				
					for (int i = 0; i < kblChildren.size(); i++)
					{
						KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
						double nodeSizePercentage = 0.0;
						nodeSizePercentage = childHeightPercentage.get(i);
						//System.out.println("Height: calc : " + nodeSizePercentage*parentHeight + " requ: "  +(kblChild.getRequiredHeight()/100)*SCREEN_HEIGHT);
						//System.out.println(" At" + kblChild.getNode().toString());
						subBoxContainer = new JPanel();	
					
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
						setChildLayoutNode(parentLayoutNode,kblChild, KBLNode.HEIGHT,interval, i , KBLNode.HORIZONTAL);				
						
						float childHeight = kblChild.getEndY()- kblChild.getStartY();
						float childWidth = kblChild.getEndX()- kblChild.getStartX();
						int associatedNodeSize = (((kblChild.getNode()).getSelectionGroup()).extractMembers()).size();
						if( childHeight < MIN_BUTTON_HEIGHT || (childWidth/(float)associatedNodeSize ) >= MIN_BUTTON_WIDTH)
						{
							kblChild.setDivideType(KBLNode.VERTICAL);
							
						}
						subBoxContainer = CHToKBLRowBased (kblChild, technique);
						boxContainer.add(subBoxContainer);
						this.configureBoxContainer(boxContainer, parentLayoutNode);
					}
					
				//}
					
			}
			return boxContainer;
		}
			
	}
	
	private void setChildLayoutNodeRowBased(KBLNode<JIndirectSelectionButton> parentLayoutNode, KBLNode<JIndirectSelectionButton> kblChild, int flag, float interval, int i , int divideType)
	{
	//	KBLNode<JVirtualKeyboardButton> parentLayoutNode = kblChild.parent;
		if(flag == KBLNode.WIDTH)
		{
			kblChild.setDivideType(divideType);
			kblChild.setStartX(i* interval);
			kblChild.setEndX((i+1)* interval);
			kblChild.setStartY(parentLayoutNode.getStartY());
			kblChild.setEndY(parentLayoutNode.getEndY());
		}
		else if(flag == KBLNode.HEIGHT)
		{
			kblChild.setDivideType(divideType);
			kblChild.setStartY(i* interval);
			kblChild.setEndY((i+1)* interval);
			kblChild.setStartX(parentLayoutNode.getStartX());
			kblChild.setEndX(parentLayoutNode.getEndX());
		}
		else
		{
			kblChild.setDivideType(divideType);
			kblChild.setStartY(parentLayoutNode.getStartY());
			kblChild.setEndY(parentLayoutNode.getEndY());
			kblChild.setStartX(parentLayoutNode.getStartX());
			kblChild.setEndX(parentLayoutNode.getEndX());
		}
		//return kblChild;
	}	
	
	
	private void setChildLayoutNode(KBLNode<JIndirectSelectionButton> parentLayoutNode, KBLNode<JIndirectSelectionButton> kblChild, int flag, float interval, int i , int divideType)
	{
	//	KBLNode<JVirtualKeyboardButton> parentLayoutNode = kblChild.parent;
		if(flag == KBLNode.WIDTH)
		{
			kblChild.setDivideType(divideType);
			kblChild.setStartX(i* interval);
			kblChild.setEndX((i+1)* interval);
			kblChild.setStartY(parentLayoutNode.getStartY());
			kblChild.setEndY(parentLayoutNode.getEndY());
		}
		else if(flag == KBLNode.HEIGHT)
		{
			kblChild.setDivideType(divideType);
			kblChild.setStartY(i* interval);
			kblChild.setEndY((i+1)* interval);
			kblChild.setStartX(parentLayoutNode.getStartX());
			kblChild.setEndX(parentLayoutNode.getEndX());
		}
		//return kblChild;
	}	
	private List<Float> scalingTechnique1(KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		List <KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode.getChildren();
		List <Node<JIndirectSelectionButton>> children = parentLayoutNode.getNode().getChildren();
		
		Vector<Float> childDimPercentage = new Vector<Float>();
		int k = children.size();
		
		for (int i = 0; i < kblChildren.size(); i++)
		{
			float scalingFactor = (float)1/(float)k;
			childDimPercentage.add(scalingFactor );
			
		}
		return childDimPercentage;
		
	}
	
	private List<Float> calculateWidthPercentageRowBased3(KBLNode<JIndirectSelectionButton> parentLayoutNode, int flag, int technique)
	{
		List <KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode.getChildren();
		List <Node<JIndirectSelectionButton>> children = parentLayoutNode.getNode().getChildren();
		int k = children.size();
		//Vector<Float> childDimPercentage = new Vector<Float>();
		
		List<Float> dimPercentageDepthBased = scalingTechnique2Or3(parentLayoutNode,flag,technique);
		List<Float> idealDimPercentage = getIdealPercentageRatio( parentLayoutNode, technique);
		//float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
		float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
		boolean assignIdealWidth = true;
		for (int i = 0; i < kblChildren.size(); i++)
		{
			KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
			int childAssociatedNodeSize = (((kblChild.getNode()).getSelectionGroup()).extractMembers()).size();
			float idealShare = idealDimPercentage.get(i);
			float estimatedChildWidth = idealShare * parentWidth;
			if (estimatedChildWidth/(float)childAssociatedNodeSize < MIN_BUTTON_WIDTH )
			{
				assignIdealWidth = false;
				break;
			}
		}
		if(assignIdealWidth)
		{
			return idealDimPercentage;
		}
		
		boolean assignCalculatedWidth = true;
		for (int i = 0; i < kblChildren.size(); i++)
		{
			KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
			int childAssociatedNodeSize = (((kblChild.getNode()).getSelectionGroup()).extractMembers()).size();
			float calculatedShare = dimPercentageDepthBased.get(i);
			float estimatedChildWidth = calculatedShare * parentWidth;
			if (estimatedChildWidth/(float)childAssociatedNodeSize < MIN_BUTTON_WIDTH )
			{
				assignCalculatedWidth = false;
				break;
			}
		}
		if(assignCalculatedWidth)
		{
			return idealDimPercentage;
		}
		return	calculateDimensionPercentageRowBased1Or2(parentLayoutNode, flag, technique);
	}
	
	private List<Float> calculateDimensionPercentageRowBased(KBLNode<JIndirectSelectionButton> parentLayoutNode, int flag, int technique)
	{
		if(technique == TECHNIQUE1)
		{
			return calculateDimensionPercentageRowBased1Or2(parentLayoutNode, flag, technique);
		}
		else if(technique == TECHNIQUE2)
		{
			return calculateDimensionPercentageRowBased1Or2(parentLayoutNode, flag, technique);
		}
		else if(technique == TECHNIQUE3)
		{
			return calculateWidthPercentageRowBased3(parentLayoutNode, flag, technique);
		}
		return calculateDimensionPercentageRowBased1Or2(parentLayoutNode, flag, technique);
	}
	
	private List<Float> calculateDimensionPercentageRowBased1Or2(KBLNode<JIndirectSelectionButton> parentLayoutNode, int flag, int technique)
	{
		List <KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode.getChildren();
		List <Node<JIndirectSelectionButton>> children = parentLayoutNode.getNode().getChildren();
		
		Vector<Float> childDimPercentage = new Vector<Float>();
		int k = children.size();
		
		float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
		float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
		
		int parentAssociatedNodeSize = (((parentLayoutNode.getNode()).getSelectionGroup()).extractMembers()).size();
		
		float intervalWidth = parentWidth/(float)parentAssociatedNodeSize;
		
		for (int i = 0; i < kblChildren.size(); i++)
		{
			KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
			int childAssociatedNodeSize = (((kblChild.getNode()).getSelectionGroup()).extractMembers()).size();
			
			float scalingFactor = (float)childAssociatedNodeSize/(float)parentAssociatedNodeSize;
			childDimPercentage.add(scalingFactor );
			
		}
		return childDimPercentage;
	}
	
	private List<Float> calculateDimensionPercentage(KBLNode<JIndirectSelectionButton> parentLayoutNode, int flag, int technique)
	{
		if(technique == TECHNIQUE1)
		{
			return scalingTechnique1(parentLayoutNode);
		}
		else if(technique == TECHNIQUE2)
		{
			return scalingTechnique2Or3(parentLayoutNode, flag,technique);
		}
		else if(technique == TECHNIQUE3)
		{
			return scalingTechnique2Or3(parentLayoutNode, flag,technique);
		}
		else
		{
			//default is technique 1
			return scalingTechnique1(parentLayoutNode);
		}
	}
	
	/*private List<Float> scalingTechnique2(KBLNode<JVirtualKeyboardButton> parentLayoutNode, int flag)
	{
		List <KBLNode<JVirtualKeyboardButton>> kblChildren = parentLayoutNode.getChildren();
		List <Node<JVirtualKeyboardButton>> children = parentLayoutNode.getNode().getChildren();
		
		int k = children.size();
		//calculate the width of the parent, i.e. the avaialble share of real estate
		//width for all the children
		float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
		float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
		//Vector<Double> childPercentage = new Vector<Double>();
			
		float parentDimension = 0;
		int parentScreenDim = 0;
		
		if(flag == KBLNode.WIDTH)
		{
			parentDimension = parentWidth;
			parentScreenDim = SCREEN_WIDTH;
			
		}
		else if (flag == KBLNode.HEIGHT)
		{
			parentDimension = parentHeight;
			parentScreenDim = SCREEN_HEIGHT;
			
		}
		
		Vector<Float> childDim = new Vector<Float>();
		Vector<Float> childDimPercentage = new Vector<Float>();
		//Vector<Float> childHeightPercentage = new Vector<Float>();
		
		for (int i = 0; i < kblChildren.size(); i++)
		{
			KBLNode<JVirtualKeyboardButton> kblChild = kblChildren.get(i);
			float tmpChildDim = kblChild.getRequiredDimension(flag) /MAX_PERCENTAGE * parentScreenDim;
			childDim.add(tmpChildDim);
			childDimPercentage.add((tmpChildDim/parentDimension)* MAX_PERCENTAGE );
			
		}
		
		double percentageSum = 0.0;
		//Sum all the percentage width values to check if the total percentage is greater than 100% or less, or 
		//equal
		for (int i = 0; i < childDimPercentage.size(); i++)
		{
			percentageSum += childDimPercentage.get(i);
		
		}
		//calculate the percentage difference
		double percentageDiff = (MAX_PERCENTAGE-percentageSum);
		Vector<Double> childDiff = new Vector<Double>();
		double sumDiff = 0.0;
		if(percentageDiff > 0)
		{
			sumDiff = 0.0;
			for (int i = 0; i < childDimPercentage.size(); i++)
			{
				double idealPercentage = 1.0/k;
				if( (childDimPercentage.get(i)  ) < idealPercentage*MAX_PERCENTAGE)
				{
					double diff  = idealPercentage*MAX_PERCENTAGE  -  childDimPercentage.get(i) ;
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
		else if(percentageDiff < 0)
		{
			sumDiff = 0.0;
			for (int i = 0; i < childDimPercentage.size(); i++)
			{
				double idealPercentage = 1.0/k;
				if( (childDimPercentage.get(i) *MAX_PERCENTAGE) > idealPercentage*MAX_PERCENTAGE)
				{
					double diff  = idealPercentage*100  -  (childDimPercentage.get(i) *MAX_PERCENTAGE) ;
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
			for (int i = 0; i < childDimPercentage.size(); i++)
			{
				childDiff.add(0.0);
			}
		}
		
		//All the above lines of code just calculated the percentage of width which each child should be given, 
		//in order to make sure that each button  would be visible on screen, and no button would have width
		// smaller than Min-width. 
		for (int i = 0; i < childDimPercentage.size(); i++)
		{
			double tmp = ( childDimPercentage.get(i) + ( Math.abs(percentageDiff) * childDiff.get(i) )) / MAX_PERCENTAGE;
			childDimPercentage.set(i, (float)tmp);
		}
		return childDimPercentage;
	}
	*/
	/*
	 * In this techinique, the Dimension percentage is assigned accordign to the cost of each child of the node. 
	 * For example, if the letter cost of the alphabet {a1,a2,a3,a4,a5} = {1,2,2,1,4} then the percentage would be 
	 * assigned such that the children with lower cost gets the highest share of percentage i.e. in this case, 
	 * the percentage assigned would be {4/13, 2/13,2/13,4/13,1/13}  
	 */

	private List<Float> getIdealPercentageRatio(KBLNode<JIndirectSelectionButton> parentLayoutNode, int technique)
	{
		List<Float> childDimRatio = new Vector<Float>();
		if(technique == TECHNIQUE2)
		{
			childDimRatio = getPercentageRatioTechnique2(parentLayoutNode);
		}
		else if(technique == TECHNIQUE3)
		{
			childDimRatio = getPercentageRatioTechnique3(parentLayoutNode);
		}
		return childDimRatio;
	}
	
	private List<Float> getPercentageRatioTechnique2(KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		List<Float> childDimRatio = new Vector<Float>();
		List <Node<JIndirectSelectionButton>> children = parentLayoutNode.getNode().getChildren();
		int k = children.size();
		for (int i = 0; i < children.size(); i++)
		{	
			childDimRatio.add(1/(float)k);
			
		}
		return childDimRatio;
	}
	
	private List<Float> getPercentageRatioTechnique3(KBLNode<JIndirectSelectionButton> parentLayoutNode)
	{
		List<Float> childDimRatio = new Vector<Float>();
		List <Node<JIndirectSelectionButton>> children = parentLayoutNode.getNode().getChildren();
		
		List<Integer> childCost = new Vector<Integer>();
		List<Integer> costAssigned = new Vector<Integer>();
		for (int i = 0; i < children.size(); i++)
		{
			childCost.add(((InternalNode<JIndirectSelectionButton>)parentLayoutNode.getNode()).getCost(i));
			childDimRatio.add((float)0);
			costAssigned.add(0);
		}
		List<Integer> tmpCost = new Vector<Integer>();;
		for(int i = 0; i < childCost.size(); i++)
		{
			tmpCost.add(childCost.get(i));
		}
		
		List<Integer> vector = calculateCharVector(childCost);
		for(int cost = 0; cost < vector.size(); cost++)
		{
			if(vector.get(cost) > 0)
			{
				int max = ConstructCodeTree.getMax(tmpCost);
				//int count = vector.get(i);
				for(int j = 0; j < childCost.size() ; j++)
				{
					if(childCost.get(j) == cost)
					{
						childDimRatio.set(j, (float)max);
					}
					
				}
				while(tmpCost.contains(new Integer(max)))
				{
					tmpCost.remove(new Integer (max));
				}
			}
		}
		
		float percentageSum = 0;
		System.out.println("b" +childDimRatio);
		for(int i = 0; i < childDimRatio.size(); i++)
		{
			percentageSum += childDimRatio.get(i);
		}
		for(int i = 0; i < childDimRatio.size(); i++)
		{
			float tmp = childDimRatio.get(i)/percentageSum;
			childDimRatio.set(i, tmp);
		}
		System.out.println("sum :" + percentageSum);
		System.out.println(childDimRatio);
		
		return childDimRatio;
	}
	/*
	 * It calculates a vector, similar to the characteristic 
	 * vector, as introduced by Golin '98
	 */
	private List<Integer> calculateCharVector(List<Integer> costList)
	{
		List<Integer> vector = new Vector<Integer>();
		vector.add(0);
		int size = ConstructCodeTree.getMax(costList);
		for(int i = 0; i < size; i++)
		{
			int j = i+1;
			int num = ConstructCodeTree.calculateNumOfCj(j, costList);
			vector.add(num);
		}
		int k = vector.size()-1;
		while(vector.get(k)== 0)
		{
			vector.remove(k);
			k = vector.size()-1;
		}
		return vector;
	
		
	}
	private List<Float> scalingTechnique2Or3(KBLNode<JIndirectSelectionButton> parentLayoutNode, int flag, int technique)
	{
		List <KBLNode<JIndirectSelectionButton>> kblChildren = parentLayoutNode.getChildren();
		List <Node<JIndirectSelectionButton>> children = parentLayoutNode.getNode().getChildren();
		
		int k = children.size();
		//calculate the width of the parent, i.e. the avaialble share of real estate
		//width for all the children
		float parentWidth = parentLayoutNode.getEndX()- parentLayoutNode.getStartX();
		float parentHeight = parentLayoutNode.getEndY()- parentLayoutNode.getStartY();
		//Vector<Double> childPercentage = new Vector<Double>();
			
		float parentDimension = 0;
		int parentScreenDim = 0;
		
		if(flag == KBLNode.WIDTH)
		{
			parentDimension = parentWidth;
			parentScreenDim = SCREEN_WIDTH;
			
		}
		else if (flag == KBLNode.HEIGHT)
		{
			parentDimension = parentHeight;
			parentScreenDim = SCREEN_HEIGHT;
			
		}
		
		//Vector<Float> childDim = new Vector<Float>();
		Vector<Float> childDimPercentage = new Vector<Float>();
		//Vector<Float> childHeightPercentage = new Vector<Float>();
		
		//This has to be automated
		/*Vector<Float> percentageRatio = new Vector<Float>();
		percentageRatio.add((float)2/3);
		percentageRatio.add((float)1/3);
		*/
		
		List <Float> idealPercentageRatio = getIdealPercentageRatio( parentLayoutNode, technique); 
		
		for (int i = 0; i < kblChildren.size(); i++)
		{
			KBLNode<JIndirectSelectionButton> kblChild = kblChildren.get(i);
			float tmpChildDim =kblChild.getRequiredDimension(flag) /MAX_PERCENTAGE * parentScreenDim;
			//childDim.add(tmpChildDim);
			childDimPercentage.add((tmpChildDim/parentDimension)* MAX_PERCENTAGE );
			
		}
		
		double percentageSum = 0.0;
		//Sum all the percentage width values to check if the total percentage is greater than 100% or less, or 
		//equal
		for (int i = 0; i < childDimPercentage.size(); i++)
		{
			percentageSum += childDimPercentage.get(i);
		
		}
		//calculate the percentage difference
		double percentageDiff = (MAX_PERCENTAGE-percentageSum);
		Vector<Double> childDiff = new Vector<Double>();
		double sumDiff = 0.0;
		if(percentageDiff > 0)
		{
			sumDiff = 0.0;
			for (int i = 0; i < childDimPercentage.size(); i++)
			{
				double idealPercentage = idealPercentageRatio.get(i);
				if( (childDimPercentage.get(i)  ) < idealPercentage*MAX_PERCENTAGE)
				{
					double diff  = idealPercentage*MAX_PERCENTAGE  -  childDimPercentage.get(i) ;
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
		else if(percentageDiff < 0)
		{
			sumDiff = 0.0;
			for (int i = 0; i < childDimPercentage.size(); i++)
			{
				double idealPercentage = idealPercentageRatio.get(i);
				if( (childDimPercentage.get(i) *MAX_PERCENTAGE) > idealPercentage*MAX_PERCENTAGE)
				{
					double diff  = idealPercentage*100  -  (childDimPercentage.get(i) *MAX_PERCENTAGE) ;
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
			for (int i = 0; i < childDimPercentage.size(); i++)
			{
				childDiff.add(0.0);
			}
		}
		
		//All the above lines of code just calculated the percentage of width which each child should be given, 
		//in order to make sure that each button  would be visible on screen, and no button would have width
		// smaller than Min-width. 
		for (int i = 0; i < childDimPercentage.size(); i++)
		{
			double tmp = ( childDimPercentage.get(i) + ( Math.abs(percentageDiff) * childDiff.get(i) )) / MAX_PERCENTAGE;
			childDimPercentage.set(i, (float)tmp);
		}
		return childDimPercentage;
	}
	
	

	/*private void configureBoxContainerRowBased(JPanel boxContainer,
			KBLNode<JVirtualKeyboardButton> layoutNode) {
		Node<JVirtualKeyboardButton> parentNode = layoutNode.getNode();

		if (parentNode.isLeaf()) {
			boxContainer.setLayout(new BoxLayout(boxContainer,
					BoxLayout.PAGE_AXIS));
			JVirtualKeyboardButton button = this.getButton(layoutNode);
			boxContainer.add(button);
		} else 
		{
			if(layoutNode.getDivideType() == KBLNode.VERTICAL)
			{//parent node Vertical
				//JPanel subBoxContainer = CHToKBL (layoutNode);
				
				//this.configureBoxContainer(boxContainer, parentLayoutNode);
				//return boxContainer;
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.LINE_AXIS));
				//boxContainer.add(subBoxContainer);
				boxContainer.setAlignmentX(JPanel.LEFT_ALIGNMENT);
				
			}
			else if(layoutNode.getDivideType() == KBLNode.HORIZONTAL)
			{
				
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.PAGE_AXIS));
				//JPanel subBoxContainer = CHToKBL (layoutNode);
				
				//boxContainer.add(subBoxContainer);
			}
		}
	}

*/
	private void configureBoxContainer(JPanel boxContainer,
			KBLNode<JIndirectSelectionButton> layoutNode) {
		Node<JIndirectSelectionButton> parentNode = layoutNode.getNode();

		if (parentNode.isLeaf()) {
			boxContainer.setLayout(new BoxLayout(boxContainer,
					BoxLayout.PAGE_AXIS));
			JIndirectSelectionButton button = this.getButton(layoutNode);
			boxContainer.add(button);
		} else 
		{
			if(layoutNode.getDivideType() == KBLNode.VERTICAL)
			{//parent node Vertical
				//JPanel subBoxContainer = CHToKBL (layoutNode);
				
				//this.configureBoxContainer(boxContainer, parentLayoutNode);
				//return boxContainer;
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.LINE_AXIS));
				//boxContainer.add(subBoxContainer);
				boxContainer.setAlignmentX(JPanel.LEFT_ALIGNMENT);
				
			}
			else if(layoutNode.getDivideType() == KBLNode.HORIZONTAL)
			{
				
				boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.PAGE_AXIS));
				//JPanel subBoxContainer = CHToKBL (layoutNode);
				
				//boxContainer.add(subBoxContainer);
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

	private void createSpecialButtonForDigit(KBLNode<JIndirectSelectionButton> kblDigit, Node<JIndirectSelectionButton> digitNode )
	{
	
		MyTraversableEncodingTree<JIndirectSelectionButton> digitCH= createDigitCH(digitNode );
	}
	
	private MyTraversableEncodingTree<JIndirectSelectionButton> createDigitCH(Node<JIndirectSelectionButton> digitNode )
	{
		InternalNode<JIndirectSelectionButton> root = new InternalNode<JIndirectSelectionButton> ()  ; 
		
		LeafNode<JIndirectSelectionButton> node1 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_1);
		LeafNode<JIndirectSelectionButton> node2 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_2);
		LeafNode<JIndirectSelectionButton> node3 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_3);
		LeafNode<JIndirectSelectionButton> node4 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_4);
		LeafNode<JIndirectSelectionButton> node5 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_5);
		LeafNode<JIndirectSelectionButton> node6 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_6);
		LeafNode<JIndirectSelectionButton> node7 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_7);
		LeafNode<JIndirectSelectionButton> node8 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_8);
		LeafNode<JIndirectSelectionButton> node9 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_9);
		LeafNode<JIndirectSelectionButton> node0 = new LeafNode<JIndirectSelectionButton>(sourceSymbolSet.VK_0);
		
		InternalNode<JIndirectSelectionButton> node12 = new InternalNode<JIndirectSelectionButton> ()  ;
		node12.addChild(node1);
		node12.addChild(node2);
		
		InternalNode<JIndirectSelectionButton> node45 = new InternalNode<JIndirectSelectionButton> ()  ;
		node45.addChild(node4);
		node45.addChild(node5);
		
		InternalNode<JIndirectSelectionButton> node345 = new InternalNode<JIndirectSelectionButton> ()  ;
		node345.addChild(node3);
		node345.addChild(node45);
		
		InternalNode<JIndirectSelectionButton> node12345 = new InternalNode<JIndirectSelectionButton> ()  ;
		
		node12345.addChild(node12);
		node12345.addChild(node345);
		
		InternalNode<JIndirectSelectionButton> node67 = new InternalNode<JIndirectSelectionButton> ()  ;
		node67.addChild(node6);
		node67.addChild(node7);
		
		InternalNode<JIndirectSelectionButton> node90 = new InternalNode<JIndirectSelectionButton> ()  ;
		node90.addChild(node9);
		node90.addChild(node0);
		
		InternalNode<JIndirectSelectionButton> node890 = new InternalNode<JIndirectSelectionButton> ()  ;
		node890.addChild(node8);
		node890.addChild(node90);
		
		InternalNode<JIndirectSelectionButton> node67890 = new InternalNode<JIndirectSelectionButton> ()  ;
		
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

		//System.out.println(button);
		
		System.out.println(button.getText() + " H: " + button.getPreferredSize().height + " W: " + button.getPreferredSize().width  );
		
		if (buttonWidth >= SCREEN_WIDTH / QUARTER
				&& buttonHeight >= SCREEN_HEIGHT / QUARTER) {
			if (button.getText().length() == 1) {

				button
						.setFont(new Font(FONT_TYPE, Font.BOLD,
								LARGEST_FONT_SIZE));
				
			} else {
				button.setFont(new Font(FONT_TYPE, Font.BOLD,
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
		//System.out.println(button + " size " + button.getFont());
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


