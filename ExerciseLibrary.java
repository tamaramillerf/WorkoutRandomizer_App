import java.util.ArrayList;
import java.util.List;

public class ExerciseLibrary {
    public static List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<>();

        // Strength
        exercises.add(new Exercise("Push-ups", 3, "Strength"));
        exercises.add(new Exercise("Squats", 3, "Strength"));
        exercises.add(new Exercise("Lunges", 3, "Strength"));
        exercises.add(new Exercise("Plank", 2, "Strength"));
        exercises.add(new Exercise("Burpees", 3, "Strength"));
        exercises.add(new Exercise("Tricep Dips", 2, "Strength"));
        exercises.add(new Exercise("Glute Bridges", 3, "Strength"));

        // Cardio
        exercises.add(new Exercise("Jumping Jacks", 2, "Cardio"));
        exercises.add(new Exercise("High Knees", 2, "Cardio"));
        exercises.add(new Exercise("Mountain Climbers", 2, "Cardio"));
        exercises.add(new Exercise("Jog in Place", 3, "Cardio"));
        exercises.add(new Exercise("Star Jumps", 3, "Cardio"));
        exercises.add(new Exercise("Butt Kicks", 2, "Cardio"));

        // Stretching
        exercises.add(new Exercise("Hamstring Stretch", 3, "Stretching"));
        exercises.add(new Exercise("Quadriceps Stretch", 3, "Stretching"));
        exercises.add(new Exercise("Shoulder Stretch", 2, "Stretching"));
        exercises.add(new Exercise("Cat-Cow Stretch", 2, "Stretching"));
        exercises.add(new Exercise("Child's Pose", 3, "Stretching"));
        exercises.add(new Exercise("Torso Twist Stretch", 2, "Stretching"));

        return exercises;
    }
}
