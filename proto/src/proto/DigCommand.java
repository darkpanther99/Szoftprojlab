package proto;

public class DigCommand implements Command {
    @Override
    public void execute(Proto state) {
        throw new RuntimeException();
    }

    @Override
    public String toString() {
        throw new RuntimeException();
    }
}
