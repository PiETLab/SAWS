package jtextproWrappedClasses;

import java.io.File;

/**
 * This class implements a trained model, which actually exists as a file
 * somewhere on the file system. To create an instance of a JTextPro object and
 * to make it useable, one needs to provide it with the trained model. This is a
 * convenience class for doing so.
 * 
 * @author mb
 * 
 */
public class TrainedModel {

	private final static String DEFAULT_MODEL_FILE = "/Users/mb/Documents/workspace/Prj-JTextPro/jtextpro/models";

	private String pathToModelDir;
	private String senSegmentationModelDir;
	private String posTaggingModelDir;
	private String phraseChunkingModelDir;

	public TrainedModel(String pathToModelDir) {
		this.pathToModelDir = pathToModelDir;
		if (isFilePresent(pathToModelDir)) {
			senSegmentationModelDir = getPathToModelDir() + File.separator
					+ "SenSegmenter";
			posTaggingModelDir = getPathToModelDir() + File.separator
					+ "CRFTagger";
			phraseChunkingModelDir = getPathToModelDir() + File.separator
					+ "CRFChunker";
		} else {
			throw new TrainedModelNotFoundException("No such directory found");
		}
	}

	public TrainedModel() {
		this(DEFAULT_MODEL_FILE);
	}

	public String getPathToModelDir() {
		return pathToModelDir;
	}

	public String getSenSegmentationModelDir() {
		return senSegmentationModelDir;
	}

	public String getPosTaggingModelDir() {
		return posTaggingModelDir;
	}

	public String getPhraseChunkingModelDir() {
		return phraseChunkingModelDir;
	}

	public boolean isFilePresent(String outputFileName) {
		return (new File(outputFileName)).exists();
	}

}
