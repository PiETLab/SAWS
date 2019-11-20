package encodingTrees;

import coreIndirectSelectionFacility.ISFLoggerServices;

/**
 * 
 * A sentence is of the form [[U(x1)]*[S(x2)]*]*, where x1 and x2 are integers
 * (milliseconds)
 * 
 * This class implements an Atomic Action.
 * 
 * The logger presents logs the following:<br>
 * W(x) - WAIT action (no action). The value x represents the time in
 * milliseconds spent on that action (the dwell time) <br>
 * S(x) - SELECT action (button press). The symbol x stands for a integer value
 * that represents the time in milliseconds spent on that action (must be less
 * than dwell time, or else not possible to select it <br>
 * A(x) - ASCEND hierarchy (second key).
 * 
 * @author mb
 * 
 */
public class AtomicAction {

	public static String SELECT = ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_SELECT_ACTION;

	public static String WAIT = ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_WAIT_ACTION;

	private final String DELIMS = "[()]";

	private String actionType;

	private Integer timeSpent;

	private boolean isEmpirical = false;

	/**
	 * 
	 * @param s
	 *            in the form of L(y), W(x) or S(x), where x in an integer or y
	 *            is the empirically selected selectable
	 */
	public AtomicAction(String s) {
		actionType = s.substring(0, 1);
		if ((ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_WAIT_ACTION
				+ ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_LEAF_REACHED
				+ ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_TARGET_PROMPT
				+ ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_SELECT_ACTION + ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_ASCEND_ACTION)
				.indexOf(actionType) == -1) {
			// if ((WAIT + SELECT + ASCEND).indexOf(actionType) == -1) {
			try {
				throw new Exception(
						"Error in log file.  Unknown atomic action: " + s);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		String str2 = s;
		str2 = str2.replaceAll(DELIMS, " ");
		str2 = str2.replaceAll(
				ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_WAIT_ACTION,
				"");
		str2 = str2
				.replaceAll(
						ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_SELECT_ACTION,
						"");
		str2 = str2
				.replaceAll(
						ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_ASCEND_ACTION,
						"");
		try {
			timeSpent = Integer.parseInt(str2.trim());
			isEmpirical = true;
		} catch (NumberFormatException e) {
			timeSpent = null;
		}
	}

	public Integer getTimeSpent() {
		return timeSpent;
	}

	public String getSymbol() {
		return actionType;
	}

	public boolean isWaitAction() {
		return ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_WAIT_ACTION
				.equals(actionType);
	}

	public boolean isSelectAction() {
		return ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_SELECT_ACTION
				.equals(actionType);
	}

	public boolean isAscendAction() {
		return ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_ASCEND_ACTION
				.equals(actionType);
	}

	public String toString() {
		return getSymbol();
	}

	public String toStringWithTime() {
		return getSymbol() + "(" + getTimeSpent() + ")";
	}

	public boolean isSystemActionSpecifyingExplicitTarget() {
		return ISFLoggerServices.TAG_FOR_LOGGER_UNIQUELY_DENOTES_TARGET_PROMPT
				.equals(actionType);
	}

}
