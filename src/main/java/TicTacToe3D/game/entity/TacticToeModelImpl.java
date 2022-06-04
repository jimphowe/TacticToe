package TicTacToe3D.game.entity;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class TacticToeModelImpl implements TacticToeModel {
    // [left-right][front-back][top-bottom]
    public LocationState[][][] pieces = new LocationState[3][3][3];
    ArrayList<LocationState[][][]> previousBoards;
    public String displayMessage = "";
    public String winner = "";

    private boolean isValidMove(int x, int y) {
        return this.pieces[x][0][y] == LocationState.EMPTY ||
                this.pieces[x][1][y] == LocationState.EMPTY ||
                this.pieces[x][2][y] == LocationState.EMPTY;
    }

    LocationState[][][] copyBoard(LocationState[][][] board) {
        LocationState[][][] copy = new LocationState[3][3][3];
        for (int i = 0; i <= 2; i++) {
            for(int j = 0; j <= 2; j++) {
                System.arraycopy(board[i][j], 0, copy[i][j], 0, 3);
            }
        }
        return copy;
    }

    @Override
    public void move(int x, int y, LocationState player) {
        if (!isValidMove(x,y)) {
            throw new IllegalArgumentException("The spot chosen must either be empty or be able to push other " +
                    "balls forward without pushing one out");
        }
        else {
            previousBoards.add(copyBoard(this.pieces));
            if (this.pieces[x][0][y] == LocationState.EMPTY) {
                this.pieces[x][0][y] = player;
            }
            else if (this.pieces[x][1][y] == LocationState.EMPTY) {
                this.pieces[x][1][y] = this.pieces[x][0][y];
                this.pieces[x][0][y] = player;
            }
            else {
                this.pieces[x][2][y] = this.pieces[x][1][y];
                this.pieces[x][1][y] = this.pieces[x][0][y];
                this.pieces[x][0][y] = player;
            }
        }
    }

    // Used in game view template
    public String[][][] getBoard() {
        String[][][] out = new String[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    out[i][j][k] = this.pieces[i][j][k].toString();
                }
            }
        }
        return out;
    }

    private boolean boardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (this.pieces[i][j][k] == LocationState.EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    LocationState locationToState(BoardLocation location) {
        return this.pieces[location.getX()][location.getY()][location.getZ()];
    }

    private boolean hasWon(LocationState player) {
        // Horizontal
        ArrayList<BoardLocation> a1 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 0, 0), new BoardLocation(2, 0, 0)));
        ArrayList<BoardLocation> a2 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 1)));
        ArrayList<BoardLocation> a3 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(1, 0, 2), new BoardLocation(2, 0, 2)));
        // Vertical
        ArrayList<BoardLocation> a4 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(0, 0, 1), new BoardLocation(0, 0, 2)));
        ArrayList<BoardLocation> a5 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 0), new BoardLocation(1, 0, 1), new BoardLocation(1, 0, 2)));
        ArrayList<BoardLocation> a6 = new ArrayList<>(Arrays.asList(new BoardLocation(2, 0, 0), new BoardLocation(2, 0, 1), new BoardLocation(2, 0, 2)));
        // Diagonal
        ArrayList<BoardLocation> a7 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 2)));
        ArrayList<BoardLocation> a8 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 2), new BoardLocation(1, 0, 1), new BoardLocation(2, 0, 0)));
        // Center
        ArrayList<BoardLocation> a9 = new ArrayList<>(Arrays.asList(new BoardLocation(1, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(1, 2, 1)));
        ArrayList<BoardLocation> a10 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 0), new BoardLocation(1, 1, 1), new BoardLocation(2, 2, 2)));
        ArrayList<BoardLocation> a11 = new ArrayList<>(Arrays.asList(new BoardLocation(0, 0, 1), new BoardLocation(1, 1, 1), new BoardLocation(2, 2, 1)));

        ArrayList<ArrayList<BoardLocation>> runs = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8,
                a9, a10, a11));
        boolean gameOver = false;
        for (int i = 0; i < 6; i++) {
            for(ArrayList<BoardLocation> run : runs) {
                if (locationToState(run.get(0)).equals(player) && locationToState(run.get(1)).equals(player)
                        && locationToState(run.get(2)).equals(player)) {
                    gameOver = true;
                }
            }
            if (i % 2 == 0) {
                this.rotateLeft();
            }
            else {
                this.rotateUp();
            }
        }
        return gameOver;
    }

    @Override
    public boolean isGameOver() {
        return hasWon(LocationState.GREEN) || hasWon(LocationState.RED) || boardFull();
    }

    public void rotateLeft() {
        LocationState[][][] rotated = new LocationState[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[i][0][j] = this.pieces[2][i][j];
                rotated[i][1][j] = this.pieces[1][i][j];
                rotated[i][2][j] = this.pieces[0][i][j];
            }
        }
        this.pieces = rotated;
    }

    public void rotateRight() {
        LocationState[][][] rotated = new LocationState[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[2][i][j] = pieces[i][0][j];
                rotated[1][i][j] = pieces[i][1][j];
                rotated[0][i][j] = pieces[i][2][j];
            }
        }
        this.pieces = rotated;
    }

    public void rotateUp() {
        LocationState[][][] rotated = new LocationState[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[i][0][j] = this.pieces[i][j][2];
                rotated[i][1][j] = this.pieces[i][j][1];
                rotated[i][2][j] = this.pieces[i][j][0];
            }
        }
        this.pieces = rotated;
    }

    public void rotateDown() {
        LocationState[][][] rotated = new LocationState[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotated[i][j][2] = pieces[i][0][j];
                rotated[i][j][1] = pieces[i][1][j];
                rotated[i][j][0] = pieces[i][2][j];
            }
        }
        this.pieces = rotated;
    }

    @Override
    public abstract void restart();

    @Override
    public abstract void undo();
}
