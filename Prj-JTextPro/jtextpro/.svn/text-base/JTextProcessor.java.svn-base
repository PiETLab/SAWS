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

public class JTextProcessor {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("Usage: JTextProcessor <directory contain trained models> <input text file>");
			return;
		}

		// assign paths to trained models
		String senSegmentationModelDir = args[0] + File.separator
				+ "SenSegmenter";
		String posTaggingModelDir = args[0] + File.separator + "CRFTagger";
		String phraseChunkingModelDir = args[0] + File.separator + "CRFChunker";
		String dataFile = args[1];

		// create a JTextPro2 object
		JTextPro textPro = new JTextPro();

		// assign path to sentence segmentation model and load it to memory
		textPro.setSenSegmenterModelDir(senSegmentationModelDir);
		System.out.println();
		System.out.println("Loading sentence segmentation model ...");
		textPro.initSenSegmenter();
		System.out.println("Loading sentence segmentation model completed!");

		// initialize sentence tokenizer
		textPro.initSenTokenizer();

		// assign path to POS tagging model and load it to memory
		textPro.setPosTaggerModelDir(posTaggingModelDir);
		System.out.println();
		System.out.println("Loading POS tagging model ...");
		textPro.initPosTagger();
		System.out.println("Loading POS tagging model completed!");

		// assign path to phrase chunking model and load it to memory
		textPro.setPhraseChunkerModelDir(phraseChunkingModelDir);
		System.out.println();
		System.out.println("Loading phrase chunking model ...");
		textPro.initPhraseChunker();
		System.out.println("Loading phrase chunking model completed!");
		System.out.println();

		BufferedReader fin = null;
		PrintWriter fout = null;

		try {
			fin = new BufferedReader(new FileReader(dataFile));
			fout = new PrintWriter(new FileWriter(dataFile + ".out"));

			String line;
			while ((line = fin.readLine()) != null) {
				List sentences = textPro.doSenSegmentation(line);

				String sentence;
				for (int i = 0; i < sentences.size(); i++) {
					sentence = (String) sentences.get(i);

					//List senToks = textPro.tokenize(textPro.doSenTokenization(sentence));
					List<String> senToks = JTextPro.tokenize(textPro
							.doSenTokenization(sentence));
					List posTags = textPro.doPosTagging(senToks);
					List chunkTags = textPro.doPhraseChunking(senToks, posTags);

					for (int j = 0; j < senToks.size(); j++) {
						fout.print((String) senToks.get(j) + "/"
								+ (String) posTags.get(j) + "/"
								+ (String) chunkTags.get(j) + " ");
					}
					fout.println();
				}
			}

			fin.close();
			fout.close();

		} catch (IOException e) {
			System.out.println(e.toString());
			return;
		}
	}

} // end of class JTextProcessor

