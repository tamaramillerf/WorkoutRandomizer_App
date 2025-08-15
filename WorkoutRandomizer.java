import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkoutRandomizer {

    public static List<Exercise> generate(int targetTime, String category) {
        List<Exercise> allExercises = ExerciseLibrary.getAllExercises();
        List<Exercise> pool = new ArrayList<>();

        // Filter by category
        for (Exercise ex : allExercises) {
            if (category.equalsIgnoreCase("All") || ex.getCategory().equalsIgnoreCase(category)) {
                pool.add(ex);
            }
        }

        if (pool.isEmpty()) return new ArrayList<>();

        Collections.shuffle(pool);

        List<Exercise> selected = new ArrayList<>();
        int total = 0;

        while (total < targetTime) {
            boolean added = false;
            for (Exercise ex : pool) {
                if (total + ex.getDuration() <= targetTime) {
                    if (!selected.isEmpty() && selected.get(selected.size() - 1).getName().equals(ex.getName())) {
                        continue;
                    }
                    selected.add(ex);
                    total += ex.getDuration();
                    added = true;
                }
            }
            if (!added) break; // cannot add more without exceeding target
        }

        return selected;
    }
}
