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

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8251778482660929029L;

	Option option = null;

	public Map lbStr2Int = null;

	public Map lbInt2Str = null;

	public Map cpStr2Int = null;

	public Map cpInt2Str = null;

	public List trnData = null;

	public List tstData = null;

	public List ulbData = null;

	public Data(Option option) {
		this.option = option;
	}

	public void readCpMaps(BufferedReader fin) throws IOException {
		if (cpStr2Int != null) {
			cpStr2Int.clear();
		} else {
			cpStr2Int = new HashMap();
		}

		if (cpInt2Str != null) {
			cpInt2Str.clear();
		} else {
			cpInt2Str = new HashMap();
		}

		String line;

		// get size of the map
		if ((line = fin.readLine()) == null) {
			System.out.println("No context predicate map size information");
			return;
		}

		int numCps = Integer.parseInt(line);
		if (numCps <= 0) {
			System.out.println("Invalid context predicate mapping size");
			return;
		}

		System.out.println("Reading the context predicate maps ...");

		for (int i = 0; i < numCps; i++) {
			line = fin.readLine();
			if (line == null) {
				System.out.println("Invalid context predicate mapping line");
				return;
			}

			StringTokenizer strTok = new StringTokenizer(line, " \t\r\n");
			if (strTok.countTokens() != 2) {
				continue;
			}

			String cpStr = strTok.nextToken();
			String cpInt = strTok.nextToken();

			cpStr2Int.put(cpStr, new Integer(cpInt));
			cpInt2Str.put(new Integer(cpInt), cpStr);
		}

		System.out.println("Reading context predicate maps ("
				+ Integer.toString(cpStr2Int.size()) + " entries) completed!");

		// read the line ###...
		line = fin.readLine();

		option.numCps = cpStr2Int.size();
	}

	public int numCps() {
		if (cpStr2Int == null) {
			return 0;
		} else {
			return cpStr2Int.size();
		}
	}

	public void writeCpMaps(Dictionary dict, PrintWriter fout)
			throws IOException {
		Iterator it = null;

		if (cpStr2Int == null) {
			return;
		}

		int count = 0;
		for (it = cpStr2Int.keySet().iterator(); it.hasNext();) {
			String cpStr = (String) it.next();
			Integer cpInt = (Integer) cpStr2Int.get(cpStr);

			Element elem = (Element) dict.dict.get(cpInt);
			if (elem != null) {
				if (elem.chosen == 1) {
					count++;
				}
			}
		}

		// write the map size
		fout.println(Integer.toString(count));

		for (it = cpStr2Int.keySet().iterator(); it.hasNext();) {
			String cpStr = (String) it.next();
			Integer cpInt = (Integer) cpStr2Int.get(cpStr);

			Element elem = (Element) dict.dict.get(cpInt);
			if (elem != null) {
				if (elem.chosen == 1) {
					fout.println(cpStr + " " + cpInt.toString());
				}
			}
		}

		// write the line ###...
		fout.println(Option.modelSeparator);
	}

	public void readLbMaps(BufferedReader fin) throws IOException {
		if (lbStr2Int != null) {
			lbStr2Int.clear();
		} else {
			lbStr2Int = new HashMap();
		}

		if (lbInt2Str != null) {
			lbInt2Str.clear();
		} else {
			lbInt2Str = new HashMap();
		}

		String line;

		// get size of the map
		if ((line = fin.readLine()) == null) {
			System.out.println("No label map size information");
			return;
		}

		int numLabels = Integer.parseInt(line);
		if (numLabels <= 0) {
			System.out.println("Invalid label mapping size");
			return;
		}

		System.out.println("Reading the context predicate maps ...");

		for (int i = 0; i < numLabels; i++) {
			line = fin.readLine();
			if (line == null) {
				System.out.println("Invalid context predicate mapping line");
				return;
			}

			StringTokenizer strTok = new StringTokenizer(line, " \t\r\n");
			if (strTok.countTokens() != 2) {
				continue;
			}

			String lbStr = strTok.nextToken();
			String lbInt = strTok.nextToken();

			lbStr2Int.put(lbStr, new Integer(lbInt));
			lbInt2Str.put(new Integer(lbInt), lbStr);
		}

		System.out.println("Reading label maps ("
				+ Integer.toString(lbStr2Int.size()) + " entries) completed!");

		// read the line ###...
		line = fin.readLine();

		option.numLabels = lbStr2Int.size();
	}

	public int numLabels() {
		if (lbStr2Int == null) {
			return 0;
		} else {
			return lbStr2Int.size();
		}
	}

	public void writeLbMaps(PrintWriter fout) throws IOException {
		if (lbStr2Int == null) {
			return;
		}

		// write the map size
		fout.println(Integer.toString(lbStr2Int.size()));

		for (Iterator it = lbStr2Int.keySet().iterator(); it.hasNext();) {
			String lbStr = (String) it.next();
			Integer lbInt = (Integer) lbStr2Int.get(lbStr);

			fout.println(lbStr + " " + lbInt.toString());
		}

		// write the line ###...
		fout.println(Option.modelSeparator);
	}

	public void readTrnData(String dataFile) {
		if (cpStr2Int != null) {
			cpStr2Int.clear();
		} else {
			cpStr2Int = new HashMap();
		}

		if (cpInt2Str != null) {
			cpInt2Str.clear();
		} else {
			cpInt2Str = new HashMap();
		}

		if (lbStr2Int != null) {
			lbStr2Int.clear();
		} else {
			lbStr2Int = new HashMap();
		}

		if (lbInt2Str != null) {
			lbInt2Str.clear();
		} else {
			lbInt2Str = new HashMap();
		}

		if (trnData != null) {
			trnData.clear();
		} else {
			trnData = new ArrayList();
		}

		// open data file
		BufferedReader fin = null;

		try {
			fin = new BufferedReader(new FileReader(dataFile));

			System.out.println("Reading training data ...");

			String line;
			while ((line = fin.readLine()) != null) {
				StringTokenizer strTok = new StringTokenizer(line, " \t\r\n");
				int len = strTok.countTokens();

				if (len <= 1) {
					// skip this invalid line
					continue;
				}

				List strCps = new ArrayList();
				for (int i = 0; i < len - 1; i++) {
					strCps.add(strTok.nextToken());
				}

				String labelStr = strTok.nextToken();

				List intCps = new ArrayList();

				for (int i = 0; i < strCps.size(); i++) {
					String cpStr = (String) strCps.get(i);
					Integer cpInt = (Integer) cpStr2Int.get(cpStr);
					if (cpInt != null) {
						intCps.add(cpInt);
					} else {
						intCps.add(new Integer(cpStr2Int.size()));
						cpStr2Int.put(cpStr, new Integer(cpStr2Int.size()));
						cpInt2Str.put(new Integer(cpInt2Str.size()), cpStr);
					}
				}

				Integer labelInt = (Integer) lbStr2Int.get(labelStr);
				if (labelInt == null) {
					labelInt = new Integer(lbStr2Int.size());
					lbStr2Int.put(labelStr, labelInt);
					lbInt2Str.put(labelInt, labelStr);
				}

				int[] cps = new int[intCps.size()];
				for (int i = 0; i < cps.length; i++) {
					cps[i] = ((Integer) intCps.get(i)).intValue();
				}

				Observation obsr = new Observation(labelInt.intValue(), cps);

				// add this observation to the data
				trnData.add(obsr);
			}

			System.out.println("Reading " + Integer.toString(trnData.size())
					+ " training data examples completed!");

		} catch (IOException e) {
			System.out.println(e.toString());
			return;
		}

		option.numCps = cpStr2Int.size();
		option.numLabels = lbStr2Int.size();
		option.numTrainExps = trnData.size();
	}

	public void readTstData(String dataFile) {
		if (tstData != null) {
			tstData.clear();
		} else {
			tstData = new ArrayList();
		}

		// open data file
		BufferedReader fin = null;

		try {
			fin = new BufferedReader(new FileReader(dataFile));

			System.out.println("Reading testing data ...");

			String line;
			while ((line = fin.readLine()) != null) {
				StringTokenizer strTok = new StringTokenizer(line, " \t\r\n");
				int len = strTok.countTokens();

				if (len <= 1) {
					// skip this invalid line
					continue;
				}

				List strCps = new ArrayList();
				for (int i = 0; i < len - 1; i++) {
					strCps.add(strTok.nextToken());
				}

				String labelStr = strTok.nextToken();

				List intCps = new ArrayList();

				for (int i = 0; i < strCps.size(); i++) {
					String cpStr = (String) strCps.get(i);
					Integer cpInt = (Integer) cpStr2Int.get(cpStr);
					if (cpInt != null) {
						intCps.add(cpInt);
					} else {
						// do nothing
					}
				}

				Integer labelInt = (Integer) lbStr2Int.get(labelStr);
				if (labelInt == null) {
					System.out
							.println("Reading testing observation, label not found or invalid");
					return;
				}

				int[] cps = new int[intCps.size()];
				for (int i = 0; i < cps.length; i++) {
					cps[i] = ((Integer) intCps.get(i)).intValue();
				}

				Observation obsr = new Observation(labelInt.intValue(), cps);

				// add this observation to the data
				tstData.add(obsr);
			}

			System.out.println("Reading " + Integer.toString(tstData.size())
					+ " testing data examples completed!");

		} catch (IOException e) {
			System.out.println(e.toString());
			return;
		}

		option.numTestExps = tstData.size();
	}

	/*
	 * public void writeTstData(String dataFile) { }
	 * 
	 * public void readUlbData(String dataFile) { }
	 * 
	 * public void writeUlbDataWithModelLabel(String dataFile) { }
	 */

} // end of class Data

