package driverApplications_ISF;


import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import javax.swing.*;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreIndirectSelectionFacility.IndirectSelectionFacilityView;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;
import IndirectSelectionFacilityCommands.EncodingTreePresentInitialView;

import simulatedUser.VOCASimUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * @author M. Baljko, 2004
 * @version 1.0
 * 
 */

public class TestInvocationHelp {
	public static void main(String[] args) throws InterruptedException,
			IOException {

		String args2 = "-h";
		StringTokenizer tok = new StringTokenizer(args2);
		String[] args3 = new String[tok.countTokens()];
		List<String> l = new Vector<String>();
		while (tok.hasMoreTokens()) {
			l.add(tok.nextToken());
		}
		args3 = l.toArray(args3);

		IndirectSelectionFacilityInvocationParameterModel invHelper = new IndirectSelectionFacilityInvocationParameterModel(args3);
	}
}