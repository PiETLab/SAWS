package coreMemoApplication;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;
import invocationParametersRSVP.RSVPInvocationParameterModel;

import java.awt.*;

import addressBook.AddressBook;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import coreIndirectSelectionFacility.IndirectSelectionFacilityView;
import coreRSVP.RSVPController;
import coreRSVP.RSVPDisplayer;
import coreRSVP.RSVPTimerWrapper;
import encodingTrees.TraversableEncodingTree;
import encodingTrees.TraversableEncodingTreeGenerator;
import IndirectSelectionFacility.OnScreenKeyboardView;
import IndirectSelectionFacilityCommands.GoToReviewModeCommand;
import IndirectSelectionFacilityCommands.SignifyFinishedCommand;
import RSVP.EBook;
import RSVP.TwoLevelTokenizer;
import RSVPStates.RSVPState;

import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

import sourceSymbolSet.SourceSymbolSet;
import sourceSymbolSet.SourceSymbolSetGenerator;
import subjectLine.SubjectLineBank;
import userComposedMemo.UserComposedMemo;

import memoApplicationCommands.DefaultMemoFillerCommand;
import memoApplicationCommands.MemoApplicationCommand;
import memoApplicationCommands.GoToEditSubmenuCommand;
import memoApplicationCommands.MemoBodyGoToReviewSourceSymbolCommand;
import memoApplicationCommands.MemoBodySignifyAsFinishedSourceSymbolCommand;
import memoControl.EditMenuOption;
import memoControl.EditMenuOptionSet;

import org.apache.fop.hyphenation.HyphenationTree;

/**
 * 
 * @author mb
 * 
 */
public class MemoFillerController implements ActionListener, KeyListener {

	private TraversableEncodingTreeGenerator tetGen;
	private MemoFillerView memoFillerView;

	private UserComposedMemo theMemo;
	private GraphicsDevice device;

	private IndirectSelectionFacilityController addresseeSelectionController;
	private IndirectSelectionFacilityController memoFieldSelectionController;
	private IndirectSelectionFacilityController memoBodyController;
	private IndirectSelectionFacilityController subjectLineSelectionController;
	private IndirectSelectionFacilityController editMenuSelectionController;
	private RSVPController rsvpController;

	private MemoFillerInvocationParameterModel paramManagerMemoFiller;
	private IndirectSelectionFacilityInvocationParameterModel paramManagerAddresseeSelection;
	private IndirectSelectionFacilityInvocationParameterModel paramManagerMemoFieldSelection;
	private IndirectSelectionFacilityInvocationParameterModel paramManagerMemoBody;
	private IndirectSelectionFacilityInvocationParameterModel paramManagerSubjectLineSelection;
	private RSVPInvocationParameterModel paramManagerRSVP;
	private IndirectSelectionFacilityInvocationParameterModel paramManagerEditMenuSelection;

	/**
	 * @param view
	 * @param paramManagerMemoFiller
	 * @param paramManagerAddresseeSelection
	 * @param paramManagerMemoFieldSelection
	 * @param paramManagerMemoBody
	 * @param paramManagerSubjectLineSelection
	 */
	public MemoFillerController(
			MemoFillerView view,
			MemoFillerInvocationParameterModel paramManagerMemoFiller,
			IndirectSelectionFacilityInvocationParameterModel paramManagerAddresseeSelection,
			IndirectSelectionFacilityInvocationParameterModel paramManagerMemoFieldSelection,
			IndirectSelectionFacilityInvocationParameterModel paramManagerMemoBody,
			IndirectSelectionFacilityInvocationParameterModel paramManagerSubjectLineSelection,
			RSVPInvocationParameterModel paramManagerRSVP,
			IndirectSelectionFacilityInvocationParameterModel paramManagerEditMenuSelection) {
		this.paramManagerMemoFiller = paramManagerMemoFiller;
		this.paramManagerAddresseeSelection = paramManagerAddresseeSelection;
		this.paramManagerMemoFieldSelection = paramManagerMemoFieldSelection;
		this.paramManagerMemoBody = paramManagerMemoBody;
		this.paramManagerSubjectLineSelection = paramManagerSubjectLineSelection;
		this.paramManagerRSVP = paramManagerRSVP;
		this.paramManagerEditMenuSelection = paramManagerEditMenuSelection;

		System.out.println("init controller: " + this.getClass().getName());

		tetGen = new TraversableEncodingTreeGenerator();

		this.memoFillerView = view;
		this.paramManagerMemoFiller = paramManagerMemoFiller;

		theMemo = new UserComposedMemo(paramManagerMemoFiller);

		// if (invHelper.isRemoveButtonDecorators()) {
		if (true) {
			IndirectSelectionFacilityView.removeButtonDecorators();
		}

		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		device = env.getDefaultScreenDevice();

		this.getAddresseeSelectionController().addActionListener(this);
		this.getMemoBodyController().addActionListener(this);
		this.getSubjectLineSelectionController().addActionListener(this);
		this.getMemoFieldSelectionController().addActionListener(this);
		this.getRSVPController().addActionListener(this);
		this.getEditSubMenuSelectionController().addActionListener(this);

		// this.getMemoBodyController().appendToGloss(
		// "here is a sample text to start");

		// this.putViewToFront();

		// assign controller to listen
		// memoFieldSelectionView.getFocusReceivingComponent().
	}

