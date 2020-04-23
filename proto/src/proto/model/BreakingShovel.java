package proto.model;

import java.util.Random;

/**
 * Törhető ásó osztály.
 */
public class BreakingShovel implements Item {

    /**
     * Tárolja az aktuális BreakingShovelDig instance-et, amivel a játékos ásni fog.
     */
    private BreakingShovelDig instance;

    /**
     * Konstruktor létrehozza a random durability-jű törékeny ásó instance-et.
     * Mivel tesztelhetetlen a random durability(nem determinisztikus), most nem ad neki random értéket, majd a tesztelő osztály beállítja egy megadott értékre.
     */
    public BreakingShovel() {
        instance = new BreakingShovelDig(this);
    }

    /**
     * A játékos így kap ásót. Az ásója annyiszor tud majd ásni törés előtt, amennyit ez a metódus beállít neki.
     */
    public void giveTo(Player p) {
        p.setDigStrategy(instance);
    }

    /**
     * Csökkenti a tárolt lapát instance durability-jét.
     */
    public void decrementInstanceDurability() {
        instance.decrementDurability();
    }
}
