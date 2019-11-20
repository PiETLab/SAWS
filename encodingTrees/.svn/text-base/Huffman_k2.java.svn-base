/*
 * Created on 11-Aug-2004
 */
package encodingTrees;

import java.util.List;
import java.util.Vector;

import buttonLayouts.ButtonLayoutSpecification;

import TreeDataStructure.LeafNode;
import TreeDataStructure.Node;
import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard_FromHuffmanEqualLetterCosts;
import probabilityDistributionsVOCA.ProbDist_Venkatagiri99_Hypothesized;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

/**
 * This class implements a text composition facility that has a containment
 * hierarchy that is derived using an Huffman encoding algorithm with k-2 and
 * the passed value for the probability distribution.
 * 
 * @author Melanie Baljko
 */
public class Huffman_k2 extends EncodingTreeFromSourceSymbolSet {

	private final static int ENCODING_ALPHABET_SIZE = 2;

	public Huffman_k2(SourceSymbolSet sourceSymbols) {
		super(sourceSymbols, ENCODING_ALPHABET_SIZE);
	}
	
	@Override
	public String getCreatingClass() {
		return this.getClass().getName();
	}


	@Override
	public Node deriveRootOfEncoding(SourceSymbolSet sourceSymbols,
			Integer encodingAlphabetSize) {
		// this.constructRowColEncodingFromKeyboardLayout(kb);

		List<Node> allNodes = new Vector<Node>();
		for (SourceSymbol but : sourceSymbols.getAllSourceSymbols()) {
			allNodes.add(new LeafNode(but));
			// System.out.println(but.getProbabilityOfOccurrence());
		}
		return EncodingUtilities.huffmanEqualLetterCosts(2, allNodes);
	}

}
