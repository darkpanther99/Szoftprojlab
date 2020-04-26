package proto;

public class PickUpCommand implements Command {
    @Override
    public void execute(Proto state) throws ProtoException {
        state.getSelectedPlayer().pickUp();
    }

    @Override
    public String toString() {
        return "pickup";
    }
}