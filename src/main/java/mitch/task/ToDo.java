package mitch.task;

/**
 * Represents a simple ToDo task without any dates or times attached to it.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task.
     *
     * @param description The text describing the todo task.
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task for displaying to the user.
     *
     * @return A formatted string starting with "T" indicating it is a ToDo.
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task formatted for saving to a text file.
     *
     * @return A formatted string meant to be written to the storage file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}