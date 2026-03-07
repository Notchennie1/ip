package mitch;

import mitch.task.Task;
import mitch.task.ToDo;
import mitch.task.Deadlines;
import mitch.task.Events;
import mitch.exception.MitchException;

import java.util.ArrayList;

/**
 * Handles the interpretation and execution of user commands.
 */
public class Parser {
    /**
     * Parses the user inputs, executes actions and update storage
     * @param input     The command type by user
     * @param tasks     The current list of tasks to be modified
     * @param ui        The user interface
     * @param storage   The storage system used to save the list
     * @return          only if command is bye
     * @throws MitchException   If user types an unrecognisable command
     */
    public static boolean parseCommand(String input, TaskList tasks, Ui ui, Storage storage) throws MitchException {
        if (input.equals("bye")) {
            ui.showGoodbye();
            return true;
        }

        if (input.equals("list")) {
            ui.showLine();
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.get(i));
            }
            ui.showLine();
            return false;
        }

        if (input.startsWith("mark ")) {
            if (input.trim().length() <= 4) {
                throw new MitchException("OOPS!!! Please specify which task to mark.");
            }
            int index = Integer.parseInt(input.substring(5).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MitchException("OOPS!!! That task number does not exist.");
            }
            tasks.get(index).markDone();
            storage.save(tasks);

            ui.showLine();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(tasks.get(index).toString());
            ui.showLine();
            return false;
        }

        if (input.startsWith("unmark ")) {
            if (input.trim().length() <= 7) {
                throw new MitchException("OOPS!!! Please specify which task to unmark.");
            }
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MitchException("OOPS!!! That task number does not exist.");
            }
            tasks.get(index).unmarkDone();
            storage.save(tasks);

            ui.showLine();
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage(tasks.get(index).toString());
            ui.showLine();
            return false;
        }

        if (input.startsWith("todo ")) {
            if (input.trim().length() <= 4) {
                throw new MitchException("OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.add(new ToDo(input));
            storage.save(tasks);
            printAddedTask(tasks, ui);
            return false;
        }

        if (input.startsWith("deadline ")) {
            if (input.trim().length() <= 8) {
                throw new MitchException("OOPS!!! The description of a deadline cannot be empty.");
            }
            tasks.add(new Deadlines(input));
            storage.save(tasks);
            printAddedTask(tasks, ui);
            return false;
        }

        if (input.startsWith("event ")) {
            if (input.trim().length() <= 5) {
                throw new MitchException("OOPS!!! The description of an event cannot be empty.");
            }
            tasks.add(new Events(input));
            storage.save(tasks);
            printAddedTask(tasks, ui);
            return false;
        }

        if (input.startsWith("delete ")) {
            if (input.trim().length() <= 7) {
                throw new MitchException("OOPS!!! Please specify which task to delete.");
            }
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MitchException("OOPS!!! That task number does not exist.");
            }
            Task removedTask = tasks.remove(index);
            storage.save(tasks);

            ui.showLine();
            ui.showMessage(" Noted. I've removed this task:");
            ui.showMessage("   " + removedTask);
            ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
            return false;
        }

        if (input.startsWith("find ")) {
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new MitchException("OOPS!!! The keyword for find cannot be empty.");
            }

            ArrayList<Task> matchingTasks = tasks.find(keyword);

            ui.showLine();
            if (matchingTasks.isEmpty()) {
                ui.showMessage(" No matching tasks found in your list.");
            } else {
                ui.showMessage(" Here are the matching tasks in your list:");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    ui.showMessage(" " + (i + 1) + "." + matchingTasks.get(i));
                }
            }
            ui.showLine();
            return false;
        }
        throw new MitchException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * A helper method to display confirmation message after actions
     * @param tasks The list of tasks
     * @param ui    The User interface to print tasks
     */
    private static void printAddedTask(TaskList tasks, Ui ui) {
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(tasks.get(tasks.size() - 1).toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }
}