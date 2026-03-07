package mitch;

import mitch.exception.MitchException;

/**
 * Main class for Mitch Chatbot
 * Initialises Main Execution loop
 */
public class Mitch {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Mitch Instance with file path for storage
     * @param filePath
     */
    public Mitch(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading file. Starting with an empty task list.");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application.
     * Continuously reads user input, parses commands, and handles potential exceptions
     * until the exit command is given.
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
                ui.showError(" OOPS!!! Please enter a valid task number.");
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showError(" OOPS!!! That task number does not exist.");
            }
        }
    }

    /**
     * Start of Mitch
     * @param args
     */
    public static void main(String[] args) {
        new Mitch("./data/mitch.txt").run();
    }
}