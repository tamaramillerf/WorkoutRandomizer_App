import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WorkoutRandomizerSwing {

    private JFrame frame;
    private JPanel exercisePanel;
    private JComboBox<String> categoryBox;
    private JTextField timeField;
    private JButton generateButton;
    private JButton saveButton;

    private List<Exercise> currentWorkout;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new WorkoutRandomizerSwing().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Workout Randomizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Top input panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        inputPanel.add(new JLabel("Workout time (minutes):"));
        timeField = new JTextField(5);
        inputPanel.add(timeField);

        inputPanel.add(new JLabel("Category:"));
        categoryBox = new JComboBox<>(new String[]{"Strength", "Cardio", "Stretching", "All"});
        inputPanel.add(categoryBox);

        topPanel.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        generateButton = new JButton("Generate Workout");
        buttonPanel.add(generateButton);

        saveButton = new JButton("Save Workout");
        saveButton.setEnabled(false);
        buttonPanel.add(saveButton);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(topPanel, BorderLayout.NORTH);

        // Exercise panel with scroll
        exercisePanel = new JPanel();
        exercisePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        exercisePanel.setLayout(new GridLayout(0, 1, 10, 10)); // single column
        JScrollPane scrollPane = new JScrollPane(exercisePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        generateButton.addActionListener(e -> generateWorkout());
        saveButton.addActionListener(e -> saveWorkout());

        frame.setVisible(true);
    }

    private void generateWorkout() {
        exercisePanel.removeAll();

        int targetTime;
        try {
            targetTime = Integer.parseInt(timeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number for minutes.");
            return;
        }

        String category = (String) categoryBox.getSelectedItem();
        currentWorkout = WorkoutRandomizer.generate(targetTime, category);

        if (currentWorkout.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No exercises fit within that time.");
            return;
        }

        saveButton.setEnabled(true);

        int totalTime = currentWorkout.stream().mapToInt(Exercise::getDuration).sum();
        JLabel timeLabel = new JLabel("Total workout time: " + totalTime + " minutes");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exercisePanel.add(timeLabel);

        // Display exercises
        for (Exercise ex : currentWorkout) {
            JLabel nameLabel = new JLabel(ex.getName() + " - " + ex.getDuration() + " min");
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            exercisePanel.add(nameLabel);
        }

        exercisePanel.revalidate();
        exercisePanel.repaint();
    }

    private void saveWorkout() {
        if (currentWorkout == null || currentWorkout.isEmpty()) return;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Workout As");

        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File file = fileChooser.getSelectedFile().getAbsoluteFile();
                if (!file.getName().toLowerCase().endsWith(".txt")) {
                    file = new java.io.File(file.getParentFile(), file.getName() + ".txt");
                }

                if (file.exists()) {
                    int confirm = JOptionPane.showConfirmDialog(frame,
                            "File already exists. Overwrite?",
                            "Confirm Save",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm != JOptionPane.YES_OPTION) return;
                }

                try (FileWriter writer = new FileWriter(file, false)) {
                    writer.write("Workout Plan:\n");
                    int totalTime = 0;
                    for (Exercise ex : currentWorkout) {
                        writer.write(ex.getName() + " - " + ex.getDuration() + " min\n");
                        totalTime += ex.getDuration();
                    }
                    writer.write("Total workout time: " + totalTime + " minutes\n");
                }

                JOptionPane.showMessageDialog(frame, "Workout saved successfully to:\n" + file.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error saving workout: " + e.getMessage());
            }
        }
    }
}
