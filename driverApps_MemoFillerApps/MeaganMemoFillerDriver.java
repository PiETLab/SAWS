package driverApps_MemoFillerApps;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import coreMemoApplication.MemoFillerController;
import coreMemoApplication.MemoFillerInvocationParameterModel;
import coreMemoApplication.MemoFillerLauncher;
import IntegrationClasses.MemoFillerUserModel;
import SoftwareDeployment.UserSpecificModel;

public class MeaganMemoFillerDriver {

	public static void main(String[] args) {

		final String PARAM_FILE = "memo-parameters.txt";

		final String MEMO_FILLER_PARAMETER_FILENAME = UserSpecificModel
				.getDefaultDirectoryForParameterFiles()
				+ PARAM_FILE;

		// String MEMO_FILLER_PARAMETER_FILENAME =
		// "/Users/mb/Documents/workspace-new2/Prj-Meagan/Parameters/memo-parameters.txt";
		UserSpecificModel um = new MemoFillerUserModel(
				MEMO_FILLER_PARAMETER_FILENAME);
		MemoFillerInvocationParameterModel memoParamModel = (MemoFillerInvocationParameterModel) um
				.getInvocationParameters();

		// final String ISF_PARAMETER_FILENAME =
		// "/Users/mb/Documents/workspace/Prj-Meagan/Parameters/isf-for-memofields-parameters.txt";
		// UserSpecificModel um2 = new IndirectSelectionFacilityUserModel(
		// ISF_PARAMETER_FILENAME);
		// IndirectSelectionFacilityInvocationParameterModel isfParamModel =
		// (IndirectSelectionFacilityInvocationParameterModel) um2
		// .getInvocationParameters();
		IndirectSelectionFacilityInvocationParameterModel isfParamModel = null;

		MemoFillerLauncher memoFillerLauncher = new MemoFillerLauncher(
				memoParamModel, isfParamModel);

		javax.swing.SwingUtilities.invokeLater(memoFillerLauncher);

		while (memoFillerLauncher.getController() == null) {
			// wait until the event dispatching thread has dealt with the
			// previous method invocation
		}

		final MemoFillerController memoFillerController = memoFillerLauncher
				.getController();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				memoFillerController.launchApp();
			}
		});
	}
}