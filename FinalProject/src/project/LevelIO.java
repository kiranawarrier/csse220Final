package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelIO {
    /**
     * Reads a text file, collects each non-blank line into a List,
     * then constructs and returns a Level.
     * Use our practice with FileIO
     */
    public static Level loadLevel(String filename) {
        List<String> rows = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            throw new RuntimeException("Level file not found: " + filename);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    rows.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading level file: " + e.getMessage(), e);
        }
        return new Level(rows);
    }
}
