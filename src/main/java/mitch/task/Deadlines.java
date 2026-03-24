package mitch.task;

/**
 * Represents a task that needs to be done before a specific date or time.
 */
public class Deadlines extends Task {
    protected String endDate;

    /**
     * Creates a new Deadline task by extracting the description and the deadline time.
     *
     * @param descriptionAndDeadline The raw string containing both the description and the "/by" time.
     */
    public Deadlines(String descriptionAndDeadline){
        super(descriptionAndDeadline.split("/by")[0].trim());
        endDate = descriptionAndDeadline.split("/by")[1].trim();
    }

    /**
     * Returns a string representation of the Deadline task for displaying to the user.
     *
     * @return A formatted string starting with "D" and including the deadline.
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + endDate;
    }

    /**
     * Returns a string representation of the Deadline task formatted for saving to a text file.
     *
     * @return A formatted string meant to be written to the storage file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + endDate;
    }
}