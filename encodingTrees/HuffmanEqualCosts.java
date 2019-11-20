/*
 * Created on 11-Aug-2004
 */
package encodingTrees;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import treeDataStructure.LeafNode;
import treeDataStructure.Node;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;

/**
 * This class implements a text composition facility that has a containment
 * hierarchy that is derived using an Huffman encoding algorithm with k-2 and
 * the passed value for the probability distribution.
 * 
 * @author Melanie Baljko
 */
public class HuffmanEqualCosts extends EncodingTreeFromSourceSymbolSet {

	public HuffmanEqualCosts(SourceSymbolSet sourceSymbols,
			Integer encodingAlphabetSize) {
		super(sourceSymbols, encodingAlphabetSize);
	}

	@Override
	public Node deriveRootOfEncoding(SourceSymbolSet sourceSymbols,
			Integer encodingAlphabetSize) {
		if (IS_VERBOSE)
			System.out.println("***********************");
		List<Node> allNodes = new Vector<Node>();
		for (SourceSymbol but : sourceSymbols.getAllSourceSymbols()) {
			allNodes.add(new LeafNode(but));
			if (IS_VERBOSE) {
				System.out.println(but.getMarginalProbability());
			}
		}
		if (IS_VERBOSE) {
			System.out.println("all nodes: " + allNodes.size());
			System.out.println("all nodes: " + allNodes.toString());
			System.out.println("***********************");
		}

		// System.out.println("x");
		return EncodingUtilities.huffmanEqualLetterCosts(encodingAlphabetSize,
				allNodes);
		// super.setEncodingTree(new AbstractEncodingTree(EncodingUtilities
		// .huffmanEqualLetterCosts(encodingAlphabetSize, allNodes)));
	}

	@Override
	public String getCreatingClass() {
		return this.getClass().getName();
	}

}