	public IndirectSelectionFacilityController getEditSubMenuSelectionController() {
		if (editMenuSelectionController == null) {
			System.out.println("INITIALIZE: " + "editMenuSelectionController");
			// TraversableEncodingTree encodingTreeAB = tetGen.createInstance(
			// paramManagerMemoFieldSelection.getETConstructTechnique(),
			// getTheMemo().getFieldsAsSourceSymbolSet(), getTheMemo()
			// .getFieldsAsSourceSymbolSet().size());

			EditMenuOptionSet editMenuOptionSet = new EditMenuOptionSet();

			TraversableEncodingTree encodingTreeEM = tetGen.createInstance(
					paramManagerEditMenuSelection
							.getEncodingTreeAlgorithmicConstructionTechnique(),
					editMenuOptionSet, editMenuOptionSet.size());

			System.out.println(encodingTreeEM.getCode()
					.toStringForLoggerFileToBeParsed());

			OnScreenKeyboardView onScreenKeyboardEM = IndirectSelectionFacilityController
					.generateOnScreenKeyboardView(
							paramManagerEditMenuSelection, encodingTreeEM);
			IndirectSelectionFacilityView editMenuSelectionView = new IndirectSelectionFacilityView(
					paramManagerEditMenuSelection, onScreenKeyboardEM, device);
			editMenuSelectionController = new IndirectSelectionFacilityController(
					editMenuSelectionView, paramManagerEditMenuSelection,
					encodingTreeEM);
			// editMenuSelectionView.getFocusReceivingComponent().addKeyListener(
			// editMenuSelectionController);
		}
		return editMenuSelectionController;
	}

	public IndirectSelectionFacilityController getSubjectLineSelectionController() {
		if (subjectLineSelectionController == null) {
			System.out.println("INITIALIZE: "
					+ "subjectLineSelectionController");
			// TraversableEncodingTree encodingTreeAB = tetGen.createInstance(
			// paramManagerMemoFieldSelection.getETConstructTechnique(),
			// getTheMemo().getFieldsAsSourceSymbolSet(), getTheMemo()
			// .getFieldsAsSourceSymbolSet().size());

			SubjectLineBank subjectLines = paramManagerMemoFiller
					.getSubjectLineBank();

			TraversableEncodingTree encodingTreeSL = tetGen.createInstance(
					paramManagerSubjectLineSelection
							.getEncodingTreeAlgorithmicConstructionTechnique(),
					subjectLines, subjectLines.size());

			OnScreenKeyboardView onScreenKeyboardSL = IndirectSelectionFacilityController
					.generateOnScreenKeyboardView(
							paramManagerSubjectLineSelection, encodingTreeSL);
			IndirectSelectionFacilityView subjectLineSelectionView = new IndirectSelectionFacilityView(
					paramManagerSubjectLineSelection, onScreenKeyboardSL,
					device);
			subjectLineSelectionController = new IndirectSelectionFacilityController(
					subjectLineSelectionView, paramManagerSubjectLineSelection,
					encodingTreeSL);
			subjectLineSelectionView.getFocusReceivingComponent()
					.addKeyListener(subjectLineSelectionController);
		}
		return subjectLineSelectionController;
	}

