package encodingTrees;

import sourceSymbolSet.SourceSymbolSet;
import treeDataStructure.Node;

public abstract class EncodingTreeFromSourceSymbolSet extends
		AbstractEncodingTree {

	private SourceSymbolSet sourceSymbols;
	private Integer encodingAlphabetSizeSpecified;

	protected EncodingTreeFromSourceSymbolSet(SourceSymbolSet sourceSymbols,
			Integer encodingAlphabetSizeSpecified) {
		super();
		this.sourceSymbols = sourceSymbols;
		if (IS_VERBOSE) {
			System.out.println("*****>>>: "
					+ sourceSymbols.getClass().getName());
			System.out.println("*****>>>: " + sourceSymbols.toString());
		}
		this.encodingAlphabetSizeSpecified = encodingAlphabetSizeSpecified;
		this.setRoot(deriveRootOfEncoding(sourceSymbols,
				encodingAlphabetSizeSpecified));
		this.registerEndOfConstructingTime();
	}

	public abstract Node deriveRootOfEncoding(SourceSymbolSet sourceSymbols,
			Integer encodingAlphabetSize);

	@Override
	public String getSourceSymbolSetIdentifier() {
		return sourceSymbols.getClass().getName();
	}

	public SourceSymbolSet getSourceSymbolSet() {
		return sourceSymbols;
	}

}
