/*
 Copyright (C) 2006, Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package crfchunker;

import java.io.Serializable;
import java.util.*;

import flexcrfs.*;

public class CRFChunker implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6459072670378299643L;

	public Prediction predictor = null;

	public FeaGeneration feagen = null;

	public CRFChunker(String modelDir) {
		predictor = new Prediction(modelDir);
		feagen = new FeaGeneration();
	}

	public void init() {
		predictor.init();
	}

	public List phraseChunks(String sen, String posTags) {
		return phraseChunks(FeaGeneration.tokenize(sen), FeaGeneration.tokenize(posTags));
		//return phraseChunks(feagen.tokenize(sen), feagen.tokenize(posTags));
	}

	public List<String> phraseChunks(List sen, List posTags) {
		List<String> results = new ArrayList<String>();

		//List<List<String>> seq = feagen.feaGeneration(sen, posTags);
		List<List<String>> seq = FeaGeneration.feaGeneration(sen, posTags);
		List chunks = predictor.predict(seq);

		for (int i = 0; i < chunks.size(); i++) {
			results.add(((String) chunks.get(i)).toUpperCase());
		}

		return results;
	}

} // end of class CRFChunker