	public IndirectSelectionFacilityController getMemoBodyController() {
		if (memoBodyController == null) {
			System.out.println("INITIALIZE: " + "memoBodyController");
			// TraversableEncodingTree encodingTreeAB = tetGen.createInstance(
			// paramManagerMemoFieldSelection.getETConstructTechnique(),
			// getTheMemo().getFieldsAsSourceSymbolSet(), getTheMemo()
			// .getFieldsAsSourceSymbolSet().size());

			TraversableEncodingTree encodingTreeMB = tetGen
					.generateEncodingTree(paramManagerMemoBody);
			OnScreenKeyboardView onScreenKeyboardMB = IndirectSelectionFacilityController
					.generateOnScreenKeyboardView(paramManagerMemoBody,
							encodingTreeMB);
			IndirectSelectionFacilityView memoBodyView = new IndirectSelectionFacilityView(
					paramManagerMemoBody, onScreenKeyboardMB, device);
			memoBodyController = new IndirectSelectionFacilityController(
					memoBodyView, paramManagerMemoBody, encodingTreeMB);
			// memoBodyView.getFocusReceivingComponent().addKeyListener(
			// memoBodyController);
		}
		return memoBodyController;
	}

	public IndirectSelectionFacilityController getAddresseeSelectionController() {
		if (addresseeSelectionController == null) {
			System.out.println("INITIALIZE: " + "addresseeSelectionController");
			// TraversableEncodingTree encodingTreeAB = tetGen.createInstance(
			// paramManagerMemoFieldSelection.getETConstructTechnique(),
			// getTheMemo().getFieldsAsSourceSymbolSet(), getTheMemo()
			// .getFieldsAsSourceSymbolSet().size());

			// TraversableEncodingTree encodingTreeAB2 = tetGen
			// .generateEncodingTree(paramManagerAddresseeSelection);
			AddressBook addressBook = paramManagerMemoFiller.getAddressBook();

			TraversableEncodingTree encodingTreeAB = tetGen.createInstance(
					paramManagerAddresseeSelection
							.getEncodingTreeAlgorithmicConstructionTechnique(),
					addressBook, addressBook.size());
			// tetGen.createInstance(
			// paramManagerAddresseeSelection.getETConstructTechnique(),
			// paramManagerAddresseeSelection.getSourceSymbolSet());
			// getTheMemo().getFieldsAsSourceSymbolSet(), getTheMemo()
			// .getFieldsAsSourceSymbolSet().size());

			OnScreenKeyboardView onScreenKeyboardAB = IndirectSelectionFacilityController
					.generateOnScreenKeyboardView(
							paramManagerAddresseeSelection, encodingTreeAB);
			IndirectSelectionFacilityView addresseeSelectionView = new IndirectSelectionFacilityView(
					paramManagerAddresseeSelection, onScreenKeyboardAB, device);
			addresseeSelectionController = new IndirectSelectionFacilityController(
					addresseeSelectionView, paramManagerAddresseeSelection,
					encodingTreeAB);
			// addresseeSelectionView.getFocusReceivingComponent().addKeyListener(
			// addresseeSelectionController);
		}
		return addresseeSelectionController;
	}

	public IndirectSelectionFacilityController getMemoFieldSelectionController() {
		if (memoFieldSelectionController == null) {
			// System.out.println("INITIALIZE: " +
			// "memoFieldSelectionController");
			TraversableEncodingTree encodingTreeMF = tetGen.createInstance(
					paramManagerMemoFieldSelection
							.getEncodingTreeAlgorithmicConstructionTechnique(),
					getTheMemo().getFieldsAsSourceSymbolSet(), getTheMemo()
							.getFieldsAsSourceSymbolSet().size());

			// System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
			// System.out.println(encodingTreeMF.toStringPlainTextLispStyle());
			// System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
			OnScreenKeyboardView onScreenKeyboardMF = IndirectSelectionFacilityController
					.generateOnScreenKeyboardView(
							paramManagerMemoFieldSelection, encodingTreeMF);
			IndirectSelectionFacilityView memoFieldSelectionView = new IndirectSelectionFacilityView(
					paramManagerMemoFieldSelection, onScreenKeyboardMF, device);

			memoFieldSelectionController = new IndirectSelectionFacilityController(
					memoFieldSelectionView, paramManagerMemoFieldSelection,
					encodingTreeMF);
			// memoFieldSelectionView.getFocusReceivingComponent().addKeyListener(
			// memoFieldSelectionController);
		}
		return memoFieldSelectionController;
	}

