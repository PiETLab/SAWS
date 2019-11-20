package PredictiveModellingRSVP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import customGUIComponentsISF.JIndirectSelectionButton;
import customGUIComponentsISF.JVirtualKeyboardDummyButton;

import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;
import treeDataStructure.InternalNode;
import treeDataStructure.Node;

import driverApplications_ISF.LaTeXHelper;
import PredictionModellingISF.UserModel;
import encodingAlphabetsISF.AscendingCostEncodingAlphabet;
import encodingAlphabetsISF.EncodingSymbolAlphabet;
import encodingTrees.CodeWord;
import encodingTrees.EncodingTree;
import encodingTrees.EncodingTreeFromFileObjectNode;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

public class Helper {

	public static String getDataSet(TraversableEncodingTree encodingTree,
			UserModel um1, String datasetIdentifier) {
		return getDataSet(encodingTree, um1, datasetIdentifier, null);
	}

	public static String getDataSet(TraversableEncodingTree encodingTree,
			UserModel um1, String datasetIdentifier,
			EncodingSymbolAlphabet alphabet) {

		StringBuffer buf = new StringBuffer();
		buf.append("# DATASET FOR:"
				+ encodingTree.getMannerOfCreationIdentifier());
		buf.append("\n");
		buf.append("# DATASET FOR:"
				+ encodingTree.getSourceSymbolSetIdentifier());
		buf.append("\n");

		double meanTimeU1 = 0.0;
		double meanCost = 0.0;
		buf.append("# DATA FOR EACH CODEWORD \n");
		buf
				.append("#xaxis-val\tsourcesym \tprob \tcost(opc)\tLB(usermodel)(time-to-enter)\tcodeword as numeric\n");
		for (CodeWord codeword : encodingTree.getCode()
				.getCodeWordsExcludingCodeWordsForFillerSourceSymbols()) {
			// if (!codeword.getSourceSymbol().equals(
			// JIndirectSelectionButton.VK_FILLER)) {
			double prob = codeword.getProbability();
			buf.append(datasetIdentifier + "\t" + codeword.getLHS());
			buf.append("\t" + prob);
			// buf.append("\t" + codeword.getCost());
			if (alphabet == null) {
				buf.append("\t" + codeword.getCost());
				meanCost += prob * codeword.getCost();
			} else {
				buf.append("\t" + codeword.getCost(alphabet));
				meanCost += prob * codeword.getCost(alphabet);
			}

			buf.append("\t" + codeword.getTimeToTransmitSourceSymbolLowerBound(um1));
			meanTimeU1 += (prob * codeword.getTimeToTransmitSourceSymbolLowerBound(um1));
			buf.append("\t" + codeword.toStringNumeric());
			buf.append("\n");
			// }
		}

		buf.append("# Number of source symbols encoded: "
				+ encodingTree.getCode()
						.getCodeWordsExcludingCodeWordsForFillerSourceSymbols()
						.size() + "\n");

		buf
				.append("# Lower bound on time-to-enter per character (manually derived): "
						+ meanTimeU1 + "\n");
		buf.append("# Lower bound on time-to-enter per character: "
				+ encodingTree.getCode().getMeanTimeToTransmitSourceSymbolLowerBound(um1)
				+ "\n");

		buf.append("# Upper bound on wpm: "
				+ encodingTree.getCode().getPredictedWPMUpperBound(um1) + "\n");

		if (alphabet == null) {
			buf.append("# MEAN ENCODING COST (OPC): "
					+ encodingTree.getCode().getMeanEncodingCost());
			buf.append("\n");
		} else {
			buf
					.append("# MEAN ENCODING COST (OPC), using provided alternative encoding alphabet: "
							+ encodingTree.getCode().getMeanEncodingCost(
									alphabet));
			buf.append("\n");
		}

		return buf.toString();
	}

	public static void saveInFile(TraversableEncodingTree encodingTree,
			String fullFileName) {

		FileOutputStream outFile;

		try {
			outFile = new FileOutputStream(fullFileName);
			System.out.println("# " + "Creating file output stream: "
					+ fullFileName);

			ObjectOutputStream s = new ObjectOutputStream(outFile);
			s.writeObject(encodingTree.getRoot());
			s.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static TraversableEncodingTree getFromFile(String fileName,
			String sourceSymbolSet, String mannerOfCreation)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		System.out.println("Reading File: " + fileName);
		// String objectFileName1 = "obj" + File.separator
		// + "ET-HuffmanUnequalCosts.obj";
		ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("obj"
				+ File.separator + fileName));
		Node node = (InternalNode) in1.readObject();

		// System.out.println(node.toString());
		// System.out.println(fileName);
		EncodingTree et = new EncodingTreeFromFileObjectNode(node,
				sourceSymbolSet, mannerOfCreation);

		return new TraversableEncodingTree(et);
	}
}
