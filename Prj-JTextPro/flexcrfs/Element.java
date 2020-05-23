/*
 Copyright (C) 2006, Xuan-Hieu Phan
 
 Email:	hieuxuan@ecei.tohoku.ac.jp
 pxhieu@gmail.com
 URL:	http://www.hori.ecei.tohoku.ac.jp/~hieuxuan
 
 Graduate School of Information Sciences,
 Tohoku University
 */

package flexcrfs;

import java.io.Serializable;
import java.util.*;

public class Element implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1803084953329747475L;

	public int count = 0; // the number of occurrences of this context

	// predicate

	public int chosen = 0; // indicating whether or not it is incorporated into

	// the model

	Map<Integer, CountFeatureIdx> lbCntFidxes = null; // map of labels to

	// CountFeatureIdxes

	List cpFeatures = null; // features associated with this context predicates

	boolean isScanned = false; // be scanned or not

	public Element() {
		lbCntFidxes = new HashMap<Integer, CountFeatureIdx>();
		cpFeatures = new ArrayList();
	}

} // end of class Element

