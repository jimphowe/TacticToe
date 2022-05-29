package TicTacToe3D.game.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Piece {
    RED,
    BLACK,
    WHITE,
    EMPTY;

    private static final List<Piece> values = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random random = new Random();

    public static Piece randomPiece() {
        return values.get(random.nextInt(values.size()));
    }

    @Override
    public String toString() {
        return (this.name() == "RED" ? " RED " : this.name());
    }
}
