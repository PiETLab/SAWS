package encodingTrees;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import sourceSymbolSet.LettersPeriodCommaSpace;
import sourceSymbolSet.SourceSymbol;
import sourceSymbolSet.SourceSymbolSet;
import TreeDataStructure.InternalNode;
import TreeDataStructure.LeafNode;
import TreeDataStructure.Node;

public class EncodingTreeFromMorseCode extends AbstractEncodingTree implements
		Serializable {

	private SourceSymbolSet sourceSymbolSet = new LettersPeriodCommaSpace();

	private static final long serialVersionUID = -6556343406373442439L;
	private MorseCodeLookupTable morseCodeLookup;

	public EncodingTreeFromMorseCode() {
		super();
		this.morseCodeLookup = new MorseCodeLookupTable();
		this.setRoot(deriveRootOfEncoding());
		this.registerEndOfConstructingTime();
	}

	public Node deriveRootOfEncoding() {
		Node root = new InternalNode();
		List<SourceSymbol> morse = morseCodeLookup
				.getSourceSymbolsInOrderSortedByModifiedLexicographicalOnMorseCodeword();
		// System.out.println("morse size: " + morse.size());
		// System.out.println("ss size: " + sourceSymbolSet.size());
		for (SourceSymbol sym : morseCodeLookup
				.getSourceSymbolsInOrderSortedByModifiedLexicographicalOnMorseCodeword()) {

			if (sourceSymbolSet.getAllSourceSymbols().contains(sym)) {
				// System.out.println("Adding source sym: " + sym);
				List<String> cw = listify(morseCodeLookup.getCode(sym));
				this.addCodewordNodesToTree(cw, sym, root);
			}

			// nothing implemented to ensure that all source symbols in source
			// symbol set are in fact contained inthe mode codeword lookup

		}
		return root;
	}

	private List<String> listify(String codeword) {
		List<String> list = new Vector<String>();
		for (int i = 0; i < codeword.length(); i++) {
			list.add(codeword.substring(i, i + 1));
		}
		return list;
	}

	@Override
	public String getSourceSymbolSetIdentifier() {
		return sourceSymbolSet.getClass().getName();
	}

	/**
	 * assumes an encoding alphabet with unique, integer costs
	 * 
	 * each element of the list is a digit which represents the cost of the
	 * encoding symbol
	 * 
	 * SIDEEFFECT - the passed encoding tree gets modified to include the nodes
	 * corresponding to the codeword
	 * 
	 * @param codeword
	 */
	private void addCodewordNodesToTree(List<String> codeword,
			SourceSymbol sourceSymbol, Node encodingTreeRoot) {

		Node currNode = encodingTreeRoot;
		for (String encodingSymbol : codeword) {

			int cost = Integer.parseInt(encodingSymbol);
			if (currNode.isLeaf()) {
				// InternalNode newNode = ((LeafNode) currNode)
				// .ConvertToInternalNode();

				InternalNode newNode = new InternalNode();

				InternalNode parent = (InternalNode) currNode.parent;
				int leafCost = parent.getCost(currNode);
				parent.removeLeafChild((LeafNode) currNode);

				parent.addChild(newNode, leafCost);

				LeafNode ln = new LeafNode(sourceSymbol);
				newNode.addChild(ln, cost);

				// System.out.print("source symbol " + sourceSymbol
				// + ", encoding symbol " + encodingSymbol);
				// System.out.println(", Branching out at leaf: " + currNode + "
				// with cost of " + cost);

				currNode = ln;
				// System.out.println(", Branching out at leaf: " + currNode);

			} else {
				// check whether a child node for this encoding symbols exists
				// already
				Node potentialMatch = getChildWithGivenCost(
						(InternalNode) currNode, cost);
				if (potentialMatch == null) {
					LeafNode ln = new LeafNode(sourceSymbol);
					((InternalNode) currNode).addChild(ln, cost);
					// System.out.print("source symbol " + sourceSymbol
					// + ", encoding symbol " + encodingSymbol);
					// System.out.println(", Adding child to node: " + currNode
					// + " with cost of " + cost);
					currNode = ln;

				} else {
					// System.out.print("source symbol " + sourceSymbol
					// + ", encoding symbol " + encodingSymbol);
					// System.out.println(", node already exists: " + currNode);
					currNode = potentialMatch;
				}
			}
			// System.out.println("current tree\n"
			// + encodingTreeRoot.toStringPlainTextLispStyle(0));
		}
	}

	private Node getChildWithGivenCost(InternalNode node, int cost) {
		Node found = null;
		if (node == null)
			return found;
		for (Node c : node.getChildren()) {
			if (node.getCost(c) == cost) {
				found = c;
				// System.out.println("found " + node);
				break;
			}
		}
		return found;
	}

	@Override
	public String getCreatingClass() {
		return this.getClass().getName();
	}

}
