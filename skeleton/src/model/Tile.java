package model;
import java.util.ArrayList;

public class Tile {
	private int snow;
	private int weightLimit;
	public Tile neighborTiles;
	private ChillStormStrategy chillStormStrategy;
	private ChillWaterStrategy chillWaterStrategy;
	private Item item;
	private ArrayList<Player> occupants;
	
	public int GetSnow() {
		return snow;
	}
	
	public ArrayList<Player> GetOccupants(){
		return occupants;
	}
	
	public Tile NeighborAt(int direction) {
		return null;
	}
	
	public void DecrementSnow() {
	}
	
	
	private void Add(Player p) {
	}
	
	private void Remove(Player p) {
	}
	
	public void StepOn(Player p) {
	}
	
	public void StepOff(Player p) {
	}
	
	public void ChillStorm() {
	}
	
	public void ChillWater() {
	}
	
	public Item TakeItem() {
		return null;
	}
}
