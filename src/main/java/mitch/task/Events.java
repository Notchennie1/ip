package mitch.task;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Events extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Creates a new Event task by extracting the description, start time, and end time.
     *
     * @param descriptionStartEnd The raw string containing the description, "/from" time, and "/to" time.
     */
    public Events(String descriptionStartEnd){
        super(descriptionStartEnd.split("/from")[0].trim());
        startDate = descriptionStartEnd.split("/from")[1].split("/to")[0].trim();
        endDate = descriptionStartEnd.split("/from")[1].split("/to")[1].trim();
    }

    /**
     * Returns a string representation of the Event task for displaying to the user.
     *
     * @return A formatted string starting with "E" and including the start and end times.
     */
    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + startDate + "-" + endDate;
    }

    /**
     * Returns a string representation of the Event task formatted for saving to a text file.
     *
     * @return A formatted string meant to be written to the storage file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startDate + "-" + endDate;
    }
}