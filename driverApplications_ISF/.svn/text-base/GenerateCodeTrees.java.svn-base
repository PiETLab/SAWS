/*
 * Created on 10-Aug-2004
 *
 */
package driverApplications_ISF;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;

import java.io.File;

import coreIndirectSelectionFacility.ISFLoggerServices;

import SoftwareDeployment.UserSpecificModel;
import UtilityClassesISF.XMLFileWrapper;

import encodingTrees.Code;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;

/**
 * 
 * 
 * @author Melanie Baljko
 * 
 */
public class GenerateCodeTrees {

	public static void main(String[] args) {

		String PARAM_FILE = "isf-parameters-compose-for-memo.txt";
		String ISF_PARAMETER_FILENAME = UserSpecificModel
				.getDefaultDirectoryForParameterFiles()
				+ PARAM_FILE;

		UserSpecificModel um = new IndirectSelectionFacilityUserModel(
				ISF_PARAMETER_FILENAME);

		IndirectSelectionFacilityInvocationParameterModel paramModel = (IndirectSelectionFacilityInvocationParameterModel) um
				.getInvocationParameters();

		TraversableEncodingTreeGenerator tetGen = new TraversableEncodingTreeGenerator();
		TraversableEncodingTree encodingTree = tetGen
				.generateEncodingTree(paramModel);

		System.out.println(encodingTree.toStringPlainTextLispStyle());
		System.out.println(encodingTree.getCode()
				.toStringForLoggerFileToBeParsed());
		String path = System.getProperty("user.home");
		String theFileName = "HuffmanUnequalCostsK2.obj";
		encodingTree.cacheInstanceInFile(path, theFileName);

		TraversableEncodingTree et2 = TraversableEncodingTree
				.getObjectFromFile(path, theFileName);
		System.out.println(et2.getCode().toStringForLoggerFileToBeParsed());

		PARAM_FILE = "isf-parameters-compose-test.txt";
		ISF_PARAMETER_FILENAME = UserSpecificModel
				.getDefaultDirectoryForParameterFiles()
				+ PARAM_FILE;

		paramModel = (IndirectSelectionFacilityInvocationParameterModel) (new IndirectSelectionFacilityUserModel(
				ISF_PARAMETER_FILENAME)).getInvocationParameters();
		TraversableEncodingTree et3 = tetGen.generateEncodingTree(paramModel);
		System.out.println("***");
		System.out.println(et3.getCode().toStringForLoggerFileToBeParsed());

		// String theFileName = ISFLoggerServices.initializeLogFile("melanie");
		// Code c = encodingTree.getCode();
		// ISFLoggerServices.theLogger.finer(c.toStringForLoggerFileToBeParsed());
		// //
		// ISFLoggerServices.theLogger.finest(c.toStringForLoggerFileToBeParsed());
		//
		// XMLFileWrapper xmlFile = new XMLFileWrapper(new File(theFileName));
		//
		// Code code = xmlFile.getCode();
		// System.out.println(code.toStringForLoggerFileToBeParsed());

		// ISFLoggerServices.

	}
}
