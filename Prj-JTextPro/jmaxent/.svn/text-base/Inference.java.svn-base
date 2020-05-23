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

public class Inference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4758826607334278976L;

	public Model model = null;

	public int numLabels = 0;

	// for classification
	double[] temp = null;

	public Inference() {
		// do nothing
	}

	public void init() {
		numLabels = model.data.numLabels();
		temp = new double[numLabels];
	}

	public void classify(Observation obsr) {
		int i;

		for (i = 0; i < numLabels; i++) {
			temp[i] = 0.0;
		}

		model.feaGen.startScanFeatures(obsr);
		while (model.feaGen.hasNextFeature()) {
			Feature f = model.feaGen.nextFeature();

			temp[f.label] += model.lambda[f.idx] * f.val;
		}

		double max = temp[0];
		int maxLabel = 0;
		for (i = 1; i < numLabels; i++) {
			if (max < temp[i]) {
				max = temp[i];
				maxLabel = i;
			}
		}

		obsr.modelLabel = maxLabel;
	}

	public void doInference(List data) {
		for (int i = 0; i < data.size(); i++) {
			Observation obsr = (Observation) data.get(i);

			classify(obsr);
		}
	}

} // end of class Inference

