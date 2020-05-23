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

public class CountFeatureIdx implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4070706157661312843L;

	public int count = 0;

	public int fidx = -1;

	public CountFeatureIdx(int count, int fidx) {
		this.count = count;
		this.fidx = fidx;
	}

} // end of class CountFeatureIdx

