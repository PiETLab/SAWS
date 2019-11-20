package encodingTrees;

import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetFromLeaves;
import treeDataStructure.Node;

public class EncodingTreeFromManualSpecification extends AbstractEncodingTree {

	private ManualEncodingTreeSpecification kb;
	private SourceSymbolSet sourceSymbolSet;

	public EncodingTreeFromManualSpecification(
			ManualEncodingTreeSpecification kb) {
		super();
		this.kb = kb;
		this.setRoot(deriveRootOfEncoding(kb));
		// this.setSourceSymbolSetIdentifier(kb.getClass().getName());
		this.registerEndOfConstructingTime();
		sourceSymbolSet = new SourceSymbolSetFromLeaves(this.getRoot().getLeaves());
		// this.reset();
	}

	public Node deriveRootOfEncoding(ManualEncodingTreeSpecification kb) {
		return kb.getRootOfEncodingTree();
	}

	@Override
	public String getSourceSymbolSetIdentifier() {
		return kb.getClass().getName();
	}

	@Override
	public String getCreatingClass() {
		// return this.getClass().getName();
		return kb.getClass().getName();
	}

	@Override
	public SourceSymbolSet getSourceSymbolSet() {
		return sourceSymbolSet;
	}

}
