public class Exercise {
    private String name;
    private int duration; // in minutes
    private String category; // "Strength", "Cardio", "Stretching"

    public Exercise(String name, int duration, String category) {
        this.name = name;
        this.duration = duration;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getCategory() {
        return category;
    }
}
