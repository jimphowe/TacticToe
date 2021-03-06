package TacticToe.game.entity;

public interface TacticToeModel {
    void move(int x, int y, int face, LocationState player) throws
            IllegalArgumentException;

    boolean isGameOver();

    String[][][] getBoard();

    void rotateLeft();

    void rotateRight();

    void rotateUp();

    void rotateDown();

    void restart();

    void undo();
}
