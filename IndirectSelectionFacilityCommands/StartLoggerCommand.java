package IndirectSelectionFacilityCommands;

import java.awt.event.ActionEvent;
import java.io.IOException;

import coreIndirectSelectionFacility.ISFLoggerServices;
import coreIndirectSelectionFacility.IndirectSelectionFacilityController;
import encodingTrees.Code;

public class StartLoggerCommand extends IndirectSelectionFaciltyCommand {

	private final String subjectID;

	public StartLoggerCommand(String _subjectID) {
		subjectID = _subjectID;
	}

	@Override
	public boolean execute(IndirectSelectionFacilityController tcf,
			ActionEvent ae) throws SecurityException, IOException {

		// while (getLogger().getHandlers().length != 0) {
		// getLogger().getHandlers()[0].flush();
		// getLogger().getHandlers()[0].close();
		// getLogger().removeHandler(getLogger().getHandlers()[0]);
		// }

		ISFLoggerServices.initializeLogFile(getLogger(), subjectID);

		// String fileName = subDirectory.getAbsolutePath() + File.separator
		// + getFileName();
		// FileHandler fh = new FileHandler(fileName);
		// getLogger().addHandler(fh);
		// getLogger().setLevel(Level.ALL);
		// getLogger().info(
		// "Starting Experiment Logging.  Using filename: " + fileName);

		Code c = tcf.getCode();
		getLogger().finer(c.toStringForLoggerFileToBeParsed());

		return false;
	}

}
