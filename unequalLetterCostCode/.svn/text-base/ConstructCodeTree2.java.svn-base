package unequalLetterCostCode;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;

import sourceSymbolSet.SourceSymbol;
import unequalLetterCostCode.OPTTable;
import unequalLetterCostCode.Signature;
import unequalLetterCostCode.TableEntry;
import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.Node;

public class ConstructCodeTree2 <X extends SourceSymbol>{
	
	
	
	private OPTTable OPT_Table; 
	private List <Integer> terminalIndices;
	private List <Double> probability;
	private Signature vectorD;
	private int n;
	private int r;
	//private int c;
	// a list to keep track which index of opt-table contains which level 
	
	/*
	 * The comparator returns 1 is entry 1 > entry2 according to the 
	 * lexicographical linear order
	 * it returns -1 if entry 1 < entry 2
	 * it returns 0 if both are equal
	 */
	
	

	
	
	
	/*
	 * Implements the algorithm on page 1777 of golin 98
	 */
	
	
	
	
	public ConstructCodeTree2(List <Double> probabilities, List <Integer> costList, int n, int r)
	{
		OPT_Table = new OPTTable();
		terminalIndices = new Vector<Integer>();
		Signature d = calculateInitialCharVector(costList);
		//Our probability starts from p[1] .... and p[0] is excluded.
		//System.out.println("Signature..........." + d );
		
		probability = new Vector<Double>();
		probability.add(0.0);
		probability.addAll(probabilities);
		//probability = probabilities;
		vectorD = d;
		this.n = n;
		this.r = r;
		//this.c = c;
		
		//initialize
		
		vectorD.setM(0);
		//page 
		TableEntry root = new TableEntry( d.clone(), 0);
		//root.setLinkIndex(0);
		//root.setQ(0);
		OPT_Table.add(0,root);
		List <Integer> levels = new Vector<Integer>();
		levels.add(0);
		//it means level[0] = 0, which means level 0 starts from the 0th index
		// level[1] = 7 would mean level 1 starts from the 7th  index of opt-table
		
		vectorD.setM(-1);
		//the first entry of the characteristic vector is set to -1. 
		
		
		
		boolean construct = true;
		int levelIndex = 0;
		
		//Steps 2a, 2 b, 2c
		while(construct  )
		{
			int size = OPT_Table.size();
			levels.add(size);
			//System.out.println("In while for level " + levelIndex + " size : " + size);
			//System.out.println(OPT_Table.toString());
			if(levels.get(levelIndex) == size)
			{
				//System.out.println("beraking the while loop");
				construct = false;
				break;
				
			}
			/************/
			//SOrting in lexicographically increasing order
			
			 List <TableEntry> sortedList = new Vector<TableEntry>();
			for(int index = levels.get(levelIndex); index < size; index ++)
			{
				sortedList.add(OPT_Table.get(index));
			}
			
			//System.out.println("OPT table Sorting.............................");
			
			//System.out.println(OPT_Table);
			
			//System.out.println("Sorting.............................");
			//System.out.println(sortedList);
			
			Collections.sort(sortedList, c);
			//System.out.println("After Sort" + sortedList);
			int k = 0;
			for(int index = levels.get(levelIndex); index < size; index ++)
			{
				OPT_Table.set( index,sortedList.get(k));
				k++;
			}
			
			//System.out.println("OPT table after Sorting...");
			
			//System.out.println(OPT_Table);
			
			
			/********************/
			for(int index = levels.get(levelIndex); index < size; index ++)
			{
				TableEntry entry = OPT_Table.get(index);
				double newCost = OPT_Table.getValue(index) + summationProbability(entry.getM() ) ;
				
				if(entry.getM() < n  && !(entry.getSignature()).allZeros())
				{
								
					for(int q = 0; q  <= entry.getL1(); q++)
					{
						Signature expandedSig = (entry.getSignature()).calculateExpandedSignature(q, vectorD);
						if(expandedSig.sumAll() <= (n * (r - 1)))
						{
							
							int exists = OPT_Table.entryExists(expandedSig);
							if(exists == -1 )
							{
								TableEntry newEntry = new TableEntry(expandedSig, newCost);
								newEntry.setQ(q);
								newEntry.setLinkIndex(index);
								OPT_Table.add(newEntry);
							}
							else
							{
								double oldCost = OPT_Table.getValue(exists);
								if(oldCost > newCost)
								{
									TableEntry newEntry = new TableEntry(expandedSig, newCost);
									
									//List <TableEntry> oldCostEntries = searchEntriesWithLink(exists);
									
									searchAndUpdateEntriesWithLink(exists, oldCost,newCost);
									//for Debug
									
									TableEntry oldEntry = OPT_Table.get(exists);
									
									newEntry.debugCost = oldEntry.debugCost;
									newEntry.debugLinks = oldEntry.debugLinks;
									newEntry.debugSigLevel = oldEntry.debugSigLevel;
									
									newEntry.debugCost.add(oldCost);
									newEntry.debugLinks.add(oldEntry.getLinkIndex());
									newEntry.debugSigLevel.add(oldEntry.getSignature().getLevel());
									
									newEntry.setQ(q);
									newEntry.setLinkIndex(index);
									
									OPT_Table.set(exists, newEntry);
								}
								/*expandedSig.setLevel(levelIndex);
								double oldCost = OPT_Table.getValue(exists);
								TableEntry newEntry = new TableEntry(expandedSig, min(oldCost,newCost));
								newEntry.setQ(q);
								newEntry.setLinkIndex(index);
								OPT_Table.set(exists, newEntry);
								*/
							}
						}
					
					}
				}
				else
				{
					if(entry.getM() >= n)
					{
						entry.setTerminal(true);
						OPT_Table.set(index, entry);
						terminalIndices.add(index);
					}
				}
				
			}
			levelIndex++;
		}
		
		
		
	}
	
