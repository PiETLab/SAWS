package driverApplications_ISF;

import java.io.*;
import java.util.ArrayList;

public class ExecDemo {
	static public String[] runCommand(String cmd) throws IOException {

		// set up list to capture command output lines

		ArrayList list = new ArrayList();

		// start command running

		Process proc = Runtime.getRuntime().exec(cmd);

		// get command's output stream and
		// put a buffered reader input stream on it

		InputStream istr = proc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(istr));

		// read output lines from command

		String str;
		while ((str = br.readLine()) != null)
			list.add(str);

		// wait for command to terminate

		try {
			proc.waitFor();
		} catch (InterruptedException e) {
			System.err.println("process was interrupted");
		}

		// check its exit value

		if (proc.exitValue() != 0)
			System.err.println("exit value was non-zero");

		// close stream

		br.close();

		// return list of strings to caller

		return (String[]) list.toArray(new String[0]);
	}

	static public String[] runShellCommand(String commandToStartShell,
			String theShellCommand) throws IOException {

		// set up list to capture command output lines

		ArrayList list = new ArrayList();

		// start command running

		Process process = Runtime.getRuntime().exec(commandToStartShell);

		BufferedWriter outCommand = new BufferedWriter(new OutputStreamWriter(
				process.getOutputStream()));
		outCommand.write(theShellCommand, 0, theShellCommand.length());
		outCommand.newLine();
		outCommand.flush();
		try {
			process.waitFor();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		outCommand.close();

		// get command's output stream and
		// put a buffered reader input stream on it

		InputStream istr = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(istr));

		// read output lines from command

		String str;
		while ((str = br.readLine()) != null)
			list.add(str);

		// wait for command to terminate

		try {
			process.waitFor();
		} catch (InterruptedException e) {
			System.err.println("process was interrupted");
		}

		// check its exit value

		if (process.exitValue() != 0)
			System.err.println("exit value was non-zero");

		// close stream

		br.close();

		// return list of strings to caller

		return (String[]) list.toArray(new String[0]);
	}

	public static void main(String args[]) throws IOException {
		String capturedLine = "";

		try {

			// run a command
			String command = "setenv COMPUTERNAME "
					+ "`grep -A1 'ComputerName<'/Library/Preferences/SystemConfiguration/preferences.plist | grep -v key "
					+ "| sed s/\"[[:blank:]]*<\\/*string>\"//g";
			command = "pwd";
			command = "grep -A1 'ComputerName<' /Library/Preferences/SystemConfiguration/preferences.plist "
					+ " | grep -v key";
			command = "more /Library/Preferences/SystemConfiguration/preferences.plist ";

			String shellCommand = "/bin/bash";
			// String outlist[] = runShellCommand(shellCommand, command);
			String outlist[] = runCommand(command);

			// display its output

			boolean shouldCaptureNextLine = false;
			for (int i = 0; i < outlist.length; i++) {
				if (shouldCaptureNextLine) {
					capturedLine = outlist[i];
					break;
				}
				System.out.println(outlist[i]);
				shouldCaptureNextLine = (outlist[i].indexOf("ComputerName") != -1);
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		System.out.println(">>>>" + capturedLine);
		capturedLine = capturedLine.replaceAll("string", "");
		capturedLine = capturedLine.replaceAll("<", "");
		capturedLine = capturedLine.replaceAll(">", "");
		capturedLine = capturedLine.replaceAll("/", "");
		System.out.println(">>>>" + capturedLine);

	}
}
