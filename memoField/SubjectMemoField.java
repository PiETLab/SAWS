package memoField;

import addressBook.Addressee;
import subjectLine.SubjectLine;
import memoApplicationCommands.CompleteTheSubjectFieldCommand;
import memoApplicationCommands.CompleteTheToFieldCommand;
import IndirectSelectionFacilityCommands.IndirectSelectionFaciltyCommand;


public class SubjectMemoField extends MemoField {

	private SubjectLine subjectLine;

	public SubjectMemoField(String nameOfMemoField) {
		super(nameOfMemoField);
	}

	public SubjectMemoField() {
	}
	
	public IndirectSelectionFaciltyCommand getVOCACommand() {
		return new CompleteTheSubjectFieldCommand(getNameOfField(), this);
	}
	
	public void setAssociatedValue(SubjectLine subjectLine) {
		super.setAssociatedValue(subjectLine.getFullName());
		this.subjectLine = subjectLine;
	}


}
