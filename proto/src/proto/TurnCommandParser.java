package proto;

import java.util.List;

public class TurnCommandParser implements CommandParser {
    private String keyword = "turn";

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public Command parse(List<String> tokens) throws ProtoException {
        if(tokens.size() < 1 || !tokens.get(0).contentEquals(keyword)) {
            throw new ProtoException("Rossz bemenet");
        }

        return new TurnCommand();
    }
}
