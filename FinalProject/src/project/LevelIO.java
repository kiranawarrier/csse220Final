package project;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class LevelIO {

    /**
     * Reads a text file as a resource, collects each non-blank line into a List,
     * then constructs and returns a Level.
     */
    public static Level loadLevel(String resourcePath) {
        List<String> rows = new ArrayList<>();

        InputStream is = LevelIO.class.getResourceAsStream(resourcePath);

        if (is == null) {
            throw new RuntimeException("Level resource not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    rows.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading level resource: " + e.getMessage(), e);
        }
        return new Level(rows);
    }
}