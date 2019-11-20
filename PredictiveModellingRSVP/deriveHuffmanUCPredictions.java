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
import PredictionModellingISF.UserModel;
import driverApplications_ISF.LaTeXHelper;
import encodingAlphabetsISF.AscendingCostEncodingAlphabet;
import encodingAlphabetsISF.EncodingSymbolAlphabet;
import encodingTrees.EncodingTree;
import encodingTrees.EncodingTreeFromFileObjectNode;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

public class deriveHuffmanUCPredictions {


	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		final boolean SHOULD_RETRIEVE_FROM_FILE = true;
		
		
		////////////////
		String mannerOfCreation = "encodingTrees.HuffmanUnequalCosts";
		String sourceSymbolSet = "sourceSymbolSet."
				+ "LettersPeriodCommaSpace";
		String datasetIdentifier = "2.0";
		
		TraversableEncodingTree encodingTree;
		String fileName = "ET-" + mannerOfCreation + "." + sourceSymbolSet
				+ ".obj";

		if (SHOULD_RETRIEVE_FROM_FILE) {

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
			SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
			SourceSymbolSet sourceSymbols;
			sourceSymbols = sssGen.createInstance(sourceSymbolSet);
			int encodingAlphabetSize = 4;

			TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();

			encodingTree = tetGen.createInstance(mannerOfCreation,
					sourceSymbols, new Integer(encodingAlphabetSize));
			Helper.saveInFile(encodingTree, "obj" + File.separator + fileName);
		}

		UserModel um1 = new UserModel();
		um1.setDwellTime(1000);
		um1.setReactionTime(200);

		EncodingSymbolAlphabet alph = new AscendingCostEncodingAlphabet(4);

		System.out.println(Helper.getDataSet(encodingTree, um1,
				datasetIdentifier, alph));

		// to do: printout option show encoding symbols instead of culmulative
		// cost; also rejig order of children by ascending cost.
		boolean SHOW_INTERNAL_EDGES = true;
		boolean SHOW_NONINTERNAL_EDGES = true;
		LaTeXHelper.generateLaTeXFile(encodingTree, false, SHOW_INTERNAL_EDGES,
				SHOW_NONINTERNAL_EDGES);

	}
}
