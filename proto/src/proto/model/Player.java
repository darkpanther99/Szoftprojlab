package proto.model;

import java.util.List;

public abstract class Player extends Entity {
    protected int bodyTemp;
    protected int energy;
    private Game game;
    private DigStrategy digStrategy;
    private RescueStrategy rescueStrategy;
    private WaterResistanceStrategy waterResistanceStrategy;
    protected FoodStore foodStore;
    private PartStore partStore;
    private List<Item> inventory;
    private BuildStrategy buildStrategy;

    public void step(int direction) {
        throw new RuntimeException();
    }

    public void resistWater() {
        throw new RuntimeException();
    }

    public void chill() {
        throw new RuntimeException();
    }

    public void bearAttack() {
        throw new RuntimeException();
    }

    public void decrementEnergy() {
        throw new RuntimeException();
    }

    public void incrementBodyTemp() {
        throw new RuntimeException();
    }

    public void pickUp() {
        throw new RuntimeException();
    }

    private void addToInventory(Item i) {
        throw new RuntimeException();
    }

    public void removeFromInventory(Item i) {
        throw new RuntimeException();
    }

    public void equip(int inventorySlot) {
        throw new RuntimeException();
    }

    public void toFoodStore() {
        throw new RuntimeException();
    }

    public void eatFood() {
        throw new RuntimeException();
    }

    public void dig() {
        throw new RuntimeException();
    }

    public void build() {
        throw new RuntimeException();
    }

    public void rescueTeammate(int direction) {
        throw new RuntimeException();
    }

    public void assembleFlare() {
        throw new RuntimeException();
    }

    public void setDigStrategy(DigStrategy d) {
        digStrategy = d;
    }

    public FoodStore getFoodStore() {
        return foodStore;
    }

    public void setEnergy(int n) {
        energy = n;
    }
}
