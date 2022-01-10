package byog.Core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;

import java.util.concurrent.TimeUnit;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 60;

    private static final long SEED = 325456;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for auto grading and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww"). The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }




    public void play() throws InterruptedException {
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // ter.renderFrame(world);

        int w = RANDOM.nextInt(WIDTH);
        int h = RANDOM.nextInt(HEIGHT);

        world[w][h] = Tileset.FLOOR;
        // ter.renderFrame(world);


        ArrayList<Integer> arr = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        arr.add(h * WIDTH + w);
        set.add(h * WIDTH + w);

        boolean[] directions = new boolean[4];
        boolean flag = false;

        int count = 0;
        while(true) {
            if (count == 1000) {
                break;
            }

            count++;
            if (flag) {
                flag = false;
                int index = RANDOM.nextInt(arr.size());

                w = arr.get(index) % WIDTH;
                h = arr.get(index) / WIDTH;
            } else {
                w = arr.get(arr.size() - 1) % WIDTH;
                h = arr.get(arr.size() - 1) / WIDTH;
            }

            directions[0] = set.contains((h + 1) * WIDTH + w);
            directions[1] = set.contains((h - 1) * WIDTH + w);
            directions[2] = set.contains(h * WIDTH + w - 1);
            directions[3] = set.contains(h * WIDTH + w + 1);

            ArrayList<Integer> a = new ArrayList<>();
            if ( (h + 1 < HEIGHT) && !directions[0]) {
                a.add(0);
            }

            if ( (h - 1 >= 0) && !directions[1]) {
                a.add(1);
            }

            if ( (w - 1 >= 0) && !directions[2]) {
                a.add(2);
            }

            if ( (w + 1 < WIDTH) && !directions[3]) {
                a.add(3);
            }

            if (a.isEmpty()) {
                count--;
                flag = true;
                continue;
            }

            int x = RANDOM.nextInt(a.size());

            switch (a.get(x)) {
                case 0:
                    world[w][h + 1] = Tileset.FLOOR;
                    arr.add((h + 1) * WIDTH + w);
                    set.add((h + 1) * WIDTH + w);
                    break;

                case 1:
                    world[w][h - 1] = Tileset.FLOOR;
                    arr.add((h - 1) * WIDTH + w);
                    set.add((h - 1) * WIDTH + w);
                    break;
                case 2:
                    world[w - 1][h] = Tileset.FLOOR;
                    arr.add(h * WIDTH + w - 1);
                    set.add(h * WIDTH + w - 1);
                    break;
                case 3:
                    world[w + 1][h] = Tileset.FLOOR;
                    arr.add(h * WIDTH + w + 1);
                    set.add(h * WIDTH + w + 1);
                    break;
                default:
                    assert false;
            }

            // ter.renderFrame(world);
            // TimeUnit.SECONDS.sleep(1);

        }

        for (int i = 0; i < arr.size(); i++) {
            w = arr.get(i) % WIDTH;
            h = arr.get(i) / WIDTH;

            if ( (h + 1 < HEIGHT) && world[w][h + 1] != Tileset.FLOOR) {
                world[w][h + 1] = Tileset.WALL;
            }

            if ( (h - 1 >= 0) && world[w][h - 1] != Tileset.FLOOR) {
                world[w][h - 1] = Tileset.WALL;
            }

            if ( (w - 1 >= 0) && world[w - 1][h] != Tileset.FLOOR) {
                world[w - 1][h] = Tileset.WALL;
            }

            if ( (w + 1 < WIDTH) && world[w + 1][h] != Tileset.FLOOR) {
                world[w + 1][h] = Tileset.WALL;
            }
        }

        ter.renderFrame(world);
    }
}
