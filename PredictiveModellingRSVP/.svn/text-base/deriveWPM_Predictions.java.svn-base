package PredictiveModellingRSVP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Vector;

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

public class deriveWPM_Predictions {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		List<TraversableEncodingTree> ets = new Vector<TraversableEncodingTree>();

		String mannerOfCreation;
		String sourceSymbolSet;
		String fileName;

		mannerOfCreation = "encodingTrees.EncodingTreeFromMorseCode";
		sourceSymbolSet = "sourceSymbolSet." + "LettersPeriodCommaSpace";
		fileName = "ET-" + mannerOfCreation + "." + sourceSymbolSet + ".obj";
		ets
				.add(Helper.getFromFile(fileName, sourceSymbolSet,
						mannerOfCreation));

		mannerOfCreation = "encodingTrees.HuffmanEqualCosts";
		sourceSymbolSet = "sourceSymbolSet." + "LettersPeriodCommaSpace";
		fileName = "ET-" + mannerOfCreation + "." + sourceSymbolSet + ".obj";
		ets
				.add(Helper.getFromFile(fileName, sourceSymbolSet,
						mannerOfCreation));

		mannerOfCreation = "encodingTrees.HuffmanUnequalCosts";
		sourceSymbolSet = "sourceSymbolSet." + "LettersPeriodCommaSpace";
		fileName = "ET-" + mannerOfCreation + "." + sourceSymbolSet + ".obj";
		ets
				.add(Helper.getFromFile(fileName, sourceSymbolSet,
						mannerOfCreation));

		UserModel um = new UserModel();
		System.out
				.println("#Predicted WPM upper bound, for various dwell times (shown in second column)");

		for (TraversableEncodingTree et : ets) {

			for (int i = 500; i <= 2500; i = i + 500) {
				um.setDwellTime(i);
				um.setReactionTime(250);

				System.out.println(et.getMannerOfCreationIdentifier() + "\t"
						+ i + "\t" + et.getCode().getPredictedWPMUpperBound(um)
						// +
						// et.getCode().getMeanTimeToTransmitSourceSymbolLowerBound(um)
						+ "\t");
			}
			System.out.println("\n");

		}

	}
}
