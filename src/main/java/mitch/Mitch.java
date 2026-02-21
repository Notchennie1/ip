package mitch;

import mitch.task.Task;
import mitch.task.ToDo;
import mitch.task.Deadlines;
import mitch.task.Events;
import mitch.exception.MitchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Mitch {
    private static final String LINE = "____________________________________________________________";

    private static final String FILE_PATH ="./data/mitch.txt";

    private static void saveTasks(ArrayList<Task> tasks){
        try{
            File f = new File(FILE_PATH);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File f = new File(FILE_PATH);

        if (!f.exists()) {
            return loadedTasks;
        }

        try {
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                // Safety check: Skip blank or corrupted lines
                if (parts.length < 3) {
                    continue;
                }

                // 1. Grab the base variables that EVERY task shares
                // (Using .trim() just in case there are weird extra spaces like "E |0 ")
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;

                if (type.equals("T")) {
                    task = new ToDo(description);

                } else if (type.equals("D") && parts.length >= 4) {
                    String by = parts[3].trim();
                    task = new Deadlines(description + " /by " + by);

                } else if (type.equals("E") && parts.length >= 4) {
                    String timeString = parts[3];

                    String[] timeParts = timeString.split("-");

                    if (timeParts.length >= 2) {
                        String start = timeParts[0].trim(); // becomes "sat"
                        String end = timeParts[1].trim();   // becomes "sun"
                        task = new Events(description + " /from " + start + " /to " + end);
                    }
                }

                if (task != null) {
                    if (isDone) task.markDone();
                    loadedTasks.add(task);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading tasks. Starting with an empty list.");
        }
        return loadedTasks;
    }


    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello, I'm Mitch");
        System.out.println("What can I do for you");
        System.out.println(LINE);

        Scanner scanner = new Scanner(System.in); //changed sc to scanner

        ArrayList<Task> tasks = loadTasks();


        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }
            try {
                if (input.equals("bye")) {
                    System.out.println(LINE);
                    System.out.println("Bye. Hope to see you again!");
                    System.out.println(LINE);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(LINE);
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(LINE);
                    continue;
                } else if (input.startsWith("mark ")) {
                    if (input.trim().length() <= 4) {
                        throw new MitchException("OOPS!!! Please specify which task to mark.");
                    }
                    int index = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new MitchException("OOPS!!! That task number does not exist.");
                    }
                    tasks.get(index).markDone();

                    System.out.println(LINE);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                    System.out.println(LINE);
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

                    System.out.println(LINE);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index));
                    System.out.println(LINE);
                    continue;
                } else if (input.startsWith("todo ")) {
                    if (input.trim().length() <= 4) {
                        throw new MitchException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new ToDo(input));
                    saveTasks(tasks);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE);
                } else if (input.startsWith("deadline ")) {
                    if (input.trim().length() <= 8) {
                        throw new MitchException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    tasks.add(new Deadlines(input));
                    saveTasks(tasks);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE);
                } else if (input.startsWith("event ")) {
                    if (input.trim().length() <= 5) {
                        throw new MitchException("OOPS!!! The description of an event cannot be empty.");
                    }
                    tasks.add(new Events(input));
                    saveTasks(tasks);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE);
                } else if (input.startsWith("delete ")) {
                    if (input.trim().length() <= 7) {
                        throw new MitchException("OOPS!!! Please specify which task to delete.");
                    }
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new MitchException("OOPS!!! That task number does not exist.");
                    }
                    Task removedTask = tasks.remove(index);
                    System.out.println(LINE);
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE);
                } else {
                    throw new MitchException("OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
            } catch (MitchException e) {
                System.out.println(LINE);
                System.out.println(" " + e.getMessage());
                System.out.println(LINE);
            } catch (NumberFormatException e) {
                System.out.println(LINE);
                System.out.println(" OOPS!!! Please enter a valid task number.");
                System.out.println(LINE);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println(LINE);
                System.out.println(" OOPS!!! That task number does not exist.");
                System.out.println(LINE);
            }
        }
        scanner.close();
    }
}
