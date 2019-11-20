package RSVP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import jtextpro.JTextPro;

/**
 * This class implements a ebook. One of its main services is to serve up this
 * book's tokens.
 * 
 * @author mb
 * 
 */
public class EBook {

	private List<RSVPChunkedSentence> chunkedSentences;
	private List<LexicalToken> tokens;

	/**
	 * 
	 * This constructor creates a chunked version of the passed EbookTextFile
	 * (chunked means shallow-parsed, with sentence breaks identified). The
	 * chunker is computationally expensive, so if this constructor is to be
	 * invoked, ensure the virtual machine has been invoked with an enlarged
	 * heap space (e.g., -mx1024M)
	 * 
	 * SideEffect: a file corresponding to the passed EbookTextFile is created
	 * as a file-written copy of this instance. Also, the code that corresponds
	 * to the chunking spews out alot of progress information.
	 * 
	 * @param eBookFile
	 * @param chunker
	 */
	public EBook(EbookTextFile eBookFile, EbookChunker chunker) {
		// boolean isChunkingRequired = !(new File(eBookFile
		// + CHUNKED_FILE_EXTENSION).exists());
		// System.out.println("Is chunking required? " + isChunkingRequired);

		// if (isChunkingRequired) {
		// processFile(eBookFile, chunker.getJTextPro());
		String fileName = eBookFile.getFilenameCorrespondingChunkedFilename();
		EbookChunkedTextFile.chunkAndWriteSentencesToFile(fileName, eBookFile
				.getAllLines(), chunker.getJTextPro());

		EbookChunkedTextFile file = new EbookChunkedTextFile(fileName);
		List<String> lines = file.getAllLines();
		chunkedSentences = populateRSVPChunkedSentences(lines);
		tokens = listify(chunkedSentences);
	}

	/**
	 * SideEffect: none
	 * 
	 * @param file
	 */
	public EBook(EbookChunkedTextFile file) {
		List<String> lines = file.getAllLines();
		chunkedSentences = populateRSVPChunkedSentences(lines);
		tokens = listify(chunkedSentences);
	}

	/**
	 * SideEffect: none
	 * 
	 * @param file
	 */
	public EBook(String stringRepresentationOfChunkedText) {
		StringTokenizer tok = new StringTokenizer(
				stringRepresentationOfChunkedText, "\n");
		List<String> lines1 = new Vector<String>();
		while (tok.hasMoreTokens()) {
			lines1.add(tok.nextToken());
		}
		chunkedSentences = populateRSVPChunkedSentences(lines1);
		tokens = listify(chunkedSentences);
	}

	/**
	 * This constructor creates a chunked version of the passed string (chunked
	 * means shallow-parsed, with sentence breaks identified). The chunker is
	 * computationally expensive, so if this constructor is to be invoked,
	 * ensure the virtual machine has been invoked with an enlarged heap space
	 * (e.g., -mx1024M). This chunked version is written to the file system
	 * using the filename that is passed.
	 * 
	 * SideEffect: the code that corresponds to the chunking spews out alot of
	 * progress information.
	 * 
	 * @param theBook
	 * @param chunker
	 */
	public EBook(String theBook, EbookChunker chunker,
			String fileNameForChunkedVersion) {
		StringTokenizer tok = new StringTokenizer(theBook, "\n");
		List<String> lines1 = new Vector<String>();
		while (tok.hasMoreTokens()) {
			lines1.add(tok.nextToken());
		}

		EbookChunkedTextFile.chunkAndWriteSentencesToFile(
				fileNameForChunkedVersion, lines1, chunker.getJTextPro());

		EbookChunkedTextFile file = new EbookChunkedTextFile(
				fileNameForChunkedVersion);
		List<String> lines = file.getAllLines();
		chunkedSentences = populateRSVPChunkedSentences(lines);
		tokens = listify(chunkedSentences);

	}

	/**
	 * This constructor creates a trivially-chunked version of the passed string
	 * (chunked means shallow-parsed, with sentence breaks identified).
	 * 
	 * @param theBook
	 * @param chunker
	 */
	public EBook(String theBook, boolean isChunked) {
		StringTokenizer tok = new StringTokenizer(theBook, "\n");
		List<String> rawText = new Vector<String>();
		while (tok.hasMoreTokens()) {
			rawText.add(tok.nextToken());
		}
		List<String> lines = generatePseudoChunkedLines(rawText);
		chunkedSentences = populateRSVPChunkedSentences(lines);
		tokens = listify(chunkedSentences);

	}

	private List<String> generatePseudoChunkedLines(List<String> rawTextLines) {
		String thePOSTag = "X";
		String theShallowParseTag = "Z";
		List<String> chunkedLines = new Vector<String>();
		for (String line : rawTextLines) {
			System.out.println("pseudo chunk: " + line);
			String chunkedLine = "";
			StringTokenizer tok = new StringTokenizer(line);
			while (tok.hasMoreTokens()) {
				String theWord = tok.nextToken();
				String tmp = theWord + "/" + thePOSTag + "/"
						+ theShallowParseTag + " ";
				chunkedLine = chunkedLine + tmp;
			}
			chunkedLines.add(chunkedLine);
			System.out.println("pseudo chunked: " + chunkedLine);
		}
		return chunkedLines;
	}

	private List<LexicalToken> listify(
			List<RSVPChunkedSentence> chunkedSentences) {
		List<LexicalToken> list = new Vector<LexicalToken>();
		for (RSVPChunkedSentence sent : chunkedSentences) {
			// System.out.println("XX " + sent);
			for (LexicalToken tok : sent.getTokens()) {
				list.add(tok);
				// System.out.println("XX " + tok);
			}
		}
		return list;
	}

	private List<RSVPChunkedSentence> populateRSVPChunkedSentences(
			List<String> lines) {
		List<RSVPChunkedSentence> chunkedSentences = new Vector<RSVPChunkedSentence>();
		for (int i = 0; i < lines.size(); i++) {
			chunkedSentences.add(new RSVPChunkedSentence(lines.get(i), i + 1,
					lines.size()));
		}
		return chunkedSentences;
	}

	public boolean exists(String fullFileName) {
		return (new File(fullFileName).exists());
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

	public List<RSVPChunkedSentence> getChunkedSentences() {
		return chunkedSentences;
	}

	/**
	 * 
	 * @param index
	 *            the index of a lexical token, as they are indexed starting at
	 *            0
	 * @return two-element array, the first element is the sentence in which the
	 *         index occurs, the second element is the word position within that
	 *         sentence
	 */
	public LexicalToken getToken(int index) {
		if (index < tokens.size()) {
			return tokens.get(index);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param fontType
	 * @param displayWidth
	 * @return
	 */
	public int findMaxFontSize(String fontType, int displayWidth) {
		return 0;
	}
	
	public static EBook getDefaultEbook() {
		//EBook eBook;
		// EbookTextFile textFile = new
		// EbookTextFile("eBooks/eBook-Berens07.txt");
		EbookChunkedTextFile chkFile = new EbookChunkedTextFile(
				"/Users/mb/Documents/workspace/Prj-RSVP/eBooks/eBook-Berens07.txt.chk");
		// EbookChunkedTextFile chkFile = new EbookChunkedTextFile(
		// "eBooks/eBook-tmp1.txt.chk");
		return new EBook(chkFile);
		// eBook = new EBook(textFile, new Chunker());
		// tok = new TwoLevelTokenizer(eBook);
	}


}
