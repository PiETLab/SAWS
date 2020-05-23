package jtextproWrappedClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jtextpro.JTextPro;

public class JTextProWrapped {

	private static final String DEFAULT_DIRECTORY_FOR_FILE_FOR_CACHED_INSTANCE = "/Users/mb/Documents/workspace-new2/Prj-JTextPro/jtextproWrappedClasses";
	private static final String DEFAULT_NAME_FOR_FILE_FOR_CACHED_INSTANCE = "JTextPro.obj";
	private JTextPro textPro;
	private String pathToModelDir;

	public JTextProWrapped(TrainedModel trainedModel) throws IOException,
			ClassNotFoundException {
		long startTime = System.currentTimeMillis();
		System.out.println("Running initialization...");
		// create a JTextPro2 object
		textPro = new JTextPro();
		boolean isVerbose = true;
		initialize(trainedModel, isVerbose);
		System.out.println(toStringElapsedSeconds(System.currentTimeMillis()
				- startTime));
	}

	/**
	 * @param textPro
	 *            - assumed to already be initialized
	 */
	private JTextProWrapped(JTextPro textPro) {
		this.textPro = textPro;
		// // need to locate the trained models
		// String senSegmentationModelDir = getPathToModelDir() + File.separator
		// + "SenSegmenter";
		// String posTaggingModelDir = getPathToModelDir() + File.separator
		// + "CRFTagger";
		// String phraseChunkingModelDir = getPathToModelDir() + File.separator
		// + "CRFChunker";
		// initialize(senSegmentationModelDir, posTaggingModelDir,
		// phraseChunkingModelDir);
	}

	private void initialize(TrainedModel trainedModel, boolean isVerbose) {
		getTextPro().setSenSegmenterModelDir(
				trainedModel.getSenSegmentationModelDir());
		if (isVerbose) {
			System.out.println("Step 1 of 3");
			System.out.println("Loading sentence segmentation model ...");
		}
		getTextPro().initSenSegmenter();
		if (isVerbose) {
			System.out
					.println("Loading sentence segmentation model completed!");
		}

		// initialize sentence tokenizer
		getTextPro().initSenTokenizer();

		// assign path to POS tagging model and load it to memory
		getTextPro().setPosTaggerModelDir(trainedModel.getPosTaggingModelDir());
		if (isVerbose) {
			System.out.println("Step 2 of 3");
			System.out.println("Loading POS tagging model ...");
		}
		getTextPro().initPosTagger();
		if (isVerbose) {
			System.out.println("Loading POS tagging model completed!");
		}
		// assign path to phrase chunking model and load it to memory
		getTextPro().setPhraseChunkerModelDir(
				trainedModel.getPhraseChunkingModelDir());
		if (isVerbose) {
			System.out.println("Step 3 of 3");
			System.out.println("Loading phrase chunking model ...");
		}
		getTextPro().initPhraseChunker();
		if (isVerbose) {
			System.out.println("Loading phrase chunking model completed!");
			System.out.println();
		}
	}

	/**
	 * 
	 * 
	 * 
	 * @param pathToInstanceCachedInFile
	 *            ensure this isn't the same subdir as the source code (for
	 *            efficiency of creating jar files, etc)
	 * @param nameOfFileContainingCachedInstance
	 * @return
	 * @throws IOException
	 */
	public boolean cacheInstanceInFile(String pathToInstanceCachedInFile,
			String nameOfFileContainingCachedInstance) throws IOException {
		// "JTextPro.obj"
		File fileJTextProObject = new File(pathToInstanceCachedInFile,
				nameOfFileContainingCachedInstance);

		// long startTime = System.currentTimeMillis();
		System.out.println("Writing JTextPro object to file...");
		FileOutputStream outFileStream = new FileOutputStream(
				fileJTextProObject);
		ObjectOutputStream s = new ObjectOutputStream(outFileStream);
		// out.println("Object is in: " + objectFileName);
		System.out.println("Object will be in file: " + fileJTextProObject);
		s.writeObject(this.getTextPro());
		s.flush();
		return true;

	}

	public static JTextProWrapped generateInstanceFromCache()
			throws IOException, ClassNotFoundException {
		return generateInstanceFromCache(
				DEFAULT_DIRECTORY_FOR_FILE_FOR_CACHED_INSTANCE,
				DEFAULT_NAME_FOR_FILE_FOR_CACHED_INSTANCE);
	}