	public void searchAndUpdateEntriesWithLink(int link, double oldCost, double newCost)
	{
		for (int i = 0 ; i < OPT_Table.size(); i++)
		{
			TableEntry tmpEntry = OPT_Table.get(i);
			if(tmpEntry.getLinkIndex() == link)
			{
				double tmpCost = tmpEntry.getValue();
				tmpCost = tmpCost - oldCost + newCost;
				OPT_Table.get(i).setValue(tmpCost);
			}
			
		}
	}
	
	private static int getMax(List<Integer> list)
	{
	 int size = 0;
	  for (int i = 0; i < list.size(); i++)
	  {
		  if(list.get(i) > size )
		  {
			  size = list.get(i);
		  }
	  }
	  return size;
	}
	
	private Signature calculateInitialCharVector(List <Integer> costList)
	{
		
		List<Integer> vector = new Vector<Integer>();
		vector.add(0);
		int size = getMax(costList);
		for(int i = 0; i < size; i++)
		{
			int j = i+1;
			int num = calculateNumOfCj(j, costList);
			vector.add(num);
		}
		int k = vector.size()-1;
		while(vector.get(k)== 0)
		{
			vector.remove(k);
			k = vector.size()-1;
		}
		Signature sig = new Signature(vector);
		return sig;
	}
	
	private int calculateNumOfCj(int j, List <Integer> costList)
	{
		int result = 0;
		for(int i = 0; i <costList.size(); i++)
		{
			if(costList.get(i) == j)
			{
				result ++ ;
			}
		}
		return result;
		
	}
	
	private static Comparator<TableEntry> c = new Comparator<TableEntry>() 
	{
		public int compare(TableEntry entry1, TableEntry entry2) 
		{
			int result = (entry1.getSignature()).partialSumCompare(entry2.getSignature());
			return result;
		}
	};
	
	public  InternalNode<X> constructCodeforUnequalLetterCosts(List<X> encodingAlphabet, List<Integer> encodingAlphabetCosts, List<X> enabledWords)
	{
		//System.out.println("------------Printing Code Not ---------------");
		
		//this.toString();
		
		InternalNode<X> codeTree = this.buildMinCostTree(encodingAlphabet, encodingAlphabetCosts);
		/*
		System.out.println("Printing Code Tree ");
		
		System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		System.out.println(codeTree.toStringPlainTextLispStyle(0));
		*/
		InternalNode<X> codeTreeWithWords = this.assignActualProbabilities(codeTree, enabledWords);
		
		
		//System.out.println(InternalNode.toStringPlainTextLispStyleHeader());
		//System.out.println(codeTreeWithWords.toStringPlainTextLispStyle(0));
		
		return codeTreeWithWords;
	}
	
	public InternalNode<X> buildMinCostTree(List<X> rChildSymbols, List<Integer> rChildCosts)
	{
		System.out.println(this.OPT_Table);
		
		int index = searchMinCostTree();
		List <TableEntry> treeEntries = new Vector<TableEntry>();
		while(index != 0)
		{
			
			TableEntry tempEntry= OPT_Table.get(index);
			treeEntries.add(tempEntry);
			//System.out.println("in while index:" + index + " " + tempEntry);
			
			index = tempEntry.getLinkIndex();
		}
		if(index == 0)
		{
			treeEntries.add(OPT_Table.get(index));
			//System.out.println("in index 0 index:" + index + OPT_Table.get(index));
		}
		
		InternalNode<X> root = new InternalNode<X>(); 
		root = root.getTreeAtLevel0(rChildSymbols, rChildCosts);
		
	//	System.out.println("Printing tree");
		
		for(int j = (treeEntries.size()-1); j >=0 ; j--)
		{
			TableEntry tempEntry = treeEntries.get(j);
		//	System.out.println(tempEntry);
			int sigLevel = tempEntry.getSignature().getLevel();
			if( sigLevel > 0 )
			{
				if(tempEntry.getQ() > 0)
			//		System.out.println("expanding "  + tempEntry + " q" + tempEntry.getQ());
					root = root.expandTreeAtLevelI(sigLevel, tempEntry.getQ(), rChildSymbols, rChildCosts);
				
			}
		}
		root  = root.updateAssociatedNodeSet();
		
		return root;
		
		
		
	}
	
