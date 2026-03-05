package mitch;

import mitch.task.Task;
import mitch.task.ToDo;
import mitch.task.Deadlines;
import mitch.task.Events;
import mitch.exception.MitchException;
import java.util.ArrayList;

public class Mitch {
    private Ui ui;
    private ArrayList<Task> tasks;
    private Storage storage;


    public Mitch(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit){
            String input = ui.readCommand();
            if (input.isEmpty()) continue;

            try{
                if (input.equals("bye")){
                    ui.showGoodbye();
                    isExit = true;
                } else if (input.equals("list")) {
                    ui.showLine();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    ui.showLine();
                } else if (input.startsWith("mark ")) {
                    if (input.trim().length() <= 4) {
                        throw new MitchException("OOPS!!! Please specify which task to mark.");
                    }
                    int index = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new MitchException("OOPS!!! That task number does not exist.");
                    }
                    tasks.get(index).markDone();

                    ui.showLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                    ui.showLine();
                    continue;
                } else if (input.startsWith("unmark ")) {
                    if (input.trim().length() <= 7) {
                        throw new MitchException("OOPS!!! Please specify which task to unmark.");
                    }
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new MitchException("OOPS!!! That task number does not exist.");
                    }
                    tasks.get(index).unmarkDone();

                    ui.showLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index));
                    ui.showLine();
                    continue;
                } else if (input.startsWith("todo ")) {
                    if (input.trim().length() <= 4) {
                        throw new MitchException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new ToDo(input));
                    storage.save(tasks);
                    ui.showLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    ui.showLine();
                } else if (input.startsWith("deadline ")) {
                    if (input.trim().length() <= 8) {
                        throw new MitchException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    tasks.add(new Deadlines(input));
                    storage.save(tasks);
                    ui.showLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    ui.showLine();
                } else if (input.startsWith("event ")) {
                    if (input.trim().length() <= 5) {
                        throw new MitchException("OOPS!!! The description of an event cannot be empty.");
                    }
                    tasks.add(new Events(input));
                    storage.save(tasks);
                    ui.showLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    ui.showLine();
                } else if (input.startsWith("delete ")) {
                    if (input.trim().length() <= 7) {
                        throw new MitchException("OOPS!!! Please specify which task to delete.");
                    }
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new MitchException("OOPS!!! That task number does not exist.");
                    }
                    Task removedTask = tasks.remove(index);
                    ui.showLine();
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    ui.showLine();

                } else {
                    throw new MitchException("OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
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
