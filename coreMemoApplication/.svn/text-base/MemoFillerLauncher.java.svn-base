package coreMemoApplication;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersISF.IndirectSelectionFacilityUserModel;
import invocationParametersRSVP.RSVPInvocationParameterModel;
import invocationParametersRSVP.RSVPUserModel;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreIndirectSelectionFacility.IndirectSelectionFacilityView;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.OnScreenKeyboardView;
import RSVP.EBook;
import RSVP.RSVPLauncher;
import SoftwareDeployment.UserSpecificModel;

/**
 * 
 * This class implements a runnable that starts up the RSVP app. For the default
 * initial state of the RSVP app, see the RSVPController class docs.
 * 
 * This runnable is intended to serve as the parameter to the
 * javax.swing.SwingUtilities.invokeLater(Runnable) method. This ensures that
 * this RSVP app is placed on the event dispatching thread).
 * 
 * @author mb
 * 
 */
public class MemoFillerLauncher implements Runnable {

	private MemoFillerInvocationParameterModel memoParamModel;
	private MemoFillerController memoFillerController;

	private MemoFillerView view;
	private IndirectSelectionFacilityController isfController;

	IndirectSelectionFacilityInvocationParameterModel isfParamModel;

	public MemoFillerLauncher(
			MemoFillerInvocationParameterModel params,
			IndirectSelectionFacilityInvocationParameterModel isfForMemoParamModel) {
		this.memoParamModel = params;
		this.isfParamModel = isfForMemoParamModel;
	}

