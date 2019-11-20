package PredictiveModellingRSVP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;
import treeDataStructure.InternalNode;
import treeDataStructure.Node;
import driverApplications_ISF.LaTeXHelper;

import PredictionModellingISF.UserModel;
import encodingAlphabetsISF.AscendingCostEncodingAlphabet;
import encodingAlphabetsISF.EncodingSymbolAlphabet;
import encodingTrees.EncodingTree;
import encodingTrees.EncodingTreeFromFileObjectNode;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

public class deriveMorseCodeBaselinePredictions {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		final boolean shouldRetrieveFromFile = false;
		String mannerOfCreation = "encodingTrees.EncodingTreeFromMorseCode";
		String sourceSymbolSet = "sourceSymbolSet." + "LettersPeriodCommaSpace";

		TraversableEncodingTree encodingTree;
		String fileName = "ET-" + mannerOfCreation + "." + sourceSymbolSet
				+ ".obj";
		if (shouldRetrieveFromFile) {

			System.out.println("Reading File: " + fileName);
			// String objectFileName1 = "obj" + File.separator
			// + "ET-HuffmanUnequalCosts.obj";
			ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(
					"obj" + File.separator + fileName));
			Node node = (InternalNode) in1.readObject();

			// System.out.println(node.toString());
			// System.out.println(fileName);
			EncodingTree et = new EncodingTreeFromFileObjectNode(node,
					sourceSymbolSet, mannerOfCreation);

			encodingTree = new TraversableEncodingTree(et);

		} else {
			System.out.println("Creating File: " + fileName);

			TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();

			encodingTree = tetGen.createInstance(mannerOfCreation);

			// sourceSymbols, new Integer(encodingAlphabetSize));
			Helper.saveInFile(encodingTree, "obj" + File.separator + fileName);
		}

		UserModel um1 = new UserModel();
		um1.setDwellTime(1000);
		um1.setReactionTime(200);

		String datasetIdentifier = "1.0";

		System.out.println(Helper.getDataSet(encodingTree, um1,
				datasetIdentifier));

		// to do: printout option show encoding symbols instead of culmulative
		// cost; also rejig order of children by ascending cost.
		boolean SHOW_INTERNAL_EDGES = true;
		boolean SHOW_NONINTERNAL_EDGES = true;
		LaTeXHelper.generateLaTeXFile(encodingTree, false, SHOW_INTERNAL_EDGES,
				SHOW_NONINTERNAL_EDGES);

	}
}
