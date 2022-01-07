package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 71;
    private static final int HEIGHT = 36;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOOR;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.WATER;
            case 4: return Tileset.FLOWER;

            default: return Tileset.TREE;
        }
    }

    private static void addHexagon(int startX, int startY, int length, TETile[][] tiles) {

        int rowLength = length;

        TETile t = randomTile();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < rowLength; j++) {
                tiles[startX + j][startY] = t;
            }
            rowLength += 2;
            startY--;
            startX--;
        }

        startX++;
        rowLength -= 2;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < rowLength; j++) {
                tiles[startX + j][startY] = t;
            }
            rowLength -= 2;

            startY--;
            startX++;
        }
    }

    private static void column(int startX, int startY, int columnSize, int length, TETile[][] tiles) {
        for (int i = 0; i < columnSize; i++) {
            addHexagon(startX, startY - i * 2 * length, length, tiles);
        }
    }

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] tiles = new TETile[WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = Tileset.NOTHING;
            }
        }

        column(35, 32, 5, 3, tiles);

        column(30, 29, 4, 3, tiles);

        column(25, 26, 3, 3, tiles);

        column(40, 29, 4, 3, tiles);

        column(45, 26, 3, 3, tiles);
        ter.renderFrame(tiles);
    }
}
