package appsTestingISF;



import indirectTextEntrySystemVariants.SelectableList;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;

import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import unequalLetterCostCode.ConstructCodeTree;
import unequalLetterCostCode.EncodingAlphabet;
import unequalLetterCostCode.Signature;

import TreeDataStructure.InternalNode;
import TreeDataStructure.Node;
import junit.framework.TestCase;
import clock.StopWatch;
import encodingTrees.ContainmentHierarchy;

public class ConstructCodeNKValues{

	private SourceSymbolSet selectableKeyList;
	private UtilityClasses.ProbabilityDistribution<JIndirectSelectionButton> pd;
	private MyTraversableEncodingTree<JIndirectSelectionButton> containmentHierarchy;
	
	private List<Double> enabledProbabilityList; 
	private List<JIndirectSelectionButton> enabledWords;
	Signature vectorD;
	
	public ConstructCodeNKValues() {
		
		pd = new ProbDist_Venkatagiri99_Hypothesized();
		selectableKeyList = new SourceSymbolSet(pd);
		
		enabledProbabilityList = selectableKeyList.getProbabilitiesByRankOrder();
		enabledWords = selectableKeyList.getSourceSymbolsByRankOrder() ;
		
		
		assignProbabilitiesToCommands(pd, 1);
		selectableKeyList.sort();
		
		
		
		
	}

	public static void main(String[] args) {
		
		ConstructCodeNKValues code = new ConstructCodeNKValues();
		code.Test1();
	}
	
	public void  Test1()
	{
		for(int i = 1; i < 46; i++)
		{
			System.out.print(i + "       ");
		}
		System.out.println("");
		for(int k = 2; k < 45; k++)
		{
			System.out.print("k=" + k + "    ");
			for(int n = 2; n <45; n++)
			{
				//StopWatch sw = subTest1( k, n);
				
				
				selectableKeyList.trimDownToSize(n);
				
				//containmentHierarchy = 
					//new ContainmentHierarchy<JVirtualKeyboardButton>( buildContainmentHierarchy(k));

				List<Integer> vector = new Vector<Integer>();
				vector.add(0);
				for(int count = 0; count < k; count ++)
				{
					vector.add(1);
					
				}
				
				vectorD = new Signature(vector);
					
				int wordsNum = enabledWords.size();
				EncodingAlphabet encodingAlphabet = new EncodingAlphabet(k);
					
				int rChildren = encodingAlphabet.size();
				
				 StopWatch sw = new StopWatch();
			     sw.start();  // capture start time
			       
				
				ConstructCodeTree<JIndirectSelectionButton> code = new ConstructCodeTree<JIndirectSelectionButton>(enabledProbabilityList, encodingAlphabet.getAlphabetCostList(), wordsNum, rChildren);
					
				InternalNode<JIndirectSelectionButton> codeTreeWithWords = code.constructCodeforUnequalLetterCosts(encodingAlphabet.getAlphabetList(), encodingAlphabet.getAlphabetCostList(), enabledWords);
					
				sw.end();
				
				containmentHierarchy = new MyTraversableEncodingTree<JIndirectSelectionButton>(codeTreeWithWords);
				
				//return sw;

				System.out.print("  " + sw.elapsedSeconds());
			}
			System.out.println("  ");
		}
	}
	/*
	public StopWatch subTest1(int k, int n)
	{
		selectableKeyList.setSelectableListOfSize(n);
		
		//containmentHierarchy = 
			//new ContainmentHierarchy<JVirtualKeyboardButton>( buildContainmentHierarchy(k));

		List<Integer> vector = new Vector<Integer>();
		vector.add(0);
		for(int count = 0; count < k; count ++)
		{
			vector.add(1);
			
		}
		
		vectorD = new Signature(vector);
			
		int wordsNum = enabledWords.size();
		EncodingAlphabet encodingAlphabet = new EncodingAlphabet(k);
			
		int rChildren = encodingAlphabet.size();
		
		 StopWatch sw = new StopWatch();
	     sw.start();  // capture start time
	       
		
		ConstructCode<JVirtualKeyboardButton> code = new ConstructCode<JVirtualKeyboardButton>(enabledProbabilityList, vectorD, wordsNum, rChildren);
			
		InternalNode<JVirtualKeyboardButton> codeTreeWithWords = code.constructCodeforUnequalLetterCosts(encodingAlphabet.getAlphabetList(), encodingAlphabet.getAlphabetCostList(), enabledWords);
			
		sw.end();
		
		containmentHierarchy = new ContainmentHierarchy<JVirtualKeyboardButton>(codeTreeWithWords);
		
		return sw;

	}*/
	
	public void assignProbabilitiesToCommands(
			UtilityClasses.ProbabilityDistribution pd, int dummy) {
		double sum = 0.0;
		for (JIndirectSelectionButton but : selectableKeyList.getSourceSymbolsByRankOrder())
			{
			// If action command is not in domain of probability distribution, a
			// null pointer exception is thrown. Need to improve this code to
			// protect agaisnt this possible run-time error.
			try {
				// double expectedFreq = getProbDist().get(but);
				// double expectedFreq = initialProbDistribution.get(but);
				double expectedFreq = pd.get(but);
				// System.out.println(expectedFreq);
				but.setMarginalProbability(expectedFreq);
				sum += expectedFreq;
			} catch (NullPointerException e) {
				// throw new RuntimeException("Button " + but + " not found in
				// probability distribution");
				System.out.println("Button " + but
						+ " not found in probability distribution");
				e.printStackTrace();
			}
		}
		
	}

	
	
}
