/*
 Copyright (C) 2006 by
 
 Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package sensegmenter;

import java.io.Serializable;
import java.util.*;
import jmaxent.*;

public class SenSegmenter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3140081296512981018L;

	public static String positiveLabel = "y";

	public Classification classifier = null;

	public FeaGeneration feaGen = null;

	public SenSegmenter(String modelDir) {
		classifier = new Classification(modelDir);
		feaGen = new FeaGeneration(classifier);
	}

	public void init() {
		classifier.init();
	}

	public List<String> senSegment(String text) {
		//MB: added genericity
		List tokens = feaGen.tokenize(text);

		List data = new ArrayList();
		List majorIdxes = new ArrayList();
		List minorIdxes = new ArrayList();

		// generate context predicates
		feaGen.feaGeneration(tokens, data, majorIdxes, minorIdxes);

		// classify
		List labels = classifier.classify(data);

		// sentence segmentation
		//MB: added genericity
		List<String> results = segmentSens(tokens, labels, majorIdxes, minorIdxes);

		return results;
	}

	public List<String> segmentSens(List tokens, List labels, List majorIdxes,
			List minorIdxes) {
		//MB: added genericity

		int i, j;

		List<String> results = new ArrayList<String>();

		List<String> positiveLabels = new ArrayList<String>();
		List<Integer> positiveMajorIdxes = new ArrayList<Integer>();
		List<Integer> positiveMinorIdxes = new ArrayList<Integer>();

		for (i = 0; i < labels.size(); i++) {
			String label = (String) labels.get(i);
			Integer majorIdx = (Integer) majorIdxes.get(i);
			Integer minorIdx = (Integer) minorIdxes.get(i);

			if (label.compareToIgnoreCase(positiveLabel) == 0) {
				positiveLabels.add(label);
				positiveMajorIdxes.add(majorIdx);
				positiveMinorIdxes.add(minorIdx);
			}
		}

		if (positiveLabels.size() <= 0) {
			String sentence = "";
			for (i = 0; i < tokens.size(); i++) {
				sentence += (String) tokens.get(i) + " ";
			}
			sentence = feaGen.normalizeSen(sentence);
			if (sentence.length() > 0) {
				results.add(sentence);
			}

			return results;
		}

		int lastMajorIdx = 0;
		int lastMinorIdx = -1;

		String sentence;

		for (i = 0; i < positiveLabels.size(); i++) {
			sentence = "";

			Integer majorIdx = (Integer) positiveMajorIdxes.get(i);
			Integer minorIdx = (Integer) positiveMinorIdxes.get(i);

			String bToken = (String) tokens.get(lastMajorIdx);
			String cToken = (String) tokens.get(majorIdx.intValue());

			String suffix = bToken.substring(lastMinorIdx + 1, bToken.length());
			if (suffix.length() > 0
					&& !(suffix.compareTo("\"") == 0
							|| suffix.compareTo("'") == 0 || suffix
							.compareTo("''") == 0)) {
				sentence += suffix + " ";
			}

			for (j = lastMajorIdx + 1; j < majorIdx.intValue(); j++) {
				String token = (String) tokens.get(j);
				sentence += token + " ";
			}

			if (cToken.endsWith(".\"") || cToken.endsWith(".'")
					|| cToken.endsWith(".''") || cToken.endsWith("?\"")
					|| cToken.endsWith("?'") || cToken.endsWith("?''")
					|| cToken.endsWith("!\"") || cToken.endsWith("!'")
					|| cToken.endsWith("!''")) {
				sentence += cToken;

			} else {

				String prefix = cToken.substring(0, minorIdx.intValue() + 1);
				if (prefix.length() > 0) {
					sentence += prefix;
				}
			}

			sentence = feaGen.normalizeSen(sentence);
			if (sentence.length() > 0) {
				results.add(sentence);
			}

			lastMajorIdx = majorIdx.intValue();
			lastMinorIdx = minorIdx.intValue();
		}

		if (lastMajorIdx < tokens.size() - 1) {
			sentence = "";

			String bToken = (String) tokens.get(lastMajorIdx);

			String suffix = bToken.substring(lastMinorIdx + 1, bToken.length());
			if (suffix.length() > 0
					&& !(suffix.compareTo("\"") == 0
							|| suffix.compareTo("'") == 0 || suffix
							.compareTo("''") == 0)) {
				sentence += suffix + " ";
			}

			for (j = lastMajorIdx + 1; j < tokens.size(); j++) {
				sentence += (String) tokens.get(j) + " ";
			}

			sentence = feaGen.normalizeSen(sentence);
			if (sentence.length() > 0) {
				results.add(sentence);
			}
		}

		return results;
	}

} // end of class JSenSegmenter