	/**
	 * Generates instance from an object that has been written to a file.
	 * Assumes the instance in the file has been fully initialized with the
	 * model.
	 * 
	 * "JTextPro.obj"
	 * 
	 * @param pathToInstanceCachedInFile
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static JTextProWrapped generateInstanceFromCache(
			String pathToInstanceCachedInFile,
			String nameOfFileContainingCachedInstance) throws IOException,
			ClassNotFoundException {
		return new JTextProWrapped(getObjectFromFile(
				pathToInstanceCachedInFile, nameOfFileContainingCachedInstance));
	}

	private static JTextPro getObjectFromFile(
			String pathToInstanceCachedInFile,
			String nameOfFileContainingCachedInstance) throws IOException,
			ClassNotFoundException {
		JTextPro textPro = null;
		File fileJTextProObject = new File(pathToInstanceCachedInFile,
				nameOfFileContainingCachedInstance);
		System.out
				.println("Mode: use file cache of JTextPro object.  Determining whether file exists.");
		if (fileJTextProObject.exists()) {
			System.out
					.println("File containing JTextPro object exists.  Reading from file...");
			FileInputStream inFileStream = new FileInputStream(
					fileJTextProObject);
			ObjectInputStream i = new ObjectInputStream(inFileStream);
			textPro = (JTextPro) i.readObject();
		}
		return textPro;
	}

	public String getPathToModelDir() {
		return pathToModelDir;
	}

	public JTextPro getTextPro() {
		return textPro;
	}

	private String toStringElapsedSeconds(long elapsedMilliseconds) {
		return "Time elapsed: " + (elapsedMilliseconds / 1000.0) + " (sec)";
	}

	/**
	 * Initializes the passed testPro object (has side effects)
	 * 
	 * @param testPro
	 */
	public void initialize(String senSegmentationModelDir,
			String posTaggingModelDir, String phraseChunkingModelDir) {
		// assign path to sentence segmentation model and load it to memory
		getTextPro().setSenSegmenterModelDir(senSegmentationModelDir);
		System.out.println("Step 1 of 3");
		System.out.println("Loading sentence segmentation model ...");
		getTextPro().initSenSegmenter();
		System.out.println("Loading sentence segmentation model completed!");

		// initialize sentence tokenizer
		getTextPro().initSenTokenizer();

		// assign path to POS tagging model and load it to memory
		getTextPro().setPosTaggerModelDir(posTaggingModelDir);
		System.out.println("Step 2 of 3");
		System.out.println("Loading POS tagging model ...");
		getTextPro().initPosTagger();
		System.out.println("Loading POS tagging model completed!");

		// assign path to phrase chunking model and load it to memory
		getTextPro().setPhraseChunkerModelDir(phraseChunkingModelDir);
		System.out.println("Step 3 of 3");
		System.out.println("Loading phrase chunking model ...");
		getTextPro().initPhraseChunker();
		System.out.println("Loading phrase chunking model completed!");
		System.out.println();
	}

	public List<String> doSenSegmentation(String line) {
		return getTextPro().doSenSegmentation(line);
	}

	public String doSenTokenization(String sentence) {
		return getTextPro().doSenTokenization(sentence);
	}

	public List<String> doPosTagging(List<String> senToks) {
		return getTextPro().doPosTagging(senToks);
	}

	public List<String> doPhraseChunking(List<String> senToks,
			List<String> posTags) {
		return getTextPro().doPhraseChunking(senToks, posTags);
	}

	public List<String> tokenize(String str) {
		List<String> results = new ArrayList<String>();

		StringTokenizer strTok = new StringTokenizer(str, " \t\r\n");
		int len = strTok.countTokens();

		for (int i = 0; i < len; i++) {
			results.add(strTok.nextToken());
		}
		return results;
	}

	public static boolean isCachedVersionAvailable() {
		File fileJTextProObject = new File(
				DEFAULT_DIRECTORY_FOR_FILE_FOR_CACHED_INSTANCE,
				DEFAULT_NAME_FOR_FILE_FOR_CACHED_INSTANCE);
		System.out
				.println("Mode: use file cache of JTextPro object.  Determining whether file exists.");
		return fileJTextProObject.exists();
	}

}
