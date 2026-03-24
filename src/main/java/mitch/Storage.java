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
 * Handles saving tasks to a text file and loading them back when the program starts.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The location of the file to save/load tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks into the text file.
     * If the folder or file doesn't exist, it creates them.
     *
     * @param tasks The TaskList containing the tasks to save.
     */
    public void save(TaskList tasks) {
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving: " + e.getMessage());
        }
    }

    /**
     * Reads the text file and converts the lines back into an ArrayList of tasks.
     *
     * @return An ArrayList of tasks loaded from the file.
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