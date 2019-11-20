/*
 * Created on 11-Aug-2004
 */
package encodingTrees;

import java.util.List;
import treeDataStructure.Node;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import unequalLetterCostCode.EncodingAlphabet;

/**
 * This class implements a text composition facility that has a containment
 * hierarchy that is derived using an Huffman encoding algorithm with k-2 and
 * the passed value for the probability distribution.
 * 
 * @author Melanie Baljko
 */
public class HuffmanUnequalCosts extends EncodingTreeFromSourceSymbolSet {

	public HuffmanUnequalCosts(SourceSymbolSet sourceSymbols,
			Integer encodingAlphabetSize) {
		super(sourceSymbols, encodingAlphabetSize);
	}
	
	@Override
	public String getCreatingClass() {
		return this.getClass().getName();
	}


	@Override
	public Node deriveRootOfEncoding(SourceSymbolSet sourceSymbols,
			Integer encodingAlphabetSize) {
		//
		// ProbabilityDistribution initialProbDistribution = new
		// ProbDist_Venkatagiri99_Hypothesized();
		// sourceSymbols = new SourceSymbolSet(initialProbDistribution);
		// // assignProbabilitiesToCommands(initialProbDistribution, 1);
		// sourceSymbols.sort();
		// sourceSymbols.trimDownToSize(43);
		//
		List<SourceSymbol> sourceSymbolsList = sourceSymbols
				.getSourceSymbolsByRankOrder();
		List<Double> probabilitiesList = sourceSymbols
				.getProbabilitiesByRankOrder();

		EncodingAlphabet encodingAlphabet = new EncodingAlphabet(
				encodingAlphabetSize);

		// System.out.println(sourceSymbolsList);
		// System.out.println(probabilitiesList);

		return EncodingUtilities.encodeHuffmanTreeUnequalCosts(
				probabilitiesList, sourceSymbolsList, encodingAlphabet);
	}

}
