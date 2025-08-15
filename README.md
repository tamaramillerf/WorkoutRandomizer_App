# WorkoutRandomizer_App

WorkoutRandomizer_App is a **Java-only desktop application** that generates randomized workouts based on your desired workout time and category. It uses a Java Swing GUI and allows you to save workouts as text files.

## Features
- Input workout time in minutes
- Select exercise category: Strength, Cardio, Stretching, or All
- Generate randomized workouts
- Save workouts as `.txt` files
- Optional global install for Mac/Linux and Windows

## Screenshot
![Workout Randomizer Screenshot](https://raw.githubusercontent.com/tamaramillerf/WorkoutRandomizer_App/main/workoutrandomizerscreenshot.png)

## Installation

### Prerequisites
- Java JDK 17+ installed
- Git (optional)

### Steps

**Option A: Clone using Git**
```bash
git clone https://github.com/tamaramillerf/WorkoutRandomizer_App.git
cd WorkoutRandomizer_App
```

**Option B: Download ZIP**
1. Go to [GitHub repo](https://github.com/tamaramillerf/WorkoutRandomizer_App)  
2. Click **Code → Download ZIP**  
3. Extract ZIP and navigate to folder:
```bash
cd path/to/WorkoutRandomizer_App
```

### Compile Java Files
```bash
javac *.java
```

### Run the App
```bash
java WorkoutRandomizerSwing
```

---

### Optional: Create a Runnable JAR
```bash
jar cfe WorkoutRandomizer.jar WorkoutRandomizerSwing *.class
java -jar WorkoutRandomizer.jar
```

### Optional: Install Globally

**Mac/Linux**
```bash
sudo sh -c "echo 'java -jar ~/WorkoutRandomizer_App/WorkoutRandomizer.jar' > /usr/local/bin/workoutrandomizer"
sudo chmod +x /usr/local/bin/workoutrandomizer

# Test command
workoutrandomizer
```

**Windows**
1. Create a batch file `WorkoutRandomizer.bat`:
```bat
@echo off
java -jar "C:\path\to\WorkoutRandomizer.jar"
pause
```
2. Place it in a folder in your PATH.
3. Run it from any terminal by typing `WorkoutRandomizer.bat`.

**Note:**  
- Use `sudo` on Mac/Linux only when writing to system directories.  
- You can run the app without `sudo` if you stay inside your project folder.

---

## Usage
1. Launch the app (`java WorkoutRandomizerSwing` or JAR/global command)  
2. Enter desired workout time in minutes  
3. Select a category: Strength, Cardio, Stretching, or All  
4. Click **Generate Workout**  
5. Click **Save Workout** to save your workout as a `.txt` file  

---

## Code Structure
- `Exercise.java` — Defines an exercise (name, duration, category)  
- `ExerciseLibrary.java` — Stores all exercises  
- `WorkoutRandomizer.java` — Generates workouts based on time and category  
- `WorkoutRandomizerSwing.java` — GUI, input, and save functionality  

---

## FAQ

**Q: Why do some workouts repeat exercises?**  
A: If your requested workout time is longer than the sum of all unique exercises, exercises may repeat to fill the time.

**Q: Can I generate a workout for any duration?**  
A: Yes. The app can generate workouts from 1 minute up to 60+ minutes depending on available exercises.

**Q: Can I add my own exercises?**  
A: Yes. Update `ExerciseLibrary.java` and add new exercises with name, duration, and category.

**Q: Where are workouts saved?**  
A: You can choose the folder and filename when clicking **Save Workout** in the GUI.

**Q: Do I always need `sudo` on Mac/Linux?**  
A: No. `sudo` is only needed when installing globally in protected directories like `/usr/local/bin`. You can run the app without `sudo` from your project folder.
