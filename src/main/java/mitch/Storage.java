package mitch;

import mitch.task.Task;
import mitch.task.ToDo;
import mitch.task.Deadlines;
import mitch.task.Events;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of task data to a text file.
 * Ensures that the user's task list persists between application sessions.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs storage instance with file path
     * @param filePath The file path where the tasks will be saved and loaded from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves current tasks list to file to hard drive
     * Creates new directory and file if they do not exists
     * @param tasks The Tasklist containing the tasks to be done
     */
    public void save(TaskList tasks) {
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                // Uses toFileFormat() to ensure it saves as "T | 1 | read book"
                fw.write(tasks.get(i).toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from hard disks into ArrayList
     * Parses the test file and reconstructs the ToDo, Deadline and Event objects
     * @return The ArrayList loaded from the file, empty list if file not found
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) return loadedTasks;

        try {
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue;

                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                Task task = null;

                if (type.equals("T")) {
                    task = new ToDo(description);
                } else if (type.equals("D") && parts.length >= 4) {
                    task = new Deadlines(description + " /by " + parts[3].trim());
                } else if (type.equals("E") && parts.length >= 4) {
                    String[] timeParts = parts[3].split("-");
                    if (timeParts.length >= 2) {
                        task = new Events(description + " /from " + timeParts[0].trim() + " /to " + timeParts[1].trim());
                    }
                }

                if (task != null) {
                    if (isDone) task.markDone();
                    loadedTasks.add(task);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
        return loadedTasks;
    }
}