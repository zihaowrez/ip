package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class UnrecognizedCommand extends Command {

    /**
     * Constructs a {@code UnrecognizedCommand} object with keyword UNRECOGNIZED.
     */
    public UnrecognizedCommand() {
        super(Keyword.UNRECOGNIZED);
    }

    /**
     * Pushes a message to the UI to indicate that the command is not recognized.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Sorry, but I don't know what that means :(");
    }

}
