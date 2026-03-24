package mitch;

import mitch.exception.MitchException;

/**
 * The main class that sets up and runs the Mitch chatbot.
 */
public class Mitch {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Mitch chatbot using the given file path to save data.
     *
     * @param filePath The path where the task list will be saved.
     */
    public Mitch(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the chatbot and keeps asking for user commands until they exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            if (input.trim().isEmpty()) continue;

            try {
                isExit = Parser.parseCommand(input, tasks, ui, storage);
            } catch (MitchException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError(" OOPS!!! Please enter a valid task number.\n Example: mark 2");
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showError(" OOPS!!! That task number does not exist in your list.");
            }
        }
    }

    /**
     * The main method to start the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Mitch("./data/mitch.txt").run();
    }
}