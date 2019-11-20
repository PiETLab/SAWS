package driverApplications_ISF;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.util.Map;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;
import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreIndirectSelectionFacility.IndirectSelectionFacilityLauncher;

import SoftwareDeployment.UserSpecificModel;

public class testLoggerDirectory {

	public static void main(String[] args) throws UnknownHostException {

		// // setenv COMPUTERNAME `grep -A1 'ComputerName<'
		// // /Library/Preferences/SystemConfiguration/preferences.plist | grep
		// -v
		// // key | sed s/"[[:blank:]]*<\/*string>"//g`
		// String computerName = System.getenv("COMPUTERNAME");
		System.out.println(System.getenv("COMPUTERNAME"));
		System.out.println(ISFLoggerServices.getOperatingSystem());
		//
		System.out.println(java.net.InetAddress.getLocalHost().getHostName());

		//
		// String baseDirectory = "";
		//
		// String subDir = System.getProperty("user.home");// + File.separator
		// // + SUB_DIR_NAME;
		// System.out.println(subDir);
		//
		// // File theSubDir = new File(subDir);
		// // if (!theSubDir.exists())
		// File subDirectory = new File(subDir);
		//
		// // Map<String, String> env = System.getenv();
		// // for (String envName : env.keySet()) {
		// // System.out.format("%s=%s%n", envName, env.get(envName));
		// // }
		//
		// String command = "setenv COMPUTERNAME "
		// +
		// "`grep -A1 'ComputerName<'/Library/Preferences/SystemConfiguration/preferences.plist | grep -v key "
		// + "| sed s/\"[[:blank:]]*<\\/*string>\"//g";
		// // + "| sed s/\"[[:blank:]]*<\/*string>\"//g`";
		//
		// // Process process;
		// // Runtime runtime = Runtime.getRuntime();
		// //
		// // try {
		// // String fullCommand = command;
		// // process = runtime.exec(fullCommand, null, subDirectory);
		// // showOnConsole(process);
		// // System.out.println("Invoked: " + fullCommand);
		// // } catch (IOException e) {
		// // e.printStackTrace();
		// // System.exit(0);
		// // }
		//
		// try {
		// Process process = Runtime.getRuntime().exec("/bin/bash");
		// InputStream cmd_output = process.getInputStream();
		// BufferedWriter outCommand = new BufferedWriter(
		// new OutputStreamWriter(process.getOutputStream()));
		// outCommand.write(command, 0, command.length());
		// outCommand.newLine();
		// int ch;
		// StringBuffer ta = new StringBuffer();
		// StringBuffer sb = new StringBuffer(512);
		// while ((ch = cmd_output.read()) != -1) {
		// sb.append((char) ch);
		// }
		// ta.append(sb.toString());
		// System.out.println(sb.toString());
		// outCommand.write("exit", 0, 4);
		// outCommand.newLine();
		// outCommand.flush();
		// process.waitFor();
		// outCommand.close();
		// } catch (IOException e) {
		//
		// }
		// // ProcessBuilder pb = new ProcessBuilder("myCommand", "myArg1",
		// // "myArg2");
		// // Map<String, String> env = pb.environment();
		// // env.put("VAR1", "myValue");
		// // env.remove("OTHERVAR");
		// // env.put("VAR2", env.get("VAR1") + "suffix");
		// // pb.directory(new File("myDir"));
		// // try {
		// // Process p = pb.start();
		// // } catch (IOException e) {
		// // e.printStackTrace();
		// // }
		// catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
	}

	public static void showOnConsole(Process process) {

		String ls_str;
		DataInputStream ls_in = new DataInputStream(process.getInputStream());

		try {
			while ((ls_str = ls_in.readLine()) != null) {
				System.out.println(ls_str);
			}
		} catch (IOException e) {
			System.exit(0);
		}
	}

}
