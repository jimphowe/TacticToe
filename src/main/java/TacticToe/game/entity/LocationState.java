package TacticToe.game.entity;

import java.util.List;
import java.util.Random;

public enum LocationState {
    RED,
    BLACK,
    GREEN,
    EMPTY;

    private static final List<LocationState> values = List.of(values());
    private static final Random random = new Random();

    // Returns a random LocationState, including empty
    public static LocationState randomPiece() {
        return values.get(random.nextInt(values.size()));
    }

    @Override
    // Makes all states 5 characters, which makes formatting easy
    public String toString() {
        return (this.name() == "RED" ? " RED " : this.name());
    }
}
