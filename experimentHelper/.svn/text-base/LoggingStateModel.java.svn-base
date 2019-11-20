package experimentHelper;

import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;
import IndirectSelectionFacilityCommands.StartLoggerAndLaunchFocusAdvancementCommand;
import IndirectSelectionFacilityCommands.StartLoggerCommand;
import IndirectSelectionFacilityCommands.StopLoggerCommand;
import abstractOnScreenIndirectSelectionKeyboard.AbstractOnScreenIndirectSelectionKeyboard;
import actionScheme.ActionSchemeUsingOneInputDevice;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

/**
 * This class implements an "Experiment Helper", which is a facility that
 * provides support for experiments in which the software needs to keep track of
 * different states and key combinations (trial started, trial stopped, etc)
 * 
 * 
 * 
 * the user is prompted to compose a target text.
 * 
 * It maintains a list of strings, which represent the target texts that are to
 * be presented to the user (one after another, where each trial has one target
 * text).
 * 
 * The client can specify whether target highlighting in on or off (if on, then
 * the target is "highlighted" from the other keys, to help the user in his/her
 * visual scan) [this isn't true yet, since the value is hard-coded, but change
 * in future]
 * 
 * @author mb
 * 
 */
public class LoggingStateModel {

	public static final int STATE_IS_NOT_LOGGING = 0;
	public static final int STATE_IS_LOGGING = 1;

	public int currentState;

	public LoggingStateModel() {
		currentState = STATE_IS_NOT_LOGGING;
	}

	public int getState() {
		return currentState;
	}

	public void setState(int nextState) {
		currentState = nextState;
	}

	public ActionEvent getStartLoggerAndLaunchFocusAdvancementCommand(
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		return new ActionEvent(new StartLoggerAndLaunchFocusAdvancementCommand(
				paramModel.getUserId()), 0, "StartLoggerCommand");
	}

	public IndirectSelectionFaciltyCommand getStartLoggerCommand(
			IndirectSelectionFacilityInvocationParameterModel paramModel) {
		return new StartLoggerCommand(paramModel.getUserId());
	}

	public ActionEvent getStopLoggerCommand() {
		return new ActionEvent(new StopLoggerCommand(), 0, "StopLoggerCommand");
	}

}
