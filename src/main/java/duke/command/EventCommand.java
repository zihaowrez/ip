package duke.command;

import duke.DukeDateTime;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A Command that adds an event when executed.
 */
public class EventCommand extends Command {

    private final String description;
    private final DukeDateTime at;

    /**
     * Constructs a {@code EventCommand} object.
     * @param description the description of the event
     * @param at a {@code DukeDateTime object specifying the time of the event}
     */
    public EventCommand(String description, DukeDateTime at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Adds an event into the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        Task t = new Event(description, at);
        tasks.add(t);
        return "Got it. I've added this task:\n  " + t
                + "\nNow you have " + tasks.size() + " task(s) in your list.";
    }

    /**
     * Indicates that the program should not be exited.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
