package mitch;

import mitch.task.Task;
import java.util.ArrayList;

/**
 * Represents the list of tasks.
 * Contains operations to add, delete, retrieve, and find tasks within the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs TaskList initialised with an existing ArrayList of tasks
     * @param tasks The ArrayList of tasks to initialise the list with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list
     * @param task The task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from the list with a specified index
     * @param index The index of task to be removed
     * @return The task that was removed
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list with a specified index
     * @param index The index of the task to be retrieved
     * @return The task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list
     * @return The size of task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Search for tasks that contains specified keyword in their string representation
     * @param keyword The String to be searched for.
     * @return An ArrayList containing all matching tasks.
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