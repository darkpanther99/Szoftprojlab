package proto;

import proto.model.Game;
import proto.model.Player;
import proto.model.PolarBear;
import proto.model.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Proto {
    /**
     * A teljes játékot tartalmazó változó.
     */
    public final Game game;
    /**
     * A parancsok feldolgozásának állapota.
     */
    private boolean running;
    /**
     * A parancsfeldolgozók tömbje.
     */
    private final ArrayList<CommandParser> parsers;
    /**
     * A kiválasztott mező.
     */
    private Tile selectedTile;
    /**
     * A kiválasztott játékos.
     */
    private Player selectedPlayer;
    /**
     * A kiválasztott jegesmedve.
     */
    private PolarBear selectedBear;


    /**
     * Létrehozza a játék objektumot és feltölti a parsers tömböt.
     */
    public Proto() {
        game = new Game();
        game.subscribe(new MessagePrinter(this));
        parsers = new ArrayList<>(createParses());
    }

    /**
     * Létrehozz egy listát, ami tartalmaz egyet az összes CommandParser típusból.
     *
     * @return A parsereket tartalmazó lista
     */
    private List<CommandParser> createParses() {
        return (Arrays.asList(new AssembleCommandParser(), new BuildCommandParser(), new ConnectCommandParser(),
                new DigCommandParser(), new EatCommandParser(), new EntityCommandParser(),
                new EquipCommandParser(), new ExamineCommandParser(), new ItemCommandParser(),
                new PickUpCommandParser(), new QueryCommandParser(), new RescueCommandParser(),
                new SelectCommandParser(), new StepCommandParser(), new StormCommandParser(),
                new TileCommandParser(), new TurnCommandParser(), new BuildingCommandParser()));
    }

    /**
     * Futtatja a parancsértelmezést.
     */
    public void run() throws ProtoException {
        running = true;
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (running) {
            Command c = getCommand(input);
            if (c != null)
                c.execute(this);
            else running = false;
        }
    }

    /**
     * Leállítja a parancsértelmezést.
     */
    public void stop() {
        running = false;
    }

    /**
     * Beolvas egy parancsot a standard bemenetről.
     */

    private Command getCommand(BufferedReader input) throws ProtoException {
        while (true) {
            String line;
            try {
                line = input.readLine();
                if (line == null)
                    return null; // EOF
            } catch (IOException ex) {
                throw new ProtoException("IOException occured.", ex);
            }
            line = line.trim();
            // TODO: comments
            List<String> tokens = Arrays.stream(line.split(" "))
                    .filter(x -> !x.isBlank()).collect(Collectors.toList());
            if (tokens.size() > 0) {
                for (var p : parsers) {
                    if (p.getKeyword().equals(tokens.get(0))) {
                        return p.parse(tokens);
                    }
                }
                throw new ProtoException("Unable to parse command.");
            }
        }
    }

    /**
     * Beállítja a selectedTile-t és lenullozza a selectedPlayer-t és a selectedBear-t.
     *
     * @param t: A kiválasztandó Tile
     */
    public void selectTile(Tile t) {
        selectedTile = t;
        selectedBear = null;
        selectedPlayer = null;
    }

    /**
     * Beállítja a selectedPlayer-t és lenullozza ~~a selectedTile-t és~~ a selectedBear-t.
     *
     * @param p: A kiválasztandó Player
     */
    public void selectPlayer(Player p) {
        selectedBear = null;
        selectedPlayer = p;
    }

    /**
     * Beállítja a selectedBear-t és lenullozza ~~a selectedTile-t és~~ a selectedPlayer-t.
     *
     * @param b: A kiválasztandó PolarBear
     */
    public void selectBear(PolarBear b) {
        selectedBear = b;
        selectedPlayer = null;
    }

    /**
     * Megmondja, hogy van e kiválasztott Tile.
     *
     * @return True, ha van False, ha nincs
     */
    public boolean hasSelectedTile() {
        return selectedTile != null;
    }

    /**
     * Megmondja, hogy van e kiválasztott Player.
     *
     * @return True, ha van False, ha nincs
     */
    public boolean hasSelectedPlayer() {
        return selectedPlayer != null;
    }

    /**
     * Megmondja, hogy van e kiválasztott PolarBear.
     *
     * @return True, ha van False, ha nincs
     */
    public boolean hasSelectedBear() {
        return selectedBear != null;
    }

    /**
     * Visszaadja a kiválasztott Tile-t, ha nincs ilyen, akkor kivételt dob.
     *
     * @return A kiválasztott Tile.
     */
    public Tile getSelectedTile() throws ProtoException {
        if (hasSelectedTile())
            return selectedTile;
        else throw new ProtoException("No tile is selected right now!");
    }

    /**
     * Visszaadja a kiválasztott Player-t, ha nincs ilyen, akkor kivételt dob.
     *
     * @return A kiválasztott Player.
     */
    public Player getSelectedPlayer() throws ProtoException {
        if (hasSelectedPlayer())
            return selectedPlayer;
        else throw new ProtoException("No player is selected right now!");
    }

    /**
     * Visszaadja a kiválasztott PolarBear-t, ha nincs ilyen, akkor kivételt dob.
     *
     * @return A kiválasztott PolarBear.
     */
    public PolarBear getSelectedBear() throws ProtoException {
        if (hasSelectedBear())
            return selectedBear;
        else throw new ProtoException("No bear is selected right now!");
    }

}
