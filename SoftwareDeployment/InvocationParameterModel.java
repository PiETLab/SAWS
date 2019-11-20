package SoftwareDeployment;

import SoftwareDeployment.InvocationParameter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements a model that keeps track of all of the various
 * parameters that can be modified when instantiating an application.
 * 
 * 
 */

public abstract class InvocationParameterModel {

	private List<InvocationParameter> allParameters = defineAllParameters();

	protected InvocationParameterModel() {
	}

	protected InvocationParameterModel(String[] args) {
		this.setPassedParameters(args);
	}

	protected InvocationParameterModel(String args3) {
		this.setPassedParameters(args3);
	}

	protected void setPassedParameters(String s) {

		String[] args = arrayify(s);
		if (args.length > 0) {
			args = customTokenize(args);
		}
		this.setPassedParameters(args);
		// System.out.println(this.generateSummary());

	}

	protected void setPassedParameters(String[] args) {
		System.out.println(this.getClass().getName()
				+ " Setting values for all parameters. ");

		// System.out.println(this.getAllParameters());
		for (InvocationParameter param : this.getAllParameters()) {
			System.out.println("Setting value for param: "
					+ param.getParameterPrefix());
			param.extractMatchingParameter(args);
			if (param.getGUIController() != null) {
				// param.getGUIController().
			}
		}
	}

	public String generateSummary() {
		StringBuffer buf = new StringBuffer();
		for (InvocationParameter param : allParameters) {
			buf.append(param.getSummary() + "\n");
		}
		return "Parameter Settings:\n" + buf.toString();
	}

	public String generateParameterString() {
		StringBuffer buf = new StringBuffer();
		for (InvocationParameter param : allParameters) {
			buf.append(param.getArgumentPrefix() + param.getParameterPrefix()
					+ " " + param.getAssociatedValue() + " ");
		}
		return "" + buf.toString() + "\n";
	}

	protected abstract List<InvocationParameter> defineAllParameters();

	public List<InvocationParameter> getAllParameters() {
		return allParameters;
	}

	/**
	 * 
	 * @param str
	 * @return the passed str tokenized by spaces, returned as an array of
	 *         tokens
	 */
	private String[] arrayify(String str) {
		// System.out.println(str);
		StringTokenizer tok = new StringTokenizer(str);
		String[] stringArray = new String[tok.countTokens()];
		List<String> l = new Vector<String>();
		while (tok.hasMoreTokens()) {
			String theToken = tok.nextToken();
			// System.out.println(theToken);
			l.add(theToken);
			// l.add(tok.nextToken());
		}
		stringArray = l.toArray(stringArray);
		return stringArray;
	}

	/**
	 * takes arrays of tokens
	 * 
	 * makes array, each element of the form -xx yyyyy, where y is a word char,
	 * directory delimiter or dash or period
	 * 
	 * also need to handle the case: -bg java.awt.Color[r=0,g=0,b=255]
	 * 
	 * so y might be also: =, [, ], comma
	 * 
	 * @param args
	 * @return
	 */
	private String[] customTokenize(String[] args) {
		List<String> result = new Vector<String>();
		String all = null;
		for (String a : args) {
			if (all == null)
				all = a;
			else
				all = all + " " + a;
		}
		// System.out.println("xx" + all + "xxx");
		// also these four characters: = [ ] ,

		String REGEX_FOR_OPEN_SQUARE_BRACKET = "\\[";
		String REGEX_FOR_CLOSE_SQUARE_BRACKET = "\\]";
		String REGEX_FOR_COMMA = ",";
		String REGEX_FOR_EQUALS_SIGN = "=";
		// 002E is unicode for period, 002d is dash
		String REGEX_FOR_PERIOD = "\\u002e";
		String REGEX_FOR_FORWARDSLASH = "/";
		String REGEX_FOR_DASH = "-";
		String REGEX_FOR_WORD_CHARACTER = "\\w";
		String REGEX = "-\\w\\w" + " " + "[" + REGEX_FOR_OPEN_SQUARE_BRACKET
				+ REGEX_FOR_CLOSE_SQUARE_BRACKET + REGEX_FOR_COMMA
				+ REGEX_FOR_EQUALS_SIGN + REGEX_FOR_WORD_CHARACTER
				+ REGEX_FOR_DASH + REGEX_FOR_FORWARDSLASH + REGEX_FOR_PERIOD
				+ "]+" + "[ ]*";

		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(all);
		while (m.find()) {
			// System.out.println(m.start());
			// System.out.println(m.end());
			// System.out.println(m.group());
			// System.out.println("*");
			result.add(m.group());
			// System.out.println(result.get(result.size() - 1));
		}
		// System.out.println("*");
		String[] res = new String[result.size()];
		return result.toArray(res);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (InvocationParameter par : allParameters) {
			buf.append(par.toString() + "\n");
		}
		return buf.toString();
	}

	public String generateVerboseExplaination() {
		StringBuffer buf = new StringBuffer();
		for (InvocationParameter param : this.getAllParameters()) {
			buf.append(param.getVerboseExplaination() + "\n");
		}
		return buf.toString();
	}

}
