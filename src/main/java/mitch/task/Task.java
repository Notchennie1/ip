package mitch.task;

/**
 * Represents a general task in the Mitch chatbot.
 * This is the base class for ToDo, Deadlines, and Events.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     * By default, a task is not done when it is created.
     *
     * @param description The text describing what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Checks if the task is currently completed.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task for displaying to the user.
     *
     * @return A formatted string showing the task's status and description.
     */
    @Override
    public String toString() {
        int firstIndex = description.indexOf(" ");
        String newDescription = description.substring(firstIndex+1);
        return  (isDone ? "1" : "0") + " | " + newDescription;
    }

    /**
     * Returns a string representation of the task formatted for saving to a text file.
     *
     * @return A formatted string meant to be written to the storage file.
     */
    public String toFileFormat() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }
}