package mitch;

import mitch.exception.MitchException;

public class Mitch {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Mitch(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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

    public static void main(String[] args) {
        new Mitch("./data/mitch.txt").run();
    }
}