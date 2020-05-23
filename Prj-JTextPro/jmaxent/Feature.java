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

public class Feature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5706458803540055050L;

	public int idx = -1; // feature index

	public String strId = ""; // string identifier

	public int label = -1; // label

	public int cp = -1; // context predicate

	public float val = 1; // feature value

	public double wgt = 0.0; // feature weight

	public Feature() {
	}

	public Feature(int label, int cp) {
		FeatureInit(label, cp);
	}

	public Feature(int label, int cp, Map fmap) {
		FeatureInit(label, cp);
		strId2IdxAdd(fmap);
	}

	// create a feature from a string (e.g., reading from model file)
	public Feature(String str, Map cpStr2Int, Map lbStr2Int) {
		FeatureInit(str, cpStr2Int, lbStr2Int);
	}

	// create a feature from a string and add it to the feature map
	public Feature(String str, Map cpStr2Int, Map lbStr2Int, Map fmap) {
		FeatureInit(str, cpStr2Int, lbStr2Int);
		strId2IdxAdd(fmap);
	}

	public void FeatureInit(int label, int cp) {
		this.label = label;
		this.cp = cp;
		strId = Integer.toString(label) + " " + Integer.toString(cp);
	}

	public void FeatureInit(String str, Map cpStr2Int, Map lbStr2Int) {
		StringTokenizer strTok = new StringTokenizer(str, " \t\r\n");
		// <label> <cp> <idx> <wgt>

		int len = strTok.countTokens();
		if (len != 4) {
			return;
		}

		String labelStr = strTok.nextToken();
		String cpStr = strTok.nextToken();
		int idx = Integer.parseInt(strTok.nextToken());
		float val = 1;
		double wgt = Double.parseDouble(strTok.nextToken());

		Integer labelInt = (Integer) lbStr2Int.get(labelStr);
		Integer cpInt = (Integer) cpStr2Int.get(cpStr);
		FeatureInit(labelInt.intValue(), cpInt.intValue());

		this.idx = idx;
		this.val = val;
		this.wgt = wgt;
	}

	// mapping from string identifier to feature index
	public int strId2Idx(Map fmap) {
		Integer idxInt = (Integer) fmap.get(strId);
		if (idxInt != null) {
			this.idx = idxInt.intValue();
		}

		return this.idx;
	}

	// mapping from string identifier to feature index (adding feature to the
	// map
	// if the mapping does not exist
	public int strId2IdxAdd(Map fmap) {
		strId2Idx(fmap);

		if (idx < 0) {
			idx = fmap.size();
			fmap.put(strId, new Integer(idx));
		}

		return idx;
	}

	// return the feature index
	public int index(Map fmap) {
		return strId2Idx(fmap);
	}

	// convert feature to string: "<label> <cp> <idx> <weight>"
	public String toString(Map cpInt2Str, Map lbInt2Str) {
		String str = "";

		String labelStr = (String) lbInt2Str.get(new Integer(label));
		if (labelStr != null) {
			str += labelStr + " ";
		}

		String cpStr = (String) cpInt2Str.get(new Integer(cp));
		if (cpStr != null) {
			str += cpStr + " ";
		}

		str += Integer.toString(idx) + " " + Double.toString(wgt);

		return str;
	}

} // end of class Feature

