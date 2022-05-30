package TicTacToe3D.game.entity;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
    // [left-right][front-back][top-bottom]
    public LocationState[][][] pieces = new LocationState[3][3][3];
    public String displayMessage = "";
    public String gameOverMessage = "";
    private static final Random random = new Random();

    // Fills the board with uniformly random LocationStates
    public void setRandomBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    this.pieces[i][j][k] = LocationState.randomPiece();
                }
            }
        }
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

    public boolean gameOver() {
        return hasWon(LocationState.WHITE) || hasWon(LocationState.RED);
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

    private boolean isValidMove(int x, int y) {
        return this.pieces[x][0][y] == LocationState.EMPTY ||
                this.pieces[x][1][y] == LocationState.EMPTY ||
                this.pieces[x][2][y] == LocationState.EMPTY;
    }

    public void move(int x, int y, LocationState player) {
        if (!isValidMove(x,y)) {
            throw new IllegalArgumentException("The spot chosen must either be empty or be able to push other " +
                    "balls forward without pushing one out");
        }
        else {
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
}
