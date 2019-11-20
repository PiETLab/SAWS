package RSVP;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import jtextpro.JTextPro;

/**
 * This class implements an ebook text file, which is a text file that has
 * certain special formatting:
 * 
 * 1. each lexical token is appended with two tags. eg. INTRODUCTION/NNP/I-NP
 * the first tag is a POS tag and the second tag is a shallow parse tag. The
 * shallow parse tag for punctuation is /O
 * 
 * 2. The shallow parsers derives the sentence breaks. They are encoded as line
 * breaks in the file. Therefore, each line contains one and only one sentence.
 * Paragraph breaks are denoted by two consecutive line breaks.
 * 
 * @author mb
 * 
 */
public class EbookChunkedTextFile extends ReadOnlyTextFile {

	public static final String CHUNKED_FILE_EXTENSION = ".chk";

	public EbookChunkedTextFile(String fullFileName) {
		super(fullFileName);
	}

	/**
	 * This method produces an output file for each text input file, which is a
	 * chunked file. "*.chk"
	 * 
	 * It also produces a data file for each chunked file "*.dat"
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void chunkAndWriteSentencesToFile(String outputFileName,
			List<String> lines, JTextPro textPro) {

		List<String> chunkedSentences = new Vector<String>();
		for (String line : lines) {
			List<String> sentences = textPro.doSenSegmentation(line);
			for (String sentence : sentences) {
				List<String> senToks = tokenize(textPro
						.doSenTokenization(sentence));
				List<String> posTags = textPro.doPosTagging(senToks);
				List<String> chunkTags = textPro.doPhraseChunking(senToks,
						posTags);

				String s = "";
				for (int j = 0; j < senToks.size(); j++) {
					s = s + senToks.get(j) + "/" + posTags.get(j) + "/"
							+ chunkTags.get(j) + " ";
				}
				chunkedSentences.add(s);
			}

			try {
				PrintWriter outputFile = new PrintWriter(new FileWriter(
						outputFileName));
				for (String s : chunkedSentences) {
					outputFile.println(s);
				}
				outputFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<String> tokenize(String str) {
		List<String> results = new ArrayList<String>();

		StringTokenizer strTok = new StringTokenizer(str, " \t\r\n");
		int len = strTok.countTokens();

		for (int i = 0; i < len; i++) {
			results.add(strTok.nextToken());
		}
		return results;
	}

}
