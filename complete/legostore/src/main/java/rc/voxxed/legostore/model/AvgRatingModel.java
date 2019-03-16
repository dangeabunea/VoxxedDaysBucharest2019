package rc.voxxed.legostore.model;

public class AvgRatingModel {
    private String id;
    private String legoSetName;
    private double avgRating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLegoSetName() {
        return legoSetName;
    }

    public void setLegoSetName(String legoSetName) {
        this.legoSetName = legoSetName;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}
