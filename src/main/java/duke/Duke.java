package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke instance that loads a TaskList from Storage.
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.tasks = storage.loadTasks();
    }

    /**
     * Runs the Duke program from CLI.
     * 
     * @param args options. (Currently none)
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        Ui ui = Ui.getInstance();

        ui.printLine("Hello! I'm Duke\nWhat can I do for you?");

        boolean isRunning = true;
        while (isRunning) {
            String input = ui.getNextLine();

            try {
                Command c = Parser.parse(input);
                String output = c.execute(duke.tasks, duke.storage);
                ui.printLine(output);

                isRunning = !c.isExit();
            } catch (DukeException e) {
                ui.printLine(e.getMessage());
            }
        }
    }

    /**
     * Parses and execute the input given, and returns a response.
     * 
     * @param input a String from the user
     * @return a String as a response
     */
    public String getResponse(String input) {
        String output;

        try {
            Command c = Parser.parse(input);
            output = c.execute(this.tasks, this.storage);
        } catch (DukeException e) {
            output = e.getMessage();
        }

        assert output != null : "Response string should not be null";

        return output;
    }
}
