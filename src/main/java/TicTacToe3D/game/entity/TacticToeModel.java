package TicTacToe3D.game.entity;

public interface TacticToeModel {
    void move(int x, int y, LocationState player) throws
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
