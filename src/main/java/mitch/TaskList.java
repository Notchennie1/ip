package mitch;

import mitch.task.Task;
import java.util.ArrayList;

/**
 * Represents the list of tasks
 * Contains methods to add, remove, get, and search for tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Create an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Create a task list with some existing tasks.
     *
     * @param tasks The starting list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to list.
     *
     * @param task The task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task from the list based on its index.
     *
     * @param index The index of the task.
     * @return The requested task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the current number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The word to search for.
     * @return A new ArrayList containing only the matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}