	/*
	 * It is assumed that this method is receiving as input the root node of the tree, 
	 * to which we want to assignt probabilities 
	 */
	
	public InternalNode<X> assignActualProbabilities(InternalNode<X> root, List<X> wordList)
	{
		int wordIndex = 0;
		int depth = root.calculateMaximumLeafDepth(root.getEdgeCostToHere());
		for(int i = 0; i <= depth; i ++)
		{
			List<Node<X>> leaves = root.getleavesAtLevel(i);
			for(Node<X> prevLeaf : leaves)
			{
				if(wordIndex >=0 && wordIndex < wordList.size())
				{	
					LeafNode<X> newLeaf = new LeafNode<X>(wordList.get(wordIndex));
					newLeaf.setDepth(prevLeaf.getEdgeCostToHere());
				//this line might be wrong
					wordIndex++;
					newLeaf.setEncodingAlphabet((prevLeaf.getRepresentative()).toString());
				
					List<Node<X>> v = (prevLeaf.parent).getChildren();

					int childNum = v.indexOf(prevLeaf);
					( (InternalNode<X>)prevLeaf.parent).setChildToParent(( (InternalNode<X>)prevLeaf.parent), newLeaf, childNum);
					//return wordIndex;
					//int temp = 0;
				}
				else if (wordIndex >=wordList.size())
				{
					List<Node<X>> v = (prevLeaf.parent).getChildren();

					int childNum = v.indexOf(prevLeaf);
					v.remove(childNum);
					//return wordIndex;
				}

			}

		}
		//root = removeExtraInternalNodes(root);
		removeExtraInternalNodes(root);
		root  = root.updateAssociatedNodeSet();
		
		
		return root;
	}

	private InternalNode<X> removeExtraInternalNodes(InternalNode<X> node)
	{
		//InternalNode<X> node = node1.clone();
		int depth = node.calculateMaximumLeafDepth(node.getEdgeCostToHere());
		for(int i = depth; i >= 0; i --)
		{
			List<InternalNode<X>> internalNodes = node.getInternalNodesAtLevel(i);
			for(InternalNode<X> prevNode : internalNodes)
			{
				List<Node<X>> children = prevNode.getChildren();
				if(children.size() == 0)
				{	
					List<Node<X>> v = (prevNode.parent).getChildren();

					int childNum = v.indexOf(prevNode);
					v.remove(childNum);
					//return wordIndex;
				}

			}

		}
		
		return node;
	}
	/*
	private InternalNode<X> cleanStrayInternalNodes(InternalNode<X> root)
	{
		InternalNode<X> temp = root.clone();
		subCleanStrayInternalNodes(temp);
		return temp;
		
		
	}
	
	private void subCleanStrayInternalNodes(Node<X> root)
	{
		if(!root.isLeaf())
		{
			List<Node<X>> children = root.getChildren();
			if( children.size() == 0)
			{
				List<Node<X>> v = (root.parent).getChildren();

				int childNum = v.indexOf(root);
				v.remove(childNum);
				return;

			}
			else
			{
				
				for(int i = 0; i < children.size(); i ++)
				{
					Node<X> child = children.get(i);
					subCleanStrayInternalNodes(child);
				}
			}
		}
		else
		{
			return;
		}
	}
	*/
	private int subAssignProbabilities(Node<X> node, List<X> wordList, int wordIndex )
	{
		if(node.isLeaf() )
		{
			if(wordIndex >=0 && wordIndex < wordList.size())
			{	
				LeafNode<X> leaf = new LeafNode<X>(wordList.get(wordIndex));
			
			//this line might be wrong
				wordIndex++;
				leaf.setEncodingAlphabet((node.getRepresentative()).toString());
			
				List<Node<X>> v = (node.parent).getChildren();

				int childNum = v.indexOf(node);
				( (InternalNode<X>)node.parent).setChildToParent(( (InternalNode<X>)node.parent), leaf, childNum);
				return wordIndex;
			}
			else if (wordIndex >=wordList.size())
			{
				List<Node<X>> v = (node.parent).getChildren();

				int childNum = v.indexOf(node);
				v.remove(childNum);
				return wordIndex;
			}
			
		}
		else
		{
			List<Node<X>> children = node.getChildren();
			int wOld = wordIndex;
			int wNew = 0;
			for(Node<X> ch : children)
			{
				
				wNew = subAssignProbabilities(ch, wordList, wOld );
				wOld = wNew;
			}
			return wNew;
		}
		
		return -1;
		
	}
	
