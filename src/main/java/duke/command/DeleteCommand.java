package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code DeleteCommand} object with keyword DELETE.
     * @param index the index of the task to delete
     */
    public DeleteCommand(int index) {
        super(Keyword.DELETE);
        this.index = index;
    }

    /**
     * Deletes the task at the specified index in the list of tasks.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index <= tasks.size()) {
            ui.showMessage("Noted. I've removed this task:\n" + tasks.get(index - 1));
            tasks.remove(index - 1);
        } else {
            ui.showMessage("Index is invalid");
        }
    }
}
