package encodingTrees;

import treeDataStructure.Node;

public abstract class ManualEncodingTreeSpecification {

	public abstract Node getRootOfEncodingTree();

	public String getIdentifier() {
		return this.getClass().getName(); // "ManualEncodingTreeSpecification";
	}

}