	/*
	private int subAssignProbabilities(Node<X> node, List<X> wordList, int wordIndex )
	{
		if(node.isLeaf() )
		{
			if(wordIndex >=0 && wordIndex < wordList.size())
			{	
				LeafNode<X> leaf = new LeafNode<X>(wordList.get(wordIndex));
			
			//this line might be wrong
				wordIndex++;
				leaf.setEncodingAlphabet((node.getRepresentative()).toString());
			
				List<Node<X>> v = (node.parent).getChildren();

				int childNum = v.indexOf(node);
				( (InternalNode<X>)node.parent).setChildToParent(( (InternalNode<X>)node.parent), leaf, childNum);
				return wordIndex;
			}
			else if (wordIndex >=wordList.size())
			{
				List<Node<X>> v = (node.parent).getChildren();

				int childNum = v.indexOf(node);
				v.remove(childNum);
				return wordIndex;
			}
			
		}
		else
		{
			List<Node<X>> children = node.getChildren();
			int wOld = wordIndex;
			int wNew = 0;
			for(Node<X> ch : children)
			{
				
				wNew = subAssignProbabilities(ch, wordList, wOld );
				wOld = wNew;
			}
			return wNew;
		}
		
		return -1;
		
	}
	*/
	/*
	public InternalNode<X> assignActualProbabilities()
	{
		
	}
	*/
	/*public InternalNode<X> buildMinCostTree2(EncodingAlphabet alphabet, int k)
	{
		if(k > 0 && k <= alphabet.size())
		{
			
		List<Integer> encodingAlphabetCosts = new Vector<Integer>();
		List<JVirtualKeyboardButton> alphabetSymbols = new Vector<JVirtualKeyboardButton> ();
		
		for(int i = 0; i < k; i ++ )
		{
			encodingAlphabetCosts.add(alphabet.getAlphabetCost(i));
			alphabetSymbols.add(alphabet.getAlphabet(i));
		}
		
		
		int index = searchMinCostTree();
		List <TableEntry> treeEntries = new Vector<TableEntry>();
		while(index != 0)
		{
			TableEntry tempEntry= OPT_Table.get(index);
			treeEntries.add(tempEntry);
			index = tempEntry.getLinkIndex();
		}
		if(index == 0)
		{
			treeEntries.add(OPT_Table.get(index));
		}
		
		InternalNode<X> root = new InternalNode<X>(); 
		root = root.getTreeAtLevel0(alphabetSymbols, encodingAlphabetCosts);
		
		for(int j = (treeEntries.size()-1); j >=0 ; j--)
		{
			TableEntry tempEntry = treeEntries.get(j);
			int sigLevel = tempEntry.getSignature().getLevel();
			if( sigLevel > 0 )
			{
				if(tempEntry.getQ() > 0)
					root = root.expandTreeAtLevelI(sigLevel, tempEntry.getQ(), alphabetSymbols, encodingAlphabetCosts);
				
			}
		}
		root  = root.updateAssociatedNodeSet();
		
		return root;
		}
		else
			return null;
	}
	
	*/
	public int searchMinCostTree()
	{
		
		int index = terminalIndices.get(0);
		int minIndex = index ;
		double minimum = OPT_Table.getValue(index);
		for(int i = 1; i < terminalIndices.size(); i ++)
		{
			index = terminalIndices.get(i);
			double newMinimum = OPT_Table.getValue(index);
			if(newMinimum < minimum)
			{
				minimum = newMinimum;
				minIndex = index;
			}
		}
		return minIndex;
	}
	
	private double min(double d1, double d2)
	{
		if(d1 <= d2)
			return d1;
		else
			return d2;
	}
	
	/*
	 * Calculate summation of probailities p(t) for m  < t <= n
	 * our probabilites start from p[1] amd so on....
	 * p[0] contains nothing
	 */
	private double summationProbability(int m )
	{
		double result = 0.0;
		if(m >= 0 && m < probability.size() )
		for(int i = (m+1); i < probability.size(); i ++)
		{
			result += probability.get(i);
			
		}
		
		return result;
	}
	
	public String toString()
	{	StringBuffer buf = new StringBuffer();
		buf.append("Code .... " );
		buf.append(OPT_Table );
		
		return buf.toString();

	}
}
