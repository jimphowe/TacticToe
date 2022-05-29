package TicTacToe3D.game.entity;

import java.util.List;
import java.util.Random;

public enum LocationState {
    RED,
    BLACK,
    WHITE,
    EMPTY;

    private static final List<LocationState> values = List.of(values());
    private static final Random random = new Random();

    public static LocationState randomPiece() {
        return values.get(random.nextInt(values.size()));
    }

    @Override
    public String toString() {
        return (this.name() == "RED" ? " RED " : this.name());
    }
}
