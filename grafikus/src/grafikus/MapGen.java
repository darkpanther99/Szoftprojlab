package grafikus;

import grafikus.model.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MapGen {
    private static final boolean TESTING = false;
    private static Game game;
    private static int rows = 7;
    private static int cols = 10;

    private MapGen() {

    }
    /*
        NORTH = 0
        EAST = 1
        SOUTH = 2
        WEST = 3
    */

    private static int index(int col, int row) {
        return cols * row + col;
    }

    public static void generateMap(Game game, int r, int c) {
        rows = r;
        cols = c;
        List<Tile> tiles = game.getTiles();
        for (int i = 0; i <= cols * rows - 1; i++) {
            game.createTile(new Random().nextInt(5 + 1), new Random().nextInt(game.getPlayers().size() + 1 + 1) - 1);
            //game.createTile(0,1);
        }

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                if (y - 1 >= 0) tiles.get(index(x, y)).addNeighbor(tiles.get(index(x, y - 1)), 0); // NORTH
                if (x + 1 <= cols - 1) tiles.get(index(x, y)).addNeighbor(tiles.get(index(x + 1, y)), 1); // EAST
                if (y + 1 <= rows - 1) tiles.get(index(x, y)).addNeighbor(tiles.get(index(x, y + 1)), 2); // SOUTH
                if (x - 1 >= 0) tiles.get(index(x, y)).addNeighbor(tiles.get(index(x - 1, y)), 3); // WEST
            }
        }
        for (int x = 0; x < cols; x++) {
            tiles.get(index(x, 0)).setSnow(0);
            tiles.get(index(x, 0)).setWeightLimit(0);
            tiles.get(index(x, rows - 1)).setSnow(0);
            tiles.get(index(x, rows - 1)).setWeightLimit(0);
        }
        for (int y = 0; y < rows; y++) {
            tiles.get(index(0, y)).setSnow(0);
            tiles.get(index(0, y)).setWeightLimit(0);
            tiles.get(index(cols - 1, y)).setSnow(0);
            tiles.get(index(cols - 1, y)).setWeightLimit(0);
        }

        tiles.get(index(1, 1)).setWeightLimit(999);
        tiles.get(index(1, 1)).setSnow(3);
        if (TESTING) {
            tiles.get(index(2, 1)).setWeightLimit(999);
            tiles.get(index(2, 1)).setSnow(0);
            tiles.get(index(2, 1)).setItem(new Food());
            tiles.get(index(2, 2)).setSnow(0);
            tiles.get(index(2, 2)).setItem(new PartView(ResourceManager.flareLight));
            tiles.get(index(2, 3)).setSnow(0);
            tiles.get(index(2, 3)).setItem(new PartView(ResourceManager.flare));
            tiles.get(index(2, 4)).setSnow(0);
            tiles.get(index(2, 4)).setItem(new PartView(ResourceManager.flareGun));
            tiles.get(index(1, 2)).setWeightLimit(0);
            tiles.get(index(1, 2)).setSnow(0);
        } else {
            LinkedList<PartView> parts = new LinkedList<>(Arrays.asList(new PartView(ResourceManager.flareGun), new PartView(ResourceManager.flareLight), new PartView(ResourceManager.flare)));
            while (parts.size() > 0) {
                //random.nextInt(max - min + 1) + min
                int x = new Random().nextInt(cols - 2) + 1;
                int y = new Random().nextInt(rows - 2) + 1;
                if (!(tiles.get(index(x, y)).getItem() instanceof Empty) || tiles.get(index(x, y)).getWeightLimit() <= 0) {
                } else {
                    tiles.get(index(x, y)).setItem(parts.pop());
                }
            }
            LinkedList<Item> items = new LinkedList<>();
            int numShovels = 2;
            int numBreakingShovels = 2;
            int numRopes = 2;
            int numFood = 5;
            for (int i = 0; i < numShovels; i++) items.push(new Shovel());
            for (int i = 0; i < numBreakingShovels; i++) items.push(new BreakingShovel(3));
            for (int i = 0; i < numRopes; i++) items.push(new Rope());
            for (int i = 0; i < numFood; i++) items.push(new Food());

            while (items.size() > 0) {
                //random.nextInt(max - min + 1) + min
                int x = new Random().nextInt(cols - 2) + 1;
                int y = new Random().nextInt(rows - 2) + 1;
                if (!(tiles.get(index(x, y)).getItem() instanceof Empty) || tiles.get(index(x, y)).getWeightLimit() <= 0) {
                } else {
                    tiles.get(index(x, y)).setItem(items.pop());
                }
            }
        }


        for (Player p : game.getPlayers()) {
            p.placeOn(tiles.get(index(1, 1)));
        }
        for (PolarBear b : game.getBears()) {
            int done = 0;
            boolean badbadbear = false;
            while (done < game.getBears().size()) {
                int x = new Random().nextInt(cols - 2) + 1;
                int y = new Random().nextInt(rows - 2) + 1;
                if (tiles.get(index(x, y)).getWeightLimit() <= 0) {
                    continue;
                }
                for (Entity e : tiles.get(index(x, y)).getOccupants()) {
                    if (e instanceof Player) {
                        badbadbear = true;
                        break;
                    }
                }
                if (badbadbear) continue;

                if (b.getCurrentTile() != null) {
                    b.getCurrentTile().getOccupants().remove(b);
                }
                b.placeOn(tiles.get(index(x, y)));
                done++;
            }
        }

    }
}