/*
 Copyright (C) 2006 by
 
 Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package jtextpro;

import java.io.*;
import java.util.*;

import sensegmenter.*;
import penntokenizer.*;
import crftagger.*;
import crfchunker.*;

public class JTextPro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7121624225959174150L;

	String senSegmenterModelDir = "models" + File.separator + "SenSegmenter";

	String posTaggerModelDir = "models" + File.separator + "CRFTagger";

	String phraseChunkerModelDir = "models" + File.separator + "CRFChunker";

	SenSegmenter senSegmenter = null;

	PennTokenizer senTokenizer = null;

	CRFTagger posTagger = null;

	CRFChunker phraseChunker = null;

	public JTextPro() {
	}

	public void setSenSegmenterModelDir(String modelDir) {
		if (modelDir != null) {
			if (modelDir.length() > 0) {
				senSegmenterModelDir = modelDir;
			}
		}
	}

	public void setPosTaggerModelDir(String modelDir) {
		if (modelDir != null) {
			if (modelDir.length() > 0) {
				posTaggerModelDir = modelDir;
			}
		}
	}

	public void setPhraseChunkerModelDir(String modelDir) {
		if (modelDir != null) {
			if (modelDir.length() > 0) {
				phraseChunkerModelDir = modelDir;
			}
		}
	}

	public void initSenSegmenter() {
		senSegmenter = new SenSegmenter(senSegmenterModelDir);
		senSegmenter.init();
	}

	public void dropSenSegmenter() {
		senSegmenter = null;
		System.gc();
	}

	public void initSenTokenizer() {
		senTokenizer = new PennTokenizer();
	}

	public void dropSenTokenizer() {
		senTokenizer = null;
		System.gc();
	}

	public void initPosTagger() {
		posTagger = new CRFTagger(posTaggerModelDir);
		posTagger.init();
	}

	public void dropPosTagger() {
		posTagger = null;
		System.gc();
	}

	public void initPhraseChunker() {
		phraseChunker = new CRFChunker(phraseChunkerModelDir);
		phraseChunker.init();
	}

	public void dropPhraseChunker() {
		phraseChunker = null;
		System.gc();
	}

	public List<String> doSenSegmentation(String text) {
		// MB: added genericity
		return senSegmenter.senSegment(text);
	}

	public String doSenTokenization(String sentence) {
		// return senTokenizer.tokenize(sentence);
		return PennTokenizer.tokenize(sentence);
	}

	public List doPosTagging(String sentence) {
		return posTagger.posTags(doSenTokenization(sentence));
	}

	public List<String> doPosTagging(List sentence) {
		return posTagger.posTags(sentence);
	}

	public List doPhraseChunking(String sentence, String posTags) {
		return phraseChunker.phraseChunks(doSenTokenization(sentence), posTags);
	}

	public List<String> doPhraseChunking(List sentence, List posTags) {
		return phraseChunker.phraseChunks(sentence, posTags);
	}

	public List doPhraseChunking(String sentence) {
		List posTags = doPosTagging(sentence);
		return phraseChunker.phraseChunks(
				tokenize(doSenTokenization(sentence)), posTags);
	}

	public List<String> extractNPs(List sentence, List posTags, List chunkTags) {
		List<String> results = new ArrayList<String>();

		String np;

		int i = 0;
		while (i < sentence.size()) {
			String token = (String) sentence.get(i);
			String chunkTag = (String) chunkTags.get(i);

			if (chunkTag.compareToIgnoreCase("B-NP") == 0) {
				np = token;

				i++;
				while (i < sentence.size()) {
					String nextToken = (String) sentence.get(i);
					String nextChunkTag = (String) chunkTags.get(i);

					if (nextChunkTag.compareToIgnoreCase("I-NP") == 0) {
						np += " " + nextToken;
						i++;
					} else {
						break;
					}
				}

				results.add(np);

			} else {
				i++;
			}
		}

		return results;
	}

	public List<String> extractPosTagNPs(List posTags, List chunkTags) {
		List<String> results = new ArrayList<String>();

		String posTagNP;

		int i = 0;
		while (i < posTags.size()) {
			String posTag = (String) posTags.get(i);
			String chunkTag = (String) chunkTags.get(i);

			if (chunkTag.compareToIgnoreCase("B-NP") == 0) {
				posTagNP = posTag;

				i++;
				while (i < posTags.size()) {
					String nextPosTag = (String) posTags.get(i);
					String nextChunkTag = (String) chunkTags.get(i);

					if (nextChunkTag.compareToIgnoreCase("I-NP") == 0) {
						posTagNP += " " + nextPosTag;
						i++;
					} else {
						break;
					}
				}

				results.add(posTagNP);

			} else {
				i++;
			}
		}

		return results;
	}

	public List<String> extractNamedEntities(List<String> np,
			List<String> posTags) {
		List<String> results = new ArrayList<String>();

		String entity;

		int i = 0;
		while (i < np.size()) {
			String token = (String) np.get(i);
			String posTag = (String) posTags.get(i);

			if (posTag.compareToIgnoreCase("NNP") == 0
					|| posTag.compareToIgnoreCase("NNPS") == 0) {
				entity = token;

				i++;
				while (i < np.size()) {
					String nextToken = (String) np.get(i);
					String nextPosTag = (String) posTags.get(i);

					if (nextPosTag.compareToIgnoreCase("NNP") == 0
							|| nextPosTag.compareToIgnoreCase("NNPS") == 0) {
						entity += " " + nextToken;
						i++;
					} else {
						break;
					}
				}

				results.add(entity);

			} else {
				i++;
			}
		}

		return results;

	}

	public List<String> extractNamedEntities(List sentence, List posTags,
			List chunkTags) {
		List<String> nes = new ArrayList<String>();

		List<String> nps = extractNPs(sentence, posTags, chunkTags);
		List<String> posTagNPs = extractPosTagNPs(posTags, chunkTags);

		for (int i = 0; i < nps.size(); i++) {
			// List tempNEs = extractNamedEntities(tokenize((String)
			// nps.get(i)),
			// tokenize((String) posTagNPs.get(i)));
			List<String> tempNEs = extractNamedEntities(tokenize(nps.get(i)),
					tokenize(posTagNPs.get(i)));
			for (int j = 0; j < tempNEs.size(); j++) {
				nes.add(tempNEs.get(j));
			}
		}

		return nes;
	}

	// =================================================================

	public static List<String> tokenize(String str) {
		List<String> results = new ArrayList<String>();

		StringTokenizer strTok = new StringTokenizer(str, " \t\r\n");
		int len = strTok.countTokens();

		for (int i = 0; i < len; i++) {
			results.add(strTok.nextToken());
		}

		return results;
	}

} // end of class JTextPro2

