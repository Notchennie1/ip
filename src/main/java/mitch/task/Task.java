package mitch.task;

public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        int firstIndex = description.indexOf(" ");
        String newDescription = description.substring(firstIndex+1);
        return  (isDone ? "1" : "0") + " | " + newDescription;
    }
}
