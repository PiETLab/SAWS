/*
 Copyright (C) 2006, Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package crftagger;

import java.io.Serializable;
import java.util.*;
import flexcrfs.*;

public class CRFTagger implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311395348180760753L;

	public Prediction predictor = null;

	public FeaGeneration feagen = null;

	public CRFTagger(String modelDir) {
		predictor = new Prediction(modelDir);
		feagen = new FeaGeneration();
	}

	public void init() {
		predictor.init();
	}

	public List<String> posTags(String sen) {
		//return posTags(feagen.tokenize(sen));
		return posTags(FeaGeneration.tokenize(sen));
	}

	public List<String> posTags(List sen) {
		List<String> results = new ArrayList<String>();

		//List seq = feagen.feaGeneration(sen);
		List seq = FeaGeneration.feaGeneration(sen);
		List<String> tags = predictor.predict(seq);

		for (int i = 0; i < tags.size(); i++) {
			//results.add(((String) tags.get(i)).toUpperCase());
			results.add(tags.get(i).toUpperCase());
		}

		return results;
	}

} // end of class CRFTagger

