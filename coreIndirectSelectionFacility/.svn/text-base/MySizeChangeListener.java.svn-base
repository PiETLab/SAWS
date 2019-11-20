package coreIndirectSelectionFacility;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MySizeChangeListener implements ComponentListener {

	private IndirectSelectionFacilityController controller;
	private boolean IS_VERBOSE = false;

	private boolean hasResizedAtLeastOnce = false;

	public MySizeChangeListener(IndirectSelectionFacilityController controller) {
		this.controller = controller;
	}

	public void componentHidden(ComponentEvent arg0) {
	}

	public void componentMoved(ComponentEvent arg0) {
	}

	public void componentResized(ComponentEvent arg0) {
		if (IS_VERBOSE) {
			System.out.println("Resizing... to "
					+ controller.getView().getSize());
		}

		// only after the display has been packed/displayed is it known what the
		// total real-estate is. Now we set the relative proportion of the
		// keyboard to the textdisplayer
		Dimension totalRealEstate = controller.getView().getSize();
		Dimension keyboardRealEstate = new Dimension(totalRealEstate.width,
				(int) Math.floor(totalRealEstate.height
						* controller.getParameterModel()
								.getKeyboardRealEstateProportion()));
		Dimension compositionWidgetRealEstate = new Dimension(
				totalRealEstate.width, totalRealEstate.height
						- keyboardRealEstate.height);
		if (IS_VERBOSE) {
			System.out.println(this.getClass().getName()
					+ " : TotalRealEstate: " + totalRealEstate);
			System.out.println(this.getClass().getName()
					+ " : keyboard real estate proportion: "
					+ controller.getParameterModel()
							.getKeyboardRealEstateProportion());
			System.out.println(this.getClass().getName()
					+ " : KeyboardRealEstate: " + keyboardRealEstate);
			System.out.println(this.getClass().getName()
					+ " : CompositionWidgetRealEstate: "
					+ compositionWidgetRealEstate);
		}
		controller.getView().getCompositionWidget().setSize(
				compositionWidgetRealEstate);
		controller.getView().getOnScreenKeyboard().setSize(keyboardRealEstate);
		// **** preferred size for composition widget does the trick, but not
		// for onscreenkeyboard - one of min/max does it
		controller.getView().getCompositionWidget().setPreferredSize(
				compositionWidgetRealEstate);
		controller.getView().getOnScreenKeyboard().setPreferredSize(
				keyboardRealEstate);
		controller.getView().getCompositionWidget().setMinimumSize(
				compositionWidgetRealEstate);
		controller.getView().getOnScreenKeyboard().setMinimumSize(
				keyboardRealEstate);
		controller.getView().getCompositionWidget().setMaximumSize(
				compositionWidgetRealEstate);
		controller.getView().getOnScreenKeyboard().setMaximumSize(
				keyboardRealEstate);

		if (!hasResizedAtLeastOnce) {
			controller.resizeOptimizedComponents();
		}
		hasResizedAtLeastOnce = true;
	}

	public void componentShown(ComponentEvent arg0) {
	}

}
