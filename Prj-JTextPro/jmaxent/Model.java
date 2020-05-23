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

public class Model implements Serializable  {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1946743792267949429L;
	public Option option = null;
    public Data data = null;
    public Dictionary dict = null;
    public FeatureGen feaGen = null;
    public Train train = null;
    public Inference inference = null;
    public Evaluation evaluation = null;
    
    // feature weight
    double[] lambda = null;
    
    public Model() {	
	// do nothing
    }
    
    public Model(Option option, Data data, Dictionary dict, FeatureGen feaGen,
		Train train, Inference inference, Evaluation evaluation) {
	this.option = option;
	this.data = data;
	this.dict = dict;
	this.feaGen = feaGen;
	this.evaluation = evaluation;

	if (train != null) {
	    this.train = train;
	    this.train.model = this;	    
	    this.train.init();
	}

	if (inference != null) {	
	    this.inference = inference;
	    this.inference.model = this;
	    this.inference.init();
	}
	
	if (evaluation != null) {
	    this.evaluation = evaluation;
	    this.evaluation.model = this;
	    this.evaluation.init();
	}
    }
        
    public void doTrain(PrintWriter fout) {
	if (lambda == null) {
	    lambda = new double[feaGen.numFeatures()];
	}
	
	// call this to train
	train.doTrain(fout);
	
	// call this to update the feature weights
	updateFeatures();
    }
    
    public void updateFeatures() {
	for (int i = 0; i < feaGen.features.size(); i++) {
	    Feature f = (Feature)feaGen.features.get(i);
	    f.wgt = lambda[f.idx];    
	}
    }
    
    public void initInference() {
	if (lambda == null) {
	    lambda = new double[feaGen.numFeatures()];
	    
	    // reading feature weights from the feature list
	    for (int i = 0; i < feaGen.features.size(); i++) {
		Feature f = (Feature)feaGen.features.get(i);
		lambda[f.idx] = f.wgt;    
	    }	    
	}    
    }
    
    public void doInference(List data) {
	if (lambda == null) {
	    lambda = new double[feaGen.numFeatures()];
	    
	    // reading feature weights from the feature list
	    for (int i = 0; i < feaGen.features.size(); i++) {
		Feature f = (Feature)feaGen.features.get(i);
		lambda[f.idx] = f.wgt;    
	    }	    
	}
	
	inference.doInference(data);	
    }
        
} // end of class Model

