/*
 Copyright (C) 2006 by
 
 Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package jmaxent;

import java.io.*;
import java.util.*;

public class Classification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5706644726357439068L;

	public Option option = null;

	public Data data = null;

	public Dictionary dict = null;

	public FeatureGen feagen = null;

	public Inference inference = null;

	public Model model = null;

	public boolean initialized = false;

	// MB: see var re-declaration below
	// private BufferedReader finModel = null;

	List intCps = null;

	public Classification(String modelDir) {
		option = new Option(modelDir);
		option.readOptions();

		// init();
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void init() {
		try {
			// MB: move var declaration inside rather than class attribute,
			// since var not used outside of this method (and BufferedReaders
			// are not serializable)
			BufferedReader finModel = null;
			// open model file
			finModel = option.openModelFile();
			if (finModel == null) {
				System.out.println("Couldn't open model file");
				return;
			}

			data = new Data(option);
			// read context predicate map
			data.readCpMaps(finModel);
			// read label map
			data.readLbMaps(finModel);

			dict = new Dictionary(option, data);
			// read dictionary
			dict.readDict(finModel);

			feagen = new FeatureGen(option, data, dict);
			// read features
			feagen.readFeatures(finModel);

			// create an inference object
			inference = new Inference();

			// create a model object
			model = new Model(option, data, dict, feagen, null, inference, null);
			model.initInference();

			// close model file
			finModel.close();

		} catch (IOException e) {
			System.out
					.println("Couldn't load the model, check the model file again");
			System.out.println(e.toString());
		}

		intCps = new ArrayList();

		initialized = true;
	}

	public String classify(String cps) {
		// cps contains a list of context predicates

		String modelLabel = "";
		int i;

		intCps.clear();

		StringTokenizer strTok = new StringTokenizer(cps, " \t\r\n");
		int count = strTok.countTokens();

		for (i = 0; i < count; i++) {
			String cpStr = strTok.nextToken();
			Integer cpInt = (Integer) data.cpStr2Int.get(cpStr);
			if (cpInt != null) {
				intCps.add(cpInt);
			}
		}

		Observation obsr = new Observation(intCps);

		// classify
		inference.classify(obsr);

		String lbStr = (String) data.lbInt2Str
				.get(new Integer(obsr.modelLabel));
		if (lbStr != null) {
			modelLabel = lbStr;
		}

		return modelLabel;
	}

	public List classify(List data) {
		List list = new ArrayList();

		for (int i = 0; i < data.size(); i++) {
			list.add(classify((String) data.get(i)));
		}

		return list;
	}

} // end of class Classification

