package model;

public class RopeRescue implements RescueStrategy {
	public void Rescue(Tile water, Tile land) {
		Player p = water.getOccupants().get(0);
		if(p != null){
			p.PlaceOn(land);
		}
	}
}
