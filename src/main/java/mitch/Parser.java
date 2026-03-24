package mitch;

import mitch.task.Task;
import mitch.task.ToDo;
import mitch.task.Deadlines;
import mitch.task.Events;
import mitch.exception.MitchException;

import java.util.ArrayList;

/**
 * Understands and processes the user's input commands.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding action.
     *
     * @param input The raw command typed by the user.
     * @param tasks The current list of tasks.
     * @param ui The Ui object used to print messages.
     * @param storage The Storage object used to save changes.
     * @return True if the user typed "bye" to exit the program, false otherwise.
     * @throws MitchException If the user input is invalid or missing information.
     */
    public static boolean parseCommand(String input, TaskList tasks, Ui ui, Storage storage) throws MitchException {
        if (input.equals("bye")) {
            ui.showGoodbye();
            return true;
        }

        if (input.equals("list")) {
            ui.showLine();
            if (tasks.size() == 0) {
                ui.showMessage(" Your task list is currently empty!");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    ui.showMessage(" " + (i + 1) + ". " + tasks.get(i));
                }
            }
            ui.showLine();
            return false;
        }

        if (input.startsWith("mark ")) {
            if (input.trim().length() <= 4) {
                throw new MitchException("OOPS!!! Please specify which task to mark.\n Please use: mark <task_number>");
            }
            int index = Integer.parseInt(input.substring(5).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MitchException("OOPS!!! That task number does not exist.");
            }
            tasks.get(index).markDone();
            storage.save(tasks);

            ui.showLine();
            ui.showMessage(" Nice! I've marked this task as done:");
            ui.showMessage("   " + tasks.get(index).toString());
            ui.showLine();
            return false;
        }

        if (input.startsWith("unmark ")) {
            if (input.trim().length() <= 7) {
                throw new MitchException("OOPS!!! Please specify which task to unmark.\n Please use: unmark <task_number>");
            }
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MitchException("OOPS!!! That task number does not exist.");
            }
            tasks.get(index).unmarkDone();
            storage.save(tasks);

            ui.showLine();
            ui.showMessage(" OK, I've marked this task as not done yet:");
            ui.showMessage("   " + tasks.get(index).toString());
            ui.showLine();
            return false;
        }

        if (input.startsWith("todo ")) {
            if (input.trim().length() <= 4) {
                throw new MitchException("OOPS!!! The description of a todo cannot be empty.\n Please use: todo <task>");
            }
            tasks.add(new ToDo(input));
            storage.save(tasks);
            printAddedTask(tasks, ui);
            return false;
        }

        if (input.startsWith("deadline ")) {
            if (!input.contains(" /by ")) {
                throw new MitchException("OOPS!!! Please use the format: deadline <task> /by <date/time>");
            }
            if (input.trim().length() <= 8) {
                throw new MitchException("OOPS!!! The description of a deadline cannot be empty.");
            }
            tasks.add(new Deadlines(input.substring(9)));
            storage.save(tasks);
            printAddedTask(tasks, ui);
            return false;
        }

        if (input.startsWith("event ")) {
            if (!input.contains(" /from ") || !input.contains(" /to ")) {
                throw new MitchException("OOPS!!! Please use the format: event <task> /from <start> /to <end>");
            }
            if (input.trim().length() <= 5) {
                throw new MitchException("OOPS!!! The description of an event cannot be empty.");
            }
            tasks.add(new Events(input.substring(6)));
            storage.save(tasks);
            printAddedTask(tasks, ui);
            return false;
        }

        if (input.startsWith("delete ")) {
            if (input.trim().length() <= 7) {
                throw new MitchException("OOPS!!! Please specify which task to delete.\n Please use: delete <task_number>");
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
                throw new MitchException("OOPS!!! The keyword for find cannot be empty.\n Please use: find <keyword>");
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
     * A helper method to print the confirmation message after adding a new task.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui object used to print messages.
     */
    private static void printAddedTask(TaskList tasks, Ui ui) {
        ui.showLine();
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + tasks.get(tasks.size() - 1).toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }
}