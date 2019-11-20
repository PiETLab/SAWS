package memoField;

import addressBook.Addressee;
import memoApplicationCommands.CompleteTheToFieldCommand;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;

public class ToMemoField extends MemoField {

	private Addressee addressee;

	public ToMemoField(String nameOfMemoField) {
		super(nameOfMemoField);
	}

	public IndirectSelectionFaciltyCommand getVOCACommand() {
		return new CompleteTheToFieldCommand(getNameOfField(), this);
	}

	public void setAssociatedValue(Addressee addressee) {
		super.setAssociatedValue(addressee.getFullName());
		this.addressee = addressee;
	}

	public String getTextLabel() {
		if (this.getAssociatedValue() == null) {
			return this.getNameOfField() + FORMATTER;
		} else {
			return "<html>" + this.getNameOfField() + FORMATTER + "<br>"
					+ this.getAssociatedValue() + "</html>";
		}
	}

}
