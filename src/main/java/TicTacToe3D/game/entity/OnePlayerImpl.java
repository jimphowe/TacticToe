package TicTacToe3D.game.entity;

import java.util.Random;

public class OnePlayerImpl extends TacticToeModelImpl {
    public Integer difficulty = null;
    private final LocationState computerColor = LocationState.GREEN;
    private static final Random random = new Random();

    private void randomMoveOnFront() {
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        this.move(x,y,computerColor);
    }

    public void randomComputerMove() {
        int side = random.nextInt(6);
        switch (side) {
            case 0:
                randomMoveOnFront();
                break;
            case 1:
                this.rotateDown();
                randomMoveOnFront();
                this.rotateUp();
                break;
            case 2:
                this.rotateUp();
                randomMoveOnFront();
                this.rotateDown();
                break;
            case 3:
                this.rotateRight();
                randomMoveOnFront();
                this.rotateLeft();
                break;
            case 4:
                this.rotateLeft();
                randomMoveOnFront();
                this.rotateRight();
                break;
            case 5:
                this.rotateUp();
                this.rotateUp();
                randomMoveOnFront();
                this.rotateDown();
                this.rotateDown();
                break;
            }
    }

    public void computerMove() {
        switch (this.difficulty) {
            case 1:
                randomComputerMove();
                break;
            default:
                randomComputerMove();
        }
    }

    // Sets the board to start a game (9 Neutral Black pieces in random locations)
    public void setStartingBoard() {
        // Set everything to empty
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    this.pieces[i][j][k] = LocationState.EMPTY;
                }
            }
        }
        // Pick 9 to make Black
        for (int i = 0; i < 9; i++) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);
            int z = random.nextInt(3);
            while (this.pieces[x][y][z] == LocationState.BLACK) {
                x = random.nextInt(3);
                y = random.nextInt(3);
                z = random.nextInt(3);
            }
            this.pieces[x][y][z] = LocationState.BLACK;
        }
    }
}
