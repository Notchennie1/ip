package mitch;

import mitch.task.Task;
import mitch.task.ToDo;
import mitch.task.Deadlines;
import mitch.task.Events;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving: " + e.getMessage());
        }
    }

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