	public void run() {
		System.out.println("Running the launcherx: "
				+ this.getClass().getName());
		System.out.println("PDF files can be found in: "
				+ this.memoParamModel.getPDFDirectory());

		// if (isfParamModel.isRemoveButtonDecorators()) {
		// IndirectSelectionFacilityView.removeButtonDecorators();
		// }
		// // instantiation of the encodingTree entails instantiations of
		// JButtons,
		// // since the source symbols are also Jbuttons
		// TraversableEncodingTreeGenerator tetGen = new
		// TraversableEncodingTreeGenerator();
		// TraversableEncodingTree encodingTree = tetGen
		// .generateEncodingTree(isfParamModel);
		// OnScreenKeyboardView onScreenKeyboard =
		// IndirectSelectionFacilityController
		// .generateOnScreenKeyboardView(isfParamModel, encodingTree);
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();

		view = new MemoFillerView(memoParamModel, device);

		// this needs to happen after the view is displayed
		// isfController = new IndirectSelectionFacilityController(view,
		// isfParamModel, encodingTree);

		String fullScreenOption = " -fu false";
		int dwellTime = 1500;

		String MEMO_FIELD_PARAM = "-eh false "
				+ "-as actionScheme.PassiveFA_ActiveSelection_CompositionMode_AutomaticStartForTraversal_NoLogging "
				+ "-te alg -ec encodingTrees.HuffmanEqualCosts -ea 5 "
				+ "-sl MemoFiller.MemoFieldsToBeCompletedSet "
				+ "-di displayScheme.ShowOneByOneWholeTraversal "
				+ "-kb buttonLayouts.MeaganKeyboardLayoutForMemoFields "
				+ "-fa passive " + "-dt " + dwellTime + " -rb true "
				+ "-ff sansserif -kp 1.00 -tt MemoFields -ha 2"
				+ fullScreenOption;

		// -el true -as
		// actionScheme.PassiveFA_ActiveSelection_CompositionMode_ExplicitStartForFirstTraversal_ImplicitLogging
		// -ui Meagan -te manual -mt
		// encodingTrees.AlphabeticET_MeaganSubset_2Level -ec RowColFromKB -sl
		// MeaganCompositionSet -di
		// displayScheme.ShowOneByOne_AllAtOnceWithHighlightingForLeaves_Variant3
		// -kb MeaganKeyboardLayout -fa passive -dt 500 -rb true -ff
		// CopperplateGothic-Light -kp 0.50 -fu false -bg WHITE -fg BLACK

		IndirectSelectionFacilityInvocationParameterModel paramManagerMemoFieldSelection = (IndirectSelectionFacilityInvocationParameterModel) (new IndirectSelectionFacilityUserModel(
				MEMO_FIELD_PARAM, false)).getInvocationParameters();

		String ADDRESSEE_PARAM = "-eh false "
				+ "-as actionScheme.PassiveFA_ActiveSelection_CompositionMode_AutomaticStartForTraversal_NoLogging "
				+ "-te alg -ec encodingTrees.HuffmanEqualCosts -ea 6 "
				// + "-sl MemoFiller.MeaganAddressBookSet " source symbol set
				// will be derived later
				+ "-di displayScheme.ShowOneByOneWholeTraversal "
				+ "-kb buttonLayouts.MeaganKeyboardLayoutForMemoFields "
				+ "-fa passive -dt " + dwellTime + " -rb true "
				+ "-ff sansserif -kp 1.00 -tt Addressees -ha 2"
				+ fullScreenOption;
		// "-eh false -te alg -ec HuffmanEqualCosts -ea 6 -sl MemoFiller.MeaganAddressBookSet -di IndirectSelectionFacility.ShowKeyboardWithAsFewElementsAsPossible -kb MeaganKeyboardLayoutForMemoFields -fa passive -dt 1000 -rb true -ff sansserif -kp 1.00 -tt Addressees -ha 2";
		IndirectSelectionFacilityInvocationParameterModel paramManagerAddresseeSelection = (IndirectSelectionFacilityInvocationParameterModel) (new IndirectSelectionFacilityUserModel(
				ADDRESSEE_PARAM, false)).getInvocationParameters();

		String SUBJECT_LINE_PARAM = "-eh false "
				+ "-as actionScheme.PassiveFA_ActiveSelection_CompositionMode_AutomaticStartForTraversal_NoLogging "
				+ "-te alg -ec encodingTrees.HuffmanEqualCosts -ea 6 "
				// + "-sl MemoFiller.MeaganAddressBookSet "
				+ "-di displayScheme.ShowOneByOneWholeTraversal "
				+ "-kb buttonLayouts.MeaganKeyboardLayoutForMemoFields "
				+ "-fa passive -dt " + dwellTime + " -rb true "
				+ "-ff sansserif -kp 1.00 -tt SubjectLines -ha 2"
				+ fullScreenOption;
		// "-eh false -te alg -ec HuffmanEqualCosts -ea 6 -sl MemoFiller.MeaganAddressBookSet -di IndirectSelectionFacility.ShowKeyboardWithAsFewElementsAsPossible -kb MeaganKeyboardLayoutForMemoFields -fa passive -dt 1000 -rb true -ff sansserif -kp 1.00 -tt SubjectLines -ha 2";
		IndirectSelectionFacilityInvocationParameterModel paramManagerSubjectLineSelection = (IndirectSelectionFacilityInvocationParameterModel) (new IndirectSelectionFacilityUserModel(
				SUBJECT_LINE_PARAM, false)).getInvocationParameters();

		final String ISF_PARAM_FILE = "isf-parameters-compose-for-memo.txt";
		//final String ISF_PARAM_FILE = "isf-parameters-compose.txt";
		final String ISF_PARAMETER_FILENAME = UserSpecificModel
				.getDefaultDirectoryForParameterFiles()
				+ ISF_PARAM_FILE;

		UserSpecificModel um = new IndirectSelectionFacilityUserModel(
				ISF_PARAMETER_FILENAME);
		// String MEMO_BODY_PARAM =
		// "-eh false -te alg -ec HuffmanEqualCosts -ea 6 -sl MemoFiller.MeaganAddressBookSet -di IndirectSelectionFacility.ShowKeyboardWithAsFewElementsAsPossible -kb MeaganKeyboardLayoutForMemoFields -fa passive -dt 600 -rb true -ff sansserif -kp 1.00 -tt Addressees";
		IndirectSelectionFacilityInvocationParameterModel paramManagerMemoBody = (IndirectSelectionFacilityInvocationParameterModel) um
				.getInvocationParameters();

		String EDIT_MENU_PARAM = "-eh false -te alg -ec encodingTrees.HuffmanEqualCosts -ea 3 "
				+ "-as actionScheme.PassiveFA_ActiveSelection_CompositionMode_AutomaticStartForTraversal_NoLogging "
				+ "-ss MemoFiller.EditMenuOptionSet "
				+ "-di displayScheme.ShowOneByOneWholeTraversal "
				// +
				// "-di displayScheme.ShowKeyboardWithAsFewElementsAsPossible "
				+ "-kb buttonLayouts.MeaganKeyboardLayoutForMemoFields "
				+ "-fa passive -dt "
				+ dwellTime
				+ " -rb true "
				+ "-ff sansserif -kp 1.00 -tt EditMenu -ha 2"
				+ fullScreenOption;

		IndirectSelectionFacilityInvocationParameterModel paramManagerEditMenuSelection = (IndirectSelectionFacilityInvocationParameterModel) (new IndirectSelectionFacilityUserModel(
				EDIT_MENU_PARAM, false)).getInvocationParameters();

		final String RSVP_PARAM_FILE = "rsvp-parameters.txt";
		final String RSVP_PARAMETER_FILENAME = UserSpecificModel
				.getDefaultDirectoryForParameterFiles()
				+ RSVP_PARAM_FILE;

		// final String RSVP_PARAMETER_FILENAME =
		// "/Users/mb/Documents/workspace-new2/Prj-Meagan/Parameters/rsvp-parameters.txt";
		UserSpecificModel umRSVP = new RSVPUserModel(RSVP_PARAMETER_FILENAME);
		RSVPInvocationParameterModel paramManagerRSVP = (RSVPInvocationParameterModel) umRSVP
				.getInvocationParameters();

		// RSVPInvocationParameterModel paramManagerRSVP = new
		// RSVPInvocationParameterModel(
		// RSVP_PARAMETER_FILENAME);

		memoFillerController = new MemoFillerController(view, memoParamModel,
				paramManagerAddresseeSelection, paramManagerMemoFieldSelection,
				paramManagerMemoBody, paramManagerSubjectLineSelection,
				paramManagerRSVP, paramManagerEditMenuSelection);
		// memoFillerController.putViewToFront();

		// view.getFocusReceivingComponent().addKeyListener(memoFillerController);
		// view.getFocusReceivingComponent().requestFocus();

		// /////// orig below
	}

	public MemoFillerController getController() {
		return memoFillerController;
	}

}
