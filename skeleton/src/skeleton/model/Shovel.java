package skeleton.model;

public class Shovel implements Item {
	public void GiveTo(Player p) {
		p.SetDigStrategy(new ShovelDig());
	}
}