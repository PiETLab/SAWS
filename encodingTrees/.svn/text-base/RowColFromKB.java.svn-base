/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees;

import java.util.List;

import treeDataStructure.InternalNode;
import treeDataStructure.LeafNode;
import treeDataStructure.Node;
import sourceSymbolSet.SourceSymbol;

/**
 * 
 * @author Melanie Baljko
 */
public class RowColFromKB extends EncodingTreeFromManualSpecification {

	public RowColFromKB(ManualEncodingTreeSpecification kb) {
		super(kb);
	}

	@Override
	public String getCreatingClass() {
		return this.getClass().getName();
	}

	@Override
	public Node deriveRootOfEncoding(ManualEncodingTreeSpecification kb) {
		// InternalNode root = new InternalNode();
		// for (List<SourceSymbol> currRow : kb.getRootOfEncodingTree()) {
		// InternalNode rootChild = new InternalNode();
		// for (SourceSymbol s : currRow) {
		// Node tmp = new LeafNode(s);
		// rootChild.addChild(tmp);
		// }
		// // test whether this row contains just one button
		// // if so, collapse the level and make the button directly a child of
		// // the root
		// if (rootChild.getChildren().size() == 1) {
		// root.addChild(new LeafNode(rootChild.getFirstChild()
		// .getNodeSelectionGroup().getFirst()));
		// } else {
		// root.addChild(rootChild);
		// }
		// }
		return kb.getRootOfEncodingTree();
	}
}
