package encodingTrees;

import buttonLayouts.ButtonLayoutSpecification;
import TreeDataStructure.Node;

public class EncodingTreeFromFileObjectNode extends AbstractEncodingTree {

	private String sourceSymbolSet;
	private String mannerOfCreation;

	public EncodingTreeFromFileObjectNode(Node node, String sourceSymbolSet,
			String mannerOfCreation) {
		super();
		this.sourceSymbolSet = sourceSymbolSet;
		this.mannerOfCreation = mannerOfCreation;
		this.setRoot(node);
		// this.setSourceSymbolSetIdentifier(kb.getClass().getName());
		this.registerEndOfConstructingTime();
		// this.reset();
	}

	@Override
	public String getSourceSymbolSetIdentifier() {
		return sourceSymbolSet;
	}

	@Override
	public String getCreatingClass() {
		return mannerOfCreation;
	}

}
