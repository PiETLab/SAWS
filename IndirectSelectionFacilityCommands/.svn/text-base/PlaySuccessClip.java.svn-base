package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import coreIndirectSelectionFacility.IndirectSelectionFacilityController;

public class PlaySuccessClip extends IndirectSelectionFaciltyCommand {

	Thread theThread;

	public PlaySuccessClip(IndirectSelectionFacilityController tcf) {
		Runnable msgDisplay = new MyRunnable2(tcf);
		theThread = new Thread(msgDisplay);
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) {
		theThread.start();
		return false;
	}

	public Thread getThread() {
		return theThread;
	}
}

class MyRunnable2 implements Runnable {
	IndirectSelectionFacilityController tcf;

	public MyRunnable2(IndirectSelectionFacilityController tcf) {
		this.tcf = tcf;
	}

	public void run() {
		tcf.clearGloss();
		// tcf.makeViewEmptyAndResetGloss();
		tcf.appendToGloss("Yipee");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		tcf.clearGloss();
		// tcf.makeViewEmptyAndResetGloss();

	}

}
