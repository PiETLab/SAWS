package encodingTrees;

import TreeDataStructure.LeafNode;

/**
 * This interface defines an error model over an encoding tree
 * 
 * @author mb
 * 
 */
public interface EncodingTreeWithErrorModel extends EncodingTree {

	/**
	 * 
	 * 
	 * @param selectedNode
	 * @param targetNode
	 * @return the likelihood of the user selecting selectedNode, given that
	 *         targetNode was the target
	 */
	public double getConditionalProbability(LeafNode selectedNode,
			LeafNode targetNode);

	/**
	 * Populates the error model so that likelihood of following correct edge is
	 * probFollowCorrectEdge and likelihood of incorrect edge is
	 * probFollowIncorrectEdge (if more than one incorrect edge, the error is
	 * distributed uniformly
	 * 
	 * @param probFollowCorrectEdge
	 * @param probFollowIncorrectEdge
	 */
	public void populate(double probFollowCorrectEdge,
			double probFollowIncorrectEdge);

	public String toStringConditionalProbabilities();

	public String toStringCheckConditionalProbabilities();

	public double costWithSimplifyingAssumption();

}
