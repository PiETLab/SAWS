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

public class Observation implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4098264604276546045L;
	public String originalData = "";
    public int[] cps = null;
    public int humanLabel = -1;
    public int modelLabel = -1;
    
    public Observation() {
	// do nothing	
    }
    
    public Observation(int[] cps) {
	this.cps = new int[cps.length];
	
	for (int i = 0; i < cps.length; i++) {
	    this.cps[i] = cps[i];
	}
    }
    
    public Observation(List intCps) {
	this.cps = new int[intCps.size()];
	
	for (int i = 0; i < intCps.size(); i++) {
	    Integer intCp = (Integer)intCps.get(i);
	    
	    this.cps[i] = intCp.intValue();
	}
    }
    
    public Observation(int humanLabel, int[] cps) {
	this.humanLabel = humanLabel;
	this.cps = new int[cps.length];
	
	for (int i = 0; i < cps.length; i++) {
	    this.cps[i] = cps[i];
	}
    }    
    
    public String toString(Map lbInt2Str) {
	String res = originalData;

	String modelLabelStr = (String)lbInt2Str.get(new Integer(modelLabel));
	if (modelLabelStr != null) {
	    res += Option.labelSeparator + modelLabelStr;
	}
	
	return res;
    }
    
} // end of class Observation

