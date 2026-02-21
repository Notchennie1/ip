package mitch.task;

public class Deadlines extends Task {
    protected String endDate;

    public Deadlines(String descriptionAndDeadline){
        super(descriptionAndDeadline.split("/by")[0].trim());
        endDate = descriptionAndDeadline.split("/by")[1].trim();
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + endDate;
    }
}
