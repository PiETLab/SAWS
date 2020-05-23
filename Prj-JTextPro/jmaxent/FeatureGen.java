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

public class FeatureGen implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8371382560349411697L;

	List features = null; // list of features

	Map fmap = null; // feature map

	Option option = null; // option object

	Data data = null; // data object

	Dictionary dict = null; // dictionary object

	// for scan feature only
	List currentFeatures = null;

	int currentFeatureIdx = 0;

	public FeatureGen(Option option, Data data, Dictionary dict) {
		this.option = option;
		this.data = data;
		this.dict = dict;
	}

	// adding a feature
	public void addFeature(Feature f) {
		f.strId2IdxAdd(fmap);
		features.add(f);
	}

	// generating features
	public void generateFeatures() {
		if (features != null) {
			features.clear();
		} else {
			features = new ArrayList();
		}

		if (fmap != null) {
			fmap.clear();
		} else {
			fmap = new HashMap();
		}

		if (currentFeatures != null) {
			currentFeatures.clear();
		} else {
			currentFeatures = new ArrayList();
		}

		if (data.trnData == null || dict.dict == null) {
			System.out.println("No data or dictionary for generating features");
			return;
		}

		// scan over data list
		for (int i = 0; i < data.trnData.size(); i++) {
			Observation obsr = (Observation) data.trnData.get(i);

			for (int j = 0; j < obsr.cps.length; j++) {
				Element elem = null;
				CountFIdx cntFIdx = null;

				elem = (Element) dict.dict.get(new Integer(obsr.cps[j]));
				if (elem != null) {
					if (elem.count <= option.cpRareThreshold) {
						// skip this context predicate, it is too rare
						continue;
					}

					cntFIdx = (CountFIdx) elem.lbCntFidxes.get(new Integer(
							obsr.humanLabel));
					if (cntFIdx != null) {
						if (cntFIdx.count <= option.fRareThreshold) {
							// skip this feature, it is too rare
							continue;
						}

					} else {
						// not found in the dictionary, then skip
						continue;
					}

				} else {
					// not found in the dictionary, then skip
					continue;
				}

				// update the feature
				Feature f = new Feature(obsr.humanLabel, obsr.cps[j]);
				f.strId2Idx(fmap);
				if (f.idx < 0) {
					// new feature, add to the feature list
					addFeature(f);

					// update the feature index in the dictionary
					cntFIdx.fidx = f.idx;
					elem.chosen = 1;
				}
			}
		}

		option.numFeatures = features.size();
	}

	public int numFeatures() {
		if (features == null) {
			return 0;
		} else {
			return features.size();
		}
	}

	public void readFeatures(BufferedReader fin) throws IOException {
		if (features != null) {
			features.clear();
		} else {
			features = new ArrayList();
		}

		if (fmap != null) {
			fmap.clear();
		} else {
			fmap = new HashMap();
		}

		if (currentFeatures != null) {
			currentFeatures.clear();
		} else {
			currentFeatures = new ArrayList();
		}

		String line;

		// get the number of features
		if ((line = fin.readLine()) == null) {
			System.out.println("Unknown number of features");
			return;
		}
		int numFeatures = Integer.parseInt(line);
		if (numFeatures <= 0) {
			System.out.println("Invalid number of features");
			return;
		}

		System.out.println("Reading features ...");

		// main loop for reading features
		for (int i = 0; i < numFeatures; i++) {
			line = fin.readLine();
			if (line == null) {
				// invalid feature line, ignore it
				continue;
			}

			StringTokenizer strTok = new StringTokenizer(line, " ");
			if (strTok.countTokens() != 4) {
				// invalid feature line, ignore it
				continue;
			}

			// create a new feature by parsing the line
			Feature f = new Feature(line, data.cpStr2Int, data.lbStr2Int);

			Integer fidx = (Integer) fmap.get(f.strId);
			if (fidx == null) {
				// insert the feature into the feature map
				fmap.put(f.strId, new Integer(f.idx));
				features.add(f);
			}
		}

		System.out.println("Reading " + Integer.toString(features.size())
				+ " features completed!");

		// read the line ###...
		line = fin.readLine();

		option.numFeatures = features.size();
	}

	public void writeFeatures(PrintWriter fout) throws IOException {
		// write the number of features
		fout.println(Integer.toString(features.size()));

		for (int i = 0; i < features.size(); i++) {
			Feature f = (Feature) features.get(i);
			fout.println(f.toString(data.cpInt2Str, data.lbInt2Str));
		}

		// wirte the line ###...
		fout.println(Option.modelSeparator);
	}

	public void scanReset() {
		currentFeatureIdx = 0;
	}

	public void startScanFeatures(Observation obsr) {
		currentFeatures.clear();
		currentFeatureIdx = 0;

		// scan over all context predicates
		for (int i = 0; i < obsr.cps.length; i++) {
			Element elem = (Element) dict.dict.get(new Integer(obsr.cps[i]));
			if (elem == null) {
				continue;
			}

			if (!(elem.isScanned)) {
				// scan all labels for features
				Iterator it = elem.lbCntFidxes.keySet().iterator();
				while (it.hasNext()) {
					Integer labelInt = (Integer) it.next();
					CountFIdx cntFIdx = (CountFIdx) elem.lbCntFidxes
							.get(labelInt);

					if (cntFIdx.fidx >= 0) {
						Feature f = new Feature();
						f.FeatureInit(labelInt.intValue(), obsr.cps[i]);
						f.idx = cntFIdx.fidx;

						elem.cpFeatures.add(f);
					}
				}

				elem.isScanned = true;
			}

			for (int j = 0; j < elem.cpFeatures.size(); j++) {
				currentFeatures.add(elem.cpFeatures.get(j));
			}
		}
	}

	public boolean hasNextFeature() {
		return (currentFeatureIdx < currentFeatures.size());
	}

	public Feature nextFeature() {
		Feature f = (Feature) currentFeatures.get(currentFeatureIdx);
		currentFeatureIdx++;
		return f;
	}

} // end of class FeatureGen

