/*
 * Created on 9-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package encodingTrees.obsolete;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import customGUIComponentsISF.JIndirectSelectionButton;

import encodingTrees.AbstractEncodingTree;
import encodingTrees.EncodingTreeWithErrorModel;

import sourceSymbolSet.SourceSymbol;

import TreeDataStructure.LeafNode;
import TreeDataStructure.Node;

/**
 * This class implements a containment hierarchy, which is a tree whose nodes
 * are instances of Node (either InternalNode or LeafNode).
 * 
 * @author Melanie Baljko
 */
/**
 * @author mb
 * 
 */
/**
 * @author mb
 * 
 */
/**
 * @author mb
 * 
 */
public class MyEncodingTreeWithErrorSupport extends AbstractEncodingTree implements
		EncodingTreeWithErrorModel {

	private Map<SourceSymbol, Double> meanCosts = new HashMap<SourceSymbol, Double>();

	public MyEncodingTreeWithErrorSupport(Node _root) {
		super(_root);
	}

	public double getConditionalProbability(LeafNode selectedNode,
			LeafNode targetNode) {
		SourceSymbol selected = selectedNode.getSelectionGroup().getFirst();
		SourceSymbol target = targetNode.getSelectionGroup().getFirst();

		return selected.getConditionalProbability(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see encodingPackage.EncodingTreeWithErrorModel#populate(double, double)
	 */
	public void populate(double probFollowCorrectEdge,
			double probFollowIncorrectEdge) {
		List<SourceSymbol> allSourceSymbols = super.getRoot()
				.getSelectionGroup().extractMembers();
		for (SourceSymbol goal : allSourceSymbols) {
			this.getRoot().propogateConditionalProbabilities(goal,
					probFollowCorrectEdge, probFollowIncorrectEdge);
		}
		this.deriveMeanCosts();
	}

	/**
	 * This method populates the map that is used to represent the mean cost for
	 * each of the possible source symbols
	 * 
	 * The mean cost of symbol \sigma_i is given by:
	 * 
	 * 
	 * p_\gamma=Pr(w_k|w_i)==> meanProbOfIncorrectSelection
	 * 
	 * 1-p_\gamma=Pr(w_i|w_i)=probOfCorrectSelection
	 * 
	 * 1-p_\epsilon=Pr(DEL|DEL)=1; p_\delta=Pr(DEL|w_i)=0;
	 * p_\epsilon=Pr(w_i|DEL)=0
	 * 
	 * a=cost(w_i|w_i), b=cost(w_j|w_i), e=cost(DEL|DEL)
	 * 
	 * cost(w_i) = \frac{1-p_\gamma-p_\delta}{1-p_\gamma-2p_\delta}a +
	 * 
	 * \frac{p_\gamma}{1-p_\gamma-2p_\delta}b +
	 * 
	 * \frac{p_\epsilon p_\gamma}{(1-2p_\epsilon)(1-p_\gamma-2p_\delta)}c +
	 * 
	 * \frac{p_\delta}{1-p_\gamma-2p_\delta}d+
	 * 
	 * \frac{(1-p_\epsilon)p_\gamma}{(1-2p_\epsilon)(1-p_\gamma-2p_\delta)}e =
	 * \frac{1-p_\gamma}{1-p_\gamma}a +
	 * 
	 * \frac{p_\gamma}{1-p_\gamma}b +
	 * 
	 * \frac{p_\gamma}{(1-p_\gamma)}e
	 * 
	 */
	private void deriveMeanCosts() {

		boolean IS_VERBOSE = false;
		List<SourceSymbol> allSourceSymbols = super.getRoot()
				.getSelectionGroup().extractMembers();
		for (SourceSymbol targetSymbol : allSourceSymbols) {
			if (IS_VERBOSE)
				System.out.print("Details for: "
						+ targetSymbol
						+ "\t"
						+ this.getCode().getCodeWordForSelectable(targetSymbol)
								.toStringNumeric() + "\n");

			double aTerm = this.cost(targetSymbol);
			if (IS_VERBOSE)
				System.out.println("aTerm:\t" + aTerm);

			// over all symbols \neq goal
			// find the mean probability that a symbol other than the goal was
			// transmitted. Find the mean cost of the error
			List<Double> allProbs = new Vector<Double>();
			List<Double> allCosts = new Vector<Double>();
			for (SourceSymbol transmittedSymbol : allSourceSymbols) {
				if ((transmittedSymbol == targetSymbol)
						|| transmittedSymbol == JIndirectSelectionButton.VK_DEL) {
				} else {

					double gamma = this.getConditionalProbability(
							transmittedSymbol, targetSymbol);
					allProbs.add(gamma);
					double cost = this.cost(transmittedSymbol);
					allCosts.add(cost);
				}
			}
			if (IS_VERBOSE)
				System.out.println(allProbs);
			if (IS_VERBOSE)
				System.out.println(allCosts);

			double probOfCorrectSelection = this.getConditionalProbability(
					targetSymbol, targetSymbol);
			// Does it make sense for this to be zero??? No
			double meanProbOfIncorrectSelection = deriveMean(allProbs);
			double b = deriveWeightedMean(allCosts, allProbs); // this should
			// be mean
			// weighted
			// by probs
			double bTerm = (meanProbOfIncorrectSelection / probOfCorrectSelection)
					* b;

			if (IS_VERBOSE)
				System.out.println("bTerm:\t" + bTerm
						+ "\tmeanProbOfIncorrectSelection: "
						+ meanProbOfIncorrectSelection
						+ "\tprobOfCorrectSelection: " + probOfCorrectSelection
						+ "\tmeanCost: " + b);

			double e = this.cost(JIndirectSelectionButton.VK_DEL); // cost of selecting del
			double eTerm = (meanProbOfIncorrectSelection / probOfCorrectSelection)
					* e;
			if (IS_VERBOSE)
				System.out.println("eTerm:\t" + eTerm);

			double meanCost = aTerm + bTerm + eTerm;

			if (IS_VERBOSE)
				System.out.println("cost:\t" + meanCost);
			getMeanCosts().put(targetSymbol, meanCost);
		}
	}

	/**
	 * returns the sum of all the conditional probabilities of reaching all
	 * leaves, given sourceSymbol as the target
	 * 
	 * @param targetSymbol
	 * @return
	 */
	public double sumConditionalProbabilities(SourceSymbol targetSymbol) {
		double sum = 0;
		List<SourceSymbol> allSourceSymbols = super.getRoot()
				.getSelectionGroup().extractMembers();
		for (SourceSymbol transmittedSymbol : allSourceSymbols) {
			// Node transmittedSymbolNode = super.root
			// .getChildLeafByContents(transmittedSymbol);
			// double
			// find the path of nodes from root to transmitted
			sum += this.getConditionalProbability(transmittedSymbol,
					targetSymbol);
		}
		return sum;
	}

	public String toStringCheckConditionalProbabilities() {
		StringBuffer buf = new StringBuffer();
		List<SourceSymbol> allSourceSymbols = super.getRoot()
				.getSelectionGroup().extractMembers();
		for (SourceSymbol goalSymbol : allSourceSymbols) {
			buf.append("Sum over all i, Pr( i | "
					+ goalSymbol.toStringOneChar() + ") = "
					+ sumConditionalProbabilities(goalSymbol) + "\n");

		}
		return buf.toString();
	}

	public String toStringConditionalProbabilities() {
		StringBuffer buf = new StringBuffer();
		List<SourceSymbol> allSourceSymbols = super.getRoot()
				.getSelectionGroup().extractMembers();
		for (SourceSymbol transmitted : allSourceSymbols) {
			for (SourceSymbol goal : allSourceSymbols) {
				buf.append("Pr(" + transmitted.toStringOneChar() + "|"
						+ goal.toStringOneChar() + ") = "
						+ this.getConditionalProbability(transmitted, goal)
						+ "\n");
			}
		}
		return buf.toString();
	}

	/**
	 * This method returns the cost of this encoding tree, as derived as the
	 * weighted mean cost of the each of the individual source symbols
	 * 
	 */
	public double costWithSimplifyingAssumption() {
		double cost = 0;

		// System.out.println(getMeanCosts());

		List<SourceSymbol> allSourceSymbols = super.getRoot()
				.getSelectionGroup().extractMembers();
		for (SourceSymbol sourceSymbol : allSourceSymbols) {
			Node sourceSymbolNode = super.root
					.getChildLeafByContents(sourceSymbol);
			// System.out.println(sourceSymbol + "\t"
			// + getMeanCosts().get(sourceSymbol) + "\t"
			// + sourceSymbolNode.getProbabilityofOccurrence());
			cost += sourceSymbolNode.getProbabilityofOccurrence()
					* getMeanCosts().get(sourceSymbol);
		}
		return cost;
	}

	private double deriveMean(List<Double> allValues) {
		if (allValues.isEmpty()) {
			return 0;
		}
		double numer = 0;
		for (Double d : allValues) {
			numer += d;
		}
		return numer / allValues.size();
	}

	/**
	 * @param allValues
	 *            values for which mean is desired
	 * @param weights
	 *            weights for mean, need not sum to 1, but size equal to
	 *            allValues
	 * @return
	 */
	private double deriveWeightedMean(List<Double> allValues,
			List<Double> weights) {
		if (allValues.isEmpty()) {
			return 0;
		}
		double weightedMean = 0;
		double denom = 0;

		for (int i = 0; i < allValues.size(); i++) {
			weightedMean += allValues.get(i) * weights.get(i);
			denom += weights.get(i);
		}
		if (denom == 0) {
			return 0;
		}
		return weightedMean / denom;
	}

	private Map<SourceSymbol, Double> getMeanCosts() {
		return meanCosts;
	}
}
