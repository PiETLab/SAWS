package PredictiveModellingRSVP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;
import treeDataStructure.InternalNode;
import treeDataStructure.Node;
import driverApplications_ISF.LaTeXHelper;
import PredictionModellingISF.UserModel;
import encodingTrees.CodeWord;
import encodingTrees.EncodingTree;
import encodingTrees.EncodingTreeFromFileObjectNode;
import encodingTrees.EncodingTreeFromMorseCode;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

public class retrieveSavedHuffmanUC {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		String mannerOfCreation = "encodingTrees.HuffmanEqualCosts";
		String sourceSymbolSet = "sourceSymbolSet."
				+ "CharactersPeriodCommaSpace";

		SourceSymbolSetGenerator sssGen = new SourceSymbolSetGenerator();
		SourceSymbolSet sourceSymbols;
		sourceSymbols = sssGen.createInstance(sourceSymbolSet);
		int encodingAlphabetSize = 4;

		TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();

		// this takes 1257251 sec or approx 21 minutes to generate
		TraversableEncodingTree encodingTree = tetGen.createInstance(
				mannerOfCreation, sourceSymbols, new Integer(
						encodingAlphabetSize));
		System.out.println(encodingTree.getMannerOfCreationIdentifier());
		System.out.println(encodingTree.getSourceSymbolSetIdentifier());

		String fileName = "ET-" + encodingTree.getMannerOfCreationIdentifier()
				+ ".obj";
		Helper.saveInFile(encodingTree, "obj" + File.separator + fileName);

		// String objectFileName1 = "obj" + File.separator
		// + "ET-HuffmanUnequalCosts.obj";
		ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("obj"
				+ File.separator + fileName));

		Node node = (InternalNode) in1.readObject();

		// System.out.println(node.toString());
		// System.out.println(fileName);
		EncodingTree et = new EncodingTreeFromFileObjectNode(node,
				sourceSymbolSet, mannerOfCreation);
		//
		// // this takes 1257251 sec or approx 21 minutes to generate
		encodingTree = new TraversableEncodingTree(et);
		System.out.println(encodingTree.getMannerOfCreationIdentifier());
		System.out.println(encodingTree.getSourceSymbolSetIdentifier());

		UserModel um1 = new UserModel();
		um1.setDwellTime(1000);
		um1.setReactionTime(200);

		String datasetIdentifier = "2.0";
		System.out.println(Helper.getDataSet(encodingTree, um1,
				datasetIdentifier));
	}
}
