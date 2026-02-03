public class Events extends Task{
    protected String startDate;
    protected String endDate;

    public Events(String descriptionStartEnd){
        super(descriptionStartEnd.split("/from")[0].trim());
        startDate = descriptionStartEnd.split("/from")[1].split("/to")[0].trim();
        endDate = descriptionStartEnd.split("/from")[1].split("/to")[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
