package project;

import java.util.List;

public class Level {

    private final List<String> rows;

    public Level(List<String> rows) {
        this.rows = rows;
    }

    public List<String> getRows() {
        return rows;
    }
}

