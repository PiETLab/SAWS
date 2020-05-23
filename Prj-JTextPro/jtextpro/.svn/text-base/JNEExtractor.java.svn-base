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

public class JNEExtractor {

    public static void main(String[] args) {
	if (args.length < 4) {
	    System.out.println("Usage: JNEExtractor <sentence segmentation model directory> <pos tagging model directory> " + 
			"<phrase chunking model directory> <input text file>");
	    return;
	}    
    
	String senSegmentationModelDir = args[0];
	String posTaggingModelDir = args[1];
	String phraseChunkingModelDir = args[2];
	String dataFile = args[3];
	
	JTextPro textPro = new JTextPro();
	
	textPro.setSenSegmenterModelDir(senSegmentationModelDir);
	textPro.initSenSegmenter();
	textPro.initSenTokenizer();
	textPro.setPosTaggerModelDir(posTaggingModelDir);
	textPro.initPosTagger();
	textPro.setPhraseChunkerModelDir(phraseChunkingModelDir);
	textPro.initPhraseChunker();
	
	BufferedReader fin = null;
	PrintWriter fout = null;
	
	try {
	    fin = new BufferedReader(new FileReader(dataFile));
	    fout = new PrintWriter(new FileWriter(dataFile + ".named-entities"));
	    
	    String line;
	    while ((line = fin.readLine()) != null) {
		List sentences = textPro.doSenSegmentation(line);
		
		String sentence;
		for (int i = 0; i < sentences.size(); i++) {
		    sentence = (String)sentences.get(i);
		    
		    List senToks = textPro.tokenize(textPro.doSenTokenization(sentence));
		    List posTags = textPro.doPosTagging(senToks);
		    List chunkTags = textPro.doPhraseChunking(senToks, posTags);
		    
		    for (int j = 0; j < senToks.size(); j++) {
			fout.print((String)senToks.get(j) + "/" + (String)posTags.get(j) + "/" +
				    (String)chunkTags.get(j) + " ");
		    }
		    fout.println();

		    List nps = textPro.extractNPs(senToks, posTags, chunkTags);
		    for (int j = 0; j < nps.size(); j++) {
			fout.print((String)nps.get(j) + "|");
		    }    
		    fout.println();
		    
		    List nes = textPro.extractNamedEntities(senToks, posTags, chunkTags);
		    for (int j = 0; j < nes.size(); j++) {
			fout.print((String)nes.get(j) + "|");
		    }    
		    fout.println();
		}
	    }
	    
	    fin.close();
	    fout.close();
	
	} catch(IOException e) {
	    System.out.println(e.toString());
	    return;
	}	
    }

} // end of class JNEExtractor

