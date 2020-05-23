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
import java.util.*;

public class Option implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1078039311890319620L;

	// model directory
	public String modelDir = ".";

	// model file
	public String modelFile = "model.txt";

	public static String modelSeparator = "##########";

	// option file
	public String optionFile = "option.txt";

	// training data, testing data file
	public String trainDataFile = "train.labeled";

	public String testDataFile = "test.labeled";

	public static String labelSeparator = "/";

	// training log
	public String trainLogFile = "trainlog.txt";

	public boolean isLogging = true;

	public int numTrainExps = 0; // number of training examples

	public int numTestExps = 0; // number of testing examples

	public int numLabels = 0; // number of class labels

	public int numCps = 0; // number of context predicates

	public int numFeatures = 0; // number of features

	// thresholds for context predicate and feature cut-off
	public int cpRareThreshold = 1;

	public int fRareThreshold = 1;

	// training options
	public int numIterations = 100; // number of training iterations

	public double initLambdaVal = 0.0; // intial value for feature weights

	public double sigmaSquare = 100; // for smoothing

	public double epsForConvergence = 0.0001; // for checking training
												// termination

	public int mForHessian = 7; // for L-BFGS corrections

	public int debugLevel = 1; // control output status information

	// evaluation options
	public boolean evaluateDuringTraining = true; // evaluate during training

	public boolean saveBestModel = true; // save the best model with testing
											// data

	public Option() {
	}

	public Option(String modelDir) {
		if (modelDir.endsWith(File.separator)) {
			this.modelDir = modelDir.substring(0, modelDir.length() - 1);
		} else {
			this.modelDir = modelDir;
		}
	}

	public boolean readOptions() {
		String filename = modelDir + File.separator + optionFile;
		BufferedReader fin = null;
		String line;

		try {
			fin = new BufferedReader(new FileReader(filename));

			System.out.println("Reading options ...");

			// read option lines
			while ((line = fin.readLine()) != null) {
				String trimLine = line.trim();
				if (trimLine.startsWith("#")) {
					// comment line
					continue;
				}

				StringTokenizer strTok = new StringTokenizer(line, "= \t\r\n");
				int len = strTok.countTokens();
				if (len != 2) {
					// invalid parameter line, ignore it
					continue;
				}

				String strOpt = strTok.nextToken();
				String strVal = strTok.nextToken();

				if (strOpt.compareToIgnoreCase("trainDataFile") == 0) {
					trainDataFile = strVal;

				} else if (strOpt.compareToIgnoreCase("testDataFile") == 0) {
					testDataFile = strVal;

				} else if (strOpt.compareToIgnoreCase("isLogging") == 0) {
					if (!(strVal.compareToIgnoreCase("true") == 0 || strVal
							.compareToIgnoreCase("false") == 0)) {
						continue;
					}
					isLogging = Boolean.valueOf(strVal).booleanValue();

				} else if (strOpt.compareToIgnoreCase("cpRareThreshold") == 0) {
					int numTemp = Integer.parseInt(strVal);
					cpRareThreshold = numTemp;

				} else if (strOpt.compareToIgnoreCase("fRareThreshold") == 0) {
					int numTemp = Integer.parseInt(strVal);
					fRareThreshold = numTemp;

				} else if (strOpt.compareToIgnoreCase("numIterations") == 0) {
					int numTemp = Integer.parseInt(strVal);
					numIterations = numTemp;

				} else if (strOpt.compareToIgnoreCase("initLambdaVal") == 0) {
					double numTemp = Double.parseDouble(strVal);
					initLambdaVal = numTemp;

				} else if (strOpt.compareToIgnoreCase("sigmaSquare") == 0) {
					double numTemp = Double.parseDouble(strVal);
					sigmaSquare = numTemp;

				} else if (strOpt.compareToIgnoreCase("epsForConvergence") == 0) {
					double numTemp = Double.parseDouble(strVal);
					epsForConvergence = numTemp;

				} else if (strOpt.compareToIgnoreCase("mForHessian") == 0) {
					int numTemp = Integer.parseInt(strVal);
					mForHessian = numTemp;

				} else if (strOpt.compareToIgnoreCase("evaluateDuringTraining") == 0) {
					if (!(strVal.compareToIgnoreCase("true") == 0 || strVal
							.compareToIgnoreCase("false") == 0)) {
						continue;
					}
					evaluateDuringTraining = Boolean.valueOf(strVal)
							.booleanValue();

				} else if (strOpt.compareToIgnoreCase("saveBestModel") == 0) {
					if (!(strVal.compareToIgnoreCase("true") == 0 || strVal
							.compareToIgnoreCase("false") == 0)) {
						continue;
					}
					saveBestModel = Boolean.valueOf(strVal).booleanValue();

				} else {
					// for future use
				}

			}

			System.out.println("Reading options completed!");

		} catch (IOException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	public PrintWriter openTrainLogFile() {
		String filename = modelDir + File.separator + trainLogFile;
		PrintWriter fout = null;

		try {
			fout = new PrintWriter(new FileWriter(filename));

		} catch (IOException e) {
			System.out.println(e.toString());
			return null;
		}

		return fout;
	}

	public BufferedReader openModelFile() {
		String filename = modelDir + File.separator + modelFile;
		BufferedReader fin = null;

		try {
			fin = new BufferedReader(new FileReader(filename));

		} catch (IOException e) {
			System.out.println(e.toString());
			return null;
		}

		return fin;
	}

	public PrintWriter createModelFile() {
		String filename = modelDir + File.separator + modelFile;
		PrintWriter fout = null;

		try {
			fout = new PrintWriter(new FileWriter(filename));

		} catch (IOException e) {
			System.out.println(e.toString());
			return null;
		}

		return fout;
	}

	public void writeOptions(PrintWriter fout) {
		fout.println("OPTION VALUES:");
		fout.println("==============");
		fout.println("Model directory: " + modelDir);
		fout.println("Model file: " + modelFile);
		fout.println("Option file: " + optionFile);
		fout.println("Training log file: " + trainLogFile + " (this one)");
		fout.println("Training data file: " + trainDataFile);
		fout.println("Testing data file: " + testDataFile);
		fout.println("Number of training examples "
				+ Integer.toString(numTrainExps));
		fout.println("Number of testing examples "
				+ Integer.toString(numTestExps));
		fout.println("Number of class labels: " + Integer.toString(numLabels));
		fout.println("Number of context predicates: "
				+ Integer.toString(numCps));
		fout.println("Number of features: " + Integer.toString(numFeatures));
		fout.println("Rare threshold for context predicates: "
				+ Integer.toString(cpRareThreshold));
		fout.println("Rare threshold for features: "
				+ Integer.toString(fRareThreshold));
		fout.println("Number of training iterations: "
				+ Integer.toString(numIterations));
		fout.println("Initial value of feature weights: "
				+ Double.toString(initLambdaVal));
		fout.println("Sigma square: " + Double.toString(sigmaSquare));
		fout.println("Epsilon for convergence: "
				+ Double.toString(epsForConvergence));
		fout.println("Number of corrections in L-BFGS: "
				+ Integer.toString(mForHessian));
		if (evaluateDuringTraining) {
			fout.println("Evaluation during training: true");
		} else {
			fout.println("Evaluation during training: false");
		}
		if (saveBestModel) {
			fout.println("Save the best model towards testing data: true");
		} else {
			fout.println("Save the best model towards testing data: false");
		}
		fout.println();
	}

} // end of class Option

