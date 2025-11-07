package project;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a level loaded from a text file.
 */
public class Level {

    Player player;
    ArrayList<Enemy> E = new ArrayList<>();
    ArrayList<Platform> plats = new ArrayList<>();
    ArrayList<Collectable> coins = new ArrayList<>();

    int WIDTH = 1500, HEIGHT = 1080;

    /**
     * Loads a level from a list of strings. (Level Constructor)
     *
     * @param rows
     */
    public Level(List<String> rows) {
        for (String line : rows) {
            line = line.strip();
            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] t = line.split("\\s+");
            String token = t[0];

            switch (token) {
                case "PLAYER" -> {
                    int x1 = Integer.parseInt(t[1]);
                    int y1 = Integer.parseInt(t[2]);
                    player = new Player(x1, y1);
                }
                case "WINDOW" -> {
                    WIDTH = Integer.parseInt(t[1]);
                    HEIGHT = Integer.parseInt(t[2]);
                }
                default -> {
                    if (token.startsWith("PLATFORM")) {
                        int x1 = Integer.parseInt(t[1]);
                        int y1 = Integer.parseInt(t[2]);
                        plats.add(new Platform(x1, y1));
                    } else if (token.startsWith("ITEM")) {
                        int x1 = Integer.parseInt(t[1]);
                        int y1 = Integer.parseInt(t[2]);
                        coins.add(new Collectable(x1, y1));
                    } else if (token.startsWith("ENEMY")) {
                        int x1 = Integer.parseInt(t[1]);
                        int y1 = Integer.parseInt(t[2]);
                        int roamRange1 = Integer.parseInt(t[3]);
                        double roamSpeed1 = Double.parseDouble(t[4]);
                        E.add(new Enemy(x1, y1, roamRange1, roamSpeed1));

                    } else {
                        System.err.println("Unknown token: " + token);
                    }
                }
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return E;
    }

    public ArrayList<Platform> getPlatforms() {
        return plats;
    }

    public ArrayList<Collectable> getCoins() {
        return coins;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}