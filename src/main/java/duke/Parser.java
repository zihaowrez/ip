package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EditCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.command.UnrecognizedCommand;

/**
 * Command parser.
 */
public class Parser {

    /**
     * Parses an input string to a {@code Command} object.
     * @param input the input string to parse, not null
     * @return a command, not null
     * @throws DukeException if the input does not have a correct format
     */
    public static Command parse(String input) throws DukeException {
        input = input.trim();
        String[] splited = input.split(" ", 2);
        String firstWord = splited[0];
        String remaining = "";
        if (splited.length == 2) {
            remaining = splited[1];
        }
        remaining = remaining.trim();
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (firstWord.equals("delete")) {
            if (!remaining.matches("-?\\d+")) {
                throw new DukeException("Must specify which task to delete");
            }
            return new DeleteCommand(Integer.parseInt(remaining));
        } else if (firstWord.equals("mark")) {
            if (!remaining.matches("-?\\d+")) {
                throw new DukeException("Must specify which task to mark");
            }
            return new MarkCommand(Integer.parseInt(remaining));
        } else if (firstWord.equals("unmark")) {
            if (!remaining.matches("-?\\d+")) {
                throw new DukeException("Must specify which task to unmark");
            }
            return new UnmarkCommand(Integer.parseInt(remaining));
        } else if (firstWord.equals("find")) {
            if (remaining.equals("")) {
                throw new DukeException("Must specify what to find");
            }
            return new FindCommand(remaining);
        } else if (firstWord.equals("edit")) {
            if (remaining.equals("")) {
                throw new DukeException("Must specify what to edit");
            } else if (!remaining.matches("-?\\d+ .+")) {
                throw new DukeException("Invalid format");
            }
            String[] indexText = remaining.split(" ", 2);
            return new EditCommand(Integer.parseInt(indexText[0]), indexText[1]);
        } else if (firstWord.equals("todo")) {
            if (remaining.equals("")) {
                throw new DukeException("The description of a todo cannot be empty");
            }
            return new TodoCommand(remaining);
        } else if (firstWord.equals("deadline")) {
            String[] desc_by = remaining.split(" /by ", 2);
            if (desc_by.length < 2) {
                throw new DukeException("Incorrect format");
            } else if (desc_by[0].length() == 0) {
                throw new DukeException("The description of a deadline cannot be empty");
            }
            DukeDateTime datetime = DukeDateTime.parse(desc_by[1].trim());
            return new DeadlineCommand(desc_by[0].trim(), datetime);
        } else if (firstWord.equals("event")) {
            String[] desc_at = remaining.split(" /at ", 2);
            if (desc_at.length < 2) {
                throw new DukeException("Incorrect format");
            } else if (desc_at[0].length() == 0) {
                throw new DukeException("The description of an event cannot be empty");
            }
            DukeDateTime datetime = DukeDateTime.parse(desc_at[1].trim());
            return new EventCommand(desc_at[0].trim(), datetime);
        } else {
            return new UnrecognizedCommand();
        }
    }

}
