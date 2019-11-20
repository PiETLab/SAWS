package IndirectSelectionFacilityCommands;

import invocationParametersISF.IndirectSelectionFacilityInvocationParameterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import experimentHelper.ExperimentHelper;

import IndirectSelectionFacility.PassiveFokusAdvancerSwing;

public class SendToTTSCommand extends IndirectSelectionFaciltyCommand {

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {

		// replace this with code to invoke TTS module
		tcf.getView().getCompositionWidget()
				.setDisplayWithAppropriateSubPortionOfGloss("{SEND To TTS}");
		// tcf.synth.speakPlainText(textGloss, null);
		// tcf.synth.speakPlainText("hello hello hello", null);

		// PassiveFokusAdvancerSwing.stop();

		System.out.println("TextCompositionFacilityFrame:\tTrigger TTS Module");
		String textGloss = tcf.getView().getCompositionWidget().getTheText();
		System.out.println("Contents: " + textGloss);

		// tcf.getView().getOnScreenKeyboard().re().reset();//.resetAppearance();

		tcf.getView().getCompositionWidget().resetText();// .setText(tcf.getView().DEFAULT_CARAT);

		// tcf.getView().onScreenKeyboard.keyboard.resetFokus();
		tcf.resetFokus();// .resetAppearance();

		if (tcf.isWithPassiveFocusAdvancement()) {
			// PassiveFokusAdvancerSwing.initToStartOfFokusCycle();
			tcf.initToStartOfFokusCycleAndLaunch();

		}

		return false;
	}

}