	public RSVPController getRSVPController() {
		if (rsvpController == null) {
			RSVPDisplayer rsvpView = new RSVPDisplayer(paramManagerRSVP, device);

			// rsvpController = new RSVPController(rsvpView,
			// new TwoLevelTokenizer(EBook.getDefaultEbook()),
			// paramManagerRSVP);

			rsvpController = new RSVPController(rsvpView,
					new TwoLevelTokenizer(), paramManagerRSVP);

		}
		return rsvpController;
	}

	public void actionPerformed(ActionEvent arg0) {
		Object theEventSource = null;
		// intercept SignifyFinishCommand from the ISF and reassign to
		// MemoFillerCommand
		if (arg0.getSource() instanceof SignifyFinishedCommand) {
			System.out.println("SignifyFinishedCommand");
			theEventSource = MemoBodySignifyAsFinishedSourceSymbolCommand
					.getInstance(this.getMemoBodyController().getTextGloss());
		} else if (arg0.getSource() instanceof GoToReviewModeCommand) {
			System.out.println("go to review mode");
			theEventSource = MemoBodyGoToReviewSourceSymbolCommand
					.getInstance(this.getMemoBodyController().getTextGloss());
		} else if (arg0.getSource() instanceof RSVPTimerWrapper) {
			if (getRSVPController().isCurrentStateEndOfTextReachedState()
					|| getRSVPController().isCurrentStatePausedState()) {
				System.out.println("Need to present edit menu choices here");
				theEventSource = GoToEditSubmenuCommand.getInstance();
			}
		} else {
			theEventSource = arg0.getSource();
		}

		// System.out.println("in actionPerformed: " + theEventSource);

		if (theEventSource instanceof MemoApplicationCommand) {
			MemoApplicationCommand command = (MemoApplicationCommand) theEventSource;
			command.performMemoFillerCommand(this);
		}

		theMemo.writeToPDF();
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public UserComposedMemo getTheMemo() {
		return theMemo;
	}

	public void launchApp() {
		System.out.println("Launching application. ");
		this.switchToSubController(getMemoFieldSelectionController());
	}

	public void switchToSubController(
			IndirectSelectionFacilityController controller) {
		System.out.println("Switching to controller: "
				+ controller.getView().getTitle());

		controller.putViewToFront();
		controller.initToStartOfFokusCycleAndLaunch();
		// System.out.println("Report:\n"
		// + getAddresseeSelectionController().getView().getTitle() + "\t"
		// + getAddresseeSelectionController().isActive() + "\n"
		// + getMemoBodyController().getView().getTitle() + "\t"
		// + getMemoBodyController().isActive() + "\n"
		// + getEditSubMenuSelectionController().getView().getTitle()
		// + "\t" + getEditSubMenuSelectionController().isActive() + "\n"
		// + getSubjectLineSelectionController().getView().getTitle()
		// + "\t" + getSubjectLineSelectionController().isActive() + "\n"
		// + getMemoFieldSelectionController().getView().getTitle() + "\t"
		// + getMemoFieldSelectionController().isActive() + "\n");
	}

	public void switchToSubController(RSVPController controller) {
		System.out.println("Switching to controller: "
				+ controller.getView().getTitle());
		controller.putViewToFront();
		controller.unpause();
	}

	public void stop() {
		this.getMemoBodyController().stopFocusAdvancement();
	}

	public void showMessageAndTerminate() {

		Runnable doDisplayAndGracefulExit = new Runnable() {
			public void run() {
				memoFillerView.displayExitMessage();
				memoFillerView
						.configureForDisplay(paramManagerMemoFieldSelection
								.isFullScreen());
				((JFrame) memoFillerView).toFront();// grabFocus();

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				memoFillerView.displayPDFLocationMessage();

				// System.exit(0);

			}
		};

		Runnable doGracefulExit = new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		};
		new Thread(doDisplayAndGracefulExit).start();
		// new Thread(doGracefulExit).start();
	}

	// public void putViewToFront() {
	// // ((JFrame) rsvpDisplay).requestFocusInWindow();//grabFocus();
	// memoFillerView.configureForDisplay(paramManagerMemoFieldSelection
	// .isFullScreen());
	// ((JFrame) memoFillerView).toFront();// grabFocus();
	// // ((JFrame) memoFillerView).toFront();// grabFocus();
	// // memoFillerView.getFocusReceivingComponent().requestFocus();
	//
	// }

}