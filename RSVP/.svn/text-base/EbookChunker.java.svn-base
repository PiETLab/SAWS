package RSVP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import jtextpro.JTextPro;

public class EbookChunker {

	private static final boolean IS_VERBOSE = false;
	private String pathToModelDir = "/Users/mb/Documents/workspace/Prj-JTestPro/jtextpro/models";
	private JTextPro textPro;

	public EbookChunker() {
		// File fileJTextProObject = new File((new File(pathToEBookDirectory))
		// .getParent(), "JTextPro.obj");
		// boolean isJTextProAlreadyExists = fileJTextProObject.exists();
		boolean isJTextProAlreadyExists = false;

		if (isJTextProAlreadyExists) {
			// System.out
			// .println("File containing JTextPro object exists. Reading from
			// file...");
			// FileInputStream inFileStream;
			// try {
			// inFileStream = new FileInputStream(fileJTextProObject);
			// ObjectInputStream i = new ObjectInputStream(inFileStream);
			// textPro = (JTextPro) i.readObject();
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// } catch (ClassNotFoundException e) {
			// e.printStackTrace();
			// }
		} else {
			textPro = getJTextPro(pathToModelDir);
			// System.out.println("Writing JTextPro object to file...");
			// FileOutputStream outFileStream = new FileOutputStream(
			// fileJTextProObject);
			// ObjectOutputStream s = new
			// ObjectOutputStream(outFileStream);
			// // out.println("Object is in: " + objectFileName);
			// System.out.println("Object will be in file: "
			// + fileJTextProObject);
			// s.writeObject(textPro);
			// s.flush();
		}
	}

	/**
	 * Initializes the passed testPro object (has side effects)
	 * 
	 * @param testPro
	 */
	private JTextPro getJTextPro(String pathToModelDir) {

		JTextPro textPro = new JTextPro();

		// assign paths to trained models
		String senSegmentationModelDir = pathToModelDir + File.separator
				+ "SenSegmenter";
		String posTaggingModelDir = pathToModelDir + File.separator
				+ "CRFTagger";
		String phraseChunkingModelDir = pathToModelDir + File.separator
				+ "CRFChunker";

		// assign path to sentence segmentation model and load it to memory
		textPro.setSenSegmenterModelDir(senSegmentationModelDir);
		if (IS_VERBOSE) {
			System.out.println("Step 1 of 3");
			System.out.println("Loading sentence segmentation model ...");
		}
		textPro.initSenSegmenter();
		if (IS_VERBOSE)
			System.out
					.println("Loading sentence segmentation model completed!");

		// initialize sentence tokenizer
		textPro.initSenTokenizer();

		// assign path to POS tagging model and load it to memory
		textPro.setPosTaggerModelDir(posTaggingModelDir);
		if (IS_VERBOSE) {
			System.out.println("Step 2 of 3");
			System.out.println("Loading POS tagging model ...");
		}
		textPro.initPosTagger();
		if (IS_VERBOSE)
			System.out.println("Loading POS tagging model completed!");

		// assign path to phrase chunking model and load it to memory
		textPro.setPhraseChunkerModelDir(phraseChunkingModelDir);
		if (IS_VERBOSE) {
			System.out.println("Step 3 of 3");
			System.out.println("Loading phrase chunking model ...");
		}
		textPro.initPhraseChunker();

		if (IS_VERBOSE) {
			System.out.println("Loading phrase chunking model completed!");
			System.out.println();
		}
		return textPro;
	}

	public JTextPro getJTextPro() {
		return textPro;
	}

}
