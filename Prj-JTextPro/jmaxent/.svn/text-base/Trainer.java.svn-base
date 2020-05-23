/*
 Copyright (C) 2006 by
 
 Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package jmaxent;

import java.io.*;

public class Trainer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8526566124730939132L;

	public static void main(String[] args) throws IOException {
		if (!checkArgs(args)) {
			displayHelp();
			return;
		}

		String modelDir = args[2];
		boolean isAll = (args[0].compareToIgnoreCase("-all") == 0);
		boolean isTrn = (args[0].compareToIgnoreCase("-trn") == 0);
		boolean isTst = (args[0].compareToIgnoreCase("-tst") == 0);

		// create option object
		Option option = new Option(modelDir);
		option.readOptions();

		Data data = null;
		Dictionary dict = null;
		FeatureGen feaGen = null;
		Train train = null;
		Inference inference = null;
		Evaluation evaluation = null;
		Model model = null;

		PrintWriter foutModel = null;
		BufferedReader finModel = null;

		if (isAll) {

			// both training and testing
			PrintWriter flog = option.openTrainLogFile();
			if (flog == null) {
				System.out.println("Couldn't create training log file");
				return;
			}

			foutModel = option.createModelFile();
			if (foutModel == null) {
				System.out.println("Couldn't create model file");
				return;
			}

			data = new Data(option);
			data.readTrnData(option.modelDir + File.separator
					+ option.trainDataFile);
			data.readTstData(option.modelDir + File.separator
					+ option.testDataFile);

			dict = new Dictionary(option, data);
			dict.generateDict();

			feaGen = new FeatureGen(option, data, dict);
			feaGen.generateFeatures();

			data.writeCpMaps(dict, foutModel);
			data.writeLbMaps(foutModel);

			train = new Train();
			inference = new Inference();
			evaluation = new Evaluation();

			model = new Model(option, data, dict, feaGen, train, inference,
					evaluation);
			model.doTrain(flog);

			model.doInference(model.data.tstData);
			model.evaluation.evaluate(flog);

			dict.writeDict(foutModel);
			feaGen.writeFeatures(foutModel);

			foutModel.close();
		}

		if (isTrn) {
			// training only

			PrintWriter flog = option.openTrainLogFile();
			if (flog == null) {
				System.out.println("Couldn't create training log file");
				return;
			}

			foutModel = option.createModelFile();
			if (foutModel == null) {
				System.out.println("Couldn't create model file");
				return;
			}

			data = new Data(option);
			data.readTrnData(option.modelDir + File.separator
					+ option.trainDataFile);

			dict = new Dictionary(option, data);
			dict.generateDict();

			feaGen = new FeatureGen(option, data, dict);
			feaGen.generateFeatures();

			data.writeCpMaps(dict, foutModel);
			data.writeLbMaps(foutModel);

			train = new Train();

			model = new Model(option, data, dict, feaGen, train, null, null);
			model.doTrain(flog);

			dict.writeDict(foutModel);
			feaGen.writeFeatures(foutModel);

			foutModel.close();
		}

		if (isTst) {
			// testing only

			finModel = option.openModelFile();
			if (finModel == null) {
				System.out.println("Couldn't open model file");
				return;
			}

			data = new Data(option);
			data.readCpMaps(finModel);
			data.readLbMaps(finModel);
			data.readTstData(option.modelDir + File.separator
					+ option.testDataFile);

			dict = new Dictionary(option, data);
			dict.readDict(finModel);

			feaGen = new FeatureGen(option, data, dict);
			feaGen.readFeatures(finModel);

			inference = new Inference();
			evaluation = new Evaluation();

			model = new Model(option, data, dict, feaGen, null, inference,
					evaluation);

			model.doInference(model.data.tstData);
			model.evaluation.evaluate(null);

			finModel.close();
		}

	} // end of the main method

	public static boolean checkArgs(String[] args) {
		if (args.length < 3) {
			return false;
		}

		if (!(args[0].compareToIgnoreCase("-all") == 0
				|| args[0].compareToIgnoreCase("-trn") == 0 || args[0]
				.compareToIgnoreCase("-tst") == 0)) {
			return false;
		}

		if (args[1].compareToIgnoreCase("-d") != 0) {
			return false;
		}

		return true;
	}

	public static void displayHelp() {
		System.out.println("Usage:");
		System.out.println("\tTrainer -all/-trn/-tst -d <model directory>");
	}
}
